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
import com.kurui.kums.agent.Agent;
import com.kurui.kums.agent.AgentHabit;
import com.kurui.kums.agent.AgentHabitListForm;
import com.kurui.kums.transaction.util.PlatComAccountStore;
import com.kurui.kums.agent.biz.AgentBiz;
import com.kurui.kums.agent.biz.AgentHabitBiz;

public class AgentHabitListAction extends BaseAction {
	private AgentBiz agentBiz;
	private AgentHabitBiz agentHabitBiz;

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		AgentHabitListForm agentHabitListForm = (AgentHabitListForm) form;
		if (agentHabitListForm == null) {
			agentHabitListForm = new AgentHabitListForm();
		}
		try {
			List<AgentHabit> agentHabitList = agentHabitBiz
					.list(agentHabitListForm);
			agentHabitListForm.setList(agentHabitList);

			request.setAttribute("agentHabitListForm", agentHabitListForm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("agentHabitListForm", agentHabitListForm);
		return mapping.findForward("listAgentHabit");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		AgentHabitListForm agentHabitListForm = (AgentHabitListForm) form;
		try {
			long agentHabitId = Constant.toLong(agentHabitListForm.getId());
			AgentHabit agentHabit = agentHabitBiz
					.getAgentHabitById(agentHabitId);
			request.setAttribute("agentHabit", agentHabit);

		} catch (Exception e) {
			e.printStackTrace();
		}
		forwardPage = "viewAgentHabit";
		return mapping.findForward(forwardPage);
	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		AgentHabitListForm alf = (AgentHabitListForm) form;
		Inform inf = new Inform();
		long agentId = Constant.toLong(alf.getAgentId());
		if (agentId > 0) {
			Agent agent = agentBiz.getAgentById(agentId);
			if (agent != null) {
				AgentHabit agentHabit = agent.getAgentHabit();

				if (agentHabit != null) {
					agentHabit.setThisAction("update");
				} else {
					agentHabit = new AgentHabit();
					agentHabit.setThisAction("insert");
					agentHabit.setAgent(agent);
				}
				request.setAttribute("agentHabit", agentHabit);
			} else {
				inf.setMessage("Agent为空");
				return forwardInformPage(inf, mapping, request);
			}
		} else {
			inf.setMessage("缺少AgentId");
			return forwardInformPage(inf, mapping, request);
		}

		String forwardPage = "editAgentHabit";
		return mapping.findForward(forwardPage);
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		Inform inf = new Inform();
		AgentHabitListForm agentHabitListForm = (AgentHabitListForm) form;

		long agentHabitId = agentHabitListForm.getId();
		if (agentHabitId < 1) {
			agentHabitId = agentHabitListForm.getSelectedItems()[0];
		}

		if (agentHabitId > 0) {
			AgentHabit agentHabit = agentHabitBiz
					.getAgentHabitById(agentHabitId);
			agentHabit.setThisAction("update");

			request.setAttribute("agentHabit", agentHabit);
		} else {
			inf.setMessage("缺少agentHabitId");
			return forwardInformPage(inf, mapping, request);
		}
		request.setAttribute("companyList", PlatComAccountStore
				.getGroupCompnayList());
		return mapping.findForward("editAgentHabit");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		AgentHabitListForm agentHabitListForm = (AgentHabitListForm) form;
		String forwardPage = "";
		long id = 0;
		Inform inf = new Inform();
		int message = 0;
		try {
			for (int i = 0; i < agentHabitListForm.getSelectedItems().length; i++) {
				id = agentHabitListForm.getSelectedItems()[i];
				if (id > 0) {
					agentHabitBiz.deleteAgentHabit(id);
				}
			}
			return list(mapping, form, request, response);
		} catch (Exception ex) {
			ex.printStackTrace();
			inf.setMessage("删除失败" + ex.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}

	public void setAgentBiz(AgentBiz agentBiz) {
		this.agentBiz = agentBiz;
	}

	public void setAgentHabitBiz(AgentHabitBiz agentHabitBiz) {
		this.agentHabitBiz = agentHabitBiz;
	}

}