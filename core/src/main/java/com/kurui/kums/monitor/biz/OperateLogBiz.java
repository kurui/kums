package com.kurui.kums.monitor.biz;

import java.util.List;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.monitor.OperateLog;
import com.kurui.kums.monitor.OperateLogListForm;

public interface OperateLogBiz {

	public OperateLog getOperateLogById(int id) throws AppException;

	public List getOperateLogs(OperateLogListForm sllf) throws AppException;

	public void saveOperateLog(OperateLog operateLog) throws AppException;

	public int updateOperateLog(OperateLog operateLog) throws AppException;

	public void deleteOperateLog(int id) throws AppException;

	public List getOperateLogByOrderNo(String orderNo) throws AppException;

}