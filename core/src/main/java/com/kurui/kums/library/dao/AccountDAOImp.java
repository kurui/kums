package com.kurui.kums.library.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;

import com.kurui.kums.base.database.hibernate.BaseDAOSupport;
import com.kurui.kums.base.database.hibernate.Hql;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.base.Constant;
import com.kurui.kums.library.Account;
import com.kurui.kums.library.AccountListForm;

public class AccountDAOImp extends BaseDAOSupport implements AccountDAO {

	public List list(AccountListForm accountListForm) throws AppException {
		Hql hql = new Hql();
		hql.add("from Account a where 1=1");
		if (accountListForm.getPaymentToolId() > 0) {
			hql.add(" and a.paymentTool.id="
					+ accountListForm.getPaymentToolId());
		}
		
		if (accountListForm.getBusinessType() > 0) {
			hql.add(" and a.businessType = " + accountListForm.getBusinessType() + "");
		}
		
		if (accountListForm.getPersonalType() > 0) {
			hql.add(" and a.personalType = " + accountListForm.getPersonalType() + "");
		}

		if (accountListForm.getTranType() > 0) {
			hql.add(" and a.tranType = " + accountListForm.getTranType() + "");
		}

		if (accountListForm.getType() > 0) {
			hql.add(" and a.type = " + accountListForm.getType() + "");
		}
		if (accountListForm.getName() != null
				&& (!(accountListForm.getName().equals("")))) {
			hql.add(" and a.name like '%" + accountListForm.getName().trim()
					+ "%'");
		}

		if (!(Constant.toString(accountListForm.getAccountNo()).equals(""))) {
			hql.add(" and a.accountNo like '%"
					+ accountListForm.getAccountNo().trim() + "%'");
		}
		if(accountListForm.getStatus()>0){
			hql.add("and a.status =" + accountListForm.getStatus());
		}
		
		
		hql.add(" order by a.paymentTool.id");
		return this.list(hql, accountListForm);
	}

	public void delete(long id) throws AppException {
		if (id > 0) {
			Account account = (Account) this.getHibernateTemplate().get(
					Account.class, new Long(id));
			this.getHibernateTemplate().delete(account);
		}
	}

	public long save(Account account) throws AppException {
		this.getHibernateTemplate().save(account);
		return account.getId();
	}

	public long update(Account account) throws AppException {
		if (account.getId() > 0) {
			this.getHibernateTemplate().update(account);
			return account.getId();
		} else
			throw new IllegalArgumentException("id isn't a valid argument.");
	}

	public Account getAccountById(long accountId) throws AppException {
		Hql hql = new Hql();
		hql.add("from Account p where p.id=" + accountId);
		Query query = this.getQuery(hql);
		Account account = null;
		if (query != null) {
			List list = query.list();
			if (list != null && list.size() > 0)

				account = (Account) list.get(0);
		}
		return account;
	}

	public List<Account> getAccountList() throws AppException {
		List<Account> list = new ArrayList<Account>();
		Hql hql = new Hql();
		hql.add("from Account");
		Query query = this.getQuery(hql);
		if (query != null) {
			list = query.list();
			if (list != null && list.size() > 0) {
				return list;
			}
		}
		return list;
	}

	public List<Account> getValidAccountList() throws AppException {
		List<Account> list = new ArrayList<Account>();
		Hql hql = new Hql();
		hql.add("from Account where 1=1 and status=" + Account.STATES_1);
		hql.add(" order by name ");
		Query query = this.getQuery(hql);
		if (query != null && query.list() != null && query.list().size() > 0) {
			list = query.list();
			list = setAccountPaymentTool(list);
		}
		return list;
	}

	public List<Account> setAccountPaymentTool(List<Account> accountList) {
		for (int i = 0; i < accountList.size(); i++) {
			Account account = accountList.get(i);
			if (account.getPaymentTool() != null) {
				String paymentToolName = account.getPaymentTool().getName();
				// System.out.println(paymentToolName);
			}
		}
		return accountList;
	}

	public List<Account> getValidAccountListByTranType(String tranType)
			throws AppException {
		List<Account> list = new ArrayList<Account>();
		Hql hql = new Hql();
		hql.add(" from Account a where 1=1 and a.status=" + Account.STATES_1);
		hql.add(" and a.tranType in(" + tranType + ") ");
		hql.add(" order by a.name ");
		Query query = this.getQuery(hql);
		if (query != null) {
			list = query.list();
			if (list != null && list.size() > 0) {
				return list;
			}
		}
		return list;
	}

	public List<Account> getAccountListByPaymentToolId(long paymentToolId) {
		List<Account> list = new ArrayList<Account>();
		Hql hql = new Hql();
		hql.add("from Account a where a.account.id=" + paymentToolId);
		hql.add(" order by a.name ");
		Query query = this.getQuery(hql);
		if (query != null) {
			list = query.list();
			if (list != null && list.size() > 0) {
				return list;
			}
		}
		return list;
	}
}