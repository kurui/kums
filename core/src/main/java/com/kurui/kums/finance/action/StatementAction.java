package com.kurui.kums.finance.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.base.external.http.client.HttpInvoker;
import com.kurui.kums.base.struts.BaseAction;
import com.kurui.kums.base.ui.inform.Inform;
import com.kurui.kums.finance.FinanceOrder;
import com.kurui.kums.finance.Statement;
import com.kurui.kums.finance.biz.FinanceOrderBiz;
import com.kurui.kums.finance.biz.StatementBiz;
import com.kurui.kums.finance.util.FinanceOrderStore;
import com.kurui.kums.right.UserRightInfo;
import com.kurui.kums.system.OperateLog;
import com.kurui.kums.system.OperateLogDetail;
import com.kurui.kums.system.biz.OperateLogBiz;
import com.kurui.kums.system.biz.OperateLogDetailBiz;
import com.kurui.kums.transaction.biz.AccountBiz;

public class StatementAction extends BaseAction {
	private StatementBiz statementBiz;
	private AccountBiz accountBiz;
	private FinanceOrderBiz financeOrderBiz;
	private OperateLogBiz operateLogBiz;
	private OperateLogDetailBiz operateLogDetailBiz;

	public ActionForward confirmStatement(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws AppException {
		String temp = "";
		UserRightInfo uri = (UserRightInfo) request.getSession().getAttribute(
				"URI");
		Statement statement = (Statement) form;
		OperateLog operateLog = new OperateLog();
		Inform inf = new Inform();
		if (statement.getId() > 0) {
			Statement tempStatement = statementBiz.getStatementById(statement
					.getId());

			tempStatement.setTotalAmount(statement.getTotalAmount());
			tempStatement.setStatementTime(statement.getStatementTime());
			tempStatement.setSysUser(uri.getUser());
			tempStatement.setMemo(statement.getMemo());
			if (statement.getStatus() == Statement.STATUS_8) {
				temp = "已经确认了";
			} else {
				temp = "修改了";
			}

			OperateLogDetail detail = new OperateLogDetail();
			detail.setIp(HttpInvoker.getRemoteIP(request));// IP
			detail.setOrderNo(tempStatement.getStatementNo());

			if (tempStatement.getType() == Statement.TYPE_2) {// 支出
				tempStatement.setFromAccount(accountBiz
						.getAccountById(statement.getFromAccountId()));

				detail.setContent(temp + tempStatement.toLogString());
			} else if (tempStatement.getType() == Statement.TYPE_1) {
				tempStatement.setToAccount(accountBiz.getAccountById(statement
						.getToAccountId()));
				detail.setContent(temp + tempStatement.toLogString());
			}

			if (tempStatement.getStatus() != Statement.STATUS_8) {
				tempStatement.setStatus(statement.getStatus());
				statementBiz.update(tempStatement);

				operateLog.setOrderId(tempStatement.getOrderId());
				operateLog.setOrderType(Statement.ORDERTYPE_1201);
				operateLog.setSysUser(uri.getUser());
				operateLog
						.setOptTime(new Timestamp(System.currentTimeMillis()));
				operateLog.setType(OperateLog.TYPE_99);
				operateLog.setStatus(new Long(1));

				operateLogDetailBiz.saveOperateLogDetail(detail);

				operateLog.setOperateLogDetail(detail);

				operateLogBiz.saveOperateLog(operateLog);

				inf.setMessage("修改成功！");
			} else {
				inf.setMessage("您不能修改已经确认的单！");
			}

			// 回改Order信息
			FinanceOrder financeOrder = financeOrderBiz
					.getFinanceOrderById(tempStatement.getOrderId());
			financeOrder.setTotalAmount(tempStatement.getTotalAmount());
			financeOrder
					.setStatementAmount(tempStatement.getTotalAmount() + "");

			FinanceOrderStore.addOrderString(financeOrderBiz
					.getGroupIdByOrderId(tempStatement.getOrderId()));
			// ==================

			inf.setClose(true);
			inf
					.setForwardPage("/finance/financeOrderList.do?thisAction=view&id="
							+ tempStatement.getOrderId());

		} else {
			inf.setMessage("statementId不能为空");
		}
		return forwardInformPage(inf, mapping, request);
	}

	public void setStatementBiz(StatementBiz statementBiz) {
		this.statementBiz = statementBiz;
	}

	public void setOperateLogBiz(OperateLogBiz operateLogBiz) {
		this.operateLogBiz = operateLogBiz;
	}

	public void setAccountBiz(AccountBiz accountBiz) {
		this.accountBiz = accountBiz;
	}

	public void setfinanceOrderBiz(FinanceOrderBiz financeOrderBiz) {
		this.financeOrderBiz = financeOrderBiz;
	}

	public void setFinanceOrderBiz(FinanceOrderBiz financeOrderBiz) {
		this.financeOrderBiz = financeOrderBiz;
	}

	public void setOperateLogDetailBiz(OperateLogDetailBiz operateLogDetailBiz) {
		this.operateLogDetailBiz = operateLogDetailBiz;
	}

}