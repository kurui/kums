package com.kurui.kums.agent.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import com.kurui.kums.agent.Coterie;
import com.kurui.kums.agent.CoterieListForm;
import com.kurui.kums.base.database.BaseDAOSupport;
import com.kurui.kums.base.database.Hql;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.base.Constant;

public class CoterieDAOImp extends BaseDAOSupport implements
		CoterieDAO {

	public List list(CoterieListForm agentCoterieListForm)
			throws AppException {
		Hql hql = new Hql();
		hql.add("from Coterie a where 1=1");
		if (Constant.toLong(agentCoterieListForm.getRootAgentId()) > 0) {
			hql.add(" and a.rootAgent.id=" + agentCoterieListForm.getRootAgentId());
		}

		return this.list(hql, agentCoterieListForm);
	}

	public void delete(long id) throws AppException {
		if (id > 0) {
			Coterie agent = (Coterie) this.getHibernateTemplate()
					.get(Coterie.class, new Long(id));
			this.getHibernateTemplate().delete(agent);
		}
	}

	public long save(Coterie agent) throws AppException {
		this.getHibernateTemplate().save(agent);
		return agent.getId();
	}

	public long update(Coterie agent) throws AppException {
		if (agent.getId() > 0) {
			this.getHibernateTemplate().update(agent);
			return agent.getId();
		} else
			throw new IllegalArgumentException("id isn't a valid argument.");
	}

	public Coterie getCoterieById(long id) throws AppException {
		Hql hql = new Hql();
		hql.add("from Coterie a where a.id=" + id);
		Query query = this.getQuery(hql);
		Coterie agent = null;
		if (query != null && query.list() != null && query.list().size() > 0) {
			agent = (Coterie) query.list().get(0);
		}
		return agent;
	}

	public Coterie getCoterieByRootAgentId(long rootAgentId)
			throws AppException {
		Hql hql = new Hql();
		hql.add("from Coterie a where a.rootAgent.id=" + rootAgentId);
		Query query = this.getQuery(hql);
		Coterie agent = null;
		if (query != null && query.list() != null && query.list().size() > 0) {
			agent = (Coterie) query.list().get(0);
		}
		return agent;
	}

	public List<Coterie> getCoterieList() throws AppException {
		List<Coterie> list = new ArrayList<Coterie>();
		Hql hql = new Hql();
		hql.add("from Coterie");
		Query query = this.getQuery(hql);
		if (query != null) {
			list = query.list();
			if (list != null && list.size() > 0) {
				return list;
			}
		}
		return list;
	}

	public List<Coterie> getCoterieList(Long type)
			throws AppException {
		List<Coterie> list = new ArrayList<Coterie>();
		Hql hql = new Hql();
		hql.add("from Coterie a where a.type=" + type);
		Query query = this.getQuery(hql);
		if (query != null) {
			list = query.list();
			if (list != null && list.size() > 0) {
				return list;
			}
		}
		return list;
	}

	public List<Coterie> getValidCoterieList() throws AppException {
		List<Coterie> list = new ArrayList<Coterie>();
		Hql hql = new Hql();
		hql.add("from Coterie a where 1=1 ");
		// hql.add("and a.status= "+ Coterie.STATES_1);
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
