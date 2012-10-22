package com.kurui.kums.finance.biz;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.kurui.kums.finance.dao.FinanceOrderDAO;
import com.kurui.kums.finance.dao.OrderDetailDAO;
import com.kurui.kums.finance.dao.StatementDAO;
import com.kurui.kums.finance.util.FinanceOrderStore;
import com.kurui.kums.base.Inform;
import com.kurui.kums.base.Constant;
import com.kurui.kums.base.util.DateUtil;
import com.kurui.kums.base.http.HttpInvoker;
import com.kurui.kums.base.util.KumsNoUtil;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.finance.FinanceGroup;
import com.kurui.kums.finance.FinanceOrder;
import com.kurui.kums.finance.FinanceOrderListForm;
import com.kurui.kums.finance.OrderDetail;
import com.kurui.kums.finance.OrderGroup;
import com.kurui.kums.finance.Statement;
import com.kurui.kums.market.Product;
import com.kurui.kums.market.dao.ProductDAO;
import com.kurui.kums.right.SysUser;
import com.kurui.kums.right.UserRightInfo;
import com.kurui.kums.system.OperateLog;
import com.kurui.kums.system.OperateLogDetail;
import com.kurui.kums.system.dao.OperateLogDAO;
import com.kurui.kums.system.dao.OperateLogDetailDAO;
import com.kurui.kums.transaction.Account;
import com.kurui.kums.agent.Agent;
import com.kurui.kums.transaction.Company;
import com.kurui.kums.transaction.Platform;
import com.kurui.kums.transaction.dao.AccountDAO;
import com.kurui.kums.agent.dao.AgentDAO;
import com.kurui.kums.transaction.dao.CompanyDAO;
import com.kurui.kums.transaction.dao.DataTypeDAO;
import com.kurui.kums.transaction.dao.PlatComAccountDAO;
import com.kurui.kums.transaction.dao.PlatformDAO;

public class FinanceOrderBizImp implements FinanceOrderBiz {
	private FinanceOrderDAO financeOrderDAO;
	private OrderDetailDAO orderDetailDAO;
	private StatementDAO statementDAO;
	private KumsNoUtil noUtil;
	private AgentDAO agentDAO;
	private OperateLogDAO operateLogDAO;
	private OperateLogDetailDAO operateLogDetailDAO;
	private PlatComAccountDAO platComAccountDAO;
	private PlatformDAO platformDAO;
	private CompanyDAO companyDAO;
	private AccountDAO accountDAO;
	private DataTypeDAO dataTypeDAO;
	private ProductDAO productDAO;

	static Logger logger = Logger.getLogger(FinanceOrderBizImp.class.getName());

	// 管理费用录入
	public String insertLiveOrder(HttpServletRequest request, FinanceOrder form)
			throws AppException {
		String forwardPage = "";
		Inform inf = new Inform();

		if (form != null) {
			Long[] cussentCompanyIds = form.getCussentCompanyIds();
			String[] outOrderNos = form.getOutOrderNos();
			BigDecimal[] totalAmounts = form.getTotalAmounts();
			Long[] tranTypes = form.getTranTypes();
			String[] memos = form.getMemos();
			String[] businessDates = form.getBusinessDates();

			if (totalAmounts != null && totalAmounts.length > 0) {
				for (int i = 0; i < totalAmounts.length; i++) {
					Long cussentCompanyId = cussentCompanyIds[i];
					String outOrderNo = outOrderNos[i];
					BigDecimal totalAmount = totalAmounts[i];
					Long tranType = tranTypes[i];
					String memo = memos[i];

					Timestamp businessTime = DateUtil.getTimestamp(
							businessDates[i], "yyyy-MM-dd");

					if (Constant.toLong(cussentCompanyId) > 0) {
						form.setCussentCompany(companyDAO
								.getCompanyById(cussentCompanyId));
					} else {
						form.setCussentCompany(null);
					}
					form.setOutOrderNo(outOrderNo);
					form.setTotalAmount(totalAmount);
					form.setTranType(tranType);
					form.setMemo(memo);
					form.setBusinessTime(businessTime);
					createLiveOrder(form, request);
				}
			}
			forwardPage = Statement.ORDERTYPE_1201 + "";
		} else {
			inf.setMessage("表单提交异常");
			inf.setBack(true);
			forwardPage = "inform";
		}
		return forwardPage;
	}

