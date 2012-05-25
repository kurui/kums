package com.kurui.kums.finance.biz;

import java.util.List;

import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.finance.Statement;
import com.kurui.kums.finance.StatementListForm;

public interface StatementBiz {
	public void excuteSynOrderStatement() throws AppException;

	public List list(StatementListForm rlf) throws AppException;

	public List list() throws AppException;

	public List getStatementListByOrder(long orderid, long ordertype)
			throws AppException;

	public void delete(long id) throws AppException;

	public long save(Statement statement) throws AppException;

	public long update(Statement statement) throws AppException;

	public Statement getStatementById(long id) throws AppException;
}
