package com.kurui.kums.agent.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;

import com.kurui.kums.agent.Agent;
import com.kurui.kums.agent.AgentListForm;
import com.kurui.kums.base.Constant;
import com.kurui.kums.base.database.hibernate.BaseDAOSupport;
import com.kurui.kums.base.database.hibernate.Hql;
import com.kurui.kums.base.database.jdbc.DBExecuteBean;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.finance.FinanceOrder;

public class AgentDAOImp extends BaseDAOSupport implements AgentDAO {
	static Logger logger = Logger.getLogger(AgentDAOImp.class.getName());

	public List list(AgentListForm agentListForm) throws AppException {
		Hql hql = new Hql();
		hql.add("from Agent a where 1=1");
		hql = listHql(agentListForm, hql);

		hql.add(" order by a.type,a.updateTime desc");

		return this.list(hql, agentListForm);
	}

	public List listGrade(AgentListForm agentListForm) throws AppException {
		Hql hql = new Hql();
		hql.add("from Agent a where 1=1");
		hql = listHql(agentListForm, hql);

		if (Constant.toLong(agentListForm.getLoyalIndex()) > 0) {
			hql.add(" and a.loyalIndex=" + agentListForm.getLoyalIndex());
		}
		if (Constant.toLong(agentListForm.getFriendIndex()) > 0) {
			hql.add("and a.friendIndex=" + agentListForm.getFriendIndex());
		}
		if (Constant.toLong(agentListForm.getAssetIndex()) > 0) {
			hql.add("and a.assetIndex=" + agentListForm.getAssetIndex());
		}
		if (Constant.toLong(agentListForm.getSpecialIndex()) > 0) {
			hql.add("and a.specialIndex=" + agentListForm.getSpecialIndex());
		}
		if (Constant.toLong(agentListForm.getTightIndex()) > 0) {
			hql.add("and a.tightIndex=" + agentListForm.getTightIndex());
		}

		if (Constant.toString(agentListForm.getOrderBy()).equals("loyalIndex")) {
			hql.add(" order by a.loyalIndex ");
		} else if (Constant.toString(agentListForm.getOrderBy()).equals(
				"friendIndex")) {
			hql.add(" order by a.friendIndex ");
		} else if (Constant.toString(agentListForm.getOrderBy()).equals(
				"assetIndex")) {
			hql.add(" order by a.assetIndex desc ");
		} else if (Constant.toString(agentListForm.getOrderBy()).equals(
				"specialIndex")) {
			hql.add(" order by a.specialIndex ");
		} else if (Constant.toString(agentListForm.getOrderBy()).equals(
				"tightIndex")) {
			hql.add(" order by a.tightIndex ");
		} else {
			hql.add(" order by a.type ");
		}

		logger.info("listGrade:" + hql.getSql());

		return this.list(hql, agentListForm);
	}

	public List listDirect(AgentListForm agentListForm) throws AppException {
		Hql hql = new Hql();
		hql.add("from Agent a where 1=1");
		hql = listHql(agentListForm, hql);

		if (Constant.toString(agentListForm.getDirectLevelIds()) != "") {
			hql.add(" and a.directLevel.id in("
					+ agentListForm.getDirectLevelIds() + ")");
		}

		hql.add(" order by a.directLevel.seqIndex desc");

		return this.list(hql, agentListForm);
	}

	public Hql listHql(AgentListForm agentListForm, Hql hql)
			throws AppException {
		if (Constant.toLong(agentListForm.getCompanyId()) > 0) {
			hql.add(" and a.company.id=" + agentListForm.getCompanyId());
		}

//		if (Constant.toString(agentListForm.getName()) != "") {
//			hql.add(" and a.name like '%" + agentListForm.getName().trim()
//					+ "%'");
//		}
		
		
		if (Constant.toString(agentListForm.getContactWay()) != "") {
			hql.add(" and( ");
			hql.add(" a.name like '%" + agentListForm.getContactWay().trim()
					+ "%'");
			hql.add(" or a.agentNo like '%"
					+ agentListForm.getContactWay().trim() + "%'");
			hql.add(" ) ");
		}
		
		if (agentListForm.getType() > 0) {
			hql.add(" and a.type=" + agentListForm.getType());
		}

		if(Constant.toString(agentListForm.getKnowPlace())!=""){
			hql.add(" and a.knowPlace like '%"+agentListForm.getKnowPlace()+"%' ");
		}
		
	

		hql.add("and a.status not in(" + Agent.STATES_0 + ")");

		return hql;
	}

	public void delete(long id) throws AppException {
		if (id > 0) {
			Agent agent = (Agent) this.getHibernateTemplate().get(Agent.class,
					new Long(id));
			this.getHibernateTemplate().delete(agent);
		}
	}

