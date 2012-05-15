package com.kurui.kums.right.action;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.kurui.kums.base.BaseAction;
import com.kurui.kums.base.Inform;
import com.kurui.kums.base.database.DBExecuteBatch;
import com.kurui.kums.base.database.SelectDataBean;
import com.kurui.kums.base.database.ListMenu;
import com.kurui.kums.base.Constant;
import com.kurui.kums.right.Role;
import com.kurui.kums.right.RoleListForm;
import com.kurui.kums.right.RoleRightForm;
import com.kurui.kums.right.UserRightInfo;

public class RoleListAction extends BaseAction {
	static Logger logger = Logger.getLogger(RoleListAction.class.getName());

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String forwardPage = "";
		RoleListForm ulf = (RoleListForm) form;
		UserRightInfo uri = (UserRightInfo) request.getSession().getAttribute(
				"URI");
		if (uri == null)
			return (mapping.findForward("valid"));
		String action = ulf.getThisAction();

		if (action.equals("addRole")) {
			Role rf = new Role();
			rf.setThisAction("insertRole");
			request.setAttribute("rf", rf);
			forwardPage = "editRole";
		} else if (action.equals("editRole")) {
			return editRole(mapping, form, request, response);
		}
		// ===============================================================
		else if (action.equals("delRight")) {
			doDeleteRight(ulf, request);
			return listRole(mapping, form, request, response);
		} else if (action.equals("editRight")) {
			return editRight(mapping, form, request, response);
		}

		else if (action.equals("addRight")) {
			RoleRightForm rrf = new RoleRightForm();
			rrf.setRoleID(ulf.getRoleID());
			rrf.setThisAction("insertRight");
			request.setAttribute("rrf", rrf);
			forwardPage = "editRight";
		}

		else if (action.equals("edituser4role")) {
			doEditUser4role(ulf, request, uri);
			forwardPage = "edituser4role";
		} else if (action.equals("updateuser4role")) {
			doUpdateUser4role(ulf, request, uri);
			forwardPage = "inform";
		}

