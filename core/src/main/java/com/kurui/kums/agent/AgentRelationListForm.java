package com.kurui.kums.agent;

import java.sql.Timestamp;

import com.kurui.kums.base.ListActionForm;

public class AgentRelationListForm extends ListActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public long id = Long.valueOf(0);
	public long agentId = Long.valueOf(0);


	public Long relationType=Long.valueOf(0);
	public Timestamp updateDate;
	public Long status=Long.valueOf(0);
	public String userNo= "";

	public String contactWay = "";
	
	public String agentIds="";
	private String[] leftUserID = new String[0];
	private String[] rightUserID = new String[0];
	private String[] leftRoleID = new String[0];
	private String[] rightRoleID = new String[0];
	private String[] selectedRoleItems = new String[0];
	private String[] selectedRightItems = new String[0];
	private String selectedRightItemsStr="";
	private int selectedRightItem = -1;
	private String xml ="";
	private int count =0;

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

	public Long getRelationType() {
		return relationType;
	}

	public void setRelationType(Long relationType) {
		this.relationType = relationType;
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

	public String getAgentIds() {
		return agentIds;
	}

	public void setAgentIds(String agentIds) {
		this.agentIds = agentIds;
	}



	public int getCount() {
		return count;
	}

	public String[] getLeftUserID() {
		return leftUserID;
	}

	public void setLeftUserID(String[] leftUserID) {
		this.leftUserID = leftUserID;
	}

	public String[] getRightUserID() {
		return rightUserID;
	}

	public void setRightUserID(String[] rightUserID) {
		this.rightUserID = rightUserID;
	}

	public String[] getLeftRoleID() {
		return leftRoleID;
	}

	public void setLeftRoleID(String[] leftRoleID) {
		this.leftRoleID = leftRoleID;
	}

	public String[] getRightRoleID() {
		return rightRoleID;
	}

	public void setRightRoleID(String[] rightRoleID) {
		this.rightRoleID = rightRoleID;
	}

	public String[] getSelectedRoleItems() {
		return selectedRoleItems;
	}

	public void setSelectedRoleItems(String[] selectedRoleItems) {
		this.selectedRoleItems = selectedRoleItems;
	}

	public String[] getSelectedRightItems() {
		return selectedRightItems;
	}

	public void setSelectedRightItems(String[] selectedRightItems) {
		this.selectedRightItems = selectedRightItems;
	}

	public String getSelectedRightItemsStr() {
		return selectedRightItemsStr;
	}

	public void setSelectedRightItemsStr(String selectedRightItemsStr) {
		this.selectedRightItemsStr = selectedRightItemsStr;
	}

	public int getSelectedRightItem() {
		return selectedRightItem;
	}

	public void setSelectedRightItem(int selectedRightItem) {
		this.selectedRightItem = selectedRightItem;
	}

	public String getXml() {
		return xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}

	public void setCount(int count) {
		this.count = count;
	}


}
