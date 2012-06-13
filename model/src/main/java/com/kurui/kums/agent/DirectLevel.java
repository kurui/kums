package com.kurui.kums.agent;


import com.kurui.kums.agent.entity._DirectLevel;
import com.kurui.kums.base.Constant;

public class DirectLevel extends _DirectLevel {

	private static final long serialVersionUID = 1L;

	// 类型
	public static final long TYPE_1 = 1;// 天狮系统

	// 状态
	public static final long STATES_1 = 1;// 有效
	public static final long STATES_0 = 0;// 无效

	// 状态
	public String getTypeInfo() {
		if (this.getType() != null) {
			if (this.getType() == TYPE_1) {
				return "天狮系统";
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

	private String seqIndexInfo = "";

	public String getSeqIndexInfo() {
		if (seqIndex != null) {
			seqIndexInfo = "第" + (Constant.toLong(seqIndex)) + "级";
		}
		return seqIndexInfo;
	}

	public void setSeqIndexInfo(String seqIndexInfo) {
		this.seqIndexInfo = seqIndexInfo;
	}

	public String getDirectDiscountInfo() {
		String directDiscountInfo = "";
		if (directDiscount != null) {
			directDiscountInfo = Constant.toPercentByBigDecimal(directDiscount);
		}
		return directDiscountInfo;
	}

	public String getNormalDiscountInfo() {
		String normalDiscountInfo = "";
		if (normalDiscount != null) {
			normalDiscountInfo = Constant.toPercentByBigDecimal(normalDiscount);
		}
		return normalDiscountInfo;
	}

	public String getDirectorDiscountInfo() {
		String directorDiscountInfo = "";
		if (directorDiscount != null) {
			directorDiscountInfo = Constant
					.toPercentByBigDecimal(directorDiscount);
		}
		return directorDiscountInfo;
	}

	public String getManagerDiscountInfo() {
		String managerDiscountInfo = "";
		if (managerDiscount != null) {
			managerDiscountInfo = Constant
					.toPercentByBigDecimal(managerDiscount);
		}
		return managerDiscountInfo;
	}

	public String getAdManagerDiscountInfo() {
		String adManagerDiscountInfo = "";
		if (adManagerDiscount != null) {
			adManagerDiscountInfo = Constant
					.toPercentByBigDecimal(adManagerDiscount);
		}
		return adManagerDiscountInfo;
	}

}
