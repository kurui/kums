package com.kurui.kums.transaction.biz;

import java.util.List;
import com.kurui.kums.transaction.CompanyAccount;
import com.kurui.kums.transaction.CompanyAccountListForm;
import com.kurui.kums.base.exception.AppException;

public interface CompanyAccountBiz {
	public List list(CompanyAccountListForm companyAccountListForm)
			throws AppException;

	public void delete(long id) throws AppException;

	public void deleteCompanyAccount(Long companyAccountId) throws AppException;

	public long save(CompanyAccount companyAccount) throws AppException;

	public long update(CompanyAccount companyAccount) throws AppException;

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
