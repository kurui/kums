package com.kurui.kums.agent._entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import com.kurui.kums.agent.AgentContact;
import com.kurui.kums.agent.AgentEvent;
import com.kurui.kums.agent.AgentResume;
import com.kurui.kums.agent.ShareHolder;

/**
 * Agent generated by hbm2java
 */

public class _Agent

extends org.apache.struts.action.ActionForm implements Cloneable {
	private static final long serialVersionUID = 1L;

	// Fields

	protected long id;
	protected String agentNo;
	protected String cardNo;
	protected String name;
	protected String stampGroup;
	protected Long type;
	protected String memo;
	protected Long status;
	protected BigDecimal physicalAmount;
	protected BigDecimal virtualAmount;
	protected BigDecimal totalIntegral;
	protected Long grade;
	protected String userName;
	protected Timestamp updateTime;
	protected Long sex;
	protected String birthday;
	protected String marriage;
	protected String language;
	protected String strongSuit;
	protected String assetInfo;
	protected String creditInfo;
	protected BigDecimal creditAmount;
	protected String healthInfo;
	protected Long loyalIndex;
	protected Long assetIndex;
	protected Long friendIndex;
	protected Long specialIndex;
	protected Long tightIndex;
	
	protected String treeFileName;

	protected com.kurui.kums.transaction.Company company;
	protected com.kurui.kums.agent.DirectLevel directLevel;
	protected com.kurui.kums.agent.AgentHabit agentHabit;

	protected java.util.Set financeOrders = new java.util.HashSet(0);
	protected java.util.Set agentAccounts = new java.util.HashSet(0);
	protected java.util.Set vehicles = new java.util.HashSet(0);
	protected java.util.Set rootAgents = new java.util.HashSet(0);
	protected java.util.Set relateAgents = new java.util.HashSet(0);
	protected java.util.Set crossAgents = new HashSet(0);
	protected java.util.Set agentCoteries = new HashSet(0);
	protected java.util.Set shareHolders = new HashSet<ShareHolder>(0);
	protected java.util.Set agentContacts = new HashSet<AgentContact>(0);
	protected java.util.Set agentEvents = new HashSet<AgentEvent>(0);
	protected java.util.Set agentResumes = new HashSet<AgentResume>(0);

	// Constructors

	// Property accessors
	
	


	public java.util.Set getRootAgents() {
		return rootAgents;
	}

	public java.util.Set getRelateAgents() {
		return relateAgents;
	}

	public void setAgentAccounts(java.util.Set agentAccounts) {
		this.agentAccounts = agentAccounts;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAgentNo() {
		return agentNo;
	}

	public void setAgentNo(String agentNo) {
		this.agentNo = agentNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStampGroup() {
		return stampGroup;
	}

	public void setStampGroup(String stampGroup) {
		this.stampGroup = stampGroup;
	}

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	

	public BigDecimal getPhysicalAmount() {
		return physicalAmount;
	}

	public void setPhysicalAmount(BigDecimal physicalAmount) {
		this.physicalAmount = physicalAmount;
	}

	public BigDecimal getVirtualAmount() {
		return virtualAmount;
	}

	public void setVirtualAmount(BigDecimal virtualAmount) {
		this.virtualAmount = virtualAmount;
	}

	public BigDecimal getTotalIntegral() {
		return totalIntegral;
	}

	public void setTotalIntegral(BigDecimal totalIntegral) {
		this.totalIntegral = totalIntegral;
	}

	public Long getGrade() {
		return grade;
	}

	public void setGrade(Long grade) {
		this.grade = grade;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public java.util.Set getFinanceOrders() {
		return financeOrders;
	}

	public void setFinanceOrders(java.util.Set financeOrders) {
		this.financeOrders = financeOrders;
	}

	public com.kurui.kums.transaction.Company getCompany() {
		return this.company;
	}

	public void setCompany(com.kurui.kums.transaction.Company company) {
		this.company = company;
	}

	// The following is extra code specified in the hbm.xml files

	public Long getLoyalIndex() {
		return loyalIndex;
	}

	public void setLoyalIndex(Long loyalIndex) {
		this.loyalIndex = loyalIndex;
	}

	public Long getAssetIndex() {
		return assetIndex;
	}

	public void setAssetIndex(Long assetIndex) {
		this.assetIndex = assetIndex;
	}

	public Long getFriendIndex() {
		return friendIndex;
	}

	public void setFriendIndex(Long friendIndex) {
		this.friendIndex = friendIndex;
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

	public com.kurui.kums.agent.DirectLevel getDirectLevel() {
		return directLevel;
	}

	public void setDirectLevel(com.kurui.kums.agent.DirectLevel directLevel) {
		this.directLevel = directLevel;
	}

	public String getHealthInfo() {
		return healthInfo;
	}

	public void setHealthInfo(String healthInfo) {
		this.healthInfo = healthInfo;
	}

	
	
	public String getTreeFileName() {
		return treeFileName;
	}

	public void setTreeFileName(String treeFileName) {
		this.treeFileName = treeFileName;
	}

	public Object clone() {
		Object o = null;
		try {
			o = super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return o;
	}

	private String thisAction = "";

	public String getThisAction() {
		return thisAction;
	}

	public void setThisAction(String thisAction) {
		this.thisAction = thisAction;
	}

	private int index = 0;

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public Long getSex() {
		return sex;
	}

	public void setSex(Long sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getMarriage() {
		return marriage;
	}

	public void setMarriage(String marriage) {
		this.marriage = marriage;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getStrongSuit() {
		return strongSuit;
	}

	public void setStrongSuit(String strongSuit) {
		this.strongSuit = strongSuit;
	}

	public String getAssetInfo() {
		return assetInfo;
	}

	public void setAssetInfo(String assetInfo) {
		this.assetInfo = assetInfo;
	}

	public String getCreditInfo() {
		return creditInfo;
	}

	public void setCreditInfo(String creditInfo) {
		this.creditInfo = creditInfo;
	}

	public BigDecimal getCreditAmount() {
		return creditAmount;
	}

	public void setCreditAmount(BigDecimal creditAmount) {
		this.creditAmount = creditAmount;
	}


	public void setRootAgents(java.util.Set rootAgents) {
		this.rootAgents = rootAgents;
	}

	public void setRelateAgents(java.util.Set relateAgents) {
		this.relateAgents = relateAgents;
	}

	public java.util.Set getAgentAccounts() {
		return agentAccounts;
	}

	public Set getCrossAgents() {
		return crossAgents;
	}

	public void setCrossAgents(Set crossAgents) {
		this.crossAgents = crossAgents;
	}
	
	

	public java.util.Set getAgentContacts() {
		return agentContacts;
	}

	public void setAgentContacts(java.util.Set agentContacts) {
		this.agentContacts = agentContacts;
	}

	public java.util.Set getAgentEvents() {
		return agentEvents;
	}

	public void setAgentEvents(java.util.Set agentEvents) {
		this.agentEvents = agentEvents;
	}

	public java.util.Set getAgentResumes() {
		return agentResumes;
	}

	public void setAgentResumes(java.util.Set agentResumes) {
		this.agentResumes = agentResumes;
	}
	
	public Set<ShareHolder> getShareHolders() {
		return shareHolders;
	}

	public void setShareHolders(java.util.Set shareHolders) {
		this.shareHolders = shareHolders;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public Set getAgentCoteries() {
		return agentCoteries;
	}

	public void setAgentCoteries(Set agentCoteries) {
		this.agentCoteries = agentCoteries;
	}




	public java.util.Set getVehicles() {
		return vehicles;
	}

	public void setVehicles(java.util.Set vehicles) {
		this.vehicles = vehicles;
	}

	public com.kurui.kums.agent.AgentHabit getAgentHabit() {
		return agentHabit;
	}

	public void setAgentHabit(com.kurui.kums.agent.AgentHabit agentHabit) {
		this.agentHabit = agentHabit;
	}

	// end of extra code specified in the hbm.xml files

}
