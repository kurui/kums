package com.kurui.kums.agent.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;
import com.kurui.kums.agent.Agent;
import com.kurui.kums.agent.AgentAccount;
import com.kurui.kums.agent.AgentAction;
import com.kurui.kums.agent.biz.AgentActionBiz;
import com.kurui.kums.agent.biz.AgentBiz;
import com.kurui.kums.base.BaseAction;
import com.kurui.kums.base.Inform;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.right.UserRightInfo;

public class AgentActionAction extends BaseAction {
	private AgentBiz agentBiz;
	private AgentActionBiz agentActionBiz;

	public ActionForward insert(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		AgentAccount agentAccountForm = (AgentAccount) form;
		Inform inf = new Inform();
		UserRightInfo uri = (UserRightInfo) request.getSession().getAttribute(
				"URI");
		try {
			long agentId = agentAccountForm.getAgentId();

			if (agentId > 0) {
				Agent agent = agentBiz.getAgentById(agentId);

			} else {
				inf.setMessage("客户ID不能为空");
			}
		} catch (Exception e) {
			e.printStackTrace();
			inf.setMessage("增加客户账号：" + e.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}

	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		AgentAction agentActionForm = (AgentAction) form;
		Inform inf = new Inform();
		UserRightInfo uri = (UserRightInfo) request.getSession().getAttribute(
				"URI");
		try {
			if (agentActionForm.getId() > 0) {
				AgentAction agentAction = agentActionBiz
						.getAgentActionById(agentActionForm.getId());

				long flag = agentActionBiz.update(agentAction);

				if (flag > 0) {
					return new ActionRedirect(
							"/agent/agentActionList.do?thisAction=list");
				} else {
					inf.setMessage("修改客户数据异常!");
				}
			}else {
				inf.setMessage("缺少agentActionId");
			}
		} catch (Exception e) {
			e.printStackTrace();
			inf.setMessage("异常:"+e.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}

	public void setAgentBiz(AgentBiz agentBiz) {
		this.agentBiz = agentBiz;
	}

	public void setAgentActionBiz(AgentActionBiz agentActionBiz) {
		this.agentActionBiz = agentActionBiz;
	}

}