	private String createLiveOrder(FinanceOrder orderForm,
			HttpServletRequest request) throws AppException {
		String forwardPage = "";
		UserRightInfo uri = (UserRightInfo) request.getSession().getAttribute(
				"URI");
		SysUser sysUser = uri.getUser();
		String userNo = sysUser.getUserNo();
		FinanceOrder financeOrder = new FinanceOrder();

		financeOrder.setOutOrderNo(orderForm.getOutOrderNo());

		financeOrder.setPlatform(platformDAO
				.getPlatformById(Platform.SYSTEM_PLATFORM_ID));
		financeOrder.setCompany(companyDAO
				.getCompanyById(Company.SYSTEM_COMPANY_ID));
		financeOrder.setAccount(accountDAO
				.getAccountById(Account.SYSTEM_ACCOUNT_ID));
		// protected Agent agent;
		financeOrder.setCussentCompany(orderForm.getCussentCompany());
		financeOrder.setHandlingCharge(BigDecimal.ZERO);
		financeOrder.setEntryOperator(userNo);
		financeOrder.setTranType(orderForm.getTranType());
		financeOrder.setBusinessType(FinanceOrder.BUSINESSTYPE_2);
		financeOrder.setBusinessTime(orderForm.getBusinessTime());
		financeOrder.setMemo(orderForm.getMemo());
		financeOrder.setStatus(FinanceOrder.STATUS_10);
		financeOrder.setTotalAmount(orderForm.getTotalAmount());
		financeOrder.setCreateTime(new Timestamp(System.currentTimeMillis()));
		financeOrder.setStatementAmount(orderForm.getTotalAmount() + "");

		// 创建一个新组
		OrderGroup og = saveOrderGroup(financeOrder);
		financeOrder.setOrderGroup(og);

		long newSubGroupNo = financeOrderDAO.getNewSubGroupMarkNo(og.getId());
		financeOrder.setSubGroupMarkNo(newSubGroupNo);
		save(financeOrder);

		// 操作日志
		saveOperateLog(financeOrder, uri.getUser(), request,
				OperateLog.TYPE_15, "");

		saveStatementByOrder(financeOrder, sysUser, Statement.ORDERTYPE_1201,
				Statement.TYPE_2, Statement.SUBTYPE_20, Statement.STATUS_1,
				financeOrder.getBusinessTime());

		return forwardPage;
	}

	// 借贷录入
	public String insertCreditOrder(HttpServletRequest request,
			FinanceOrder orderForm) throws AppException {
		String forwardPage = "";
		Inform inf = new Inform();

		if (orderForm != null) {

			UserRightInfo uri = (UserRightInfo) request.getSession()
					.getAttribute("URI");
			SysUser sysUser = uri.getUser();
			String userNo = sysUser.getUserNo();

			FinanceOrder financeOrder = new FinanceOrder();
			financeOrder.setOutOrderNo(orderForm.getOutOrderNo());

			financeOrder.setPlatform(platformDAO
					.getPlatformById(Platform.SYSTEM_PLATFORM_ID));
			financeOrder.setCompany(companyDAO
					.getCompanyById(Company.SYSTEM_FINANCE_COMPANY_ID));

			financeOrder.setAccount(accountDAO
					.getAccountById(Account.SYSTEM_ACCOUNT_ID));
			Agent agent = agentDAO.getAgentById(orderForm.getAgentId());//			
			financeOrder.setAgent(agent);
			financeOrder.setTotalAmount(orderForm.getTotalAmount());
			financeOrder.setStatementAmount(orderForm.getTotalAmount() + "");
			financeOrder.setHandlingCharge(orderForm.getHandlingCharge());
			financeOrder.setEntryOperator(userNo);
			Timestamp businessTime = DateUtil.getTimestamp(orderForm
					.getBusinessDate(), "yyyy-MM-dd");
			financeOrder.setBusinessTime(businessTime);
			financeOrder.setMemo(orderForm.getMemo());
			financeOrder
					.setCreateTime(new Timestamp(System.currentTimeMillis()));
			financeOrder.setMaturityTime(DateUtil.getTimestamp(orderForm
					.getMaturityDate(), "yyyy-MM-dd"));
			financeOrder.setWarningDays(orderForm.getWarningDays());

			// 创建一个新组
			OrderGroup og = saveOrderGroup(financeOrder);
			financeOrder.setOrderGroup(og);

			long newSubGroupNo = financeOrderDAO.getNewSubGroupMarkNo(og
					.getId());
			financeOrder.setSubGroupMarkNo(newSubGroupNo);
			save(financeOrder);

			financeOrder.setTranType(orderForm.getTranType());

			if (financeOrder.getTranType() == FinanceOrder.TRANTYPE_1401) {// 借入债务
				financeOrder.setBusinessType(FinanceOrder.BUSINESSTYPE_1);
				financeOrder.setStatus(FinanceOrder.STATUS_10);

				// 操作日志
				saveOperateLog(financeOrder, uri.getUser(), request,
						OperateLog.TYPE_1001, "");

				saveStatementByOrder(financeOrder, sysUser,
						Statement.ORDERTYPE_1400, Statement.TYPE_1,
						Statement.SUBTYPE_10, Statement.STATUS_1, financeOrder
								.getBusinessTime());

				forwardPage = Statement.ORDERTYPE_1400 + "";
			} else if (financeOrder.getTranType() == FinanceOrder.TRANTYPE_1301) {// 借出债权
				financeOrder.setBusinessType(FinanceOrder.BUSINESSTYPE_2);
				financeOrder.setStatus(FinanceOrder.STATUS_10);

				// 操作日志
				saveOperateLog(financeOrder, uri.getUser(), request,
						OperateLog.TYPE_2001, "");

				saveStatementByOrder(financeOrder, sysUser,
						Statement.ORDERTYPE_1300, Statement.TYPE_2,
						Statement.SUBTYPE_20, Statement.STATUS_1, financeOrder
								.getBusinessTime());

				forwardPage = Statement.ORDERTYPE_1300 + "";
			} else {
				inf.setMessage("未定义的TranType参数");
				inf.setBack(true);
				forwardPage = "inform";
			}
		} else {
			inf.setMessage("表单提交异常");
			inf.setBack(true);
			forwardPage = "inform";
		}
		return forwardPage;
	}

