package com.kurui.kums.system;

import com.kurui.kums.base.ListActionForm;

public class LoginLogListForm extends ListActionForm {

	private static final long serialVersionUID = 1L;

	private String userNo = "";
	private long locate = 0;

	public long getLocate() {
		return locate;
	}

	public void setLocate(long locate) {
		this.locate = locate;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}



	public static long getSerialVersionUID() {

		return serialVersionUID;
	}
}
