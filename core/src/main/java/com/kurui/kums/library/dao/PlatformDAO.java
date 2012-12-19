package com.kurui.kums.library.dao;

import java.util.List;

import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.library.Platform;
import com.kurui.kums.library.PlatformListForm;

public interface PlatformDAO {

	public List list(PlatformListForm platformForm) throws AppException;

	public void delete(long id) throws AppException;

	public long save(Platform platform) throws AppException;

	public long update(Platform platform) throws AppException;

	public Platform getPlatformById(long platformId) throws AppException;

	public List<Platform> getPlatformList() throws AppException;

	public List<Platform> getValidPlatformList() throws AppException;
}
