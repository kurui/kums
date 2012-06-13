package com.kurui.kums.agent;

import com.kurui.kums.agent.entity._ShareHolder;

public class ShareHolder extends _ShareHolder {

	private static final long serialVersionUID = 1L;

	private long agentId = Long.valueOf(0);
	private String agentNo = "";
	private long companyId = Long.valueOf(0);

	// 控制权
	public static final long CONTRL_TYPE_1 = 1;// 绝对控股
	public static final long CONTRL_TYPE_2 = 2;// 相对控股
	public static final long CONTRL_TYPE_3 = 3;// 参股
	public static final long CONTRL_TYPE_11 = 11;// 关联

	// 状态
	public static final long STATES_1 = 1;// 有效
	public static final long STATES_0 = 0;// 无效
	
	// 状态
	public String getControlTypeInfo() {
		if (this.getControlType() != null) {
			if (this.getControlType() == CONTRL_TYPE_1) {
				return "绝对控股";
			} else if (this.getControlType().intValue() == CONTRL_TYPE_2) {
				return "相对控股";
			}else if (this.getControlType().intValue() == CONTRL_TYPE_3) {
				return "参股";
			}else if (this.getControlType().intValue() == CONTRL_TYPE_11) {
				return "关联";
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

	public long getAgentId() {
		return agentId;
	}

	public void setAgentId(long agentId) {
		this.agentId = agentId;
	}

	public String getAgentNo() {
		return agentNo;
	}

	public void setAgentNo(String agentNo) {
		this.agentNo = agentNo;
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

}
