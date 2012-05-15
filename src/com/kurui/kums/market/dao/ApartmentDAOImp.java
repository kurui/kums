package com.kurui.kums.market.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import com.kurui.kums.base.database.BaseDAOSupport;
import com.kurui.kums.base.database.Hql;
import com.kurui.kums.base.database.SelectDataBean;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.base.Constant;
import com.kurui.kums.market.Apartment;
import com.kurui.kums.market.ApartmentListForm;

public class ApartmentDAOImp extends BaseDAOSupport implements ApartmentDAO {

	static Logger logger = Logger.getLogger(ApartmentDAOImp.class.getName());

	public List list(ApartmentListForm plf) throws AppException {
		Hql hql = new Hql();
		hql.add("from Apartment p where 1=1");

		if (plf.getName() != null && (!(plf.getName().equals("")))) {
			hql.add(" and ");
			hql.add(" ( p.name like '%" + plf.getName().trim() + "%'");
			hql.add(" or p.estateDish.name like '%" + plf.getName().trim() + "%'");
			hql.add(" ) ");
		}

		if (Constant.toLong(plf.getTransactionType()) > 0) {
			hql.add(" and p.transactionType=" + plf.getTransactionType());
		}

		if (Constant.toLong(plf.getBusinessType()) > 0) {
			hql.add(" and p.businessType=" + plf.getBusinessType());
		}

		if (Constant.toLong(plf.getEstateDishType()) > 0) {
			hql.add(" and p.estateDish.type=" + plf.getEstateDishType());
		}

		if (plf.getEstateDishId() > 0) {
			hql.add(" and p.estateDish.id=" + plf.getEstateDishId());
		}
		String estateDishIds = plf.getEstateDishIds();
		if ("".equals(estateDishIds) == false) {
			hql.add(" and p.estateDish.id in(" + estateDishIds + " ) ");
		}
		// 按日期搜索
		String startDate = plf.getStartDate();
		String endDate = plf.getEndDate();
		if ("".equals(startDate) == false && "".equals(endDate) == false) {
			hql
					.add(" and  p.snatchTime  between to_date(?,'yyyy-mm-dd hh24:mi:ss') ");
			hql.add(" and to_date(?,'yyyy-mm-dd hh24:mi:ss') ");
			hql.addParamter(startDate);
			hql.addParamter(endDate);
		}

		hql.add("and p.status=" + Apartment.STATES_1);

		if (Constant.toString(plf.getOrderBy()) != "") {
			if (Constant.toString(plf.getOrderBy()) == "estateDishType") {
				hql.add(" order by  p.estateDish.type ");
			} else if (Constant.toString(plf.getOrderBy()) == "snatchTime") {
				hql.add(" order by  p.snatchTime ");
			} else if (Constant.toString(plf.getOrderBy()) == "connection") {
				hql.add(" order by  p.connection ");
			} else {
				hql.add(" order by  p.estateDish.type,p.snatchTime desc ");
			}
		} else {
			hql.add(" order by  p.estateDish.type,p.snatchTime desc ");
		}

		return this.list(hql, plf);
	}

	public Apartment getApartment(long estateDishId, String dateString)
			throws AppException {
		Apartment apartment = null;
		Hql hql = new Hql();
		hql
				.add("from Apartment p where 1=1 and p.status="
						+ Apartment.STATES_1);
		hql.add(" and p.estateDish.id in( " + estateDishId + " )");

		if ("".equals(dateString) == false && "".equals(dateString) == false) {
			hql
					.add(" and  p.snatchTime  between to_date(?,'yyyy-mm-dd hh24:mi:ss') ");
			hql.add(" and to_date(?,'yyyy-mm-dd hh24:mi:ss') ");
			hql.addParamter(dateString + " 00:00:00");
			hql.addParamter(dateString + " 23:59:59");
		}
		Query query = this.getQuery(hql);
		if (query != null) {
			List list = query.list();
			if (list != null) {
				if (list.size() > 0) {
					apartment = (Apartment) list.get(0);
				}
			}
		}
		return apartment;
	}

