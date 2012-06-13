package com.kurui.kums.right;

import com.kurui.kums.base.database.SelectDataBean;
import com.kurui.kums.right.entity._Role;

public class Role extends _Role {
	private static final long serialVersionUID = 1L;

	private String roleID = "";
	
	private String roleSystemDisabled = "";

//	public static long ROLE_SYSTEM_0 = 0;// 系统角色
//	public static long ROLE_SYSTEM_1 = 1;// 客户角色

	public String getRoleID() {
		if (roleID.equals(""))
			roleID = "-1";
		return roleID;
	}

	public void setRoleID(String roleID) {
		this.roleID = roleID;
	}

	public String getRoleID(String roleKey) {
		if (roleKey.trim().equals(""))
			return "-1";
		else {
			String sql = "select role_id from st_role where role_key='"
					+ roleKey + "' and role_status=1";
			System.out.println("sql=" + sql);
			SelectDataBean sdb = new SelectDataBean();
			sdb.setQuerySQL(sql);
			sdb.executeQuery();
			String roleID = sdb.getColValue(1, 1);
			if (sdb.getRowCount() > 0 || !roleID.equals(""))
				return roleID;
			else
				return "-1";
		}
	}
	
	public String getRoleSystemDisabled() {
		return roleSystemDisabled;
	}

	public void setRoleSystemDisabled(String roleSystemDisabled) {
		this.roleSystemDisabled = roleSystemDisabled;
	}

}
