package com.kurui.kums.finance;

import com.kurui.kums.base.ListActionForm;

public class CommissionListForm extends ListActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id = Long.valueOf(0);
	private String name = "";
	private long type = Long.valueOf(0);

	private String orderNo = "";

	private String agentContactWay = "";

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getType() {
		return type;
	}

	public void setType(long type) {
		this.type = type;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getAgentContactWay() {
		return agentContactWay;
	}

	public void setAgentContactWay(String agentContactWay) {
		this.agentContactWay = agentContactWay;
	}

}
