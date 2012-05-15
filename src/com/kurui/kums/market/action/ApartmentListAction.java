package com.kurui.kums.market.action;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.kurui.kums.base.BaseAction;
import com.kurui.kums.base.Inform;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.base.Constant;
import com.kurui.kums.market.Apartment;
import com.kurui.kums.market.ApartmentListForm;
import com.kurui.kums.market.EstateDish;
import com.kurui.kums.market.biz.ApartmentBiz;
import com.kurui.kums.market.biz.EstateDishBiz;

public class ApartmentListAction extends BaseAction {
	private ApartmentBiz apartmentBiz;
	private EstateDishBiz estateDishBiz;

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		ApartmentListForm apartmentListForm = (ApartmentListForm) form;
		if (apartmentListForm == null) {
			apartmentListForm = new ApartmentListForm();
		}
		try {
			apartmentListForm.setList(apartmentBiz.list(apartmentListForm));
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("apartmentListForm", apartmentListForm);
		
		loadDataStore(request);
		
		return mapping.findForward("listApartment");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		ApartmentListForm apartmentListForm = (ApartmentListForm) form;
		try {
			Long apartmentId = apartmentListForm.getId();
			Apartment apartment = apartmentBiz.getApartmentById(apartmentId);
			request.setAttribute("apartment", apartment);
		} catch (Exception e) {
			e.printStackTrace();
		}
		forwardPage = "viewApartment";
		return mapping.findForward(forwardPage);

	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		ApartmentListForm apartmentListForm = (ApartmentListForm) form;

		Apartment apartment = new Apartment();
		apartment.setThisAction("insert");
		long estateDishId = Constant
				.toLong(apartmentListForm.getEstateDishId());
		if (estateDishId > 0) {
			EstateDish estateDish = estateDishBiz
					.getEstateDishById(estateDishId);
			apartment.setEstateDish(estateDish);
		}

		apartment.setSnatchTime(new Timestamp(System.currentTimeMillis()));
		apartment.setType(Apartment.TYPE_1);
		apartment.setStatus(Apartment.STATES_1);
		
		request.setAttribute("apartment", apartment);
		
		loadDataStore(request);
		
		String forwardPage = "editApartment";

		return mapping.findForward(forwardPage);
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		ApartmentListForm apartmentListForm = (ApartmentListForm) form;

		long apartmentId = apartmentListForm.getId();
		if (apartmentId < 1) {
			apartmentId = apartmentListForm.getSelectedItems()[0];
		}

		if (apartmentId > 0) {
			Apartment apartment = apartmentBiz.getApartmentById(apartmentId);
			apartment.setThisAction("update");
			request.setAttribute("apartment", apartment);
			apartment.setType(Apartment.TYPE_1);
			apartment.setStatus(Apartment.STATES_1);
		} else {
			request.setAttribute("apartment", new Apartment());
		}
		
		
		
		loadDataStore(request);

		return mapping.findForward("editApartment");
	}

	public HttpServletRequest loadDataStore(HttpServletRequest request)
			throws AppException {
		List<EstateDish> estateDishList = estateDishBiz
				.getValidEstateDishList();// 楼盘
		request.setAttribute("estateDishList", estateDishList);
		return request;
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		ApartmentListForm apartmentListForm = (ApartmentListForm) form;
		// String forwardPage = "";
		long id = 0;
		Inform inf = new Inform();
		try {
			for (int i = 0; i < apartmentListForm.getSelectedItems().length; i++) {
				id = apartmentListForm.getSelectedItems()[i];
				if (id > 0) {
					apartmentBiz.deleteApartment(id);
				}
			}
			return list(mapping, form, request, response);
		} catch (Exception ex) {
			inf.setMessage("删除失败" + ex.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}

	public void setApartmentBiz(ApartmentBiz apartmentBiz) {
		this.apartmentBiz = apartmentBiz;
	}

	public void setEstateDishBiz(EstateDishBiz estateDishBiz) {
		this.estateDishBiz = estateDishBiz;
	}

}