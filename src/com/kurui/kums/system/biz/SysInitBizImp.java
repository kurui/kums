package com.kurui.kums.system.biz;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import com.kurui.kums.finance.FinanceOrder;
import com.kurui.kums.market.PriceReference;
import com.kurui.kums.market.Product;
import com.kurui.kums.market.dao.PriceReferenceDAO;
import com.kurui.kums.market.dao.ProductDAO;
import com.kurui.kums.market.util.PriceIndexStore;
import com.kurui.kums.market.util.ProductStore;
import com.kurui.kums.base.MainTask;
import com.kurui.kums.base.PerformListener;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.right.SysUser;
import com.kurui.kums.right.UserStore;
import com.kurui.kums.right.dao.UserDAO;
import com.kurui.kums.transaction.Account;
import com.kurui.kums.agent.Agent;
import com.kurui.kums.agent.DirectLevel;
import com.kurui.kums.transaction.Company;
import com.kurui.kums.transaction.DataType;
import com.kurui.kums.transaction.PaymentTool;
import com.kurui.kums.transaction.PlatComAccount;
import com.kurui.kums.transaction.Platform;
import com.kurui.kums.transaction.dao.AccountDAO;
import com.kurui.kums.agent.dao.AgentDAO;
import com.kurui.kums.agent.dao.DirectLevelDAO;
import com.kurui.kums.agent.util.AgentStore;
import com.kurui.kums.transaction.dao.CompanyDAO;
import com.kurui.kums.transaction.dao.DataTypeDAO;
import com.kurui.kums.transaction.dao.PaymentToolDAO;
import com.kurui.kums.transaction.dao.PlatComAccountDAO;
import com.kurui.kums.transaction.dao.PlatformDAO;
import com.kurui.kums.transaction.util.DataTypeStore;
import com.kurui.kums.transaction.util.PlatComAccountStore;

public class SysInitBizImp implements SysInitBiz {

	static Logger logger = Logger.getLogger(SysInitBizImp.class.getName());

	private PlatComAccountDAO platComAccountDAO;
	private PlatformDAO platformDAO;
	private CompanyDAO companyDAO;
	private AccountDAO accountDAO;
	private PaymentToolDAO paymentToolDAO;
	private AgentDAO agentDAO;
	private UserDAO userDAO;
	private DataTypeDAO dataTypeDAO;
	private DirectLevelDAO directLevelDAO;

	private ProductDAO productDAO;
	private PriceReferenceDAO priceReferenceDAO;

	public void initMainTask() {
		try {
			MainTask task = MainTask.getInstance();
			Thread thread = new Thread(task);
			thread.start();
			logger.info("init MainTask success! ");
		} catch (Exception ex) {
			System.out.println("init MainTask fails... " + ex.getMessage());
		}
	}

	public void updateDataStore() {
		try {
			long a = System.currentTimeMillis();

			updatePlatComAccountStore();
			new PerformListener("update platComAccount", a);

			long c = System.currentTimeMillis();
			updateAgentStore();
			new PerformListener("update agent", c);

			long d = System.currentTimeMillis();
			updateProductStore();
			new PerformListener("update product", d);

			long e = System.currentTimeMillis();
			updateUserStore();
			new PerformListener("update user", e);

			long f = System.currentTimeMillis();
			updateDataTypeStore();
			new PerformListener("update datatype", f);

			long g = System.currentTimeMillis();
			updatePriceIndexStore();
			new PerformListener("update priceIndexStore", g);

			long h = System.currentTimeMillis();
			buildProvideChainTree();
			new PerformListener("update provideChainTree", h);

			logger.info("init DataStore success! ");
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("init DataStore fails... " + e.getMessage());
		}
	}

