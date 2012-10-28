package com.kurui.kums.agent.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;

import com.kurui.kums.agent.AgentResume;
import com.kurui.kums.agent.AgentResumeListForm;
import com.kurui.kums.base.Constant;
import com.kurui.kums.base.database.BaseDAOSupport;
import com.kurui.kums.base.database.Hql;
import com.kurui.kums.base.exception.AppException;

public class AgentResumeDAOImp extends BaseDAOSupport implements AgentResumeDAO {

	public List list(AgentResumeListForm alf) throws AppException {
		Hql hql = new Hql();
		hql.add("from AgentResume r where 1=1 ");
		if (Constant.toLong(alf.getAgentId()) > 0) {
			hql.add(" and r.agent.id=" + alf.getAgentId());
		}
		if (Constant.toLong(alf.getCompanyId()) > 0) {
			hql.add(" and r.company.id=" + alf.getCompanyId());
		}
		if (Constant.toString(alf.getKeywords()) != "") {
			if (Constant.toString(alf.getKeywords()) != "") {
				hql.add(" and( ");
				hql.add(" r.agent.name like '%" + alf.getKeywords().trim()
						+ "%'");
				hql.add(" or r.agent.agentNo like '%"
						+ alf.getKeywords().trim() + "%'");
				hql.add(" or r.content like '%" + alf.getKeywords().trim()
						+ "%'");

				hql.add(" ) ");
			}
		}
		hql.add(" ) ");

		hql.add(" or r.company.id in(select a.id from Company a where 1=1  ");
		if (Constant.toString(alf.getKeywords()) != "") {
			hql.add(" and( ");
			hql.add(" a.name like '%" + alf.getKeywords().trim() + "%'");
			hql.add(" ) ");
		}
		hql.add(" ) ");
		hql.add(" or  r.content like '%" + alf.getKeywords().trim() + "%'");

		hql.add(" and r.type=" + alf.getType());
		hql.add(" and r.status=" + alf.getStatus());

		System.out.println(hql);
		return this.list(hql, alf);
	}

	public void delete(long id) throws AppException {
		if (id > 0) {
			AgentResume agent = (AgentResume) this.getHibernateTemplate().get(
					AgentResume.class, new Long(id));
			this.getHibernateTemplate().delete(agent);
		}
	}

	public long save(AgentResume agent) throws AppException {
		this.getHibernateTemplate().save(agent);
		return agent.getId();
	}

	public long update(AgentResume agent) throws AppException {
		if (agent.getId() > 0) {
			this.getHibernateTemplate().update(agent);
			return agent.getId();
		} else
			throw new IllegalArgumentException("id isn't a valid argument.");
	}

	public AgentResume getAgentResumeById(long id) throws AppException {
		Hql hql = new Hql();
		hql.add("from AgentResume a where a.id=" + id);
		Query query = this.getQuery(hql);
		AgentResume agentResume = null;
		if (query != null && query.list() != null && query.list().size() > 0) {
			agentResume = (AgentResume) query.list().get(0);
			if(agentResume.getCompany()!=null){
				agentResume.setCompanyId(agentResume.getCompany().getId());
			}
		
		}
		return agentResume;
	}

	public List<AgentResume> getAgentResumeListByAgent(Long agentId)
			throws AppException {
		List<AgentResume> list = new ArrayList<AgentResume>();
		Hql hql = new Hql();
		hql.add("from AgentResume a where 1=1 and a.agent.id=" + agentId);
		hql.add(" and a.status not in(" + AgentResume.STATES_0 + ")");

		Query query = this.getQuery(hql);
		if (query != null) {
			list = query.list();
			if (list != null && list.size() > 0) {
				return list;
			}
		}
		return list;
	}

	public List<AgentResume> getAgentResumeList() throws AppException {
		List<AgentResume> list = new ArrayList<AgentResume>();
		Hql hql = new Hql();
		hql.add("from AgentResume");
		Query query = this.getQuery(hql);
		if (query != null) {
			list = query.list();
			if (list != null && list.size() > 0) {
				return list;
			}
		}
		return list;
	}

	public List<AgentResume> getAgentResumeList(Long type) throws AppException {
		List<AgentResume> list = new ArrayList<AgentResume>();
		Hql hql = new Hql();
		hql.add("from AgentResume a where a.type=" + type);
		Query query = this.getQuery(hql);
		if (query != null) {
			list = query.list();
			if (list != null && list.size() > 0) {
				return list;
			}
		}
		return list;
	}

	public List<AgentResume> getValidAgentResumeList() throws AppException {
		List<AgentResume> list = new ArrayList<AgentResume>();
		Hql hql = new Hql();
		hql.add("from AgentResume a where 1=1 ");
		hql.add("and a.status= " + AgentResume.STATES_1);
		// hql.add(" order by a.name ");
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
