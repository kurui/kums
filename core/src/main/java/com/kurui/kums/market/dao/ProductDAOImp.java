package com.kurui.kums.market.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import com.kurui.kums.base.database.hibernate.BaseDAOSupport;
import com.kurui.kums.base.database.hibernate.Hql;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.base.Constant;
import com.kurui.kums.market.Product;
import com.kurui.kums.market.ProductListForm;

public class ProductDAOImp extends BaseDAOSupport implements ProductDAO {

	public List list(ProductListForm productListForm) throws AppException {
		Hql hql = new Hql();
		hql.add("from Product p where 1=1");
		if (!(Constant.toUpperCase(productListForm.getName()).equals(""))) {
			hql.add(" and ( ");
			hql.add(" p.name like '%" + productListForm.getName().trim()
					+ "%' ");
			hql.add(" or p.no like '%" + productListForm.getName().trim() + "%' ");
			hql.add(" ) ");
		}
		
		String productTypes =productListForm.getProductTypes();
		if ("".equals(productTypes)==false) {
			hql.add(" and p.productType in(" + productTypes + " ) ");
		}
		
		if (Constant.toLong(productListForm.getType()) > 0) {
			hql.add(" and p.type=" + productListForm.getType());
		}

		hql.add(" and p.status=" + Product.STATES_1);

		hql.add(" order by p.type,p.no ");

		return this.list(hql, productListForm);
	}
	
	

	public void delete(long id) throws AppException {
		if (id > 0) {
			Product product = (Product) this.getHibernateTemplate().get(
					Product.class, new Long(id));
			this.getHibernateTemplate().delete(product);
		}
	}

	public long save(Product product) throws AppException {
		this.getHibernateTemplate().save(product);
		return product.getId();
	}

	public long update(Product product) throws AppException {
		if (product.getId() > 0) {
			this.getHibernateTemplate().update(product);
			return product.getId();
		} else
			throw new IllegalArgumentException("id isn't a valid argument.");
	}

	public Product getProductById(long id) throws AppException {
		Hql hql = new Hql();
		hql.add("from Product p where p.id=" + id);

		Query query = this.getQuery(hql);
		Product product = null;
		if (query != null && query.list() != null && query.list().size() > 0) {
			product = (Product) query.list().get(0);
		}

		return product;
	}

	public List<Product> getProductList() throws AppException {
		List<Product> list = new ArrayList<Product>();
		Hql hql = new Hql();
		hql.add(" from Product p  where 1=1 ");
		hql.add(" and p.status=" + Product.STATES_1);
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

	public List<Product> getProductListByType(String productTypes)
			throws AppException {
		List<Product> list = new ArrayList<Product>();
		Hql hql = new Hql();
		hql.add(" from Product p where  1=1 ");

		if (Constant.toString(productTypes) != "") {
			hql.add(" and p.type in(" + productTypes + ")");
		}

		hql.add(" and p.status=" + Product.STATES_1);
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

	public List<Product> getValidProductList() throws AppException {
		List<Product> list = new ArrayList<Product>();
		Hql hql = new Hql();
		hql.add(" from Product p where 1=1 ");
		hql.add(" and p.status=" + Product.STATES_1);
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
