package com.kurui.kums.right.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.kurui.kums.base.database.hibernate.BaseDAOSupport;
import com.kurui.kums.base.database.jdbc.SelectDataBean;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.right.UserRightInfo;

public class RightDAOImp extends BaseDAOSupport implements RightDAO {
	static Logger logger = Logger.getLogger(RightDAOImp.class.getName());

	public List listRoleRights() throws AppException {
		String hql = "from RoleRight ";
		return this.list(hql);
	}

	public List listRightsByUserId(long userId) throws AppException {
		String hql = "from RoleRight where roleId in (select roleId from UserRole where userId ="
				+ userId + ")";

//		logger.info("listRightsByUserId:" + hql);

		return this.list(hql);
	}

	public String[] getMyRightKey(String roleID, HttpServletRequest request) {
		UserRightInfo uri = (UserRightInfo) request.getSession().getAttribute(
				"URI");
		String[] myKey = new String[0];
		String temp = "";
		if (uri == null)
			return myKey;
		else {
			String sql = "select right_key,right_code from role_right where right_status=1 and  role_id="
					+ roleID;
			SelectDataBean sdb = new SelectDataBean();
			sdb.setQuerySQL(sql);
			sdb.executeQuery();
			for (int i = 1; i < sdb.getRowCount() + 1; i++) {
				// if (uri.hasRight(sdb.getColValue(i, "right_code")))
				temp = temp + sdb.getColValue(i, "right_key") + ",";
			}
			myKey = temp.split(",");
			sdb.close();
			return myKey;
		}

	}

	public String[] getRightKeyOfRole(String roleID) {
		String temp = "";
		String[] right = new String[0];
		String sql = "select right_key from role_right where right_status=1 and  role_id="
				+ roleID;
		SelectDataBean sdb = new SelectDataBean();
		sdb.setQuerySQL(sql);
		sdb.executeQuery();
		int rowcount = sdb.getRowCount();
		if (rowcount > 0) {
			right = new String[rowcount];
			for (int i = 1; i < rowcount + 1; i++) {
				temp = sdb.getColValue(i, "right_key");
				right[i - 1] = temp;
			}
		}
		sdb.close();
		return right;
	}
}
