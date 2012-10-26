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

	public List list(AgentResumeListForm agentActionListForm)
			throws AppException {
		Hql hql = new Hql();
		hql.add("from AgentResume a where 1=1");
		if (Constant.toLong(agentActionListForm.getAgentId()) > 0) {
			hql.add(" and a.agent.id=" + agentActionListForm.getAgentId());
		}
		if (Constant.toString(agentActionListForm.getContactWay()) != "") {
			hql.add(" and( ");
			hql.add(" a.agent.name like '%"
					+ agentActionListForm.getContactWay().trim() + "%'");
			hql.add(" or a.agent.agentNo like '%"
					+ agentActionListForm.getContactWay().trim() + "%'");
			hql.add(" or a.agent.qqCode like '%"
					+ agentActionListForm.getContactWay().trim() + "%'");
			hql.add(" or a.agent.email like '%"
					+ agentActionListForm.getContactWay().trim() + "%'");
			hql.add(" or a.agent.mobilePhone like '%"
					+ agentActionListForm.getContactWay().trim() + "%'");
			hql.add(" ) ");
		}
		return this.list(hql, agentActionListForm);
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
		AgentResume agent = null;
		if (query != null && query.list() != null && query.list().size() > 0) {
			agent = (AgentResume) query.list().get(0);
		}
		return agent;
	}

	public List<AgentResume> getAgentResumeListByAgent(Long agentId)
			throws AppException {
		List<AgentResume> list = new ArrayList<AgentResume>();
		Hql hql = new Hql();
		hql.add("from AgentResume a where 1=1 and a.agent.id=" + agentId);
		
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
