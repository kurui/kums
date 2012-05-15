package com.kurui.kums.transaction.biz;

import java.util.List;

import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.transaction.Platform;
import com.kurui.kums.transaction.PlatformListForm;
import com.kurui.kums.transaction.dao.PlatformDAO;

public class PlatformBizImp implements PlatformBiz {
	private PlatformDAO platformDAO;

	public List list(PlatformListForm platformForm) throws AppException {
		return platformDAO.list(platformForm);
	}

	public long delete(long id) throws AppException {
		try {
			platformDAO.delete(id);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public void deletePlatform(Long id) throws AppException {
		Platform platform = getPlatformById(id);
		platform.setStatus(Platform.STATES_0);// 将状态变为无效
		update(platform);
	}

	public long save(Platform platform) throws AppException {
		return platformDAO.save(platform);
	}

	public long update(Platform platform) throws AppException {
		return platformDAO.update(platform);
	}

	public Platform getPlatformById(long platformId) throws AppException {
		return platformDAO.getPlatformById(platformId);
	}

	public List<Platform> getPlatformList() throws AppException {
		return platformDAO.getPlatformList();
	}

	public List<Platform> getValidPlatformList() throws AppException {
		return platformDAO.getValidPlatformList();
	}

	public void setPlatformDAO(PlatformDAO platformDAO) {
		this.platformDAO = platformDAO;
	}
}
