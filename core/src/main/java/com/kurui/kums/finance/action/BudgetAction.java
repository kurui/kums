package com.kurui.kums.finance.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;

import com.kurui.kums.base.struts.BaseAction;
import com.kurui.kums.base.ui.inform.Inform;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.finance.Budget;
import com.kurui.kums.finance.biz.BudgetOrderBiz;
import com.kurui.kums.finance.biz.BudgetBiz;
import com.kurui.kums.right.UserRightInfo;

public class BudgetAction extends BaseAction {
	private BudgetOrderBiz budgetOrderBiz;
	private BudgetBiz budgetBiz;

	public ActionForward insert(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		Budget pform = (Budget) form;
		Inform inf = new Inform();
		UserRightInfo uri = (UserRightInfo) request.getSession().getAttribute(
				"URI");
		try {
			Budget budget = new Budget();
			budget.setName(pform.getName());
			budget.setBeginTime(new Timestamp(System.currentTimeMillis()));
			budget.setCompanyNo(pform.getCompanyNo());
			budget.setEntryOperator(uri.getUser().getUserName());
			// budget.setMemo(pform.getMemo());
			budget.setType(pform.getType());
			budget.setStatus(pform.getStatus());

			long num = budgetBiz.save(budget);
			if (num > 0) {
				return redirectView(budget);
			} else {
				inf.setMessage("您添加预算案失败！");
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
		Budget pform = (Budget) form;
		Inform inf = new Inform();
		UserRightInfo uri = (UserRightInfo) request.getSession().getAttribute(
				"URI");
		try {
			if (pform.getId() > 0) {
				Budget budget = budgetBiz.getBudgetById(pform.getId());
				budget.setName(pform.getName());
				budget.setBeginTime(new Timestamp(System.currentTimeMillis()));
				budget.setCompanyNo(pform.getCompanyNo());
				budget.setEntryOperator(uri.getUser().getUserName());
				// budget.setMemo(pform.getMemo());
				budget.setType(pform.getType());
				budget.setStatus(pform.getStatus());
				long flag = budgetBiz.update(budget);

				if (flag > 0) {
					return redirectView(budget);
				} else {
					inf.setMessage("您修改预算案失败！");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			inf.setMessage("异常:" + e.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}

	public ActionForward redirectView(Budget budget) {
		ActionRedirect redirect = new ActionRedirect("/finance/budgetList.do");
		redirect.addParameter("thisAction", "view");
		redirect.addParameter("id", budget.getId());

		return redirect;
	}

	public void setBudgetOrderBiz(BudgetOrderBiz budgetOrderBiz) {
		this.budgetOrderBiz = budgetOrderBiz;
	}

	public void setBudgetBiz(BudgetBiz budgetBiz) {
		this.budgetBiz = budgetBiz;
	}

}