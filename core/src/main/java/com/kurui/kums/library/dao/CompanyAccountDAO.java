package com.kurui.kums.library.dao;

import java.util.List;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.library.CompanyAccount;
import com.kurui.kums.library.CompanyAccountListForm;

public interface CompanyAccountDAO {
	public List list(CompanyAccountListForm companyListForm)
			throws AppException;

	public void delete(long id) throws AppException;

	public long save(CompanyAccount companyAccount) throws AppException;

	public long update(CompanyAccount companyAccount) throws AppException;
	
	public CompanyAccount getCompanyAccountById(long id) throws AppException;

	public CompanyAccount getCompanyAccountByCompanyId(long companyId)
			throws AppException;

	public CompanyAccount getCompanyAccountByAccountId(long accountId)
			throws AppException;

	public List<CompanyAccount> getCompanyAccountList() throws AppException;

	public List<CompanyAccount> getCompanyAccountList(Long type)
			throws AppException;

	public List<CompanyAccount> getValidCompanyAccountList()
			throws AppException;
}
