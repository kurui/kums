package com.kurui.kums.transaction.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.kurui.kums.base.Constant;
import com.kurui.kums.transaction.Account;
import com.kurui.kums.transaction.Company;
import com.kurui.kums.transaction.PaymentTool;
import com.kurui.kums.transaction.PlatComAccount;
import com.kurui.kums.transaction.Platform;

/**
 * 
 * @author yanrui
 */
public class PlatComAccountStore {
	
	static Logger logger = Logger.getLogger(PlatComAccountStore.class.getName());
	
	public static List<PlatComAccount> platComAccountList = new ArrayList<PlatComAccount>();
	public static List<Platform> platformList = new ArrayList<Platform>();
	public static List<Company> companyList = new ArrayList<Company>();
	public static List<Account> accountList = new ArrayList<Account>();
	public static List<PaymentTool> paymentToolList = new ArrayList<PaymentTool>();

	// 采购平台
	public static List<Platform> getOutPlatform() {
		List<Platform> outPlatformList = new ArrayList<Platform>();
		for (int i = 0; i < platformList.size(); i++) {
			Platform platform = platformList.get(i);
			if (platform.getType() == Platform.TYPE_1
					|| platform.getType() == Platform.TYPE_3) {
				outPlatformList.add(platform);
			}
		}
		return outPlatformList;
	}

	// 销售平台
	public static List<Platform> getInPlatform() {
		List<Platform> inPlatformList = new ArrayList<Platform>();
		for (int i = 0; i < platformList.size(); i++) {
			Platform platform = platformList.get(i);
			if (platform.getType() == Platform.TYPE_2
					|| platform.getType() == Platform.TYPE_3) {
				inPlatformList.add(platform);
			}
		}
		return inPlatformList;
	}

	// 取同类账号
	public static List<Account> getAccountListByType(Long tranType) {

		List<Account> tempAccountList = new ArrayList<Account>();

		for (int i = 0; i < accountList.size(); i++) {
			Account ac = accountList.get(i);

			if (ac.getTranType() == tranType
					|| ac.getTranType() == Account.TRAN_TYPE_3) {
				tempAccountList.add(ac);
			}
		}
		return tempAccountList;
	}

	// 付款账号
	public static List<Account> getOutAccount() {
		List<Account> outAccountList = new ArrayList<Account>();
		for (int i = 0; i < accountList.size(); i++) {
			Account account = accountList.get(i);
			long type = account.getType();
			if (type == Account.TYPE_1) {// 系统账号
				long tranType = account.getTranType();

				if (tranType == Account.TRAN_TYPE_1
						|| tranType == Account.TRAN_TYPE_3) {
					outAccountList.add(account);
				}
			}

		}
		return outAccountList;
	}

	// 收款账号
	public static List<Account> getInAccount() {
		List<Account> inAccountList = new ArrayList<Account>();
		for (int i = 0; i < accountList.size(); i++) {
			Account account = accountList.get(i);
			long type = account.getType();
			if (type == Account.TYPE_1) {// 系统账号
				long tranType = account.getTranType();

				if (tranType == Account.TRAN_TYPE_2
						|| tranType == Account.TRAN_TYPE_3) {
					inAccountList.add(account);
				}
			}
		}
		return inAccountList;
	}

	// 集团旗下公司
	public static List<Company> getGroupCompnayList() {
		List<Company> teamList = new ArrayList<Company>();
		for (int i = 0; i < companyList.size(); i++) {
			Company company = (Company) companyList.get(i);
			if (company.getType() == Company.TYPE_1) {
				teamList.add(company);
			}
		}
		return teamList;
	}

	// 客户公司
	public static List<Company> getAgentCompnayList() {
		List<Company> teamList = new ArrayList<Company>();
		for (int i = 0; i < companyList.size(); i++) {
			Company company = (Company) companyList.get(i);
			if (company.getType() == Company.TYPE_2) {
				teamList.add(company);
			}
		}
		return teamList;
	}

	// 根据外键 交易平台表ID(dwr)
	public static List<PlatComAccount> getPlatComAccountListByPlatformId(
			long platformId) {
		List<PlatComAccount> tempList = new ArrayList<PlatComAccount>();
		Set set = new HashSet();
		for (int i = 0; i < platComAccountList.size(); i++) {
			PlatComAccount platComAccount = platComAccountList.get(i);

			if (platComAccount.getPlatform().getId() == platformId) {
				if (set.add(platComAccount.getCompany().getId())) {// 为什么要加这个，要查证。严睿2010-11-02
					tempList.add(platComAccount);
				}
			}
		}
		return tempList;
	}

