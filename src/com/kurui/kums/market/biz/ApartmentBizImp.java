package com.kurui.kums.market.biz;

import java.util.List;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.market.Apartment;
import com.kurui.kums.market.ApartmentListForm;
import com.kurui.kums.market.dao.ApartmentDAO;

public class ApartmentBizImp implements ApartmentBiz {
	private ApartmentDAO apartmentDAO;

	public List list(ApartmentListForm apartmentListForm) throws AppException {
		return apartmentDAO.list(apartmentListForm);
	}

	public void delete(long id) throws AppException {
		try {
			apartmentDAO.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteApartment(Long apartmentId) throws AppException {
		Apartment apartment = getApartmentById(apartmentId);
		apartment.setStatus(Apartment.STATES_0);// 将状态变为无效
		update(apartment);
	}

	public long save(Apartment apartment) throws AppException {
		return apartmentDAO.save(apartment);
	}

	public long update(Apartment apartment) throws AppException {
		return apartmentDAO.update(apartment);
	}

	public Apartment getApartmentById(long apartmentId) throws AppException {
		return apartmentDAO.getApartmentById(apartmentId);
	}

	public List<Apartment> getApartmentList() throws AppException {
		return apartmentDAO.getApartmentList();
	}

	public List<Apartment> getValidApartmentList() throws AppException {
		return apartmentDAO.getValidApartmentList();
	}

	public void setApartmentDAO(ApartmentDAO apartmentDAO) {
		this.apartmentDAO = apartmentDAO;
	}
}
