package com.kurui.kums.finance;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.kurui.kums.base.PerformListener;
import com.kurui.kums.finance.util.FinanceComparator;

public class FinanceGroup {
	private long saleOrderFlag = new Long(0);
	private String groupMarkNo = "";

	// private int orderCount = 1;// 订单明细数量
	private List<FinanceOrder> orderList = new ArrayList<FinanceOrder>();
	private FinanceOrder saleOrder = new FinanceOrder();// 卖出订单（第一条）
	private FinanceOrder buyOrder = new FinanceOrder();

	private BigDecimal saleAmount = BigDecimal.ZERO;
	private BigDecimal buyAmount = BigDecimal.ZERO;

	public FinanceGroup() {

	}

	public FinanceGroup(List<FinanceOrder> orderList) {
		int listSize = orderList.size();
		if (listSize > 0) {
			if (listSize == 1) {
				FinanceOrder tempOrder = orderList.get(0);
				getCommonInfoBySaleOrder(tempOrder);
				this.saleOrder = tempOrder;
				this.buyOrder = tempOrder;
				this.saleAmount = tempOrder.getTotalAmount();
				this.buyAmount = tempOrder.getTotalAmount();
			} else {
				for (int i = 0; i < orderList.size(); i++) {
					FinanceOrder order = orderList.get(i);
					if (order.getBusinessType() != null) {

						if (order.getBusinessType() == FinanceOrder.BUSINESSTYPE_1) {// 销售
							getCommonInfoBySaleOrder(order);
							this.saleOrder = order;
							// this.buyAgent = order.getAgent();
							this.saleAmount = order.getTotalAmount();
						}
						//
						if (order.getBusinessType() == FinanceOrder.BUSINESSTYPE_2) {// 采购
							this.buyOrder = order;
							this.buyAmount = order.getTotalAmount();
						}
					}
				}

				if (this.saleOrderFlag == 0) {
					System.out.println("only one order saleOrderFlag:"
							+ this.saleOrderFlag);
					getCommonInfoBySaleOrder(orderList.get(0));
				}

				orderList = sortListByEntryTime(orderList);
				this.orderList = orderList;
			}
		}
	}

	public FinanceGroup(List<FinanceOrder> orderList, String groupNo) {
		orderList = sortListByEntryTime(orderList);
		this.orderList = orderList;
		this.groupMarkNo = groupNo;
	}

	public List<FinanceOrder> sortListByEntryTime(List<FinanceOrder> orderList) {

		FinanceComparator comp = new FinanceComparator();
		// 执行排序方法
		Collections.sort(orderList, comp);

		return orderList;
	}

	public static List<FinanceGroup> getGroupList(List<FinanceOrder> orderList) {
		// long a = System.currentTimeMillis();
		String temp = "";
		List<FinanceGroup> groupList = new ArrayList<FinanceGroup>();
		for (int i = 0; i < orderList.size(); i++) {
			FinanceOrder tempOrder = orderList.get(i);
			if (i == 0) {
				temp = tempOrder.getGroupMark();

				List<FinanceOrder> sameGroupOrderList = getSameGroup(orderList,
						temp);
				// System.out.println("i==0 groupList add:" + temp
				// + "--orderList:" + sameGroupOrderList.size());
				groupList.add(new FinanceGroup(sameGroupOrderList));
				continue;
			}

			String newGroupMark = tempOrder.getGroupMark();
			if (!newGroupMark.equals(temp)) {
				temp = tempOrder.getGroupMark();

				List<FinanceOrder> sameGroupOrderList = getSameGroup(orderList,
						temp);
				// System.out.println("groupList add:" + temp + "--orderList:"
				// + sameGroupOrderList.size());
				groupList.add(new FinanceGroup(sameGroupOrderList));
			}
		}
		return groupList;
	}

	public static List<FinanceGroup> getSubGroupList(
			List<FinanceOrder> orderList) {
		long a = System.currentTimeMillis();
		String temp = "";
		List<FinanceGroup> groupList = new ArrayList<FinanceGroup>();
		for (int i = 0; i < orderList.size(); i++) {
			FinanceOrder tempOrder = orderList.get(i);
			if (i == 0) {
				temp = tempOrder.getSubGroupMark();

				List<FinanceOrder> sameGroupOrderList = getSameSubGroup(
						orderList, temp);
				groupList.add(new FinanceGroup(sameGroupOrderList));
				continue;
			}

			String newTempSubGroupMark = tempOrder.getSubGroupMark();
			if (!newTempSubGroupMark.equals(temp)) {
				temp = tempOrder.getSubGroupMark();

				List<FinanceOrder> sameGroupOrderList = getSameSubGroup(
						orderList, temp);
				// System.out.println("groupList add:" + temp + "--orderList:"
				// + sameGroupOrderList.size());
				groupList.add(new FinanceGroup(sameGroupOrderList));
			}
		}

		new PerformListener("getSubGroupList", a);
		return groupList;
	}

	// 大组
	public static List<FinanceOrder> getSameGroup(List<FinanceOrder> orderList,
			String groupMark) {
		List<FinanceOrder> tempOrderList = new ArrayList<FinanceOrder>();

		for (int i = 0; i < orderList.size(); i++) {
			FinanceOrder order = orderList.get(i);

			if (order.getGroupMark().equals(groupMark)) {
				tempOrderList.add(order);
			}
		}
		return tempOrderList;
	}

	// 小组
	public static List<FinanceOrder> getSameSubGroup(
			List<FinanceOrder> orderList, String groupMark) {
		List<FinanceOrder> tempOrderList = new ArrayList<FinanceOrder>();

		for (int i = 0; i < orderList.size(); i++) {
			FinanceOrder order = orderList.get(i);

			if (order.getSubGroupMark().equals(groupMark)) {
				tempOrderList.add(order);
			}
		}
		return tempOrderList;
	}

	// 从卖出订单获取相同的信息
	public void getCommonInfoBySaleOrder(FinanceOrder order) {
		// flag
		this.saleOrderFlag = order.getId();

	}

	// 从买入订单获取相同的信息
	public void getCommonInfoByBuyOrder(FinanceOrder order) {
		getCommonInfoBySaleOrder(order);
	}

	public String getGroupMarkNo() {
		return groupMarkNo;
	}

	public void setGroupMarkNo(String groupMarkNo) {
		this.groupMarkNo = groupMarkNo;
	}

	public int getOrderCount() {
		return orderList.size();
	}

	public List<FinanceOrder> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<FinanceOrder> orderList) {
		this.orderList = orderList;
	}

	public FinanceOrder getSaleOrder() {
		return saleOrder;
	}

	public void setSaleOrder(FinanceOrder saleOrder) {
		this.saleOrder = saleOrder;
	}

	public long getSaleOrderFlag() {
		return saleOrderFlag;
	}

	public void setSaleOrderFlag(long saleOrderFlag) {
		this.saleOrderFlag = saleOrderFlag;
	}

	public FinanceOrder getBuyOrder() {
		return buyOrder;
	}

	public void setBuyOrder(FinanceOrder buyOrder) {
		this.buyOrder = buyOrder;
	}

	public BigDecimal getSaleAmount() {
		return saleAmount;
	}

	public void setSaleAmount(BigDecimal saleAmount) {
		this.saleAmount = saleAmount;
	}

	public BigDecimal getBuyAmount() {
		return buyAmount;
	}

	public void setBuyAmount(BigDecimal buyAmount) {
		this.buyAmount = buyAmount;
	}

}
