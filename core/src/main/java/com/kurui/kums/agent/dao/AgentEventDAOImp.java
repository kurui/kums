package com.kurui.kums.agent.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;

import com.kurui.kums.agent.AgentEvent;
import com.kurui.kums.agent.AgentEventListForm;
import com.kurui.kums.base.Constant;
import com.kurui.kums.base.database.BaseDAOSupport;
import com.kurui.kums.base.database.Hql;
import com.kurui.kums.base.exception.AppException;

public class AgentEventDAOImp extends BaseDAOSupport implements AgentEventDAO {

	public List list(AgentEventListForm alf) throws AppException {
		Hql hql = new Hql();
		hql.add("from AgentEvent a where 1=1");
		if (Constant.toLong(alf.getAgentId()) > 0) {
			hql.add(" and a.agent.id=" + alf.getAgentId());
		}
		if (Constant.toString(alf.getKeywords()) != "") {
			hql.add(" and( ");
			hql.add(" a.agent.name like '%" + alf.getKeywords().trim() + "%'");
			hql.add(" or a.agent.agentNo like '%" + alf.getKeywords().trim()
					+ "%'");
			hql.add(" ) ");
		}

		hql.add(" and a.type=" + alf.getType());
		hql.add(" and a.status=" + alf.getStatus());

		return this.list(hql, alf);
	}

	public void delete(long id) throws AppException {
		if (id > 0) {
			AgentEvent agent = (AgentEvent) this.getHibernateTemplate().get(
					AgentEvent.class, new Long(id));
			this.getHibernateTemplate().delete(agent);
		}
	}

	public long save(AgentEvent agent) throws AppException {
		this.getHibernateTemplate().save(agent);
		return agent.getId();
	}

	public long update(AgentEvent agent) throws AppException {
		if (agent.getId() > 0) {
			this.getHibernateTemplate().update(agent);
			return agent.getId();
		} else
			throw new IllegalArgumentException("id isn't a valid argument.");
	}

	public AgentEvent getAgentEventById(long id) throws AppException {
		Hql hql = new Hql();
		hql.add("from AgentEvent a where a.id=" + id);
		Query query = this.getQuery(hql);
		AgentEvent agent = null;
		if (query != null && query.list() != null && query.list().size() > 0) {
			agent = (AgentEvent) query.list().get(0);
		}
		return agent;
	}

	public AgentEvent getAgentEventByAgentId(long agentId) throws AppException {
		Hql hql = new Hql();
		hql.add("from AgentEvent a where a.agent.id=" + agentId);
		Query query = this.getQuery(hql);
		AgentEvent agent = null;
		if (query != null && query.list() != null && query.list().size() > 0) {
			agent = (AgentEvent) query.list().get(0);
		}
		return agent;
	}

	public List<AgentEvent> getAgentEventList() throws AppException {
		List<AgentEvent> list = new ArrayList<AgentEvent>();
		Hql hql = new Hql();
		hql.add("from AgentEvent");
		Query query = this.getQuery(hql);
		if (query != null) {
			list = query.list();
			if (list != null && list.size() > 0) {
				return list;
			}
		}
		return list;
	}

	public List<AgentEvent> getAgentEventList(Long type) throws AppException {
		List<AgentEvent> list = new ArrayList<AgentEvent>();
		Hql hql = new Hql();
		hql.add("from AgentEvent a where a.type=" + type);
		Query query = this.getQuery(hql);
		if (query != null) {
			list = query.list();
			if (list != null && list.size() > 0) {
				return list;
			}
		}
		return list;
	}

	public List<AgentEvent> getValidAgentEventList() throws AppException {
		List<AgentEvent> list = new ArrayList<AgentEvent>();
		Hql hql = new Hql();
		hql.add("from AgentEvent a where 1=1 ");
		hql.add("and a.status= " + AgentEvent.STATES_1);
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
