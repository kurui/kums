package com.kurui.kums.finance.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import com.kurui.kums.base.database.BaseDAOSupport;
import com.kurui.kums.base.database.Hql;
import com.kurui.kums.base.Constant;
import com.kurui.kums.base.util.KumsNoUtil;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.finance.Budget;
import com.kurui.kums.finance.BudgetListForm;

public class BudgetDAOImp extends BaseDAOSupport implements BudgetDAO {
	private KumsNoUtil noUtil;

	static Logger logger = Logger.getLogger(BudgetDAOImp.class.getName());

	public List list(BudgetListForm plf) throws AppException {
		Hql hql = new Hql();
		hql.add("from Budget p where 1=1 ");

		hql.add("and p.status=" + Budget.STATUS_1);

		if (Constant.toString(plf.getOrderBy()) != "") {
			if (Constant.toString(plf.getOrderBy()) == "company") {
				hql.add(" order by  p.companyNo ");
			} else if (Constant.toString(plf.getOrderBy()) == "beginTime") {
				hql.add(" order by  p.beginTime ");
			} else {
				hql.add(" order by  p.id desc");
			}
		} else {
			hql.add(" order by  p.id desc");
		}

		// System.out.println(hql);
		return this.list(hql, plf);
	}

	public Budget getBudgetById(long budgetId) throws AppException {
		Hql hql = new Hql();
		hql.add("from Budget a where a.id=" + budgetId);
		Query query = this.getQuery(hql);
		Budget financeOrder = null;
		if (query != null) {
			List<Budget> list = query.list();
			if (list != null && list.size() > 0)
				financeOrder = list.get(0);

		}
		return financeOrder;
	}

	public void delete(long id) throws AppException {
		if (id > 0) {
			Budget financeOrder = (Budget) this.getHibernateTemplate().get(
					Budget.class, Long.valueOf(id));
			this.getHibernateTemplate().delete(financeOrder);
		}
	}

	public long save(Budget budget) throws AppException {
		if (budget.getId() <= 0) {
			budget.setNo(noUtil.getBudgetNo());
		}
		this.getHibernateTemplate().save(budget);
		return budget.getId();
	}

	public long update(Budget budget) throws AppException {
		if (budget.getId() <= 0) {
			budget.setNo(noUtil.getBudgetNo());
		}
		this.getHibernateTemplate().saveOrUpdate(budget);
		return budget.getId();
	}

	public void setNoUtil(KumsNoUtil noUtil) {
		this.noUtil = noUtil;
	}

}
