package com.kurui.kums.right.dao;

import java.util.List;

import com.kurui.kums.base.database.hibernate.BaseDAO;
import com.kurui.kums.base.exception.AppException;

public interface RightDAO  {
	public List listRoleRights() throws AppException;

	public List listRightsByUserId(long userId) throws AppException;
}
