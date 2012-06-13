package com.kurui.kums.transaction;

import java.math.BigDecimal;
import java.util.Date;

import com.kurui.kums.base.util.DateUtil;
import com.kurui.kums.transaction.entity._AccountCheck;

public class AccountCheck extends _AccountCheck {
	private static final long serialVersionUID = 1L;

	public long userId = new Long(0);
	public long accountId = new Long(0);
	public String accountName="";
	
	
	// 类型
	public static final long TYPE_0 = 0;// 上班
	public static final long TYPE_1 = 1;// 下班

	// 状态
	public static final long STATES_0 = 0;// 有效
	public static final long STATES_1 = 1;// 无效
	
	
	// 状态
	public String getTypeInfo() {
		if (this.getType() != null) {
			if (this.getType() == TYPE_0) {
				return "上班";
			} else if (this.getType().intValue() == TYPE_1) {
				return "下班";
			} else {
				return "";
			}
		}
		return "";
	}

	// 状态
	public String getStatusInfo() {
		if (this.getStatus() != null) {
			if (this.getStatus() == STATES_0) {
				return "有效";
			} else if (this.getStatus().intValue() == STATES_1) {
				return "无效";
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	public java.math.BigDecimal getCheckOnAmount() {
		if (this.checkOnAmount == null) {
			return BigDecimal.ZERO;
		}
		return this.checkOnAmount;
	}

	public java.math.BigDecimal getTransInAmount() {
		if (this.transInAmount == null) {
			return BigDecimal.ZERO;
		}
		return this.transInAmount;
	}

	public java.math.BigDecimal getTransOutAmount() {
		if (this.transOutAmount == null) {
			return BigDecimal.ZERO;
		}
		return this.transOutAmount;
	}

	public java.math.BigDecimal getPayAmount() {
		if (this.payAmount == null) {
			return BigDecimal.ZERO;
		}
		return this.payAmount;
	}

	public java.math.BigDecimal getRefundAmount() {
		if (this.refundAmount == null) {
			return BigDecimal.ZERO;
		}
		return this.refundAmount;
	}

	public java.math.BigDecimal getCheckOffAmount() {
		if (this.checkOffAmount == null) {
			return BigDecimal.ZERO;
		}
		return this.checkOffAmount;
	}

	public String getFormatCheckOnTime() {
		String mydate = "";
		if (this.checkOnTime != null && "".equals(checkOnTime) == false) {
			Date tempDate = new Date(checkOnTime.getTime());
			mydate = DateUtil.getDateString(tempDate, "yyyy-MM-dd HH:mm:ss");
		}
		return mydate;
	}

	public String getFormatCheckOffTime() {
		String mydate = "";
		if (this.checkOffTime != null && "".equals(checkOffTime) == false) {
			Date tempDate = new Date(checkOffTime.getTime());
			mydate = DateUtil.getDateString(tempDate, "yyyy-MM-dd HH:mm:ss");
		}
		return mydate;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

}
