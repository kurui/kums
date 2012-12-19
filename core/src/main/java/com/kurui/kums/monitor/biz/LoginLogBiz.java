package com.kurui.kums.monitor.biz;



import java.util.List;

import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.monitor.LoginLog;
import com.kurui.kums.monitor.LoginLogListForm;

public interface LoginLogBiz {
	public void saveLoginLog(LoginLog loginlog) throws AppException;
	public List getLoginLogs(LoginLogListForm lllf) throws AppException;
}