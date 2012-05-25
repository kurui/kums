package com.kurui.kums.market;

import com.kurui.kums.base.ListActionForm;

public class ApartmentListForm extends ListActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id = Long.valueOf(0);
	private String name = "";

	private Long estateDishId = Long.valueOf(0);
	private String estateDishIds = "";

	private Long estateDishType = Long.valueOf(0);
	private String estateDishTypes = "";

	private Long transactionType = Long.valueOf(0);
	private Long businessType = Long.valueOf(0);



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getEstateDishId() {
		return estateDishId;
	}

	public void setEstateDishId(Long estateDishId) {
		this.estateDishId = estateDishId;
	}

	public String getEstateDishIds() {
		return estateDishIds;
	}

	public void setEstateDishIds(String estateDishIds) {
		this.estateDishIds = estateDishIds;
	}

	public Long getEstateDishType() {
		return estateDishType;
	}

	public void setEstateDishType(Long estateDishType) {
		this.estateDishType = estateDishType;
	}

	public String getEstateDishTypes() {
		return estateDishTypes;
	}

	public void setEstateDishTypes(String estateDishTypes) {
		this.estateDishTypes = estateDishTypes;
	}

	public Long getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(Long transactionType) {
		this.transactionType = transactionType;
	}

	public Long getBusinessType() {
		return businessType;
	}

	public void setBusinessType(Long businessType) {
		this.businessType = businessType;
	}
}
