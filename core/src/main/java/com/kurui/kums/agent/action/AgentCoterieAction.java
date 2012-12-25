package com.kurui.kums.agent.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;

import com.kurui.kums.agent.Agent;
import com.kurui.kums.agent.AgentCoterie;
import com.kurui.kums.agent.Coterie;
import com.kurui.kums.agent.biz.AgentBiz;
import com.kurui.kums.agent.biz.AgentCoterieBiz;
import com.kurui.kums.agent.biz.CoterieBiz;
import com.kurui.kums.base.struts.BaseAction;
import com.kurui.kums.base.ui.inform.Inform;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.right.UserRightInfo;

public class AgentCoterieAction extends BaseAction {
	private AgentBiz agentBiz;
	private AgentCoterieBiz agentCoterieBiz;
	private CoterieBiz coterieBiz;

	public ActionForward insert(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		AgentCoterie agentCoterieForm = (AgentCoterie) form;
		Inform inf = new Inform();
		UserRightInfo uri = (UserRightInfo) request.getSession().getAttribute(
				"URI");
		try {
			long agentId = agentCoterieForm.getSubAgentId();
			if (agentId > 0) {
				long coterieId = agentCoterieForm.getCoterieId();
				if (coterieId > 0) {
					AgentCoterie agentCoterie = new AgentCoterie();

					Coterie coterie = coterieBiz.getCoterieById(coterieId);
					agentCoterie.setCoterie(coterie);
					Agent agent = agentBiz.getAgentById(agentId);
					agentCoterie.setSubAgent(agent);

					agentCoterie.setStatus(agentCoterieForm.getStatus());
					agentCoterie.setCreateTime(new Timestamp(System
							.currentTimeMillis()));
					agentCoterie.setFromTime(new Timestamp(System
							.currentTimeMillis()));
					agentCoterie.setExpireTime(new Timestamp(System
							.currentTimeMillis()));

					long num = agentCoterieBiz.save(agentCoterie);

					if (num > 0) {
						ActionRedirect redirect = new ActionRedirect(
								"/agent/agentCoterieList.do?thisAction=list");
						redirect = new ActionRedirect(
									"/agent/coterieList.do?thisAction=view&id="+coterieId);
						return redirect;
					} else {
						inf.setMessage("您添加客户数据异常！");
					}
				} else {
					inf.setMessage("缺少agentCoterieId");
				}
			} else {
				inf.setMessage("缺少agentId");
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
		AgentCoterie agentCoterieForm = (AgentCoterie) form;
		Inform inf = new Inform();
		UserRightInfo uri = (UserRightInfo) request.getSession().getAttribute(
				"URI");
		try {
			long habitId = agentCoterieForm.getId();
			if (habitId > 0) {
				AgentCoterie agentCoterie = agentCoterieBiz
						.getAgentCoterieById(habitId);
				agentCoterie.setStatus(agentCoterieForm.getStatus());
				agentCoterie.setCreateTime(new Timestamp(System
						.currentTimeMillis()));
				agentCoterie.setFromTime(new Timestamp(System
						.currentTimeMillis()));
				agentCoterie.setExpireTime(new Timestamp(System
						.currentTimeMillis()));

				long flag = agentCoterieBiz.update(agentCoterie);

				if (flag > 0) {
					return new ActionRedirect(
							"/agent/agentCoterieList.do?thisAction=list");
				} else {
					inf.setMessage("修改客户数据异常!");
				}
			} else {
				inf.setMessage("缺少agentCoterieId");
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

	public void setAgentCoterieBiz(AgentCoterieBiz agentCoterieBiz) {
		this.agentCoterieBiz = agentCoterieBiz;
	}

	public void setCoterieBiz(CoterieBiz coterieBiz) {
		this.coterieBiz = coterieBiz;
	}

}