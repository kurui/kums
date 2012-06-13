package com.kurui.kums.system;

import com.kurui.kums.base.util.DateUtil;
import com.kurui.kums.finance.Statement;
import com.kurui.kums.system.entity._OperateLog;

public class OperateLog extends _OperateLog {
	private static final long serialVersionUID = 1L;

	public static final long TYPE_1 = 1;// 卖出订单录入*
	public static final long TYPE_2 = 2;// 收款(销售)*

	public static final long TYPE_4 = 4;// 取消出票*
	public static final long TYPE_5 = 5;// 确定出票*

	public static final long TYPE_13 = 13;// 申请支付（创建买入订单）*
	public static final long TYPE_14 = 14;// 锁定
	public static final long TYPE_15 = 15;// 付款（采购）*
	public static final long TYPE_16 = 16;// 解锁（自己的订单）
	public static final long TYPE_17 = 17;// 解锁（别人的订单）

	public static final long TYPE_20 = 20;// 付退款（取消出票）*
	public static final long TYPE_21 = 21;// 收退款（取消出票）*

	public static final long TYPE_35 = 35;// 退票订单录入

	public static final long TYPE_201 = 201;// 编辑备注
	
	public static final long TYPE_202 = 202;// 编辑订单

	public static final long TYPE_88 = 88;// 删除订单()

	public static final long TYPE_98 = 98;// 创建收支单

	public static final long TYPE_99 = 99;// 修改结算单

	public static final long TYPE_1001 = 1001;// 借入债务
	public static final long TYPE_1011 = 1011;// 债务还款
	
	public static final long TYPE_2001 = 2001;// 借出债权
	public static final long TYPE_2011 = 2011;// 债权收款

	public String orderTypeInfo = "";

	public void setOrderTypeInfo(String orderTypeInfo) {
		this.orderTypeInfo = orderTypeInfo;
	}

	public String getOrderTypeInfo() {
		return new Statement().getOrderTypeInfoByValue(this.getOrderType());
	}

	public String getTypeInfo() {
		if (type != null) {
			if (type == TYPE_1) {
				return "销售订单录入";
			} else if (type == TYPE_2) {
				return "销售订单收款";
			} else if (type == TYPE_13) {
				return "申请支付";// （创建买入订单）
			} else if (type == TYPE_14) {
				return "锁定";
			} else if (type == TYPE_15) {
				return "确认支付";// 付款（采购）*
			} else if (type == TYPE_16) {
				return "解锁";// （自己的订单）
			} else if (type == TYPE_17) {
				return "解锁";// （别人的订单）
			} else if (type == TYPE_98) {
				return "创建结算单";
			} else if (type == TYPE_99) {
				return "修改结算单";
			} else if (type == TYPE_201) {
				return "编辑备注";
			} else if (type == TYPE_202) {
				return "编辑订单";
			} else if (type == TYPE_88) {
				return "删除订单";
			} else if (type == TYPE_1001) {
				return "借入债务";
			} else if (type == TYPE_1011) {
				return "债务还款";
			} else if (type == TYPE_2001) {
				return "借出债权";
			} else if (type == TYPE_2011) {
				return "债权收款";
			} else {
				return "未定义";
			}
		} else {
			return "";
		}
	}

	public String getFormatOptTime() {
		if (this.optTime != null) {
			return DateUtil.getDateString(this.optTime, "yyyy-MM-dd HH:mm:ss");
		}
		return "";
	}
}
