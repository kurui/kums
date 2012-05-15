package com.kurui.kums.finance.biz;

import java.util.List;

import com.kurui.kums.finance.Statement;
import com.kurui.kums.finance.StatementListForm;
import com.kurui.kums.finance.dao.FinanceOrderDAO;
import com.kurui.kums.finance.dao.StatementDAO;
import com.kurui.kums.finance.util.FinanceOrderStore;
import com.kurui.kums.base.Constant;
import com.kurui.kums.base.util.StringUtil;
import com.kurui.kums.base.exception.AppException;

public class StatementBizImp implements StatementBiz {
	private StatementDAO statementDAO;
	private FinanceOrderDAO financeOrderDAO;

	public void excuteSynOrderStatement() {
		try {
			String[] orderGroupIds = StringUtil.getSplitString(
					FinanceOrderStore.orderGroupIdString, ",");
			for (int i = 0; i < orderGroupIds.length; i++) {
				long orderGroupId = Constant.toLong(orderGroupIds[i]);
				if (orderGroupId > 0) {
					List orderIds = financeOrderDAO
							.listIDByGroupId(orderGroupId);
					for (int j = 0; j < orderIds.size(); j++) {
						Long orderId = (Long) orderIds.get(j);
						if (orderId > 0) {
							synStatementAmount(orderId);
							synOldStatementAmount(orderId);
						}
					}
					FinanceOrderStore.removeOrderId(orderGroupId);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void synOldStatementAmount(long orderId) throws AppException {
		statementDAO.synOldStatementAmount(orderId);
	}

	public void synStatementAmount(long orderId) {
		statementDAO.synStatementAmount(orderId);
	}

	public List getStatementListByOrderSubType(long orderid, long orderSubtype,
			long orderTable) throws AppException {
		return statementDAO.getStatementListByOrderSubType(orderid,
				orderSubtype, orderTable);
	}

	public List getStatementListByOrder(long orderid, long ordertable)
			throws AppException {
		return statementDAO.getStatementListByOrder(orderid, ordertable);
	}

	public List list(StatementListForm rlf) throws AppException {
		return statementDAO.list(rlf);
	}

	public List list() throws AppException {
		return statementDAO.list();
	}

	public void delete(long id) throws AppException {
		statementDAO.delete(id);
	}

	public long save(Statement statement) throws AppException {
		return statementDAO.save(statement);
	}

	public long update(Statement statement) throws AppException {
		return statementDAO.update(statement);
	}

	public Statement getStatementById(long id) throws AppException {
		return statementDAO.getStatementById(id);
	}

	public void setfinanceOrderDAO(FinanceOrderDAO financeOrderDAO) {
		this.financeOrderDAO = financeOrderDAO;
	}

	public void setStatementDAO(StatementDAO statementDAO) {
		this.statementDAO = statementDAO;
	}
}
