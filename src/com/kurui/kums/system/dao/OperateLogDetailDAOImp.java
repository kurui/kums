package com.kurui.kums.system.dao;

import java.util.List;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import com.kurui.kums.base.database.BaseDAOSupport;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.system.OperateLogDetail;
import com.kurui.kums.system.OperateLogDetailListForm;

public class OperateLogDetailDAOImp extends BaseDAOSupport implements OperateLogDetailDAO {
	private TransactionTemplate transactionTemplate;

	public void setTransactionManager(
			PlatformTransactionManager transactionManager) {
		this.transactionTemplate = new TransactionTemplate(transactionManager);
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}


	public List list(OperateLogDetailListForm sllf) throws AppException {
		String hql = "from OperateLogDetail t where 1=1 ";
		try {


		} catch (Exception ex) {
			ex.getMessage();
		}
		return this.list(hql, sllf);
	}

	public List list(OperateLogDetail operateLog) throws AppException {
		return null;
	}

	public long save(OperateLogDetail operateLog) throws AppException {
		this.getHibernateTemplate().save(operateLog);
		return operateLog.getId();
	}

	public long merge(OperateLogDetail operateLog) throws AppException {
		this.getHibernateTemplate().merge(operateLog);
		return operateLog.getId();
	}

	public long update(OperateLogDetail operateLog) throws AppException {
		if (operateLog.getId() > 0)
			return save(operateLog);
		else
			throw new IllegalArgumentException("id isn't a valid argument.");
	}

	public void deleteById(long id) throws AppException {
		if (id > 0) {
			OperateLogDetail operateLog = (OperateLogDetail) this.getHibernateTemplate().get(
					OperateLogDetail.class, new Long(id));
			this.getHibernateTemplate().delete(operateLog);
		}
	}

	public OperateLogDetail getOperateLogDetailById(long id) throws AppException {
		OperateLogDetail operateLog;
		if (id > 0) {
			operateLog = (OperateLogDetail) this.getHibernateTemplate().get(
					OperateLogDetail.class, new Long(id));
			return operateLog;
		} else
			return new OperateLogDetail();
	}

}
