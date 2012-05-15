package com.kurui.kums.market.biz;

import java.util.List;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.market.EstateDish;
import com.kurui.kums.market.EstateDishListForm;
import com.kurui.kums.market.dao.EstateDishDAO;

public class EstateDishBizImp implements EstateDishBiz {
	private EstateDishDAO estateDishDAO;

	public List list(EstateDishListForm estateDishListForm) throws AppException {
		return estateDishDAO.list(estateDishListForm);
	}

	public void delete(long id) throws AppException {
		try {
			estateDishDAO.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteEstateDish(Long estateDishId) throws AppException {
		EstateDish estateDish = getEstateDishById(estateDishId);
		estateDish.setStatus(EstateDish.STATES_0);// 将状态变为无效
		update(estateDish);
	}

	public long save(EstateDish estateDish) throws AppException {
		return estateDishDAO.save(estateDish);
	}

	public long update(EstateDish estateDish) throws AppException {
		return estateDishDAO.update(estateDish);
	}

	public EstateDish getEstateDishById(long estateDishId) throws AppException {
		return estateDishDAO.getEstateDishById(estateDishId);
	}

	public List<EstateDish> getEstateDishList() throws AppException {
		return estateDishDAO.getEstateDishList();
	}

	public List<EstateDish> getValidEstateDishList() throws AppException {
		return estateDishDAO.getValidEstateDishList();
	}

	public void setEstateDishDAO(EstateDishDAO estateDishDAO) {
		this.estateDishDAO = estateDishDAO;
	}

}
