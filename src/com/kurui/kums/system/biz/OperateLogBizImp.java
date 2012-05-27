package com.kurui.kums.system.biz;

import java.util.List;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.system.OperateLog;
import com.kurui.kums.system.OperateLogListForm;
import com.kurui.kums.system.dao.OperateLogDAO;

public class OperateLogBizImp implements OperateLogBiz {
	private OperateLogDAO operateLogDAO;
	private TransactionTemplate transactionTemplate;

	public void setTransactionManager(
			HibernateTransactionManager transactionManager) {
		this.transactionTemplate = new TransactionTemplate(transactionManager);
	}

	public List getOperateLogs(OperateLogListForm tslf) throws AppException {
		return operateLogDAO.list(tslf);
	}

	public void saveOperateLog(OperateLog operateLog) throws AppException {
		operateLogDAO.save(operateLog);
	}

	public void deleteOperateLog(int id) throws AppException {
		operateLogDAO.deleteById(id);
	}

	public int updateOperateLog(OperateLog operateLog) throws AppException {
		operateLogDAO.update(operateLog);
		return 0;
	}

	public OperateLog getOperateLogById(int id) throws AppException {
		return operateLogDAO.getOperateLogById(id);
	}
	
	public List getOperateLogByOrderNo(String orderNo) throws AppException	{
		return operateLogDAO.getOperateLogByOrderNo(orderNo);
	}

	public void setOperateLogDAO(OperateLogDAO operateLogDAO) {
		this.operateLogDAO = operateLogDAO;
	}
}
