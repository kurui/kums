package com.kurui.kums.finance.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import com.kurui.kums.base.database.BaseDAOSupport;
import com.kurui.kums.base.database.Hql;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.base.Constant;
import com.kurui.kums.finance.BudgetOrder;
import com.kurui.kums.finance.BudgetOrderListForm;

public class BudgetOrderDAOImp extends BaseDAOSupport implements BudgetOrderDAO {

	public List list(BudgetOrderListForm plf) throws AppException {
		Hql hql = new Hql();
		hql.add("from BudgetOrder p where 1=1 ");

		hql.add("and p.status=" + BudgetOrder.STATES_1);

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
			BudgetOrder BudgetOrder = (BudgetOrder) this.getHibernateTemplate()
					.get(BudgetOrder.class, new Long(id));
			this.getHibernateTemplate().delete(BudgetOrder);
		}
	}

	public long save(BudgetOrder tranType) throws AppException {
		this.getHibernateTemplate().save(tranType);
		return tranType.getId();
	}

	public long update(BudgetOrder budgetOrder) throws AppException {
		if (budgetOrder.getId() > 0) {
			this.getHibernateTemplate().update(budgetOrder);
			return budgetOrder.getId();
		} else
			throw new IllegalArgumentException("id isn't a valid argument.");
	}

	public BudgetOrder getBudgetOrderById(long id) throws AppException {
		Hql hql = new Hql();
		hql.add("from BudgetOrder p where p.id=" + id);
		Query query = this.getQuery(hql);
		BudgetOrder tranType = null;
		if (query != null) {
			if (query.list() != null) {
				if (query.list().size() > 0) {
					tranType = (BudgetOrder) query.list().get(0);
				}
			}
		}
		return tranType;
	}

	public List<BudgetOrder> getBudgetOrderByBudgetId(long budgetId)
			throws AppException {
		List<BudgetOrder> list = new ArrayList<BudgetOrder>();
		Hql hql = new Hql();
		hql.add("from BudgetOrder p where p.budget.id=" + budgetId);
		Query query = this.getQuery(hql);
		BudgetOrder tranType = null;
		if (query != null) {
			if (query.list() != null) {
				if (query.list().size() > 0) {
					list = query.list();
				}
			}
		}
		return list;
	}

	public List<BudgetOrder> getBudgetOrderListByOrderIds(String orderids)
			throws AppException {
		List<BudgetOrder> list = new ArrayList<BudgetOrder>();
		Hql hql = new Hql();

		hql.add("from BudgetOrder s where 1=1 and s.budget.id in("
				+ orderids + ") ");
		hql.add("and s.status=" + BudgetOrder.STATES_1);
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

	public List<BudgetOrder> getBudgetOrderList() throws AppException {
		List<BudgetOrder> list = new ArrayList<BudgetOrder>();
		Hql hql = new Hql();
		hql.add("from BudgetOrder p where 1=1 ");
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

	public List<BudgetOrder> getValidBudgetOrderList() throws AppException {
		List<BudgetOrder> list = new ArrayList<BudgetOrder>();
		Hql hql = new Hql();
		hql.add("from BudgetOrder p where 1=1 and p.status="
				+ BudgetOrder.STATES_1);
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
