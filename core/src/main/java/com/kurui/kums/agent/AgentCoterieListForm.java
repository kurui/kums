package com.kurui.kums.agent;

import com.kurui.kums.base.ListActionForm;

public class AgentCoterieListForm extends ListActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public long id = Long.valueOf(0);
	public long coterieId = Long.valueOf(0);
	public long subAgentId = Long.valueOf(0);

	public Long status = Long.valueOf(0);


	public String contactWay = "";

	public long getCoterieId() {
		return coterieId;
	}

	public void setCoterieId(long coterieId) {
		this.coterieId = coterieId;
	}

	public long getSubAgentId() {
		return subAgentId;
	}

	public void setSubAgentId(long subAgentId) {
		this.subAgentId = subAgentId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
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
