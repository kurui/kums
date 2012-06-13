package com.kurui.kums.finance;

import java.util.Date;

import com.kurui.kums.base.util.DateUtil;
import com.kurui.kums.finance.entity._Statement;
import com.kurui.kums.transaction.Account;

public class Statement extends _Statement {
	private static final long serialVersionUID = 1L;

	public static final long STATUS_0 = 0;// 未结算
	public static final long STATUS_1 = 1;// 已结算
	public static final long STATUS_2 = 2;// 等待收款
	public static final long STATUS_8 = 8;// 已确认结算

	public static final long STATUS_88 = 88;// 废弃

	public static final long TYPE_1 = 1;// 收入
	public static final long TYPE_2 = 2;// 支出

	public static final long SUBTYPE_10 = 10;// 正常收款
	public static final long SUBTYPE_20 = 20;// 正常支付
	public static final long SUBTYPE_11 = 11;// 收退款/债权收款//取消出单、退废单
	public static final long SUBTYPE_21 = 21;// 付退款/债务还款//取消出单、退废单

	/** 订单类型 */
	public static final long ORDERTYPE_1201 = 1201;// 管理费用
	public static final long ORDERTYPE_15 = 15;// 主营业务
	public static final long ORDERTYPE_1400 = 1400;// 债务
	public static final long ORDERTYPE_1300 = 1300;// 债权

	public static final long ORDERTABLE_1 = 1;// FinanceOrder

	private Object[] tempData = new Object[0];
	protected Account fromAccount;
	protected Account toAccount;

	public String getOrderTypeInfoByValue(Long type) {
		String info = "";
		if (type != null) {
			if (type == ORDERTYPE_1201) {
				info = "管理费用";
			} else if (type == ORDERTYPE_15) {
				info = "主营业务";
			} else if (type == ORDERTYPE_1400) {
				info = "债务";
			} else if (type == ORDERTYPE_1300) {
				info = "债权";
			} else {
				info = "";
			}
		} else {
			info = "";
		}
		return info;
	}

	public String getOrderTypeInfo() {
		return getOrderTypeInfoByValue(this.getOrderType());
	}

	public String getStatusInfo() {
		if (this.getStatus() != null) {
			if (this.getStatus() == STATUS_0) {
				return "未结算";
			} else if (this.getStatus().intValue() == STATUS_1) {
				return "已结算";
			} else if (this.getStatus().intValue() == STATUS_2) {
				return "部分结算";
			} else if (this.getStatus().intValue() == STATUS_8) {
				return "已确认结算";
			} else if (this.getStatus().intValue() == STATUS_88) {
				return "已废弃";
			} else {
				return "";
			}
		} else {
			return "";
		}
	}

	public String getOrderSubtypeText() {
		if (this.getOrderSubtype() != null) {
			if (this.getOrderSubtype() == SUBTYPE_20) {
				return "正常付款";
			} else if (this.getOrderSubtype().intValue() == SUBTYPE_21) {
				return "付退款";
			} else if (this.getOrderSubtype().intValue() == SUBTYPE_10) {
				return "正常收款";
			} else if (this.getOrderSubtype().intValue() == SUBTYPE_11) {
				return "收退款";
			} else {
				return "";
			}
		} else {
			return "";
		}
	}

	public String getTypeInfo() {
		if (this.getType() != null) {
			if (this.getType() == TYPE_1) {
				return "收入";
			} else if (this.getType() == TYPE_2) {
				return "支出";
			} else {
				return "";
			}
		} else {
			return "";
		}
	}

	public long getAccountType() {
		if (this.getType() != null && this.getOrderSubtype() != null) {
			if (this.getOrderSubtype() == Statement.SUBTYPE_10) {
				return 1;
			}
			if (this.getOrderSubtype() == Statement.SUBTYPE_11)
				return 2;

			if (this.getOrderSubtype() == Statement.SUBTYPE_20)
				return 2;

			if (this.getOrderSubtype() == Statement.SUBTYPE_21)
				return 1;

		}
		return 0;
	}

	public String getFormatOptTime() {
		if (this.optTime != null) {
			return DateUtil.getDateString(this.optTime, "yyyy-MM-dd HH:mm:ss");
		}
		return "";
	}

	private String statementDate = "";

	public String getStatementDate() {
		String mydate = "";
		if (this.statementTime != null && "".equals(statementTime) == false) {
			Date tempDate = new Date(statementTime.getTime());
			mydate = DateUtil.getDateString(tempDate, "yyyy-MM-dd");
		}
		return mydate;
	}

	public void setStatementDate(String statementDate) {
		this.statementDate = statementDate;
	}

	public Account getFromAccount() {
		return fromAccount;
	}

	public void setFromAccount(Account fromAccount) {
		this.fromAccount = fromAccount;
	}

	public Account getToAccount() {
		return toAccount;
	}

	public void setToAccount(Account toAccount) {
		this.toAccount = toAccount;
	}

	public String toLogString() {
		String temp = "";
		if (this.getType() == Statement.TYPE_2) // 支出
		{

			temp = "结算单：" + this.getStatementNo() + "，结算时间是："
					+ this.getStatementDate() + "，结算单金额为："
					+ this.getTotalAmount() + "元，支出账户为：";
			if (this.getFromAccount() != null)
				temp = temp + this.getFromAccount().getName();
		} else if (this.getType() == Statement.TYPE_1) // 收入
		{

			temp = "结算单：" + this.getStatementNo() + "，结算时间是："
					+ this.getStatementDate() + "，结算单金额为："
					+ this.getTotalAmount() + "元，收款账户为：";
			if (this.getToAccount() != null)
				temp = temp + this.getToAccount().getName();
		}

		return temp;
	}

	public Object[] getTempData() {
		return tempData;
	}

	public void setTempData(Object[] tempData) {
		this.tempData = tempData;
	}

}
