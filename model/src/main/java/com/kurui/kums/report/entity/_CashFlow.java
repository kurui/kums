package com.kurui.kums.report.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 资金流水
 */
public class _CashFlow extends org.apache.struts.action.ActionForm implements
		Cloneable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected long id = Long.valueOf(0);
	protected Long tranType = Long.valueOf(0);
	protected String itemName = "";
	protected Timestamp businessTime;
	protected String businessDate = "";
	protected String businessNo = "";
	protected String summary = "";
	protected String cussentCompany = "";// 往来单位
	protected Long cussentCompanyId = Long.valueOf(0);
	protected String cussentAccount = "";// 往来账户
	protected Long cussentAccountId = Long.valueOf(0);

	protected BigDecimal inAmount = BigDecimal.ZERO;
	protected BigDecimal outAmount = BigDecimal.ZERO;

	protected Long type;
	protected Long status;
	protected String sessionId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Timestamp getBusinessTime() {
		return businessTime;
	}

	public void setBusinessTime(Timestamp businessTime) {
		this.businessTime = businessTime;
	}

	public String getBusinessDate() {
		return businessDate;
	}

	public void setBusinessDate(String businessDate) {
		this.businessDate = businessDate;
	}

	public String getBusinessNo() {
		return businessNo;
	}

	public void setBusinessNo(String businessNo) {
		this.businessNo = businessNo;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getCussentCompany() {
		return cussentCompany;
	}

	public void setCussentCompany(String cussentCompany) {
		this.cussentCompany = cussentCompany;
	}

	public String getCussentAccount() {
		return cussentAccount;
	}

	public void setCussentAccount(String cussentAccount) {
		this.cussentAccount = cussentAccount;
	}

	public BigDecimal getInAmount() {
		return inAmount;
	}

	public void setInAmount(BigDecimal inAmount) {
		this.inAmount = inAmount;
	}

	public BigDecimal getOutAmount() {
		return outAmount;
	}

	public void setOutAmount(BigDecimal outAmount) {
		this.outAmount = outAmount;
	}

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public Long getTranType() {
		return tranType;
	}

	public void setTranType(Long tranType) {
		this.tranType = tranType;
	}

	public Long getCussentCompanyId() {
		return cussentCompanyId;
	}

	public void setCussentCompanyId(Long cussentCompanyId) {
		this.cussentCompanyId = cussentCompanyId;
	}

	public Long getCussentAccountId() {
		return cussentAccountId;
	}

	public void setCussentAccountId(Long cussentAccountId) {
		this.cussentAccountId = cussentAccountId;
	}

	// The following is extra code
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

	// end of extra code

}
