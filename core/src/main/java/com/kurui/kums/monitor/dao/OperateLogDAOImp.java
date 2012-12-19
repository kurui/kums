package com.kurui.kums.monitor.dao;

import java.util.List;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import com.kurui.kums.base.database.hibernate.BaseDAOSupport;
import com.kurui.kums.base.database.hibernate.Hql;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.monitor.OperateLog;
import com.kurui.kums.monitor.OperateLogListForm;

public class OperateLogDAOImp extends BaseDAOSupport implements OperateLogDAO {
	private TransactionTemplate transactionTemplate;

	public void setTransactionManager(
			PlatformTransactionManager transactionManager) {
		this.transactionTemplate = new TransactionTemplate(transactionManager);
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public boolean getOperateLogByUserId(long id) throws AppException {
		String hql = " from OperateLog where sysUser.userId=" + id;
		List list = this.list(hql);
		if (list.size() > 0) {
			return true;
		} else
			return false;
	}

	public List list(OperateLogListForm sllf) throws AppException {
		String hql = "from OperateLog t where 1=1 ";
		try {
			String logUser = sllf.getUserNo().toString().trim();
			String formDate = sllf.getStartDate().toString().trim();
			String toDate = sllf.getEndDate().toString().trim();
			if (logUser != "" && logUser != null) {
				hql += " and sysUser.userNo like '%" + logUser + "%'";
			}
			if (!"".equals(formDate) && formDate != null && !"".equals(toDate)
					&& toDate != null) {
				hql += " and  to_char(optTime,'yyyy-MM-dd') between '"
						+ formDate + "' and '" + toDate + "'";

			}
			if (sllf.getOrderNo() != null && (!(sllf.getOrderNo().equals("")))) {
				hql += " and t.orderNo='" + sllf.getOrderNo() + "'";
			}
			hql += " order by optTime desc ";
		} catch (Exception ex) {
			ex.getMessage();
		}
		return this.list(hql, sllf);
	}

	public List list(OperateLog operateLog) throws AppException {
		return null;
	}

	public List getOperateLogByOrderNo(String orderNo) throws AppException {
		Hql hql = new Hql();
		hql.add("from OperateLog t where t.orderNo like '%" + orderNo + "%'");
		hql.add(" order by id desc ");

		return this.list(hql);
	}

	public List getOperateLogByOrderIds(String orderIds) throws AppException {
		Hql hql = new Hql();
		hql.add("from OperateLog t where t.orderId in (" + orderIds + ")");
		hql.add(" order by id desc ");
		return this.list(hql);
	}

	public long save(OperateLog operateLog) throws AppException {
		this.getHibernateTemplate().save(operateLog);
		return operateLog.getId();
	}

	public long merge(OperateLog operateLog) throws AppException {
		this.getHibernateTemplate().merge(operateLog);
		return operateLog.getId();
	}

	public long update(OperateLog operateLog) throws AppException {
		if (operateLog.getId() > 0)
			return save(operateLog);
		else
			throw new IllegalArgumentException("id isn't a valid argument.");
	}

	public void deleteById(long id) throws AppException {
		if (id > 0) {
			OperateLog operateLog = (OperateLog) this.getHibernateTemplate().get(
					OperateLog.class, new Long(id));
			this.getHibernateTemplate().delete(operateLog);
		}
	}

	public OperateLog getOperateLogById(long id) throws AppException {
		OperateLog operateLog;
		if (id > 0) {
			operateLog = (OperateLog) this.getHibernateTemplate().get(
					OperateLog.class, new Long(id));
			return operateLog;
		} else
			return new OperateLog();
	}

}
