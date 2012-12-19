package com.kurui.kums.library.action;

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
import com.kurui.kums.right.UserRightInfo;
import com.kurui.kums.library.Account;
import com.kurui.kums.library.AccountCheck;
import com.kurui.kums.library.AccountCheckListForm;
import com.kurui.kums.library.biz.AccountBiz;
import com.kurui.kums.library.biz.AccountCheckBiz;

public class AccountCheckListAction extends BaseAction {
	private AccountBiz accountBiz;
	private AccountCheckBiz accountCheckBiz;

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		UserRightInfo uri = (UserRightInfo) request.getSession()
    .getAttribute("URI");
		AccountCheckListForm accountCheckListForm = (AccountCheckListForm) form;
		List<Account> accountList = accountBiz.getValidAccountList();
		request.setAttribute("accountList", accountList);

		if (accountCheckListForm == null) {
			accountCheckListForm = new AccountCheckListForm();
		}
		try {
			accountCheckListForm.setList(accountCheckBiz
					.list(accountCheckListForm,uri));
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("aclf", accountCheckListForm);
		return mapping.findForward("list");
	}

	// 导出
	public ActionForward downloadAccountCheck(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws AppException {
		UserRightInfo uri = (UserRightInfo) request.getSession()
    .getAttribute("URI");
		AccountCheckListForm alf = (AccountCheckListForm) form;
		if (alf != null) {

			ArrayList<ArrayList<Object>> lists = accountCheckBiz
					.getAccountCheckList(alf,uri);
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
			return mapping.findForward("list");
		}
	}

	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		AccountCheck accountCheckForm = (AccountCheck) form;
		try {
			long id = accountCheckForm.getId();
			if (id > 0) {
				AccountCheck accountCheck = accountCheckBiz
						.getAccountCheckById(id);
				request.setAttribute("accountCheck", accountCheck);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		forwardPage = "view";
		return mapping.findForward(forwardPage);

	}

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		AccountCheck accountCheck = new AccountCheck();

		accountCheck.setThisAction("checkOn");
		request.setAttribute("accountCheck", accountCheck);
		
		List<Account> accountList=accountBiz.getValidAccountListByTranType(Account.TRAN_TYPE_1+","+Account.TRAN_TYPE_3);
		request.setAttribute("accountList", accountList);
				
		String forwardPage = "edit";
		return mapping.findForward(forwardPage);
	}

	//编辑
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		AccountCheckListForm accountListForm = (AccountCheckListForm) form;

		long id = accountListForm.getSelectedItems()[0];
		if (id > 0) {
			AccountCheck accountCheck = accountCheckBiz.getAccountCheckById(id);
			accountCheck.setThisAction("update");
			request.setAttribute("accountCheck", accountCheck);
			
			List<Account> accountList=accountBiz.getValidAccountListByTranType(Account.TRAN_TYPE_1+","+Account.TRAN_TYPE_3);
			request.setAttribute("accountList", accountList);
		} else {
			request.setAttribute("accountCheck", new AccountCheck());
		}
		return mapping.findForward("edit");
	}
	
	// 下班
	public ActionForward checkOff(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		AccountCheckListForm accountCheckListForm = (AccountCheckListForm) form;

		long id = accountCheckListForm.getId();
		if (id > 0) {
			AccountCheck accountCheck = accountCheckBiz.getAccountCheckById(id);
			accountCheck.setThisAction("checkOff");
			request.setAttribute("accountCheck", accountCheck);
		} else {
			request.setAttribute("accountCheck", new AccountCheck());
		}
		return mapping.findForward("edit");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		AccountCheckListForm accountCheckListForm = (AccountCheckListForm) form;
		String forwardPage = "";
		Inform inf = new Inform();
		try {
			for (int i = 0; i < accountCheckListForm.getSelectedItems().length; i++) {
				long id = accountCheckListForm.getSelectedItems()[i];				
				if (id > 0){				
					accountCheckBiz.deleteAccountCheck(id);
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


	public void setAccountBiz(AccountBiz accountBiz) {
		this.accountBiz = accountBiz;
	}

	public void setAccountCheckBiz(AccountCheckBiz accountCheckBiz) {
		this.accountCheckBiz = accountCheckBiz;
	}
}