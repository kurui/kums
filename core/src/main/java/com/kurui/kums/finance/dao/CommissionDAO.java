package com.kurui.kums.finance.dao;

import java.util.List;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.finance.Commission;
import com.kurui.kums.finance.CommissionListForm;

public interface CommissionDAO {

	public List list(CommissionListForm tlf) throws AppException;

	public void delete(long id) throws AppException;

	public long save(Commission orderDetail) throws AppException;

	public long update(Commission orderDetail) throws AppException;

	public Commission getCommissionById(long id) throws AppException;

	public List<Commission> getCommissionByOrderId(long orderId)
			throws AppException;

	public List<Commission> getCommissionListByOrderIds(String orderids)
			throws AppException;

	public List<Commission> getCommissionList() throws AppException;

	public List<Commission> getValidCommissionList() throws AppException;

}
