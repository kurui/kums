package com.kurui.kums.right.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.kurui.kums.base.BaseAction;
import com.kurui.kums.base.Inform;
import com.kurui.kums.base.database.DBExecuteBean;
import com.kurui.kums.right.Role;

public class RoleAction extends BaseAction {

	static Logger logger = Logger.getLogger(RoleAction.class.getName());

	public ActionForward updateRole(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Role rf = (Role) form;
		Inform inf = new Inform();
		String sql = "";
		DBExecuteBean dbe = new DBExecuteBean();

		sql = "update Role set ";
		sql += " role_name='" + rf.getRoleName();
		sql += "',role_key='" + rf.getRoleKey();
		sql += "',role_description='" + rf.getRoleDescription();
		sql += "' where role_id=" + rf.getRoleID();

		log.info("RoleAction doUpdate：" + sql);

		dbe.executeUpdateSQL(sql);

		if (dbe.getStatusCode() > 0) {
			inf.setMessage("您已经成功修改了角色!");
			inf.setForwardPage("/right/rolelist.do?thisAction=listRole");
			return forwardInformPageNoBack(inf, mapping, request);
		} else {
			inf.setMessage("修改角色失败!");
			return forwardInformPage(inf, mapping, request);
		}
	}

	public ActionForward insertRole(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Role rf = (Role) form;
		Inform inf = new Inform();
		String sql = "";
		DBExecuteBean dbe = new DBExecuteBean();

		sql = "insert into role(role_id,role_name,role_key,role_description,role_status,role_type)";
		sql += " values(seq_role.nextval,'" + rf.getRoleName() + "','";
		sql += rf.getRoleKey() + "','";
		sql += rf.getRoleDescription() + "',1,1)";

		log.info("RoleAction doInsert：" + sql);

		dbe.executeUpdateSQL(sql);

		if (dbe.getStatusCode() > 0) {
			inf.setMessage("增加角色成功");
			inf.setForwardPage("/right/rolelist.do?thisAction=listRole");
			return forwardInformPageNoBack(inf, mapping, request);
		} else {
			inf.setMessage("增加角色失败!");
			return forwardInformPage(inf, mapping, request);
		}
	}

}
