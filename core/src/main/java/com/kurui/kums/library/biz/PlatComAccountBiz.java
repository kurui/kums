package com.kurui.kums.library.biz;

import java.util.List;

import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.library.PlatComAccount;
import com.kurui.kums.library.PlatComAccountListForm;

public interface PlatComAccountBiz {

	public List list(PlatComAccountListForm platComAccountForm)
			throws AppException;

	public long delete(long id) throws AppException;

	public void deletePlatComAccount(Long id) throws AppException;

	public long save(PlatComAccount platComAccount) throws AppException;

	public long update(PlatComAccount platComAccount) throws AppException;

	public PlatComAccount getPlatComAccountById(long platComAccountId)
			throws AppException;

	public List<PlatComAccount> getPlatComAccountList() throws AppException;

}
