package com.kurui.kums.library.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;

import com.kurui.kums.base.Constant;
import com.kurui.kums.base.database.hibernate.BaseDAOSupport;
import com.kurui.kums.base.database.hibernate.Hql;
import com.kurui.kums.base.database.jdbc.SelectDataBean;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.library.ImageLibrary;
import com.kurui.kums.library.ImageLibraryListForm;

public class ImageLibraryDAOImp extends BaseDAOSupport implements ImageLibraryDAO {

	static Logger logger = Logger.getLogger(ImageLibraryDAOImp.class.getName());

	public List list(ImageLibraryListForm plf) throws AppException {
		Hql hql = new Hql();
		hql.add("from ImageLibrary p where 1=1");



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

		hql.add("and p.status=" + ImageLibrary.STATES_1);

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

	public ImageLibrary getImageLibrary(long estateDishId, String dateString)
			throws AppException {
		ImageLibrary imageLibrary = null;
		Hql hql = new Hql();
		hql
				.add("from ImageLibrary p where 1=1 and p.status="
						+ ImageLibrary.STATES_1);
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
					imageLibrary = (ImageLibrary) list.get(0);
				}
			}
		}
		return imageLibrary;
	}

	public String[] getExistDateArray(String estateDishId, String startDate,
			String endDate) throws AppException {
		String sql = "";
		sql += " select p.snatch_Time from Price_Index p where 1=1 and p.status="
				+ ImageLibrary.STATES_1;
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
			ImageLibrary ImageLibrary = (ImageLibrary) this.getHibernateTemplate().get(
					ImageLibrary.class, new Long(id));
			this.getHibernateTemplate().delete(ImageLibrary);
		}
	}

	public long save(ImageLibrary ImageLibrary) throws AppException {
		this.getHibernateTemplate().save(ImageLibrary);
		return ImageLibrary.getId();
	}

	public long update(ImageLibrary ImageLibrary) throws AppException {
		if (ImageLibrary.getId() > 0) {
			this.getHibernateTemplate().update(ImageLibrary);
			return ImageLibrary.getId();
		} else
			throw new IllegalArgumentException("id isn't a valid argument.");
	}

	public ImageLibrary getImageLibraryById(long id) throws AppException {
		ImageLibrary imageLibrary = null;
		Hql hql = new Hql();
		hql.add("from ImageLibrary p where p.id=" + id);
		Query query = this.getQuery(hql);
		ImageLibrary ImageLibrary = null;
		if (query != null) {
			List<ImageLibrary> list = query.list();
			if (list != null && list.size() > 0) {
				return list.get(0);
			}
		}
		return imageLibrary;
	}

	public List<ImageLibrary> getImageLibraryByDishId(long referenceId)
			throws AppException {
		List<ImageLibrary> list = new ArrayList<ImageLibrary>();
		Hql hql = new Hql();
		hql.add("from ImageLibrary p where p.estateDish.id=" + referenceId);
		Query query = this.getQuery(hql);
		ImageLibrary ImageLibrary = null;
		if (query != null) {
			if (query.list() != null) {
				if (query.list().size() > 0) {
					list = query.list();
				}
			}
		}
		return list;
	}

	public List<ImageLibrary> getImageLibraryList() throws AppException {
		List<ImageLibrary> list = new ArrayList<ImageLibrary>();
		Hql hql = new Hql();
		hql.add("from ImageLibrary p where 1=1 ");
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

	public List<ImageLibrary> getValidImageLibraryList() throws AppException {
		List<ImageLibrary> list = new ArrayList<ImageLibrary>();
		Hql hql = new Hql();
		hql
				.add("from ImageLibrary p where 1=1 and p.status="
						+ ImageLibrary.STATES_1);
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
