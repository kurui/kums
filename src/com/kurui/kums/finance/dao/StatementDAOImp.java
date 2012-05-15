package com.kurui.kums.finance.dao;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

import com.kurui.kums.base.database.BaseDAOSupport;
import com.kurui.kums.base.database.Hql;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.finance.Statement;
import com.kurui.kums.finance.StatementListForm;
import com.kurui.kums.transaction.Account;

public class StatementDAOImp extends BaseDAOSupport implements StatementDAO {

	public void synStatementAmount(long orderId) {
		try {
			Session session = this.getSessionFactory().getCurrentSession();
			try {
				Connection con = session.connection();
				con.setAutoCommit(true);
				CallableStatement call = con
						.prepareCall("{Call update_statementamount(?)}");
				call.setInt("orderId", (int) orderId);
				call.execute();
				call.close();
				con.commit();
				con.close();
			} catch (Exception ex) {
				System.out.println("--同步订单结算金额失败：" + ex.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void synOldStatementAmount(long orderId) {
		try {
			Session session = this.getSessionFactory().getCurrentSession();
			try {
				Connection con = session.connection();
				con.setAutoCommit(true);
				CallableStatement call = con
						.prepareCall("{Call update_oldstatementamount(?)}");
				call.setInt("orderId", (int) orderId);
				call.execute();
				call.close();
				con.commit();
				con.close();
			} catch (Exception ex) {
				System.out.println("--同步退改订单销售结算金额失败：" + ex.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	public BigDecimal getStatementAmount(long orderId, long orderSubtype,
			long orderTable) throws AppException {
		Hql hql = new Hql();
		hql.add(" select sum(s.totalAmount) ");

		hql.add(" from Statement s where 1=1 ");
		hql.add(" and s.orderId=" + orderId);
		hql.add(" and s.orderSubtype=" + orderSubtype);
		hql.add(" and s.orderTable=" + orderTable);

		Query query = this.getQuery(hql);
		BigDecimal amount = BigDecimal.ZERO;
		if (query != null) {
			List list = query.list();
			if (list != null) {
				if (list.size() > 0) {
					amount = (BigDecimal) list.get(0);
				}
			}
		}
		return amount;
	}

	public Account getStatementAccountByOrderSubType(long orderid,
			long orderSubtype, long orderTable) throws AppException {
		Account account = null;
		try {
			Statement statement = getStatementByOrderSubType(orderid,
					orderSubtype, orderTable);
			if (statement != null) {
				Long type = statement.getType();
				if (type != null) {
					if (type == Statement.TYPE_1) {
						account = statement.getToAccount();
					} else if (type == Statement.TYPE_2) {
						account = statement.getFromAccount();
					}
				} else {
					System.out.println("getStatementAccountByOrderSubType==>"
							+ statement.getId() + " type is null");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return account;
	}

	public Statement getStatementByOrderSubType(long orderid,
			long orderSubtype, long orderTable) throws AppException {
		Statement statement = null;
		List list = getStatementListByOrderSubType(orderid, orderSubtype,
				orderTable);
		if (list != null && list.size() > 0) {
			if (list.size() > 1) {
				System.out.println("list size>1 ====>orderid:" + orderid
						+ "---orderSubtype" + orderSubtype + "--orderTable:"
						+ orderTable);
			}
			statement = (Statement) list.get(0);
		} else {
			System.out.println("list is null ====>orderid:" + orderid
					+ "---orderSubtype" + orderSubtype + "--orderTable:"
					+ orderTable);
		}
		return statement;
	}

	public List getStatementListByOrderSubType(long orderid, long orderSubtype,
			long orderTable) throws AppException {
		Hql hql = new Hql();

		hql.add(" from Statement s where 1=1");
		hql.add(" and s.orderId=" + orderid);

		hql.add(" and s.orderSubtype=" + orderSubtype);

		hql.add(" and s.orderTable=" + orderTable);

		hql.add(" and s.status not in(88) ");

		List<Statement> list = new ArrayList<Statement>();
		Query query = this.getQuery(hql);

		if (query != null) {
			list = query.list();
			if (list != null) {
				if (list.size() > 0) {
					return list;
				}
			}
		}
		return list;
	}



	public List getStatementListByOrder(long orderid, long orderTable)
			throws AppException {
		Hql hql = new Hql();

		hql.add("from Statement s where 1=1 and s.orderId=" + orderid
				+ " and s.orderTable=" + orderTable);
		hql.add("and s.status not in(88) ");
		return this.list(hql);
	}

	public List getStatementListByOrders(String orderid, long orderTable)
			throws AppException {
		Hql hql = new Hql();

		hql.add("from Statement s where 1=1 and s.orderId in(" + orderid
				+ ") and s.orderTable=" + orderTable);
		hql.add("and s.status not in(88) ");
//		System.out.println("hql:"+hql);
		return this.list(hql);
	}

	public Statement getStatementByOrder(long orderid, long orderTable,
			long statementType) throws AppException {
		Hql hql = new Hql();
		hql.add("from Statement s where 1=1 and s.orderId=" + orderid
				+ " and s.orderTable=" + orderTable + " and s.type="
				+ statementType);
		hql.add(" and s.status not in(88) ");
		Query query = this.getQuery(hql);
		Statement statement = new Statement();
		if (query != null) {
			List list = query.list();
			if (list != null) {
				if (list.size() > 0) {
					statement = (Statement) list.get(0);
				}
			}
		}
		return statement;
	}

	public Statement getStatementByOrderIdAndStatus(long orderid,
			long orderSubtype, long statementStatus) throws AppException {
		Statement statement = null;
		List list = listByStatementStatus(orderid, orderSubtype,
				statementStatus);
		if (list != null && list.size() > 0) {
			if (list.size() > 1) {
				System.out.println("list size>1 ====>orderid:" + orderid
						+ "---orderSubtype" + orderSubtype
						+ "--statementStatus:" + statementStatus);
			}
			statement = (Statement) list.get(0);
		} else {
			System.out.println("list is null ====>orderid:" + orderid
					+ "--orderSubtype" + orderSubtype + "--statementStatus:"
					+ statementStatus);
		}
		return statement;
	}

	public List<Statement> listByStatementStatus(long orderid,
			long orderSubtype, long status) throws AppException {
		Hql hql = new Hql();
		hql.add("from Statement s where 1=1 ");
		hql.add(" and s.orderId=" + orderid);
		hql.add(" and s.orderSubtype=" + orderSubtype);
		hql.add(" and s.status=" + status);

		Query query = this.getQuery(hql);

		List<Statement> list = new ArrayList<Statement>();
		if (query != null) {
			list = query.list();
			if (list != null) {
				if (list.size() > 0) {
					return list;
				}
			}
		}
		return list;
	}

	public Statement getStatementById(long id) throws AppException {
		Hql hql = new Hql();
		hql.add("from Statement s where s.id=" + id);
		Query query = this.getQuery(hql);
		Statement statement = new Statement();
		if (query != null) {
			List list = query.list();
			if (list != null) {
				if (list.size() > 0) {
					statement = (Statement) list.get(0);
				}
			}
		}
		return statement;
	}

	public List getStatementListByfinanceGroupMarkNo(String groupMarkNo)
			throws AppException {
		Hql hql = new Hql();
		hql.add("from Statement s where 1=1 and s.status not in(88) ");
		hql.add("exists("
				+ " select o.id from financeOrder o where o.groupMarkNo="
				+ groupMarkNo.trim());
		hql.add("and s.orderId=o.id  and s.orderTable=" + Statement.ORDERTABLE_1);
		hql.add(" and s.status not in(88) )");

		return this.list(hql);
	}

	public List list(StatementListForm rlf) throws AppException {
		Hql hql = new Hql();
		hql.add("from Statement s where 1=1");
		if (rlf.getStatementNo() != null
				&& (!(rlf.getStatementNo().equals("")))) {
			hql.add(" and s.statementNo like '%" + rlf.getStatementNo().trim()
					+ "%'");
		}

		hql.add(" and s.status not in(88) ");

		hql.add(" order by s.optTime desc");
		return this.list(hql, rlf);
	}

	public List list() throws AppException {
		Hql hql = new Hql();
		hql.add("from Statement where 1=1");
		return this.list(hql);
	}

	public void delete(long id) throws AppException {
		if (id > 0) {
			Statement statement = (Statement) this.getHibernateTemplate().get(
					Statement.class, Long.valueOf(id));
			this.getHibernateTemplate().delete(statement);
		}
	}

	public long save(Statement statement) throws AppException {
		this.getHibernateTemplate().save(statement);
		return statement.getId();
	}

	public long update(Statement statement) throws AppException {
		if (statement.getId() > 0) {
			this.getHibernateTemplate().update(statement);
			return statement.getId();
		} else
			throw new IllegalArgumentException("id isn't a valid argument.");
	}

	public long merge(Statement statement) throws AppException {
		this.getHibernateTemplate().merge(statement);
		return statement.getId();
	}
}
