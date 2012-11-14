package com.kurui.kums.transaction.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;

import com.kurui.kums.base.Constant;
import com.kurui.kums.base.database.hibernate.BaseDAOSupport;
import com.kurui.kums.base.database.hibernate.Hql;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.transaction.AreaCode;
import com.kurui.kums.transaction.AreaCodeListForm;

public class AreaCodeDAOImp extends BaseDAOSupport implements AreaCodeDAO {

	public List list(AreaCodeListForm form) throws AppException {
		Hql hql = new Hql();
		hql.add("from AreaCode c where 1=1");
		String name = Constant.toString(form.getName());
		if (name.equals("") == false) {
			hql.add(" and c.name like '%" + name + "%'");
		}

		hql.add(" order by c.areaCode");

		return this.list(hql, form);
	}

	public void delete(long id) throws AppException {
		if (id > 0) {
			AreaCode AreaCode = (AreaCode) this.getHibernateTemplate().get(
					AreaCode.class, new Long(id));
			this.getHibernateTemplate().delete(AreaCode);
		}
	}

	public long save(AreaCode AreaCode) throws AppException {
		this.getHibernateTemplate().save(AreaCode);
		return AreaCode.getId();
	}

	public long update(AreaCode AreaCode) throws AppException {
		if (AreaCode.getId() > 0) {
			this.getHibernateTemplate().update(AreaCode);
			return AreaCode.getId();
		} else
			throw new IllegalArgumentException("id isn't a valid argument.");
	}

	public AreaCode getAreaCodeById(long AreaCodeId) throws AppException {
		Hql hql = new Hql();
		hql.add("from AreaCode p where p.id=" + AreaCodeId);
		Query query = this.getQuery(hql);
		AreaCode AreaCode = null;
		if (query != null && query.list() != null && query.list().size() > 0) {
			AreaCode = (AreaCode) query.list().get(0);
		}
		return AreaCode;
	}

	public AreaCode getAreaCodeByNo(String no) throws AppException {
		Hql hql = new Hql();
		hql.add(" from AreaCode p where p.areaCode='" + no+"'");
		System.out.println("hql:"+hql);
		Query query = this.getQuery(hql);
		AreaCode AreaCode = null;
		if (query != null && query.list() != null && query.list().size() > 0) {
			AreaCode = (AreaCode) query.list().get(0);
		}
		
		return AreaCode;
	}

	public String getAreaTextByCode(String no) throws AppException {

		String text = "";
		if (no != null && no.length() == 6) {
			AreaCode country = getAreaCodeByNo(no);

			String cityCode = no.substring(0, 4) + "00";
			String provinceCode = no.substring(0, 2) + "0000";

			AreaCode city = getAreaCodeByNo(cityCode);
			AreaCode province = getAreaCodeByNo(provinceCode);
			

			if (province != null) {
				text += province.getName();
			}

			if (city != null) {
				text += city.getName();
			}
			if (country != null) {
				text += country.getName();
			}
			
			text=text.replace(" ", "");

		}
		return text;
	}

	public List<AreaCode> getValidAreaCodeList() throws AppException {
		List<AreaCode> list = new ArrayList<AreaCode>();
		Hql hql = new Hql();
		hql.add("from AreaCode c where 1=1  order by areaCode");
		Query query = this.getQuery(hql);
		if (query != null) {
			list = query.list();
			if (list != null) {
				return list;
			}
		}
		return list;
	}
}
