package com.kurui.kums.finance.biz;

import java.util.List;

import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.finance.AssetsItem;
import com.kurui.kums.finance.AssetsItemListForm;

public interface AssetsItemBiz {

	public List list(AssetsItemListForm assetsItemListForm) throws AppException;

	public long delete(long id) throws AppException;

	public void deleteAssetsItem(Long id) throws AppException;

	public long save(AssetsItem assetsItem) throws AppException;

	public long update(AssetsItem assetsItem) throws AppException;

	public AssetsItem getAssetsItemById(long id) throws AppException;

	public List<AssetsItem> getAssetsItemList(Long type) throws AppException;

}
