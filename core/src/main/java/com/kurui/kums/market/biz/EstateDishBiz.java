package com.kurui.kums.market.biz;

import java.util.List;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.market.EstateDish;
import com.kurui.kums.market.EstateDishListForm;

public interface EstateDishBiz {

	public List list(EstateDishListForm form) throws AppException;

	public void delete(long id) throws AppException;

	public void deleteEstateDish(Long estateDishId) throws AppException;

	public long save(EstateDish estateDish) throws AppException;

	public long update(EstateDish estateDish) throws AppException;

	public EstateDish getEstateDishById(long estateDishId) throws AppException;

	public List<EstateDish> getEstateDishList() throws AppException;

	public List<EstateDish> getValidEstateDishList() throws AppException;

}
