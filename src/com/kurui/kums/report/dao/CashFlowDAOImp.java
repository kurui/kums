package com.kurui.kums.report.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import com.kurui.kums.report.CashFlow;
import com.kurui.kums.report.CashFlowListForm;
import com.kurui.kums.base.PerformListener;
import com.kurui.kums.base.database.BaseDAOSupport;
import com.kurui.kums.base.database.Hql;
import com.kurui.kums.base.database.SelectDataBean;
import com.kurui.kums.base.exception.AppException;

public class CashFlowDAOImp extends BaseDAOSupport implements CashFlowDAO {
	static Logger logger = Logger.getLogger(CashFlowDAOImp.class.getName());

	public void createCashFlow(CashFlowListForm cashFlowListForm,
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
				new PerformListener("createCashFlow", a);
				logger.info("-----createCashFlow SUCCESS--------------");
			} catch (Exception ex) {
				logger.error("--createCashFlow失败：" + ex.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List list(CashFlowListForm cashFlowListForm,
			HttpServletRequest request) throws AppException {
		Hql hql = new Hql();
		hql.add("from CashFlow a where 1=1");
		// if (Constant.toLong(cashFlowListForm.getAgentId()) > 0) {
		// hql.add(" and a.agent.id=" + cashFlowListForm.getAgentId());
		// }
		//
		// if (Constant.toLong(cashFlowListForm.getType()) > 0) {
		// hql.add(" and a.type=" + cashFlowListForm.getType());
		// }

		hql.add(" and ( a.inAmount>0 or a.outAmount>0) ");

		// hql.add(" and a.status=" + CashFlow.STATES_1);

		hql.add(" and a.sessionId='" + request.getSession().getId() + "'");
		hql.add(" order by a.businessTime desc ");

		// System.out.println(hql);

		return this.list(hql, cashFlowListForm);
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
			CashFlow agent = (CashFlow) this.getHibernateTemplate().get(
					CashFlow.class, new Long(id));
			this.getHibernateTemplate().delete(agent);
		}
	}

	public long save(CashFlow cashFlow) throws AppException {
		this.getHibernateTemplate().save(cashFlow);
		return cashFlow.getId();
	}

	public long update(CashFlow cashFlow) throws AppException {
		if (cashFlow.getId() > 0) {
			this.getHibernateTemplate().update(cashFlow);
			return cashFlow.getId();
		} else
			throw new IllegalArgumentException("id isn't a valid argument.");
	}

	public CashFlow getCashFlowById(long id) throws AppException {
		Hql hql = new Hql();
		hql.add("from CashFlow a where a.id=" + id);
		Query query = this.getQuery(hql);
		CashFlow cashFlow = null;
		if (query != null && query.list() != null && query.list().size() > 0) {
			cashFlow = (CashFlow) query.list().get(0);
		}
		return cashFlow;
	}

	public List<CashFlow> getCashFlowList() throws AppException {
		List<CashFlow> list = new ArrayList<CashFlow>();
		Hql hql = new Hql();
		hql.add("from CashFlow");
		Query query = this.getQuery(hql);
		if (query != null) {
			list = query.list();
			if (list != null && list.size() > 0) {
				return list;
			}
		}
		return list;
	}

	public List<CashFlow> getCashFlowList(Long type) throws AppException {
		List<CashFlow> list = new ArrayList<CashFlow>();
		Hql hql = new Hql();
		hql.add("from CashFlow a where a.type=" + type);
		Query query = this.getQuery(hql);
		if (query != null) {
			list = query.list();
			if (list != null && list.size() > 0) {
				return list;
			}
		}
		return list;
	}

	public List<CashFlow> getValidCashFlowList() throws AppException {
		List<CashFlow> list = new ArrayList<CashFlow>();
		Hql hql = new Hql();
		hql.add("from CashFlow a where 1=1 ");
		// hql.add("and a.status= " + CashFlow.STATES_1);
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
