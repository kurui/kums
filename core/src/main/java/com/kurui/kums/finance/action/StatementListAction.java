package com.kurui.kums.finance.action;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.kurui.kums.base.struts.BaseAction;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.finance.biz.FinanceOrderBiz;
import com.kurui.kums.finance.biz.StatementBiz;
import com.kurui.kums.finance.FinanceOrder;
import com.kurui.kums.finance.Statement;
import com.kurui.kums.finance.StatementListForm;
import com.kurui.kums.transaction.Account;
import com.kurui.kums.transaction.util.PlatComAccountStore;

public class StatementListAction extends BaseAction {
	private StatementBiz statementBiz;
	private FinanceOrderBiz financeOrderBiz;

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		StatementListForm ulf = (StatementListForm) form;
		if (ulf == null) {
			ulf = new StatementListForm();
		}
		try {
			ulf.setList(statementBiz.list(ulf));
		} catch (Exception ex) {
			ulf.setList(new ArrayList<Statement>());
		}

		ulf.addSumField(1, "totalAmount");

		request.setAttribute("ulf", ulf);
		forwardPage = "listStatement";
		return (mapping.findForward(forwardPage));
	}

	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		StatementListForm slf = (StatementListForm) form;
		long statementId = slf.getId();
		if (statementId > 0) {
			Statement statement = statementBiz.getStatementById(statementId);
			request.setAttribute("statement", statement);
		}
		forwardPage = "viewStatement";
		return mapping.findForward(forwardPage);
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		StatementListForm slf = (StatementListForm) form;

		if (slf != null && slf.getId() > 0) {
			Statement statement = statementBiz.getStatementById(slf.getId());
			request.setAttribute("statement", statement);
			FinanceOrder ao = financeOrderBiz.getFinanceOrderById(statement
					.getOrderId());
			List<Account> accountList = new ArrayList<Account>();
			if (ao.getAccount() != null) {
				Long accountTranType = ao.getAccount().getTranType();
				accountList = PlatComAccountStore
						.getAccountListByType(accountTranType);
			}
			request.setAttribute("accountList", accountList);
		}
		return mapping.findForward("editStatement");
	}

	public void setStatementBiz(StatementBiz statementBiz) {
		this.statementBiz = statementBiz;
	}

	public void setfinanceOrderBiz(FinanceOrderBiz financeOrderBiz) {
		this.financeOrderBiz = financeOrderBiz;
	}
}