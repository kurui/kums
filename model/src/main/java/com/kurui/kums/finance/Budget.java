package com.kurui.kums.finance;

import java.math.BigDecimal;
import java.util.Date;
import com.kurui.kums.base.util.DateUtil;
import com.kurui.kums.finance.entity._Budget;

public class Budget extends _Budget {
	private static final long serialVersionUID = 1L;
	
	private BigDecimal totalBudgetAmount=BigDecimal.ZERO;
	private BigDecimal totalStatementAmount=BigDecimal.ZERO;
	

	// 类型
	public static final long BUDGET_TYPE_1 = 1;// 
	public static final long BUDGET_TYPE_2 = 2;//

	public static final long STATUS_1 = 1;
	public static final long STATUS_0 = 0;

	public String getTypeInfo() {
		if (this.getType() != null) {
			if (this.getType() == BUDGET_TYPE_1) {
				return "默认";
			} else {
				return "";
			}
		} else {
			return "";
		}
	}

	// 状态
	public String getStatusInfo() {
		if (this.getStatus() != null) {
			if (this.getStatus() == STATUS_1) {
				return "有效";
			} else if (this.getStatus().intValue() == STATUS_0) {
				return "无效";
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
	
	private String beginDate="";
	private String endDate="";

	public String getBeginDate() {
		String mydate = "";
		if (this.beginTime != null && "".equals(beginTime) == false) {
			Date tempDate = new Date(beginTime.getTime());
			mydate = DateUtil.getDateString(tempDate, "yyyy-MM-dd");
		}
		return mydate;
	}

	public String getEndDate() {
		String mydate = "";
		if (this.endTime != null && "".equals(endTime) == false) {
			Date tempDate = new Date(endTime.getTime());
			mydate = DateUtil.getDateString(tempDate, "yyyy-MM-dd");
		}
		return mydate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public BigDecimal getTotalBudgetAmount() {
		return totalBudgetAmount;
	}

	public void setTotalBudgetAmount(BigDecimal totalBudgetAmount) {
		this.totalBudgetAmount = totalBudgetAmount;
	}

	public BigDecimal getTotalStatementAmount() {
		return totalStatementAmount;
	}

	public void setTotalStatementAmount(BigDecimal totalStatementAmount) {
		this.totalStatementAmount = totalStatementAmount;
	}
	
	
	
	
}
