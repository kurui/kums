package com.kurui.kums.agent.dao;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import com.kurui.kums.agent.AgentHabit;
import com.kurui.kums.agent.AgentHabitListForm;
import com.kurui.kums.base.database.BaseDAOSupport;
import com.kurui.kums.base.database.Hql;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.base.Constant;

public class AgentHabitDAOImp extends BaseDAOSupport implements AgentHabitDAO {
	static Logger logger = Logger.getLogger(AgentHabitDAOImp.class.getName());

	public List list(AgentHabitListForm agentHabitListForm) throws AppException {
		Hql hql = new Hql();
		hql.add(" select new AgentHabit(t,a) from AgentHabit a,Agent t where 1=1");
		if (Constant.toLong(agentHabitListForm.getAgentId()) > 0) {
			hql.add(" and a.agent.id=" + agentHabitListForm.getAgentId());
		}

		if (Constant.toString(agentHabitListForm.getContactWay()) != "") {
			hql.add(" and( ");
			hql.add(" t.name like '%"
					+ agentHabitListForm.getContactWay().trim() + "%'");
			hql.add(" or t.agentNo like '%"
					+ agentHabitListForm.getContactWay().trim() + "%'");
			hql.add(" ) ");
		}

		hql.add(" and t.agentHabit.id=a.id ");

		logger.info(hql);

		return this.list(hql, agentHabitListForm);
	}

	public void delete(long id) throws AppException {
		if (id > 0) {
			AgentHabit agent = (AgentHabit) this.getHibernateTemplate().get(
					AgentHabit.class, new Long(id));
			this.getHibernateTemplate().delete(agent);
		}
	}

	public long save(AgentHabit agent) throws AppException {
		this.getHibernateTemplate().save(agent);
		return agent.getId();
	}

	public long update(AgentHabit agent) throws AppException {
		if (agent.getId() > 0) {
			this.getHibernateTemplate().update(agent);
			return agent.getId();
		} else
			throw new IllegalArgumentException("id isn't a valid argument.");
	}

	public AgentHabit getAgentHabitById(long id) throws AppException {
		Hql hql = new Hql();
		hql.add("from AgentHabit a where a.id=" + id);
		Query query = this.getQuery(hql);
		AgentHabit agent = null;
		if (query != null && query.list() != null && query.list().size() > 0) {
			agent = (AgentHabit) query.list().get(0);
		}
		return agent;
	}


	public List<AgentHabit> getAgentHabitList() throws AppException {
		List<AgentHabit> list = new ArrayList<AgentHabit>();
		Hql hql = new Hql();
		hql.add("from AgentHabit");
		Query query = this.getQuery(hql);
		if (query != null) {
			list = query.list();
			if (list != null && list.size() > 0) {
				return list;
			}
		}
		return list;
	}

	public List<AgentHabit> getAgentHabitList(Long type) throws AppException {
		List<AgentHabit> list = new ArrayList<AgentHabit>();
		Hql hql = new Hql();
		hql.add("from AgentHabit a where a.type=" + type);
		Query query = this.getQuery(hql);
		if (query != null) {
			list = query.list();
			if (list != null && list.size() > 0) {
				return list;
			}
		}
		return list;
	}

	public List<AgentHabit> getValidAgentHabitList() throws AppException {
		List<AgentHabit> list = new ArrayList<AgentHabit>();
		Hql hql = new Hql();
		hql.add("from AgentHabit a where 1=1 ");
		// hql.add("and a.status= "+ AgentHabit.STATES_1);
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
