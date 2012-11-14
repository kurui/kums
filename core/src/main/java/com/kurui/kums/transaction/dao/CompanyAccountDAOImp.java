package com.kurui.kums.transaction.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import com.kurui.kums.transaction.CompanyAccount;
import com.kurui.kums.transaction.CompanyAccountListForm;
import com.kurui.kums.agent.AgentAccount;
import com.kurui.kums.base.database.hibernate.BaseDAOSupport;
import com.kurui.kums.base.database.hibernate.Hql;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.base.Constant;

public class CompanyAccountDAOImp extends BaseDAOSupport implements
		CompanyAccountDAO {

	public List list(CompanyAccountListForm companyAccountListForm)
			throws AppException {
		Hql hql = new Hql();
		hql.add("from CompanyAccount a where 1=1");
		if (Constant.toLong(companyAccountListForm.getCompanyId()) > 0) {
			hql.add(" and a.company.id="
					+ companyAccountListForm.getCompanyId());
		}

		if (Constant.toLong(companyAccountListForm.getAccountId()) > 0) {
			hql.add(" and a.account.id="
					+ companyAccountListForm.getAccountId());
		}
		hql.add(" and a.status="+AgentAccount.STATES_1);
		return this.list(hql, companyAccountListForm);
	}

	public void delete(long id) throws AppException {
		if (id > 0) {
			CompanyAccount company = (CompanyAccount) this
					.getHibernateTemplate().get(CompanyAccount.class,
							new Long(id));
			this.getHibernateTemplate().delete(company);
		}
	}

	public long save(CompanyAccount company) throws AppException {
		this.getHibernateTemplate().save(company);
		return company.getId();
	}

	public long update(CompanyAccount company) throws AppException {
		if (company.getId() > 0) {
			this.getHibernateTemplate().update(company);
			return company.getId();
		} else
			throw new IllegalArgumentException("id isn't a valid argument.");
	}

	public CompanyAccount getCompanyAccountById(long id) throws AppException {
		Hql hql = new Hql();
		hql.add("from CompanyAccount a where a.id=" + id);
		Query query = this.getQuery(hql);
		CompanyAccount company = null;
		if (query != null && query.list() != null && query.list().size() > 0) {
			company = (CompanyAccount) query.list().get(0);
		}
		return company;
	}

	public CompanyAccount getCompanyAccountByCompanyId(long companyId)
			throws AppException {
		Hql hql = new Hql();
		hql.add("from CompanyAccount a where a.company.id=" + companyId);
		Query query = this.getQuery(hql);
		CompanyAccount company = null;
		if (query != null && query.list() != null && query.list().size() > 0) {
			company = (CompanyAccount) query.list().get(0);
		}
		return company;
	}

	public CompanyAccount getCompanyAccountByAccountId(long accountId)
			throws AppException {
		Hql hql = new Hql();
		hql.add("from CompanyAccount a where a.account.id=" + accountId);
		Query query = this.getQuery(hql);
		CompanyAccount companyAccount = null;
		if (query != null && query.list() != null && query.list().size() > 0) {
			companyAccount = (CompanyAccount) query.list().get(0);
		}
		return companyAccount;
	}

	public List<CompanyAccount> getCompanyAccountList() throws AppException {
		List<CompanyAccount> list = new ArrayList<CompanyAccount>();
		Hql hql = new Hql();
		hql.add("from CompanyAccount");
		Query query = this.getQuery(hql);
		if (query != null) {
			list = query.list();
			if (list != null && list.size() > 0) {
				return list;
			}
		}
		return list;
	}

	public List<CompanyAccount> getCompanyAccountList(Long type)
			throws AppException {
		List<CompanyAccount> list = new ArrayList<CompanyAccount>();
		Hql hql = new Hql();
		hql.add("from CompanyAccount a where a.type=" + type);
		Query query = this.getQuery(hql);
		if (query != null) {
			list = query.list();
			if (list != null && list.size() > 0) {
				return list;
			}
		}
		return list;
	}

	public List<CompanyAccount> getValidCompanyAccountList()
			throws AppException {
		List<CompanyAccount> list = new ArrayList<CompanyAccount>();
		Hql hql = new Hql();
		hql.add("from CompanyAccount a where 1=1 ");
		// hql.add("and a.status= "+ CompanyAccount.STATES_1);
		// hql.add(" order by a.name ");
		Query query = this.getQuery(hql);
		if (query != null) {
			list = query.list();
			if (list != null && list.size() > 0) {
				return list;
			}
		}
		return list;
	}

}
