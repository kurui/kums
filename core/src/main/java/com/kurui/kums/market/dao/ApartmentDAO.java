package com.kurui.kums.market.dao;

import java.util.List;

import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.market.Apartment;
import com.kurui.kums.market.ApartmentListForm;

public interface ApartmentDAO {

	public List list(ApartmentListForm apartmentForm) throws AppException;

	public void delete(long id) throws AppException;

	public long save(Apartment Apartment) throws AppException;

	public long update(Apartment Apartment) throws AppException;

	public Apartment getApartmentById(long apartmentId) throws AppException;

	public List<Apartment> getApartmentList() throws AppException;

	public Apartment getApartment(long dishId, String dateString)
			throws AppException;

	public List<Apartment> getValidApartmentList() throws AppException;

	public List<Apartment> getApartmentByDishId(long dishId)
			throws AppException;
}
