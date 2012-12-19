package com.kurui.kums.library._entity;

import com.kurui.kums.right.UserStore;

/**
 * Platform generated by hbm2java
 */

public class _Platform

extends org.apache.struts.action.ActionForm implements Cloneable {
	private static final long serialVersionUID = 1L;

	// Fields

	protected long id;
	protected String name;
	protected String description;
	protected Long type;
	protected String userNo;
	protected java.sql.Timestamp updateTime;
	protected Long status;
	protected java.util.Set financeOrders = new java.util.HashSet(0);
	protected java.util.Set platComAccounts = new java.util.HashSet(0);

	// Constructors

	// Property accessors

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getType() {
		return this.type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	public String getUserName() {
		if (userNo != null && "".equals(userNo.trim()) == false) {
			return UserStore.getUserNameByNo(userNo);
		} else {
			return "";
		}
	}
	
	

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public Long getStatus() {
		return this.status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public java.sql.Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(java.sql.Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public java.util.Set getfinanceOrders() {
		return this.financeOrders;
	}

	public void setfinanceOrders(java.util.Set financeOrders) {
		this.financeOrders = financeOrders;
	}

	public java.util.Set getPlatComAccounts() {
		return this.platComAccounts;
	}

	public void setPlatComAccounts(java.util.Set platComAccounts) {
		this.platComAccounts = platComAccounts;
	}

	// The following is extra code specified in the hbm.xml files

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

	// end of extra code specified in the hbm.xml files

}