		// 给用户分配角色
		else if (action.equals("editrole4user")) {
			doEditRole4User(ulf, request, uri);
			forwardPage = "editrole4user";
		} else if (action.equals("updaterole4user")) {
			doUpdateRole4User(ulf, request, uri);
			forwardPage = "inform";
		} else {
			return listRole(mapping, form, request, response);
		}
		return (mapping.findForward(forwardPage));
	}

	public ActionForward listRole(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		RoleListForm ulf = (RoleListForm) form;
		String sql = "";
		String temp = "";
		int rowcount = 0;
		int srowcount = 0;

		ArrayList list = new ArrayList();
		ArrayList slist = new ArrayList();

		sql = "select * from role where role_status=1  order by role_name ";
		SelectDataBean sdb = new SelectDataBean();
		sdb.setQuerySQL(sql);
		sdb.executeQuery();
		rowcount = sdb.getRowCount();

		for (int i = 1; i < rowcount + 1; i++) {
			Role rf = new Role();
			temp = sdb.getColValue(i, "role_id");
			if (ulf.getRoleID().equals("") && i == 1)
				ulf.setRoleID(temp);
			rf.setRoleID(temp);
			rf.setRoleName(sdb.getColValue(i, "role_name"));
			rf.setRoleKey(sdb.getColValue(i, "role_key"));
			rf.setRoleDescription(sdb.getColValue(i, "role_description"));
			rf.setRoleSystemDisabled((sdb.getColValue(i, "role_system").equals(
					"0") ? "true" : "false"));
			list.add(rf);
		}
		sql = "select * from role_right r join role o on r.role_id=o.role_id where r.right_status=1 and  r.role_id="
				+ ulf.getRoleID() + " order by r.right_code";
		sdb.setQuerySQL(sql);
		sdb.executeQuery();
		srowcount = sdb.getRowCount();
		for (int j = 1; j < srowcount + 1; j++) {
			RoleRightForm rrf = new RoleRightForm();
			rrf.setRightKey(sdb.getColValue(j, "right_key"));
			rrf.setRightName(sdb.getColValue(j, "right_name"));
			rrf.setRightCode(sdb.getColValue(j, "right_code"));
			ulf.setRoleName(sdb.getColValue(j, "role_name"));
			rrf.setRightDescription(sdb.getColValue(j, "right_description"));
			rrf.setRightAction(sdb.getColValue(j, "right_action"));
			rrf.setRightEvent(sdb.getColValue(j, "right_event"));
			slist.add(rrf);
		}
		ulf.setList(list);
		ulf.setThisAction("");
		request.setAttribute("ulf", ulf);
		request.setAttribute("slist", slist);
		sdb.close();

		String forwardPage = "listRole";
		return mapping.findForward(forwardPage);
	}

	public void doEditUser4role(RoleListForm ulf, HttpServletRequest request,
			UserRightInfo uri) {
		String sql = "";

		sql = "select role_id,role_name from role where 1=1 and role_status=1 order by role_name";
		ListMenu rolelist = new ListMenu(sql, false);
		request.setAttribute("rolelist", rolelist);
		if (Constant.toInt(ulf.getRoleID()) < 1) {
			ulf.setRoleID(rolelist.getValue()[0]);
		}
		String sql1 = "";
		String sql2 = "";
		sql1 = "select u.user_id,user_no||'--'||user_name  as user_name from sys_user u where user_status=1 "
				+ " and not exists (select * from user_role r where r.role_id="
				+ ulf.getRoleID()
				+ " and u.user_id=r.user_id) order by  user_no,user_name";

		sql2 = "select u.user_id,user_no||'--'||user_name  as user_name from user_role r join sys_user u on u.user_id=r.user_id where r.role_id="
				+ ulf.getRoleID() + " order by  user_no,user_name";

		ListMenu userlist1 = new ListMenu(sql1, false);
		ListMenu userlist2 = new ListMenu(sql2, false);
		request.setAttribute("userlist1", userlist1);
		request.setAttribute("userlist2", userlist2);
		request.setAttribute("ulf", ulf);
	}

	public void doUpdateUser4role(RoleListForm ulf, HttpServletRequest request,
			UserRightInfo uri) {
		String sql = "";
		DBExecuteBatch dbt = new DBExecuteBatch();

		sql = "delete from user_role where role_id=" + ulf.getRoleID();
		dbt.append(sql);
		if (ulf.getCount() > 0) {
			for (int i = 0; i < ulf.getRightUserID().length; i++) {
				sql = "insert into user_role(user_role_id,user_id,role_id) values(seq_userrole.nextval,"
						+ ulf.getRightUserID()[i] + "," + ulf.getRoleID() + ")";
				dbt.append(sql);
			}
		}
		dbt.executeBatch();
		Inform inf = new Inform();
		if (dbt.isSuccess()) {
			inf.setMessage("您已经成功给用户授权!");
			inf.setForwardPage("/right/rolelist.do?thisAction=edituser4role");
		} else {
			inf.setMessage("修改失败!" + dbt.getMessage());
			inf.setBack(true);
		}
		request.setAttribute("inf", inf);
	}

	// 给用户分配角色
	public void doEditRole4User(RoleListForm ulf, HttpServletRequest request,
			UserRightInfo uri) {
		String sql = "";

		sql = "select user_id,user_no||'--'||user_name  as user_name from sys_user where user_status=1 order by user_no||'--'||user_name";
		ListMenu userlist = new ListMenu(sql, false);
		request.setAttribute("userlist", userlist);

		String sql1 = "";
		String sql2 = "";

		if (Constant.toInt(ulf.getUserID()) < 1) {
			ulf.setUserID(userlist.getValue()[0]);
		}

		sql1 = "select role_id,role_name from role where 1=1  and role_status=1 and not exists (select * from user_role where user_role.role_id=role.role_id  and user_id="
				+ ulf.getUserID() + ") order by role_name";

		sql2 = "select r.role_id,role_name from user_role ur join role r on ur.role_id=r.role_id where user_id="
				+ ulf.getUserID() + " order by role_name";

		ListMenu rolelist1 = new ListMenu(sql1, false);
		ListMenu rolelist2 = new ListMenu(sql2, false);
		request.setAttribute("rolelist1", rolelist1);
		request.setAttribute("rolelist2", rolelist2);
		request.setAttribute("ulf", ulf);
	}

	public void doUpdateRole4User(RoleListForm ulf, HttpServletRequest request,
			UserRightInfo uri) {
		String sql = "";
		DBExecuteBatch dbt = new DBExecuteBatch();

		sql = "delete from user_role where user_id=" + ulf.getUserID();
		dbt.append(sql);
		if (ulf.getCount() > 0) {
			for (int i = 0; i < ulf.getRightRoleID().length; i++) {
				sql = "insert into user_role(user_role_id,user_id,role_id) values(seq_userrole.nextval,"
						+ ulf.getUserID() + "," + ulf.getRightRoleID()[i] + ")";
				dbt.append(sql);
			}
		}
		dbt.executeBatch();
		Inform inf = new Inform();
		if (dbt.isSuccess()) {
			inf.setMessage("您已经成功给用户授权!");
			inf.setForwardPage("/right/rolelist.do?thisAction=editrole4user");
		} else {
			inf.setMessage("修改失败!" + dbt.getMessage());
			inf.setBack(true);
		}
		request.setAttribute("inf", inf);
	}

	public ActionForward editRole(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		RoleListForm ulf = (RoleListForm) form;
		String sql = "";
		sql = "select * from role where role_id=";
		sql = sql + ulf.getRoleID();
		SelectDataBean sdb = new SelectDataBean();
		sdb.setQuerySQL(sql);
		sdb.executeQuery();
		if (sdb.getRowCount() == 1) {
			Role rf = new Role();
			rf.setRoleID(sdb.getColValue(1, "role_id"));
			rf.setRoleName(sdb.getColValue(1, "role_name"));
			rf.setRoleKey(sdb.getColValue(1, "role_key"));
			rf.setRoleDescription(sdb.getColValue(1, "role_description"));

			rf.setThisAction("updateRole");
			request.setAttribute("rf", rf);
		}
		sdb.close();
		String forwardPage = "editRole";
		return mapping.findForward(forwardPage);
	}

	public void doDeleteRight(RoleListForm ulf, HttpServletRequest request) {
		String sql = "";
		String[] items = ulf.getSelectedRightItems();

		try {
			DBExecuteBatch dbt = new DBExecuteBatch();
			for (int i = 0; i < items.length; i++) {
				sql = "delete from role_right where right_key=" + items[i];
				dbt.append(sql);
			}
			dbt.executeBatch();
		} catch (Exception ex) {
			System.out.println("" + ex);
		}
	}

	public ActionForward editRight(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		RoleListForm ulf = (RoleListForm) form;
		String sql = "";
		sql = "select * from role_right where right_key="
				+ ulf.getSelectedRightItems()[0];
		SelectDataBean sdb = new SelectDataBean();
		sdb.setQuerySQL(sql);
		sdb.executeQuery();
		if (sdb.getRowCount() == 1) {
			RoleRightForm rrf = new RoleRightForm();
			rrf.setRightKey(sdb.getColValue(1, "right_key"));
			rrf.setRightName(sdb.getColValue(1, "right_name"));
			rrf.setRightCode(sdb.getColValue(1, "right_code"));
			rrf.setRightAction(sdb.getColValue(1, "right_action"));
			rrf.setRightEvent(sdb.getColValue(1, "right_event"));
			rrf.setRightDescription(sdb.getColValue(1, "right_description"));
			rrf.setThisAction("updateRight");
			request.setAttribute("rrf", rrf);
		} else {
			log.error("editRight getRowCount!=1..");
		}
		sdb.close();
		String forwardPage = "editRight";
		return mapping.findForward(forwardPage);
	}

	public ActionForward delRole(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		RoleListForm ulf = (RoleListForm) form;
		String sql = "";
		Inform inf = new Inform();
		String userRole = ulf.getRoleID();
		try {
			sql = "select role_id from user_role where role_id=" + userRole;
			SelectDataBean sdb = new SelectDataBean();
			sdb.setQuerySQL(sql);
			sdb.executeQuery();
			if (sdb.getRowCount() > 0) {
				inf.setMessage("该用户角色已经被使用,不能删除!");
				inf.setForwardPage("/right/rolelist.do?thisAction=listRole");
			} else {
				sql = " delete role where role_id=" + userRole;
				sdb.executeUpdateSQL(sql);
				inf.setMessage("您已经成功删除该用户角色!");
				inf.setForwardPage("/right/rolelist.do?thisAction=listRole");
			}
			sdb.close();
			return forwardInformPage(inf, mapping, request);
		} catch (Exception ex) {
			inf.setMessage("系统出错:" + ex.getMessage());
			return forwardInformPage(inf, mapping, request);
		}
	}

}