package com.kurui.kums.right.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.kurui.kums.base.struts.BaseAction;
import com.kurui.kums.base.ui.inform.Inform;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.right.License;
import com.kurui.kums.right.LicenseListForm;
import com.kurui.kums.right.SysUser;
import com.kurui.kums.right.UserRightInfo;
import com.kurui.kums.right.biz.LicenseBiz;

public class LicenseListAction extends BaseAction {
	private LicenseBiz licenseBiz;

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		LicenseListForm nlf = (LicenseListForm) form;
		if (nlf == null)
			nlf = new LicenseListForm();

		List list = licenseBiz.getLicense(nlf);
	
		nlf.setList(list);
		request.setAttribute("nlf", nlf);
		forwardPage = "listLicense";
		return mapping.findForward(forwardPage);
	}
	
	
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		SysUser user = this.getUserByURI(request);

		License license = new License();
//		license.setLicenseType(License.LICENSE_TYPE_0);
//		license.setStatus(License.STATUS_1);
		license.setThisAction("insert");
		
		request.setAttribute("license", license);
		forwardPage = "editLicense";

		return (mapping.findForward(forwardPage));
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		SysUser user = this.getUserByURI(request);
		String forwardPage = "";
		License license = null;
		if (user != null) {
			LicenseListForm nlf = (LicenseListForm) form;
			int id = 0;
			if (nlf.getSelectedItems().length > 0) {
				id = nlf.getSelectedItems()[0];
			} else
				id = nlf.getId();
			if (id > 0) {
				license = licenseBiz.getLicenseById(id);
				license.setThisAction("update");
				request.setAttribute("license", license);
			} else
				request.setAttribute("license", new License());
			request.setAttribute("userName", user.getUserName());
			forwardPage = "editLicense";
		} else {
			request.getSession().invalidate();
			return mapping.findForward("exit");
		}
		return (mapping.findForward(forwardPage));
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		LicenseListForm nlf = (LicenseListForm) form;

		String forwardPage = "";
		int id = 0;
		Inform inf = new Inform();
		try {
			for (int i = 0; i < nlf.getSelectedItems().length; i++) {
				id = nlf.getSelectedItems()[i];
				if (id > 0) {
					licenseBiz.deleteLicenseById(id);
					inf.setMessage("您已经成功删除了新闻！");
				}
			}
			inf.setForwardPage("/information/licenseList.do");
			inf.setParamId("thisAction");
			inf.setParamValue("list");
		} catch (Exception ex) {
			inf.setMessage("删除新闻出错！错误信息是：" + ex.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}

	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		int id = Integer.parseInt(request.getParameter("id"));

		License license = (License) licenseBiz.getLicenseById(id);

		license.setThisAction("view");
		request.setAttribute("license", license);

		forwardPage = "viewLicense";
		return (mapping.findForward(forwardPage));
	}

	public ActionForward viewClient(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";

		License license =  licenseBiz.getClientLicense();

		license.setThisAction("view");
		request.setAttribute("license", license);

		forwardPage = "viewLicense";
		return (mapping.findForward(forwardPage));
	}
	
	public SysUser getUserByURI(HttpServletRequest request) {
		UserRightInfo uri = (UserRightInfo) request.getSession().getAttribute(
				"URI");
		if (uri != null && uri.getUser() != null)
			return uri.getUser();
		else {
			return null;
		}
	}

	public void setLicenseBiz(LicenseBiz licenseBiz) {
		this.licenseBiz = licenseBiz;
	}
}
