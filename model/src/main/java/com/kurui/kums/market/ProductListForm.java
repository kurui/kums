package com.kurui.kums.market;

import com.kurui.kums.base.ListActionForm;
import com.kurui.kums.transaction.util.DataTypeStore;

public class ProductListForm extends ListActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id = Long.valueOf(0);
	private long rowId = Long.valueOf(0);

	private String name = "";
	private Long type = Long.valueOf(0);
	private String productTypes = "";
	private String productTypesInfo = "";

	public long getRowId() {
		return rowId;
	}

	public void setRowId(long rowId) {
		this.rowId = rowId;
	}

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

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	public String getProductTypes() {
		return productTypes;
	}

	public void setProductTypes(String productTypes) {
		this.productTypes = productTypes;
	}

	public String getProductTypesInfo() {
		if (productTypes != null & productTypes.equals("") == false) {
			productTypesInfo = DataTypeStore
					.getDataTypesNameByNos(productTypes);
		}
		return productTypesInfo;
	}

	public void setProductTypesInfo(String productTypesInfo) {
		this.productTypesInfo = productTypesInfo;
	}

}
