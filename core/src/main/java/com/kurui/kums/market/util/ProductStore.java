package com.kurui.kums.market.util;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import com.kurui.kums.base.Constant;
import com.kurui.kums.market.Product;

public class ProductStore {
	
	static Logger logger = Logger.getLogger(ProductStore.class.getName());
	
	public static List<Product> productList = new ArrayList<Product>();

	public static List<Product> getBlurProductList(String blur) {
//		 System.out.println("productList size:"+productList.size()+"--getBlurProductList:" + blur);
		List<Product> blurProductList = new ArrayList<Product>();
		if (Constant.toString(blur).equals("") == false) {
			int j = 0;
			for (int i = 0; i < productList.size(); i++) {
				if (j > 4) {
					break;
				} else {
					Product product = productList.get(i);
					if (product != null) {
						String name = Constant.toString(product.getName());
						String productNo = Constant.toString(product.getNo());

						if (name.indexOf(blur) > -1) {
							blurProductList.add(product);
						} else if (productNo.indexOf(blur) > -1) {
							blurProductList.add(product);
						} 
					}
				}

				j = blurProductList.size();
			}
		}
//		 System.out.println("result:"+blurProductList.size());
		return blurProductList;
	}

	public static List<Product> getProductListByType(long type) {
		List<Product> tempList = new ArrayList<Product>();
		for (int i = 0; i < productList.size(); i++) {
			Product product = productList.get(i);
			if (product.getType() == type) {
				tempList.add(product);
			}
		}
		return tempList;
	}

	public static List<Product> getProductList() {
		return productList;
	}

	public static void setProductList(List<Product> productList) {
		ProductStore.productList = productList;
	}

}
