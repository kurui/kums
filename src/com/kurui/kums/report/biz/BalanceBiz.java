package com.kurui.kums.report.biz;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.kurui.kums.report.Balance;
import com.kurui.kums.report.BalanceListForm;
import com.kurui.kums.base.exception.AppException;

public interface BalanceBiz {

	public void createBalance(BalanceListForm balanceListForm,
			HttpServletRequest request);

	public void listEquity(BalanceListForm balanceListForm,
			HttpServletRequest request) throws AppException;

	public List<Balance> listEarnings(BalanceListForm balanceListForm,
			HttpServletRequest request) throws AppException;

	public List list(BalanceListForm balanceListForm, HttpServletRequest request)
			throws AppException;

	public long delete(long id) throws AppException;

	public void deleteBalance(Long id) throws AppException;

	public long save(Balance balance) throws AppException;

	public long update(Balance creditReport) throws AppException;

	public Balance getBalanceById(long id) throws AppException;

	public List<Balance> getBalanceList() throws AppException;

	public List<Balance> getBalanceList(Long type) throws AppException;

}
