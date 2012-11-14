package com.kurui.kums.market.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.kurui.kums.base.struts.BaseAction;
import com.kurui.kums.base.ui.inform.Inform;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.market.EstateDish;
import com.kurui.kums.market.EstateDishListForm;
import com.kurui.kums.market.biz.EstateDishBiz;
import com.kurui.kums.transaction.Company;
import com.kurui.kums.transaction.biz.CompanyBiz;

public class EstateDishListAction extends BaseAction {
	private EstateDishBiz estateDishBiz;
	private CompanyBiz companyBiz;

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		EstateDishListForm estateDishListForm = (EstateDishListForm) form;
		if (estateDishListForm == null) {
			estateDishListForm = new EstateDishListForm();
		}
		try {
			estateDishListForm.setList(estateDishBiz.list(estateDishListForm));
			
			loadDataStore(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("estateDish", estateDishListForm);
		return mapping.findForward("listEstateDish");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		EstateDishListForm estateDishListForm = (EstateDishListForm) form;
		if (estateDishListForm == null) {
			estateDishListForm = new EstateDishListForm();
		}
		String forwardPage = "";
		try {
			Long estateDishId = estateDishListForm.getId();
			EstateDish estateDish = estateDishBiz
					.getEstateDishById(estateDishId);
			request.setAttribute("estateDish", estateDish);

		} catch (Exception e) {
			e.printStackTrace();
		}
		forwardPage = "viewEstateDish";
		return mapping.findForward(forwardPage);

	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		EstateDishListForm estateDishListForm = (EstateDishListForm) form;

		EstateDish estateDish = new EstateDish();
		estateDish.setThisAction("insert");
		estateDish.setType(EstateDish.TYPE_1);

		request.setAttribute("estateDish", estateDish);
		
		loadDataStore(request);
		
		String forwardPage = "editEstateDish";
		return mapping.findForward(forwardPage);
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		EstateDishListForm estateDishListForm = (EstateDishListForm) form;
		long estateDishId = estateDishListForm.getId();
		if (estateDishId < 1) {
			estateDishId = estateDishListForm.getSelectedItems()[0];
		}

		if (estateDishId > 0) {
			EstateDish estateDish = estateDishBiz
					.getEstateDishById(estateDishId);
			estateDish.setThisAction("update");
			
			request.setAttribute("estateDish", estateDish);
		} else {
			request.setAttribute("estateDish", new EstateDish());
		}
		
		loadDataStore(request);
		
		return mapping.findForward("editEstateDish");
	}

	public HttpServletRequest loadDataStore(HttpServletRequest request)
			throws AppException {
		List<Company> companyList = companyBiz
				.getCompanyListByProvideChain("5040");// 地产公司
		request.setAttribute("companyList", companyList);
		return request;
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		EstateDishListForm estateDishListForm = (EstateDishListForm) form;
		// String forwardPage = "";
		long id = 0;
		Inform inf = new Inform();
		try {
			for (int i = 0; i < estateDishListForm.getSelectedItems().length; i++) {
				id = estateDishListForm.getSelectedItems()[i];
				estateDishBiz.deleteEstateDish(id);
			}
			return list(mapping, form, request, response);
		} catch (Exception ex) {
			inf.setMessage("删除失败" + ex.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}

	public void setEstateDishBiz(EstateDishBiz estateDishBiz) {
		this.estateDishBiz = estateDishBiz;
	}

	public void setCompanyBiz(CompanyBiz companyBiz) {
		this.companyBiz = companyBiz;
	}

}