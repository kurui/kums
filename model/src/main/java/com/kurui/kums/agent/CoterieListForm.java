package com.kurui.kums.agent;

import com.kurui.kums.base.ListActionForm;

public class CoterieListForm extends ListActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public long id = Long.valueOf(0);
	public long rootAgentId = Long.valueOf(0);

	public String contactWay = "";

	public Long status = Long.valueOf(0);

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

	public long getRootAgentId() {
		return rootAgentId;
	}

	public void setRootAgentId(long rootAgentId) {
		this.rootAgentId = rootAgentId;
	}

	public String getContactWay() {
		return contactWay;
	}

	public void setContactWay(String contactWay) {
		this.contactWay = contactWay;
	}

}
