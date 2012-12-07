package com.kurui.kums.library;

import java.util.Date;

import com.kurui.kums.base.util.time.DateUtil;
import com.kurui.kums.library._entity._ImageDependent;

public class ImageDependent extends _ImageDependent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// 类型
	public static final long TYPE_1 = 1;// 默认
	public static final long TYPE_2 = 2;// 封面

	// 状态
	public static final long STATES_1 = 1;// 有效
	public static final long STATES_0 = 0;// 无效
	
	
	public String getTypeInfo() {
		if (this.getType() != null) {
			if (this.getType().intValue() == TYPE_1) {
				return "默认";
			}else if (this.getType().intValue() == TYPE_2) {
				return "封面";
			}  else {
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

}
