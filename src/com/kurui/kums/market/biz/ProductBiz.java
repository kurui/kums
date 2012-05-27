package com.kurui.kums.market.biz;

import java.util.List;

import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.market.Product;
import com.kurui.kums.market.ProductListForm;

public interface ProductBiz {

	public List list(ProductListForm form) throws AppException;

	public void delete(long id) throws AppException;

	public void deleteProduct(Long productId) throws AppException;

	public long save(Product product) throws AppException;

	public long update(Product product) throws AppException;

	public Product getProductById(long productId)
			throws AppException;

	public List<Product> getProductList() throws AppException;

	public List<Product> getProductListByType(
			String productTypes) throws AppException;

	public List<Product> getValidProductList()
			throws AppException;

}