	// 借贷还款/收款录入
	public String insertRepayCreditOrder(HttpServletRequest request,
			FinanceOrder orderForm) throws AppException {
		String forwardPage = "";
		Inform inf = new Inform();

		if (orderForm != null) {

			UserRightInfo uri = (UserRightInfo) request.getSession()
					.getAttribute("URI");
			SysUser sysUser = uri.getUser();
			String userNo = sysUser.getUserNo();
			Long oldOrderId = orderForm.getId();
			FinanceOrder oldOrder = getFinanceOrderById(oldOrderId);
			// String creditAmount=financeOrderDAO.getCreditAmount(oldOrderId);

			Long finishedStatus = Constant
					.toLong(orderForm.getFinishedStatus());
			if (finishedStatus > 0) {
				oldOrder.setStatus(finishedStatus);
				update(oldOrder);
			}

			FinanceOrder financeOrder = new FinanceOrder();
			financeOrder.setOutOrderNo(orderForm.getOutOrderNo());

			financeOrder.setPlatform(platformDAO
					.getPlatformById(Platform.SYSTEM_PLATFORM_ID));
			financeOrder.setCompany(companyDAO
					.getCompanyById(Company.SYSTEM_FINANCE_COMPANY_ID));

			financeOrder.setAccount(accountDAO.getAccountById(orderForm
					.getAccountId()));

			financeOrder.setAgent(oldOrder.getAgent());
			financeOrder.setTotalAmount(orderForm.getTotalAmount());
			financeOrder.setStatementAmount(orderForm.getTotalAmount() + "");
			financeOrder.setHandlingCharge(orderForm.getHandlingCharge());
			financeOrder.setEntryOperator(userNo);
			String businessDate = orderForm.getBusinessDate();
			Timestamp businessTime = DateUtil.getTimestamp(businessDate,
					"yyyy-MM-dd");
			financeOrder.setBusinessTime(businessTime);
			financeOrder.setMemo(orderForm.getMemo());
			financeOrder
					.setCreateTime(new Timestamp(System.currentTimeMillis()));

			// 与借贷订单为同一组
			OrderGroup og = oldOrder.getOrderGroup();
			financeOrder.setOrderGroup(og);

			// long newSubGroupNo =
			// financeOrderDAO.getNewSubGroupMarkNo(og.getId());
			long newSubGroupNo = oldOrder.getSubGroupMarkNo();// 同一小组
			financeOrder.setSubGroupMarkNo(newSubGroupNo);
			save(financeOrder);

			financeOrder.setTranType(orderForm.getTranType());

			if (financeOrder.getTranType() == FinanceOrder.TRANTYPE_1411) {// 偿还债务
				financeOrder.setBusinessType(FinanceOrder.BUSINESSTYPE_2);
				if (finishedStatus > 0) {
					financeOrder.setStatus(finishedStatus);
				} else {
					financeOrder.setStatus(FinanceOrder.STATUS_10);
				}

				// 操作日志
				saveOperateLog(financeOrder, uri.getUser(), request,
						OperateLog.TYPE_1011, "");

				saveStatementByOrder(financeOrder, sysUser,
						Statement.ORDERTYPE_1400, Statement.TYPE_2,
						Statement.SUBTYPE_21, Statement.STATUS_1, financeOrder
								.getBusinessTime());

				forwardPage = Statement.ORDERTYPE_1400 + "";
			} else if (financeOrder.getTranType() == FinanceOrder.TRANTYPE_1311) {// 债权收款
				financeOrder.setBusinessType(FinanceOrder.BUSINESSTYPE_1);
				if (finishedStatus > 0) {
					financeOrder.setStatus(finishedStatus);
				} else {
					financeOrder.setStatus(FinanceOrder.STATUS_10);
				}

				// 操作日志
				saveOperateLog(financeOrder, uri.getUser(), request,
						OperateLog.TYPE_2011, "");

				saveStatementByOrder(financeOrder, sysUser,
						Statement.ORDERTYPE_1300, Statement.TYPE_1,
						Statement.SUBTYPE_11, Statement.STATUS_1, financeOrder
								.getBusinessTime());

				forwardPage = Statement.ORDERTYPE_1300 + "";
			}
		} else {
			inf.setMessage("表单提交异常");
			inf.setBack(true);
			forwardPage = "inform";
		}
		return forwardPage;
	}

