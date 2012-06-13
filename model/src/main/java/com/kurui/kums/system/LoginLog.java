package com.kurui.kums.system;

import java.util.Date;

import com.kurui.kums.base.util.DateUtil;
import com.kurui.kums.system.entity._LoginLog;

public class LoginLog extends _LoginLog {
	private static final long serialVersionUID = 1L;

	public String loginStatus;

	public static final long LOCATE_1 = 1;// 1:客户前台
	public static final long LOCATE_2 = 2;// 2:管理后台

	public static final long STATUS_1 = 1;//
	public static final long STATUS_0 = 0;//

	public String getLoginStatus() {
		if (this.getStatus() != null) {
			if (this.getStatus() == STATUS_0) {
				return "登录失败!";
			} else if (this.getStatus() == STATUS_1) {
				return "登录成功";
			} else {
				return "未定义";
			}
		} else {
			return "";
		}
	}

	public String getFormatLoginTime() {
		String mydate = "";
		if (this.loginTime != null && "".equals(loginTime) == false) {
			Date tempDate = new Date(loginTime.getTime());
			mydate = DateUtil.getDateString(tempDate, "yyyy-MM-dd HH:mm:ss");
		}
		return mydate;
	}

}
