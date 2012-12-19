package com.kurui.kums.agent.action;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.kurui.kums.base.struts.BaseAction;
import com.kurui.kums.base.ui.inform.Inform;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.library.Company;
import com.kurui.kums.library.biz.CompanyBiz;
import com.kurui.kums.library.util.PlatComAccountStore;
import com.kurui.kums.agent.Agent;
import com.kurui.kums.agent.ShareHolder;
import com.kurui.kums.agent.ShareHolderListForm;
import com.kurui.kums.agent.biz.ShareHolderBiz;
import com.kurui.kums.agent.biz.AgentBiz;

public class ShareHolderListAction extends BaseAction {
	private AgentBiz agentBiz;
	private CompanyBiz companyBiz;
	private ShareHolderBiz shareHolderBiz;

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		ShareHolderListForm shareHolderListForm = (ShareHolderListForm) form;

		if (shareHolderListForm == null) {
			shareHolderListForm = new ShareHolderListForm();
		}
		try {
			List<ShareHolder> shareHolderList = shareHolderBiz
					.list(shareHolderListForm);
			shareHolderListForm.setList(shareHolderList);

			long agentId = shareHolderListForm.getAgentId();
			if (agentId > 0) {
				Agent agent = agentBiz.getAgentById(agentId);
				request.setAttribute("agent", agent);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("shareHolderListForm", shareHolderListForm);
		return mapping.findForward("listShareHolder");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		ShareHolderListForm alf = (ShareHolderListForm) form;
		ShareHolder shareHolder = new ShareHolder();

		long agentId = alf.getAgentId();
		if (agentId > 0) {
			Agent agent = agentBiz.getAgentById(agentId);
			shareHolder.setAgent(agent);
		}

		shareHolder.setThisAction("insert");
		request.setAttribute("shareHolder", shareHolder);

		List<Company> companyList = PlatComAccountStore.companyList;
		request.setAttribute("companyList", companyList);

		String forwardPage = "editShareHolder";
		return mapping.findForward(forwardPage);
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		Inform inf = new Inform();
		ShareHolderListForm shareHolderListForm = (ShareHolderListForm) form;

		long shareHolderId = shareHolderListForm.getId();
		if (shareHolderId < 1) {
			shareHolderId = shareHolderListForm.getSelectedItems()[0];
		}

		if (shareHolderId > 0) {
			ShareHolder shareHolder = shareHolderBiz
					.getShareHolderById(shareHolderId);
			shareHolder.setThisAction("update");

			request.setAttribute("shareHolder", shareHolder);
		} else {
			inf.setMessage("缺少shareHolderId");
			return forwardInformPage(inf, mapping, request);
		}
		request.setAttribute("companyList", PlatComAccountStore.companyList);
		return mapping.findForward("editShareHolder");
	}

	public void setCompanyBiz(CompanyBiz companyBiz) {
		this.companyBiz = companyBiz;
	}

	public void setShareHolderBiz(ShareHolderBiz shareHolderBiz) {
		this.shareHolderBiz = shareHolderBiz;
	}

	public void setAgentBiz(AgentBiz agentBiz) {
		this.agentBiz = agentBiz;
	}
}