	// 主营业务录入
	public String insertMainOrder(HttpServletRequest request,
			FinanceOrder orderForm) throws AppException {
		String forwardPage = "";

		if (orderForm != null) {

			UserRightInfo uri = (UserRightInfo) request.getSession()
					.getAttribute("URI");
			SysUser sysUser = uri.getUser();
			String userNo = sysUser.getUserNo();

			FinanceOrder financeOrder = new FinanceOrder();
			financeOrder.setOutOrderNo(orderForm.getOutOrderNo());

			financeOrder.setPlatform(platformDAO.getPlatformById(orderForm
					.getPlatformId()));
			financeOrder.setCompany(companyDAO.getCompanyById(orderForm
					.getCompanyId()));
			financeOrder.setAccount(accountDAO.getAccountById(orderForm
					.getAccountId()));

			Agent agent = agentDAO.getAgentById(orderForm.getAgentId());//	
			financeOrder.setAgent(agent);
			financeOrder.setTotalAmount(orderForm.getTotalAmount());
			financeOrder.setStatementAmount(orderForm.getTotalAmount() + "");
			financeOrder.setHandlingCharge(orderForm.getHandlingCharge());
			financeOrder.setEntryOperator(userNo);
			
			Timestamp businessTime=DateUtil.getTimestamp(orderForm.getBusinessDate(), "yyyy-MM-dd");
			financeOrder.setBusinessTime(businessTime);
			
			financeOrder.setMemo(orderForm.getMemo());
			financeOrder
					.setCreateTime(new Timestamp(System.currentTimeMillis()));

			// 创建一个新组
			OrderGroup og = saveOrderGroup(financeOrder);
			financeOrder.setOrderGroup(og);

			long newSubGroupNo = financeOrderDAO.getNewSubGroupMarkNo(og
					.getId());
			financeOrder.setSubGroupMarkNo(newSubGroupNo);
			save(financeOrder);

			financeOrder.setTranType(orderForm.getTranType());

			financeOrder.setBusinessType(FinanceOrder.BUSINESSTYPE_1);
			financeOrder.setStatus(FinanceOrder.STATUS_10);

			// 保存订单明细
			insertOrderDetail(orderForm, financeOrder, request);

			// 操作日志
			saveOperateLog(financeOrder, uri.getUser(), request,
					OperateLog.TYPE_1, "");

			saveStatementByOrder(financeOrder, sysUser, Statement.ORDERTYPE_15,
					Statement.TYPE_1, Statement.SUBTYPE_10, Statement.STATUS_1,
					financeOrder.getBusinessTime());

			forwardPage = Statement.ORDERTYPE_15 + "";
		} else {
			forwardPage = "NULLFORM";
		}
		return forwardPage;
	}

	/**
	 * 保存订单明细
	 */
	private String insertOrderDetail(FinanceOrder orderForm,
			FinanceOrder financeOrder, HttpServletRequest request)
			throws AppException {
		String result = "";
		Long[] productIds = orderForm.getProductIds();
		String[] productNames = orderForm.getProductNames();
		BigDecimal[] productPrices = orderForm.getProductPrices();
		BigDecimal[] productCounts = orderForm.getProductCounts();
		BigDecimal[] detailAmounts = orderForm.getDetailAmounts();
		String[] detailMemos = orderForm.getDetailMemos();

		for (int i = 0; i < productIds.length; i++) {
			Long productId = productIds[i];
			String productName = productNames[i];
			BigDecimal productPrice = productPrices[i];
			BigDecimal productCount = productCounts[i];
			BigDecimal detailAmount = detailAmounts[i];
			String detailMemo = detailMemos[i];
			OrderDetail orderDetail = new OrderDetail();
			Product product = productDAO.getProductById(productId);
			orderDetail.setProduct(product);
			orderDetail.setFinanceOrder(financeOrder);
			orderDetail.setDiscountPrice(productPrice);
			orderDetail.setProductCount(productCount);
			orderDetail.setDetailAmount(detailAmount);
			orderDetail.setStatus(OrderDetail.STATES_1);
			orderDetail.setMemo(detailMemo);
			long num = orderDetailDAO.save(orderDetail);
		}

		return result;
	}

