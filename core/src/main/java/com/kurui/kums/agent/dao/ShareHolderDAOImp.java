package com.kurui.kums.agent.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import com.kurui.kums.agent.ShareHolder;
import com.kurui.kums.agent.ShareHolderListForm;
import com.kurui.kums.base.database.hibernate.BaseDAOSupport;
import com.kurui.kums.base.database.hibernate.Hql;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.base.Constant;

public class ShareHolderDAOImp extends BaseDAOSupport implements ShareHolderDAO {

	public List list(ShareHolderListForm shareHolderListForm)
			throws AppException {
		Hql hql = new Hql();
		hql.add("from ShareHolder a where 1=1");
		if (Constant.toLong(shareHolderListForm.getAgentId()) > 0) {
			hql.add(" and a.agent.id=" + shareHolderListForm.getAgentId());
		}

		if (Constant.toLong(shareHolderListForm.getCompanyId()) > 0) {
			hql.add(" and a.account.id=" + shareHolderListForm.getCompanyId());
		}

		hql.add(" and a.status=" + ShareHolder.STATES_1);

		String orderBy = Constant.toString(shareHolderListForm.getOrderBy());
		if (orderBy != "") {
			if (orderBy == "holder") {
				hql.add(" order by a.agent.id,a.controlType ");
			} else if (orderBy == "company") {
				hql.add(" order by a.company.id,a.controlType ");
			}
		}

		return this.list(hql, shareHolderListForm);
	}

	public void delete(long id) throws AppException {
		if (id > 0) {
			ShareHolder agent = (ShareHolder) this.getHibernateTemplate().get(
					ShareHolder.class, new Long(id));
			this.getHibernateTemplate().delete(agent);
		}
	}

	public long save(ShareHolder agent) throws AppException {
		this.getHibernateTemplate().save(agent);
		return agent.getId();
	}

	public long update(ShareHolder agent) throws AppException {
		if (agent.getId() > 0) {
			this.getHibernateTemplate().update(agent);
			return agent.getId();
		} else
			throw new IllegalArgumentException("id isn't a valid argument.");
	}

	public ShareHolder getShareHolderById(long id) throws AppException {
		Hql hql = new Hql();
		hql.add("from ShareHolder a where a.id=" + id);
		Query query = this.getQuery(hql);
		ShareHolder agent = null;
		if (query != null && query.list() != null && query.list().size() > 0) {
			agent = (ShareHolder) query.list().get(0);
		}
		return agent;
	}

	public ShareHolder getShareHolderByAgentId(long agentId)
			throws AppException {
		Hql hql = new Hql();
		hql.add("from ShareHolder a where a.agent.id=" + agentId);
		Query query = this.getQuery(hql);
		ShareHolder agent = null;
		if (query != null && query.list() != null && query.list().size() > 0) {
			agent = (ShareHolder) query.list().get(0);
		}
		return agent;
	}

	public ShareHolder getShareHolderByCompanyId(long accountId)
			throws AppException {
		Hql hql = new Hql();
		hql.add("from ShareHolder a where a.company.id=" + accountId);
		Query query = this.getQuery(hql);
		ShareHolder agent = null;
		if (query != null && query.list() != null && query.list().size() > 0) {
			agent = (ShareHolder) query.list().get(0);
		}
		return agent;
	}

	public List<ShareHolder> getShareHolderList() throws AppException {
		List<ShareHolder> list = new ArrayList<ShareHolder>();
		Hql hql = new Hql();
		hql.add("from ShareHolder");
		Query query = this.getQuery(hql);
		if (query != null) {
			list = query.list();
			if (list != null && list.size() > 0) {
				return list;
			}
		}
		return list;
	}

	public List<ShareHolder> getShareHolderList(Long type) throws AppException {
		List<ShareHolder> list = new ArrayList<ShareHolder>();
		Hql hql = new Hql();
		hql.add("from ShareHolder a where a.type=" + type);
		Query query = this.getQuery(hql);
		if (query != null) {
			list = query.list();
			if (list != null && list.size() > 0) {
				return list;
			}
		}
		return list;
	}

	public List<ShareHolder> getValidShareHolderList() throws AppException {
		List<ShareHolder> list = new ArrayList<ShareHolder>();
		Hql hql = new Hql();
		hql.add("from ShareHolder a where 1=1 ");
		hql.add("and a.status= " + ShareHolder.STATES_1);
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
