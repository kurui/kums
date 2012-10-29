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

	public List list(AgentContactListForm alf)
			throws AppException {
		Hql hql = new Hql();
		hql.add("from AgentContact a where 1=1");
		if (Constant.toLong(alf.getAgentId()) > 0) {
			hql.add(" and a.agent.id=" + alf.getAgentId());
		}
		if (Constant.toString(alf.getKeywords()) != "") {
			hql.add(" and( ");
			hql.add(" a.agent.name like '%"
					+ alf.getKeywords().trim() + "%'");
			hql.add(" or a.agent.agentNo like '%"
					+ alf.getKeywords().trim() + "%'");
			hql.add(" or a.content like '%"
					+ alf.getKeywords().trim() + "%'");
			
			hql.add(" ) ");
		}
		if (Constant.toLong(alf.getType()) >0) {
			hql.add(" and a.type="+alf.getType());
		}
		if (Constant.toLong(alf.getStatus()) >0) {
		hql.add(" and a.status="+alf.getStatus());
		}
		hql.add(" order by a.agent.type,a.agent.id ");
		System.out.println(hql);
		return this.list(hql, alf);
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
		hql.add("from AgentContact a where 1=1 and a.agent.id=" + agentId);
		
		hql.add(" and a.status not in("+AgentContact.STATES_0+")");
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