	public long save(Agent agent) throws AppException {
		this.getHibernateTemplate().save(agent);
		return agent.getId();
	}

	public long update(Agent agent) throws AppException {
		if (agent.getId() > 0) {
			this.getHibernateTemplate().update(agent);
			return 1;
		} else
			throw new IllegalArgumentException("id isn't a valid argument.");
	}

	public Agent getAgentById(long agentId) throws AppException {
		Hql hql = new Hql();
		hql.add("from Agent a where a.id=" + agentId);
		Query query = this.getQuery(hql);
		Agent agent = null;
		if (query != null) {
			List<Agent> list = query.list();
			if (list != null && list.size() > 0) {
				agent = list.get(0);
			}
		}
		return agent;
	}

	public Agent getAgentByNo(String agentNo) throws AppException {
		Hql hql = new Hql();
		hql.add("from Agent a where a.agentNo='" + agentNo + "'");
		Query query = this.getQuery(hql);
		Agent agent = null;
		if (query != null) {
			List<Agent> list = query.list();
			if (list != null && list.size() > 0) {
				agent = list.get(0);
			}
		}
		return agent;
	}

	public List<Agent> getAgentList() throws AppException {
		List<Agent> list = new ArrayList<Agent>();
		Hql hql = new Hql();
		hql.add("from Agent");
		Query query = this.getQuery(hql);
		if (query != null) {
			list = query.list();
			if (list != null && list.size() > 0) {
				return list;
			}
		}
		return list;
	}

	public List<Agent> getAgentList(Long type) throws AppException {
		List<Agent> list = new ArrayList<Agent>();
		Hql hql = new Hql();
		hql.add("from Agent a where a.type=" + type);
		Query query = this.getQuery(hql);
		if (query != null) {
			list = query.list();
			if (list != null && list.size() > 0) {
				return list;
			}
		}
		return list;
	}

	public List<Agent> getValidAgentList() throws AppException {
		List<Agent> list = new ArrayList<Agent>();
		Hql hql = new Hql();
		hql.add("from Agent a where 1=1 and a.status= " + Agent.STATES_1);
		hql.add(" order by a.name ");
		Query query = this.getQuery(hql);
		if (query != null) {
			list = query.list();
			if (list != null && list.size() > 0) {
				return list;
			}
		}
		return list;
	}

	// 实物金额 physical_amount NUMBER(15,4) FALSE FALSE FALSE
	public void updatePhysicalAmount(long agentId) throws AppException {
		String sql = "";
		if (agentId > 0) {
			sql += "update agent a set  a.physical_amount =(";
			sql += " select sum(s.total_amount)  from Statement s where 1=1 ";
			sql += " and s.order_id in(";
			sql += " select o.id  from finance_order o where o.agent_id="
					+ agentId;

			sql += " and o.tran_type in( " + FinanceOrder.TRAN_TYPE_GROUP_15
					+ " ) ";
			sql += " and o.tran_type not in( " + FinanceOrder.TRANTYPE_1560
					+ " ) ";
			sql += " ) ";
			sql += ") where a.id=" + agentId;
		}
		logger.info("sql>>>>>\n" + sql);

		if (sql != "") {
			try {
				DBExecuteBean db = new DBExecuteBean();
				db.executeUpdateSQL(sql);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	// 虚拟金额 virtual_amount NUMBER(15,4) FALSE FALSE FALSE
	public void updateVirtualAmount(long agentId) throws AppException {
		String sql = "";
		if (agentId > 0) {
			sql += "update agent a set  a.virtual_amount =(";
			sql += " select sum(s.total_amount)  from Statement s where 1=1 ";
			sql += " and s.order_id in(";
			sql += " select o.id  from finance_order o where o.agent_id="
					+ agentId;

			sql += " and o.tran_type in( " + FinanceOrder.TRAN_TYPE_GROUP_15
					+ " ) ";
			sql += " and o.tran_type in( " + FinanceOrder.TRANTYPE_1560 + " ) ";

			sql += " ) ";
			sql += ") where a.id=" + agentId;
		}
		logger.info("sql>>>>>\n" + sql);
		if (sql != "") {
			try {
				DBExecuteBean db = new DBExecuteBean();
				db.executeUpdateSQL(sql);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	// 消费积分 total_integral NUMBER(15,4) FALSE FALSE FALSE
	public void updateTotalIntegral(long agentId) throws AppException {
		String sql = "";
		if (agentId > 0) {
			sql += "update agent a set  a.total_integral = (";
			sql += " (a.physical_amount*1) + ";
			sql += " (a.virtual_amount*0.5) ";
			sql += " ) ";
			sql += " where a.id=" + agentId;
		}
		logger.info("sql>>>>>\n" + sql);
		if (sql != "") {
			try {
				DBExecuteBean db = new DBExecuteBean();
				db.executeUpdateSQL(sql);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

}
