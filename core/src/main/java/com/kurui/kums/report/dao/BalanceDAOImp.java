package com.kurui.kums.report.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import com.kurui.kums.base.database.hibernate.BaseDAOSupport;
import com.kurui.kums.base.database.hibernate.Hql;
import com.kurui.kums.base.database.jdbc.SelectDataBean;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.base.threads.listener.PerformListener;
import com.kurui.kums.report.Balance;
import com.kurui.kums.report.BalanceListForm;

public class BalanceDAOImp extends BaseDAOSupport implements BalanceDAO {
	static Logger logger = Logger.getLogger(BalanceDAOImp.class.getName());

	public void createBalance(BalanceListForm balanceListForm,
			HttpServletRequest request) {
		try {
			Session session = this.getSessionFactory().getCurrentSession();
			try {
				long a = System.currentTimeMillis();
				Connection con = session.connection();
				con.setAutoCommit(true);
				CallableStatement call = con
						.prepareCall("{Call create_cash_flow(?)}");
				call.setString("sessionId", request.getSession().getId());
				call.execute();
				call.close();
				con.commit();
				con.close();
				new PerformListener("createBalance", a);
				logger.info("-----createBalance SUCCESS--------------");
			} catch (Exception ex) {
				logger.error("--createBalance失败：" + ex.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List list(BalanceListForm balanceListForm, HttpServletRequest request)
			throws AppException {
		Hql hql = new Hql();
		hql.add(" from Balance a where 1=1");
		// if (Constant.toLong(balanceListForm.getAgentId()) > 0) {
		// hql.add(" and a.agent.id=" + balanceListForm.getAgentId());
		// }
		//
		// if (Constant.toLong(balanceListForm.getType()) > 0) {
		// hql.add(" and a.type=" + balanceListForm.getType());
		// }

		hql.add(" and ( a.inAmount>0 or a.outAmount>0) ");

		// hql.add(" and a.status=" + Balance.STATES_1);

		hql.add(" and a.sessionId='" + request.getSession().getId() + "'");
		hql.add(" order by a.businessTime desc ");

		// System.out.println(hql);

		return this.list(hql, balanceListForm);
	}

	public String getItemAmountBySuperItem(String itemKey) throws AppException {
		String itemAmount = "";

		if (itemKey != "") {
			String sql = "select sum(f.total_amount) from finance_order f where 1=1 ";
			sql += " and f.tran_type in( ";
			sql += " select t.no from data_type t where t.super_no=" + itemKey;
			sql += ")";

			SelectDataBean sdb = new SelectDataBean();
			sdb.setQuerySQL(sql);
			sdb.executeQuery();
			int rowcount = sdb.getRowCount();
			if (rowcount > 0) {
				itemAmount = sdb.getColValue(1, 1);
			}
		}
		return itemAmount;
	}

	public String getItemAmount(String itemKey) throws AppException {
		String itemAmount = "";

		if (itemKey != "") {
			String sql = "select sum(f.total_amount) from finance_order f where f.tran_type in("
					+ itemKey + ")";

			SelectDataBean sdb = new SelectDataBean();
			sdb.setQuerySQL(sql);
			sdb.executeQuery();
			int rowcount = sdb.getRowCount();
			if (rowcount > 0) {
				itemAmount = sdb.getColValue(1, 1);
			}
		}
		return itemAmount;
	}

	public static long existSession(HttpServletRequest request)
			throws AppException {
		String sessionId = request.getSession().getId();
		// if(sessionId){
		//			
		// }
		String sql = "select * from (select distinct(session_id) sid from cash_flow) t where t.sid="
				+ sessionId;
		SelectDataBean sdb = new SelectDataBean();
		sdb.setQuerySQL(sql);
		sdb.executeQuery();
		int rowcount = sdb.getRowCount();

		return rowcount;
	}

	public void delete(long id) throws AppException {
		if (id > 0) {
			Balance agent = (Balance) this.getHibernateTemplate().get(
					Balance.class, new Long(id));
			this.getHibernateTemplate().delete(agent);
		}
	}

	public long save(Balance balance) throws AppException {
		this.getHibernateTemplate().save(balance);
		return balance.getId();
	}

	public long update(Balance balance) throws AppException {
		if (balance.getId() > 0) {
			this.getHibernateTemplate().update(balance);
			return balance.getId();
		} else
			throw new IllegalArgumentException("id isn't a valid argument.");
	}

	public Balance getBalanceById(long id) throws AppException {
		Hql hql = new Hql();
		hql.add("from Balance a where a.id=" + id);
		Query query = this.getQuery(hql);
		Balance balance = null;
		if (query != null && query.list() != null && query.list().size() > 0) {
			balance = (Balance) query.list().get(0);
		}
		return balance;
	}

	public List<Balance> getBalanceList() throws AppException {
		List<Balance> list = new ArrayList<Balance>();
		Hql hql = new Hql();
		hql.add("from Balance");
		Query query = this.getQuery(hql);
		if (query != null) {
			list = query.list();
			if (list != null && list.size() > 0) {
				return list;
			}
		}
		return list;
	}

	public List<Balance> getBalanceList(Long type) throws AppException {
		List<Balance> list = new ArrayList<Balance>();
		Hql hql = new Hql();
		hql.add("from Balance a where a.type=" + type);
		Query query = this.getQuery(hql);
		if (query != null) {
			list = query.list();
			if (list != null && list.size() > 0) {
				return list;
			}
		}
		return list;
	}

	public List<Balance> getValidBalanceList() throws AppException {
		List<Balance> list = new ArrayList<Balance>();
		Hql hql = new Hql();
		hql.add("from Balance a where 1=1 ");
		// hql.add("and a.status= " + Balance.STATES_1);
		hql.add(" order by a.beginAmount ");
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