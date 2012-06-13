package com.kurui.kums.transaction;

import java.math.BigDecimal;

import com.kurui.kums.base.Constant;
import com.kurui.kums.base.util.PingYin;
import com.kurui.kums.transaction.entity._Account;

public class Account extends _Account {
	private static final long serialVersionUID = 1L;

	private long paymentToolId;// 支付工具表ID
	protected java.math.BigDecimal totalAmount = new BigDecimal(0);

	// 公私类型
	public static final long PERSONAL_TYPE_1 = 1;// 个人
	public static final long PERSONAL_TYPE_11 = 11;// 公司基本户
	public static final long PERSONAL_TYPE_12 = 12;// 公司一般户

	// 业务类型
	public static final long BUSINESS_TYPE_1 = 1;// 活期账户
	public static final long BUSINESS_TYPE_2 = 2;// 定期账户
	public static final long BUSINESS_TYPE_11 = 11;// 贷款账户
	public static final long BUSINESS_TYPE_21 = 21;// 证券交易

	// 交易类型
	public static final long TRAN_TYPE_1 = 1;// 付款账户
	public static final long TRAN_TYPE_2 = 2;// 收款账户
	public static final long TRAN_TYPE_3 = 3;// 收付账户

	// 系统类型
	public static final long TYPE_1 = 1;// 系统账户
	public static final long TYPE_11 = 11;// 客户账户

	// 状态
	public static final long STATES_1 = 1;// 启用
	public static final long STATES_0 = 0;// 停用

	public static final long SYSTEM_ACCOUNT_ID = 522;//

	private String charSet = "GBK";

	public String getShowName() {
		String showName = "";
		PaymentTool paymentTool = this.paymentTool;
		if (paymentTool != null && paymentTool.getId() > 0) {
			showName = Constant.toString(paymentTool.getName()) + "-";
			if (this.name != null && "".equals(this.name) == false) {
				showName += this.name;
			}
			if (this.accountNo != null && "".equals(this.accountNo) == false) {
				int accountLength = this.accountNo.length();
				int beginIndex = 0;
				int endIndex = 0;

				// if(accountLength>4){
				// beginIndex=accountLength-4;
				// }
				if (accountLength > 8) {
					endIndex = 8;
				}

				String tempAccountNo = this.accountNo.substring(beginIndex,
						endIndex);
				showName += tempAccountNo;
			}

			if ("".equals(showName) == false) {
				String myFirstLetter = PingYin
						.getFirstLetter(showName, charSet);
				if (myFirstLetter != null && myFirstLetter.length() > 1) {
					myFirstLetter = myFirstLetter.substring(0, 1);
					return myFirstLetter + "-" + showName;
				}
			}
		}
		return this.name;
	}

	public String _getShowName() {
		if (this.name != null && "".equals(this.name) == false) {
			String myFirstLetter = PingYin.getFirstLetter(this.name, charSet);
			if (myFirstLetter != null && myFirstLetter.length() > 1) {
				myFirstLetter = myFirstLetter.substring(0, 1);
				return myFirstLetter + "-" + this.name;
			}
		}
		return this.name;
	}

	public String getTypeInfo() {
		if (this.getType() != null) {
			if (this.getType().intValue() == TYPE_1) {
				return "系统";
			} else if (this.getType().intValue() == TYPE_11) {
				return "客户";
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	public String getBusinessTypeInfo() {
		if (this.getBusinessType() != null) {
			if (this.getBusinessType().intValue() == BUSINESS_TYPE_1) {
				return "活期";
			} else if (this.getBusinessType().intValue() == BUSINESS_TYPE_2) {
				return "定期";
			} else if (this.getBusinessType().intValue() == BUSINESS_TYPE_11) {
				return "贷款";
			} else if (this.getBusinessType().intValue() == BUSINESS_TYPE_21) {
				return "证券交易";
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	public String getPersonalTypeInfo() {
		if (this.getPersonalType() != null) {
			if (this.getPersonalType().intValue() == PERSONAL_TYPE_1) {
				return "个人";
			} else if (this.getPersonalType().intValue() == PERSONAL_TYPE_11) {
				return "企业基本户";
			} else if (this.getPersonalType().intValue() == PERSONAL_TYPE_12) {
				return "企业一般户";
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	public String getTranTypeInfo() {
		if (this.getTranType() != null) {
			if (this.getTranType().intValue() == TRAN_TYPE_1) {
				return "付款";
			} else if (this.getTranType().intValue() == TRAN_TYPE_2) {
				return "收款";
			} else if (this.getTranType().intValue() == TRAN_TYPE_3) {
				return "收付款";
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	// 状态
	public String getStatusInfo() {
		if (this.getStatus() != null) {
			if (this.getStatus() == STATES_1) {
				return "启用";
			} else if (this.getStatus().intValue() == STATES_0) {
				return "停用";
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	public long getPaymentToolId() {
		return paymentToolId;
	}

	public void setPaymentToolId(long paymentToolId) {
		this.paymentToolId = paymentToolId;
	}

	public java.math.BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(java.math.BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
}
