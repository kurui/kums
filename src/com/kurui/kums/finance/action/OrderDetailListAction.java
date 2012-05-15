package com.kurui.kums.finance.action;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.kurui.kums.base.BaseAction;
import com.kurui.kums.base.Inform;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.finance.OrderDetail;
import com.kurui.kums.finance.OrderDetailListForm;
import com.kurui.kums.finance.biz.OrderDetailBiz;

public class OrderDetailListAction extends BaseAction {
	private OrderDetailBiz orderDetailBiz;

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		OrderDetailListForm orderDetailListForm = (OrderDetailListForm) form;
		if (orderDetailListForm == null) {
			orderDetailListForm = new OrderDetailListForm();
		}
		try {
			orderDetailListForm.setList(orderDetailBiz
					.list(orderDetailListForm));
		
			orderDetailListForm.addSumField(1, "discountPrice");
			orderDetailListForm.addSumField(2, "productCount");
			orderDetailListForm.addSumField(3, "detailAmount");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("orderDetailListForm", orderDetailListForm);
		return mapping.findForward("listOrderDetail");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		OrderDetailListForm orderDetailListForm = (OrderDetailListForm) form;
		try {
			Long orderDetailId = orderDetailListForm.getId();
			OrderDetail orderDetail = orderDetailBiz
					.getOrderDetailById(orderDetailId);

			request.setAttribute("orderDetail", orderDetail);
		} catch (Exception e) {
			e.printStackTrace();
		}
		forwardPage = "viewOrderDetail";
		return mapping.findForward(forwardPage);

	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setThisAction("insert");
		orderDetail.setStatus(OrderDetail.STATES_1);

		request.setAttribute("orderDetail", orderDetail);

		List<OrderDetail> orderDetailList = orderDetailBiz
				.getValidOrderDetailList();

		request.setAttribute("orderDetailList", orderDetailList);

		String forwardPage = "editOrderDetail";

		return mapping.findForward(forwardPage);
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		OrderDetailListForm orderDetailListForm = (OrderDetailListForm) form;
		long orderDetailId = orderDetailListForm.getId();
		if (orderDetailId < 1) {
			orderDetailId = orderDetailListForm.getSelectedItems()[0];
		}
		if (orderDetailId > 0) {
			OrderDetail orderDetail = orderDetailBiz
					.getOrderDetailById(orderDetailId);
			orderDetail.setThisAction("update");
			request.setAttribute("orderDetail", orderDetail);
		} else {
			request.setAttribute("orderDetail", new OrderDetail());
		}

		List<OrderDetail> orderDetailList = orderDetailBiz
				.getValidOrderDetailList();
		request.setAttribute("orderDetailList", orderDetailList);

		return mapping.findForward("editOrderDetail");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		OrderDetailListForm orderDetailListForm = (OrderDetailListForm) form;
		// String forwardPage = "";
		long id = 0;
		Inform inf = new Inform();
		try {
			for (int i = 0; i < orderDetailListForm.getSelectedItems().length; i++) {
				id = orderDetailListForm.getSelectedItems()[i];
				if (id > 0) {
					orderDetailBiz.deleteOrderDetail(id);
				}
			}
			return list(mapping, form, request, response);
		} catch (Exception ex) {
			inf.setMessage("删除失败" + ex.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}

	public void setOrderDetailBiz(OrderDetailBiz orderDetailBiz) {
		this.orderDetailBiz = orderDetailBiz;
	}

}