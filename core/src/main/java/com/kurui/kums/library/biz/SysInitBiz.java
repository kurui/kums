package com.kurui.kums.library.biz;

public interface SysInitBiz {
	public void initMainTask();

	public void updateDataStore();

	public void updateUserStore();

	public void updateDataTypeStore();

	public void updateAgentStore();
	
	public void updateProductStore();

	public void updatePlatComAccountStore();

	public void updatePCAStore_Platform();

	public void updatePCAStore_Company();

	public void updatePCAStore_Account();

	public void updatePCAStore_PaymentTool();

	public void updatePCAStore_PlatComAccount();
}