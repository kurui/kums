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
import com.kurui.kums.report.CreditReport;
import com.kurui.kums.report.CreditReportListForm;
import com.kurui.kums.report.biz.CreditReportBiz;
import com.kurui.kums.report.biz.CreditReportBizImp;

public class CreditReportListAction extends BaseAction {
	private CreditReportBiz creditReportBiz;

	static Logger logger = Logger.getLogger(CreditReportBizImp.class.getName());

	public void createReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		CreditReportListForm crlf = (CreditReportListForm) form;

		if (crlf == null) {
			crlf = new CreditReportListForm();
		}
		try {
			if (Constant.toLong(crlf.getType()) > 0) {
				if (crlf.getType() == CreditReport.TYPE_1) {
					creditReportBiz.createDeditReport(crlf, request);
				} else if (crlf.getType() == CreditReport.TYPE_11) {
					creditReportBiz.createCreditReport(crlf, request);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	}

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		CreditReportListForm arlf = (CreditReportListForm) form;

		if (arlf == null) {
			arlf = new CreditReportListForm();
		}
		try {
			createReport(mapping, form, request, response);

			List<CreditReport> creditReportList = creditReportBiz.list(arlf,
					request);			
			arlf.setList(creditReportList);
			arlf.addSumField(1, "beginAmount");
			arlf.addSumField(2, "addAmount");
			arlf.addSumField(3, "lessenAmount");
			arlf.addSumField(4, "endAmount");
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("arlf", arlf);
		return mapping.findForward("listCreditReport");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		CreditReportListForm alf = (CreditReportListForm) form;
		CreditReport creditReport = new CreditReport();

		creditReport.setThisAction("insert");
		request.setAttribute("creditReport", creditReport);

		String forwardPage = "editCreditReport";
		return mapping.findForward(forwardPage);
	}

	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		CreditReportListForm arlf = (CreditReportListForm) form;
		try {
			long creditReportId = Constant.toLong(arlf.getId());
			CreditReport creditReport = creditReportBiz
					.getCreditReportById(creditReportId);
			request.setAttribute("creditReport", creditReport);

		} catch (Exception e) {
			e.printStackTrace();
		}
		forwardPage = "viewCreditReport";
		return mapping.findForward(forwardPage);
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		Inform inf = new Inform();
		CreditReportListForm arlf = (CreditReportListForm) form;

		long creditReportId = arlf.getId();
		if (creditReportId < 1) {
			creditReportId = arlf.getSelectedItems()[0];
		}

		if (creditReportId > 0) {
			CreditReport creditReport = creditReportBiz
					.getCreditReportById(creditReportId);
			creditReport.setThisAction("update");

			request.setAttribute("creditReport", creditReport);
		} else {
			inf.setMessage("缺少creditReportId");
			return forwardInformPage(inf, mapping, request);
		}
		request.setAttribute("companyList", PlatComAccountStore
				.getGroupCompnayList());
		return mapping.findForward("editCreditReport");
	}

	public void setCreditReportBiz(CreditReportBiz creditReportBiz) {
		this.creditReportBiz = creditReportBiz;
	}

}