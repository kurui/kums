package com.kurui.kums.system.dao;

import java.util.List;

import com.kurui.kums.base.database.BaseDAO;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.system.LoginLog;
import com.kurui.kums.system.LoginLogListForm;

public interface LoginLogDAO extends BaseDAO {

	public long save(LoginLog loginlog) throws AppException; 
	public List list(LoginLogListForm lllf) throws AppException;
}
