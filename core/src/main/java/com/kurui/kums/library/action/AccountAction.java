package com.kurui.kums.library.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;

import com.kurui.kums.base.struts.BaseAction;
import com.kurui.kums.base.KumsDataStoreListener;
import com.kurui.kums.base.ui.inform.Inform;
import com.kurui.kums.base.threads.MainTask;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.system.biz.SysInitBiz;
import com.kurui.kums.library.Account;
import com.kurui.kums.library.PaymentTool;
import com.kurui.kums.library.biz.AccountBiz;
import com.kurui.kums.library.biz.PaymentToolBiz;

public class AccountAction extends BaseAction {
	private AccountBiz accountBiz;
	private PaymentToolBiz paymentToolBiz;
	private SysInitBiz sysInitBiz;

	public ActionForward insert(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		System.out.println("=====================");
//		String forwardPage = "";
		Account accountForm = (Account) form;
		Inform inf = new Inform();
		try {
			long paymentToolId = accountForm.getPaymentToolId();
			if (paymentToolId > 0) {
				Account account = new Account();
				PaymentTool paymentTool = paymentToolBiz
						.getPaymentToolById(paymentToolId);
				account.setAccountAddress(accountForm.getAccountAddress());
				account.setName(accountForm.getName());
				account.setAccountNo(accountForm.getAccountNo());
				account.setBusinessType(accountForm.getBusinessType());
				account.setPersonalType(accountForm.getPersonalType());				
				account.setTranType(accountForm.getTranType());
				account.setType(Account.TYPE_1);
				account.setDescription(accountForm.getDescription());
				account.setStatus(accountForm.getStatus());
				account.setPaymentTool(paymentTool);
				long num = accountBiz.save(account);
				// --更新静态库
				KumsDataStoreListener listener = new KumsDataStoreListener(sysInitBiz,
						"Account");
				MainTask.put(listener);
				// ---------
				if (num > 0) {
					return redirect(account);
				} else {
					inf.setMessage("添加账号失败！");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			inf.setMessage("异常:" + e.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}

	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		Account accountForm = (Account) form;
		Inform inf = new Inform();
		try {
			if (accountForm.getId() > 0) {
				long paymentToolId = accountForm.getPaymentToolId();
				if (paymentToolId > 0) {
					PaymentTool paymentTool = paymentToolBiz
							.getPaymentToolById(paymentToolId);

					Account account = accountBiz.getAccountById(accountForm
							.getId());
					account.setAccountAddress(accountForm.getAccountAddress());
					account.setName(accountForm.getName());
					account.setAccountNo(accountForm.getAccountNo());
					account.setBusinessType(accountForm.getBusinessType());
					account.setPersonalType(accountForm.getPersonalType());	
					account.setTranType(accountForm.getTranType());
					account.setType(Account.TYPE_1);
					account.setDescription(accountForm.getDescription());
					account.setStatus(accountForm.getStatus());
					account.setPaymentTool(paymentTool);
					long flag = accountBiz.update(account);
					// --更新静态库
					KumsDataStoreListener listener = new KumsDataStoreListener(
							sysInitBiz, "Account");
					MainTask.put(listener);
					// ---------
					if (flag > 0) {
						return redirect(account);
					} else {
						inf.setMessage("修改账号失败！");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			inf.setMessage("异常:" + e.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}

	public ActionRedirect redirect(Account account) {
		ActionRedirect redirect = new ActionRedirect(
				"/library/accountList.do");

		redirect.addParameter("thisAction", "view");
		redirect.addParameter("id", account.getId());
		return redirect;
	}

	public void setSysInitBiz(SysInitBiz sysInitBiz) {
		this.sysInitBiz = sysInitBiz;
	}

	public void setPaymentToolBiz(PaymentToolBiz paymentToolBiz) {
		this.paymentToolBiz = paymentToolBiz;
	}

	public void setAccountBiz(AccountBiz accountBiz) {
		this.accountBiz = accountBiz;
	}

}