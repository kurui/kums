package com.kurui.kums.market;

import com.kurui.kums.market.entity._SaleEvent;

public class SaleEvent extends _SaleEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 类型
	public static final long TYPE_1 = 1;// 默认

	// 状态
	public static final long STATES_1 = 1;// 有效
	public static final long STATES_0 = 0;// 无效

	public String getTypeInfo() {
		if (this.getType() != null) {
			if (this.getType().intValue() == TYPE_1) {
				return "默认";
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

}
