package com.kurui.kums.transaction;

import java.util.Date;

import com.kurui.kums.base.util.DateUtil;
import com.kurui.kums.base.util.PingYin;
import com.kurui.kums.base.util.StringUtil;
import com.kurui.kums.transaction.entity._Platform;

public class Platform extends _Platform {
	private static final long serialVersionUID = 1L;

	// 交易类型
	public static final long TYPE_1 = 1;// 买入平台
	public static final long TYPE_2 = 2;// 卖出平台
	public static final long TYPE_3 = 3;// 买卖平台

	// 出票类型
	public static final long DRAW_TYPE_0 = 0;// 交易平台
	public static final long DRAW_TYPE_1 = 1;// 网电
	public static final long DRAW_TYPE_2 = 2;// BSP

	//
	public static final long SYSTEM_PLATFORM_ID = 520;//

	// 状态
	public static final long STATES_0 = 0;// 有效
	public static final long STATES_1 = 1;// 无效
	private String charSet = "GBK";

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
				return "买入平台";
			} else if (this.getType().intValue() == TYPE_2) {
				return "卖出平台";
			} else if (this.getType().intValue() == TYPE_3) {
				return "买卖平台";
			} else {
				return "";
			}
		} else {
			return "";
		}
	}

	// 状态
	public String getStatusInfo() {
		if (this.getStatus() != null) {
			if (this.getStatus() == STATES_0) {
				return "有效";
			} else if (this.getStatus().intValue() == STATES_1) {
				return "无效";
			} else {
				return "";
			}
		} else {
			return "";
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
}
