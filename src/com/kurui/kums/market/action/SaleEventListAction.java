package com.kurui.kums.market.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.kurui.kums.base.BaseAction;
import com.kurui.kums.base.Inform;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.market.SaleEvent;
import com.kurui.kums.market.SaleEventListForm;
import com.kurui.kums.market.biz.SaleEventBiz;

public class SaleEventListAction extends BaseAction {
	private SaleEventBiz saleEventBiz;

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		SaleEventListForm saleEventListForm = (SaleEventListForm) form;
		if (saleEventListForm == null) {
			saleEventListForm = new SaleEventListForm();
		}
		try {
			saleEventListForm.setList(saleEventBiz.list(saleEventListForm));
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("saleEvent", saleEventListForm);
		return mapping.findForward("listSaleEvent");
	}

	/***************************************************************************
	 * 选择产品
	 **************************************************************************/
	public ActionForward selectFinanceSaleEvent(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws AppException {
		String forwardPage = "";
		SaleEventListForm saleEventListForm = (SaleEventListForm) form;
		saleEventListForm.setList(saleEventBiz.list(saleEventListForm));
		saleEventListForm.setThisAction("selectFinanceSaleEvent");

		request.setAttribute("saleEventListForm", saleEventListForm);
		// System.out.println("rowId:"+saleEventListForm.getRowId());
		forwardPage = "listSaleEventSelect";
		return (mapping.findForward(forwardPage));
	}

	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		try {
			String saleEventId = request.getParameter("id");
			SaleEvent saleEvent = saleEventBiz.getSaleEventById(Long
					.parseLong(saleEventId));
			request.setAttribute("saleEvent", saleEvent);
		} catch (Exception e) {
			e.printStackTrace();
		}
		forwardPage = "viewSaleEvent";
		return mapping.findForward(forwardPage);

	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		SaleEvent saleEvent = new SaleEvent();
		saleEvent.setType(SaleEvent.TYPE_1);
		saleEvent.setThisAction("insert");
		request.setAttribute("saleEvent", saleEvent);
		String forwardPage = "editSaleEvent";
		return mapping.findForward(forwardPage);
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		SaleEventListForm saleEventListForm = (SaleEventListForm) form;

		long saleEventId = saleEventListForm.getSelectedItems()[0];
		
		long productId = saleEventListForm.getId();
		if (saleEventId < 1) {
			saleEventId = saleEventListForm.getSelectedItems()[0];
		}

		if (saleEventId > 0) {
			SaleEvent saleEvent = saleEventBiz.getSaleEventById(saleEventId);
			saleEvent.setThisAction("update");
			request.setAttribute("saleEvent", saleEvent);
		} else {
			request.setAttribute("saleEvent", new SaleEvent());
		}
		return mapping.findForward("editSaleEvent");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		SaleEventListForm saleEventListForm = (SaleEventListForm) form;
		// String forwardPage = "";
		long id = 0;
		Inform inf = new Inform();
		try {
			for (int i = 0; i < saleEventListForm.getSelectedItems().length; i++) {
				id = saleEventListForm.getSelectedItems()[i];
				saleEventBiz.deleteSaleEvent(id);
			}
			return list(mapping, form, request, response);
		} catch (Exception ex) {
			inf.setMessage("删除失败" + ex.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}

	public void setSaleEventBiz(SaleEventBiz saleEventBiz) {
		this.saleEventBiz = saleEventBiz;
	}
}