	public void updatePlatComAccountStore() {
		try {
			updatePCAStore_PlatComAccount();
			updatePCAStore_Platform();
			updatePCAStore_Company();
			updatePCAStore_Account();
			updatePCAStore_PaymentTool();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateUserStore() {
		try {
			List<SysUser> userList = userDAO.list();
			if (userList != null) {
				UserStore.userList = userList;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateDataTypeStore() {
		try {
			List<DataType> dataTypeList = dataTypeDAO.getValidDataTypeList();
			if (dataTypeList != null) {
				DataTypeStore.dataTypeList = dataTypeList;
			}

			FinanceOrder.TRAN_TYPE_GROUP_1201 = DataTypeStore
					.getDataTypeGroup("1201");
			FinanceOrder.TRAN_TYPE_GROUP_15 = DataTypeStore
					.getDataTypeGroup("15");
			FinanceOrder.TRAN_TYPE_GROUP_1400 = DataTypeStore
					.getDataTypeGroup("1400");
			FinanceOrder.TRAN_TYPE_GROUP_1300 = DataTypeStore
					.getDataTypeGroup("1300");

			buildDataTypeTree();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updatePriceIndexStore() {
		try {
			List<PriceReference> priceReferenceList = priceReferenceDAO
					.getValidPriceReferenceList();
			if (priceReferenceList != null) {
				PriceIndexStore.priceReferenceList = priceReferenceList;
			}

			buildReferenceTree();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void buildReferenceTree() {
		try {
			priceReferenceDAO.buildPriceReferenceTree();
		} catch (AppException e) {
			e.printStackTrace();
		}
	}

	public void buildDataTypeTree() {
		try {
			dataTypeDAO.buildProductTree();
		} catch (AppException e) {
			e.printStackTrace();
		}
	}

	public void buildProvideChainTree() {
		try {
			dataTypeDAO.buildProvideChainTree();
		} catch (AppException e) {
			e.printStackTrace();
		}
	}

	public void updateAgentStore() {
		try {
			List<Agent> agentList = agentDAO.getValidAgentList();
			if (agentList != null) {
				AgentStore.agentList = agentList;
			}

			List<DirectLevel> directLevelList = directLevelDAO
					.getValidDirectLevelList();
			if (agentList != null) {
				AgentStore.directLevelList = directLevelList;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateProductStore() {
		try {
			List<Product> productList = productDAO.getValidProductList();
			if (productList != null) {
				ProductStore.productList = productList;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updatePCAStore_PlatComAccount() {
		try {
			List<PlatComAccount> platComAccountList = platComAccountDAO
					.getValidPlatComAccountList();
			List<PlatComAccount> temPlatComAccountList = new ArrayList<PlatComAccount>();

			for (int i = 0; i < platComAccountList.size(); i++) {
				PlatComAccount pca = platComAccountList.get(i);
				Account a = platComAccountList.get(i).getAccount();
				pca.setAccount((Account) a.clone());
				Platform pf = platComAccountList.get(i).getPlatform();
				pca.setPlatform((Platform) pf.clone());
				Company c = platComAccountList.get(i).getCompany();
				pca.setCompany((Company) c.clone());
				temPlatComAccountList.add(pca);
			}

			if (platComAccountList != null) {
				PlatComAccountStore.platComAccountList = temPlatComAccountList;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updatePCAStore_Account() {
		try {
			List<Account> accountList = accountDAO.getValidAccountList();
			if (accountList != null) {
				PlatComAccountStore.accountList = accountList;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void updatePCAStore_Company() {
		try {
			List<Company> companyList = companyDAO.getValidCompanyList();
			if (companyList != null) {
				PlatComAccountStore.companyList = companyList;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updatePCAStore_Platform() {
		try {
			List<Platform> platFormList = platformDAO.getValidPlatformList();

			if (platFormList != null) {
				PlatComAccountStore.platformList = platFormList;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updatePCAStore_PaymentTool() {
		try {
			List<PaymentTool> paymentToolList = paymentToolDAO
					.getValidPaymentToolList();
			if (paymentToolList != null) {
				PlatComAccountStore.paymentToolList = paymentToolList;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setPlatComAccountDAO(PlatComAccountDAO platComAccountDAO) {
		this.platComAccountDAO = platComAccountDAO;
	}

	public void setPlatformDAO(PlatformDAO platformDAO) {
		this.platformDAO = platformDAO;
	}

	public void setCompanyDAO(CompanyDAO companyDAO) {
		this.companyDAO = companyDAO;
	}

	public void setAccountDAO(AccountDAO accountDAO) {
		this.accountDAO = accountDAO;
	}

	public void setPaymentToolDAO(PaymentToolDAO paymentToolDAO) {
		this.paymentToolDAO = paymentToolDAO;
	}

	public void setAgentDAO(AgentDAO agentDAO) {
		this.agentDAO = agentDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public void setDirectLevelDAO(DirectLevelDAO directLevelDAO) {
		this.directLevelDAO = directLevelDAO;
	}

	public void setPriceReferenceDAO(PriceReferenceDAO priceReferenceDAO) {
		this.priceReferenceDAO = priceReferenceDAO;
	}

	public void setDataTypeDAO(DataTypeDAO dataTypeDAO) {
		this.dataTypeDAO = dataTypeDAO;
	}

	public void setProductDAO(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}

}
