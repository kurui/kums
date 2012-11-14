package com.kurui.kums.agent.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import com.kurui.kums.agent.Vehicle;
import com.kurui.kums.agent.VehicleListForm;
import com.kurui.kums.base.database.hibernate.BaseDAOSupport;
import com.kurui.kums.base.database.hibernate.Hql;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.base.Constant;

public class VehicleDAOImp extends BaseDAOSupport implements VehicleDAO {

	public List list(VehicleListForm vehicleListForm) throws AppException {
		Hql hql = new Hql();
		hql.add("from Vehicle a where 1=1");
		if (Constant.toLong(vehicleListForm.getAgentId()) > 0) {
			hql.add(" and a.agent.id=" + vehicleListForm.getAgentId());
		}
		
		if (Constant.toString(vehicleListForm.getContactWay()) != "") {
			hql.add(" and( ");
			hql.add(" a.agent.name like '%"
					+ vehicleListForm.getContactWay().trim() + "%'");
			hql.add(" or a.agent.agentNo like '%"
					+ vehicleListForm.getContactWay().trim() + "%'");
			hql.add(" ) ");
		}

		return this.list(hql, vehicleListForm);
	}

	public void delete(long id) throws AppException {
		if (id > 0) {
			Vehicle agent = (Vehicle) this.getHibernateTemplate().get(
					Vehicle.class, new Long(id));
			this.getHibernateTemplate().delete(agent);
		}
	}

	public long save(Vehicle agent) throws AppException {
		this.getHibernateTemplate().save(agent);
		return agent.getId();
	}

	public long update(Vehicle agent) throws AppException {
		if (agent.getId() > 0) {
			this.getHibernateTemplate().update(agent);
			return agent.getId();
		} else
			throw new IllegalArgumentException("id isn't a valid argument.");
	}

	public Vehicle getVehicleById(long id) throws AppException {
		Hql hql = new Hql();
		hql.add("from Vehicle a where a.id=" + id);
		Query query = this.getQuery(hql);
		Vehicle agent = null;
		if (query != null && query.list() != null && query.list().size() > 0) {
			agent = (Vehicle) query.list().get(0);
		}
		return agent;
	}

	public Vehicle getVehicleByAgentId(long agentId) throws AppException {
		Hql hql = new Hql();
		hql.add("from Vehicle a where a.agent.id=" + agentId);
		Query query = this.getQuery(hql);
		Vehicle agent = null;
		if (query != null && query.list() != null && query.list().size() > 0) {
			agent = (Vehicle) query.list().get(0);
		}
		return agent;
	}

	public List<Vehicle> getVehicleList(Long type) throws AppException {
		List<Vehicle> list = new ArrayList<Vehicle>();
		Hql hql = new Hql();
		hql.add("from Vehicle a where a.type=" + type);
		Query query = this.getQuery(hql);
		if (query != null) {
			list = query.list();
			if (list != null && list.size() > 0) {
				return list;
			}
		}
		return list;
	}

	public List<Vehicle> getValidVehicleList() throws AppException {
		List<Vehicle> list = new ArrayList<Vehicle>();
		Hql hql = new Hql();
		hql.add("from Vehicle a where 1=1 ");
		// hql.add("and a.status= "+ Vehicle.STATES_1);
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
