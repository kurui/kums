package com.kurui.kums.finance._entity;


import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * AssetsItem generated by hbm2java
 */

public class _AssetsItem extends org.apache.struts.action.ActionForm implements
		Cloneable {

	private static final long serialVersionUID = 1L;

	protected long id;
	protected String itemNo;
	protected String name;
	protected String itemType;
	protected Long itemCount;
	protected BigDecimal valuation;
	protected String areaCode;
	protected Timestamp lastDeprecTime;
	protected long financeOrderId;
	public long getFinanceOrderId() {
		return financeOrderId;
	}

	public void setFinanceOrderId(long financeOrderId) {
		this.financeOrderId = financeOrderId;
	}

	protected String memo;
	protected Timestamp updateTime;
	protected Long type;
	protected Long status;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	
	public String getItemNo() {
		return itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public Long getItemCount() {
		if (itemCount==null) {
			return Long.valueOf(1);
		}
		return itemCount;
	}

	public void setItemCount(Long itemCount) {
		this.itemCount = itemCount;
	}
	
	

	public BigDecimal getValuation() {
		if (valuation==null) {
			return BigDecimal.ZERO;
		}
		return valuation;
	}

	public void setValuation(BigDecimal valuation) {
		this.valuation = valuation;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public Timestamp getLastDeprecTime() {
		return lastDeprecTime;
	}

	public void setLastDeprecTime(Timestamp lastDeprecTime) {
		this.lastDeprecTime = lastDeprecTime;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public String getMemo() {

		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Long getType() {

		return this.type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	public Long getStatus() {

		return this.status;
	}

	public void setStatus(Long status) {
		this.status = status;
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
	
	public String lastAction="";
	public int intPage;
	public int pageCount;
	
	public String getLastAction() {
		return lastAction;
	}

	public void setLastAction(String lastAction) {
		this.lastAction = lastAction;
	}
	
	

	public int getIntPage() {
		return intPage;
	}

	public void setIntPage(int intPage) {
		this.intPage = intPage;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	// end of extra code

}
