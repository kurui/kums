package com.kurui.kums.library.biz;

import java.util.List;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.library.Company;
import com.kurui.kums.library.CompanyListForm;
import com.kurui.kums.library.dao.CompanyDAO;

public class CompanyBizImp implements CompanyBiz {
	private CompanyDAO companyDAO;

	public List list(CompanyListForm form) throws AppException {
		return companyDAO.list(form);
	}

	public long delete(long id) throws AppException {
		try {
			companyDAO.delete(id);
			return 1;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	public void deleteCompany(Long companyId) throws AppException {
		Company company = getCompanyById(companyId);
		company.setStatus(Company.STATES_0);// 将状态变为无效
		update(company);
	}

	public long save(Company Company) throws AppException {
		return companyDAO.save(Company);
	}

	public long update(Company Company) throws AppException {
		return companyDAO.update(Company);
	}

	public Company getCompanyById(long CompanyId) throws AppException {
		return companyDAO.getCompanyById(CompanyId);
	}

	public List<Company> getValidCompanyList() throws AppException {
		return companyDAO.getValidCompanyList();
	}

	public List<Company> getCompanyListByProvideChain(String provideChain)
			throws AppException {
		return companyDAO.getCompanyListByProvideChain(provideChain);
	}

	public List<Company> getCompanyList(String types, String status)
			throws AppException {
		return companyDAO.getCompanyList(types, status);
	}

	public void setCompanyDAO(CompanyDAO companyDAO) {
		this.companyDAO = companyDAO;
	}

}
