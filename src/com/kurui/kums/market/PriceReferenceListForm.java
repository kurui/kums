package com.kurui.kums.market;

import com.kurui.kums.base.ListActionForm;

public class PriceReferenceListForm extends ListActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id = Long.valueOf(0);
	private String name = "";
	private Long type = Long.valueOf(0);

	private String rapid = "";

	public String getRapid() {
		return rapid;
	}

	public void setRapid(String rapid) {
		this.rapid = rapid;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}

}
