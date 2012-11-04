package com.kurui.kums.transaction.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;

import com.kurui.kums.base.database.BaseDAOSupport;
import com.kurui.kums.base.database.Hql;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.base.Constant;
import com.kurui.kums.base.util.StringUtil;
import com.kurui.kums.transaction.Company;
import com.kurui.kums.transaction.CompanyListForm;

public class CompanyDAOImp extends BaseDAOSupport implements CompanyDAO {

	public List list(CompanyListForm form) throws AppException {
		Hql hql = new Hql();
		hql.add("from Company c where 1=1");
		String name = Constant.toString(form.getName());
		if (name.equals("") == false) {
			hql.add(" and c.name like '%" + name + "%'");
		}
		long type = Constant.toLong(form.getType());
		if (type > 0) {
			hql.add(" and c.type=" + type);
		}

		String provideChain = Constant.toString(form.getProvideChain());
		if (provideChain.equals("") == false) {
			String[] provideChainGroup = StringUtil.split(provideChain, ",");
			if (provideChainGroup != null) {
				hql.add(" and (");
				for (int i = 0; i < provideChainGroup.length; i++) {
					String temp = Constant.toString(provideChainGroup[i]);
					if (i > 00) {
						hql.add(" or ");
					}
					hql.add("  c.provideChain like '%" + temp + "%'");
				}
				hql.add(" ) ");
			}
		}

		hql.add("and c.status not in(" + Company.STATES_0 + ")");// 过滤无效

		if (StringUtil.isEmpty(form.getOrderBy()) ==false) {
			if("ORDER_BY_FINANCE_COUNT".equals(form.getOrderBy())){
				hql.add(" order by c.financeCount desc,c.type,provideChain,updateTime desc");
			}else if("ORDER_BY_AGENT_COUNT".equals(form.getOrderBy())){
				hql.add(" order by c.agentCount desc,c.type,provideChain,updateTime desc");
			}else{
				hql.add(" order by c.financeCount desc,c.type,provideChain,updateTime desc");
			}
			
		}else{
			hql.add(" order by c.financeCount desc,c.type,provideChain,updateTime desc");
		}
		
		

		return this.list(hql, form);
	}

	public void delete(long id) throws AppException {
		if (id > 0) {
			Company company = (Company) this.getHibernateTemplate().get(
					Company.class, new Long(id));
			this.getHibernateTemplate().delete(company);
		}
	}

	public long save(Company company) throws AppException {
		this.getHibernateTemplate().save(company);
		return company.getId();
	}

	public long update(Company company) throws AppException {
		if (company.getId() > 0) {
			this.getHibernateTemplate().update(company);
			return company.getId();
		} else
			throw new IllegalArgumentException("id isn't a valid argument.");
	}

	public Company getCompanyById(long companyId) throws AppException {
		Hql hql = new Hql();
		hql.add("from Company p where p.id=" + companyId);
		Query query = this.getQuery(hql);
		Company company = null;
		if (query != null && query.list() != null && query.list().size() > 0) {
			company = (Company) query.list().get(0);
		}
		return company;
	}

	public List<Company> getCompanyList(String types, String status)
			throws AppException {
		List<Company> list = new ArrayList<Company>();
		Hql hql = new Hql();
		hql.add(" from Company c where 1=1 ");
		if (Constant.toString(types) != "") {
			hql.add(" and c.type in(" + types + " ) ");
		}
		if (Constant.toString(status) != "") {
			hql.add(" and c.status in(" + status + " ) ");
		}
		hql.add(" order by name");

		// System.out.println("hql>>\n"+hql);

		Query query = this.getQuery(hql);
		if (query != null) {
			list = query.list();
			if (list != null && list.size() > 0) {
				return list;
			}
		}
		return list;
	}

	public List<Company> getCompanyListByProvideChain(String provideChain)
			throws AppException {
		List<Company> list = new ArrayList<Company>();
		Hql hql = new Hql();
		hql.add(" from Company c where 1=1 ");

		provideChain = Constant.toString(provideChain);
		if (provideChain.equals("") == false) {
			String[] provideChainGroup = StringUtil.split(provideChain, ",");
			if (provideChainGroup != null) {
				hql.add(" and (");
				for (int i = 0; i < provideChainGroup.length; i++) {
					String temp = Constant.toString(provideChainGroup[i]);
					if (i > 00) {
						hql.add(" or ");
					}
					hql.add("  c.provideChain like '%" + temp + "%'");
				}
				hql.add(" ) ");
			}
		}
		
		hql.add("and c.status not in(" + Company.STATES_0 + ")");// 过滤无效
		hql.add(" order by name");

//		 System.out.println("hql>>\n"+hql);

		Query query = this.getQuery(hql);
		if (query != null) {
			list = query.list();
			if (list != null && list.size() > 0) {
				return list;
			}
		}
		return list;
	}

	public List<Company> getValidCompanyList() throws AppException {
		List<Company> list = new ArrayList<Company>();
		Hql hql = new Hql();
		hql.add("from Company c where 1=1 and c.status=" + Company.STATES_1
				+ " order by name");
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
