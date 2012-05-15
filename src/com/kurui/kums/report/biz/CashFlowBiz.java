package com.kurui.kums.report.biz;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.kurui.kums.report.CashFlow;
import com.kurui.kums.report.CashFlowListForm;
import com.kurui.kums.base.exception.AppException;

public interface CashFlowBiz {

	public void createCashFlow(CashFlowListForm cashFlowListForm,
			HttpServletRequest request);

	public List list(CashFlowListForm cashFlowListForm,
			HttpServletRequest request) throws AppException;

	public long delete(long id) throws AppException;

	public void deleteCashFlow(Long id) throws AppException;

	public long save(CashFlow cashFlow) throws AppException;

	public long update(CashFlow creditReport) throws AppException;

	public CashFlow getCashFlowById(long id) throws AppException;

	public List<CashFlow> getCashFlowList() throws AppException;

	public List<CashFlow> getCashFlowList(Long type) throws AppException;

}
