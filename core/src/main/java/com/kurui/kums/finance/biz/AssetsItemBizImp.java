package com.kurui.kums.finance.biz;

import java.util.List;

import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.finance.AssetsItem;
import com.kurui.kums.finance.AssetsItemListForm;
import com.kurui.kums.finance.dao.AssetsItemDAO;

public class AssetsItemBizImp implements AssetsItemBiz {
	private AssetsItemDAO assetsItemDAO;

	public List list(AssetsItemListForm assetsItemListForm) throws AppException {
		return assetsItemDAO.list(assetsItemListForm);
	}

	public long delete(long id) throws AppException {
		try {
			assetsItemDAO.delete(id);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public void deleteAssetsItem(Long id) throws AppException {
		AssetsItem assetsItem = getAssetsItemById(id);
		assetsItem.setStatus(AssetsItem.STATES_0);// 将状态变为无效
		update(assetsItem);
	}

	public long save(AssetsItem assetsItem) throws AppException {
		return assetsItemDAO.save(assetsItem);
	}

	public long update(AssetsItem assetsItem) throws AppException {
		return assetsItemDAO.update(assetsItem);
	}

	public AssetsItem getAssetsItemById(long id) throws AppException {
		return assetsItemDAO.getAssetsItemById(id);
	}

	public List<AssetsItem> getAssetsItemList(Long type) throws AppException {
		return assetsItemDAO.getAssetsItemList(type);
	}

	public void setAssetsItemDAO(AssetsItemDAO assetsItemDAO) {
		this.assetsItemDAO = assetsItemDAO;
	}
}
