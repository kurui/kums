package com.kurui.kums.agent.action;

import java.util.List;
import java.util.Set;
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
import com.kurui.kums.agent.Coterie;
import com.kurui.kums.agent.CoterieListForm;
import com.kurui.kums.transaction.util.PlatComAccountStore;
import com.kurui.kums.agent.biz.AgentBiz;
import com.kurui.kums.agent.biz.CoterieBiz;

public class CoterieListAction extends BaseAction {
	private AgentBiz agentBiz;
	private CoterieBiz coterieBiz;

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		CoterieListForm coterieListForm = (CoterieListForm) form;
		if (coterieListForm == null) {
			coterieListForm = new CoterieListForm();
		}
		try {
			List<Coterie> coterieList = coterieBiz.list(coterieListForm);
			coterieListForm.setList(coterieList);

			request.setAttribute("coterieListForm", coterieListForm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("coterieListForm", coterieListForm);
		return mapping.findForward("listCoterie");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		CoterieListForm coterieListForm = (CoterieListForm) form;
		try {
			long coterieId = Constant.toLong(coterieListForm.getId());
			Coterie coterie = coterieBiz.getCoterieById(coterieId);
			request.setAttribute("coterie", coterie);

		} catch (Exception e) {
			e.printStackTrace();
		}
		forwardPage = "viewCoterie";
		return mapping.findForward(forwardPage);
	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		CoterieListForm alf = (CoterieListForm) form;
		Inform inf = new Inform();
		long agentId = Constant.toLong(alf.getRootAgentId());
		if (agentId > 0) {
			Agent agent = agentBiz.getAgentById(agentId);
			if (agent != null) {
				Coterie coterie = null;
				Set<Coterie> coterieSet = agent.getAgentCoteries();

				if (coterieSet.size() > 0) {
					coterie = coterieSet.iterator().next();
					coterie.setThisAction("update");
				} else {
					coterie = new Coterie();
					coterie.setThisAction("insert");
					coterie.setRootAgent(agent);
				}
				request.setAttribute("coterie", coterie);
			} else {
				inf.setMessage("Agent为空");
				return forwardInformPage(inf, mapping, request);
			}
		} else {
			inf.setMessage("缺少AgentId");
			return forwardInformPage(inf, mapping, request);
		}

		String forwardPage = "editCoterie";
		return mapping.findForward(forwardPage);
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		Inform inf = new Inform();
		CoterieListForm coterieListForm = (CoterieListForm) form;

		long coterieId = coterieListForm.getId();
		if (coterieId < 1) {
			coterieId = coterieListForm.getSelectedItems()[0];
		}

		if (coterieId > 0) {
			Coterie coterie = coterieBiz.getCoterieById(coterieId);
			coterie.setThisAction("update");

			request.setAttribute("coterie", coterie);
		} else {
			inf.setMessage("缺少coterieId");
			return forwardInformPage(inf, mapping, request);
		}
		request.setAttribute("companyList", PlatComAccountStore
				.getGroupCompnayList());
		return mapping.findForward("editCoterie");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		CoterieListForm coterieListForm = (CoterieListForm) form;
		String forwardPage = "";
		long id = 0;
		Inform inf = new Inform();
		int message = 0;
		try {
			for (int i = 0; i < coterieListForm.getSelectedItems().length; i++) {
				id = coterieListForm.getSelectedItems()[i];
				if (id > 0) {
					coterieBiz.deleteCoterie(id);
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

	public void setCoterieBiz(CoterieBiz coterieBiz) {
		this.coterieBiz = coterieBiz;
	}

}