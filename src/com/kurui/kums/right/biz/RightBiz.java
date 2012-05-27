package com.kurui.kums.right.biz;

import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.right.UserRightInfo;

public interface RightBiz {
	public void setRights(UserRightInfo uri, long userId) throws AppException;
}
