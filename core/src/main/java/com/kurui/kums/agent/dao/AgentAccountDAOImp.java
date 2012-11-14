package com.kurui.kums.agent.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;

import com.kurui.kums.agent.AgentAccount;
import com.kurui.kums.agent.AgentAccountListForm;
import com.kurui.kums.base.Constant;
import com.kurui.kums.base.database.hibernate.BaseDAOSupport;
import com.kurui.kums.base.database.hibernate.Hql;
import com.kurui.kums.base.exception.AppException;

public class AgentAccountDAOImp extends BaseDAOSupport implements
		AgentAccountDAO {

	public List list(AgentAccountListForm agentAccountListForm)
			throws AppException {
		Hql hql = new Hql();
		hql.add("from AgentAccount a where 1=1");
		if (Constant.toLong(agentAccountListForm.getAgentId()) > 0) {
			hql.add(" and a.agent.id=" + agentAccountListForm.getAgentId());
		}

		if (Constant.toLong(agentAccountListForm.getAccountId()) > 0) {
			hql.add(" and a.account.id=" + agentAccountListForm.getAccountId());
		}
		
		hql.add(" and a.status="+AgentAccount.STATES_1);

		return this.list(hql, agentAccountListForm);
	}

	public void delete(long id) throws AppException {
		if (id > 0) {
			AgentAccount agent = (AgentAccount) this.getHibernateTemplate()
					.get(AgentAccount.class, new Long(id));
			this.getHibernateTemplate().delete(agent);
		}
	}

	public long save(AgentAccount agent) throws AppException {
		this.getHibernateTemplate().save(agent);
		return agent.getId();
	}

	public long update(AgentAccount agent) throws AppException {
		if (agent.getId() > 0) {
			this.getHibernateTemplate().update(agent);
			return agent.getId();
		} else
			throw new IllegalArgumentException("id isn't a valid argument.");
	}

	public AgentAccount getAgentAccountByAgentId(long agentId)
			throws AppException {
		Hql hql = new Hql();
		hql.add("from AgentAccount a where a.agent.id=" + agentId);
		Query query = this.getQuery(hql);
		AgentAccount agent = null;
		if (query != null && query.list() != null && query.list().size() > 0) {
			agent = (AgentAccount) query.list().get(0);
		}
		return agent;
	}

	public AgentAccount getAgentAccountByAccountId(long accountId)
			throws AppException {
		Hql hql = new Hql();
		hql.add("from AgentAccount a where a.account.id=" + accountId);
		Query query = this.getQuery(hql);
		AgentAccount agent = null;
		if (query != null && query.list() != null && query.list().size() > 0) {
			agent = (AgentAccount) query.list().get(0);
		}
		return agent;
	}

	public List<AgentAccount> getAgentAccountList() throws AppException {
		List<AgentAccount> list = new ArrayList<AgentAccount>();
		Hql hql = new Hql();
		hql.add("from AgentAccount");
		Query query = this.getQuery(hql);
		if (query != null) {
			list = query.list();
			if (list != null && list.size() > 0) {
				return list;
			}
		}
		return list;
	}

	public List<AgentAccount> getAgentAccountList(Long type)
			throws AppException {
		List<AgentAccount> list = new ArrayList<AgentAccount>();
		Hql hql = new Hql();
		hql.add("from AgentAccount a where a.type=" + type);
		Query query = this.getQuery(hql);
		if (query != null) {
			list = query.list();
			if (list != null && list.size() > 0) {
				return list;
			}
		}
		return list;
	}

	public List<AgentAccount> getValidAgentAccountList() throws AppException {
		List<AgentAccount> list = new ArrayList<AgentAccount>();
		Hql hql = new Hql();
		hql.add("from AgentAccount a where 1=1 ");
		hql.add("and a.status= " + AgentAccount.STATES_1);
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
