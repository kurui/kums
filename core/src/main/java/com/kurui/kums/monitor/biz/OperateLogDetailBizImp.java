package com.kurui.kums.monitor.biz;

import java.util.List;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.monitor.OperateLogDetail;
import com.kurui.kums.monitor.OperateLogDetailListForm;
import com.kurui.kums.monitor.dao.OperateLogDAO;
import com.kurui.kums.monitor.dao.OperateLogDetailDAO;

public class OperateLogDetailBizImp implements OperateLogDetailBiz {
	private OperateLogDetailDAO operateLogDetailDAO;
	private TransactionTemplate transactionTemplate;

	public void setTransactionManager(
			HibernateTransactionManager transactionManager) {
		this.transactionTemplate = new TransactionTemplate(transactionManager);
	}

	public List getOperateLogDetails(OperateLogDetailListForm tslf) throws AppException {
		return operateLogDetailDAO.list(tslf);
	}

	public void saveOperateLogDetail(OperateLogDetail operateLogDetail) throws AppException {
		operateLogDetailDAO.save(operateLogDetail);
	}

	public void deleteOperateLogDetail(int id) throws AppException {
		operateLogDetailDAO.deleteById(id);
	}

	public int updateOperateLogDetail(OperateLogDetail operateLogDetail) throws AppException {
		operateLogDetailDAO.update(operateLogDetail);
		return 0;
	}

	public OperateLogDetail getOperateLogDetailById(int id) throws AppException {
		return operateLogDetailDAO.getOperateLogDetailById(id);
	}

	public void setOperateLogDetailDAO(OperateLogDetailDAO operateLogDetailDAO) {
		this.operateLogDetailDAO = operateLogDetailDAO;
	}
}
