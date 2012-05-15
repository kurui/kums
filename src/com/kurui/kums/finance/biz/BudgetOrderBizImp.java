package com.kurui.kums.finance.biz;

import java.util.List;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.finance.BudgetOrder;
import com.kurui.kums.finance.BudgetOrderListForm;
import com.kurui.kums.finance.dao.BudgetOrderDAO;
import com.kurui.kums.finance.dao.BudgetDAO;

public class BudgetOrderBizImp implements BudgetOrderBiz {
	private BudgetOrderDAO budgetOrderDAO;
	private BudgetDAO budgetDAO;

	public List list(BudgetOrderListForm budgetOrderForm) throws AppException {
		return budgetOrderDAO.list(budgetOrderForm);
	}

	public void delete(long id) throws AppException {
		try {
			budgetOrderDAO.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteBudgetOrder(Long budgetOrderId) throws AppException {
		BudgetOrder budgetOrder = getBudgetOrderById(budgetOrderId);
		budgetOrder.setStatus(BudgetOrder.STATES_0);// 将状态变为无效
		update(budgetOrder);
	}

	public void deleteBudgetOrderByBudgetId(Long budgetId) throws AppException {
		List<BudgetOrder> budgetOrderList = getBudgetOrderByBudgetId(budgetId);
		for (int i = 0; i < budgetOrderList.size(); i++) {
			BudgetOrder budgetOrder = budgetOrderList.get(i);
			budgetOrder.setStatus(BudgetOrder.STATES_0);// 将状态变为无效
			update(budgetOrder);
		}
	}

	public long save(BudgetOrder budgetOrder) throws AppException {
		return budgetOrderDAO.save(budgetOrder);
	}

	public long update(BudgetOrder budgetOrder) throws AppException {
		return budgetOrderDAO.update(budgetOrder);
	}

	public BudgetOrder getBudgetOrderById(long id) throws AppException {
		return budgetOrderDAO.getBudgetOrderById(id);
	}

	public List<BudgetOrder> getBudgetOrderByBudgetId(long id)
			throws AppException {
		return budgetOrderDAO.getBudgetOrderByBudgetId(id);
	}

	public List<BudgetOrder> getBudgetOrderList() throws AppException {
		return budgetOrderDAO.getBudgetOrderList();
	}

	public List<BudgetOrder> getValidBudgetOrderList() throws AppException {
		return budgetOrderDAO.getValidBudgetOrderList();
	}

	public void setBudgetOrderDAO(BudgetOrderDAO budgetOrderDAO) {
		this.budgetOrderDAO = budgetOrderDAO;
	}

	public void setBudgetDAO(BudgetDAO budgetDAO) {
		this.budgetDAO = budgetDAO;
	}

}
