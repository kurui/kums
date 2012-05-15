package com.kurui.kums.agent.action;

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
import com.kurui.kums.agent.AgentAction;
import com.kurui.kums.agent.AgentActionListForm;
import com.kurui.kums.transaction.util.PlatComAccountStore;
import com.kurui.kums.agent.biz.AgentActionBiz;
import com.kurui.kums.agent.biz.AgentBiz;

public class AgentActionListAction extends BaseAction {
	private AgentActionBiz agentActionBiz;

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		AgentActionListForm agentActionListForm = (AgentActionListForm) form;
		if (agentActionListForm == null) {
			agentActionListForm = new AgentActionListForm();
		}
		try {
			List<AgentAction> agentActionList = agentActionBiz
					.list(agentActionListForm);
			agentActionListForm.setList(agentActionList);

			request.setAttribute("agentActionListForm", agentActionListForm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("agentActionListForm", agentActionListForm);
		return mapping.findForward("listAgentAction");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		AgentActionListForm agentActionListForm = (AgentActionListForm) form;
		try {
			long agentActionId = Constant.toLong(agentActionListForm.getId());
			AgentAction agentAction = agentActionBiz
					.getAgentActionById(agentActionId);
			request.setAttribute("agentAction", agentAction);

		} catch (Exception e) {
			e.printStackTrace();
		}
		forwardPage = "viewAgentAction";
		return mapping.findForward(forwardPage);
	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		AgentActionListForm alf = (AgentActionListForm) form;
		AgentAction agentAction = new AgentAction();
		request.setAttribute("companyList", PlatComAccountStore
				.getGroupCompnayList());
		agentAction.setThisAction("insert");

		request.setAttribute("agentAction", agentAction);
		String forwardPage = "editAgentAction";
		return mapping.findForward(forwardPage);
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		Inform inf = new Inform();
		AgentActionListForm agentActionListForm = (AgentActionListForm) form;

		long agentActionId = agentActionListForm.getId();
		if (agentActionId < 1) {
			agentActionId = agentActionListForm.getSelectedItems()[0];
		}

		if (agentActionId > 0) {
			AgentAction agentAction = agentActionBiz
					.getAgentActionById(agentActionId);
			agentAction.setThisAction("update");

			request.setAttribute("agentAction", agentAction);
		} else {
			inf.setMessage("缺少agentActionId");
			return forwardInformPage(inf, mapping, request);
		}
		request.setAttribute("companyList", PlatComAccountStore
				.getGroupCompnayList());
		return mapping.findForward("editAgentAction");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		AgentActionListForm agentActionListForm = (AgentActionListForm) form;
		String forwardPage = "";
		long id = 0;
		Inform inf = new Inform();
		int message = 0;
		try {
			for (int i = 0; i < agentActionListForm.getSelectedItems().length; i++) {
				id = agentActionListForm.getSelectedItems()[i];
				if (id > 0) {
					agentActionBiz.deleteAgentAction(id);
				}
			}
			return list(mapping, form, request, response);
		} catch (Exception ex) {
			ex.printStackTrace();
			inf.setMessage("删除失败" + ex.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}

	public void setAgentActionBiz(AgentActionBiz agentActionBiz) {
		this.agentActionBiz = agentActionBiz;
	}

}