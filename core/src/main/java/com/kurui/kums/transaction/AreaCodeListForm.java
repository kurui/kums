package com.kurui.kums.transaction;

import com.kurui.kums.base.ListActionForm;

public class AreaCodeListForm extends ListActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id = 0;
	private String areaCode;//
	private String name;//

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
