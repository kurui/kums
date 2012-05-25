package com.kurui.kums.agent.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import com.kurui.kums.agent.AgentAction;
import com.kurui.kums.agent.AgentActionListForm;
import com.kurui.kums.base.database.BaseDAOSupport;
import com.kurui.kums.base.database.Hql;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.base.Constant;

public class AgentActionDAOImp extends BaseDAOSupport implements AgentActionDAO {

	public List list(AgentActionListForm agentActionListForm)
			throws AppException {
		Hql hql = new Hql();
		hql.add("from AgentAction a where 1=1");
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
			AgentAction agent = (AgentAction) this.getHibernateTemplate().get(
					AgentAction.class, new Long(id));
			this.getHibernateTemplate().delete(agent);
		}
	}

	public long save(AgentAction agent) throws AppException {
		this.getHibernateTemplate().save(agent);
		return agent.getId();
	}

	public long update(AgentAction agent) throws AppException {
		if (agent.getId() > 0) {
			this.getHibernateTemplate().update(agent);
			return agent.getId();
		} else
			throw new IllegalArgumentException("id isn't a valid argument.");
	}

	public AgentAction getAgentActionById(long id) throws AppException {
		Hql hql = new Hql();
		hql.add("from AgentAction a where a.id=" + id);
		Query query = this.getQuery(hql);
		AgentAction agent = null;
		if (query != null && query.list() != null && query.list().size() > 0) {
			agent = (AgentAction) query.list().get(0);
		}
		return agent;
	}

	public AgentAction getAgentActionByAgentId(long agentId)
			throws AppException {
		Hql hql = new Hql();
		hql.add("from AgentAction a where a.agent.id=" + agentId);
		Query query = this.getQuery(hql);
		AgentAction agent = null;
		if (query != null && query.list() != null && query.list().size() > 0) {
			agent = (AgentAction) query.list().get(0);
		}
		return agent;
	}

	public List<AgentAction> getAgentActionList() throws AppException {
		List<AgentAction> list = new ArrayList<AgentAction>();
		Hql hql = new Hql();
		hql.add("from AgentAction");
		Query query = this.getQuery(hql);
		if (query != null) {
			list = query.list();
			if (list != null && list.size() > 0) {
				return list;
			}
		}
		return list;
	}

	public List<AgentAction> getAgentActionList(Long type) throws AppException {
		List<AgentAction> list = new ArrayList<AgentAction>();
		Hql hql = new Hql();
		hql.add("from AgentAction a where a.type=" + type);
		Query query = this.getQuery(hql);
		if (query != null) {
			list = query.list();
			if (list != null && list.size() > 0) {
				return list;
			}
		}
		return list;
	}

	public List<AgentAction> getValidAgentActionList() throws AppException {
		List<AgentAction> list = new ArrayList<AgentAction>();
		Hql hql = new Hql();
		hql.add("from AgentAction a where 1=1 ");
		hql.add("and a.status= " + AgentAction.STATES_1);
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
