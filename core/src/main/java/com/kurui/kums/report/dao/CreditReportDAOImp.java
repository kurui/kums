package com.kurui.kums.report.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import com.kurui.kums.report.CreditReport;
import com.kurui.kums.report.CreditReportListForm;
import com.kurui.kums.base.database.hibernate.BaseDAOSupport;
import com.kurui.kums.base.database.hibernate.Hql;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.base.Constant;

public class CreditReportDAOImp extends BaseDAOSupport implements
		CreditReportDAO {
	static Logger logger = Logger.getLogger(CreditReportDAOImp.class.getName());

	public void createCreditReport(CreditReportListForm creditReportListForm,
			HttpServletRequest request) {
		try {
			Session session = this.getSessionFactory().getCurrentSession();
			try {
				Connection con = session.connection();
				con.setAutoCommit(true);
				CallableStatement call = con
						.prepareCall("{Call create_credit_report(?)}");
				call.setString("sessionId", request.getSession().getId());
				call.execute();
				call.close();
				con.commit();
				con.close();
				logger.info("-----createCreditReport SUCCESS--------------");
			} catch (Exception ex) {
				logger.error("--createCreditReport失败：" + ex.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void createDeditReport(CreditReportListForm creditReportListForm,
			HttpServletRequest request) {
		try {
			Session session = this.getSessionFactory().getCurrentSession();
			try {
				Connection con = session.connection();
				con.setAutoCommit(true);
				CallableStatement call = con
						.prepareCall("{Call create_debit_report(?)}");
				call.setString("sessionId", request.getSession().getId());
				call.execute();
				call.close();
				con.commit();
				con.close();
				logger.info("-----createDeditReport SUCCESS--------------");
			} catch (Exception ex) {
				logger.error("--createDeditReport失败：" + ex.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List list(CreditReportListForm creditReportListForm,
			HttpServletRequest request) throws AppException {
		Hql hql = new Hql();
		hql.add("from CreditReport a where 1=1");
		if (Constant.toLong(creditReportListForm.getAgentId()) > 0) {
			hql.add(" and a.agent.id=" + creditReportListForm.getAgentId());
		}

		if (Constant.toLong(creditReportListForm.getType()) > 0) {
			hql.add(" and a.type=" + creditReportListForm.getType());
		}

		hql.add(" and a.beginAmount>0 ");

		// hql.add(" and a.status=" + CreditReport.STATES_1);

		hql.add(" and a.sessionId='" + request.getSession().getId()+"'");
		hql.add(" order by a.beginAmount ");

		// System.out.println(hql);

		return this.list(hql, creditReportListForm);
	}

	public void delete(long id) throws AppException {
		if (id > 0) {
			CreditReport agent = (CreditReport) this.getHibernateTemplate()
					.get(CreditReport.class, new Long(id));
			this.getHibernateTemplate().delete(agent);
		}
	}

	public long save(CreditReport agent) throws AppException {
		this.getHibernateTemplate().save(agent);
		return agent.getId();
	}

	public long update(CreditReport agent) throws AppException {
		if (agent.getId() > 0) {
			this.getHibernateTemplate().update(agent);
			return agent.getId();
		} else
			throw new IllegalArgumentException("id isn't a valid argument.");
	}

	public CreditReport getCreditReportById(long id) throws AppException {
		Hql hql = new Hql();
		hql.add("from CreditReport a where a.id=" + id);
		Query query = this.getQuery(hql);
		CreditReport agent = null;
		if (query != null && query.list() != null && query.list().size() > 0) {
			agent = (CreditReport) query.list().get(0);
		}
		return agent;
	}

	public List<CreditReport> getCreditReportList() throws AppException {
		List<CreditReport> list = new ArrayList<CreditReport>();
		Hql hql = new Hql();
		hql.add("from CreditReport");
		Query query = this.getQuery(hql);
		if (query != null) {
			list = query.list();
			if (list != null && list.size() > 0) {
				return list;
			}
		}
		return list;
	}

	public List<CreditReport> getCreditReportList(Long type)
			throws AppException {
		List<CreditReport> list = new ArrayList<CreditReport>();
		Hql hql = new Hql();
		hql.add("from CreditReport a where a.type=" + type);
		Query query = this.getQuery(hql);
		if (query != null) {
			list = query.list();
			if (list != null && list.size() > 0) {
				return list;
			}
		}
		return list;
	}

	public List<CreditReport> getValidCreditReportList() throws AppException {
		List<CreditReport> list = new ArrayList<CreditReport>();
		Hql hql = new Hql();
		hql.add("from CreditReport a where 1=1 ");
		// hql.add("and a.status= " + CreditReport.STATES_1);
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
