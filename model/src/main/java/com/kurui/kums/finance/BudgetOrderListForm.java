package com.kurui.kums.finance;

import com.kurui.kums.base.ListActionForm;

public class BudgetOrderListForm extends ListActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id = Long.valueOf(0);
	private Long budgetId = Long.valueOf(0);

	private String name = "";
	private long type = Long.valueOf(0);

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Long getBudgetId() {
		return budgetId;
	}

	public void setBudgetId(Long budgetId) {
		this.budgetId = budgetId;
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
