package com.kurui.kums.transaction;

import com.kurui.kums.transaction.entity._CompanyAccount;

public class CompanyAccount extends _CompanyAccount {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long companyId = Long.valueOf(0);
	private long accountId = Long.valueOf(0);

	private long paymentToolId = Long.valueOf(0);
	private String accountName;
	private String accountNo;
	private long accountBusinessType = Long.valueOf(0);
	private long accountPersonalType = Long.valueOf(0);
	private long accountTranType = Long.valueOf(0);
	private long accountType = Long.valueOf(0);
	private String accountDescription;
	private long accountStatus = Long.valueOf(0);

	// 状态
	public static final long STATES_1 = 1;// 有效
	public static final long STATES_0 = 0;// 无效

	// 状态
	public String getStatusInfo() {
		if (this.getStatus() != null) {
			if (this.getStatus() == STATES_1) {
				return "有效";
			} else if (this.getStatus().intValue() == STATES_0) {
				return "无效";
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

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

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
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

	public long getAccountType() {
		return accountType;
	}

	public void setAccountType(long accountType) {
		this.accountType = accountType;
	}

	public String getAccountDescription() {
		return accountDescription;
	}

	public void setAccountDescription(String accountDescription) {
		this.accountDescription = accountDescription;
	}

	public long getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(long accountStatus) {
		this.accountStatus = accountStatus;
	}

}
