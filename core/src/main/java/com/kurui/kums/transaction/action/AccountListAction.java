package com.kurui.kums.transaction.action;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;

import com.kurui.kums.base.file.report.DownLoadFile;
import com.kurui.kums.base.struts.BaseAction;
import com.kurui.kums.base.ui.inform.Inform;
import com.kurui.kums.base.file.FileUtil;
import com.kurui.kums.base.util.time.DateUtil;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.transaction.Account;
import com.kurui.kums.transaction.AccountListForm;
import com.kurui.kums.transaction.PaymentTool;
import com.kurui.kums.transaction.biz.AccountBiz;
import com.kurui.kums.transaction.biz.PaymentToolBiz;

public class AccountListAction extends BaseAction {
	private AccountBiz accountBiz;
	private PaymentToolBiz paymentToolBiz;

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		AccountListForm accountListForm = (AccountListForm) form;
		List<PaymentTool> paymentToolList = paymentToolBiz.getPaymentToolList();// PlatComAccountStore.paymentToolList
		request.setAttribute("paymentToolList", paymentToolList);

		if (accountListForm == null) {
			accountListForm = new AccountListForm();
		}
		try {
			accountListForm.setList(accountBiz.list(accountListForm));
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("accountListForm", accountListForm);
		return mapping.findForward("listAccount");
	}

	// 账号余额
	public ActionForward listAccountBanlance(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws AppException {
		String forwardPage = "";
		AccountListForm accountListForm = (AccountListForm) form;
		if (accountListForm == null) {
			accountListForm = new AccountListForm();
		}
		try {
			accountListForm.setList(accountBiz.list(accountListForm));
		} catch (Exception e) {
			e.printStackTrace();
		}
		accountListForm.addSumField(1, "totalAmount");

		forwardPage = "listAccountBalance";
		request.setAttribute("accountListForm", accountListForm);
		return mapping.findForward(forwardPage);
	}

	// 导出
	public ActionForward downloadAccountBalance(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws AppException {
		AccountListForm alf = (AccountListForm) form;
		if (alf != null) {

			ArrayList<ArrayList<Object>> lists = accountBiz
					.getAccountBalanceList(alf);
			String outFileName = DateUtil.getDateString("yyyyMMddhhmmss")
					+ ".csv";
			String outText = FileUtil.createCSVFile(lists);
			try {
				outText = new String(outText.getBytes("UTF-8"));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			DownLoadFile df = new DownLoadFile();
			df.performTask(response, outText, outFileName, "GBK");
			return null;
		} else {
			request.getSession().invalidate();
			return mapping.findForward("listAccountBalance");
		}
	}

	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		try {
			AccountListForm alf = (AccountListForm) form;
			long accountId = alf.getId();
			Account account = accountBiz.getAccountById(accountId);
			request.setAttribute("account", account);
		} catch (Exception e) {
			e.printStackTrace();
		}
		forwardPage = "viewAccount";
		return mapping.findForward(forwardPage);
	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		Account account = new Account();
		List<PaymentTool> paymentToolList = paymentToolBiz.getPaymentToolList();// PlatComAccountStore.paymentToolList
		request.setAttribute("paymentToolList", paymentToolList);

		account.setThisAction("insert");
		request.setAttribute("account", account);
		String forwardPage = "editAccount";
		return mapping.findForward(forwardPage);
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		AccountListForm accountListForm = (AccountListForm) form;

		List<PaymentTool> paymentToolList = paymentToolBiz.getPaymentToolList();// PlatComAccountStore.paymentToolList
		request.setAttribute("paymentToolList", paymentToolList);

		long accountId = accountListForm.getSelectedItems()[0];
		if (accountId > 0) {
			Account account = accountBiz.getAccountById(accountId);
			account.setThisAction("update");
			account.setPaymentToolId(account.getPaymentTool().getId());
			request.setAttribute("account", account);
		} else {
			request.setAttribute("account", new Account());
		}
		return mapping.findForward("editAccount");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		AccountListForm accountListForm = (AccountListForm) form;
		String forwardPage = "";
		Inform inf = new Inform();
		try {
			for (int i = 0; i < accountListForm.getSelectedItems().length; i++) {
				long id = accountListForm.getSelectedItems()[i];				
				if (id > 0){				
					 accountBiz.deleteAccount(id);
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

	public void setAccountBiz(AccountBiz accountBiz) {
		this.accountBiz = accountBiz;
	}

}