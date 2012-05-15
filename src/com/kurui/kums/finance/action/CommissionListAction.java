package com.kurui.kums.finance.action;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.kurui.kums.agent.biz.AgentBiz;
import com.kurui.kums.base.BaseAction;
import com.kurui.kums.base.Inform;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.finance.Commission;
import com.kurui.kums.finance.CommissionListForm;
import com.kurui.kums.finance.biz.CommissionBiz;
import com.kurui.kums.finance.biz.FinanceOrderBiz;
import com.kurui.kums.market.SaleEvent;
import com.kurui.kums.market.biz.SaleEventBiz;

public class CommissionListAction extends BaseAction {
	private CommissionBiz commissionBiz;
	private FinanceOrderBiz financeOrderBiz;
	private AgentBiz agentBiz;
	private SaleEventBiz saleEventBiz;

	public ActionForward updateCommissionData(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws AppException {
		CommissionListForm commissionListForm = (CommissionListForm) form;
		if (commissionListForm == null) {
			commissionListForm = new CommissionListForm();
		}
		try {

			commissionBiz.updateCommissionData(commissionListForm);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		CommissionListForm commissionListForm = (CommissionListForm) form;
		if (commissionListForm == null) {
			commissionListForm = new CommissionListForm();
		}
		try {
			List<Commission> commissionList = commissionBiz
					.list(commissionListForm);
			commissionListForm.setList(commissionList);
			commissionListForm.addSumField(1, "totalAmount");
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("commissionListForm", commissionListForm);
		return mapping.findForward("listCommission");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		CommissionListForm commissionListForm = (CommissionListForm) form;
		try {
			Long commissionId = commissionListForm.getId();
			Commission commission = commissionBiz
					.getCommissionById(commissionId);

			request.setAttribute("commission", commission);
		} catch (Exception e) {
			e.printStackTrace();
		}
		forwardPage = "viewCommission";
		return mapping.findForward(forwardPage);

	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		Commission commission = new Commission();
		commission.setThisAction("insert");
		commission.setStatus(Commission.STATES_1);

		request.setAttribute("commission", commission);

		List<SaleEvent> saleEventList = saleEventBiz.getValidSaleEventList();
		request.setAttribute("saleEventList", saleEventList);

		String forwardPage = "editCommission";

		return mapping.findForward(forwardPage);
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		CommissionListForm commissionListForm = (CommissionListForm) form;

		long commissionId = commissionListForm.getId();
		if (commissionId < 1) {
			commissionId = commissionListForm.getSelectedItems()[0];
		}
		
		if (commissionId > 0) {
			Commission commission = commissionBiz
					.getCommissionById(commissionId);
			commission.setThisAction("update");
			request.setAttribute("commission", commission);
		} else {
			request.setAttribute("commission", new Commission());
		}

		List<Commission> commissionList = commissionBiz
				.getValidCommissionList();
		request.setAttribute("commissionList", commissionList);

		return mapping.findForward("editCommission");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		CommissionListForm commissionListForm = (CommissionListForm) form;
		// String forwardPage = "";
		long id = 0;
		Inform inf = new Inform();
		try {
			for (int i = 0; i < commissionListForm.getSelectedItems().length; i++) {
				id = commissionListForm.getSelectedItems()[i];
				if (id > 0) {
					commissionBiz.deleteCommission(id);
				}
			}
			return list(mapping, form, request, response);
		} catch (Exception ex) {
			inf.setMessage("删除失败" + ex.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}

	public void setCommissionBiz(CommissionBiz commissionBiz) {
		this.commissionBiz = commissionBiz;
	}

	public void setFinanceOrderBiz(FinanceOrderBiz financeOrderBiz) {
		this.financeOrderBiz = financeOrderBiz;
	}

	public void setAgentBiz(AgentBiz agentBiz) {
		this.agentBiz = agentBiz;
	}

	public void setSaleEventBiz(SaleEventBiz saleEventBiz) {
		this.saleEventBiz = saleEventBiz;
	}

}