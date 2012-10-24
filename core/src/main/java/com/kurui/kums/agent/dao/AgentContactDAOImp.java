package com.kurui.kums.agent.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;

import com.kurui.kums.agent.AgentContact;
import com.kurui.kums.agent.AgentContactListForm;
import com.kurui.kums.base.Constant;
import com.kurui.kums.base.database.BaseDAOSupport;
import com.kurui.kums.base.database.Hql;
import com.kurui.kums.base.exception.AppException;

public class AgentContactDAOImp extends BaseDAOSupport implements AgentContactDAO {

	public List list(AgentContactListForm agentActionListForm)
			throws AppException {
		Hql hql = new Hql();
		hql.add("from AgentContact a where 1=1");
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
		hql.add(" order by a.agent.type,a.agent.id ");
		return this.list(hql, agentActionListForm);
	}

	public void delete(long id) throws AppException {
		if (id > 0) {
			AgentContact agent = (AgentContact) this.getHibernateTemplate().get(
					AgentContact.class, new Long(id));
			this.getHibernateTemplate().delete(agent);
		}
	}

	public long save(AgentContact agent) throws AppException {
		this.getHibernateTemplate().save(agent);
		return agent.getId();
	}

	public long update(AgentContact agent) throws AppException {
		if (agent.getId() > 0) {
			this.getHibernateTemplate().update(agent);
			return agent.getId();
		} else
			throw new IllegalArgumentException("id isn't a valid argument.");
	}

	public AgentContact getAgentContactById(long id) throws AppException {
		Hql hql = new Hql();
		hql.add("from AgentContact a where a.id=" + id);
		Query query = this.getQuery(hql);
		AgentContact agent = null;
		if (query != null && query.list() != null && query.list().size() > 0) {
			agent = (AgentContact) query.list().get(0);
		}
		return agent;
	}

	public AgentContact getAgentContactByAgentId(long agentId)
			throws AppException {
		Hql hql = new Hql();
		hql.add("from AgentContact a where a.agent.id=" + agentId);
		Query query = this.getQuery(hql);
		AgentContact agent = null;
		if (query != null && query.list() != null && query.list().size() > 0) {
			agent = (AgentContact) query.list().get(0);
		}
		return agent;
	}

	public List<AgentContact> getAgentContactList() throws AppException {
		List<AgentContact> list = new ArrayList<AgentContact>();
		Hql hql = new Hql();
		hql.add("from AgentContact");
		Query query = this.getQuery(hql);
		if (query != null) {
			list = query.list();
			if (list != null && list.size() > 0) {
				return list;
			}
		}
		return list;
	}

	public List<AgentContact> getAgentContactList(Long type) throws AppException {
		List<AgentContact> list = new ArrayList<AgentContact>();
		Hql hql = new Hql();
		hql.add("from AgentContact a where a.type=" + type);
		Query query = this.getQuery(hql);
		if (query != null) {
			list = query.list();
			if (list != null && list.size() > 0) {
				return list;
			}
		}
		return list;
	}
	
	public List<AgentContact> getAgentContactListByAgent(Long agentId) {
		List<AgentContact> list = new ArrayList<AgentContact>();
		Hql hql = new Hql();
		hql.add("from AgentContact a where a.agent.id=" + agentId);
		Query query = this.getQuery(hql);
		if (query != null) {
			list = query.list();
			if (list != null && list.size() > 0) {
				return list;
			}
		}
		return list;
	}

	public List<AgentContact> getValidAgentContactList() throws AppException {
		List<AgentContact> list = new ArrayList<AgentContact>();
		Hql hql = new Hql();
		hql.add("from AgentContact a where 1=1 ");
		hql.add("and a.status= " + AgentContact.STATES_1);
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
