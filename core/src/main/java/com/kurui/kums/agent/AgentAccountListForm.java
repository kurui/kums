package com.kurui.kums.agent;

import com.kurui.kums.base.ListActionForm;

public class AgentAccountListForm extends ListActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public long agentId = Long.valueOf(0);
	public long accountId = Long.valueOf(0);
	public long paymentToolId = Long.valueOf(0);
	private long accountBusinessType = Long.valueOf(0);
	private long accountPersonalType = Long.valueOf(0);
	public long accountTranType = Long.valueOf(0);
	public long accountType = Long.valueOf(0);
	public String accountName = "";

	public long getAgentId() {
		return agentId;
	}

	public void setAgentId(long agentId) {
		this.agentId = agentId;
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public long getPaymentToolId() {
		return paymentToolId;
	}

	public void setPaymentToolId(long paymentToolId) {
		this.paymentToolId = paymentToolId;
	}

	public long getAccountBusinessType() {
		return accountBusinessType;
	}

	public void setAccountBusinessType(long accountBusinessType) {
		this.accountBusinessType = accountBusinessType;
	}

	public long getAccountPersonalType() {
		return accountPersonalType;
	}

	public void setAccountPersonalType(long accountPersonalType) {
		this.accountPersonalType = accountPersonalType;
	}

	public long getAccountTranType() {
		return accountTranType;
	}

	public void setAccountTranType(long accountTranType) {
		this.accountTranType = accountTranType;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public long getAccountType() {
		return accountType;
	}

	public void setAccountType(long accountType) {
		this.accountType = accountType;
	}

}
