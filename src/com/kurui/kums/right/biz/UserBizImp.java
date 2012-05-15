package com.kurui.kums.right.biz;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import com.kurui.kums.base.encrypt.MD5;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.base.Constant;
import com.kurui.kums.right.SysUser;
import com.kurui.kums.right.UserListForm;
import com.kurui.kums.right.UserRightInfo;
import com.kurui.kums.right.dao.UserDAO;

public class UserBizImp implements UserBiz {
	private UserDAO userDAO;
	private TransactionTemplate transactionTemplate;

	public SysUser getCurrentUser(HttpServletRequest request)
			throws AppException {
		UserRightInfo uri = (UserRightInfo) request.getSession().getAttribute(
				"URI");
		SysUser user = uri.getUser();
		return user;
	}

	public SysUser getUserById(long id) throws AppException {
		return userDAO.getUserById(id);
	}

	public List getUsers() throws AppException {
		return userDAO.list();
	}

	public List getUsers(UserListForm ulf) throws AppException {
		return userDAO.list(ulf);
	}

	public SysUser login(String userNo, String userPassword)
			throws AppException {
		return userDAO.login(userNo, userPassword);
	}

	public int checkUser(SysUser user, String password) throws AppException {
		int loginStatus = 0;
		SysUser tempUser = userDAO.getUserByNo(user);
		if (tempUser == null) {
			loginStatus = 1;
		} else if (!Constant.toString(tempUser.getUserNo()).equalsIgnoreCase(Constant.toString(user.getUserNo()))) {
			loginStatus = 1;
		} else {
			if (!Constant.toString(tempUser.getUserPassword()).equals(MD5.encrypt(password))) {
				loginStatus = 2;
			}
		}
		return loginStatus;
	}

	public void saveUser(SysUser user) throws AppException {
		userDAO.save(user);
	}

	public long updateUserPassword(SysUser user) throws AppException {
		return 0;
	}

	public long updateUserInfo(SysUser user) throws AppException {
		userDAO.update(user);
		return user.getUserId();
	}

	public long delete(long id) throws AppException {
		try {
			userDAO.deleteById(id);
			return 1;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	public long deleteUser(long id) throws AppException {
		SysUser user = getUserById(id);
		user.setUserStatus(SysUser.STATES_0);// 将状态变为无效
		return updateUserInfo(user);
	}

	public boolean hasSameNo(String userNo) throws AppException {
		return false;
	}

	public SysUser getUserByNo(SysUser user) throws AppException {
		return userDAO.getUserByNo(user);
	}

	public SysUser getUserByNo(String userNo) throws AppException {
		return userDAO.getUserByNo(userNo);
	}

	public SysUser getUserByName(String userName) throws AppException {
		return userDAO.getUserByName(userName);
	}

	public void setTransactionManager(
			HibernateTransactionManager transactionManager) {
		this.transactionTemplate = new TransactionTemplate(transactionManager);
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

}
