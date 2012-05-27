package com.kurui.kums.finance.dao;

import java.util.List;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.finance.OrderDetail;
import com.kurui.kums.finance.OrderDetailListForm;

public interface OrderDetailDAO {

	public List list(OrderDetailListForm tlf) throws AppException;

	public void delete(long id) throws AppException;

	public long save(OrderDetail orderDetail) throws AppException;

	public long update(OrderDetail orderDetail) throws AppException;

	public OrderDetail getOrderDetailById(long id) throws AppException;

	public List<OrderDetail> getOrderDetailByOrderId(long orderId)
			throws AppException;

	public List<OrderDetail> getOrderDetailListByOrderIds(String orderids)
			throws AppException;

	public List<OrderDetail> getOrderDetailList() throws AppException;

	public List<OrderDetail> getValidOrderDetailList() throws AppException;

}
