package com.kurui.kums.right;

import org.apache.struts.action.ActionForm;

public class Right extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String thisAction = "";
	private String userId = "";

	public String getThisAction() {
		return thisAction;
	}

	public void setThisAction(String thisAction) {
		this.thisAction = thisAction;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}