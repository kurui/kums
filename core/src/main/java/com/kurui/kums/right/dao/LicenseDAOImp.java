package com.kurui.kums.right.dao;

import java.util.List;

import org.hibernate.Query;

import com.kurui.kums.base.database.BaseDAOSupport;
import com.kurui.kums.base.database.Hql;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.right.License;
import com.kurui.kums.right.LicenseListForm;

public class LicenseDAOImp extends BaseDAOSupport implements LicenseDAO {

	public List getLicense(LicenseListForm clf) throws AppException {
		Hql hql = new Hql();
		hql.add("from License n where 1=1");
		

		hql.add(" order by n.updateTime desc");
//		System.out.println("hql===" + hql);
		return this.list(hql, clf);
	}

	public License getLicenseById(long id) throws AppException {
		Hql hql = new Hql();
		hql.add("from License where id=?");
		hql.addParamter(id);
//		System.out.println("HQL = " + hql);
		Query query = this.getQuery(hql);
		if (query != null && query.list() != null && query.list().size() > 0)
			return (License) query.list().get(0);
		else
			return new License();
	}

	public long updateInfo(License license) throws AppException {
		if (license.getId() > 0)
			return save(license);
		else
			throw new IllegalArgumentException("id isn't a valid argument.");
	}

	public long save(License license) throws AppException {
		this.getHibernateTemplate().saveOrUpdate(license);
		return license.getId();
	}

	public void deleteLicenseById(int id) throws AppException {
		this.getHibernateTemplate().delete(this.getLicenseById(id));
	}
}
