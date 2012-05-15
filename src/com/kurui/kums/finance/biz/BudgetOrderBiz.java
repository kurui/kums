package com.kurui.kums.finance.biz;

import java.util.List;

import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.finance.BudgetOrder;
import com.kurui.kums.finance.BudgetOrderListForm;

public interface BudgetOrderBiz {
	public List list(BudgetOrderListForm form) throws AppException;

	public void delete(long id) throws AppException;

	public void deleteBudgetOrder(Long id) throws AppException;

	public long save(BudgetOrder budgetOrder) throws AppException;

	public long update(BudgetOrder budgetOrder) throws AppException;

	public BudgetOrder getBudgetOrderById(long Id) throws AppException;

	public List<BudgetOrder> getBudgetOrderByBudgetId(long budgetId)
			throws AppException;

	public List<BudgetOrder> getBudgetOrderList() throws AppException;

	public List<BudgetOrder> getValidBudgetOrderList() throws AppException;

}
