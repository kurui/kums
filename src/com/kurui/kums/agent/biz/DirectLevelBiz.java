package com.kurui.kums.agent.biz;

import java.util.List;

import com.kurui.kums.agent.DirectLevel;
import com.kurui.kums.agent.DirectLevelListForm;
import com.kurui.kums.base.exception.AppException;

public interface DirectLevelBiz {

	public List list(DirectLevelListForm directLevelListForm)
			throws AppException;

	public long delete(long id) throws AppException;

	public void deleteDirectLevel(Long id) throws AppException;

	public long save(DirectLevel directLevel) throws AppException;

	public long update(DirectLevel directLevel) throws AppException;

	public DirectLevel getDirectLevelById(long id) throws AppException;

	public List<DirectLevel> getDirectLevelList() throws AppException;

	public List<DirectLevel> getDirectLevelList(Long type) throws AppException;

}
