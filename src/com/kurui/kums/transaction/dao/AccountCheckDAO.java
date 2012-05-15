package com.kurui.kums.transaction.dao;

import java.util.List;

import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.right.UserRightInfo;
import com.kurui.kums.transaction.AccountCheck;
import com.kurui.kums.transaction.AccountCheckListForm;

public interface AccountCheckDAO {

	public List list(AccountCheckListForm accountCheckListForm,UserRightInfo uri) throws AppException;

	public void delete(long id) throws AppException;

	public long save(AccountCheck accountCheck) throws AppException;

	public long update(AccountCheck accountCheck) throws AppException;

	public AccountCheck getAccountCheckById(long accountCheckId) throws AppException;

	public List<AccountCheck> getAccountCheckList() throws AppException;

	public List<AccountCheck> getValidAccountCheckList() throws AppException;
}
