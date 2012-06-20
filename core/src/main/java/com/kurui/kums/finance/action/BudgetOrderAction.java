package com.kurui.kums.finance.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;

import com.kurui.kums.base.BaseAction;
import com.kurui.kums.base.Inform;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.finance.Budget;
import com.kurui.kums.finance.BudgetOrder;
import com.kurui.kums.finance.biz.BudgetOrderBiz;
import com.kurui.kums.finance.biz.BudgetBiz;

public class BudgetOrderAction extends BaseAction {
	private BudgetOrderBiz budgetOrderBiz;
	private BudgetBiz budgetBiz;

	public ActionForward insert(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		BudgetOrder pform = (BudgetOrder) form;
		Inform inf = new Inform();
		try {
			BudgetOrder budgetOrder = new BudgetOrder();
			budgetOrder.setName(pform.getName());
			budgetOrder.setBudgetAmount(pform.getBudgetAmount());
			budgetOrder.setBudgeTime(new Timestamp(System.currentTimeMillis()));

			Budget budget = budgetBiz.getBudgetById(pform.getBudgetId());
			budgetOrder.setBudget(budget);
			budgetOrder.setMemo(pform.getMemo());
			budgetOrder.setStatus(pform.getStatus());

			long num = budgetOrderBiz.save(budgetOrder);
			if (num > 0) {
				return redirectViewBudget(budgetOrder);
			} else {
				inf.setMessage("您添加预算明细失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			inf.setMessage("异常:" + e.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}

	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		BudgetOrder pform = (BudgetOrder) form;
		Inform inf = new Inform();
//		UserRightInfo uri = (UserRightInfo) request.getSession().getAttribute(
//				"URI");
		try {
			if (pform.getId() > 0) {
				BudgetOrder budgetOrder = budgetOrderBiz
						.getBudgetOrderById(pform.getId());
				budgetOrder.setName(pform.getName());
				budgetOrder.setBudgetAmount(pform.getBudgetAmount());
				budgetOrder.setBudgeTime(new Timestamp(System
						.currentTimeMillis()));

				// Budget budget = budgetBiz.getBudgetById(pform.getBudgetId());
				// budgetOrder.setBudget(budget);
				budgetOrder.setMemo(pform.getMemo());
				budgetOrder.setStatus(pform.getStatus());
				long flag = budgetOrderBiz.update(budgetOrder);

				if (flag > 0) {
					return redirectViewBudget(budgetOrder);
				} else {
					inf.setMessage("您修改预算明细失败！");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			inf.setMessage("异常:" + e.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}
	//决算
	public ActionForward settlement(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		BudgetOrder pform = (BudgetOrder) form;
		Inform inf = new Inform();
//		UserRightInfo uri = (UserRightInfo) request.getSession().getAttribute(
//				"URI");
		try {
			if (pform.getId() > 0) {
				BudgetOrder budgetOrder = budgetOrderBiz
						.getBudgetOrderById(pform.getId());
				budgetOrder.setStatementAmount(pform.getBudgetAmount());
				budgetOrder.setStatementTime(new Timestamp(System
						.currentTimeMillis()));

				budgetOrder.setMemo(pform.getMemo());
				budgetOrder.setStatus(BudgetOrder.STATES_21);//决算
				long flag = budgetOrderBiz.update(budgetOrder);

				if (flag > 0) {
					return redirectViewBudget(budgetOrder);
				} else {
					inf.setMessage("您修改预算明细失败！");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			inf.setMessage("异常:" + e.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}
	

	public ActionForward redirectList(BudgetOrder budgetOrder) {
		ActionRedirect redirect = new ActionRedirect(
				"/finance/budgetOrderList.do");
		redirect.addParameter("thisAction", "list");
		return redirect;
	}

	public ActionForward redirectView(BudgetOrder budgetOrder) {
		ActionRedirect redirect = new ActionRedirect(
				"/finance/budgetOrderList.do");
		redirect.addParameter("thisAction", "view");
		redirect.addParameter("id", budgetOrder.getId());
		return redirect;
	}

	public ActionForward redirectViewBudget(BudgetOrder budgetOrder) {
		ActionRedirect redirect = new ActionRedirect("/finance/budgetList.do");
		redirect.addParameter("thisAction", "view");
		if (budgetOrder != null && budgetOrder.getBudget() != null) {
			redirect.addParameter("id", budgetOrder.getBudget().getId());
		} else {
			return redirectView(budgetOrder);
		}

		return redirect;
	}

	public void setBudgetOrderBiz(BudgetOrderBiz budgetOrderBiz) {
		this.budgetOrderBiz = budgetOrderBiz;
	}

	public void setBudgetBiz(BudgetBiz budgetBiz) {
		this.budgetBiz = budgetBiz;
	}

}