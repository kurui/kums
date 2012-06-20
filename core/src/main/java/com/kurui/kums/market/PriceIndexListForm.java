package com.kurui.kums.market;

import com.kurui.kums.base.ListActionForm;
import com.kurui.kums.base.Constant;
import com.kurui.kums.base.util.StringUtil;
import com.kurui.kums.market.util.PriceIndexStore;

public class PriceIndexListForm extends ListActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id = Long.valueOf(0);

	private Long priceReferenceId = Long.valueOf(0);
	private String priceReferenceIds = "";

	private Long priceReferenceType = Long.valueOf(0);
	private String priceReferenceTypes = "";

	private String rapid = "";// 本周/上周


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

	public Long getPriceReferenceId() {
		return priceReferenceId;
	}

	public void setPriceReferenceId(Long priceReferenceId) {
		this.priceReferenceId = priceReferenceId;
	}

	public String getPriceReferenceIds() {
		priceReferenceIds = Constant.toString(priceReferenceIds);
		priceReferenceIds = StringUtil.move(priceReferenceIds, "type");

		return priceReferenceIds;
	}

	public void setPriceReferenceIds(String priceReferenceIds) {
		this.priceReferenceIds = priceReferenceIds;
	}

	private String priceReferenceNames = "";

	public String getPriceReferenceNames() {
		if (priceReferenceIds != null & priceReferenceIds.equals("") == false) {
			priceReferenceNames = PriceIndexStore
					.getPriceReferencesNameByIds(priceReferenceIds);
		}
		return priceReferenceNames;
	}

	public void setPriceReferenceNames(String priceReferenceNames) {
		this.priceReferenceNames = priceReferenceNames;
	}

	public Long getPriceReferenceType() {
		return priceReferenceType;
	}

	public void setPriceReferenceType(Long priceReferenceType) {
		this.priceReferenceType = priceReferenceType;
	}

	public String getPriceReferenceTypes() {
		return priceReferenceTypes;
	}

	public void setPriceReferenceTypes(String priceReferenceTypes) {
		this.priceReferenceTypes = priceReferenceTypes;
	}

}
