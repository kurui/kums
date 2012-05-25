package com.kurui.kums.transaction;

import com.kurui.kums.base.ListActionForm;

public class CompanyAccountListForm extends ListActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public long companyId = Long.valueOf(0);
	public long accountId = Long.valueOf(0);
	public long paymentToolId = Long.valueOf(0);
	private long accountBusinessType = Long.valueOf(0);
	private long accountPersonalType = Long.valueOf(0);
	public long accountTranType = Long.valueOf(0);
	public long accountType = Long.valueOf(0);
	public String accountName = "";

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
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

	public long getAccountTranType() {
		return accountTranType;
	}

	public void setAccountTranType(long accountTranType) {
		this.accountTranType = accountTranType;
	}

	public long getAccountType() {
		return accountType;
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

	public void setAccountType(long accountType) {
		this.accountType = accountType;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

}
