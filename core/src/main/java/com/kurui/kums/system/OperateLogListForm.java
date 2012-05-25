package com.kurui.kums.system;

import com.kurui.kums.base.ListActionForm;
import com.kurui.kums.right.SysUser;

public class OperateLogListForm extends ListActionForm{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private String orderNo;
	private long orderType;
	private String ip;
	private java.sql.Timestamp optTime;
	private long type;
	private long status;
	private com.kurui.kums.right.SysUser sysUser = new SysUser();

	private String userNo = "";

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public long getOrderType() {
		return orderType;
	}

	public void setOrderType(long orderType) {
		this.orderType = orderType;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public java.sql.Timestamp getOptTime() {
		return optTime;
	}

	public void setOptTime(java.sql.Timestamp optTime) {
		this.optTime = optTime;
	}

	public long getType() {
		return type;
	}

	public void setType(long type) {
		this.type = type;
	}

	public long getStatus() {
		return status;
	}

	public void setStatus(long status) {
		this.status = status;
	}

	public com.kurui.kums.right.SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(com.kurui.kums.right.SysUser sysUser) {
		this.sysUser = sysUser;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	
}
