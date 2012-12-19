package com.kurui.kums.library._entity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import com.kurui.kums.agent.ShareHolder;
import com.kurui.kums.market.EstateDish;
import com.kurui.kums.right.UserStore;
import com.kurui.kums.library.CompanyAccount;

/**
 * Company generated by hbm2java
 */

public class _Company

extends org.apache.struts.action.ActionForm implements Cloneable {
	private static final long serialVersionUID = 1L;

	// Fields

	protected long id;
	protected String name;
	protected String shortName;
	protected Long type;
	protected String provideChain;
	protected String revenueCode;
	protected String deputy;
	protected String registerType;
	protected String registerAddress;
	protected String entityAddress;
	protected String registerCode;
	protected String corporationCode;
	protected String registerCapital;
	protected BigDecimal netAssetValue;
	protected String mainBusiness;
	protected String sidelineBusiness;
	protected String telephone;
	protected String registerDate;
	protected String memo;
	protected Long status;
	protected java.sql.Timestamp updateTime;
	protected String userNo;
	protected java.util.Set financeOrders = new java.util.HashSet(0);
	protected java.util.Set platComAccounts = new java.util.HashSet(0);
	protected java.util.Set agents = new java.util.HashSet(0);
	protected Set<CompanyAccount> companyAccounts = new HashSet<CompanyAccount>(
			0);
	protected Set<ShareHolder> shareHolders = new HashSet<ShareHolder>(0);
	protected Set<EstateDish> estateDishs = new HashSet<EstateDish>(0);
	
	protected Long financeCount;
	protected Long agentCount;
	

	// Constructors

	// Property accessors

	public Set<EstateDish> getEstateDishs() {
		return estateDishs;
	}

	public void setEstateDishs(Set<EstateDish> estateDishs) {
		this.estateDishs = estateDishs;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public Long getType() {
		return this.type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	public Long getStatus() {
		return this.status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public java.sql.Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(java.sql.Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public String getUserName() {
		if (userNo != null && "".equals(userNo.trim()) == false) {
			return UserStore.getUserNameByNo(userNo);
		} else {
			return "";
		}
	}
	
	

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public java.util.Set getFinanceOrders() {
		return financeOrders;
	}

	public void setFinanceOrders(java.util.Set financeOrders) {
		this.financeOrders = financeOrders;
	}

	public java.util.Set getPlatComAccounts() {
		return this.platComAccounts;
	}

	public void setPlatComAccounts(java.util.Set platComAccounts) {
		this.platComAccounts = platComAccounts;
	}

	public Set<CompanyAccount> getCompanyAccounts() {
		return companyAccounts;
	}

	public void setCompanyAccounts(Set<CompanyAccount> companyAccounts) {
		this.companyAccounts = companyAccounts;
	}

	public java.util.Set getAgents() {
		return this.agents;
	}

	public void setAgents(java.util.Set agents) {
		this.agents = agents;
	}

	public String getProvideChain() {
		return provideChain;
	}

	public void setProvideChain(String provideChain) {
		this.provideChain = provideChain;
	}

	public String getRevenueCode() {
		return revenueCode;
	}

	public void setRevenueCode(String revenueCode) {
		this.revenueCode = revenueCode;
	}

	public String getDeputy() {
		return deputy;
	}

	public void setDeputy(String deputy) {
		this.deputy = deputy;
	}

	public String getRegisterType() {
		return registerType;
	}

	public void setRegisterType(String registerType) {
		this.registerType = registerType;
	}

	public String getRegisterAddress() {
		return registerAddress;
	}

	public void setRegisterAddress(String registerAddress) {
		this.registerAddress = registerAddress;
	}

	public String getEntityAddress() {
		return entityAddress;
	}

	public void setEntityAddress(String entityAddress) {
		this.entityAddress = entityAddress;
	}

	public String getRegisterCode() {
		return registerCode;
	}

	public void setRegisterCode(String registerCode) {
		this.registerCode = registerCode;
	}

	public String getCorporationCode() {
		return corporationCode;
	}

	public void setCorporationCode(String corporationCode) {
		this.corporationCode = corporationCode;
	}

	public String getRegisterCapital() {
		return registerCapital;
	}

	public void setRegisterCapital(String registerCapital) {
		this.registerCapital = registerCapital;
	}

	public BigDecimal getNetAssetValue() {
		if (netAssetValue == null) {
			return BigDecimal.ZERO;
		}
		return netAssetValue;
	}

	public void setNetAssetValue(BigDecimal netAssetValue) {
		this.netAssetValue = netAssetValue;
	}

	public String getMainBusiness() {
		return mainBusiness;
	}

	public void setMainBusiness(String mainBusiness) {
		this.mainBusiness = mainBusiness;
	}

	public String getSidelineBusiness() {
		return sidelineBusiness;
	}

	public void setSidelineBusiness(String sidelineBusiness) {
		this.sidelineBusiness = sidelineBusiness;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
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

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Set<ShareHolder> getShareHolders() {
		return shareHolders;
	}

	public void setShareHolders(Set<ShareHolder> shareHolders) {
		this.shareHolders = shareHolders;
	}

	public Long getFinanceCount() {
		return financeCount;
	}

	public void setFinanceCount(Long financeCount) {
		this.financeCount = financeCount;
	}

	public Long getAgentCount() {
		return agentCount;
	}

	public void setAgentCount(Long agentCount) {
		this.agentCount = agentCount;
	}

	// end of extra code specified in the hbm.xml files

}
