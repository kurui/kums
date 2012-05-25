package com.kurui.kums.system.biz;

import java.util.List;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.system.OperateLog;
import com.kurui.kums.system.OperateLogListForm;

public interface OperateLogBiz {

	public OperateLog getOperateLogById(int id) throws AppException;

	public List getOperateLogs(OperateLogListForm sllf) throws AppException;

	public void saveOperateLog(OperateLog operateLog) throws AppException;

	public int updateOperateLog(OperateLog operateLog) throws AppException;

	public void deleteOperateLog(int id) throws AppException;

	public List getOperateLogByOrderNo(String orderNo) throws AppException;

}