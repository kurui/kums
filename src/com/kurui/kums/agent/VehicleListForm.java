package com.kurui.kums.agent;

import com.kurui.kums.base.ListActionForm;

public class VehicleListForm extends ListActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public long id = Long.valueOf(0);
	public long agentId = Long.valueOf(0);

	public Long status = Long.valueOf(0);

	public String contactWay = "";

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public long getAgentId() {
		return agentId;
	}

	public void setAgentId(long agentId) {
		this.agentId = agentId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getContactWay() {
		return contactWay;
	}

	public void setContactWay(String contactWay) {
		this.contactWay = contactWay;
	}

}
