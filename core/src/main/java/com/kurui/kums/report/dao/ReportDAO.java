package com.kurui.kums.report.dao;

import java.util.List;

import com.kurui.kums.base.database.BaseDAO;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.finance.FinanceOrder;
import com.kurui.kums.report.BusinessReport;

public interface ReportDAO extends BaseDAO {
	public List<FinanceOrder> getOrderStatementList(BusinessReport report)
			throws AppException;
}
