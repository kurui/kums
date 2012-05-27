package com.kurui.kums.agent.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;

import com.kurui.kums.agent.Agent;
import com.kurui.kums.agent.Coterie;
import com.kurui.kums.agent.biz.AgentBiz;
import com.kurui.kums.agent.biz.CoterieBiz;
import com.kurui.kums.base.BaseAction;
import com.kurui.kums.base.Inform;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.right.UserRightInfo;

public class CoterieAction extends BaseAction {
	private AgentBiz agentBiz;
	private CoterieBiz coterieBiz;

	public ActionForward insert(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		Coterie coterieForm = (Coterie) form;
		Inform inf = new Inform();
		UserRightInfo uri = (UserRightInfo) request.getSession().getAttribute(
				"URI");
		try {
			long agentId = coterieForm.getRootAgentId();
			if (agentId > 0) {
				Coterie coterie = new Coterie();
				coterie.setName(coterieForm.getName());
				Agent agent = agentBiz.getAgentById(agentId);
				coterie.setRootAgent(agent);			
				
				coterie.setStatus(coterieForm.getStatus());
				coterie.setMemo(coterieForm.getMemo());

				long num = coterieBiz.save(coterie);

				if (num > 0) {
					ActionRedirect redirect = new ActionRedirect(
							"/agent/coterieList.do?thisAction=list");
					return redirect;
				} else {
					inf.setMessage("您添加客户数据异常！");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			inf.setMessage("异常:" + e.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}

	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		Coterie coterieForm = (Coterie) form;
		Inform inf = new Inform();
		UserRightInfo uri = (UserRightInfo) request.getSession().getAttribute(
				"URI");
		try {
			long coterieId = coterieForm.getId();
			if (coterieId > 0) {
				Coterie coterie = coterieBiz.getCoterieById(coterieId);
				
				coterie.setName(coterieForm.getName());
				coterie.setStatus(coterieForm.getStatus());
				coterie.setMemo(coterieForm.getMemo());

				long flag = coterieBiz.update(coterie);

				if (flag > 0) {
					return new ActionRedirect(
							"/agent/coterieList.do?thisAction=list");
				} else {
					inf.setMessage("修改客户数据异常!");
				}
			} else {
				inf.setMessage("缺少directLevelId");
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

	public void setCoterieBiz(CoterieBiz coterieBiz) {
		this.coterieBiz = coterieBiz;
	}

}