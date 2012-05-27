package com.kurui.kums.market.biz;

import java.util.List;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.market.SaleEvent;
import com.kurui.kums.market.SaleEventListForm;
import com.kurui.kums.market.dao.SaleEventDAO;

public class SaleEventBizImp implements SaleEventBiz {
	private SaleEventDAO saleEventDAO;

	public List list(SaleEventListForm saleEventForm) throws AppException {
		return saleEventDAO.list(saleEventForm);
	}

	public void delete(long id) throws AppException {
		try {
			saleEventDAO.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteSaleEvent(Long saleEventId) throws AppException {
		SaleEvent saleEvent = getSaleEventById(saleEventId);
		saleEvent.setStatus(SaleEvent.STATES_0);// 将状态变为无效
		update(saleEvent);
	}

	public long save(SaleEvent saleEvent) throws AppException {
		return saleEventDAO.save(saleEvent);
	}

	public long update(SaleEvent saleEvent) throws AppException {
		return saleEventDAO.update(saleEvent);
	}

	public SaleEvent getSaleEventById(long saleEventId) throws AppException {
		return saleEventDAO.getSaleEventById(saleEventId);
	}

	public List<SaleEvent> getSaleEventList() throws AppException {
		return saleEventDAO.getSaleEventList();
	}

	public List<SaleEvent> getSaleEventListByType(String saleEventTypes)
			throws AppException {
		return saleEventDAO.getSaleEventListByType(saleEventTypes);
	}

	public List<SaleEvent> getValidSaleEventList() throws AppException {
		return saleEventDAO.getValidSaleEventList();
	}

	public void setSaleEventDAO(SaleEventDAO saleEventDAO) {
		this.saleEventDAO = saleEventDAO;
	}

}
