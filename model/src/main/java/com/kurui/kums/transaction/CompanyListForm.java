package com.kurui.kums.transaction;

import com.kurui.kums.base.ListActionForm;

public class CompanyListForm extends ListActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id = 0;
	private String name;// 公司名称
	private long type;// 公司类型

	protected String provideChain;// 供应链

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

	public long getType() {
		return type;
	}

	public void setType(long type) {
		this.type = type;
	}

	public String getProvideChain() {
		return provideChain;
	}

	public void setProvideChain(String provideChain) {
		this.provideChain = provideChain;
	}

}
