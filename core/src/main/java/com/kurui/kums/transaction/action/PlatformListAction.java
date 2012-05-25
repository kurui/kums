package com.kurui.kums.transaction.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;

import com.kurui.kums.base.BaseAction;
import com.kurui.kums.base.Inform;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.transaction.Platform;
import com.kurui.kums.transaction.PlatformListForm;
import com.kurui.kums.transaction.biz.PlatformBiz;

public class PlatformListAction extends BaseAction {
	private PlatformBiz platformBiz;

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		PlatformListForm platformListForm = (PlatformListForm) form;
		if (platformListForm == null) {
			platformListForm = new PlatformListForm();
		}
		try {
			platformListForm.setList(platformBiz.list(platformListForm));
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("platformListForm", platformListForm);
		return mapping.findForward("listPlatform");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		try {
			PlatformListForm platformListForm = (PlatformListForm) form;

			long platformId = platformListForm.getId();
			Platform platform = platformBiz.getPlatformById(platformId);
			request.setAttribute("platform", platform);
		} catch (Exception e) {
			e.printStackTrace();
		}
		forwardPage = "viewPlatform";
		return mapping.findForward(forwardPage);
	}

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		Platform platform = new Platform();
		platform.setThisAction("insert");
		request.setAttribute("platform", platform);
		String forwardPage = "editPlatform";
		return mapping.findForward(forwardPage);
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		PlatformListForm platformListForm = (PlatformListForm) form;
		long platformId = platformListForm.getSelectedItems()[0];
		if (platformId > 0) {
			Platform platform = platformBiz.getPlatformById(platformId);
			platform.setThisAction("update");
			request.setAttribute("platform", platform);
		} else {
			request.setAttribute("platform", new Platform());
		}
		return mapping.findForward("editPlatform");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		PlatformListForm platformListForm = (PlatformListForm) form;
		// String forwardPage = "";
		long id = 0;
		Inform inf = new Inform();
		try {
			for (int i = 0; i < platformListForm.getSelectedItems().length; i++) {
				id = platformListForm.getSelectedItems()[i];
				if (id > 0) {
					platformBiz.deletePlatform(id);
				} else {
					inf.setMessage("不能获取ID,删除失败!");
				}
			}
			return list(mapping, form, request, response);
		} catch (Exception ex) {
			inf.setMessage("删除失败" + ex.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}

	public void setPlatformBiz(PlatformBiz platformBiz) {
		this.platformBiz = platformBiz;
	}
}