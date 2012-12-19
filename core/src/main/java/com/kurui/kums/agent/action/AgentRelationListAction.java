package com.kurui.kums.agent.action;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.kurui.kums.agent.Agent;
import com.kurui.kums.agent.AgentRelation;
import com.kurui.kums.agent.AgentRelationListForm;
import com.kurui.kums.agent.biz.AgentBiz;
import com.kurui.kums.agent.biz.AgentRelationBiz;
import com.kurui.kums.base.Constant;
import com.kurui.kums.base.database.jdbc.app.example.ListMenu;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.base.struts.BaseAction;
import com.kurui.kums.base.ui.inform.Inform;
import com.kurui.kums.library.util.PlatComAccountStore;

public class AgentRelationListAction extends BaseAction {
	private AgentRelationBiz agentRelationBiz;
	private AgentBiz agentBiz;

	public ActionForward listGroupChart(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		AgentRelationListForm arlf = (AgentRelationListForm) form;

		if (arlf == null) {
			arlf = new AgentRelationListForm();
		}
		try {
			request = agentRelationBiz.listAgentGroup(arlf,
					request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("arlf", arlf);
		return mapping.findForward("listAgentGroup");
	}

	public ActionForward editAgentGroup(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		AgentRelationListForm ulf = (AgentRelationListForm) form;

		if (ulf == null) {
			ulf = new AgentRelationListForm();
		}
		try {
			String sql = "";

			sql = "select id,agent_no||'--'||name from agent where status=1 order by name";
			ListMenu agentlist = new ListMenu(sql, false);
			request.setAttribute("agentlist", agentlist);

			if (Constant.toLong(ulf.getAgentId()) < 1) {
				ulf.setAgentId(Constant.toLong(agentlist.getValue()[0]));
				// System.out.println("agentId:" + ulf.getAgentId());
			}
			String sql1 = "";
			String sql2 = "";
			sql1 = "select a.id,agent_no||'--'||name  as agent_name from agent a where status=1 "
					+ " and a.id not in (" + ulf.getAgentId() + " ) ";

			sql2 = "select a.id,agent_no||'--'||name  as agent_name from agent a where status=1 "
					+ " and  a.id in (select r.relate_agent_id from agent_relation r where r.root_agent_id="
					+ ulf.getAgentId() + " ) ";

			// System.out.println("sql2:" + sql2);

			ListMenu userlist1 = new ListMenu(sql1, false);
			ListMenu userlist2 = new ListMenu(sql2, false);
			request.setAttribute("agentlist1", userlist1);
			request.setAttribute("agentlist2", userlist2);
			request.setAttribute("ulf", ulf);

		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("ulf", ulf);
		return mapping.findForward("editAgentGroup");
	}

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		AgentRelationListForm agentRelationListForm = (AgentRelationListForm) form;

		if (agentRelationListForm == null) {
			agentRelationListForm = new AgentRelationListForm();
		}
		try {
			List<AgentRelation> agentRelationList = agentRelationBiz
					.list(agentRelationListForm);
			agentRelationListForm.setList(agentRelationList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("agentRelationListForm", agentRelationListForm);
		return mapping.findForward("listAgentRelation");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		AgentRelationListForm alf = (AgentRelationListForm) form;
		AgentRelation agentRelation = new AgentRelation();
		
		long rootAgentId=alf.getRootAgentId();
		if(rootAgentId>0){
			Agent rootAgent=agentBiz.getAgentById(rootAgentId);
			if(rootAgent!=null){
				agentRelation.setRootAgent(rootAgent);
			}
		}
		agentRelation.setUpdateTime(new Timestamp(System.currentTimeMillis()));

		agentRelation.setThisAction("insert");
		request.setAttribute("agentRelation", agentRelation);

		String forwardPage = "editAgentRelation";
		return mapping.findForward(forwardPage);
	}

	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		AgentRelationListForm arlf = (AgentRelationListForm) form;
		try {
			long agentRelationId = Constant.toLong(arlf
					.getId());
			AgentRelation agentRelation = agentRelationBiz
					.getAgentRelationById(agentRelationId);
			request.setAttribute("agentRelation", agentRelation);

		} catch (Exception e) {
			e.printStackTrace();
		}
		forwardPage = "viewAgentRelation";
		return mapping.findForward(forwardPage);
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		Inform inf = new Inform();
		AgentRelationListForm arlf = (AgentRelationListForm) form;

		long agentRelationId = arlf.getId();
		if (agentRelationId < 1) {
			agentRelationId = arlf.getSelectedItems()[0];
		}

		if (agentRelationId > 0) {
			AgentRelation agentRelation = agentRelationBiz
					.getAgentRelationById(agentRelationId);
			agentRelation.setThisAction("update");

			request.setAttribute("agentRelation", agentRelation);
		} else {
			inf.setMessage("缺少agentRelationId");
			return forwardInformPage(inf, mapping, request);
		}
		request.setAttribute("companyList", PlatComAccountStore
				.getGroupCompnayList());
		return mapping.findForward("editAgentRelation");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		AgentRelationListForm arlf = (AgentRelationListForm) form;
		String forwardPage = "";
		long id = 0;
		Inform inf = new Inform();
		int message = 0;
		try {
			for (int i = 0; i < arlf.getSelectedItems().length; i++) {
				id = arlf.getSelectedItems()[i];
				if (id > 0) {
					agentRelationBiz.deleteAgentRelation(id);
				}
			}
			return list(mapping, form, request, response);
		} catch (Exception ex) {
			ex.printStackTrace();
			inf.setMessage("删除失败" + ex.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}

	public void setAgentRelationBiz(AgentRelationBiz agentRelationBiz) {
		this.agentRelationBiz = agentRelationBiz;
	}

	public void setAgentBiz(AgentBiz agentBiz) {
		this.agentBiz = agentBiz;
	}
}