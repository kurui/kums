package com.kurui.kums.finance.action;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;
import org.hsqldb.lib.StringUtil;

import com.kurui.kums.finance.biz.FinanceOrderBiz;
import com.kurui.kums.finance.FinanceGroup;
import com.kurui.kums.finance.FinanceOrder;
import com.kurui.kums.finance.FinanceOrderListForm;
import com.kurui.kums.report.biz.ReportBiz;
import com.kurui.kums.right.UserRightInfo;
import com.kurui.kums.transaction.DataType;
import com.kurui.kums.transaction.biz.DataTypeBiz;
import com.kurui.kums.transaction.util.PlatComAccountStore;
import com.kurui.kums.base.struts.BaseAction;
import com.kurui.kums.base.ui.inform.Inform;
import com.kurui.kums.base.threads.listener.PerformListener;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.base.Constant;

public class FinanceOrderListAction extends BaseAction {
	private FinanceOrderBiz financeOrderBiz;
	private DataTypeBiz dataTypeBiz;
	private ReportBiz reportBiz;

	/***************************************************************************
	 * 主营业务录入
	 **************************************************************************/
	public ActionForward addMainOrder(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		Inform inf = new Inform();
		FinanceOrderListForm ulf = (FinanceOrderListForm) form;
		FinanceOrder financeOrder = new FinanceOrder();
		financeOrder.setThisAction("insertMainOrder");
		financeOrder.setBusinessTime(new Timestamp(System.currentTimeMillis()));

		long companyId = Constant.toLong(ulf.getCompanyId());
		if (companyId > 0) {
			financeOrder.setCompanyId(companyId);
		}

		long ulfTranType = ulf.getTranType();
		if (ulfTranType > 0) {
			if (ulfTranType == FinanceOrder.TRANTYPE_1501) {
				// tranTypeText = "保健品销售";
			} else if (ulfTranType == FinanceOrder.TRANTYPE_1560) {
				// tranTypeText = "充值销售";
			} else if (ulfTranType == FinanceOrder.TRANTYPE_1565) {
				// tranTypeText = "网购销售(实物)";
			} else if (ulfTranType == FinanceOrder.TRANTYPE_1570) {
				// tranTypeText = "机票销售";
			} else if (ulfTranType == FinanceOrder.TRANTYPE_1580) {
				// tranTypeText = "软件销售";
			} else if (ulfTranType == FinanceOrder.TRANTYPE_1585) {
				// tranTypeText = "软件劳务";
			} else if (ulfTranType == FinanceOrder.TRANTYPE_1590) {
				// tranTypeText = "金融衍生品销售";
			}else if (ulfTranType == FinanceOrder.TRANTYPE_1591) {
				// tranTypeText = "保险销售";
			} else {
				inf.setMessage("未定义的TranType");
				return forwardInformPage(inf, mapping, request);
			}
			financeOrder.setTranType(ulfTranType);
		}

		List<DataType> tranTypeList = dataTypeBiz.getSubDataTypeList("15");
		request.setAttribute("tranTypeList", tranTypeList);

		forwardPage = "editMainOrder";

		request.setAttribute("financeOrder", financeOrder);
		request = loadPlatComAccountStoreForRequest(request);

		return (mapping.findForward(forwardPage));
	}

	/***************************************************************************
	 * 借贷录入
	 **************************************************************************/
	public ActionForward addCreditOrder(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		FinanceOrderListForm ulf = (FinanceOrderListForm) form;
		FinanceOrder financeOrder = new FinanceOrder();
		financeOrder.setThisAction("insertCreditOrder");
		financeOrder.setBusinessTime(new Timestamp(System.currentTimeMillis()));
		financeOrder.setMaturityTime(new Timestamp(System.currentTimeMillis()));
		financeOrder.setWarningDays("5");
		financeOrder.setHandlingCharge(BigDecimal.ZERO);
		financeOrder.setTranType(ulf.getTranType());

		request.setAttribute("financeOrder", financeOrder);

		request = loadPlatComAccountStoreForRequest(request);

		forwardPage = "editCreditOrder";
		return (mapping.findForward(forwardPage));
	}

