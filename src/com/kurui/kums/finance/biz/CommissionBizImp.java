package com.kurui.kums.finance.biz;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import com.kurui.kums.agent.Agent;
import com.kurui.kums.agent.DirectLevel;
import com.kurui.kums.agent.dao.AgentDAO;
import com.kurui.kums.agent.dao.AgentRelationDAO;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.base.Constant;
import com.kurui.kums.finance.Commission;
import com.kurui.kums.finance.CommissionListForm;
import com.kurui.kums.finance.FinanceOrder;
import com.kurui.kums.finance.OrderDetail;
import com.kurui.kums.finance.dao.CommissionDAO;
import com.kurui.kums.finance.dao.FinanceOrderDAO;
import com.kurui.kums.market.Product;
import com.kurui.kums.market.dao.SaleEventDAO;

public class CommissionBizImp implements CommissionBiz {
	private CommissionDAO commissionDAO;
	private FinanceOrderDAO financeOrderDAO;
	private AgentDAO agentDAO;
	private AgentRelationDAO agentRelationDAO;
	private SaleEventDAO saleEventDAO;

	// 更新佣金明细
	public void updateCommissionData(CommissionListForm commissionForm)
			throws AppException {
		// List<Agent> crossAgent=agentDAO.get
		List<FinanceOrder> orderList = financeOrderDAO
				.listByTranType(FinanceOrder.TRANTYPE_1501 + "");// 保健品
		// System.out.println("orderList:" + orderList.size());

		deleteCommissionByOrderList(orderList);

		for (int i = 0; i < orderList.size(); i++) {
			FinanceOrder order = orderList.get(i);
			if (order != null) {
				Agent agent = order.getAgent();
				if (agent != null) {
					saveDirectCommission(order, agent);
					Agent superAgent = null;
					Agent tempAgent = agent;
					for (int j = 0; j < 5; j++) {// 上推5代，最大11
						if (tempAgent != null) {
							superAgent = agentRelationDAO.getSupAgent(tempAgent
									.getId());
							if (superAgent != null && tempAgent != null) {
								saveSuperAgentCommission(order, agent,
										superAgent);
							}
							tempAgent = superAgent;
						}
					}

				} else {
					System.out.println("updateCommissionData agent is null..");
				}
			} else {
				System.out.println("updateCommissionData order is null..");
			}
		}
	}

	/**
	 * 计算直接销售提成
	 */
	public void saveDirectCommission(FinanceOrder order, Agent crossAgent)
			throws AppException {
		if (order != null) {
			BigDecimal commissionAmount = BigDecimal.ZERO;
			DirectLevel directLevel = crossAgent.getDirectLevel();
			if (directLevel != null) {
				BigDecimal directDiscount = directLevel.getDirectDiscount();
				if (directDiscount.compareTo(BigDecimal.ZERO) > 0) {
					// 订单额
					// commissionAmount = getCommissionAmountAsOrder(order,
					// directDiscount);
					// 绩效权重
					commissionAmount = getCommissionAmountAsProduct(order,
							directDiscount);

					String memo = order.getOrderNo() + "--"
							+ crossAgent.getName() + "-"
							+ directLevel.getName() + "-比例："
							+ Constant.toPercentByBigDecimal(directDiscount)
							+ "--佣金：" + commissionAmount;

					Commission commission = new Commission();
					commission.setTotalAmount(commissionAmount);
					commission.setMemo(memo);
					commission.setType(Commission.TYPE_1);
					commission.setCreateTime(new Timestamp(System
							.currentTimeMillis()));
					commission.setStatus(Commission.STATES_1);
					commission.setFinanceOrder(order);
					commission.setCrossAgent(crossAgent);
					// commission.setSaleEvent(saleEvent);
					save(commission);
				}
			}
		}
	}

	/**
	 * 直接按订单额计算
	 */
	private BigDecimal getCommissionAmountAsOrder(FinanceOrder order,
			BigDecimal directDiscount) {
		BigDecimal orderAmount = order.getTotalAmount();
		BigDecimal commissionAmount = BigDecimal.ZERO;
		commissionAmount = orderAmount.multiply(directDiscount);
		return commissionAmount;
	}

	/**
	 * 按产品绩效权重计算
	 */
	private BigDecimal getCommissionAmountAsProduct(FinanceOrder order,
			BigDecimal directDiscount) {
		BigDecimal orderAmount = BigDecimal.ZERO;
		Set<OrderDetail> orderDetailSet = order.getOrderDetails();

		for (Iterator iterator = orderDetailSet.iterator(); iterator.hasNext();) {
			OrderDetail orderDetail = (OrderDetail) iterator.next();
			Product product = orderDetail.getProduct();
			BigDecimal prop = product.getProportion();
			BigDecimal tempAmount = product.getPrice().multiply(
					orderDetail.getProductCount()).multiply(prop);
			orderAmount = orderAmount.add(tempAmount);
		}

		BigDecimal commissionAmount = BigDecimal.ZERO;
		commissionAmount = orderAmount.multiply(directDiscount);
		return commissionAmount;
	}

