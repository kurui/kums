package com.kurui.kums.transaction.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.kurui.kums.base.struts.BaseAction;
import com.kurui.kums.base.ui.inform.Inform;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.base.Constant;
import com.kurui.kums.transaction.Company;
import com.kurui.kums.transaction.CompanyListForm;
import com.kurui.kums.transaction.biz.CompanyBiz;

public class CompanyListAction extends BaseAction {
	private CompanyBiz companyBiz;

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		CompanyListForm clf = (CompanyListForm) form;
		String forwardPage = "";
		Inform inf = new Inform();
		try {
			if (clf != null) {
				clf.setList(companyBiz.list(clf));
				
				request.setAttribute("companyListForm", clf);

				forwardPage = "listCompany";
				return mapping.findForward(forwardPage);
			} else {
				inf.setMessage("表单不能为空");
			}
		} catch (Exception e) {
			e.printStackTrace();
			inf.setMessage("异常:"+e.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}

	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		try {
			CompanyListForm clf = (CompanyListForm) form;
			long companyId = Constant.toLong(clf.getId());
			Company company = companyBiz.getCompanyById(companyId);
			request.setAttribute("company", company);
		} catch (Exception e) {
			e.printStackTrace();
		}
		forwardPage = "viewCompany";
		return mapping.findForward(forwardPage);
	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		CompanyListForm clf = (CompanyListForm) form;
		Company company = new Company();
		company.setThisAction("insert");
		company.setType(clf.getType());
		request.setAttribute("company", company);
		String forwardPage = "editCompany";
		return mapping.findForward(forwardPage);
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		CompanyListForm clf = (CompanyListForm) form;
		long companyId = 0;
		if (clf.getSelectedItems() != null) {
			companyId = clf.getSelectedItems()[0];
		}
		Company company = companyBiz.getCompanyById(companyId);
		company.setThisAction("update");
				
		request.setAttribute("company", company);
		String forwardPage = "editCompany";
		return mapping.findForward(forwardPage);
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		CompanyListForm companyListForm = (CompanyListForm) form;
		String forwardPage = "";
		Inform inf = new Inform();
		try {
			for (int i = 0; i < companyListForm.getSelectedItems().length; i++) {
				long id = companyListForm.getSelectedItems()[i];
				if (id > 0) {
					companyBiz.deleteCompany(id);
				} else {
					inf.setMessage("不能获取ID,删除失败!");
				}
			}
			return list(mapping, form, request, response);
		} catch (Exception e) {
			e.printStackTrace();
			inf.setMessage("异常:"+e.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}

	public void setCompanyBiz(CompanyBiz companyBiz) {
		this.companyBiz = companyBiz;
	}
}