	/***************************************************************************
	 * 偿还借款
	 **************************************************************************/
	public ActionForward addRepayCreditOrder(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws AppException {
		String forwardPage = "";
		FinanceOrderListForm ulf = (FinanceOrderListForm) form;
		Inform inf = new Inform();
		long financeOrderId = 0;
		if (ulf.getSelectedItems() != null && ulf.getSelectedItems().length > 0) {
			financeOrderId = ulf.getSelectedItems()[0];
		} else {
			financeOrderId = ulf.getId();
		}
		FinanceOrder financeOrder = financeOrderBiz
				.getFinanceOrderById(financeOrderId);
		financeOrder.setThisAction("insertRepayCreditOrder");
		financeOrder.setHandlingCharge(BigDecimal.ZERO);

		Long tranType = Constant.toLong(financeOrder.getTranType());
		if (tranType > 0) {
			if (tranType == FinanceOrder.TRANTYPE_1401) {// 债务
				financeOrder.setTranType(FinanceOrder.TRANTYPE_1411);
				financeOrder.setFinishedStatus(FinanceOrder.STATUS_11);
			} else if (tranType == FinanceOrder.TRANTYPE_1301) {// 债权
				financeOrder.setTranType(FinanceOrder.TRANTYPE_1311);
				financeOrder.setFinishedStatus(FinanceOrder.STATUS_11);
			}
		} else {
			inf.setMessage("缺少TranType参数");
			return forwardInformPage(inf, mapping, request);
		}

		request.setAttribute("financeOrder", financeOrder);

		request = loadPlatComAccountStoreForRequest(request);

		forwardPage = "editRepayCreditOrder";
		return (mapping.findForward(forwardPage));
	}

	/***************************************************************************
	 * 借贷编辑
	 **************************************************************************/
	public ActionForward editCreditOrder(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws AppException {
		String forwardPage = "";
		FinanceOrderListForm ulf = (FinanceOrderListForm) form;
		long financeOrderId = 0;
		if (ulf.getSelectedItems() != null) {
			financeOrderId = ulf.getSelectedItems()[0];
		} else {
			financeOrderId = ulf.getId();
		}
		FinanceOrder financeOrder = financeOrderBiz
				.getFinanceOrderById(financeOrderId);
		financeOrder.setThisAction("updateCreditOrder");
		request.setAttribute("financeOrder", financeOrder);
		forwardPage = "editCreditOrder";
		return (mapping.findForward(forwardPage));
	}

	/***************************************************************************
	 * 管理费用录入
	 **************************************************************************/
	public ActionForward addLiveOrder(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		FinanceOrderListForm ulf = (FinanceOrderListForm) form;
		FinanceOrder financeOrder = new FinanceOrder();
		financeOrder.setThisAction("insertLiveOrder");
		financeOrder.setBusinessTime(new Timestamp(System.currentTimeMillis()));

		request.setAttribute("financeOrder", financeOrder);

		forwardPage = "editLiveOrder";
		return (mapping.findForward(forwardPage));
	}

	/***************************************************************************
	 * 管理费用列表
	 **************************************************************************/
	public ActionForward listLiveOrder(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		Inform inf = new Inform();
		FinanceOrderListForm ulf = (FinanceOrderListForm) form;
		if (ulf == null) {
			ulf = new FinanceOrderListForm();
		}
		ulf.setOrderType(1201);
		request = setSearchToolBar(request, ulf);
		
		long beginTime=System.currentTimeMillis();
		try {
			String showType = ulf.getShowType();
			if ("listChart".equals(showType)) {
				List<FinanceOrder> orderList = setAllFinanceOrderList(request,
						ulf);
				request = reportBiz.setLiveChartList(orderList, ulf, request,
						response);
				forwardPage = "listLiveOrderChart";
			} else if ("listReport".equals(showType)) {
				List<FinanceOrder> orderList = setAllFinanceOrderList(request,
						ulf);
				reportBiz.exportLiveReport(orderList);
				inf.setMessage("导出成功");
				return forwardInformPage(inf, mapping, request);
			} else {
				ulf = setFinanceOrderListForm(request, ulf);
				ulf.addSumField(1, "totalAmount");
				forwardPage = "listLiveOrder";
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			ulf.setList(new ArrayList());
		}
		
		new PerformListener("管理费用列表", beginTime);
		request.setAttribute("ulf", ulf);
		return (mapping.findForward(forwardPage));
	}

	/***************************************************************************
	 * 主营业务列表
	 **************************************************************************/
	public ActionForward listMainOrder(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		FinanceOrderListForm ulf = (FinanceOrderListForm) form;
		if (ulf == null) {
			ulf = new FinanceOrderListForm();
		}
		ulf.setOrderType(15);
		request = setSearchToolBar(request, ulf);

		long beginTime = System.currentTimeMillis();
		try {
			String showType = ulf.getShowType();
			if ("listChart".equals(showType)) {
				// List<FinanceOrder> orderList = setFinanceOrderList(request,
				// ulf);
				// request = reportBiz.setLiveChartList(orderList,request, ulf);

				ulf = setFinanceOrderGroup(request, ulf);
			} else {
				ulf = setFinanceOrderGroup(request, ulf);
				ulf.addSumField(1, "saleAmount");
			}
			forwardPage = "listMainOrder";
		} catch (Exception ex) {
			ex.printStackTrace();
			ulf.setList(new ArrayList());
		}

		new PerformListener("主营业务列表", beginTime);

		request.setAttribute("ulf", ulf);
		return (mapping.findForward(forwardPage));
	}

	/***************************************************************************
	 * 债权债务列表
	 **************************************************************************/
	public ActionForward listCreditOrder(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws AppException {
		String forwardPage = "";
		FinanceOrderListForm ulf = (FinanceOrderListForm) form;
		if (ulf == null) {
			ulf = new FinanceOrderListForm();
		}
		request = setSearchToolBar(request, ulf);
		try {
			String showType = ulf.getShowType();
			if ("listChart".equals(showType)) {
				ulf = setFinanceOrderGroup(request, ulf);
			} else {
				ulf = setFinanceOrderGroup(request, ulf);
			}
			forwardPage = "listCreditOrder";
		} catch (Exception ex) {
			ex.printStackTrace();
			ulf.setList(new ArrayList());
		}
		request.setAttribute("ulf", ulf);
		return (mapping.findForward(forwardPage));
	}

	/***************************************************************************
	 * 订单列表
	 **************************************************************************/
	public ActionForward listFinanceOrder(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws AppException {
		String forwardPage = "";
		FinanceOrderListForm ulf = (FinanceOrderListForm) form;
		if (ulf == null) {
			ulf = new FinanceOrderListForm();
		}
		request = setSearchToolBar(request, ulf);
		try {
			String showType = ulf.getShowType();
			if ("listChart".equals(showType)) {
				ulf = setFinanceOrderGroup(request, ulf);
			} else {
				ulf = setFinanceOrderGroup(request, ulf);
			}
			forwardPage = "listFinanceOrder";
		} catch (Exception ex) {
			ex.printStackTrace();
			ulf.setList(new ArrayList());
		}
		request.setAttribute("ulf", ulf);
		return (mapping.findForward(forwardPage));
	}
	
	//从日记账中选择资产录入
	public ActionForward listFinanceForAssetsItem(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws AppException {
		String forwardPage = "";
		FinanceOrderListForm ulf = (FinanceOrderListForm) form;
		if (ulf == null) {
			ulf = new FinanceOrderListForm();
		}
		try {
			List<FinanceOrder> orderList = financeOrderBiz.listFinanceForAssetsItem(ulf);
			ulf.setList(orderList);
			forwardPage = "listFinanceForAssetsItem";
		} catch (Exception ex) {
			ex.printStackTrace();
			ulf.setList(new ArrayList());
		}
		request.setAttribute("ulf", ulf);
		return (mapping.findForward(forwardPage));
	}


	private HttpServletRequest setSearchToolBar(HttpServletRequest request,
			FinanceOrderListForm ulf) throws AppException {
		UserRightInfo uri = (UserRightInfo) request.getSession().getAttribute(
				"URI");
		saveCustomerSession(request, uri, ulf);
		request = loadPlatComAccountStoreForRequest(request);
		return request;
	}

	public List<FinanceOrder> setAllFinanceOrderList(
			HttpServletRequest request, FinanceOrderListForm ulf)
			throws AppException {
		UserRightInfo uri = (UserRightInfo) request.getSession().getAttribute(
				"URI");
		String path = request.getContextPath();
		List<FinanceOrder> orderList = financeOrderBiz.listData(ulf, uri);
		for (FinanceOrder ao : orderList) {
			ao.setUri(uri);
			ao.setPath(path);
		}
		return orderList;
	}

	public List<FinanceOrder> setFinanceOrderList(HttpServletRequest request,
			FinanceOrderListForm ulf) throws AppException {
		UserRightInfo uri = (UserRightInfo) request.getSession().getAttribute(
				"URI");
		String path = request.getContextPath();
		List<FinanceOrder> orderList = financeOrderBiz.list(ulf, uri);
		for (FinanceOrder ao : orderList) {
			ao.setUri(uri);
			ao.setPath(path);
		}
		return orderList;
	}

	private FinanceOrderListForm setFinanceOrderListForm(
			HttpServletRequest request, FinanceOrderListForm ulf)
			throws AppException {
		List<FinanceOrder> orderList = setFinanceOrderList(request, ulf);
		ulf.setList(orderList);
		return ulf;
	}

	private FinanceOrderListForm setFinanceOrderGroup(
			HttpServletRequest request, FinanceOrderListForm ulf)
			throws AppException {
		List<FinanceOrder> orderList = setFinanceOrderList(request, ulf);
		List groupList = FinanceGroup.getGroupList(orderList);// 大组
		ulf.setList(groupList);
		return ulf;
	}

	private FinanceOrderListForm setFinanceOrderSubGroup(
			HttpServletRequest request, FinanceOrderListForm ulf)
			throws AppException {
		List<FinanceOrder> orderList = setFinanceOrderList(request, ulf);
		List groupList = FinanceGroup.getGroupList(orderList);// 小组
		ulf.setList(groupList);
		return ulf;
	}

	/***************************************************************************
	 * 关联订单
	 **************************************************************************/
	public ActionForward processing(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		FinanceOrderListForm ulf = (FinanceOrderListForm) form;
		try {
			financeOrderBiz.processing(ulf, request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		forwardPage = "processing";
		return (mapping.findForward(forwardPage));
	}

	/***************************************************************************
	 * 跳转 编辑订单页
	 **************************************************************************/
	public ActionForward editOrder(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		FinanceOrderListForm ulf = (FinanceOrderListForm) form;
		try {
			financeOrderBiz.editOrder(ulf, request);

			request = loadPlatComAccountStoreForRequest(request);
			
			request.setAttribute("companyList", PlatComAccountStore.getAgentCompnayList());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		forwardPage = "editFinanceOrder";
		return (mapping.findForward(forwardPage));
	}

	public ActionForward redirectManagePage(HttpServletRequest request) {
		ActionRedirect redirect = new ActionRedirect(
				FinanceOrder.GeneralManagePath);

		if (request.getSession().getAttribute("orderType") != null) {
			redirect.addParameter("orderType", request.getSession()
					.getAttribute("orderType"));
		}
		if (request.getSession().getAttribute("statusGroup") != null) {
			redirect.addParameter("statusGroup", request.getSession()
					.getAttribute("statusGroup"));
		}
		if (request.getSession().getAttribute("recentlyDay") != null) {
			redirect.addParameter("recentlyDay", request.getSession()
					.getAttribute("recentlyDay"));
		}
		return redirect;
	}

	/**
	 * 加载平台、账户信息到Request
	 */
	public HttpServletRequest loadPlatComAccountStoreForRequest(
			HttpServletRequest request) {

		request.setAttribute("outPlatformList", PlatComAccountStore
				.getOutPlatform());// 采购平台
		request.setAttribute("inPlatformList", PlatComAccountStore
				.getInPlatform());// 销售平台

		request.setAttribute("platformList", PlatComAccountStore.platformList);// 平台

		request.setAttribute("groupCompanyList", PlatComAccountStore
				.getGroupCompnayList());// 集团旗下公司

		request.setAttribute("outAccountList", PlatComAccountStore
				.getOutAccount());// 付款账号
		request.setAttribute("inAccountList", PlatComAccountStore
				.getInAccount());// 收款账号
		return request;
	}

	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		FinanceOrderListForm ulf = (FinanceOrderListForm) form;
		Inform inf = new Inform();
		try {
			forwardPage = financeOrderBiz.view(ulf.getId(), request);
			if ("ERROR".equals(forwardPage)) {
				inf.setMessage("程序异常,请联系技术支持");
				return forwardInformPage(inf, mapping, request);
			} else {
				forwardPage = "view";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward(forwardPage);
	}

	/**
	 * 编辑备注
	 */
	public ActionForward editOrderMemo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		FinanceOrderListForm ulf = (FinanceOrderListForm) form;
		String forwardPage = "";
		try {
			if (ulf.getId() > 0) {
				FinanceOrder order = financeOrderBiz.getFinanceOrderById(ulf
						.getId());
				request.setAttribute("order", order);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		forwardPage = "editOrderMemo";
		return mapping.findForward(forwardPage);
	}

	public ActionForward deleteFinanceOrder(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String forwardPage = "";
		FinanceOrderListForm ulf = (FinanceOrderListForm) form;
		String forwardPageFlag = ulf.getForwardPageFlag();

		Inform inf = new Inform();
		try {
			if (ulf.getId() != null && ulf.getId().longValue() > 0) {
				FinanceOrder ao = financeOrderBiz.getFinanceOrderById(ulf
						.getId());
				financeOrderBiz.deleteFinanceOrder(ao.getId());

				if (forwardPageFlag != null
						&& "".equals(forwardPageFlag) == false) {
					if ("General".equals(forwardPageFlag)) {
						return new ActionRedirect(
								FinanceOrder.GeneralManagePath);
					} else if ("Team".equals(forwardPageFlag)) {
						return null;
					}
				}
			} else {
				inf.setMessage("删除明细失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			inf.setMessage("异常:" + e.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}

	public void saveCustomerSession(HttpServletRequest request,
			UserRightInfo uri, FinanceOrderListForm alf) {
		if (alf.getOperatorName() == null) {
			if (request.getSession().getAttribute("operatorName") == null) {
				alf.setOperatorName(uri.getUser().getUserNo());
				request.getSession().setAttribute("operatorName",
						alf.getOperatorName());
			} else {
				alf.setOperatorName((String) request.getSession().getAttribute(
						"operatorName"));
			}
		} else {
			request.getSession().setAttribute("operatorName",
					alf.getOperatorName());
		}

		if (alf.getOrderBy() == null) {
			if (request.getSession().getAttribute("orderBy") == null) {
				alf.setOrderBy("0");
				request.getSession().setAttribute("orderBy", alf.getOrderBy());
			} else {
				alf.setOrderBy((String) request.getSession().getAttribute(
						"orderBy"));
			}
		} else {
			request.getSession().setAttribute("orderBy", alf.getOrderBy());
		}

		if (alf.getOrderType() == Long.valueOf(0)) {
			if (request.getSession().getAttribute("orderType") == null) {
				alf.setOrderType(Long.valueOf(0));
				request.getSession().setAttribute("orderType",
						alf.getOrderType());
			} else {
				alf.setOrderType((Long) request.getSession().getAttribute(
						"orderType"));
			}
		} else {
			request.getSession().setAttribute("orderType", alf.getOrderType());
		}
		
		if (StringUtil.isEmpty(alf.getTranTypeGroup())) {
			if (request.getSession().getAttribute("tranTypeGroup") == null) {
				alf.setTranTypeGroup("");
				request.getSession().setAttribute("tranTypeGroup",
						alf.getTranTypeGroup());
			} else {
				alf.setTranTypeGroup((String) request.getSession().getAttribute(
						"tranTypeGroup"));
			}
		} else {
			request.getSession().setAttribute("tranTypeGroup", alf.getTranTypeGroup());
		}

		if (alf.getOrderNo() == null) {
			if (request.getSession().getAttribute("orderNo") == null) {
				alf.setOrderNo("");
				request.getSession().setAttribute("orderNo", alf.getOrderNo());
			} else {
				alf.setOrderNo((String) request.getSession().getAttribute(
						"orderNo"));
			}
		} else {
			request.getSession().setAttribute("keyWord", alf.getOrderNo());
		}
		
		if (alf.getKeyWord() == null) {
			if (request.getSession().getAttribute("keyWord") == null) {
				alf.setKeyWord("");
				request.getSession().setAttribute("keyWord", alf.getKeyWord());
			} else {
				alf.setKeyWord((String) request.getSession().getAttribute(
						"keyWord"));
			}
		} else {
			request.getSession().setAttribute("keyWord", alf.getKeyWord());
		}

		if (alf.getStatusGroup() == null) {
			if (request.getSession().getAttribute("statusGroup") == null) {
				alf.setStatusGroup("");
				request.getSession().setAttribute("statusGroup",
						alf.getStatusGroup());
			} else {
				alf.setStatusGroup((String) request.getSession().getAttribute(
						"statusGroup"));
			}
		} else {
			request.getSession().setAttribute("statusGroup",
					alf.getStatusGroup());
		}
		if (request.getSession().getAttribute("recentlyDay") == null) {
			if (alf.getRecentlyDay() == null) {
				alf.setRecentlyDay(new Long(1));
			}
			request.getSession().setAttribute("recentlyDay",
					alf.getRecentlyDay());
		} else {
			if (alf.getRecentlyDay() == null) {
				int x = Constant.toInt((String) request.getSession()
						.getAttribute("recentlyDay").toString());
				alf.setRecentlyDay(new Long(x));
			}
			request.getSession().setAttribute("recentlyDay",
					alf.getRecentlyDay());
		}
	}

	public void setFinanceOrderBiz(FinanceOrderBiz financeOrderBiz) {
		this.financeOrderBiz = financeOrderBiz;
	}

	public void setDataTypeBiz(DataTypeBiz dataTypeBiz) {
		this.dataTypeBiz = dataTypeBiz;
	}

	public void setReportBiz(ReportBiz reportBiz) {
		this.reportBiz = reportBiz;
	}

}