	/**
	 * 计算上级提成
	 */
	public void saveSuperAgentCommission(FinanceOrder order, Agent directAgent,
			Agent superAgent) throws AppException {
		if (order != null) {
			BigDecimal orderAmount = order.getTotalAmount();
			BigDecimal commissionAmount = BigDecimal.ZERO;
			DirectLevel directLevel = directAgent.getDirectLevel();
			DirectLevel superLevel = superAgent.getDirectLevel();
			if (directLevel != null) {
				if (superLevel != null) {
					BigDecimal directDiscount = BigDecimal.ZERO;

					if (directLevel.getSeqIndex() == 0) {
						directDiscount = superLevel.getDirectDiscount();
					} else if (directLevel.getSeqIndex() == 1) {
						directDiscount = superLevel.getNormalDiscount();
					} else if (directLevel.getSeqIndex() == 2) {
						directDiscount = superLevel.getDirectorDiscount();
					} else if (directLevel.getSeqIndex() == 3) {
						directDiscount = superLevel.getManagerDiscount();
					} else if (directLevel.getSeqIndex() == 4) {
						directDiscount = superLevel.getAdManagerDiscount();
					} else {
						System.out.println("----未定义的seqIndex..");
						return;
					}

					if (directDiscount.compareTo(BigDecimal.ZERO) > 0) {
						// orderAmount//按每件订单明细物品绩效比例累加
						commissionAmount = getCommissionAmountAsProduct(order,
								directDiscount);

						String memo = order.getOrderNo()
								+ "--直销商："
								+ directAgent.getName()
								+ "-"
								+ directLevel.getName()
								+ "--上级："
								+ superAgent.getName()
								+ "-"
								+ superLevel.getName()
								+ "-比例："
								+ Constant
										.toPercentByBigDecimal(directDiscount)
								+ "--佣金：" + commissionAmount;

						Commission commission = new Commission();
						commission.setTotalAmount(commissionAmount);
						commission.setMemo(memo);
						commission.setType(Commission.TYPE_1);
						commission.setCreateTime(new Timestamp(System
								.currentTimeMillis()));
						commission.setStatus(Commission.STATES_1);
						commission.setFinanceOrder(order);
						commission.setCrossAgent(superAgent);
						// commission.setSaleEvent(saleEvent);
						save(commission);
					}
				} else {
					System.out.println(superAgent.getName() + "未定义级别。。");
				}
			} else {
				System.out.println(directAgent.getName() + "未定义级别。。");
			}
		}
	}

	public List list(CommissionListForm commissionForm) throws AppException {
		return commissionDAO.list(commissionForm);
	}

	public void delete(long id) throws AppException {
		try {
			commissionDAO.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteCommission(Long commissionId) throws AppException {
		Commission commission = getCommissionById(commissionId);
		commission.setStatus(Commission.STATES_0);// 将状态变为无效
		update(commission);
	}

	public void deleteCommissionByOrderId(Long orderId) throws AppException {
		List<Commission> commissionList = getCommissionByOrderId(orderId);
		for (int i = 0; i < commissionList.size(); i++) {
			Commission commission = commissionList.get(i);
			commission.setStatus(Commission.STATES_0);// 将状态变为无效
			update(commission);
		}
	}

	public void deleteCommissionByOrderList(List<FinanceOrder> orderList)
			throws AppException {
		for (int i = 0; i < orderList.size(); i++) {
			FinanceOrder financeOrder = orderList.get(i);
			if (financeOrder != null) {
				deleteCommissionByOrderId(financeOrder.getId());
			}
		}
	}

	public long save(Commission commission) throws AppException {
		return commissionDAO.save(commission);
	}

	public long update(Commission commission) throws AppException {
		return commissionDAO.update(commission);
	}

	public Commission getCommissionById(long id) throws AppException {
		return commissionDAO.getCommissionById(id);
	}

	public List<Commission> getCommissionByOrderId(long id) throws AppException {
		return commissionDAO.getCommissionByOrderId(id);
	}

	public List<Commission> getCommissionList() throws AppException {
		return commissionDAO.getCommissionList();
	}

	public List<Commission> getValidCommissionList() throws AppException {
		return commissionDAO.getValidCommissionList();
	}

	public void setCommissionDAO(CommissionDAO commissionDAO) {
		this.commissionDAO = commissionDAO;
	}

	public void setFinanceOrderDAO(FinanceOrderDAO financeOrderDAO) {
		this.financeOrderDAO = financeOrderDAO;
	}

	public void setAgentDAO(AgentDAO agentDAO) {
		this.agentDAO = agentDAO;
	}

	public void setAgentRelationDAO(AgentRelationDAO agentRelationDAO) {
		this.agentRelationDAO = agentRelationDAO;
	}

	public void setSaleEventDAO(SaleEventDAO saleEventDAO) {
		this.saleEventDAO = saleEventDAO;
	}

}
