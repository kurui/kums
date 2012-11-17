package com.kurui.kums.report.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;

import com.kurui.kums.base.database.hibernate.BaseDAOSupport;
import com.kurui.kums.base.database.hibernate.Hql;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.report.AgentReport;

public class AgentReportDAOImp extends BaseDAOSupport implements AgentReportDAO {
	public List<AgentReport> getSexList() throws AppException {
		List<AgentReport> list = new ArrayList<AgentReport>();
		Hql hql = new Hql();
		hql.add("select new com.kurui.kums.report.AgentReport( t.sex,count(*),'SEX' ) from Agent t where  t.status=1 group by t.sex order by t.sex ");
		Query query = this.getQuery(hql);
		if (query != null) {
			list = query.list();
			if (list != null && list.size() > 0) {
				return list;
			}
		}
		return list;
	}
	
	public List<AgentReport> getTypeList() throws AppException {
		List<AgentReport> list = new ArrayList<AgentReport>();
		Hql hql = new Hql();
		hql.add("select new com.kurui.kums.report.AgentReport( t.type,count(*),'TYPE' ) from Agent t where  t.status=1 group by t.type order by t.type ");
		Query query = this.getQuery(hql);
		if (query != null) {
			list = query.list();
			if (list != null && list.size() > 0) {
				return list;
			}
		}
		return list;
	}
	
	public List<AgentReport> getTypeSexList() throws AppException {
		List<AgentReport> list = new ArrayList<AgentReport>();
		Hql hql = new Hql();
		hql.add("select new com.kurui.kums.report.AgentReport( t.type,t.sex,count(*),'TYPE_SEX' ) from Agent t where  t.status=1 group by ROLLUP(t.type,t.sex) order by GROUPING_ID(t.type) ");
		Query query = this.getQuery(hql);
		if (query != null) {
			list = query.list();
			if (list != null && list.size() > 0) {
				return list;
			}
		}
		return list;
	}
	
	public List<AgentReport> getCompanyList() throws AppException {
		List<AgentReport> list = new ArrayList<AgentReport>();
		Hql hql = new Hql();
//		hql.add("select new com.kurui.kums.report.AgentReport( c.id,c.name,P.tc,'COMPANY' ) from ");
//hql.add(" Company c,");
//hql.add("(");
//hql.add(" select t.company.id  cid,count(*) tc from AgentResume t where t.status=1 and t.type=51 group by t.companyId ");
//hql.add(" ) P ");
//hql.add(" where c.id=P.cid order by P.tc desc ");
		
		hql.add("select new com.kurui.kums.report.AgentReport(c.id,c.name,c.agentCount,'COMPANY' )");		
		hql.add(" from Company c where c.agentCount>1 order by c.agentCount desc ");

//System.out.println(hql);

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
