package com.kurui.kums.finance.action;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.kurui.kums.base.BaseAction;
import com.kurui.kums.base.Inform;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.base.Constant;
import com.kurui.kums.finance.Budget;
import com.kurui.kums.finance.BudgetOrder;
import com.kurui.kums.finance.BudgetOrderListForm;
import com.kurui.kums.finance.biz.BudgetOrderBiz;
import com.kurui.kums.finance.biz.BudgetBiz;

public class BudgetOrderListAction extends BaseAction {
	private BudgetOrderBiz budgetOrderBiz;
	private BudgetBiz budgetBiz;

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		BudgetOrderListForm budgetOrderListForm = (BudgetOrderListForm) form;
		if (budgetOrderListForm == null) {
			budgetOrderListForm = new BudgetOrderListForm();
		}
		try {
			List<BudgetOrder> budgetOrderList = budgetOrderBiz
					.list(budgetOrderListForm);
			budgetOrderListForm.setList(budgetOrderList);
			budgetOrderListForm.addSumField(1, "totalAmount");
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("budgetOrderListForm", budgetOrderListForm);
		return mapping.findForward("listBudgetOrder");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		BudgetOrderListForm budgetOrderListForm = (BudgetOrderListForm) form;
		try {
			Long budgetOrderId = budgetOrderListForm.getId();
			BudgetOrder budgetOrder = budgetOrderBiz
					.getBudgetOrderById(budgetOrderId);

			request.setAttribute("budgetOrder", budgetOrder);
		} catch (Exception e) {
			e.printStackTrace();
		}
		forwardPage = "viewBudgetOrder";
		return mapping.findForward(forwardPage);

	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		BudgetOrderListForm bolf = (BudgetOrderListForm) form;

		BudgetOrder budgetOrder = new BudgetOrder();
		budgetOrder.setThisAction("insert");
		budgetOrder.setStatus(BudgetOrder.STATES_1);

		if (Constant.toLong(bolf.getBudgetId()) > 0) {
			Budget budget=budgetBiz.getBudgetById(bolf.getBudgetId());
			budgetOrder.setBudget(budget);
		}

		request.setAttribute("budgetOrder", budgetOrder);

		String forwardPage = "editBudgetOrder";

		return mapping.findForward(forwardPage);
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		BudgetOrderListForm budgetOrderListForm = (BudgetOrderListForm) form;

		long budgetOrderId = budgetOrderListForm.getId();
		if (budgetOrderId < 1) {
			budgetOrderId = budgetOrderListForm.getSelectedItems()[0];
		}

		if (budgetOrderId > 0) {
			BudgetOrder budgetOrder = budgetOrderBiz
					.getBudgetOrderById(budgetOrderId);
			budgetOrder.setThisAction("update");
			request.setAttribute("budgetOrder", budgetOrder);
		} else {
			request.setAttribute("budgetOrder", new BudgetOrder());
		}

		List<BudgetOrder> budgetOrderList = budgetOrderBiz
				.getValidBudgetOrderList();
		request.setAttribute("budgetOrderList", budgetOrderList);

		return mapping.findForward("editBudgetOrder");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		BudgetOrderListForm budgetOrderListForm = (BudgetOrderListForm) form;
		// String forwardPage = "";
		long id = 0;
		Inform inf = new Inform();
		try {
			for (int i = 0; i < budgetOrderListForm.getSelectedItems().length; i++) {
				id = budgetOrderListForm.getSelectedItems()[i];
				if (id > 0) {
					budgetOrderBiz.deleteBudgetOrder(id);
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