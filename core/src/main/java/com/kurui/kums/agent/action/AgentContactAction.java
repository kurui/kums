package com.kurui.kums.agent.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;

import com.kurui.kums.agent.Agent;
import com.kurui.kums.agent.AgentContact;
import com.kurui.kums.agent.biz.AgentBiz;
import com.kurui.kums.agent.biz.AgentContactBiz;
import com.kurui.kums.base.BaseAction;
import com.kurui.kums.base.Inform;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.right.UserRightInfo;

public class AgentContactAction extends BaseAction {
	private AgentBiz agentBiz;
	private AgentContactBiz agentContactBiz;

	public ActionForward insert(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		AgentContact agentContactForm = (AgentContact) form;
		Inform inf = new Inform();
		UserRightInfo uri = (UserRightInfo) request.getSession().getAttribute(
				"URI");
		try {
			long agentId = agentContactForm.getAgentId();

			if (agentId > 0) {
				Agent agent = agentBiz.getAgentById(agentId);
				
				AgentContact agentContact=new AgentContact();
				agentContact.setAgent(agent);
				
				agentContact.setTag(agentContactForm.getTag());				
				agentContact.setContent(agentContactForm.getContent());
				
				agentContact.setUpdateTime(new Timestamp(System.currentTimeMillis()));

				agentContact.setStatus(agentContactForm.getStatus());
				agentContact.setType(agentContactForm.getType());
				
				agentContactBiz.save(agentContact);

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
		AgentContact agentContactForm = (AgentContact) form;
		Inform inf = new Inform();
		UserRightInfo uri = (UserRightInfo) request.getSession().getAttribute(
				"URI");
		try {
			if (agentContactForm.getId() > 0) {
				AgentContact agentContact = agentContactBiz
						.getAgentContactById(agentContactForm.getId());
				
				agentContact.setTag(agentContactForm.getTag());				
				agentContact.setContent(agentContactForm.getContent());
				
				agentContact.setUpdateTime(new Timestamp(System.currentTimeMillis()));

				agentContact.setStatus(agentContactForm.getStatus());
				agentContact.setType(agentContactForm.getType());
				
				agentContactBiz.save(agentContact);
				
				long flag = agentContactBiz.update(agentContact);
				

				if (flag > 0) {
					return new ActionRedirect(
							"/agent/agentContactList.do?thisAction=list");
				} else {
					inf.setMessage("修改客户数据异常!");
				}
			}else {
				inf.setMessage("缺少agentContactId");
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

	public void setAgentContactBiz(AgentContactBiz agentContactBiz) {
		this.agentContactBiz = agentContactBiz;
	}

}
