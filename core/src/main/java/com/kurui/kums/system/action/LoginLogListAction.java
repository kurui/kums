package com.kurui.kums.system.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kurui.kums.base.struts.BaseAction;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.system.LoginLogListForm;
import com.kurui.kums.system.biz.LoginLogBiz;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class LoginLogListAction extends BaseAction {
	private LoginLogBiz loginlogBiz;

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String locate = request.getParameter("locate");
		String forwardPage = "";
		LoginLogListForm lllf = (LoginLogListForm) form;
		if (lllf == null)
			lllf = new LoginLogListForm();
		lllf.setLocate(new Long(locate));
		lllf.setList(loginlogBiz.getLoginLogs(lllf));
		request.setAttribute("lllf", lllf);
		request.setAttribute("locate", locate);
		
		forwardPage = "listclientloginlog";

		return (mapping.findForward(forwardPage));
	}

	public void setLoginlogBiz(LoginLogBiz loginlogBiz) {
		this.loginlogBiz = loginlogBiz;
	}
}