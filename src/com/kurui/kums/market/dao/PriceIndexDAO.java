package com.kurui.kums.market.dao;

import java.util.List;

import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.market.PriceIndex;
import com.kurui.kums.market.PriceIndexListForm;

public interface PriceIndexDAO {

	public List list(PriceIndexListForm platComAccountForm) throws AppException;

	public void delete(long id) throws AppException;

	public long save(PriceIndex priceIndex) throws AppException;

	public long update(PriceIndex priceIndex) throws AppException;

	public PriceIndex getPriceIndexById(long priceIndexId) throws AppException;

	public List<PriceIndex> getPriceIndexList() throws AppException;

	public PriceIndex getPriceIndex(long priceReferenceId, String dateString)
			throws AppException;

	public PriceIndex getPriceIndexByPhase(long priceReferenceId,
			String startDate, String endDate) throws AppException;

	public String[] getExistDateArray(String priceReferenceId,
			String startDate, String endDate) throws AppException;

	public String[] getExistDateArray(long priceReferenceId)
			throws AppException;

	public String getReferenceIdsByIndexGroup(int[] indexIdGroup)
			throws AppException;

	public List<PriceIndex> getValidPriceIndexList() throws AppException;

	public List<PriceIndex> getPriceIndexByReferenceId(long referenceId)
			throws AppException;

	public List<PriceIndex> getPriceIndexByIds(String indexIdsStr)
			throws AppException;

}
