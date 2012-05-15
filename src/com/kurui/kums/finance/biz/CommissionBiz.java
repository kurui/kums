package com.kurui.kums.finance.biz;

import java.util.List;

import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.finance.Commission;
import com.kurui.kums.finance.CommissionListForm;

public interface CommissionBiz {

	public void updateCommissionData(CommissionListForm form)
			throws AppException;

	public List list(CommissionListForm form) throws AppException;

	public void delete(long id) throws AppException;

	public void deleteCommission(Long id) throws AppException;

	public long save(Commission commission) throws AppException;

	public long update(Commission commission) throws AppException;

	public Commission getCommissionById(long Id) throws AppException;

	public List<Commission> getCommissionByOrderId(long orderId)
			throws AppException;

	public List<Commission> getCommissionList() throws AppException;

	public List<Commission> getValidCommissionList() throws AppException;

}
