package com.kurui.kums.market.biz;

import java.util.List;

import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.market.SaleEvent;
import com.kurui.kums.market.SaleEventListForm;

public interface SaleEventBiz {

	public List list(SaleEventListForm form) throws AppException;

	public void delete(long id) throws AppException;

	public void deleteSaleEvent(Long saleEventId) throws AppException;

	public long save(SaleEvent saleEvent) throws AppException;

	public long update(SaleEvent saleEvent) throws AppException;

	public SaleEvent getSaleEventById(long saleEventId)
			throws AppException;

	public List<SaleEvent> getSaleEventList() throws AppException;

	public List<SaleEvent> getSaleEventListByType(
			String saleEventTypes) throws AppException;

	public List<SaleEvent> getValidSaleEventList()
			throws AppException;

}
