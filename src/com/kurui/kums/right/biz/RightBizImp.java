package com.kurui.kums.right.biz;

import java.util.List;

import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.right.RoleRight;
import com.kurui.kums.right.UserRightInfo;
import com.kurui.kums.right.dao.RightDAO;

public class RightBizImp implements RightBiz {
	private RightDAO rightDAO;

	public void setRights(UserRightInfo uri, long userId) throws AppException {
		List list = rightDAO.listRightsByUserId(userId);
		long b = System.currentTimeMillis();

		for (int i = 0; i < list.size(); i++) {
			RoleRight rr = (RoleRight) list.get(i);
			if (rr.getRightCode().equals("sa01")) {
				List list1 = rightDAO.listRoleRights();
				uri.clear();
				for (int j = 0; j < list1.size(); j++) {

					RoleRight rrx = (RoleRight) list1.get(j);

					uri.addRight(rrx.getRightCode(), rrx.getRightAction());
				}

				return;
			}
			uri.addRight(rr.getRightCode(), rr.getRightAction());
		}
	}

	public void setRightDAO(RightDAO rightDAO) {
		this.rightDAO = rightDAO;
	}

}
