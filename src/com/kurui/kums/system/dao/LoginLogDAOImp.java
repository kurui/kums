package com.kurui.kums.system.dao;

import java.util.List;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import com.kurui.kums.base.database.BaseDAOSupport;
import com.kurui.kums.base.database.Hql;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.system.LoginLog;
import com.kurui.kums.system.LoginLogListForm;

public class LoginLogDAOImp extends BaseDAOSupport implements LoginLogDAO {
	private TransactionTemplate transactionTemplate;

	public void setTransactionManager(
			PlatformTransactionManager transactionManager) {
		this.transactionTemplate = new TransactionTemplate(transactionManager);
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public long save(LoginLog loginlog) throws AppException {
		this.getHibernateTemplate().saveOrUpdate(loginlog);
		return loginlog.getId();
	}

	public List list(LoginLogListForm lllf) throws AppException {

		Hql hql = new Hql();
		hql
				.add("from LoginLog tat where tat.locate=? and tat.loginName like ?");
		hql.addParamter(lllf.getLocate());
		hql.addParamter("%" + lllf.getUserNo() + "%");
		String beginDate = lllf.getStartDate().toString().trim();
		String endDate = lllf.getEndDate().toString().trim();

		if ("".equals(beginDate) == false && "".equals(endDate) == true) {
			hql.add(" and to_char(tat.loginTime,'yyyy-mm-dd hh24:mi:ss') > ?");
			hql.addParamter(beginDate);
		}
		if ("".equals(beginDate) == true && "".equals(endDate) == false) {
			hql.add(" and to_char(tat.loginTime,'yyyy-mm-dd hh24:mi:ss') < ?");
			hql.addParamter(endDate);
		}
		if ("".equals(beginDate) == false && "".equals(endDate) == false) {
			hql
					.add(" and  to_char(tat.loginTime,'yyyy-mm-dd hh24:mi:ss')  between ? and ? ");
			hql.addParamter(beginDate);
			hql.addParamter(endDate);
		}

		hql.add(" order by loginTime desc");
		// System.out.println("hql:>>>>>>>>>" + hql);

		return this.list(hql, lllf);
	}

}
