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
import com.kurui.kums.agent.AgentCoterie;
import com.kurui.kums.agent.AgentCoterieListForm;
import com.kurui.kums.agent.Coterie;
import com.kurui.kums.transaction.util.PlatComAccountStore;
import com.kurui.kums.agent.biz.AgentBiz;
import com.kurui.kums.agent.biz.AgentCoterieBiz;
import com.kurui.kums.agent.biz.CoterieBiz;

public class AgentCoterieListAction extends BaseAction {
	private AgentBiz agentBiz;
	private AgentCoterieBiz agentCoterieBiz;
	private CoterieBiz coterieBiz;

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		AgentCoterieListForm agentCoterieListForm = (AgentCoterieListForm) form;
		if (agentCoterieListForm == null) {
			agentCoterieListForm = new AgentCoterieListForm();
		}
		try {
			List<AgentCoterie> agentCoterieList = agentCoterieBiz
					.list(agentCoterieListForm);
			agentCoterieListForm.setList(agentCoterieList);

			request.setAttribute("agentCoterieListForm", agentCoterieListForm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("agentCoterieListForm", agentCoterieListForm);
		return mapping.findForward("listAgentCoterie");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		AgentCoterieListForm agentCoterieListForm = (AgentCoterieListForm) form;
		try {
			long agentCoterieId = Constant.toLong(agentCoterieListForm.getId());
			AgentCoterie agentCoterie = agentCoterieBiz
					.getAgentCoterieById(agentCoterieId);
			request.setAttribute("agentCoterie", agentCoterie);

		} catch (Exception e) {
			e.printStackTrace();
		}
		forwardPage = "viewAgentCoterie";
		return mapping.findForward(forwardPage);
	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		AgentCoterieListForm alf = (AgentCoterieListForm) form;
		Inform inf = new Inform();
		long coterieId = Constant.toLong(alf.getCoterieId());
		if (coterieId > 0) {
			Coterie coterie = coterieBiz.getCoterieById(coterieId);
			if (coterie != null) {
				AgentCoterie agentCoterie = new AgentCoterie();				
				agentCoterie.setThisAction("insert");
				agentCoterie.setCoterie(coterie);
				// agentCoterie.setSubAgent(agent);

				request.setAttribute("agentCoterie", agentCoterie);
			} else {
				inf.setMessage("Agent为空");
				return forwardInformPage(inf, mapping, request);
			}
		} else {
			inf.setMessage("缺少圈子Id");
			return forwardInformPage(inf, mapping, request);
		}

		String forwardPage = "editAgentCoterie";
		return mapping.findForward(forwardPage);
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		Inform inf = new Inform();
		AgentCoterieListForm agentCoterieListForm = (AgentCoterieListForm) form;

		long agentCoterieId = agentCoterieListForm.getId();
		if (agentCoterieId < 1) {
			agentCoterieId = agentCoterieListForm.getSelectedItems()[0];
		}

		if (agentCoterieId > 0) {
			AgentCoterie agentCoterie = agentCoterieBiz
					.getAgentCoterieById(agentCoterieId);
			agentCoterie.setThisAction("update");

			request.setAttribute("agentCoterie", agentCoterie);
		} else {
			inf.setMessage("缺少agentCoterieId");
			return forwardInformPage(inf, mapping, request);
		}
		request.setAttribute("companyList", PlatComAccountStore
				.getGroupCompnayList());
		return mapping.findForward("editAgentCoterie");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		AgentCoterieListForm agentCoterieListForm = (AgentCoterieListForm) form;
		String forwardPage = "";
		long id = 0;
		Inform inf = new Inform();
		int message = 0;
		try {
			for (int i = 0; i < agentCoterieListForm.getSelectedItems().length; i++) {
				id = agentCoterieListForm.getSelectedItems()[i];
				if (id > 0) {
					agentCoterieBiz.deleteAgentCoterie(id);
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

	public void setAgentCoterieBiz(AgentCoterieBiz agentCoterieBiz) {
		this.agentCoterieBiz = agentCoterieBiz;
	}

	public void setCoterieBiz(CoterieBiz coterieBiz) {
		this.coterieBiz = coterieBiz;
	}

}