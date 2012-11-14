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
import com.kurui.kums.base.Constant;
import com.kurui.kums.agent.DirectLevel;
import com.kurui.kums.agent.DirectLevelListForm;
import com.kurui.kums.transaction.util.PlatComAccountStore;
import com.kurui.kums.agent.biz.DirectLevelBiz;

public class DirectLevelListAction extends BaseAction {
	private DirectLevelBiz directLevelBiz;

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		DirectLevelListForm directLevelListForm = (DirectLevelListForm) form;
		if (directLevelListForm == null) {
			directLevelListForm = new DirectLevelListForm();
		}
		try {
			List<DirectLevel> directLevelList = directLevelBiz
					.list(directLevelListForm);
			directLevelListForm.setList(directLevelList);

			request.setAttribute("directLevelListForm", directLevelListForm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("directLevelListForm", directLevelListForm);
		return mapping.findForward("listDirectLevel");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		DirectLevelListForm directLevelListForm = (DirectLevelListForm) form;
		try {
			long directLevelId = Constant.toLong(directLevelListForm.getId());
			DirectLevel directLevel = directLevelBiz
					.getDirectLevelById(directLevelId);
			request.setAttribute("directLevel", directLevel);

		} catch (Exception e) {
			e.printStackTrace();
		}
		forwardPage = "viewDirectLevel";
		return mapping.findForward(forwardPage);
	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		DirectLevelListForm alf = (DirectLevelListForm) form;
		DirectLevel directLevel = new DirectLevel();		
		directLevel.setThisAction("insert");
		directLevel.setSeqIndex(Long.valueOf(0));
		directLevel.setType(DirectLevel.TYPE_1);
		directLevel.setStatus(DirectLevel.STATES_1);

		request.setAttribute("directLevel", directLevel);
		String forwardPage = "editDirectLevel";
		return mapping.findForward(forwardPage);
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		Inform inf = new Inform();
		DirectLevelListForm directLevelListForm = (DirectLevelListForm) form;

		long directLevelId = directLevelListForm.getId();
		if (directLevelId < 1) {
			directLevelId = directLevelListForm.getSelectedItems()[0];
		}

		if (directLevelId > 0) {
			DirectLevel directLevel = directLevelBiz
					.getDirectLevelById(directLevelId);
			directLevel.setThisAction("update");

			request.setAttribute("directLevel", directLevel);
		} else {
			inf.setMessage("缺少directLevelId");
			return forwardInformPage(inf, mapping, request);
		}
		request.setAttribute("companyList", PlatComAccountStore
				.getGroupCompnayList());
		return mapping.findForward("editDirectLevel");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		DirectLevelListForm directLevelListForm = (DirectLevelListForm) form;
		String forwardPage = "";
		long id = 0;
		Inform inf = new Inform();
		try {
			for (int i = 0; i < directLevelListForm.getSelectedItems().length; i++) {
				id = directLevelListForm.getSelectedItems()[i];
				if (id > 0) {
					directLevelBiz.deleteDirectLevel(id);
				}
			}
			return list(mapping, form, request, response);
		} catch (Exception ex) {
			ex.printStackTrace();
			inf.setMessage("删除失败" + ex.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}

	public void setDirectLevelBiz(DirectLevelBiz directLevelBiz) {
		this.directLevelBiz = directLevelBiz;
	}

}