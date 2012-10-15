package com.kurui.kums.finance.dao;

import java.util.List;

import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.finance.AssetsItem;
import com.kurui.kums.finance.AssetsItemListForm;

public interface AssetsItemDAO {
	public List list(AssetsItemListForm agentListForm) throws AppException;
	public List listSTA(AssetsItemListForm assetsItemListForm)throws AppException;

	public void delete(long id) throws AppException;

	public long save(AssetsItem vehicle) throws AppException;

	public long update(AssetsItem vehicle) throws AppException;

	public AssetsItem getAssetsItemById(long id) throws AppException;

	public AssetsItem getAssetsItemByAgentId(long agentId) throws AppException;

	public List<AssetsItem> getAssetsItemList(Long type) throws AppException;

	public List<AssetsItem> getValidAssetsItemList() throws AppException;

	
}
