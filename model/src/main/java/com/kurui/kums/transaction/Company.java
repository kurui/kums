package com.kurui.kums.transaction;

import java.util.Date;

import com.kurui.kums.base.util.DateUtil;
import com.kurui.kums.base.util.PingYin;
import com.kurui.kums.base.util.StringUtil;
import com.kurui.kums.transaction.entity._Company;
import com.kurui.kums.transaction.util.DataTypeStore;

public class Company extends _Company {
	private static final long serialVersionUID = 1L;

	private String no = "";

	private String[] provideChainGroup = new String[0];

	// 类型
	public static final long TYPE_1 = 1;// 集团下属
	public static final long TYPE_2 = 2;// 客户公司
	public static final long TYPE_3 = 3;// 合作企业

	// 状态
	public static final long STATES_1 = 1;// 有效
	public static final long STATES_0 = 0;// 无效

	//
	public static final long SYSTEM_COMPANY_ID = 534;//
	public static final long SYSTEM_FINANCE_COMPANY_ID = 536;//

	private String charSet = "GBK";

	public void setProvideChainGroup(String[] provideChainGroup) {
		this.provideChainGroup = provideChainGroup;
	}

	public String[] getProvideChainGroup() {
		if (provideChainGroup == null) {
			if (provideChain != null) {
				provideChainGroup = StringUtil
						.getSplitString(provideChain, ",");
			}
		}
		return provideChainGroup;
	}

	public String getProvideChainInfo() {
		String info = "";
		if (provideChain != null) {
			String[] provideChainGroup = StringUtil.getSplitString(
					provideChain, ",");
			for (int i = 0; i < provideChainGroup.length; i++) {
				String no = provideChainGroup[i];
				String name = DataTypeStore.getDataTypeNameByNo(no);
				info += name + ",";
			}
			if (info.indexOf(",", info.length() - 1) > 1) {
				info = info.substring(0, info.length() - 1);
			}
		}
		return info;
	}

	public String getShowName() {
		if (this.name != null && "".equals(this.name) == false) {
			if (StringUtil.isNumeric(this.name.substring(0, 1))
					|| StringUtil.isLetter(this.name.substring(0, 1))) {
				return this.name;
			} else {
				String myFirstLetter = PingYin.getFirstLetter(this.name,
						charSet);
				if (myFirstLetter != null && myFirstLetter.length() > 1) {
					myFirstLetter = myFirstLetter.substring(0, 1);
					return myFirstLetter + "-" + this.name;
				}
			}
		}
		return this.name;
	}

	public String getTypeInfo() {
		if (this.getType() != null) {
			if (this.getType().intValue() == TYPE_1) {
				return "集团下属";
			} else if (this.getType().intValue() == TYPE_2) {
				return "客户公司";
			} else if (this.getType().intValue() == TYPE_3) {
				return "合作企业";
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	// 状态
	public String getStatusInfo() {
		if (this.getStatus() != null) {
			if (this.getStatus() == STATES_1) {
				return "有效";
			} else if (this.getStatus().intValue() == STATES_0) {
				return "无效";
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	public String getUpdateDate() {
		String mydate = "";
		if (this.updateTime != null && "".equals(updateTime) == false) {
			Date tempDate = new Date(updateTime.getTime());
			mydate = DateUtil.getDateString(tempDate, "yyyy-MM-dd");
		}
		return mydate;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

}
