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
import com.kurui.kums.right.RoleRightForm;

public class RoleRightAction extends BaseAction {
	static Logger logger = Logger.getLogger(RoleRightAction.class.getName());

	public ActionForward updateRight(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		RoleRightForm rrf = (RoleRightForm) form;
		Inform inf = new Inform();
		String sql = "";
		DBExecuteBean dbe = new DBExecuteBean();
		sql = " update Role_Right set ";
		sql += " right_name='" + rrf.getRightName();
		sql += "',right_code='" + rrf.getRightCode();
		sql += "',right_action='" + rrf.getRightAction();
		sql += "',right_event='" + rrf.getRightEvent();
		sql += "',right_description='" + rrf.getRightDescription();
		sql += "' where right_key=" + rrf.getRightKey();

		logger.info("RoleRightAction doUpdate:" + sql);

		dbe.executeUpdateSQL(sql);

		if (dbe.getStatusCode() > 0) {
			inf.setMessage("修改角色权限成功");
			inf.setForwardPage("/right/rolelist.do?thisAction=listRole");
			return forwardInformPageNoBack(inf, mapping, request);
		} else {
			inf.setMessage("修改角色权限失败!");
			return forwardInformPage(inf, mapping, request);
		}
	}

	public ActionForward insertRight(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		RoleRightForm rrf = (RoleRightForm) form;
		Inform inf = new Inform();
		String sql = "";
		DBExecuteBean dbe = new DBExecuteBean();
		sql = "insert into role_right(right_key,right_name,right_code,right_action,right_event,right_description,role_id,right_status)";
		sql += " values(seq_roleright.nextval,'" + rrf.getRightName() + "','"
				+ rrf.getRightCode() + "','";
		sql += rrf.getRightAction() + "','" + rrf.getRightEvent() + "','";
		sql += rrf.getRightDescription() + "'," + rrf.getRoleID() + ",1)";

		logger.info("RoleRightAction doInsert:" + sql);

		dbe.executeUpdateSQL(sql);
		int statusCode=dbe.getStatusCode();
		
		if (statusCode > 0) {
			inf.setMessage("增加角色权限成功");
			inf.setForwardPage("/right/rolelist.do?thisAction=listRole");
			
			return forwardInformPageNoBack(inf, mapping, request);
		} else {
			inf.setMessage("增加角色权限失败!");
			return forwardInformPage(inf, mapping, request);
		}
	}

}