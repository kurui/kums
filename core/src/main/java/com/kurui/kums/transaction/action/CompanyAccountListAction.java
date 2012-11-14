package com.kurui.kums.transaction.action;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.kurui.kums.base.struts.BaseAction;
import com.kurui.kums.base.ui.inform.Inform;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.transaction.Account;
import com.kurui.kums.transaction.PaymentTool;
import com.kurui.kums.transaction.biz.PaymentToolBiz;
import com.kurui.kums.transaction.Company;
import com.kurui.kums.transaction.CompanyAccount;
import com.kurui.kums.transaction.CompanyAccountListForm;
import com.kurui.kums.transaction.biz.CompanyAccountBiz;
import com.kurui.kums.transaction.biz.CompanyBiz;

public class CompanyAccountListAction extends BaseAction {
	private CompanyAccountBiz companyAccountBiz;
	private CompanyBiz companyBiz;
	private PaymentToolBiz paymentToolBiz;

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		CompanyAccountListForm companyAccountListForm = (CompanyAccountListForm) form;

		if (companyAccountListForm == null) {
			companyAccountListForm = new CompanyAccountListForm();
		}
		try {
			List<CompanyAccount> companyAccountList = companyAccountBiz
					.list(companyAccountListForm);
			companyAccountListForm.setList(companyAccountList);

			List<PaymentTool> paymentToolList = paymentToolBiz
					.getPaymentToolList();
			request.setAttribute("paymentToolList", paymentToolList);
			
			long companyId=companyAccountListForm.getCompanyId();
			if(companyId>0){
				Company company=companyBiz.getCompanyById(companyId);
				request.setAttribute("company",company);
			}			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("companyAccountListForm", companyAccountListForm);
		return mapping.findForward("listCompanyAccount");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		CompanyAccountListForm alf = (CompanyAccountListForm) form;
		CompanyAccount companyAccount = new CompanyAccount();
		companyAccount.setAccountType(alf.getAccountType());
		
		long companyId = alf.getCompanyId();
		Company company = companyBiz.getCompanyById(companyId);
		companyAccount.setCompany(company);

		Account account = new Account();
		account.setTranType(Account.TRAN_TYPE_3);
		account.setType(Account.TYPE_11);
		account.setStatus(Account.STATES_1);
		
		companyAccount.setAccount(account);

		List<PaymentTool> paymentToolList = paymentToolBiz.getPaymentToolList();
		request.setAttribute("paymentToolList", paymentToolList);

		companyAccount.setThisAction("insert");
		request.setAttribute("companyAccount", companyAccount);

		String forwardPage = "editCompanyAccount";
		return mapping.findForward(forwardPage);
	}
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		CompanyAccountListForm companyAccountListForm = (CompanyAccountListForm) form;
		String forwardPage = "";
		Inform inf = new Inform();
		try {
			int[] selectedItsms=companyAccountListForm.getSelectedItems();
			int itemsLength=selectedItsms.length;
			for (int i = 0; i < itemsLength; i++) {
				long id = companyAccountListForm.getSelectedItems()[i];
				if (id > 0) {
					companyAccountBiz.deleteCompanyAccount(id);
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

	public void setPaymentToolBiz(PaymentToolBiz paymentToolBiz) {
		this.paymentToolBiz = paymentToolBiz;
	}

	public void setCompanyAccountBiz(CompanyAccountBiz companyAccountBiz) {
		this.companyAccountBiz = companyAccountBiz;
	}

	public void setCompanyBiz(CompanyBiz companyBiz) {
		this.companyBiz = companyBiz;
	}
}