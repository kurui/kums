package com.kurui.kums.library.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;

import com.kurui.kums.library.Company;
import com.kurui.kums.library.CompanyAccount;
import com.kurui.kums.library.biz.CompanyAccountBiz;
import com.kurui.kums.library.biz.CompanyBiz;
import com.kurui.kums.base.struts.BaseAction;
import com.kurui.kums.base.KumsDataStoreListener;
import com.kurui.kums.base.ui.inform.Inform;
import com.kurui.kums.base.threads.MainTask;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.right.UserRightInfo;
import com.kurui.kums.system.biz.SysInitBiz;
import com.kurui.kums.library.Account;
import com.kurui.kums.library.PaymentTool;
import com.kurui.kums.library.biz.AccountBiz;
import com.kurui.kums.library.biz.PaymentToolBiz;

public class CompanyAccountAction extends BaseAction {
	private AccountBiz accountBiz;
	private CompanyBiz companyBiz;
	private CompanyAccountBiz companyAccountBiz;
	private PaymentToolBiz paymentToolBiz;
	private SysInitBiz sysInitBiz;

	public ActionForward insert(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		CompanyAccount companyAccountForm = (CompanyAccount) form;
		Inform inf = new Inform();
		UserRightInfo uri = (UserRightInfo) request.getSession().getAttribute(
				"URI");
		try {
			long companyId = companyAccountForm.getCompanyId();

			if (companyId > 0) {				
				Company company = companyBiz.getCompanyById(companyId);
				long paymentToolId = companyAccountForm.getPaymentToolId();
				if (paymentToolId > 0) {
					Account account = new Account();
					PaymentTool paymentTool = paymentToolBiz
							.getPaymentToolById(paymentToolId);
					account.setName(companyAccountForm.getAccountName());
					account.setAccountNo(companyAccountForm.getAccountNo());
					account.setBusinessType(companyAccountForm.getAccountBusinessType());
					account.setPersonalType(companyAccountForm.getAccountPersonalType());
					account.setTranType(companyAccountForm.getAccountTranType());
					account.setType(Account.TYPE_11);
					account.setDescription(companyAccountForm
							.getAccountDescription());
					account.setStatus(companyAccountForm.getAccountStatus());
					account.setPaymentTool(paymentTool);
					long newAccountId = accountBiz.save(account);					
					if(newAccountId>0){		
						CompanyAccount companyAccount = new CompanyAccount();
						company=companyBiz.getCompanyById(companyId);
						companyAccount.setCompany(company);
						account=accountBiz.getAccountById(newAccountId);
						companyAccount.setAccount(account);
						companyAccount.setStatus(CompanyAccount.STATES_1);
						long newCompanyAccountId = companyAccountBiz.save(companyAccount);
						
						// --更新静态库
						KumsDataStoreListener listener = new KumsDataStoreListener(
								sysInitBiz, "Account");
						MainTask.put(listener);
						// ---------
						
						if (newCompanyAccountId > 0) {
							ActionRedirect redirect = new ActionRedirect(
									"/library/companyAccountList.do?thisAction=list");
							return redirect;
						} else {
							inf.setMessage("保存客户账号异常");
						}
					}else{
						inf.setMessage("账号保存异常");
					}					
				}else{
					inf.setMessage("支付工具ID不能为空");
				}
			}else{
				inf.setMessage("客户ID不能为空");
			}
		} catch (Exception e) {
			e.printStackTrace();
			inf.setMessage("增加客户账号："+e.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
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

	public void setCompanyBiz(CompanyBiz companyBiz) {
		this.companyBiz = companyBiz;
	}

	public void setCompanyAccountBiz(CompanyAccountBiz companyAccountBiz) {
		this.companyAccountBiz = companyAccountBiz;
	}

}
