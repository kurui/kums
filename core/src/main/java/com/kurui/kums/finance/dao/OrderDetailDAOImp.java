package com.kurui.kums.finance.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;

import com.kurui.kums.base.database.BaseDAOSupport;
import com.kurui.kums.base.database.Hql;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.base.Constant;
import com.kurui.kums.finance.OrderDetail;
import com.kurui.kums.finance.OrderDetailListForm;

public class OrderDetailDAOImp extends BaseDAOSupport implements OrderDetailDAO {

	public List list(OrderDetailListForm plf) throws AppException {
		Hql hql = new Hql();
		hql.add("from OrderDetail p where 1=1 ");

		

		hql.add("and p.status=" + OrderDetail.STATES_1);

		if (Constant.toString(plf.getOrderBy()) != "") {
			if (Constant.toString(plf.getOrderBy()) == "agent") {
				hql.add(" order by  p.agent.id ");
			} else if (Constant.toString(plf.getOrderBy()) == "product") {
				hql.add(" order by  p.product.id ");
			}else{
				hql.add(" order by  p.id ");
			}			
		}else{
			hql.add(" order by  p.id ");
		}
		
		
		// System.out.println(hql);
		return this.list(hql, plf);
	}

	public void delete(long id) throws AppException {
		if (id > 0) {
			OrderDetail OrderDetail = (OrderDetail) this.getHibernateTemplate()
					.get(OrderDetail.class, new Long(id));
			this.getHibernateTemplate().delete(OrderDetail);
		}
	}

	public long save(OrderDetail tranType) throws AppException {
		this.getHibernateTemplate().save(tranType);
		return tranType.getId();
	}

	public long update(OrderDetail OrderDetail) throws AppException {
		if (OrderDetail.getId() > 0) {
			this.getHibernateTemplate().update(OrderDetail);
			return OrderDetail.getId();
		} else
			throw new IllegalArgumentException("id isn't a valid argument.");
	}

	public OrderDetail getOrderDetailById(long id) throws AppException {
		Hql hql = new Hql();
		hql.add("from OrderDetail p where p.id=" + id);
		Query query = this.getQuery(hql);
		OrderDetail tranType = null;
		if (query != null) {
			if (query.list() != null) {
				if (query.list().size() > 0) {
					tranType = (OrderDetail) query.list().get(0);
				}
			}
		}
		return tranType;
	}

	public List<OrderDetail> getOrderDetailByOrderId(long orderId)
			throws AppException {
		List<OrderDetail> list = new ArrayList<OrderDetail>();
		Hql hql = new Hql();
		hql.add("from OrderDetail p where p.financeOrder.id=" + orderId);
		Query query = this.getQuery(hql);
		OrderDetail tranType = null;
		if (query != null) {
			if (query.list() != null) {
				if (query.list().size() > 0) {
					list = query.list();
				}
			}
		}
		return list;
	}

	public List<OrderDetail> getOrderDetailListByOrderIds(String orderids)
			throws AppException {
		List<OrderDetail> list = new ArrayList<OrderDetail>();
		Hql hql = new Hql();

		hql.add("from OrderDetail s where 1=1 and s.financeOrder.id in("
				+ orderids + ") ");
		hql.add("and s.status=" + OrderDetail.STATES_1);
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

	public List<OrderDetail> getOrderDetailList() throws AppException {
		List<OrderDetail> list = new ArrayList<OrderDetail>();
		Hql hql = new Hql();
		hql.add("from OrderDetail p where 1=1 ");
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

	public List<OrderDetail> getValidOrderDetailList() throws AppException {
		List<OrderDetail> list = new ArrayList<OrderDetail>();
		Hql hql = new Hql();
		hql.add("from OrderDetail p where 1=1 and p.status="
				+ OrderDetail.STATES_1);
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