	// 保存订单修改
	public String updateOrder(FinanceOrder form, HttpServletRequest request)
			throws AppException {
		String forwardPage = "";

		if (form != null) {
			UserRightInfo uri = (UserRightInfo) request.getSession()
					.getAttribute("URI");

			Long[] financeOrderIds = form.getFinanceOrderIds();
			Long[] tranTypes = form.getTranTypes();
			Long[] platformIds = form.getPlatformIds();
			Long[] companyIds = form.getCompanyIds();
			// Long[] cussentCompanyIds=form.getCussentCompanyIds();
			String[] outOrderNos = form.getOutOrderNos();
//			Timestamp[] businessTimes = form.getBusinessTimes();
			String[] businessDates = form.getBusinessDates();
			
			BigDecimal[] handlingCharges = form.getHandlingCharges();

			if (financeOrderIds != null) {
				List<FinanceOrder> tempOrderList = new ArrayList<FinanceOrder>();
				for (int i = 0; i < financeOrderIds.length; i++) {
					Long id = Constant.toLong(financeOrderIds[i]);
					if (id > 0) {
						FinanceOrder order = getFinanceOrderById(id);
						tempOrderList.add(order);

						order.setTranType(tranTypes[i]);
						order.setPlatform(platformDAO
								.getPlatformById(platformIds[i]));
						order.setCompany(companyDAO
								.getCompanyById(companyIds[i]));

						if (order.getPlatform() == null
								|| order.getCompany() == null) {
							return "ACCOUNTERROR";
						}

						// Long cussentCompanyId = cussentCompanyIds[i];
						// if(cussentCompanyId!=null&&cussentCompanyId>0){
						// order.setCussentCompany(companyDAO.getCompanyById(cussentCompanyId));
						// } else {
						// order.setCussentCompany(null);
						// }
						order.setOutOrderNo(outOrderNos[i]);// 订单号
						order.setHandlingCharge(handlingCharges[i]);// 手续费

						order.setMemo(form.getMemo());

						if (businessDates != null && businessDates[i] == null
								|| "".equals(businessDates[i])) {
							order.setBusinessTime(new Timestamp(System
									.currentTimeMillis()));
						} else {
							
							order.setBusinessTime(DateUtil.getTimestamp(businessDates[i], "yyyy-MM-dd"));
						}
						updateOrderGroup(order);

						update(order);

						// 操作日志
						saveOperateLog(order, uri.getUser(), request,
								OperateLog.TYPE_202, "");
						forwardPage = order.getId() + "";
					} else {
						forwardPage = "NOORDER";
					}
				}

			} else {
				forwardPage = "NOORDER";
			}
		} else {
			forwardPage = "NOORDER";
		}

		return forwardPage;
	}

	// 跳转到编辑页面
	public void editOrder(FinanceOrderListForm folf, HttpServletRequest request)
			throws AppException {
		if (folf == null) {
			folf = new FinanceOrderListForm();
		}

		Long id = folf.getId();
		if (id != null && id > 0) {
			FinanceOrder order = getFinanceOrderById(id);
			request.setAttribute("financeOrder", order);

			List<FinanceOrder> orderList = financeOrderDAO
					.listBySubGroupAndGroupId(order.getOrderGroup().getId(),
							order.getSubGroupMarkNo());

			request.setAttribute("financeOrderList", orderList);

			List tranTypeList = dataTypeDAO.getSameLevelDataTypeList(order
					.getTranType());
			request.setAttribute("tranTypeList", tranTypeList);

			String ordersString = "";
			long orderType = Long.valueOf(0);
			for (int i = 0; i < orderList.size(); i++) {
				FinanceOrder tempOrder = orderList.get(i);
				ordersString += tempOrder.getId() + ",";
				orderType = tempOrder.getOrderType();
			}
			if (ordersString.length() > 1) {
				ordersString = ordersString.substring(0,
						ordersString.length() - 1);
			}

			List<Statement> statementList = statementDAO
					.getStatementListByOrders(ordersString, orderType);
			request.setAttribute("statementList", statementList);
		} else {
			folf.setList(new ArrayList());
		}

	}

