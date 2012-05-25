package com.kurui.kums.market.dao;

import java.util.List;

import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.market.SaleEvent;
import com.kurui.kums.market.SaleEventListForm;

public interface SaleEventDAO {

	public List list(SaleEventListForm saleEventForm) throws AppException;

	public void delete(long id) throws AppException;

	public long save(SaleEvent saleEvent) throws AppException;

	public long update(SaleEvent saleEvent) throws AppException;

	public SaleEvent getSaleEventById(long saleEventId) throws AppException;

	public List<SaleEvent> getSaleEventList() throws AppException;

	public List<SaleEvent> getSaleEventListByType(String saleEventTypes)
			throws AppException;

	public List<SaleEvent> getValidSaleEventList() throws AppException;
}
