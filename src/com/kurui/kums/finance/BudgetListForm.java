package com.kurui.kums.finance;

import com.kurui.kums.base.ListActionForm;

public class BudgetListForm extends ListActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id = Long.valueOf(0);
	private String no = "";
	private String name = "";
	private long type = Long.valueOf(0);

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
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

}
