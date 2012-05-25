package com.kurui.kums.finance.biz;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.finance.Budget;
import com.kurui.kums.finance.BudgetListForm;

public interface BudgetBiz {

	public String view(long id, HttpServletRequest request) throws AppException;

	public void deleteBudget(Long budgetId) throws AppException;

	public Budget getBudgetById(long budgetId) throws AppException;

	public List list(BudgetListForm rlf) throws AppException;

	public void delete(long id) throws AppException;

	public long save(Budget budget) throws AppException;

	public long update(Budget financeOrder) throws AppException;
}
