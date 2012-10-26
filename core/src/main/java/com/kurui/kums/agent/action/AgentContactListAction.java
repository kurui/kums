package com.kurui.kums.agent.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.kurui.kums.agent.Agent;
import com.kurui.kums.agent.AgentContact;
import com.kurui.kums.agent.AgentContactListForm;
import com.kurui.kums.agent.biz.AgentBiz;
import com.kurui.kums.agent.biz.AgentContactBiz;
import com.kurui.kums.base.BaseAction;
import com.kurui.kums.base.Constant;
import com.kurui.kums.base.Inform;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.transaction.util.PlatComAccountStore;

public class AgentContactListAction extends BaseAction {
	private AgentContactBiz agentContactBiz;
	private AgentBiz agentBiz;

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		AgentContactListForm agentContactListForm = (AgentContactListForm) form;
		if (agentContactListForm == null) {
			agentContactListForm = new AgentContactListForm();
		}
		try {
			List<AgentContact> agentContactList = agentContactBiz
					.list(agentContactListForm);
			agentContactListForm.setList(agentContactList);

			request.setAttribute("agentContactListForm", agentContactListForm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("agentContactListForm", agentContactListForm);
		return mapping.findForward("listAgentContact");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		AgentContactListForm agentContactListForm = (AgentContactListForm) form;
		try {
			long agentContactId = Constant.toLong(agentContactListForm.getId());
			AgentContact agentContact = agentContactBiz
					.getAgentContactById(agentContactId);
			request.setAttribute("agentContact", agentContact);

		} catch (Exception e) {
			e.printStackTrace();
		}
		forwardPage = "viewAgentContact";
		return mapping.findForward(forwardPage);
	}
	
	public ActionForward viewALL(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		AgentContactListForm agentContactListForm = (AgentContactListForm) form;
		try {
			Long agentId = agentContactListForm.getAgentId();
			Agent agent=agentBiz.getAgentById(agentId);
			List<AgentContact> agentContactList	=agentContactBiz.getAgentContactListByAgent(agentId);
			
			request.setAttribute("agent", agent);
			request.setAttribute("agentContactList", agentContactList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		forwardPage = "viewAgentContactALL";
		return mapping.findForward(forwardPage);
	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		AgentContactListForm alf = (AgentContactListForm) form;
		AgentContact agentContact = new AgentContact();
		agentContact.setAgentNo("");
		agentContact.setThisAction("insert");

		request.setAttribute("agentContact", agentContact);
		String forwardPage = "editAgentContact";
		forwardPage = "viewAgentContactALL";
		return mapping.findForward(forwardPage);
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		Inform inf = new Inform();
		AgentContactListForm agentContactListForm = (AgentContactListForm) form;

		long agentContactId = agentContactListForm.getId();
		if (agentContactId < 1) {
			if(agentContactListForm.getSelectedItems()!=null&&agentContactListForm.getSelectedItems().length>0){
				agentContactId = agentContactListForm.getSelectedItems()[0];
			}else{
				inf.setMessage("请选择需要修改的明细");
				return forwardInformPage(inf, mapping, request);
			}
			
		}

		if (agentContactId > 0) {
			AgentContact agentContact = agentContactBiz
					.getAgentContactById(agentContactId);
			agentContact.setThisAction("update");

			request.setAttribute("agentContact", agentContact);
		} else {
			inf.setMessage("缺少agentContactId");
			return forwardInformPage(inf, mapping, request);
		}
		request.setAttribute("companyList", PlatComAccountStore
				.getGroupCompnayList());
		String forwardPage="editAgentContact";
		forwardPage = "viewAgentContactALL";
		return mapping.findForward("editAgentContact");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		AgentContactListForm agentContactListForm = (AgentContactListForm) form;
		String forwardPage = "";
		long id = 0;
		Inform inf = new Inform();
		int message = 0;
		try {
			for (int i = 0; i < agentContactListForm.getSelectedItems().length; i++) {
				id = agentContactListForm.getSelectedItems()[i];
				if (id > 0) {
					agentContactBiz.deleteAgentContact(id);
				}
			}
			return list(mapping, form, request, response);
		} catch (Exception ex) {
			ex.printStackTrace();
			inf.setMessage("删除失败" + ex.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}

	public void setAgentContactBiz(AgentContactBiz agentContactBiz) {
		this.agentContactBiz = agentContactBiz;
	}

	public void setAgentBiz(AgentBiz agentBiz) {
		this.agentBiz = agentBiz;
	}
	
	

}