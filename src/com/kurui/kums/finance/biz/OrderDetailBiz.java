package com.kurui.kums.finance.biz;

import java.util.List;

import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.finance.OrderDetail;
import com.kurui.kums.finance.OrderDetailListForm;

public interface OrderDetailBiz {

	public List list(OrderDetailListForm form) throws AppException;

	public void delete(long id) throws AppException;

	public void deleteOrderDetail(Long id) throws AppException;

	public long save(OrderDetail orderDetail) throws AppException;

	public long update(OrderDetail orderDetail) throws AppException;

	public OrderDetail getOrderDetailById(long Id) throws AppException;

	public List<OrderDetail> getOrderDetailByOrderId(long orderId)
			throws AppException;

	public List<OrderDetail> getOrderDetailList() throws AppException;

	public List<OrderDetail> getValidOrderDetailList() throws AppException;

}
