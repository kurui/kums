package com.kurui.kums.report.dao;

import java.util.List;

import com.kurui.kums.base.database.hibernate.BaseDAO;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.finance.FinanceOrder;
import com.kurui.kums.report.BusinessReport;

public interface ReportDAO  {
	public List<FinanceOrder> getOrderStatementList(BusinessReport report)
			throws AppException;
}
