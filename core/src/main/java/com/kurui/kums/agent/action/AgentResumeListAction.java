package com.kurui.kums.agent.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;

import com.kurui.kums.agent.Agent;
import com.kurui.kums.agent.AgentResume;
import com.kurui.kums.agent.AgentResumeListForm;
import com.kurui.kums.agent.biz.AgentBiz;
import com.kurui.kums.agent.biz.AgentResumeBiz;
import com.kurui.kums.base.struts.BaseAction;
import com.kurui.kums.base.Constant;
import com.kurui.kums.base.ui.inform.Inform;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.transaction.util.PlatComAccountStore;

public class AgentResumeListAction extends BaseAction {
	private AgentResumeBiz agentResumeBiz;
	private AgentBiz agentBiz;

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		AgentResumeListForm agentResumeListForm = (AgentResumeListForm) form;
		if (agentResumeListForm == null) {
			agentResumeListForm = new AgentResumeListForm();
		}
		try {
			List<AgentResume> agentResumeList = agentResumeBiz
					.list(agentResumeListForm);
			agentResumeListForm.setList(agentResumeList);

			request.setAttribute("agentResumeListForm", agentResumeListForm);
			
			request.setAttribute("companyList", PlatComAccountStore.getAgentCompnayList());

		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("agentResumeListForm", agentResumeListForm);
		return mapping.findForward("listAgentResume");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		AgentResumeListForm agentResumeListForm = (AgentResumeListForm) form;
		try {
			long agentResumeId = Constant.toLong(agentResumeListForm.getId());
			AgentResume agentResume = agentResumeBiz
					.getAgentResumeById(agentResumeId);
			request.setAttribute("agentResume", agentResume);

		} catch (Exception e) {
			e.printStackTrace();
		}
		forwardPage = "viewAgentResume";
		return mapping.findForward(forwardPage);
	}
	
	public ActionForward viewALL(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		AgentResumeListForm agentResumeListForm = (AgentResumeListForm) form;
		try {
			Long agentId = agentResumeListForm.getAgentId();
			Agent agent=agentBiz.getAgentById(agentId);
			List<AgentResume> agentResumeList	=agentResumeBiz.getAgentResumeListByAgent(agentId);
			
			request.setAttribute("agent", agent);
			request.setAttribute("agentResumeList", agentResumeList);
			
			request.setAttribute("companyList", PlatComAccountStore.getAgentCompnayList());

		} catch (Exception e) {
			e.printStackTrace();
		}
		forwardPage = "viewAgentResumeALL";
		return mapping.findForward(forwardPage);
	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		AgentResumeListForm alf = (AgentResumeListForm) form;
		AgentResume agentResume = new AgentResume();
		
		request.setAttribute("companyList", PlatComAccountStore
				.getAgentCompnayList());
		agentResume.setThisAction("insert");

		request.setAttribute("agentResume", agentResume);
		String forwardPage = "editAgentResume";
		return mapping.findForward(forwardPage);
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		Inform inf = new Inform();
		AgentResumeListForm agentResumeListForm = (AgentResumeListForm) form;

		long agentResumeId = agentResumeListForm.getId();
		if (agentResumeId < 1) {
			agentResumeId = agentResumeListForm.getSelectedItems()[0];
		}

		if (agentResumeId > 0) {
			AgentResume agentResume = agentResumeBiz
					.getAgentResumeById(agentResumeId);
			agentResume.setThisAction("update");

			request.setAttribute("agentResume", agentResume);
			
			request.setAttribute("companyList", PlatComAccountStore
					.getAgentCompnayList());
		} else {
			inf.setMessage("缺少agentResumeId");
			return forwardInformPage(inf, mapping, request);
		}
		return mapping.findForward("editAgentResume");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		AgentResumeListForm agentResumeListForm = (AgentResumeListForm) form;
		String forwardPage = "";
		long id = 0;
		Inform inf = new Inform();
		int message = 0;
		try {
			for (int i = 0; i < agentResumeListForm.getSelectedItems().length; i++) {
				id = agentResumeListForm.getSelectedItems()[i];
				if (id > 0) {
					agentResumeBiz.deleteAgentResume(id);
					
					AgentResume agentResume=agentResumeBiz.getAgentResumeById(id);
					
					
					if (agentResume!=null) {
						return new ActionRedirect(
								"/agent/agentResumeList.do?thisAction=viewALL&agentId="
										+ agentResume.getAgent().getId());
					} 
				}
			}
			return list(mapping, form, request, response);
		} catch (Exception ex) {
			ex.printStackTrace();
			inf.setMessage("删除失败" + ex.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}

	public void setAgentResumeBiz(AgentResumeBiz agentResumeBiz) {
		this.agentResumeBiz = agentResumeBiz;
	}

	public void setAgentBiz(AgentBiz agentBiz) {
		this.agentBiz = agentBiz;
	}

	
}