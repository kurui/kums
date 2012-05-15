package com.kurui.kums.system.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.kurui.kums.base.BaseAction;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.system.OperateLogListForm;
import com.kurui.kums.system.biz.OperateLogBiz;

public class OperateLogListAction extends BaseAction {
	private OperateLogBiz operateLogBiz;

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		OperateLogListForm tllf = (OperateLogListForm) form;
		if (tllf == null)
			tllf = new OperateLogListForm();
		tllf.setList(operateLogBiz.getOperateLogs(tllf));
		request.setAttribute("tllf", tllf);
		forwardPage = "listOperateLog";
		return (mapping.findForward(forwardPage));
	}

	public void setOperateLogBiz(OperateLogBiz operateLogBiz) {
		this.operateLogBiz = operateLogBiz;
	}

}