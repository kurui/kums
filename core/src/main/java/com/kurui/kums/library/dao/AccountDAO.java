package com.kurui.kums.library.dao;

import java.util.List;

import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.library.Account;
import com.kurui.kums.library.AccountListForm;

public interface AccountDAO {
	public List list(AccountListForm accountListForm) throws AppException;

	public void delete(long id) throws AppException;

	public long save(Account account) throws AppException;

	public long update(Account account) throws AppException;

	public Account getAccountById(long accountId) throws AppException;

	public List<Account> getAccountList() throws AppException;

	public List<Account> getValidAccountList() throws AppException;

	public List<Account> getValidAccountListByTranType(String tranType)
			throws AppException;

	public List<Account> getAccountListByPaymentToolId(long paymentToolId);
}
