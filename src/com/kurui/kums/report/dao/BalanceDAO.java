package com.kurui.kums.report.dao;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import com.kurui.kums.report.Balance;
import com.kurui.kums.report.BalanceListForm;
import com.kurui.kums.base.exception.AppException;

public interface BalanceDAO {

	public void createBalance(BalanceListForm balanceListForm,
			HttpServletRequest request);

	public List list(BalanceListForm balanceListForm, HttpServletRequest request)
			throws AppException;

	public String getItemAmountBySuperItem(String itemKey) throws AppException;

	public String getItemAmount(String itemKey) throws AppException;

	public void delete(long id) throws AppException;

	public long save(Balance balance) throws AppException;

	public long update(Balance balance) throws AppException;

	public Balance getBalanceById(long id) throws AppException;

	public List<Balance> getBalanceList() throws AppException;

	public List<Balance> getBalanceList(Long type) throws AppException;

	public List<Balance> getValidBalanceList() throws AppException;
}
