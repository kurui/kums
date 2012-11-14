package com.kurui.kums.transaction.action;

import java.sql.Timestamp;

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
import com.kurui.kums.right.UserRightInfo;
import com.kurui.kums.system.biz.SysInitBiz;
import com.kurui.kums.transaction.Account;
import com.kurui.kums.transaction.Company;
import com.kurui.kums.transaction.PlatComAccount;
import com.kurui.kums.transaction.Platform;
import com.kurui.kums.transaction.biz.AccountBiz;
import com.kurui.kums.transaction.biz.CompanyBiz;

import com.kurui.kums.transaction.biz.PlatComAccountBiz;
import com.kurui.kums.transaction.biz.PlatformBiz;

public class PlatComAccountAction extends BaseAction {
	private PlatComAccountBiz platComAccountBiz;
	private CompanyBiz companyBiz;
	private PlatformBiz platformBiz;
	private AccountBiz accountBiz;
	private SysInitBiz sysInitBiz;

	public ActionForward insert(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		UserRightInfo uri = (UserRightInfo) request.getSession().getAttribute(
				"URI");
		PlatComAccount platComAccount = (PlatComAccount) form;
		Inform inf = new Inform();
		try {
			if (platComAccount.getAccountId() > 0
					&& platComAccount.getPlatformId() > 0
					&& platComAccount.getCompanyId() > 0) {
				PlatComAccount pComAccount = new PlatComAccount();
				Account account = accountBiz.getAccountById(platComAccount
						.getAccountId());
				Company company = companyBiz.getCompanyById(platComAccount
						.getCompanyId());
				Platform platform = platformBiz.getPlatformById(platComAccount
						.getPlatformId());

				pComAccount.setStatus(platComAccount.getStatus());
				pComAccount.setType(platComAccount.getType());
				pComAccount.setAccount(account);// 面象对象形式添加
				pComAccount.setCompany(company);
				pComAccount.setPlatform(platform);
				pComAccount.setUpdateTime(new Timestamp(System
						.currentTimeMillis()));
				pComAccount.setUserName(uri.getUser().getUserName());
				long num = platComAccountBiz.save(pComAccount);
				// --更新静态库
				KumsDataStoreListener listener = new KumsDataStoreListener(
						sysInitBiz, "PlatComAccount");
				MainTask.put(listener);
				// -------------------------
				if (num > 0) {
					return new ActionRedirect(
							"/transaction/platComAccountList.do?thisAction=list");
				} else {
					inf.setMessage("您添加平台账号据失败！");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			inf.setMessage("异常:" + e.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		UserRightInfo uri = (UserRightInfo) request.getSession().getAttribute(
				"URI");
		PlatComAccount platComAccount = (PlatComAccount) form;
		Inform inf = new Inform();
		try {
			if (platComAccount.getId() > 0) {
				if (platComAccount.getAccountId() > 0
						&& platComAccount.getPlatformId() > 0
						&& platComAccount.getCompanyId() > 0) {
					PlatComAccount pComAccount = platComAccountBiz
							.getPlatComAccountById(platComAccount.getId());
					Account account = accountBiz.getAccountById(platComAccount
							.getAccountId());
					Company company = companyBiz.getCompanyById(platComAccount
							.getCompanyId());
					Platform platform = platformBiz
							.getPlatformById(platComAccount.getPlatformId());
					pComAccount.setStatus(platComAccount.getStatus());
					pComAccount.setType(platComAccount.getType());

					pComAccount.setAccount(account);
					pComAccount.setCompany(company);
					pComAccount.setPlatform(platform);
					pComAccount.setUpdateTime(new Timestamp(System
							.currentTimeMillis()));
					pComAccount.setUserName(uri.getUser().getUserName());
					long flag = platComAccountBiz.update(pComAccount);
					// --更新静态库
					KumsDataStoreListener listener = new KumsDataStoreListener(
							sysInitBiz, "PlatComAccount");
					MainTask.put(listener);
					//
					if (flag > 0) {
						return new ActionRedirect(
								"/transaction/platComAccountList.do?thisAction=list");
					} else {
						inf.setMessage("您修改平台账号失败！");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			inf.setMessage("异常:" + e.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}

	public void setSysInitBiz(SysInitBiz sysInitBiz) {
		this.sysInitBiz = sysInitBiz;
	}

	public void setPlatformBiz(PlatformBiz platformBiz) {
		this.platformBiz = platformBiz;
	}

	public void setPlatComAccountBiz(PlatComAccountBiz platComAccountBiz) {
		this.platComAccountBiz = platComAccountBiz;
	}

	public void setCompanyBiz(CompanyBiz companyBiz) {
		this.companyBiz = companyBiz;
	}

	public void setAccountBiz(AccountBiz accountBiz) {
		this.accountBiz = accountBiz;
	}
}