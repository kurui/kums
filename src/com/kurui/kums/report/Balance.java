package com.kurui.kums.report;

import java.math.BigDecimal;
import java.util.List;

import com.kurui.kums.base.Constant;
import com.kurui.kums.finance.FinanceOrder;

public class Balance extends Report {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id = Long.valueOf(0);

	private String itemKey = "";
	private String itemName = "";
	private String itemAmount = "";

	public Balance() {
	}

	public Balance(String itemKey, String itemName) {
		this.itemKey = itemKey;
		this.itemName = itemName;
	}

	public Balance(String itemKey, String itemName, String itemAmount) {
		this.itemKey = itemKey;
		this.itemName = itemName;
		this.itemAmount = itemAmount;
	}

	public Balance(String itemKey, String itemName, String itemAmount,
			long businessType) {
		this.itemKey = itemKey;
		this.itemName = itemName;
		this.itemAmount = itemAmount;
		this.businessType = businessType;
	}

	public String getItemAmountHtml() {
		if (!"".equals(itemAmount)) {
			if (this.businessType == FinanceOrder.BUSINESSTYPE_1) {
				return "<span style=\"color:Red\">" + itemAmount + "</span>";
			} else if (this.businessType == FinanceOrder.BUSINESSTYPE_2) {
				return "<span style=\"color:Green\">" + itemAmount + "</span>";
			} else {
				return itemAmount;
			}
		} else {
			return "0";
		}
	}

	public static BigDecimal getTotalValue(List<Balance> balanceList) {
		BigDecimal totalValue = BigDecimal.ZERO;

		for (int i = 0; i < balanceList.size(); i++) {
			Balance balance = balanceList.get(i);
			BigDecimal itemAmount = Constant.toBigDecimal(balance
					.getItemAmount());
			if (balance.getBusinessType() == FinanceOrder.BUSINESSTYPE_1) {
				totalValue=totalValue.add(itemAmount);
			} else if (balance.getBusinessType() == FinanceOrder.BUSINESSTYPE_2) {
				totalValue=totalValue.add(itemAmount.multiply(BigDecimal.valueOf(-1)));
			}else{
				System.out.println("no businessType");
			}
		}
		return totalValue;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getItemAmount() {
		return itemAmount;
	}

	public void setItemAmount(String itemAmount) {
		this.itemAmount = itemAmount;
	}

}
