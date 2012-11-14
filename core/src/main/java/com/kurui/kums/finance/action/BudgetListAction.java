package com.kurui.kums.finance.action;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.kurui.kums.base.struts.BaseAction;
import com.kurui.kums.base.ui.inform.Inform;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.finance.Budget;
import com.kurui.kums.finance.BudgetListForm;
import com.kurui.kums.finance.BudgetOrder;
import com.kurui.kums.finance.biz.BudgetOrderBiz;
import com.kurui.kums.finance.biz.BudgetBiz;

public class BudgetListAction extends BaseAction {
	private BudgetOrderBiz budgetOrderBiz;
	private BudgetBiz budgetBiz;

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		BudgetListForm budgetListForm = (BudgetListForm) form;
		if (budgetListForm == null) {
			budgetListForm = new BudgetListForm();
		}
		try {
			List<Budget> budgetList = budgetBiz.list(budgetListForm);
			budgetListForm.setList(budgetList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("budgetListForm", budgetListForm);
		return mapping.findForward("listBudget");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		BudgetListForm budgetListForm = (BudgetListForm) form;
		try {
			Long budgetId = budgetListForm.getId();
			Budget budget = budgetBiz.getBudgetById(budgetId);

			request.setAttribute("budget", budget);

			List<BudgetOrder> budgetOrderList = budgetOrderBiz
					.getBudgetOrderByBudgetId(budgetId);
			request.setAttribute("budgetOrderList", budgetOrderList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		forwardPage = "viewBudget";
		return mapping.findForward(forwardPage);

	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		Budget budget = new Budget();
		budget.setThisAction("insert");
		budget.setStatus(Budget.STATUS_1);

		request.setAttribute("budget", budget);

		String forwardPage = "editBudget";

		return mapping.findForward(forwardPage);
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		BudgetListForm budgetListForm = (BudgetListForm) form;

		long budgetId = budgetListForm.getId();
		if (budgetId < 1) {
			budgetId = budgetListForm.getSelectedItems()[0];
		}

		if (budgetId > 0) {
			Budget budget = budgetBiz.getBudgetById(budgetId);
			budget.setThisAction("update");
			request.setAttribute("budget", budget);
		} else {
			request.setAttribute("budget", new Budget());
		}

		List<BudgetOrder> budgetOrderList = budgetOrderBiz
				.getValidBudgetOrderList();
		request.setAttribute("budgetOrderList", budgetOrderList);

		return mapping.findForward("editBudget");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		BudgetListForm budgetListForm = (BudgetListForm) form;
		// String forwardPage = "";
		long id = 0;
		Inform inf = new Inform();
		try {
			for (int i = 0; i < budgetListForm.getSelectedItems().length; i++) {
				id = budgetListForm.getSelectedItems()[i];
				if (id > 0) {
					budgetBiz.deleteBudget(id);
				}
			}
			return list(mapping, form, request, response);
		} catch (Exception ex) {
			inf.setMessage("删除失败" + ex.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}

	public void setBudgetOrderBiz(BudgetOrderBiz budgetOrderBiz) {
		this.budgetOrderBiz = budgetOrderBiz;
	}

	public void setBudgetBiz(BudgetBiz budgetBiz) {
		this.budgetBiz = budgetBiz;
	}

}