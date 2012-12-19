package com.kurui.kums.library.biz;

import java.util.List;
import com.kurui.kums.library.CompanyAccount;
import com.kurui.kums.library.CompanyAccountListForm;
import com.kurui.kums.library.dao.CompanyAccountDAO;
import com.kurui.kums.base.exception.AppException;

public class CompanyAccountBizImp implements CompanyAccountBiz {
	private CompanyAccountDAO companyAccountDAO;

	public List list(CompanyAccountListForm companyAccountListForm)
			throws AppException {
		return companyAccountDAO.list(companyAccountListForm);
	}

	public void delete(long id) throws AppException {
		companyAccountDAO.delete(id);
	}

	public void deleteCompanyAccount(Long companyAccountId) throws AppException {
		CompanyAccount companyAccount = getCompanyAccountById(companyAccountId);
		companyAccount.setStatus(CompanyAccount.STATES_0);// 将状态变为无效
		update(companyAccount);
	}

	public long save(CompanyAccount companyAccount) throws AppException {
		return companyAccountDAO.save(companyAccount);
	}

	public long update(CompanyAccount companyAccount) throws AppException {
		return companyAccountDAO.update(companyAccount);
	}

	public CompanyAccount getCompanyAccountById(long id) throws AppException {
		return companyAccountDAO.getCompanyAccountById(id);
	}

	public CompanyAccount getCompanyAccountByCompanyId(long companyId)
			throws AppException {
		return companyAccountDAO.getCompanyAccountByCompanyId(companyId);
	}

	public CompanyAccount getCompanyAccountByAccountId(long accountId)
			throws AppException {
		return companyAccountDAO.getCompanyAccountByAccountId(accountId);
	}

	public List<CompanyAccount> getCompanyAccountList() throws AppException {
		return companyAccountDAO.getCompanyAccountList();
	}

	public List<CompanyAccount> getCompanyAccountList(Long type)
			throws AppException {
		return companyAccountDAO.getCompanyAccountList(type);
	}

	public List<CompanyAccount> getValidCompanyAccountList()
			throws AppException {
		return companyAccountDAO.getValidCompanyAccountList();
	}

	public void setCompanyAccountDAO(CompanyAccountDAO companyAccountDAO) {
		this.companyAccountDAO = companyAccountDAO;
	}

}
