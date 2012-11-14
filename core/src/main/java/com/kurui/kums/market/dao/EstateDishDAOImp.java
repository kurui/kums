package com.kurui.kums.market.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import com.kurui.kums.base.database.hibernate.BaseDAOSupport;
import com.kurui.kums.base.database.hibernate.Hql;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.base.Constant;
import com.kurui.kums.market.EstateDish;
import com.kurui.kums.market.EstateDishListForm;

public class EstateDishDAOImp extends BaseDAOSupport implements EstateDishDAO {

	public List list(EstateDishListForm estateDishListForm)
			throws AppException {
		Hql hql = new Hql();
		hql.add("from EstateDish p where 1=1");
		if (estateDishListForm.getName() != null
				&& (!(estateDishListForm.getName().equals("")))) {
			hql.add(" and p.name like '%"
					+ estateDishListForm.getName().trim() + "%'");
		}
		
		if (Constant.toLong(estateDishListForm.getCompanyId()) > 0) {
			hql.add(" and p.company.id=" + estateDishListForm.getCompanyId());
		}
		
		if (Constant.toLong(estateDishListForm.getType()) > 0) {
			hql.add(" and p.type=" + estateDishListForm.getType());
		}

		hql.add(" and p.status=" + EstateDish.STATES_1);
		
		
		if(Constant.toString(estateDishListForm.getOrderBy()).equals("beginTimeUp")){
			hql.add(" order by p.beginTime  ");
		}else if(Constant.toString(estateDishListForm.getOrderBy()).equals("beginTimeDown")){
			hql.add(" order by p.beginTime desc ");
		}

		return this.list(hql, estateDishListForm);
	}

	public void delete(long id) throws AppException {
		if (id > 0) {
			EstateDish platform = (EstateDish) this.getHibernateTemplate().get(
					EstateDish.class, new Long(id));
			this.getHibernateTemplate().delete(platform);
		}
	}

	public long save(EstateDish platform) throws AppException {
		this.getHibernateTemplate().save(platform);
		return platform.getId();
	}

	public long update(EstateDish platform) throws AppException {
		if (platform.getId() > 0) {
			this.getHibernateTemplate().update(platform);
			return platform.getId();
		} else
			throw new IllegalArgumentException("id isn't a valid argument.");
	}

	public EstateDish getEstateDishById(long id) throws AppException {
		Hql hql = new Hql();
		hql.add("from EstateDish p where p.id=" + id);
		Query query = this.getQuery(hql);
		EstateDish priceReference = null;
		if (query != null && query.list() != null && query.list().size() > 0) {
			priceReference = (EstateDish) query.list().get(0);
		}
		return priceReference;
	}

	public List<EstateDish> getEstateDishByIds(String referenceIds)
			throws AppException {
		List<EstateDish> list = new ArrayList<EstateDish>();
		Hql hql = new Hql();
		hql.add("from EstateDish p where 1=1  ");
		if (Constant.toString(referenceIds) != "") {
			hql.add(" and p.id in(" + referenceIds + ") ");
		}
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

	public List<EstateDish> getEstateDishList() throws AppException {
		List<EstateDish> list = new ArrayList<EstateDish>();
		Hql hql = new Hql();
		hql.add(" from EstateDish p  where 1=1 ");
		hql.add(" and p.status=" + EstateDish.STATES_1);
		hql.add(" order by p.type ");
		Query query = this.getQuery(hql);
		if (query != null) {
			list = query.list();
			if (list != null && list.size() > 0) {
				return list;
			}
		}
		return list;
	}


	public List<EstateDish> getValidEstateDishList() throws AppException {
		List<EstateDish> list = new ArrayList<EstateDish>();
		Hql hql = new Hql();
		hql.add(" from EstateDish p where 1=1 ");
		hql.add(" and p.status=" + EstateDish.STATES_1);
		hql.add(" order by p.type ");
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
