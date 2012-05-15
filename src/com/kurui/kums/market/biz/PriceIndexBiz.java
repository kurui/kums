package com.kurui.kums.market.biz;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.market.PriceIndex;
import com.kurui.kums.market.PriceIndexListForm;

public interface PriceIndexBiz {

	public List list(PriceIndexListForm form) throws AppException;

	public List listChart(PriceIndexListForm form, HttpServletRequest request,
			HttpServletResponse response) throws AppException;

	public List<String> listChartByReference(long priceReferenceId,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException;

	public ArrayList<String[]> listByPhaseTime(
			PriceIndexListForm priceIndexListForm, HttpServletRequest request)
			throws AppException;

	public void delete(long id) throws AppException;

	public void deletePriceIndex(Long priceIndexId) throws AppException;

	public long save(PriceIndex priceIndex) throws AppException;

	public long update(PriceIndex priceIndex) throws AppException;

	public PriceIndex getPriceIndexById(long priceIndexId) throws AppException;

	public List<PriceIndex> getPriceIndexByIds(String indexIds)
			throws AppException;

	public List<PriceIndex> getPriceIndexByReferenceId(long referenceId)
			throws AppException;

	public List<PriceIndex> getPriceIndexList() throws AppException;

	public List<PriceIndex> getValidPriceIndexList() throws AppException;

}
