package com.kurui.kums.monitor.dao;

import java.util.List;

import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.monitor.OperateLogDetail;
import com.kurui.kums.monitor.OperateLogDetailListForm;

public interface OperateLogDetailDAO  {

	public List list(OperateLogDetailListForm tslf) throws AppException;

	public long save(OperateLogDetail operateLogDetail) throws AppException;

	public long merge(OperateLogDetail operateLogDetail) throws AppException;

	public long update(OperateLogDetail operateLogDetail) throws AppException;

	public OperateLogDetail getOperateLogDetailById(long id)
			throws AppException;

	public void deleteById(long id) throws AppException;

}
