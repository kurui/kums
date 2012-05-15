package com.kurui.kums.market.biz;

import java.util.List;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.market.Product;
import com.kurui.kums.market.ProductListForm;
import com.kurui.kums.market.dao.ProductDAO;

public class ProductBizImp implements ProductBiz {
	private ProductDAO productDAO;

	public List list(ProductListForm productForm) throws AppException {
		return productDAO.list(productForm);
	}

	public void delete(long id) throws AppException {
		try {
			productDAO.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteProduct(Long productId) throws AppException {
		Product product = getProductById(productId);
		product.setStatus(Product.STATES_0);// 将状态变为无效
		update(product);
	}

	public long save(Product product) throws AppException {
		return productDAO.save(product);
	}

	public long update(Product product) throws AppException {
		return productDAO.update(product);
	}

	public Product getProductById(long productId) throws AppException {
		return productDAO.getProductById(productId);
	}

	public List<Product> getProductList() throws AppException {
		return productDAO.getProductList();
	}

	public List<Product> getProductListByType(String productTypes)
			throws AppException {
		return productDAO.getProductListByType(productTypes);
	}

	public List<Product> getValidProductList() throws AppException {
		return productDAO.getValidProductList();
	}

	public void setProductDAO(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}

}
