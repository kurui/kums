package com.kurui.kums.system.biz;



import java.util.List;

import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.system.LoginLog;
import com.kurui.kums.system.LoginLogListForm;

public interface LoginLogBiz {
	public void saveLoginLog(LoginLog loginlog) throws AppException;
	public List getLoginLogs(LoginLogListForm lllf) throws AppException;
}