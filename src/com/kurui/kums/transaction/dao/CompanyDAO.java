package com.kurui.kums.transaction.dao;

import java.util.List;

import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.transaction.Company;
import com.kurui.kums.transaction.CompanyListForm;

public interface CompanyDAO {

	public List list(CompanyListForm companyForm) throws AppException;

	public void delete(long id) throws AppException;

	public long save(Company company) throws AppException;

	public long update(Company company) throws AppException;

	public Company getCompanyById(long companyId) throws AppException;

	public List<Company> getCompanyListByProvideChain(String provideChain)
			throws AppException;

	public List<Company> getValidCompanyList() throws AppException;

	public List<Company> getCompanyList(String types, String status)
			throws AppException;
}
