package com.kurui.kums.transaction;

import com.kurui.kums.base.ListActionForm;

public class PlatformListForm extends ListActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id = Long.valueOf(0);
	private String name;// 交易名称
	private String type;// 交易类型
	private String drawType;// 出票类型

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDrawType() {
		return drawType;
	}

	public void setDrawType(String drawType) {
		this.drawType = drawType;
	}

}
