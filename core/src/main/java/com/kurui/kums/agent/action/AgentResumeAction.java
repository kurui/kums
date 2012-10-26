package com.kurui.kums.agent.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;

import com.kurui.kums.agent.Agent;
import com.kurui.kums.agent.AgentResume;
import com.kurui.kums.agent.biz.AgentBiz;
import com.kurui.kums.agent.biz.AgentResumeBiz;
import com.kurui.kums.base.BaseAction;
import com.kurui.kums.base.Inform;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.base.util.DateUtil;
import com.kurui.kums.right.UserRightInfo;
import com.kurui.kums.transaction.Company;
import com.kurui.kums.transaction.util.PlatComAccountStore;

public class AgentResumeAction extends BaseAction {
	private AgentBiz agentBiz;
	private AgentResumeBiz agentResumeBiz;

	public ActionForward insert(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		AgentResume agentResumeForm = (AgentResume) form;
		Inform inf = new Inform();
		UserRightInfo uri = (UserRightInfo) request.getSession().getAttribute(
				"URI");
		try {
			long agentId = agentResumeForm.getAgentId();
			long companyId=agentResumeForm.getCompanyId();

			if (agentId > 0) {
				AgentResume agentResume = new AgentResume();
				
				Agent agent = agentBiz.getAgentById(agentId);
				agentResume.setAgent(agent);
				
				if(companyId>0){
					Company company=PlatComAccountStore.getCompanyById(companyId);
					agentResume.setCompany(company);
				}			

				agentResume.setPosition(agentResumeForm.getPosition());
				agentResume.setContent(agentResumeForm.getContent());

				Timestamp beginTime=DateUtil.getTimestamp(agentResumeForm.getBeginDate(), "yyyy-MM-dd");
				agentResume.setBeginTime(beginTime);
				
				Timestamp endTime=DateUtil.getTimestamp(agentResumeForm.getEndDate(), "yyyy-MM-dd");
				agentResume.setEndTime(endTime);

				agentResume.setStatus(agentResumeForm.getStatus());
				agentResume.setType(agentResumeForm.getType());

				long flag = agentResumeBiz.save(agentResume);

				if (flag > 0) {
					return new ActionRedirect(
							"/agent/agentResumeList.do?thisAction=viewALL&agentId="
									+ agentResume.getAgent().getId());
				} else {
					inf.setMessage("修改客户数据异常!");
				}

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
		AgentResume agentResumeForm = (AgentResume) form;
		Inform inf = new Inform();
		UserRightInfo uri = (UserRightInfo) request.getSession().getAttribute(
				"URI");
		try {
			if (agentResumeForm.getId() > 0) {
				AgentResume agentResume = agentResumeBiz
						.getAgentResumeById(agentResumeForm.getId());
				
				long companyId=agentResumeForm.getCompanyId();
				if(companyId>0){
					Company company=PlatComAccountStore.getCompanyById(companyId);
					agentResume.setCompany(company);
				}			

				agentResume.setPosition(agentResumeForm.getPosition());
				agentResume.setContent(agentResumeForm.getContent());

				Timestamp beginTime=DateUtil.getTimestamp(agentResumeForm.getBeginDate(), "yyyy-MM-dd");
				agentResume.setBeginTime(beginTime);
				
				Timestamp endTime=DateUtil.getTimestamp(agentResumeForm.getEndDate(), "yyyy-MM-dd");
				agentResume.setEndTime(endTime);

				agentResume.setStatus(agentResumeForm.getStatus());
				agentResume.setType(agentResumeForm.getType());

				long flag = agentResumeBiz.update(agentResume);


				if (flag > 0) {
					return new ActionRedirect(
							"/agent/agentResumeList.do?thisAction=viewALL&agentId="
									+ agentResume.getAgent().getId());
				} else {
					inf.setMessage("修改客户数据异常!");
				}
			} else {
				inf.setMessage("缺少agentResumeId");
			}
		} catch (Exception e) {
			e.printStackTrace();
			inf.setMessage("异常:" + e.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}

	public void setAgentBiz(AgentBiz agentBiz) {
		this.agentBiz = agentBiz;
	}

	public void setAgentResumeBiz(AgentResumeBiz agentResumeBiz) {
		this.agentResumeBiz = agentResumeBiz;
	}

}
