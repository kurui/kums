package com.kurui.kums.report.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;

import com.kurui.kums.base.BaseAction;
import com.kurui.kums.base.Inform;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.right.UserRightInfo;
import com.kurui.kums.report.CreditReport;
import com.kurui.kums.report.biz.CreditReportBiz;

public class CreditReportAction extends BaseAction {
	private CreditReportBiz creditReportBiz;

	public ActionForward insert(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		CreditReport creditReportForm = (CreditReport) form;
		Inform inf = new Inform();
		UserRightInfo uri = (UserRightInfo) request.getSession().getAttribute(
				"URI");
		try {
			long agentId = creditReportForm.getAgentId();
			if (agentId > 0) {
				CreditReport creditReport = new CreditReport();

				// creditReport.setType(creditReportForm.getType());
				creditReport.setStatus(creditReportForm.getStatus());

				long num = creditReportBiz.save(creditReport);

				if (num > 0) {
					ActionRedirect redirect = new ActionRedirect(
							"/report/creditReportList.do?thisAction=list");
					return redirect;
				} else {
					inf.setMessage("您添加客户数据异常！");
				}
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
		CreditReport creditReportForm = (CreditReport) form;
		Inform inf = new Inform();
		UserRightInfo uri = (UserRightInfo) request.getSession().getAttribute(
				"URI");
		try {
			if (creditReportForm.getId() > 0) {
				long habitId = creditReportForm.getId();
				if (habitId > 0) {
					CreditReport creditReport = creditReportBiz
							.getCreditReportById(habitId);
					// creditReport.setName(creditReportForm.getName());

					long flag = creditReportBiz.update(creditReport);

					if (flag > 0) {
						return new ActionRedirect(
								"/report/creditReportList.do?thisAction=list");
					} else {
						inf.setMessage("修改借贷报表异常!");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			inf.setMessage("异常:" + e.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}

	public void setCreditReportBiz(CreditReportBiz creditReportBiz) {
		this.creditReportBiz = creditReportBiz;
	}

}