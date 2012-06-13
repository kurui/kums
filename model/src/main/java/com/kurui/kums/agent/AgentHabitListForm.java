package com.kurui.kums.agent;

import java.sql.Timestamp;

import com.kurui.kums.base.ListActionForm;

public class AgentHabitListForm extends ListActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public long id = Long.valueOf(0);
	public long agentId = Long.valueOf(0);

	public String contactWay = "";

	public Long airplaneSeat = Long.valueOf(0);
	public Timestamp updateDate;
	public Long status = Long.valueOf(0);
	public String userNo = "";

	public Long getAirplaneSeat() {
		return airplaneSeat;
	}

	public void setAirplaneSeat(Long airplaneSeat) {
		this.airplaneSeat = airplaneSeat;
	}

	public Timestamp getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getContactWay() {
		return contactWay;
	}

	public void setContactWay(String contactWay) {
		this.contactWay = contactWay;
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

}
