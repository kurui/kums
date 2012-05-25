package com.kurui.kums.agent.biz;

import java.util.List;

import com.kurui.kums.agent.biz.DirectLevelBiz;
import com.kurui.kums.agent.DirectLevel;
import com.kurui.kums.agent.DirectLevelListForm;
import com.kurui.kums.agent.dao.DirectLevelDAO;
import com.kurui.kums.base.exception.AppException;

public class DirectLevelBizImp implements DirectLevelBiz {
	private DirectLevelDAO directLevelDAO;

	public List list(DirectLevelListForm directLevelListForm) throws AppException {
		return directLevelDAO.list(directLevelListForm);
	}

	public long delete(long id) throws AppException {
		try {
			directLevelDAO.delete(id);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public void deleteDirectLevel(Long id) throws AppException {
		DirectLevel directLevel = getDirectLevelById(id);
		directLevel.setStatus(DirectLevel.STATES_0);// 将状态变为无效
		update(directLevel);
	}

	public long save(DirectLevel directLevel) throws AppException {
		return directLevelDAO.save(directLevel);
	}

	public long update(DirectLevel directLevel) throws AppException {
		return directLevelDAO.update(directLevel);
	}

	public DirectLevel getDirectLevelById(long id) throws AppException {
		return directLevelDAO.getDirectLevelById(id);
	}

	public List<DirectLevel> getDirectLevelList() throws AppException {
		return directLevelDAO.getDirectLevelList();
	}

	public List<DirectLevel> getDirectLevelList(Long type) throws AppException {
		return directLevelDAO.getDirectLevelList(type);
	}

	public void setDirectLevelDAO(DirectLevelDAO directLevelDAO) {
		this.directLevelDAO = directLevelDAO;
	}
}
