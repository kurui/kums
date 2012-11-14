package com.kurui.kums.market.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import com.kurui.kums.base.database.hibernate.BaseDAOSupport;
import com.kurui.kums.base.database.hibernate.Hql;
import com.kurui.kums.base.database.jdbc.SelectDataBean;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.base.Constant;
import com.kurui.kums.base.util.StringUtil;
import com.kurui.kums.market.PriceIndex;
import com.kurui.kums.market.PriceIndexListForm;

public class PriceIndexDAOImp extends BaseDAOSupport implements PriceIndexDAO {

	static Logger logger = Logger.getLogger(PriceIndexDAOImp.class.getName());

	public List list(PriceIndexListForm plf) throws AppException {
		Hql hql = new Hql();
		hql.add("from PriceIndex p where 1=1");

		if (Constant.toLong(plf.getPriceReferenceType()) > 0) {
			hql
					.add(" and p.priceReference.type="
							+ plf.getPriceReferenceType());
		}

		if (plf.getPriceReferenceId() > 0) {
			hql.add(" and p.priceReference.id=" + plf.getPriceReferenceId());
		}
		String priceReferenceIds = plf.getPriceReferenceIds();
		if ("".equals(priceReferenceIds) == false) {
			hql.add(" and p.priceReference.id in(" + priceReferenceIds + " ) ");
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

		hql.add("and p.status=" + PriceIndex.STATES_1);

		hql
				.add(" order by  p.priceReference.type,p.priceReference.code,p.snatchTime desc ");

		// System.out.println(hql);

		return this.list(hql, plf);
	}

	public PriceIndex getPriceIndex(long priceReferenceId, String dateString)
			throws AppException {
		PriceIndex priceIndex = null;
		Hql hql = new Hql();
		hql.add("from PriceIndex p where 1=1 and p.status="
				+ PriceIndex.STATES_1);
		hql.add(" and p.priceReference.id in( " + priceReferenceId + " )");

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
					priceIndex = (PriceIndex) list.get(0);
				}
			}
		}
		return priceIndex;
	}

	public PriceIndex getPriceIndexByPhase(long priceReferenceId,
			String startDate, String endDate) throws AppException {
		PriceIndex priceIndex = null;
		Hql hql = new Hql();
		if (priceReferenceId > 0) {
			hql.add("from PriceIndex p where 1=1 and p.status="
					+ PriceIndex.STATES_1);
			hql.add(" and p.priceReference.id =" + priceReferenceId);

			hql
					.add(" and  p.snatchTime  between to_date(?,'yyyy-mm-dd hh24:mi:ss') ");
			hql.add(" and to_date(?,'yyyy-mm-dd hh24:mi:ss') ");
			hql.addParamter(startDate);
			hql.addParamter(endDate);

			hql.add(" and rownum=1 ");
		}

		// logger.info("getPriceIndexByMonth:" + hql);

		Query query = this.getQuery(hql);
		if (query != null) {
			List list = query.list();
			if (list != null) {
				if (list.size() > 0) {
					priceIndex = (PriceIndex) list.get(0);
				}
			}
		}
		return priceIndex;
	}

	public String[] getExistDateArray(long priceReferenceId)
			throws AppException {
		String sql = "";
		sql += " select p.snatch_Time from Price_Index p where 1=1 and p.status="
				+ PriceIndex.STATES_1;
		sql += " and p.reference_id=" + priceReferenceId + "  ";

		sql += " order by p.snatch_Time desc ";

		String sql2 = "";
		sql2 += "select  ppp.snatch_date from ( ";
		sql2 += " select distinct to_char(pp.snatch_Time,'yyyy-mm-dd') snatch_date from(";
		sql2 += sql;
		sql2 += " ) pp ";
		sql2 += " ) ppp order by ppp.snatch_date ";

//		logger.info("getExistDateArray(priceReferenceId,startDate,endDate)："
//				+ sql2);

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

	public String[] getExistDateArray(String priceReferenceId,
			String startDate, String endDate) throws AppException {
		String sql = "";
		sql += " select p.snatch_Time from Price_Index p where 1=1 and p.status="
				+ PriceIndex.STATES_1;
		sql += " and p.reference_id in ( " + priceReferenceId + " ) ";

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
		sql2 += " ) ppp order by ppp.snatch_date ";

		logger.info("getExistDateArray(priceReferenceId,startDate,endDate)："
				+ sql2);

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

	public String getReferenceIdsByIndexGroup(int[] indexIdGroup)
			throws AppException {
		String referenceIds = "";
		String indexIds = "";
		if (indexIdGroup != null) {
			indexIds = StringUtil.getStringByArray(indexIdGroup, ",");
		}

		if (indexIds != "") {
			String sql = "select distinct t.reference_id from price_index t where t.id in("
					+ indexIds + ")";

			SelectDataBean sdb = new SelectDataBean();
			sdb.setQuerySQL(sql);
			sdb.executeQuery();
			int rowcount = sdb.getRowCount();
			String[] result = new String[rowcount];
			for (int i = 1; i < rowcount + 1; i++) {
				String temp = sdb.getColValue(i, 1);
				result[i - 1] = temp;
				System.out.println("reference_id:" + temp);
			}

			referenceIds = StringUtil.getStringByArray(result, ",");

		}

		return referenceIds;
	}

	public void delete(long id) throws AppException {
		if (id > 0) {
			PriceIndex PriceIndex = (PriceIndex) this.getHibernateTemplate()
					.get(PriceIndex.class, new Long(id));
			this.getHibernateTemplate().delete(PriceIndex);
		}
	}

	public long save(PriceIndex PriceIndex) throws AppException {
		this.getHibernateTemplate().save(PriceIndex);
		return PriceIndex.getId();
	}

	public long update(PriceIndex PriceIndex) throws AppException {
		if (PriceIndex.getId() > 0) {
			this.getHibernateTemplate().update(PriceIndex);
			return PriceIndex.getId();
		} else
			throw new IllegalArgumentException("id isn't a valid argument.");
	}

	public PriceIndex getPriceIndexById(long id) throws AppException {
		PriceIndex priceIndex = null;
		Hql hql = new Hql();
		hql.add("from PriceIndex p where p.id=" + id);
		Query query = this.getQuery(hql);
		PriceIndex PriceIndex = null;
		if (query != null) {
			List<PriceIndex> list = query.list();
			if (list != null && list.size() > 0) {
				return list.get(0);
			}
		}
		return priceIndex;
	}

	/**
	 * indexIdsStr ('1','2','3')
	 */
	public List<PriceIndex> getPriceIndexByIds(String indexIdsStr)
			throws AppException {
		List<PriceIndex> list = new ArrayList<PriceIndex>();
		Hql hql = new Hql();
		hql.add("from PriceIndex p where p.id in (" + indexIdsStr + ")");
		Query query = this.getQuery(hql);
		PriceIndex PriceIndex = null;
		if (query != null) {
			if (query.list() != null) {
				if (query.list().size() > 0) {
					list = query.list();
				}
			}
		}
		return list;
	}

	public List<PriceIndex> getPriceIndexByReferenceId(long referenceId)
			throws AppException {
		List<PriceIndex> list = new ArrayList<PriceIndex>();
		Hql hql = new Hql();
		hql.add("from PriceIndex p where p.priceReference.id=" + referenceId);
		hql.add(" order by p.snatchTime desc ");
		Query query = this.getQuery(hql);
		PriceIndex PriceIndex = null;
		if (query != null) {
			if (query.list() != null) {
				if (query.list().size() > 0) {
					list = query.list();
				}
			}
		}
		return list;
	}

	public List<PriceIndex> getPriceIndexList() throws AppException {
		List<PriceIndex> list = new ArrayList<PriceIndex>();
		Hql hql = new Hql();
		hql.add("from PriceIndex p where 1=1 ");
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

	public List<PriceIndex> getValidPriceIndexList() throws AppException {
		List<PriceIndex> list = new ArrayList<PriceIndex>();
		Hql hql = new Hql();
		hql.add("from PriceIndex p where 1=1 and p.status="
				+ PriceIndex.STATES_1);
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
