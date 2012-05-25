package com.kurui.kums.transaction.biz;

import java.util.List;

import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.transaction.PlatComAccount;
import com.kurui.kums.transaction.PlatComAccountListForm;
import com.kurui.kums.transaction.dao.PlatComAccountDAO;

public class PlatComAccountBizImp implements PlatComAccountBiz {
	private PlatComAccountDAO platComAccountDAO;	

	public List list(PlatComAccountListForm platComAccountForm)
			throws AppException {
		return platComAccountDAO.list(platComAccountForm);
	}

	public long delete(long id) throws AppException {
		try {
			platComAccountDAO.delete(id);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public void deletePlatComAccount(Long id) throws AppException {
		PlatComAccount platComAccount = getPlatComAccountById(id);
		platComAccount.setStatus(PlatComAccount.STATES_0);// 将状态变为无效
		update(platComAccount);
	}

	public long save(PlatComAccount platComAccount) throws AppException {
		return platComAccountDAO.save(platComAccount);
	}

	public long update(PlatComAccount platComAccount) throws AppException {
		return platComAccountDAO.update(platComAccount);
	}

	public PlatComAccount getPlatComAccountById(long platComAccountId)
			throws AppException {
		return platComAccountDAO.getPlatComAccountById(platComAccountId);
	}

	public List<PlatComAccount> getPlatComAccountList() throws AppException {
		return platComAccountDAO.getPlatComAccountList();
	}
	
	public void setPlatComAccountDAO(PlatComAccountDAO platComAccountDAO) {
		this.platComAccountDAO = platComAccountDAO;
	}

}