	// 跳转到关联订单页面
	public void processing(FinanceOrderListForm ulf, HttpServletRequest request)
			throws AppException {
		String path = request.getContextPath();
		ulf.setPerPageNum(100);
		if (ulf == null) {
			ulf = new FinanceOrderListForm();
		}
		UserRightInfo uri = (UserRightInfo) request.getSession().getAttribute(
				"URI");

		try {
			Long financeOrderId = ulf.getId();
			if (financeOrderId != null && financeOrderId > 0) {
				FinanceOrder order = getFinanceOrderById(financeOrderId);
				List<FinanceOrder> orderList = listByGroupId(order
						.getOrderGroup().getId());

				for (FinanceOrder ao : orderList) {
					ao.setUri(uri);
					ao.setPath(path);
				}

				List groupList = FinanceGroup.getGroupList(orderList);
				ulf.setList(groupList);
			} else {
				ulf.setList(new ArrayList());
			}
		} catch (Exception ex) {
			ulf.setList(new ArrayList());
		}
		request.setAttribute("ulf", ulf);
	}

	// 删除订单(改变状态)
	public void deleteFinanceOrder(Long id) throws AppException {
		FinanceOrder financeOrder = getFinanceOrderById(id);
		financeOrder.setStatus(FinanceOrder.STATUS_88);// 将订单状态变为已废弃
		update(financeOrder);

		deleteStatementByOrderId(id);
		deleteDetailByOrderId(id);
	}

	// 删除订单的关联结算记录(改变状态)
	public void deleteStatementByOrderId(Long orderId) throws AppException {
		List statementList = statementDAO.getStatementListByOrder(orderId,
				Statement.ORDERTABLE_1);
		for (int i = 0; i < statementList.size(); i++) {
			Statement statement = (Statement) statementList.get(i);
			statement.setStatus(Statement.STATUS_88);// 已废弃
			statementDAO.update(statement);
		}
	}

	// 删除订单的关联明细记录(改变状态)
	public void deleteDetailByOrderId(Long orderId) throws AppException {
		List orderDetailList = orderDetailDAO.getOrderDetailByOrderId(orderId);
		for (int i = 0; i < orderDetailList.size(); i++) {
			OrderDetail orderDetail = (OrderDetail) orderDetailList.get(i);
			orderDetail.setStatus(OrderDetail.STATES_0);//
			orderDetailDAO.update(orderDetail);
		}
	}

	public String view(long id, HttpServletRequest request) throws AppException {
		String forwardPage = "";

		if (id > 0) {
			List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();

			List<Statement> statementList = new ArrayList<Statement>();
			List<OperateLog> operateLogList = new ArrayList<OperateLog>();

			FinanceOrder financeOrder = getFinanceOrderById(id);
			if (financeOrder != null) {
				long subGroupNo = financeOrder.getSubGroupMarkNo();

				List<FinanceOrder> financeOrderList = listBySubGroupAndGroupId(
						financeOrder.getOrderGroup().getId(), subGroupNo);
				String ordersString = "";

				for (int i = 0; i < financeOrderList.size(); i++) {
					FinanceOrder tempOrder = financeOrderList.get(i);
					ordersString += tempOrder.getId() + ",";
				}
				if (ordersString.length() > 1) {
					ordersString = ordersString.substring(0, ordersString
							.length() - 1);
				}
				// System.out.println("orderString:" + ordersString);

				statementList = statementDAO.getStatementListByOrders(
						ordersString, Statement.ORDERTABLE_1);

				request.setAttribute("financeOrder", financeOrder);
				request.setAttribute("statementList", statementList);
				request.setAttribute("financeOrderList", financeOrderList);

				orderDetailList = orderDetailDAO
						.getOrderDetailListByOrderIds(ordersString);
				request.setAttribute("orderDetailList", orderDetailList);

				operateLogList = operateLogDAO
						.getOperateLogByOrderIds(ordersString);
				request.setAttribute("operateLogList", operateLogList);
				forwardPage = financeOrder.getOrderGroup().getNo();
			} else {
				forwardPage = "NOORDER";
			}
		} else {
			forwardPage = "ERROR";
		}
		return forwardPage;
	}

	public HttpServletRequest loadFlightAgentByfinanceOrder(FinanceOrder order,
			HttpServletRequest request) throws AppException {
		List<Agent> agentList = agentDAO.getAgentList();
		request.setAttribute("agentList", agentList);
		return request;
	}

	// ------DWR
	public FinanceOrder getFinanceOrderByGroupIdAndTranType(long groupId,
			String tranType) throws AppException {
		FinanceOrder order = new FinanceOrder();
		List financeOrderList = listByGroupIdAndTranType(groupId, tranType);
		if (financeOrderList != null && financeOrderList.size() > 0) {
			order = (FinanceOrder) financeOrderList.get(0);
		}

		if (order.getAgent() != null) {
			String agentName = order.getAgent().getName();
			System.out.println(agentName);
		}

		return order;
	}