	// 根据外键 交易平台表ID(dwr)(account type:)
	public static List<PlatComAccount> getPlatComAccountListByPlatformId2AndAccountType(
			long platformId, long accountType) {
		List<PlatComAccount> tempList = new ArrayList<PlatComAccount>();
		long tempAccountTranType = new Long(0);
		for (int i = 0; i < platComAccountList.size(); i++) {
			PlatComAccount platComAccount = platComAccountList.get(i);
			Platform platform = platComAccount.getPlatform();
			Account account = platComAccount.getAccount();

			if (platform.getId() == platformId) {
				tempAccountTranType = account.getTranType();
				if (tempAccountTranType == accountType
						|| tempAccountTranType == Account.TRAN_TYPE_3) {
					tempList.add(platComAccount);
				}
			}
		}
		return tempList;
	}

	// 公司表ID,交易平台ID,买卖类别(dwr)
	public static List<PlatComAccount> getPlatComAccountListByCompanyIdType(
			long companyId, long platformId, long type) {
		List<PlatComAccount> tempList = new ArrayList<PlatComAccount>();

		for (int i = 0; i < platComAccountList.size(); i++) {
			PlatComAccount platComAccount = platComAccountList.get(i);
			// System.out.println(platComAccount.getCompany().getId()+","+
			// platComAccount.getPlatform().getId());

			if (platComAccount.getCompany().getId() == companyId
					&& platComAccount.getPlatform().getId() == platformId) {

				long accountTranType = platComAccount.getType();

				if (type == 1) {// 卖出收款
					if (accountTranType == 1) {
						tempList.add(platComAccount);
					}
				} else if (type == 2) {// 买入付款
					if (accountTranType == 2) {
						tempList.add(platComAccount);
					}
				}
			}
		}
		return tempList;
	}

	// 公司表ID,交易平台ID,买卖类别(dwr)
	public List<PlatComAccount> _getPlatComAccountListByCompanyIdType(
			long companyId, long platformId, long type) {
		List<PlatComAccount> tempList = new ArrayList<PlatComAccount>();

		for (int i = 0; i < platComAccountList.size(); i++) {
			PlatComAccount platComAccount = platComAccountList.get(i);

			if (platComAccount.getCompany().getId() == companyId
					&& platComAccount.getPlatform().getId() == platformId) {

				Account account = platComAccount.getAccount();
				long accountTranType = account.getTranType();

				if (type == 1) {// 卖出收款
					if (accountTranType == 2 || accountTranType == 3) {
						tempList.add(platComAccount);
					}
				} else if (type == 2) {// 买入付款
					if (accountTranType == 1 || accountTranType == 3) {
						tempList.add(platComAccount);
					}
				}
			}
		}
		return tempList;
	}

	// 根据外键 公司表ID,交易平台ID(dwr)
	public List<PlatComAccount> getPlatComAccountListByCompanyId(
			long companyId, long platformId) {
		List<PlatComAccount> tempList = new ArrayList<PlatComAccount>();

		for (int i = 0; i < platComAccountList.size(); i++) {
			PlatComAccount platComAccount = platComAccountList.get(i);

			if (platComAccount.getCompany().getId() == companyId
					&& platComAccount.getPlatform().getId() == platformId) {
				tempList.add(platComAccount);
			}
		}
		return tempList;
	}

	// 根据外键 公司表ID,交易平台ID,支付账号ID(dwr)
	public List<PlatComAccount> getPlatComAccountListbyAllId(long platformId,
			long companyId, long accountId) {
		List<PlatComAccount> tempList = new ArrayList<PlatComAccount>();
		for (int i = 0; i < platComAccountList.size(); i++) {
			PlatComAccount platComAccount = platComAccountList.get(i);
			if (platComAccount.getCompany().getId() == companyId
					&& platComAccount.getPlatform().getId() == platformId
					&& platComAccount.getAccount().getId() == accountId) {
				tempList.add(platComAccount);
			}
		}
		return tempList;
	}

