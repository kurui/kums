package com.kurui.kums.market.biz;

import java.util.List;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.market.PriceReference;
import com.kurui.kums.market.PriceReferenceListForm;
import com.kurui.kums.market.dao.PriceReferenceDAO;

public class PriceReferenceBizImp implements PriceReferenceBiz {
	private PriceReferenceDAO priceReferenceDAO;

	public void buildPriceReferenceTree() throws AppException {
		priceReferenceDAO.buildPriceReferenceTree();
	}

	public List list(PriceReferenceListForm priceReferenceForm)
			throws AppException {
		return priceReferenceDAO.list(priceReferenceForm);
	}

	public void delete(long id) throws AppException {
		try {
			priceReferenceDAO.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deletePriceReference(Long priceReferenceId) throws AppException {
		PriceReference priceReference = getPriceReferenceById(priceReferenceId);
		priceReference.setStatus(PriceReference.STATES_0);// 将状态变为无效
		update(priceReference);
	}

	public long save(PriceReference priceReference) throws AppException {
		return priceReferenceDAO.save(priceReference);
	}

	public long update(PriceReference priceReference) throws AppException {
		return priceReferenceDAO.update(priceReference);
	}

	public PriceReference getPriceReferenceById(long priceReferenceId)
			throws AppException {
		return priceReferenceDAO.getPriceReferenceById(priceReferenceId);
	}

	public List<PriceReference> getPriceReferenceList() throws AppException {
		return priceReferenceDAO.getPriceReferenceList();
	}

	public List<PriceReference> getPriceReferenceListByIndexIds(int[] indexIds)
			throws AppException {
		return priceReferenceDAO.getPriceReferenceListByIndexIds(indexIds);
	}

	public List<PriceReference> getPriceReferenceListByType(
			String priceReferenceTypes) throws AppException {
		return priceReferenceDAO
				.getPriceReferenceListByType(priceReferenceTypes);
	}

	public List<PriceReference> getValidPriceReferenceList()
			throws AppException {
		return priceReferenceDAO.getValidPriceReferenceList();
	}

	public void setPriceReferenceDAO(PriceReferenceDAO priceReferenceDAO) {
		this.priceReferenceDAO = priceReferenceDAO;
	}

}
