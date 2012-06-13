package com.kurui.kums.transaction;

import com.kurui.kums.base.ListActionForm;

public class AccountListForm extends ListActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id = Long.valueOf(0);

	private long paymentToolId;// 支付工具名称
	private String name;// 户名
	private String accountNo;// 账号
	private long businessType = Long.valueOf(0);// 业务类型
	private long personalType = Long.valueOf(0);// 公私类型
	private long tranType = Long.valueOf(0);// 收付类型
	private long type = Long.valueOf(0);// 类型
	private long status = Long.valueOf(0);

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getPaymentToolId() {
		return paymentToolId;
	}

	public void setPaymentToolId(long paymentToolId) {
		this.paymentToolId = paymentToolId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public long getBusinessType() {
		return businessType;
	}

	public void setBusinessType(long businessType) {
		this.businessType = businessType;
	}

	public long getPersonalType() {
		return personalType;
	}

	public void setPersonalType(long personalType) {
		this.personalType = personalType;
	}

	public long getTranType() {
		return tranType;
	}

	public void setTranType(long tranType) {
		this.tranType = tranType;
	}

	public long getType() {
		return type;
	}

	public void setType(long type) {
		this.type = type;
	}

	public long getStatus() {
		return status;
	}

	public void setStatus(long status) {
		this.status = status;
	}

}
