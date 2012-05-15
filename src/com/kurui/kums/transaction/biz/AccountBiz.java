package com.kurui.kums.transaction.biz;

import java.util.ArrayList;
import java.util.List;

import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.transaction.Account;
import com.kurui.kums.transaction.AccountListForm;

public interface AccountBiz {

	public List list(AccountListForm accountListForm) throws AppException;

	public long delete(long id) throws AppException;

	public void deleteAccount(Long accountId) throws AppException;

	public long save(Account account) throws AppException;

	public long update(Account account) throws AppException;

	public Account getAccountById(long accountId) throws AppException;

	public List<Account> getAccountList() throws AppException;

	public List<Account> getValidAccountList() throws AppException;

	public List<Account> getValidAccountListByTranType(String tranType)
			throws AppException;

	public List<Account> getAccountListByPaymentToolId(long paymentToolId);

	public ArrayList<ArrayList<Object>> getAccountBalanceList(
			AccountListForm alf) throws AppException;

}
