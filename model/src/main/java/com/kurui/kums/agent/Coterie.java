package com.kurui.kums.agent;

import com.kurui.kums.agent.entity._Coterie;

public class Coterie extends _Coterie {

	private static final long serialVersionUID = 1L;

	private long rootAgentId = Long.valueOf(0);
	

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

	public long getRootAgentId() {
		return rootAgentId;
	}

	public void setRootAgentId(long rootAgentId) {
		this.rootAgentId = rootAgentId;
	}

}
