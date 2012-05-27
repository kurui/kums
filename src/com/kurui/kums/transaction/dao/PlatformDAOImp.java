package com.kurui.kums.transaction.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;

import com.kurui.kums.base.database.BaseDAOSupport;
import com.kurui.kums.base.database.Hql;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.transaction.Platform;
import com.kurui.kums.transaction.PlatformListForm;

public class PlatformDAOImp extends BaseDAOSupport implements PlatformDAO {

	public List list(PlatformListForm platformListForm) throws AppException {
		Hql hql = new Hql();
		hql.add("from Platform p where 1=1");
		if (platformListForm.getName() != null
				&& (!(platformListForm.getName().equals("")))) {
			hql.add(" and p.name like '%" + platformListForm.getName().trim()
					+ "%'");
		}
		if (platformListForm.getType() != null
				&& (!(platformListForm.getType().equals("")))) {
			hql.add(" and p.type=" + platformListForm.getType());
		}
		if (platformListForm.getDrawType() != null
				&& (!(platformListForm.getDrawType().equals("")))) {
			hql.add(" and p.drawType=" + platformListForm.getDrawType());
		}

		hql.add("and p.status not in(" + Platform.STATES_1 + ")");// 过滤无效
		hql.add(" order by p.name");
		return this.list(hql, platformListForm);
	}

	public void delete(long id) throws AppException {
		if (id > 0) {
			Platform platform = (Platform) this.getHibernateTemplate().get(
					Platform.class, new Long(id));
			this.getHibernateTemplate().delete(platform);
		}
	}
	


	public long save(Platform platform) throws AppException {
		this.getHibernateTemplate().save(platform);
		return platform.getId();
	}

	public long update(Platform platform) throws AppException {
		if (platform.getId() > 0) {
			this.getHibernateTemplate().update(platform);
			return platform.getId();
		} else
			throw new IllegalArgumentException("id isn't a valid argument.");
	}

	public Platform getPlatformById(long platformId) throws AppException {
		Hql hql = new Hql();
		hql.add("from Platform p where p.id=" + platformId);
		Query query = this.getQuery(hql);
		Platform platform = null;
		if (query != null && query.list() != null && query.list().size() > 0) {
			platform = (Platform) query.list().get(0);
		}
		return platform;
	}

	public List<Platform> getPlatformList() throws AppException {
		List<Platform> list = new ArrayList<Platform>();
		Hql hql = new Hql();
		hql.add(" from Platform p ");
		hql.add(" order by p.name ");
		Query query = this.getQuery(hql);
		if (query != null) {
			list = query.list();
			if (list != null && list.size() > 0) {
				return list;
			}
		}
		return list;
	}

	public List<Platform> getValidPlatformList() throws AppException {
		List<Platform> list = new ArrayList<Platform>();
		Hql hql = new Hql();
		hql.add(" from Platform p where 1=1 and p.status=0 ");
		hql.add(" order by p.name ");
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
