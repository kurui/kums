package com.kurui.kums.transaction;

import com.kurui.kums.base.ListActionForm;

public class DataTypeListForm extends ListActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id = Long.valueOf(0);
	private String no = "";
	private long level = Long.valueOf(0);

	private String superNo = "0";

	
	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getLevel() {
		return level;
	}

	public void setLevel(long level) {
		this.level = level;
	}

	public String getSuperNo() {
		return superNo;
	}

	public void setSuperNo(String superNo) {
		this.superNo = superNo;
	}

}
