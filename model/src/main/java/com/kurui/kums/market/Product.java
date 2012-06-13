package com.kurui.kums.market;

import com.kurui.kums.base.Constant;
import com.kurui.kums.market.entity._Product;
import com.kurui.kums.transaction.util.DataTypeStore;

public class Product extends _Product {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 剂型
	public static final long CLASS_TYPE_1 = 1;// 默认

	// 类型
	public static final long TYPE_1 = 1;// 自有
	public static final long TYPE_11 = 11;// 市场

	// 状态
	public static final long STATES_1 = 1;// 有效
	public static final long STATES_0 = 0;// 无效

	public String getClassTypeInfo() {
		if (this.getClassType() != null) {
			if (this.getClassType().intValue() == CLASS_TYPE_1) {
				return "默认";
			} else {
				return "";
			}
		} else {
			return "";
		}
	}

	public String productTypeInfo="";
	
	public void setProductTypeInfo(String productTypeInfo) {
		this.productTypeInfo = productTypeInfo;
	}

	public String getProductTypeInfo() {
		String productTypeText = "货品类型";
		if (this.getProductType() != null) {
			productTypeText = DataTypeStore.getDataTypeNameByNo(this
					.getProductType()
					+ "");
		} else {
			return productTypeText;
		}
		return productTypeText;
	}

	public String getTypeInfo() {
		if (this.getType() != null) {
			if (this.getType().intValue() == TYPE_1) {
				return "自有";
			} else if (this.getType().intValue() == TYPE_11) {
				return "市场";
			} else {
				return "";
			}
		} else {
			return "";
		}
	}

	// 状态
	public String getStatusInfo() {
		if (this.getStatus() != null) {
			if (this.getStatus() == STATES_1) {
				return "有效";
			} else if (this.getStatus().intValue() == STATES_0) {
				return "无效";
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	public String getProportionInfo() {
		String proportionInfo = "";
		if (proportion != null) {
			proportionInfo = Constant.toPercentByBigDecimal(proportion);
		}
		return proportionInfo;
	}
}
