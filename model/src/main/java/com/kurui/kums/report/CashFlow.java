package com.kurui.kums.report;

import java.math.BigDecimal;
import com.kurui.kums.base.Constant;
import com.kurui.kums.finance.FinanceOrder;
import com.kurui.kums.report.entity._CashFlow;

/**
 * 资金流水
 */
public class CashFlow extends _CashFlow {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private FinanceOrder order;

	public CashFlow() {

	}

	public CashFlow(FinanceOrder financeOrder) {
		System.out.println("=====CashFlow(FinanceOrder order)=====");
		if (financeOrder != null) {
			this.itemName = financeOrder.getTranTypeText();
			this.businessDate = financeOrder.getBusinessDate();
			this.businessNo = financeOrder.getOrderNo();
			this.summary = financeOrder.getMemo();
			if (financeOrder.getBusinessType() != null) {
				if (financeOrder.getBusinessType() == FinanceOrder.BUSINESSTYPE_1) {
					this.inAmount = financeOrder.getTotalAmount();
				} else if (financeOrder.getBusinessType() == FinanceOrder.BUSINESSTYPE_2) {
					this.outAmount = financeOrder.getTotalAmount();
				}
			}
		}
	}

	public CashFlow(BigDecimal inAmount, BigDecimal outAmount) {
		this.inAmount = Constant.toBigDecimal(inAmount);
		this.inAmount = Constant.toBigDecimal(outAmount);
	}

	public FinanceOrder getOrder() {
		return order;
	}

	public void setOrder(FinanceOrder order) {
		this.order = order;
	}

}
