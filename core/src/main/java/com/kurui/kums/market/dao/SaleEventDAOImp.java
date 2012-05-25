package com.kurui.kums.market.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import com.kurui.kums.base.database.BaseDAOSupport;
import com.kurui.kums.base.database.Hql;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.base.Constant;
import com.kurui.kums.market.SaleEvent;
import com.kurui.kums.market.SaleEventListForm;

public class SaleEventDAOImp extends BaseDAOSupport implements SaleEventDAO {

	public List list(SaleEventListForm saleEventListForm) throws AppException {
		Hql hql = new Hql();
		hql.add("from SaleEvent p where 1=1");
		if (!(Constant.toUpperCase(saleEventListForm.getName()).equals(""))) {
			hql.add(" and ( ");
			hql.add(" p.name like '%" + saleEventListForm.getName().trim()
					+ "%' ");
			hql.add(" ) ");
		}
		if (Constant.toLong(saleEventListForm.getType()) > 0) {
			hql.add(" and p.type=" + saleEventListForm.getType());
		}

		hql.add(" and p.status=" + SaleEvent.STATES_1);

		hql.add(" order by p.type ");

		return this.list(hql, saleEventListForm);
	}

	public void delete(long id) throws AppException {
		if (id > 0) {
			SaleEvent saleEvent = (SaleEvent) this.getHibernateTemplate().get(
					SaleEvent.class, new Long(id));
			this.getHibernateTemplate().delete(saleEvent);
		}
	}

	public long save(SaleEvent saleEvent) throws AppException {
		this.getHibernateTemplate().save(saleEvent);
		return saleEvent.getId();
	}

	public long update(SaleEvent saleEvent) throws AppException {
		if (saleEvent.getId() > 0) {
			this.getHibernateTemplate().update(saleEvent);
			return saleEvent.getId();
		} else
			throw new IllegalArgumentException("id isn't a valid argument.");
	}

	public SaleEvent getSaleEventById(long id) throws AppException {
		Hql hql = new Hql();
		hql.add("from SaleEvent p where p.id=" + id);

		Query query = this.getQuery(hql);
		SaleEvent saleEvent = null;
		if (query != null && query.list() != null && query.list().size() > 0) {
			saleEvent = (SaleEvent) query.list().get(0);
		}

		return saleEvent;
	}

	public List<SaleEvent> getSaleEventList() throws AppException {
		List<SaleEvent> list = new ArrayList<SaleEvent>();
		Hql hql = new Hql();
		hql.add(" from SaleEvent p  where 1=1 ");
		hql.add(" and p.status=" + SaleEvent.STATES_1);
		hql.add(" order by p.type ");
		Query query = this.getQuery(hql);
		if (query != null) {
			list = query.list();
			if (list != null && list.size() > 0) {
				return list;
			}
		}
		return list;
	}

	public List<SaleEvent> getSaleEventListByType(String saleEventTypes)
			throws AppException {
		List<SaleEvent> list = new ArrayList<SaleEvent>();
		Hql hql = new Hql();
		hql.add(" from SaleEvent p where  1=1 ");

		if (Constant.toString(saleEventTypes) != "") {
			hql.add(" and p.type in(" + saleEventTypes + ")");
		}

		hql.add(" and p.status=" + SaleEvent.STATES_1);
		hql.add(" order by p.type ");
		Query query = this.getQuery(hql);
		if (query != null) {
			list = query.list();
			if (list != null && list.size() > 0) {
				return list;
			}
		}
		return list;
	}

	public List<SaleEvent> getValidSaleEventList() throws AppException {
		List<SaleEvent> list = new ArrayList<SaleEvent>();
		Hql hql = new Hql();
		hql.add(" from SaleEvent p where 1=1 ");
		hql.add(" and p.status=" + SaleEvent.STATES_1);
		hql.add(" order by p.type ");
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
