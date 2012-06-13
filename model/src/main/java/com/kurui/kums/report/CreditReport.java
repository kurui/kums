package com.kurui.kums.report;

import com.kurui.kums.report.entity._CreditReport;

public class CreditReport extends _CreditReport {

	private static final long serialVersionUID = 1L;

	private Long agentId = Long.valueOf(0);

	// 类型
	public static final long TYPE_1 = 1;// 应收欠款
	public static final long TYPE_11 = 11;//应付欠款

	public Long getAgentId() {
		return agentId;
	}

	public void setAgentId(Long agentId) {
		this.agentId = agentId;
	}

}
