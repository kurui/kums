package com.kurui.kums.transaction.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;

import com.kurui.kums.base.database.hibernate.BaseDAOSupport;
import com.kurui.kums.base.database.hibernate.Hql;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.transaction.PlatComAccount;
import com.kurui.kums.transaction.PlatComAccountListForm;

public class PlatComAccountDAOImp extends BaseDAOSupport implements
		PlatComAccountDAO {

	public List list(PlatComAccountListForm platComAccountForm)
			throws AppException {
		Hql hql = new Hql();
		hql.add("from PlatComAccount p where 1=1");
		if (platComAccountForm.getCompanyName() != null
				&& (!(platComAccountForm.getCompanyName().equals("")))) {
			hql.add(" and p.company.id=" + platComAccountForm.getCompanyName());
		}
		if (platComAccountForm.getPlatformName() != null
				&& (!(platComAccountForm.getPlatformName().equals("")))) {
			hql.add(" and p.platform.id="
					+ platComAccountForm.getPlatformName());
		}
		if (platComAccountForm.getAccountName() != null
				&& (!(platComAccountForm.getAccountName().equals("")))) {
			hql.add(" and p.account.id=" + platComAccountForm.getAccountName());
		}

		hql.add("and p.status not in(" + PlatComAccount.STATES_0 + ")");// 过滤无效
		hql.add(" order by p.platform.name,p.company.name,p.type,p.id");
		return this.list(hql, platComAccountForm);
	}

	public void delete(long id) throws AppException {
		if (id > 0) {
			PlatComAccount platComAccount = (PlatComAccount) this
					.getHibernateTemplate().get(PlatComAccount.class,
							new Long(id));
			this.getHibernateTemplate().delete(platComAccount);
		}
	}
	


	public long save(PlatComAccount platComAccount) throws AppException {
		this.getHibernateTemplate().save(platComAccount);
		return platComAccount.getId();
	}

	public long update(PlatComAccount platComAccount) throws AppException {
		if (platComAccount.getId() > 0) {
			this.getHibernateTemplate().update(platComAccount);
			return platComAccount.getId();
		} else
			throw new IllegalArgumentException("id isn't a valid argument.");
	}

	public PlatComAccount getPlatComAccountById(long platComAccountId)
			throws AppException {
		Hql hql = new Hql();
		hql.add("from PlatComAccount p where p.id=" + platComAccountId);
		Query query = this.getQuery(hql);
		PlatComAccount platComAccount = null;
		if (query != null && query.list() != null&&query.list().size()>0) {
			platComAccount = (PlatComAccount) query.list().get(0);
		}
		return platComAccount;
	}

	public List<PlatComAccount> getPlatComAccountByPlatformId(long platformId)
			throws AppException {
		List<PlatComAccount> list = new ArrayList<PlatComAccount>();
		Hql hql = new Hql();
		hql.add("from PlatComAccount p where p.platform.id=" + platformId);
		Query query = this.getQuery(hql);
		PlatComAccount platComAccount = null;
		if (query != null && query.list() != null && query.list().size() > 0) {
			list = query.list();
		}
		return list;
	}

	public List<PlatComAccount> getPlatComAccountList() throws AppException {
		List<PlatComAccount> list = new ArrayList<PlatComAccount>();
		Hql hql = new Hql();
		hql.add("from PlatComAccount p where 1=1 ");
		Query query = this.getQuery(hql);
		if (query != null) {
			list = query.list();
			if (list != null && list.size() > 0) {
				return list;
			}
		}
		return list;
	}

	public List<PlatComAccount> getValidPlatComAccountList()
			throws AppException {
		List<PlatComAccount> list = new ArrayList<PlatComAccount>();
		Hql hql = new Hql();
		hql.add("from PlatComAccount p where 1=1 and p.status="
				+ PlatComAccount.STATES_1);
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
