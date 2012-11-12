package com.kurui.kums.report;

public class CommonReport {
	protected String itemKey = "";
	protected String itemName = "";
	protected String itemValue = "";
	
	public CommonReport() {
	}

	public CommonReport(String itemKey, String itemName) {
		this.itemKey = itemKey;
		this.itemName = itemName;
	}

	public CommonReport(String itemKey, String itemName, String itemValue) {
		this.itemKey = itemKey;
		this.itemName = itemName;
		this.itemValue = itemValue;
	}

	
	public String getItemKey() {
		return itemKey;
	}
	public void setItemKey(String itemKey) {
		this.itemKey = itemKey;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemValue() {
		return itemValue;
	}
	public void setItemValue(String itemValue) {
		this.itemValue = itemValue;
	}
	
	
	
}
