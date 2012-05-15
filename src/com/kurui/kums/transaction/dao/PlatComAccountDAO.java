package com.kurui.kums.transaction.dao;

import java.util.List;

import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.transaction.PlatComAccount;
import com.kurui.kums.transaction.PlatComAccountListForm;

public interface PlatComAccountDAO {

	public List list(PlatComAccountListForm platComAccountForm)
			throws AppException;

	public void delete(long id) throws AppException;

	public long save(PlatComAccount platComAccount) throws AppException;

	public long update(PlatComAccount platComAccount) throws AppException;

	public PlatComAccount getPlatComAccountById(long platComAccountId)
			throws AppException;

	public List<PlatComAccount> getPlatComAccountList() throws AppException;

	public List<PlatComAccount> getValidPlatComAccountList()
			throws AppException;

	public List<PlatComAccount> getPlatComAccountByPlatformId(long platformId)
			throws AppException;
}
