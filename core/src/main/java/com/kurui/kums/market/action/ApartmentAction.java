package com.kurui.kums.market.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;

import com.kurui.kums.base.struts.BaseAction;
import com.kurui.kums.base.ui.inform.Inform;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.base.Constant;
import com.kurui.kums.base.util.time.DateUtil;
import com.kurui.kums.base.util.KumsNoUtil;
import com.kurui.kums.market.Apartment;
import com.kurui.kums.market.EstateDish;
import com.kurui.kums.market.biz.ApartmentBiz;
import com.kurui.kums.market.biz.EstateDishBiz;
import com.kurui.kums.right.UserRightInfo;

public class ApartmentAction extends BaseAction {
	private ApartmentBiz apartmentBiz;
	private EstateDishBiz estateDishBiz;
	private KumsNoUtil noUtil;

	public ActionForward insert(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		Apartment pform = (Apartment) form;
		Inform inf = new Inform();
		UserRightInfo uri = (UserRightInfo) request.getSession().getAttribute(
				"URI");
		try {
			Apartment apartment = new Apartment();
			apartment.setNo(noUtil.getApartmentNo());

			apartment.setName(pform.getName());

			long estateDishId = Constant.toLong(pform.getEstateDishId());
			if (estateDishId > 0) {
				EstateDish estateDish = estateDishBiz
						.getEstateDishById(estateDishId);
				apartment.setEstateDish(estateDish);
			}

			apartment.setTransactionType(pform.getTransactionType());
			apartment.setBusinessType(pform.getBusinessType());
			apartment.setHouseType(pform.getHouseType());
			apartment.setArea(pform.getArea());
			apartment.setOwner(pform.getOwner());
			apartment.setLinkman(pform.getLinkman());
			apartment.setConnection(pform.getConnection());
			apartment.setSnatchTime(DateUtil.getTimestamp(
					pform.getSnatchDate(), "yyyy-MM-dd"));
			apartment.setQuotePrice(pform.getQuotePrice());
			apartment.setAverageAreaPrice(pform.getAverageAreaPrice());

			// apartment.setEntryOperator(uri.getUser().getUserNo());
			apartment.setMemo(pform.getMemo());
			apartment.setType(Apartment.TYPE_1);
			apartment.setStatus(pform.getStatus());

			long num = apartmentBiz.save(apartment);

			if (num > 0) {
				return redirect(apartment);
			} else {
				inf.setMessage("您添加物业失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			inf.setMessage("异常:" + e.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}

	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		Apartment pform = (Apartment) form;
		Inform inf = new Inform();
		UserRightInfo uri = (UserRightInfo) request.getSession().getAttribute(
				"URI");
		try {
			if (pform.getId() > 0) {
				Apartment apartment = apartmentBiz.getApartmentById(pform
						.getId());

				String no = apartment.getNo();
				if (no==null||"".equals(no)) {
					apartment.setNo(noUtil.getApartmentNo());
				}

				apartment.setName(pform.getName());

				long estateDishId = pform.getEstateDishId();
				if (estateDishId > 0) {
					EstateDish estateDish = estateDishBiz
							.getEstateDishById(estateDishId);
					apartment.setEstateDish(estateDish);
				}

				apartment.setTransactionType(pform.getTransactionType());
				apartment.setBusinessType(pform.getBusinessType());
				apartment.setHouseType(pform.getHouseType());
				apartment.setArea(pform.getArea());
				apartment.setOwner(pform.getOwner());
				apartment.setLinkman(pform.getLinkman());
				apartment.setConnection(pform.getConnection());
				apartment.setSnatchTime(DateUtil.getTimestamp(pform
						.getSnatchDate(), "yyyy-MM-dd"));
				apartment.setQuotePrice(pform.getQuotePrice());
				apartment.setAverageAreaPrice(pform.getAverageAreaPrice());

				// apartment.setEntryOperator(uri.getUser().getUserNo());
				apartment.setMemo(pform.getMemo());
				apartment.setType(Apartment.TYPE_1);
				apartment.setStatus(pform.getStatus());

				long flag = apartmentBiz.update(apartment);

				if (flag > 0) {
					return redirect(apartment);
				} else {
					inf.setMessage("您修改物业失败！");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			inf.setMessage("异常:" + e.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}

	public ActionRedirect redirect(Apartment apartment) {
		ActionRedirect redirect = new ActionRedirect("/market/apartmentList.do");
		redirect.addParameter("thisAction", "view");
		redirect.addParameter("id", apartment.getId());
		return redirect;
	}

	public void setApartmentBiz(ApartmentBiz apartmentBiz) {
		this.apartmentBiz = apartmentBiz;
	}

	public void setEstateDishBiz(EstateDishBiz estateDishBiz) {
		this.estateDishBiz = estateDishBiz;
	}

	public void setNoUtil(KumsNoUtil noUtil) {
		this.noUtil = noUtil;
	}
	
	

}