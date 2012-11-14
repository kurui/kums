package com.kurui.kums.finance.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;

import com.kurui.kums.base.struts.BaseAction;
import com.kurui.kums.base.ui.inform.Inform;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.finance.OrderDetail;
import com.kurui.kums.finance.biz.OrderDetailBiz;

public class OrderDetailAction extends BaseAction {
	private OrderDetailBiz orderDetailBiz;

	public ActionForward insert(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		OrderDetail pform = (OrderDetail) form;
		Inform inf = new Inform();
		try {
			OrderDetail orderDetail = new OrderDetail();
			
			orderDetail.setMemo(pform.getMemo());
			orderDetail.setStatus(pform.getStatus());

			long num = orderDetailBiz.save(orderDetail);
			if (num > 0) {
				return new ActionRedirect(
						"/finance/orderDetailList.do?thisAction=list");
			} else {
				inf.setMessage("您添加订单明细失败！");
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
		OrderDetail pform = (OrderDetail) form;
		Inform inf = new Inform();
//		UserRightInfo uri = (UserRightInfo) request.getSession().getAttribute(
//				"URI");
		try {
			if (pform.getId() > 0) {
				OrderDetail orderDetail = orderDetailBiz
						.getOrderDetailById(pform.getId());

				orderDetail.setMemo(pform.getMemo());
				orderDetail.setStatus(pform.getStatus());

				long flag = orderDetailBiz.update(orderDetail);

				if (flag > 0) {
					return new ActionRedirect(
							"/finance/orderDetailList.do?thisAction=list");
				} else {
					inf.setMessage("您修改订单明细失败！");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			inf.setMessage("异常:" + e.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}

	public void setOrderDetailBiz(OrderDetailBiz orderDetailBiz) {
		this.orderDetailBiz = orderDetailBiz;
	}

}