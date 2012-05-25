package com.kurui.kums.market.dao;

import java.util.List;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.market.EstateDish;
import com.kurui.kums.market.EstateDishListForm;

public interface EstateDishDAO {

	public List list(EstateDishListForm EstateDishForm) throws AppException;

	public void delete(long id) throws AppException;

	public long save(EstateDish estateDish) throws AppException;

	public long update(EstateDish estateDish) throws AppException;

	public EstateDish getEstateDishById(long estateDishId) throws AppException;

	public List<EstateDish> getEstateDishByIds(String estateDishIds)
			throws AppException;

	public List<EstateDish> getEstateDishList() throws AppException;

	public List<EstateDish> getValidEstateDishList() throws AppException;
}
