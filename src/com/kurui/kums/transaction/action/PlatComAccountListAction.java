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
import com.kurui.kums.transaction.PlatComAccount;
import com.kurui.kums.transaction.PlatComAccountListForm;
import com.kurui.kums.transaction.biz.PlatComAccountBiz;
import com.kurui.kums.transaction.util.PlatComAccountStore;

public class PlatComAccountListAction extends BaseAction {
	private PlatComAccountBiz platComAccountBiz;

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		PlatComAccountListForm platComAccountListForm = (PlatComAccountListForm) form;
		request.setAttribute("companyList", PlatComAccountStore.companyList);
		request.setAttribute("platformList", PlatComAccountStore.platformList);
		request.setAttribute("accountList", PlatComAccountStore.accountList);
		if (platComAccountListForm == null) {
			platComAccountListForm = new PlatComAccountListForm();
		}
		try {
			platComAccountListForm.setList(platComAccountBiz
					.list(platComAccountListForm));
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("platComAccountListForm", platComAccountListForm);
		return mapping.findForward("listPlatComAccount");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		PlatComAccount platComAccount = new PlatComAccount();
		try {
			request
					.setAttribute("companyList",
							PlatComAccountStore.companyList);
			request.setAttribute("platformList",
					PlatComAccountStore.platformList);
			request
					.setAttribute("accountList",
							PlatComAccountStore.accountList);
			// request.setAttribute("paymentToolList", paymentToolList);
			platComAccount.setThisAction("insert");
			request.setAttribute("platComAccount", platComAccount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String forwardPage = "editPlatComAccount";
		return mapping.findForward(forwardPage);
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		PlatComAccountListForm platComAccountListForm = (PlatComAccountListForm) form;

		long platComAccountId = platComAccountListForm.getSelectedItems()[0];
		if (platComAccountId > 0) {
			PlatComAccount platComAccount = platComAccountBiz
					.getPlatComAccountById(platComAccountId);
			platComAccount.setThisAction("insert");
			platComAccount.setCompanyId(platComAccount.getCompany().getId());// 修改时返回下拉框原数据
			platComAccount.setPlatformId(platComAccount.getPlatform().getId());
			platComAccount.setAccountId(platComAccount.getAccount().getId());
			request
					.setAttribute("companyList",
							PlatComAccountStore.companyList);
			request.setAttribute("platformList",
					PlatComAccountStore.platformList);
			request
					.setAttribute("accountList",
							PlatComAccountStore.accountList);
			request.setAttribute("platComAccount", platComAccount);
		} else {
			request.setAttribute("platComAccount", new PlatComAccount());
		}
		return mapping.findForward("editPlatComAccount");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		PlatComAccountListForm platComAccountListForm = (PlatComAccountListForm) form;
		String forwardPage = "";
		Inform inf = new Inform();
		try {
			for (int i = 0; i < platComAccountListForm.getSelectedItems().length; i++) {
				long id = platComAccountListForm.getSelectedItems()[i];
				if (id > 0) {
					platComAccountBiz.deletePlatComAccount(id);
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

	public void setPlatComAccountBiz(PlatComAccountBiz platComAccountBiz) {
		this.platComAccountBiz = platComAccountBiz;
	}
}