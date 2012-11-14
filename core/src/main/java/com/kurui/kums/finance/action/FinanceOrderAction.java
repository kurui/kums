package com.kurui.kums.finance.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;

import com.kurui.kums.base.struts.BaseAction;
import com.kurui.kums.base.ui.inform.Inform;
import com.kurui.kums.base.Constant;
import com.kurui.kums.finance.biz.FinanceOrderBiz;
import com.kurui.kums.finance.FinanceOrder;

public class FinanceOrderAction extends BaseAction {
	public FinanceOrderBiz financeOrderBiz;

	/**
	 * 管理费用录入
	 */
	public ActionForward insertLiveOrder(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		FinanceOrder financeOrderForm = (FinanceOrder) form;
		String forwardPage = "";
		Inform inf = new Inform();
		try {
			forwardPage = financeOrderBiz.insertLiveOrder(request,
					financeOrderForm);

			ActionRedirect redirect = new ActionRedirect(
					FinanceOrder.LiveManagePath);
			return redirectManagePage(redirect, mapping, request, true,
					forwardPage, "");
		} catch (Exception e) {
			e.printStackTrace();
			inf.setMessage("订单录入异常" + e.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}

	/**
	 * 主营业务录入
	 */
	public ActionForward insertMainOrder(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		FinanceOrder financeOrderForm = (FinanceOrder) form;
		String forwardPage = "";
		Inform inf = new Inform();
		try {
			forwardPage = financeOrderBiz.insertMainOrder(request,
					financeOrderForm);
			ActionRedirect redirect = new ActionRedirect(
					FinanceOrder.MainManagePath);
			return redirectManagePage(redirect, mapping, request, true,
					forwardPage, "");
		} catch (Exception e) {
			e.printStackTrace();
			inf.setMessage("订单录入异常" + e.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}

	/**
	 * 借贷录入
	 */
	public ActionForward insertCreditOrder(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		FinanceOrder financeOrderForm = (FinanceOrder) form;
		String forwardPage = "";
		Inform inf = new Inform();
		try {
			forwardPage = financeOrderBiz.insertCreditOrder(request,
					financeOrderForm);
			ActionRedirect redirect = new ActionRedirect(
					FinanceOrder.CreditManagePath);
			return redirectManagePage(redirect, mapping, request, true,
					forwardPage, "");
		} catch (Exception e) {
			e.printStackTrace();
			inf.setMessage("订单录入异常" + e.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}

	/**
	 * 借贷还款/收款录入
	 */
	public ActionForward insertRepayCreditOrder(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		FinanceOrder financeOrderForm = (FinanceOrder) form;
		String forwardPage = "";
		Inform inf = new Inform();
		try {
			forwardPage = financeOrderBiz.insertRepayCreditOrder(request,
					financeOrderForm);
			if (forwardPage != null) {
				inf = forwardErrorInfo(forwardPage, inf);
				if (inf == null) {
					inf = new Inform();
					inf.setMessage("成功入账");
					inf.setClose(true);
					inf.setForwardPage(FinanceOrder.GeneralManagePath
							+ "&orderType=" + forwardPage);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			inf.setMessage("订单录入异常" + e.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}

	/**
	 * 保存订单修改
	 */
	public ActionForward updateOrder(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FinanceOrder financeOrderForm = (FinanceOrder) form;
		String forwardPage = "";
		Inform inf = new Inform();
		try {
			forwardPage = financeOrderBiz
					.updateOrder(financeOrderForm, request);
			inf = forwardErrorInfo(forwardPage, inf);
			if (inf == null) {
				return redirectViewPage(forwardPage);
			}
		} catch (Exception e) {
			e.printStackTrace();
			inf.setMessage("修改订单异常" + e.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}

	/***************************************************************************
	 * 修改备注
	 **************************************************************************/
	public ActionForward updateOrderMemo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		FinanceOrder orderForm = (FinanceOrder) form;
		String forwardPage = "";
		Inform inf = new Inform();

		try {
			FinanceOrder ao = financeOrderBiz.getFinanceOrderById(orderForm
					.getId());
			ao.setMemo(orderForm.getMemo());
			financeOrderBiz.update(ao);
			forwardPage = "/finance/financeOrderList.do?thisAction=view&id="
					+ orderForm.getId();
			inf.setForwardPage(forwardPage);
			inf.setClose(true);
			inf.setMessage("修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			inf.setMessage("更新异常" + e.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}

	public ActionForward redirectViewPage(String orderId) {
		ActionRedirect redirect = new ActionRedirect(FinanceOrder.ViewPath);
		redirect.addParameter("id", orderId);
		return redirect;
	}

	/**
	 * 重定向到XX订单管理页面
	 * 
	 * @param forwardPage
	 *            :错误码/orderType
	 * @param isEnforce
	 *            :是否强制跳转
	 */
	public ActionForward redirectManagePage(ActionRedirect redirect,
			ActionMapping mapping, HttpServletRequest request,
			boolean isEnforce, String forwardPage, String statusGroup) {
		Inform inf = new Inform();
		if (forwardPage != null) {

			inf = forwardErrorInfo(forwardPage, inf);

			if (inf == null) {
				inf = new Inform();
				if (Constant.toLong(forwardPage) > 0) {
					if (isEnforce) {
						redirect.addParameter("orderType", forwardPage);
						redirect.addParameter("statusGroup", statusGroup);
					} else {
						if (request.getSession().getAttribute("orderType") != null) {
							redirect.addParameter("orderType", request
									.getSession().getAttribute("orderType"));
						} else {
							redirect.addParameter("orderType", forwardPage);
						}
						if (request.getSession().getAttribute("statusGroup") != null) {
							redirect.addParameter("statusGroup", request
									.getSession().getAttribute("statusGroup"));
						}
					}
					return redirect;
				} else {
					inf.setMessage("页面跳转异常");
				}
			} else {
				return forwardInformPage(inf, mapping, request);
			}
		}
		return forwardInformPage(inf, mapping, request);
	}

	/**
	 * 重定向到关联订单处理页面
	 */
	public ActionForward redirectProcessingPage(ActionMapping mapping,
			HttpServletRequest request, String forwardPage) {
		Inform inf = new Inform();
		inf = forwardErrorInfo(forwardPage, inf);

		if (inf == null) {
			inf = new Inform();
			if (Long.parseLong(forwardPage.trim()) > 0) {
				ActionRedirect redirect = new ActionRedirect(
						"/finance/financeOrderList.do?thisAction=processing");
				redirect.addParameter("id", forwardPage);
				return redirect;
			} else {
				inf.setMessage("跳转到关联订单页面异常");
			}
		}
		return forwardInformPage(inf, mapping, request);
	}

	public void setfinanceOrderBiz(FinanceOrderBiz financeOrderBiz) {
		this.financeOrderBiz = financeOrderBiz;
	}
}