	// 根据平台账号表ID查询
	public static PlatComAccount getPlatComAccountById(long id) {
		PlatComAccount platComAccount = new PlatComAccount();
		for (int i = 0; i < platComAccountList.size(); i++) {
			PlatComAccount pAccount = platComAccountList.get(i);
			if (pAccount.getId() == id) {
				platComAccount = pAccount;
			}
		}
		return platComAccount;
	}

	public PlatComAccount getPlatComAccountByAllId(long platformId,
			long companyId, long accountId) {
		PlatComAccount platComAccount = null;
		try {
			List<PlatComAccount> platComAccountList = getPlatComAccountListbyAllId(
					platformId, companyId, accountId);
			if (platComAccountList != null && platComAccountList.size() > 0) {

				platComAccount = platComAccountList.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return platComAccount;
	}

	public static Platform getPlatformById(long platformId) {
		if (platformList != null) {
			for (int i = 0; i < platformList.size(); i++) {
				Platform plat = platformList.get(i);
				if (plat != null) {
					if (platformId == plat.getId()) {
						return plat;
					}
				}
			}
		}
		return null;
	}

	// ================================================================================================
	public static Company getCompanyById(long companyId) {
		if (companyList != null) {
			for (int i = 0; i < companyList.size(); i++) {
				Company company = companyList.get(i);
				if (company != null) {
					if (companyId == company.getId()) {
						return company;
					}
				}
			}
		}
		return null;
	}

	public static Account getAccountById(long accountId) {
		if (accountList != null) {
			for (int i = 0; i < accountList.size(); i++) {
				Account account = accountList.get(i);
				if (account != null) {
					if (accountId == account.getId()) {
						return account;
					}
				}
			}
		}
		return null;
	}

	public static PaymentTool getPaymentToolById(long paymentToolId) {
		if (paymentToolList != null) {
			for (int i = 0; i < paymentToolList.size(); i++) {
				PaymentTool paymentTool = paymentToolList.get(i);
				if (paymentTool != null) {
					if (paymentToolId == paymentTool.getId()) {
						return paymentTool;
					}
				}
			}
		}
		return null;
	}

	public static List<Company> getBlurCompanyList(String blur) {
//		logger.info("companyList size:"+companyList.size()+"--getBlurCompanyList:" + blur);
		List<Company> blurCompanyList = new ArrayList<Company>();
		if (Constant.toString(blur).equals("") == false) {
			int j = 0;
			for (int i = 0; i < companyList.size(); i++) {
				if (j > 4) {
					break;
				} else {
					Company company = companyList.get(i);
					if (company != null) {
						String name = Constant.toString(company.getName());
						String companyNo = Constant.toString(company.getNo());

						if (name.indexOf(blur) > -1) {
							blurCompanyList.add(company);
						} else if (companyNo.indexOf(blur) > -1) {
							blurCompanyList.add(company);
						}
					}
				}

				j = blurCompanyList.size();
			}
		}
//		logger.info("result:"+blurCompanyList.size());
		return blurCompanyList;
	}

	public static List<Company> getCompanyListByType(long type) {
		List<Company> tempList = new ArrayList<Company>();
		for (int i = 0; i < companyList.size(); i++) {
			Company company = companyList.get(i);
			if (company.getType() == type) {
				tempList.add(company);
			}
		}
		return tempList;
	}

	public List<PlatComAccount> getPlatComAccountList() {
		return platComAccountList;
	}

	public void setPlatComAccountList(List<PlatComAccount> platComAccountList) {
		PlatComAccountStore.platComAccountList = platComAccountList;
	}

	public List<Platform> getPlatFormList() {
		return platformList;
	}

	public void setPlatFormList(List<Platform> platFormList) {
		PlatComAccountStore.platformList = platFormList;
	}

	public List<Company> getCompanyList() {
		return companyList;
	}

	public void setCompanyList(List<Company> companyList) {
		PlatComAccountStore.companyList = companyList;
	}

	public List<Account> getAccountList() {
		return accountList;
	}

	public void setAccountList(List<Account> accountList) {
		PlatComAccountStore.accountList = accountList;
	}

	public static List<PaymentTool> getPaymentToolList() {
		return paymentToolList;
	}

	public static void setPaymentToolList(List<PaymentTool> paymentToolList) {
		PlatComAccountStore.paymentToolList = paymentToolList;
	}

}
