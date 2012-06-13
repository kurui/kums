package com.kurui.kums.market;

import org.jdom.Element;

import com.kurui.kums.market.entity._PriceReference;

public class PriceReference extends _PriceReference {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 类型
	public static final long TYPE_1 = 1;// 央行利率
	public static final long TYPE_10 = 10;// 外汇行情
	public static final long TYPE_11 = 11;// 人民币汇率中间价
	public static final long TYPE_20 = 20;// 股市指数
	public static final long TYPE_30 = 30;// 期货指数
	public static final long TYPE_40 = 40;// 贵金属指数

	public static final long TYPE_101 = 101;// 粮食
	public static final long TYPE_102 = 102;// 果蔬
	public static final long TYPE_103 = 103;// 肉/鱼/蛋
	public static final long TYPE_110 = 110;// 加工食品
	public static final long TYPE_120 = 120;// 药品

	// 状态
	public static final long STATES_1 = 1;// 有效
	public static final long STATES_0 = 0;// 无效

	public String getTypeInfo() {
		if (this.getType() != null) {
			if (this.getType().intValue() == TYPE_1) {
				return "央行利率";
			} else if (this.getType().intValue() == TYPE_10) {
				return "外汇";
			} else if (this.getType().intValue() == TYPE_11) {
				return "人民币汇率中间价";
			} else if (this.getType().intValue() == TYPE_20) {
				return "股市";
			} else if (this.getType().intValue() == TYPE_30) {
				return "期货";
			} else if (this.getType().intValue() == TYPE_30) {
				return "贵金属";
			} else if (this.getType().intValue() == TYPE_101) {
				return "粮食";
			} else if (this.getType().intValue() == TYPE_102) {
				return "果蔬";
			} else if (this.getType().intValue() == TYPE_103) {
				return "肉/鱼/蛋";
			} else if (this.getType().intValue() == TYPE_110) {
				return "加工食品";
			} else if (this.getType().intValue() == TYPE_120) {
				return "药品";
			} else {
				return "";
			}
		} else {
			return "";
		}
	}

	public static Element getTypeItems(Element rootItem) {
		Element typeItem = new Element("item");
		typeItem.setAttribute("id", "type" + TYPE_1 + "");
		typeItem.setAttribute("text", "央行利率");
		// if (itemIndex == 0) {
		typeItem.setAttribute("call", "1");
		typeItem.setAttribute("select", "1");
		// }
		rootItem.addContent(typeItem);

		typeItem = new Element("item");
		typeItem.setAttribute("id", "type" + TYPE_10 + "");
		typeItem.setAttribute("text", "外汇");
		rootItem.addContent(typeItem);

		typeItem = new Element("item");
		typeItem.setAttribute("id", "type" + TYPE_11 + "");
		typeItem.setAttribute("text", "人民币汇率中间价");
		rootItem.addContent(typeItem);

		typeItem = new Element("item");
		typeItem.setAttribute("id", "type" + TYPE_20 + "");
		typeItem.setAttribute("text", "股市");
		rootItem.addContent(typeItem);

		typeItem = new Element("item");
		typeItem.setAttribute("id", "type" + TYPE_30 + "");
		typeItem.setAttribute("text", "期货");
		rootItem.addContent(typeItem);

		typeItem = new Element("item");
		typeItem.setAttribute("id", "type" + TYPE_40 + "");
		typeItem.setAttribute("text", "贵金属");
		rootItem.addContent(typeItem);

		typeItem = new Element("item");
		typeItem.setAttribute("id", "type" + TYPE_101 + "");
		typeItem.setAttribute("text", "粮食");
		rootItem.addContent(typeItem);
		
		typeItem = new Element("item");
		typeItem.setAttribute("id", "type" + TYPE_102 + "");
		typeItem.setAttribute("text", "果蔬");
		rootItem.addContent(typeItem);
		
		typeItem = new Element("item");
		typeItem.setAttribute("id", "type" + TYPE_103 + "");
		typeItem.setAttribute("text", "肉/鱼/蛋");
		rootItem.addContent(typeItem);
		
		typeItem = new Element("item");
		typeItem.setAttribute("id", "type" + TYPE_110 + "");
		typeItem.setAttribute("text", "加工食品");
		rootItem.addContent(typeItem);
		
		typeItem = new Element("item");
		typeItem.setAttribute("id", "type" + TYPE_120 + "");
		typeItem.setAttribute("text", "药品");
		rootItem.addContent(typeItem);
		
		return rootItem;
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
}
