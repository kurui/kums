package com.kurui.kums.finance.dao;

import java.util.List;

import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.finance.Budget;
import com.kurui.kums.finance.BudgetListForm;

public interface BudgetDAO {

	public Budget getBudgetById(long airtickeOrderId) throws AppException;

	public List list(BudgetListForm rlf) throws AppException;

	public void delete(long id) throws AppException;

	public long save(Budget budget) throws AppException;

	public long update(Budget budget) throws AppException;

}
