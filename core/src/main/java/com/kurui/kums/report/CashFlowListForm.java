package com.kurui.kums.report;

import com.kurui.kums.base.ListActionForm;

/**
 * 资金流水
 */
public class CashFlowListForm extends ListActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id = Long.valueOf(0);

	private String itemName = "";
	private String businessDate = "";
	private String businessNo = "";
	private String cussentCompany = "";// 往来单位
	private String cussentAccount = "";// 往来账户

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
}
