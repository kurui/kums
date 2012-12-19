package com.kurui.kums.monitor.dao;

import java.util.List;

import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.monitor.OperateLog;
import com.kurui.kums.monitor.OperateLogListForm;

public interface OperateLogDAO  {

	public List list(OperateLogListForm tslf) throws AppException;

	public long save(OperateLog operateLog) throws AppException;

	public long merge(OperateLog operateLog) throws AppException;

	public long update(OperateLog operateLog) throws AppException;

	public OperateLog getOperateLogById(long id) throws AppException;

	public void deleteById(long id) throws AppException;

	public boolean getOperateLogByUserId(long id) throws AppException;

	public List getOperateLogByOrderNo(String orderNo) throws AppException;

	public List getOperateLogByOrderIds(String orderIds) throws AppException;
}
