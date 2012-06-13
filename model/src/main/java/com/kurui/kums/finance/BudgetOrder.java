package com.kurui.kums.finance;

import java.util.Date;
import com.kurui.kums.base.util.DateUtil;
import com.kurui.kums.finance.entity._BudgetOrder;

public class BudgetOrder extends _BudgetOrder {

	private static final long serialVersionUID = 1L;

	private Long budgetId = Long.valueOf(0);

	public static final long TYPE_1 = 1;// 默认

	// 状态
	public static final long STATES_1 = 1;// 有效
	public static final long STATES_0 = 0;// 无效

	// public String getTypeInfo() {
	// if (this.getType() != null) {
	// if (this.getType() == TYPE_1) {
	// return "默认";
	// } else {
	// return "";
	// }
	// } else {
	// return "";
	// }
	// }

	// 状态
	public String getStatusInfo() {
		if (this.getStatus() != null) {
			if (this.getStatus() == STATES_1) {
				return "有效";
			} else if (this.getStatus().intValue() == STATES_0) {
				return "无效";
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	private String budgetDate = "";
	private String statementDate = "";

	public String getBudgetDate() {
		String mydate = "";
		if (this.budgeTime != null && "".equals(budgeTime) == false) {
			Date tempDate = new Date(budgeTime.getTime());
			mydate = DateUtil.getDateString(tempDate, "yyyy-MM-dd");
		}
		return mydate;
	}

	public String getStatementDate() {
		String mydate = "";
		if (this.statementTime != null && "".equals(statementTime) == false) {
			Date tempDate = new Date(statementTime.getTime());
			mydate = DateUtil.getDateString(tempDate, "yyyy-MM-dd");
		}
		return mydate;
	}

	public void setBudgetDate(String budgetDate) {
		this.budgetDate = budgetDate;
	}

	public void setStatementDate(String statementDate) {
		this.statementDate = statementDate;
	}

	public Long getBudgetId() {
		return budgetId;
	}

	public void setBudgetId(Long budgetId) {
		this.budgetId = budgetId;
	}

}
