package com.kurui.kums.finance.entity;

// Generated 2011-9-30 2:40:48 by Hibernate Tools 3.2.2.GA

import java.math.BigDecimal;

import com.kurui.kums.finance.FinanceOrder;
import com.kurui.kums.market.Product;

/**
 * OrderDetail generated by hbm2java
 */

public class _OrderDetail extends org.apache.struts.action.ActionForm implements
		Cloneable {

	private static final long serialVersionUID = 1L;

	protected long id;
	protected Product product;
	protected FinanceOrder financeOrder;
	protected BigDecimal discountPrice;
	protected BigDecimal productCount;
	protected BigDecimal detailAmount;
	protected Long status;
	protected String memo;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public FinanceOrder getFinanceOrder() {
		return financeOrder;
	}

	public void setFinanceOrder(FinanceOrder financeOrder) {
		this.financeOrder = financeOrder;
	}


	public BigDecimal getDiscountPrice() {
		if(discountPrice==null){
			return BigDecimal.ZERO;
		}
		return discountPrice;
	}

	public void setDiscountPrice(BigDecimal discountPrice) {
		this.discountPrice = discountPrice;
	}

	public BigDecimal getProductCount() {
		if(productCount==null){
			return BigDecimal.ZERO;
		}
		return productCount;
	}

	public void setProductCount(BigDecimal productCount) {
		this.productCount = productCount;
	}

	public BigDecimal getDetailAmount() {
		if(detailAmount==null){
			return BigDecimal.ZERO;
		}
		return this.detailAmount;
	}

	public void setDetailAmount(BigDecimal detailAmount) {
		this.detailAmount = detailAmount;
	}

	public Long getStatus() {
		return this.status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	// The following is extra code
	public Object clone() {
		Object o = null;
		try {
			o = super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return o;
	}

	private String thisAction = "";

	public String getThisAction() {
		return thisAction;
	}

	public void setThisAction(String thisAction) {
		this.thisAction = thisAction;
	}

	private int index = 0;

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	// end of extra code

}