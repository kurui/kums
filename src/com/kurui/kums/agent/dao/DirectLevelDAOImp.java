package com.kurui.kums.agent.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import com.kurui.kums.agent.DirectLevel;
import com.kurui.kums.agent.DirectLevelListForm;
import com.kurui.kums.base.database.BaseDAOSupport;
import com.kurui.kums.base.database.Hql;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.base.Constant;

public class DirectLevelDAOImp extends BaseDAOSupport implements DirectLevelDAO {

	public List list(DirectLevelListForm DirectLevelListForm)
			throws AppException {
		Hql hql = new Hql();
		hql.add("from DirectLevel a where 1=1");
		if (Constant.toLong(DirectLevelListForm.getType()) > 0) {
			hql.add(" and a.type=" + DirectLevelListForm.getType());
		}

		hql.add(" and a.status=" + DirectLevel.STATES_1);
		
		hql.add(" order by a.type,a.seqIndex");
		
		return this.list(hql, DirectLevelListForm);
	}

	public void delete(long id) throws AppException {
		if (id > 0) {
			DirectLevel directLevel = (DirectLevel) this.getHibernateTemplate().get(
					DirectLevel.class, new Long(id));
			this.getHibernateTemplate().delete(directLevel);
		}
	}

	public long save(DirectLevel directLevel) throws AppException {
		this.getHibernateTemplate().save(directLevel);
		return directLevel.getId();
	}

	public long update(DirectLevel directLevel) throws AppException {
		if (directLevel.getId() > 0) {
			this.getHibernateTemplate().update(directLevel);
			return directLevel.getId();
		} else
			throw new IllegalArgumentException("id isn't a valid argument.");
	}

	public DirectLevel getDirectLevelById(long id) throws AppException {
		Hql hql = new Hql();
		hql.add("from DirectLevel a where a.id=" + id);
		Query query = this.getQuery(hql);
		DirectLevel directLevel = null;
		if (query != null && query.list() != null && query.list().size() > 0) {
			directLevel = (DirectLevel) query.list().get(0);
		}
		return directLevel;
	}

	public List<DirectLevel> getDirectLevelList() throws AppException {
		List<DirectLevel> list = new ArrayList<DirectLevel>();
		Hql hql = new Hql();
		hql.add("from DirectLevel");
		Query query = this.getQuery(hql);
		if (query != null) {
			list = query.list();
			if (list != null && list.size() > 0) {
				return list;
			}
		}
		return list;
	}

	public List<DirectLevel> getDirectLevelList(Long type) throws AppException {
		List<DirectLevel> list = new ArrayList<DirectLevel>();
		Hql hql = new Hql();
		hql.add("from DirectLevel a where a.type=" + type);
		Query query = this.getQuery(hql);
		if (query != null) {
			list = query.list();
			if (list != null && list.size() > 0) {
				return list;
			}
		}
		return list;
	}

	public List<DirectLevel> getValidDirectLevelList() throws AppException {
		List<DirectLevel> list = new ArrayList<DirectLevel>();
		Hql hql = new Hql();
		hql.add("from DirectLevel a where 1=1 ");
		// hql.add("and a.status= "+ DirectLevel.STATES_1);
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
