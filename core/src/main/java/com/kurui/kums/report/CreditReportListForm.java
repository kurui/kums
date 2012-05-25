package com.kurui.kums.report;

import com.kurui.kums.base.ListActionForm;

public class CreditReportListForm extends ListActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public long id = Long.valueOf(0);
	public Long agentId = Long.valueOf(0);
	public String agentName = "";

	public Long status = Long.valueOf(0);
	public Long type = Long.valueOf(0);


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Long getAgentId() {
		return agentId;
	}

	public void setAgentId(Long agentId) {
		this.agentId = agentId;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}
}
