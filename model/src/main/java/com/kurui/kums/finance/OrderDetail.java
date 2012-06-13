package com.kurui.kums.finance;

import com.kurui.kums.finance.entity._OrderDetail;


public class OrderDetail extends _OrderDetail {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 状态
	public static final long STATES_1 = 1;// 有效
	public static final long STATES_0 = 0;// 无效

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
