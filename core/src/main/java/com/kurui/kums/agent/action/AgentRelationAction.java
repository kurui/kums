package com.kurui.kums.agent.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;

import com.kurui.kums.agent.Agent;
import com.kurui.kums.agent.AgentRelation;
import com.kurui.kums.agent.biz.AgentBiz;
import com.kurui.kums.agent.biz.AgentRelationBiz;
import com.kurui.kums.base.BaseAction;
import com.kurui.kums.base.Inform;
import com.kurui.kums.base.database.DBExecuteBatch;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.base.util.DateUtil;
import com.kurui.kums.right.UserRightInfo;

public class AgentRelationAction extends BaseAction {
	private AgentBiz agentBiz;
	private AgentRelationBiz agentRelationBiz;

	public ActionForward updateAgentGroup(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws AppException {
		String forwardPage = "";
		AgentRelation ulf = (AgentRelation) form;

		if (ulf == null) {
			ulf = new AgentRelation();
		}
		UserRightInfo uri = (UserRightInfo) request.getSession().getAttribute(
				"URI");
		Inform inf = new Inform();

		try {
			String sql = "";
			DBExecuteBatch dbt = new DBExecuteBatch();

			sql = "delete from agent_relation where root_agent_id="
					+ ulf.getAgentId();
			dbt.append(sql);
			if (ulf.getCount() > 0) {
				for (int i = 0; i < ulf.getRightRoleID().length; i++) {
					sql = "insert into agent_relation(id,root_agent_id,relate_agent_id,relation_type,update_time,status,user_no) ";
					sql += " values(seq_agentrelation.nextval,";
					sql += ulf.getAgentId() + ",";
					sql += ulf.getRightRoleID()[i] + ",";
					sql += AgentRelation.RELATION_TYPE_1 + ",";
					sql += "sysdate,";
					sql += AgentRelation.STATES_1 + ",";
					sql += "'" + uri.getUser().getUserNo() + "'";
					sql += ")";
					dbt.append(sql);
				}
			}
			dbt.executeBatch();

			if (dbt.isSuccess()) {
				forwardPage = "../agent/agentRelationList.do?thisAction=editAgentGroup";

				if (ulf.getAgentId() > 0) {
					forwardPage += "&agentId=" + ulf.getAgentId();
				}
				return new ActionRedirect(forwardPage);
			} else {
				inf.setMessage("修改失败!" + dbt.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
			inf.setMessage("修改失败!" + e.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}

	public ActionForward insert(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		AgentRelation agentRelationForm = (AgentRelation) form;
		Inform inf = new Inform();
		UserRightInfo uri = (UserRightInfo) request.getSession().getAttribute(
				"URI");
		try {
			long rootAgentId = agentRelationForm.getRootAgentId();
			long relateAgentId = agentRelationForm.getRelateAgentId();
			
			if (rootAgentId > 0&&relateAgentId>0) {
				AgentRelation agentRelation = new AgentRelation();
				Agent rootAgent = agentBiz.getAgentById(rootAgentId);
				agentRelation.setRootAgent(rootAgent);
				
				Agent relateAgent = agentBiz.getAgentById(relateAgentId);
				agentRelation.setRelateAgent(relateAgent);
				
				agentRelation.setRelationType(agentRelationForm.getRelationType());
				agentRelation.setStatus(agentRelationForm.getStatus());
//				agentRelation.setMemo(agentRelationForm.getMemo());
				agentRelation.setUpdateTime(new Timestamp(System
						.currentTimeMillis()));
				agentRelation.setUserNo(uri.getUser().getUserNo());

				long num = agentRelationBiz.save(agentRelation);

				if (num > 0) {
					ActionRedirect redirect = new ActionRedirect(
							"/agent/agentRelationList.do?thisAction=list");
					return redirect;
				} else {
					inf.setMessage("您添加客户数据异常！");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			inf.setMessage("异常:"+e.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}

	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		AgentRelation agentRelationForm = (AgentRelation) form;
		Inform inf = new Inform();
		UserRightInfo uri = (UserRightInfo) request.getSession().getAttribute(
				"URI");
		try {
			if (agentRelationForm.getId() > 0) {				
					AgentRelation agentRelation = agentRelationBiz
							.getAgentRelationById(agentRelationForm.getId());
					
					long rootAgentId = agentRelationForm.getRootAgentId();
					long relateAgentId = agentRelationForm.getRelateAgentId();
					
					if (rootAgentId > 0&&relateAgentId>0) {
					Agent rootAgent = agentBiz.getAgentById(rootAgentId);
					agentRelation.setRootAgent(rootAgent);
					
					Agent relateAgent = agentBiz.getAgentById(relateAgentId);
					agentRelation.setRelateAgent(relateAgent);
					
					agentRelation.setRelationType(agentRelationForm.getRelationType());
					agentRelation.setStatus(agentRelationForm.getStatus());
//					agentRelation.setMemo(agentRelationForm.getMemo());
					agentRelation.setUpdateTime(new Timestamp(System
							.currentTimeMillis()));
					agentRelation.setUserNo(uri.getUser().getUserNo());

					long flag = agentRelationBiz.update(agentRelation);
					
					if (flag > 0) {
						return new ActionRedirect(
								"/agent/agentRelationList.do?thisAction=list");
					} else {
						inf.setMessage("修改客户数据异常!");
					}
				}
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

	public void setAgentRelationBiz(AgentRelationBiz agentRelationBiz) {
		this.agentRelationBiz = agentRelationBiz;
	}

}