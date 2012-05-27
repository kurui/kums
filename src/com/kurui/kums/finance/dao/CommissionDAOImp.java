package com.kurui.kums.finance.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import com.kurui.kums.base.database.BaseDAOSupport;
import com.kurui.kums.base.database.Hql;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.base.Constant;
import com.kurui.kums.finance.Commission;
import com.kurui.kums.finance.CommissionListForm;

public class CommissionDAOImp extends BaseDAOSupport implements CommissionDAO {

	public List list(CommissionListForm plf) throws AppException {
		Hql hql = new Hql();
		hql.add("from Commission p where 1=1 ");

		hql.add("and p.status=" + Commission.STATES_1);

		if (Constant.toString(plf.getOrderBy()) != "") {
			if (Constant.toString(plf.getOrderBy()) == "agent") {
				hql.add(" order by  p.agent.id ");
			} else if (Constant.toString(plf.getOrderBy()) == "product") {
				hql.add(" order by  p.product.id ");
			} else {
				hql.add(" order by  p.id ");
			}
		} else {
			hql.add(" order by  p.id ");
		}

		// System.out.println(hql);
		return this.list(hql, plf);
	}

	public void delete(long id) throws AppException {
		if (id > 0) {
			Commission Commission = (Commission) this.getHibernateTemplate()
					.get(Commission.class, new Long(id));
			this.getHibernateTemplate().delete(Commission);
		}
	}

	public long save(Commission tranType) throws AppException {
		this.getHibernateTemplate().save(tranType);
		return tranType.getId();
	}

	public long update(Commission Commission) throws AppException {
		if (Commission.getId() > 0) {
			this.getHibernateTemplate().update(Commission);
			return Commission.getId();
		} else
			throw new IllegalArgumentException("id isn't a valid argument.");
	}

	public Commission getCommissionById(long id) throws AppException {
		Hql hql = new Hql();
		hql.add("from Commission p where p.id=" + id);
		Query query = this.getQuery(hql);
		Commission tranType = null;
		if (query != null) {
			if (query.list() != null) {
				if (query.list().size() > 0) {
					tranType = (Commission) query.list().get(0);
				}
			}
		}
		return tranType;
	}

	public List<Commission> getCommissionByOrderId(long orderId)
			throws AppException {
		List<Commission> list = new ArrayList<Commission>();
		Hql hql = new Hql();
		hql.add("from Commission p where p.financeOrder.id=" + orderId);
		Query query = this.getQuery(hql);
		Commission tranType = null;
		if (query != null) {
			if (query.list() != null) {
				if (query.list().size() > 0) {
					list = query.list();
				}
			}
		}
		return list;
	}

	public List<Commission> getCommissionListByOrderIds(String orderids)
			throws AppException {
		List<Commission> list = new ArrayList<Commission>();
		Hql hql = new Hql();

		hql.add("from Commission s where 1=1 and s.financeOrder.id in("
				+ orderids + ") ");
		hql.add("and s.status=" + Commission.STATES_1);
		// System.out.println("hql:"+hql);
		Query query = this.getQuery(hql);
		if (query != null) {
			if (query.list() != null) {
				if (query.list().size() > 0) {
					list = query.list();
				}
			}
		}
		return list;
	}

	public List<Commission> getCommissionList() throws AppException {
		List<Commission> list = new ArrayList<Commission>();
		Hql hql = new Hql();
		hql.add("from Commission p where 1=1 ");
		Query query = this.getQuery(hql);
		if (query != null) {
			if (query.list() != null) {
				if (query.list().size() > 0) {
					list = query.list();
				}
			}
		}
		return list;
	}

	public List<Commission> getValidCommissionList() throws AppException {
		List<Commission> list = new ArrayList<Commission>();
		Hql hql = new Hql();
		hql.add("from Commission p where 1=1 and p.status="
				+ Commission.STATES_1);
		Query query = this.getQuery(hql);
		if (query != null) {
			if (query.list() != null) {
				if (query.list().size() > 0) {
					list = query.list();
				}
			}
		}
		return list;
	}

}
