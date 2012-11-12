package com.kurui.kums.report.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;

import com.kurui.kums.base.database.BaseDAOSupport;
import com.kurui.kums.base.database.Hql;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.report.AgentReport;

public class AgentReportDAOImp extends BaseDAOSupport implements AgentReportDAO {
	public List<AgentReport> getSexList() throws AppException {
		List<AgentReport> list = new ArrayList<AgentReport>();
		Hql hql = new Hql();
		hql.add("select new com.kurui.kums.report.AgentReport( t.sex,count(*) ) from Agent t where  t.status=1 group by t.sex order by t.sex ");
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
}