	public FinanceOrder getFinanceOrderByGroupIdStatus(long groupId,
			String tranType, String status) throws AppException {
		FinanceOrder ao = new FinanceOrder();
		List financeOrderList = financeOrderDAO.listByGroupIdAndTranTypeStatus(
				groupId, tranType, status);
		if (financeOrderList != null && financeOrderList.size() > 0) {
			ao = (FinanceOrder) financeOrderList.get(0);
		}
		return ao;
	}

	private OrderGroup saveOrderGroup(FinanceOrder ao) throws AppException {
		OrderGroup og = new OrderGroup();
		og.setNo(noUtil.getFinanceGroupNo());
		if (ao.getBusinessTime() == null) {
			og.setFirstTime(new Timestamp(System.currentTimeMillis()));
		} else {
			og.setFirstTime(ao.getBusinessTime());
		}

		og.setLastTime(new Timestamp(System.currentTimeMillis()));
		financeOrderDAO.saveOrderGroup(og);
		return og;
	}

	private OrderGroup updateOrderGroup(FinanceOrder ao) throws AppException {
		OrderGroup og = financeOrderDAO.getOrderGroupById(ao.getOrderGroup()
				.getId());
		og.setLastTime(new Timestamp(System.currentTimeMillis()));
		financeOrderDAO.saveOrderGroup(og);
		return og;
	}

	/**
	 * 保存结算记录
	 * 
	 * @param financeOrder
	 *            order
	 * @param SysUser
	 *            sysUser
	 * @param long
	 *            statementType
	 * @param long
	 *            orderSubtype
	 * @param long
	 *            statementStatus
	 * @param Timestamp
	 *            statementTime
	 */
	public void saveStatementByOrder(FinanceOrder order, SysUser sysUser,
			long orderType, long statementType, long orderSubtype,
			long statementStatus, Timestamp statementTime) throws AppException {
		long orderId = order.getId();
		long orderGroupId = order.getOrderGroup().getId();
		FinanceOrderStore.addOrderString(orderGroupId);
		// 结算
		Statement statement = new Statement();
		statement.setStatementNo(noUtil.getStatementNo());// 结算单号
		statement.setOrderId(orderId);
		statement.setOrderType(orderType);
		statement.setOrderSubtype(orderSubtype);
		if (statementType == Statement.TYPE_1) {
			statement.setToAccount(order.getAccount());
		} else if (statementType == Statement.TYPE_2) {
			statement.setFromAccount(order.getAccount());
		}
		statement.setTotalAmount(order.getTotalAmount());

		statement.setSysUser(sysUser);
		statement.setOptTime(new Timestamp(System.currentTimeMillis()));// 修改时间
		if (statementTime != null) {
			statement.setStatementTime(statementTime);// 结算时间
		} else {
			logger.info(statement.getStatementNo() + "--结算时间为空,默认取当前修改时间为结算时间");
			statement
					.setStatementTime(new Timestamp(System.currentTimeMillis()));// 结算时间
		}

		if (orderSubtype == Statement.SUBTYPE_20) {
			// statement.setMemo(order.getOutMemo());
		}

		statement.setType(statementType);// 类型
		statement.setStatus(statementStatus);// 状态
		statement.setOrderTable(Statement.ORDERTABLE_1);
		statementDAO.save(statement);

		OperateLog operateLog = new OperateLog();
		operateLog.setOrderId(statement.getOrderId());
		operateLog.setOrderType(order.getOrderType());
		operateLog.setSysUser(statement.getSysUser());// 操作员
		operateLog.setOptTime(new Timestamp(System.currentTimeMillis()));
		operateLog.setType(OperateLog.TYPE_98);
		operateLog.setStatus(new Long(1));

		OperateLogDetail detail = new OperateLogDetail();
		detail.setOrderNo(statement.getStatementNo());
		detail.setContent("创建了" + statement.toLogString());

		operateLogDetailDAO.save(detail);

		operateLog.setOperateLogDetail(detail);

		operateLogDAO.save(operateLog);
	}

	/**
	 * 修改保存结算记录
	 */
	public void updateStatementByOrder(FinanceOrder order, SysUser sysUser,
			long statementType, long statementStatus, Timestamp statementTime)
			throws AppException {
	}

	public void saveOperateLog(FinanceOrder order, SysUser sysUser,
			HttpServletRequest request, long operateLogType, String content)
			throws AppException {
		OperateLog operateLog = new OperateLog();
		operateLog.setOrderId(order.getId());
		operateLog.setOrderType(order.getOrderType());
		operateLog.setSysUser(sysUser);// 操作员
		operateLog.setOptTime(new Timestamp(System.currentTimeMillis()));
		operateLog.setType(operateLogType);
		operateLog.setStatus(1L);

		OperateLogDetail detail = new OperateLogDetail();
		if (request != null) {
			detail.setIp(HttpInvoker.getRemoteIP(request));// IP
		}
		detail.setOrderNo(order.getOrderNo());
		detail.setContent(content);

		operateLogDetailDAO.save(detail);

		operateLog.setOperateLogDetail(detail);

		operateLogDAO.save(operateLog);
	}

