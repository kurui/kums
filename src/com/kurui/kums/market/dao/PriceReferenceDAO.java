package com.kurui.kums.market.dao;

import java.util.List;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.market.PriceReference;
import com.kurui.kums.market.PriceReferenceListForm;

public interface PriceReferenceDAO {

	public List list(PriceReferenceListForm priceReferenceForm)
			throws AppException;

	public void buildPriceReferenceTree() throws AppException;

	public void delete(long id) throws AppException;

	public long save(PriceReference priceReference) throws AppException;

	public long update(PriceReference priceReference) throws AppException;

	public PriceReference getPriceReferenceById(long priceReferenceId)
			throws AppException;

	public List<PriceReference> getPriceReferenceByIds(String referenceIds)
			throws AppException;

	public List<PriceReference> getPriceReferenceList() throws AppException;

	public List<PriceReference> getPriceReferenceListByType(
			String priceReferenceTypes) throws AppException;

	public List<PriceReference> getPriceReferenceListByIndexIds(int[] indexIds)
			throws AppException;

	public List<PriceReference> getValidPriceReferenceList()
			throws AppException;
}