	public String[] getExistDateArray(String estateDishId, String startDate,
			String endDate) throws AppException {
		String sql = "";
		sql += " select p.snatch_Time from Price_Index p where 1=1 and p.status="
				+ Apartment.STATES_1;
		sql += " and p.reference_id in ( " + estateDishId + " ) ";

		if ("".equals(startDate) == false && "".equals(endDate) == false) {
			sql += " and  p.snatch_Time  between ";
			sql += "to_date('" + startDate + " 00:00:00" + "'";
			sql += ",'yyyy-mm-dd hh24:mi:ss')";
			sql += " and to_date('" + endDate + " 23:59:59" + "'";
			sql += ",'yyyy-mm-dd hh24:mi:ss')";
		}
		sql += " order by p.snatch_Time desc ";

		String sql2 = "";
		sql2 += "select  ppp.snatch_date from ( ";
		sql2 += " select distinct to_char(pp.snatch_Time,'yyyy-mm-dd') snatch_date from(";
		sql2 += sql;
		sql2 += " ) pp ";
		sql2 += " ) ppp order by ppp.snatch_time ";

		// System.out.println("hql：" + sql2);

		SelectDataBean sdb = new SelectDataBean();
		sdb.setQuerySQL(sql2);
		sdb.executeQuery();
		int rowcount = sdb.getRowCount();
		String[] result = new String[rowcount];
		for (int i = 1; i < rowcount + 1; i++) {
			String temp = sdb.getColValue(i, 1);
			result[i - 1] = temp;
			System.out.println("SNATCH_DATE:" + temp);
		}

		return result;
	}

	public void delete(long id) throws AppException {
		if (id > 0) {
			Apartment Apartment = (Apartment) this.getHibernateTemplate().get(
					Apartment.class, new Long(id));
			this.getHibernateTemplate().delete(Apartment);
		}
	}

	public long save(Apartment Apartment) throws AppException {
		this.getHibernateTemplate().save(Apartment);
		return Apartment.getId();
	}

	public long update(Apartment Apartment) throws AppException {
		if (Apartment.getId() > 0) {
			this.getHibernateTemplate().update(Apartment);
			return Apartment.getId();
		} else
			throw new IllegalArgumentException("id isn't a valid argument.");
	}

	public Apartment getApartmentById(long id) throws AppException {
		Apartment apartment = null;
		Hql hql = new Hql();
		hql.add("from Apartment p where p.id=" + id);
		Query query = this.getQuery(hql);
		Apartment Apartment = null;
		if (query != null) {
			List<Apartment> list = query.list();
			if (list != null && list.size() > 0) {
				return list.get(0);
			}
		}
		return apartment;
	}

	public List<Apartment> getApartmentByDishId(long referenceId)
			throws AppException {
		List<Apartment> list = new ArrayList<Apartment>();
		Hql hql = new Hql();
		hql.add("from Apartment p where p.estateDish.id=" + referenceId);
		Query query = this.getQuery(hql);
		Apartment Apartment = null;
		if (query != null) {
			if (query.list() != null) {
				if (query.list().size() > 0) {
					list = query.list();
				}
			}
		}
		return list;
	}

	public List<Apartment> getApartmentList() throws AppException {
		List<Apartment> list = new ArrayList<Apartment>();
		Hql hql = new Hql();
		hql.add("from Apartment p where 1=1 ");
		Query query = this.getQuery(hql);
		if (query != null) {
			if (query.list() != null) {
				if (query.list().size() > 0) {
					list = query.list();
				}
			}
		}
		return list;
	}

	public List<Apartment> getValidApartmentList() throws AppException {
		List<Apartment> list = new ArrayList<Apartment>();
		Hql hql = new Hql();
		hql
				.add("from Apartment p where 1=1 and p.status="
						+ Apartment.STATES_1);
		Query query = this.getQuery(hql);
		if (query != null) {
			if (query.list() != null) {
				if (query.list().size() > 0) {
					list = query.list();
				}
			}
		}
		return list;
	}

}