	public FinanceOrder getFinanceOrderByStatementId(long statementId)
			throws AppException {
		return financeOrderDAO.getFinanceOrderByStatementId(statementId);
	}

	public List<FinanceOrder> listByGroupId(long groupId) throws AppException {
		return financeOrderDAO.listByGroupId(groupId);
	}

	public List<FinanceOrder> listBySubGroupAndGroupId(long orderGroupId,
			Long subMarkNo) throws AppException {
		return financeOrderDAO
				.listBySubGroupAndGroupId(orderGroupId, subMarkNo);
	}

	public List<FinanceOrder> listByAgent(long agentId, String tranType)
			throws AppException {
		return financeOrderDAO.listByAgent(agentId, tranType);
	}

	// ---------DWR有调用
	public List<FinanceOrder> listByGroupIdAndTranType(long groupId,
			String tranType) throws AppException {
		return financeOrderDAO.listByGroupIdAndTranType(groupId, tranType);
	}

	public List<FinanceOrder> listByGroupIdAndBusinessTranType(long groupId,
			String tranType, String businessType) throws AppException {
		return financeOrderDAO.listByGroupIdAndBusinessTranType(groupId,
				tranType, businessType);
	}

	public long getGroupIdByOrderId(long orderId) throws AppException {
		return financeOrderDAO.getGroupIdByOrderId(orderId);
	}

	public List list(FinanceOrderListForm rlf, UserRightInfo uri)
			throws AppException {
		return financeOrderDAO.list(rlf, uri);
	}

	public List listData(FinanceOrderListForm rlf, UserRightInfo uri)
			throws AppException {
		return financeOrderDAO.listData(rlf, uri);
	}
	
	public List<FinanceOrder> listFinanceForAssetsItem(FinanceOrderListForm ulf)
			throws AppException{
		return financeOrderDAO.listFinanceForAssetsItem(ulf);
	}

	public List list() throws AppException {
		return financeOrderDAO.list();
	}

	public void delete(long id) throws AppException {
		financeOrderDAO.delete(id);
	}

	public long save(FinanceOrder financeOrder) throws AppException {
		return financeOrderDAO.save(financeOrder);
	}

	public long update(FinanceOrder financeOrder) throws AppException {
		return financeOrderDAO.update(financeOrder);
	}

	public FinanceOrder getFinanceOrderById(long orderId) throws AppException {
		return financeOrderDAO.getFinanceOrderById(orderId);
	}

	public List listIDBySubGroupAndGroupId(long orderGroupId, Long subMarkNo)
			throws AppException {
		return financeOrderDAO.listIDBySubGroupAndGroupId(orderGroupId,
				subMarkNo);
	}

	public void setfinanceOrderDAO(FinanceOrderDAO financeOrderDAO) {
		this.financeOrderDAO = financeOrderDAO;
	}

	public void setStatementDAO(StatementDAO statementDAO) {
		this.statementDAO = statementDAO;
	}

	public void setNoUtil(KumsNoUtil noUtil) {
		this.noUtil = noUtil;
	}

	public void setAgentDAO(AgentDAO agentDAO) {
		this.agentDAO = agentDAO;
	}

	public void setOperateLogDAO(OperateLogDAO operateLogDAO) {
		this.operateLogDAO = operateLogDAO;
	}

	public void setAccountDAO(AccountDAO accountDAO) {
		this.accountDAO = accountDAO;
	}

	public void setPlatformDAO(PlatformDAO platformDAO) {
		this.platformDAO = platformDAO;
	}

	public void setCompanyDAO(CompanyDAO companyDAO) {
		this.companyDAO = companyDAO;
	}

	public void setPlatComAccountDAO(PlatComAccountDAO platComAccountDAO) {
		this.platComAccountDAO = platComAccountDAO;
	}

	public void setDataTypeDAO(DataTypeDAO dataTypeDAO) {
		this.dataTypeDAO = dataTypeDAO;
	}

	public void setProductDAO(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}

	public void setOrderDetailDAO(OrderDetailDAO orderDetailDAO) {
		this.orderDetailDAO = orderDetailDAO;
	}

	public void setOperateLogDetailDAO(OperateLogDetailDAO operateLogDetailDAO) {
		this.operateLogDetailDAO = operateLogDetailDAO;
	}

}
