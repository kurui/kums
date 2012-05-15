package com.kurui.kums.report.dao;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import com.kurui.kums.report.CashFlow;
import com.kurui.kums.report.CashFlowListForm;
import com.kurui.kums.base.exception.AppException;

public interface CashFlowDAO {

	public void createCashFlow(CashFlowListForm cashFlowListForm,
			HttpServletRequest request);

	public List list(CashFlowListForm cashFlowListForm,
			HttpServletRequest request) throws AppException;

	public void delete(long id) throws AppException;

	public long save(CashFlow cashFlow) throws AppException;

	public long update(CashFlow cashFlow) throws AppException;

	public CashFlow getCashFlowById(long id) throws AppException;

	public List<CashFlow> getCashFlowList() throws AppException;

	public List<CashFlow> getCashFlowList(Long type) throws AppException;

	public List<CashFlow> getValidCashFlowList() throws AppException;
}
