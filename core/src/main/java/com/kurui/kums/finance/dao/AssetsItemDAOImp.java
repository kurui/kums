package com.kurui.kums.finance.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;

import com.kurui.kums.base.database.BaseDAOSupport;
import com.kurui.kums.base.database.Hql;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.base.util.StringUtil;
import com.kurui.kums.finance.AssetsItem;
import com.kurui.kums.finance.AssetsItemListForm;

public class AssetsItemDAOImp extends BaseDAOSupport implements AssetsItemDAO {

	public List list(AssetsItemListForm vehicleListForm) throws AppException {
		Hql hql = new Hql();
		hql.add("from AssetsItem a where 1=1");
		if (StringUtil.isEmpty(vehicleListForm.getItemType())==false) {
			hql.add(" and a.itemType=" + vehicleListForm.getItemType());
		}
		
		if (StringUtil.isEmpty(vehicleListForm.getContactWay())==false) {
			hql.add(" and ( ");
			hql.add(" a.name like'%" + vehicleListForm.getContactWay()+"%' ");
			hql.add(" or a.memo like'%" + vehicleListForm.getContactWay()+"%' ");
			
			hql.add(" ) "); 
		}
		
	

		return this.list(hql, vehicleListForm);
	}

	public void delete(long id) throws AppException {
		if (id > 0) {
			AssetsItem agent = (AssetsItem) this.getHibernateTemplate().get(
					AssetsItem.class, new Long(id));
			this.getHibernateTemplate().delete(agent);
		}
	}

	public long save(AssetsItem agent) throws AppException {
		this.getHibernateTemplate().save(agent);
		return agent.getId();
	}

	public long update(AssetsItem agent) throws AppException {
		if (agent.getId() > 0) {
			this.getHibernateTemplate().update(agent);
			return agent.getId();
		} else
			throw new IllegalArgumentException("id isn't a valid argument.");
	}

	public AssetsItem getAssetsItemById(long id) throws AppException {
		Hql hql = new Hql();
		hql.add("from AssetsItem a where a.id=" + id);
		Query query = this.getQuery(hql);
		AssetsItem agent = null;
		if (query != null && query.list() != null && query.list().size() > 0) {
			agent = (AssetsItem) query.list().get(0);
		}
		return agent;
	}

	public AssetsItem getAssetsItemByAgentId(long agentId) throws AppException {
		Hql hql = new Hql();
		hql.add("from AssetsItem a where a.agent.id=" + agentId);
		Query query = this.getQuery(hql);
		AssetsItem agent = null;
		if (query != null && query.list() != null && query.list().size() > 0) {
			agent = (AssetsItem) query.list().get(0);
		}
		return agent;
	}

	public List<AssetsItem> getAssetsItemList(Long type) throws AppException {
		List<AssetsItem> list = new ArrayList<AssetsItem>();
		Hql hql = new Hql();
		hql.add("from AssetsItem a where a.type=" + type);
		Query query = this.getQuery(hql);
		if (query != null) {
			list = query.list();
			if (list != null && list.size() > 0) {
				return list;
			}
		}
		return list;
	}

	public List<AssetsItem> getValidAssetsItemList() throws AppException {
		List<AssetsItem> list = new ArrayList<AssetsItem>();
		Hql hql = new Hql();
		hql.add("from AssetsItem a where 1=1 ");
		// hql.add("and a.status= "+ AssetsItem.STATES_1);
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
