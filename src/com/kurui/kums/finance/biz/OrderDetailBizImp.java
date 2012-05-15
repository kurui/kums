package com.kurui.kums.finance.biz;

import java.util.List;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.finance.OrderDetail;
import com.kurui.kums.finance.OrderDetailListForm;
import com.kurui.kums.finance.dao.OrderDetailDAO;

public class OrderDetailBizImp implements OrderDetailBiz {
	private OrderDetailDAO orderDetailDAO;

	public List list(OrderDetailListForm orderDetailForm) throws AppException {
		return orderDetailDAO.list(orderDetailForm);
	}

	public void delete(long id) throws AppException {
		try {
			orderDetailDAO.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteOrderDetail(Long orderDetailId) throws AppException {
		OrderDetail orderDetail = getOrderDetailById(orderDetailId);
		orderDetail.setStatus(OrderDetail.STATES_0);// 将状态变为无效
		update(orderDetail);
	}

	public long save(OrderDetail orderDetail) throws AppException {
		return orderDetailDAO.save(orderDetail);
	}

	public long update(OrderDetail orderDetail) throws AppException {
		return orderDetailDAO.update(orderDetail);
	}

	public OrderDetail getOrderDetailById(long id) throws AppException {
		return orderDetailDAO.getOrderDetailById(id);
	}

	public List<OrderDetail> getOrderDetailByOrderId(long id)
			throws AppException {
		return orderDetailDAO.getOrderDetailByOrderId(id);
	}

	public List<OrderDetail> getOrderDetailList() throws AppException {
		return orderDetailDAO.getOrderDetailList();
	}

	public List<OrderDetail> getValidOrderDetailList() throws AppException {
		return orderDetailDAO.getValidOrderDetailList();
	}

	public void setOrderDetailDAO(OrderDetailDAO orderDetailDAO) {
		this.orderDetailDAO = orderDetailDAO;
	}

}
