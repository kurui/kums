package com.kurui.kums.agent;

import com.kurui.kums.base.ListActionForm;

public class AgentListForm extends ListActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id = Long.valueOf(0);
	private long companyId = Long.valueOf(0);// 公司ID
	private String name;// 客户名称
	private Long type = Long.valueOf(0);// 客户类型

	private long directLevelId = Long.valueOf(0);
	private String directLevelIds = "";

	private String[] agentNames;

	private String contactWay;// 联系方式(qq/email/telphone)
	
	private String knowPlace;
	private String stampGroup;

	public Long loyalIndex = Long.valueOf(0);
	public Long friendIndex = Long.valueOf(0);
	public Long assetIndex = Long.valueOf(0);
	public Long specialIndex = Long.valueOf(0);
	public Long tightIndex = Long.valueOf(0);

	private String content; // 短信发送的内容
	private String receiver; // 短信接收人号码集合
	private String operatorObject; // 修改对象 (all:全部;b2c:团队;team:团队)

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getDirectLevelId() {
		return directLevelId;
	}

	public void setDirectLevelId(long directLevelId) {
		this.directLevelId = directLevelId;
	}

	public String getDirectLevelIds() {
		return directLevelIds;
	}

	public void setDirectLevelIds(String directLevelIds) {
		this.directLevelIds = directLevelIds;
	}

	public String getOperatorObject() {
		return operatorObject;
	}

	public void setOperatorObject(String operatorObject) {
		this.operatorObject = operatorObject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactWay() {
		return contactWay;
	}

	public void setContactWay(String contactWay) {
		this.contactWay = contactWay;
	}

	public String[] getAgentNames() {
		return agentNames;
	}

	public void setAgentNames(String[] agentNames) {
		this.agentNames = agentNames;
	}

	public Long getLoyalIndex() {
		return loyalIndex;
	}

	public void setLoyalIndex(Long loyalIndex) {
		this.loyalIndex = loyalIndex;
	}

	public Long getFriendIndex() {
		return friendIndex;
	}

	public void setFriendIndex(Long friendIndex) {
		this.friendIndex = friendIndex;
	}

	public Long getAssetIndex() {
		return assetIndex;
	}

	public void setAssetIndex(Long assetIndex) {
		this.assetIndex = assetIndex;
	}

	public Long getSpecialIndex() {
		return specialIndex;
	}

	public void setSpecialIndex(Long specialIndex) {
		this.specialIndex = specialIndex;
	}

	public Long getTightIndex() {
		return tightIndex;
	}

	public void setTightIndex(Long tightIndex) {
		this.tightIndex = tightIndex;
	}

	public String getKnowPlace() {
		return knowPlace;
	}

	public void setKnowPlace(String knowPlace) {
		this.knowPlace = knowPlace;
	}

	public String getStampGroup() {
		return stampGroup;
	}

	public void setStampGroup(String stampGroup) {
		this.stampGroup = stampGroup;
	}
	
	

}
