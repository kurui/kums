package com.kurui.kums.transaction;

import java.util.Date;
import com.kurui.kums.base.util.DateUtil;
import com.kurui.kums.base.util.PingYin;
import com.kurui.kums.base.util.StringUtil;
import com.kurui.kums.transaction.entity._DataType;

public class DataType extends _DataType {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private DataType supDataType;

	// 状态
	public static final long STATES_1 = 1;// 有效
	public static final long STATES_0 = 0;// 无效
	private String charSet = "GBK";
	
	
	public String getCreateDate() {
		String mydate = "";
		if (this.createTime != null && "".equals(createTime) == false) {
			Date tempDate = new Date(createTime.getTime());
			mydate = DateUtil.getDateString(tempDate, "yyyy-MM-dd");
		}
		return mydate;
	}
	
	public String getFormatCreateTime() {
		String mydate = "";
		if (this.createTime != null && "".equals(createTime) == false) {
			Date tempDate = new Date(createTime.getTime());
			mydate = DateUtil.getDateString(tempDate, "yyyy-MM-dd HH:mm:ss");
		}
		return mydate;
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

	public DataType getSupDataType() {
		return supDataType;
	}

	public void setSupDataType(DataType supDataType) {
		this.supDataType = supDataType;
	}

}
