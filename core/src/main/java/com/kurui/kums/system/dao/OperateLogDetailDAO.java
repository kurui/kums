package com.kurui.kums.system.dao;

import java.util.List;

import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.system.OperateLogDetail;
import com.kurui.kums.system.OperateLogDetailListForm;

public interface OperateLogDetailDAO  {

	public List list(OperateLogDetailListForm tslf) throws AppException;

	public long save(OperateLogDetail operateLogDetail) throws AppException;

	public long merge(OperateLogDetail operateLogDetail) throws AppException;

	public long update(OperateLogDetail operateLogDetail) throws AppException;

	public OperateLogDetail getOperateLogDetailById(long id)
			throws AppException;

	public void deleteById(long id) throws AppException;

}
