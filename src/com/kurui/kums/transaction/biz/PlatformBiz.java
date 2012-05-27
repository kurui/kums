package com.kurui.kums.transaction.biz;

import java.util.List;

import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.transaction.Platform;
import com.kurui.kums.transaction.PlatformListForm;

public interface PlatformBiz {

	public List list(PlatformListForm platformForm) throws AppException;

	public long delete(long id) throws AppException;
	
	public void deletePlatform(Long id) throws AppException;

	public long save(Platform platform) throws AppException;

	public long update(Platform platform) throws AppException;

	public Platform getPlatformById(long platformId) throws AppException;

	public List<Platform> getPlatformList() throws AppException;

	public List<Platform> getValidPlatformList() throws AppException;

}
