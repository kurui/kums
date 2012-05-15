package com.kurui.kums.market.biz;

import java.util.List;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.market.Apartment;
import com.kurui.kums.market.ApartmentListForm;

public interface ApartmentBiz {

	public List list(ApartmentListForm form) throws AppException;

	public void delete(long id) throws AppException;

	public void deleteApartment(Long apartmentId) throws AppException;

	public long save(Apartment apartment) throws AppException;

	public long update(Apartment apartment) throws AppException;

	public Apartment getApartmentById(long apartmentId) throws AppException;

	public List<Apartment> getApartmentList() throws AppException;

	public List<Apartment> getValidApartmentList() throws AppException;

}
