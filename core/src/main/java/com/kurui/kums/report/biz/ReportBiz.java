package com.kurui.kums.report.biz;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.finance.FinanceOrder;
import com.kurui.kums.finance.FinanceOrderListForm;
import com.kurui.kums.right.UserRightInfo;

public interface ReportBiz {
	public HttpServletRequest setLiveChartList(List<FinanceOrder> orderList,
			FinanceOrderListForm ulf, HttpServletRequest request,
			HttpServletResponse response) throws AppException;

	public void exportLiveReport(List<FinanceOrder> orderList)
			throws AppException;
}
