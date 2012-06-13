package com.kurui.kums.market;

import java.util.Date;

import com.kurui.kums.base.util.DateUtil;
import com.kurui.kums.market.entity._EstateDish;

public class EstateDish extends _EstateDish {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long companyId = Long.valueOf(0);

	private String beginDate = "";
	private String entranceDate = "";

	// 类型
	public static final long TYPE_1 = 1;// 住宅
	public static final long TYPE_11 = 11;// 写字楼

	// 状态
	public static final long STATES_1 = 1;// 有效
	public static final long STATES_0 = 0;// 无效

	public String getTypeInfo() {
		if (this.getType() != null) {
			if (this.getType().intValue() == TYPE_1) {
				return "住宅";
			} else if (this.getType().intValue() == TYPE_11) {
				return "写字楼";
			} else {
				return "未定义";
			}
		} else {
			return "";
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

	public String getBeginDate() {

		if (this.beginTime != null && "".equals(beginTime) == false) {
			Date tempDate = new Date(beginTime.getTime());
			beginDate = DateUtil.getDateString(tempDate, "yyyy-MM-dd");
		}
		return beginDate;
	}

	public String getEntranceDate() {

		if (this.entranceTime != null && "".equals(entranceTime) == false) {
			Date tempDate = new Date(entranceTime.getTime());
			entranceDate = DateUtil.getDateString(tempDate, "yyyy-MM-dd");
		}
		return entranceDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public void setEntranceDate(String entranceDate) {
		this.entranceDate = entranceDate;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

}
