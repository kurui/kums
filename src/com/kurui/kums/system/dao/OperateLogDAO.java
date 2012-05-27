package com.kurui.kums.system.dao;

import java.util.List;

import com.kurui.kums.base.database.BaseDAO;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.system.OperateLog;
import com.kurui.kums.system.OperateLogListForm;

public interface OperateLogDAO extends BaseDAO {

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
