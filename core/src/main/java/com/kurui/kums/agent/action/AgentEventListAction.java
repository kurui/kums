package com.kurui.kums.agent.action;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.kurui.kums.agent.AgentEvent;
import com.kurui.kums.agent.AgentEventListForm;
import com.kurui.kums.agent.biz.AgentEventBiz;
import com.kurui.kums.base.struts.BaseAction;
import com.kurui.kums.base.Constant;
import com.kurui.kums.base.ui.inform.Inform;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.library.util.PlatComAccountStore;

public class AgentEventListAction extends BaseAction {
	private AgentEventBiz agentEventBiz;

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		AgentEventListForm agentEventListForm = (AgentEventListForm) form;
		if (agentEventListForm == null) {
			agentEventListForm = new AgentEventListForm();
		}
		try {
			List<AgentEvent> agentEventList = agentEventBiz
					.list(agentEventListForm);
			agentEventListForm.setList(agentEventList);

			request.setAttribute("agentEventListForm", agentEventListForm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("agentEventListForm", agentEventListForm);
		return mapping.findForward("listAgentEvent");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		AgentEventListForm agentEventListForm = (AgentEventListForm) form;
		try {
			long agentEventId = Constant.toLong(agentEventListForm.getId());
			AgentEvent agentEvent = agentEventBiz
					.getAgentEventById(agentEventId);
			request.setAttribute("agentEvent", agentEvent);

		} catch (Exception e) {
			e.printStackTrace();
		}
		forwardPage = "viewAgentEvent";
		return mapping.findForward(forwardPage);
	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		AgentEventListForm alf = (AgentEventListForm) form;
		AgentEvent agentEvent = new AgentEvent();
		agentEvent.setStatus(AgentEvent.STATES_1);
		agentEvent.setType(AgentEvent.TYPE_1);
		agentEvent.setAgentNo("");
		agentEvent.setUpdateTime(new Timestamp(System.currentTimeMillis()));

		agentEvent.setThisAction("insert");

		request.setAttribute("agentEvent", agentEvent);
		String forwardPage = "editAgentEvent";
		return mapping.findForward(forwardPage);
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		Inform inf = new Inform();
		AgentEventListForm agentEventListForm = (AgentEventListForm) form;

		long agentEventId = agentEventListForm.getId();
		if (agentEventId < 1) {
			agentEventId = agentEventListForm.getSelectedItems()[0];
		}

		if (agentEventId > 0) {
			AgentEvent agentEvent = agentEventBiz
					.getAgentEventById(agentEventId);
			agentEvent.setThisAction("update");

			request.setAttribute("agentEvent", agentEvent);
		} else {
			inf.setMessage("缺少agentEventId");
			return forwardInformPage(inf, mapping, request);
		}
		request.setAttribute("companyList", PlatComAccountStore
				.getGroupCompnayList());
		return mapping.findForward("editAgentEvent");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		AgentEventListForm agentEventListForm = (AgentEventListForm) form;
		String forwardPage = "";
		long id = 0;
		Inform inf = new Inform();
		int message = 0;
		try {
			for (int i = 0; i < agentEventListForm.getSelectedItems().length; i++) {
				id = agentEventListForm.getSelectedItems()[i];
				if (id > 0) {
					agentEventBiz.deleteAgentEvent(id);
				}
			}
			return list(mapping, form, request, response);
		} catch (Exception ex) {
			ex.printStackTrace();
			inf.setMessage("删除失败" + ex.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}

	public void setAgentEventBiz(AgentEventBiz agentEventBiz) {
		this.agentEventBiz = agentEventBiz;
	}

}