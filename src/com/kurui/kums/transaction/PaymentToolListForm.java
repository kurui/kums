package com.kurui.kums.transaction;

import com.kurui.kums.base.ListActionForm;

public class PaymentToolListForm extends ListActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id=Long.valueOf(0);
	private String name;// 支付名称
	private String type;// 支付类型
	
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
