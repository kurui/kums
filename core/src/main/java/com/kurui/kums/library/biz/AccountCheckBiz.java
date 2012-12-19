package com.kurui.kums.library.biz;

import java.util.ArrayList;
import java.util.List;

import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.right.UserRightInfo;
import com.kurui.kums.library.AccountCheck;
import com.kurui.kums.library.AccountCheckListForm;

public interface AccountCheckBiz {

	public List list(AccountCheckListForm accountListForm, UserRightInfo uri)
			throws AppException;

	public long delete(long id) throws AppException;

	public void deleteAccountCheck(Long accountCheckId) throws AppException;

	public long save(AccountCheck account) throws AppException;

	public long update(AccountCheck account) throws AppException;

	public AccountCheck getAccountCheckById(long accountId) throws AppException;

	public List<AccountCheck> getAccountCheckList() throws AppException;

	public ArrayList<ArrayList<Object>> getAccountCheckList(
			AccountCheckListForm alf, UserRightInfo uri) throws AppException;

}
