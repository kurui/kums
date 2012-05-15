package com.kurui.kums.finance;

import com.kurui.kums.base.ListActionForm;

public class StatementListForm extends ListActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id = Long.valueOf(0);
	private String statementNo;// 结算单号
	private long status = Long.valueOf(-1);// 状态

	public long getStatus() {
		return status;
	}

	public void setStatus(long status) {
		this.status = status;
	}

	public String getStatementNo() {
		return statementNo;
	}

	public void setStatementNo(String statementNo) {
		this.statementNo = statementNo;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
