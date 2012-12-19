package com.kurui.kums.monitor.dao;

import java.util.List;

import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.monitor.LoginLog;
import com.kurui.kums.monitor.LoginLogListForm;

public interface LoginLogDAO {

	public long save(LoginLog loginlog) throws AppException; 
	public List list(LoginLogListForm lllf) throws AppException;
}
