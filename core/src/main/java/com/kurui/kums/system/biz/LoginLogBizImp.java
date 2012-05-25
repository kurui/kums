package com.kurui.kums.system.biz;


import java.util.List;

import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.system.LoginLog;
import com.kurui.kums.system.LoginLogListForm;
import com.kurui.kums.system.dao.LoginLogDAO;

public class LoginLogBizImp implements LoginLogBiz {

	private LoginLogDAO loginlogDao;
	private TransactionTemplate transactionTemplate;

	public void setTransactionManager(
			HibernateTransactionManager transactionManager) {
		this.transactionTemplate = new TransactionTemplate(transactionManager);
	}



	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}



	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}



	public void saveLoginLog(LoginLog loginlog) throws AppException {
		loginlogDao.save(loginlog);
	}



	public void setLoginlogDao(LoginLogDAO loginlogDao) {
		this.loginlogDao = loginlogDao;
	}



	public List getLoginLogs(LoginLogListForm lllf) throws AppException {
		return loginlogDao.list(lllf);
		
	}







}
