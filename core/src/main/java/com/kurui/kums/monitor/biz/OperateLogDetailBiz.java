package com.kurui.kums.monitor.biz;

import java.util.List;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.monitor.OperateLogDetail;
import com.kurui.kums.monitor.OperateLogDetailListForm;

public interface OperateLogDetailBiz {

	public OperateLogDetail getOperateLogDetailById(int id) throws AppException;

	public List getOperateLogDetails(OperateLogDetailListForm sllf)
			throws AppException;

	public void saveOperateLogDetail(OperateLogDetail operateLog)
			throws AppException;

	public int updateOperateLogDetail(OperateLogDetail operateLog)
			throws AppException;

	public void deleteOperateLogDetail(int id) throws AppException;

}