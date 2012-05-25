package com.kurui.kums.report.action;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.kurui.kums.base.BaseAction;
import com.kurui.kums.base.Inform;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.base.Constant;
import com.kurui.kums.transaction.util.PlatComAccountStore;
import com.kurui.kums.report.CashFlow;
import com.kurui.kums.report.CashFlowListForm;
import com.kurui.kums.report.biz.CashFlowBiz;
import com.kurui.kums.report.biz.CashFlowBizImp;

public class CashFlowListAction extends BaseAction {
	private CashFlowBiz cashFlowBiz;

	static Logger logger = Logger.getLogger(CashFlowBizImp.class.getName());

	public void createReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		CashFlowListForm crlf = (CashFlowListForm) form;

		if (crlf == null) {
			crlf = new CashFlowListForm();
		}
		try {
			crlf.setPerPageNum(100);//每页显示100条
			cashFlowBiz.createCashFlow(crlf, request);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	}

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		CashFlowListForm arlf = (CashFlowListForm) form;

		if (arlf == null) {
			arlf = new CashFlowListForm();
		}
		try {
			createReport(mapping, form, request, response);

			List<CashFlow> cashFlowList = cashFlowBiz.list(arlf, request);
			arlf.setList(cashFlowList);
			arlf.addSumField(1, "inAmount");
			arlf.addSumField(2, "outAmount");
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("arlf", arlf);
		return mapping.findForward("listCashFlow");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		CashFlowListForm alf = (CashFlowListForm) form;
		CashFlow cashFlow = new CashFlow();

		cashFlow.setThisAction("insert");
		request.setAttribute("cashFlow", cashFlow);

		String forwardPage = "editCashFlow";
		return mapping.findForward(forwardPage);
	}

	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		CashFlowListForm arlf = (CashFlowListForm) form;
		try {
			long cashFlowId = Constant.toLong(arlf.getId());
			CashFlow cashFlow = cashFlowBiz.getCashFlowById(cashFlowId);
			request.setAttribute("cashFlow", cashFlow);

		} catch (Exception e) {
			e.printStackTrace();
		}
		forwardPage = "viewCashFlow";
		return mapping.findForward(forwardPage);
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		Inform inf = new Inform();
		CashFlowListForm arlf = (CashFlowListForm) form;

		long cashFlowId = arlf.getId();
		if (cashFlowId < 1) {
			cashFlowId = arlf.getSelectedItems()[0];
		}

		if (cashFlowId > 0) {
			CashFlow cashFlow = cashFlowBiz.getCashFlowById(cashFlowId);
			cashFlow.setThisAction("update");

			request.setAttribute("cashFlow", cashFlow);
		} else {
			inf.setMessage("缺少cashFlowId");
			return forwardInformPage(inf, mapping, request);
		}
		request.setAttribute("companyList", PlatComAccountStore
				.getGroupCompnayList());
		return mapping.findForward("editCashFlow");
	}

	public void setCashFlowBiz(CashFlowBiz cashFlowBiz) {
		this.cashFlowBiz = cashFlowBiz;
	}

}