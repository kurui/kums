package com.kurui.kums.agent.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import com.kurui.kums.agent.AgentCoterie;
import com.kurui.kums.agent.AgentCoterieListForm;
import com.kurui.kums.base.database.hibernate.BaseDAOSupport;
import com.kurui.kums.base.database.hibernate.Hql;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.base.Constant;

public class AgentCoterieDAOImp extends BaseDAOSupport implements
		AgentCoterieDAO {

	public List list(AgentCoterieListForm agentCoterieListForm)
			throws AppException {
		Hql hql = new Hql();
		hql.add("from AgentCoterie a where 1=1");
		if (Constant.toLong(agentCoterieListForm.getSubAgentId()) > 0) {
			hql.add(" and a.subAgent.id="
					+ agentCoterieListForm.getSubAgentId());
		}

		hql.add("and a.status= " + AgentCoterie.STATES_1);

		return this.list(hql, agentCoterieListForm);
	}

	public void delete(long id) throws AppException {
		if (id > 0) {
			AgentCoterie agent = (AgentCoterie) this.getHibernateTemplate()
					.get(AgentCoterie.class, new Long(id));
			this.getHibernateTemplate().delete(agent);
		}
	}

	public long save(AgentCoterie agent) throws AppException {
		this.getHibernateTemplate().save(agent);
		return agent.getId();
	}

	public long update(AgentCoterie agent) throws AppException {
		if (agent.getId() > 0) {
			this.getHibernateTemplate().update(agent);
			return agent.getId();
		} else
			throw new IllegalArgumentException("id isn't a valid argument.");
	}

	public AgentCoterie getAgentCoterieById(long id) throws AppException {
		Hql hql = new Hql();
		hql.add("from AgentCoterie a where a.id=" + id);
		Query query = this.getQuery(hql);
		AgentCoterie agent = null;
		if (query != null && query.list() != null && query.list().size() > 0) {
			agent = (AgentCoterie) query.list().get(0);
		}
		return agent;
	}

	public AgentCoterie getAgentCoterieBySubAgentId(long subAgentId)
			throws AppException {
		Hql hql = new Hql();
		hql.add("from AgentCoterie a where a.subAgent.id=" + subAgentId);
		Query query = this.getQuery(hql);
		AgentCoterie agent = null;
		if (query != null && query.list() != null && query.list().size() > 0) {
			agent = (AgentCoterie) query.list().get(0);
		}
		return agent;
	}

	public List<AgentCoterie> getAgentCoterieList() throws AppException {
		List<AgentCoterie> list = new ArrayList<AgentCoterie>();
		Hql hql = new Hql();
		hql.add("from AgentCoterie");
		Query query = this.getQuery(hql);
		if (query != null) {
			list = query.list();
			if (list != null && list.size() > 0) {
				return list;
			}
		}
		return list;
	}

	public List<AgentCoterie> getAgentCoterieList(Long type)
			throws AppException {
		List<AgentCoterie> list = new ArrayList<AgentCoterie>();
		Hql hql = new Hql();
		hql.add("from AgentCoterie a where a.type=" + type);
		Query query = this.getQuery(hql);
		if (query != null) {
			list = query.list();
			if (list != null && list.size() > 0) {
				return list;
			}
		}
		return list;
	}

	public List<AgentCoterie> getValidAgentCoterieList() throws AppException {
		List<AgentCoterie> list = new ArrayList<AgentCoterie>();
		Hql hql = new Hql();
		hql.add("from AgentCoterie a where 1=1 ");
		hql.add("and a.status= " + AgentCoterie.STATES_1);
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
