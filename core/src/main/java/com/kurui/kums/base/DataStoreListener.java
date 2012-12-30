package com.kurui.kums.base;

import org.apache.log4j.Logger;

import com.kurui.kums.library.biz.SysInitBiz;

/**
 * 监听
 * 
 * @author YanRui
 */
public class DataStoreListener implements Runnable {
	private SysInitBiz sysInitBiz;
	private String dataName = "";

	static Logger logger = Logger.getLogger(DataStoreListener.class.getName());

	/**
	 * 监听器(默认) SysInitBiz
	 */
	public DataStoreListener(SysInitBiz sysInitBiz, String dataName) {
		super();
		this.sysInitBiz = sysInitBiz;
		this.dataName = dataName;
	}

	public void run() {
		try {
			logger.info("----------------------run() begin----");
			if (dataName == "PlatComAccount") {
				sysInitBiz.updatePlatComAccountStore();
			} else if (dataName == "PaymentTool") {
				sysInitBiz.updatePCAStore_PaymentTool();
			} else if (dataName == "Platform") {
				sysInitBiz.updatePCAStore_Platform();
			} else if (dataName == "Company") {
				sysInitBiz.updatePCAStore_Company();
			} else if (dataName == "Account") {
				sysInitBiz.updatePCAStore_Account();
			}else if (dataName == "Product") {
				sysInitBiz.updateProductStore();
			} else if (dataName == "SysUser") {
				sysInitBiz.updateUserStore();
			} else {
				logger.info("未定义的监听类型");
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	}

	public void setSysInitBiz(SysInitBiz sysInitBiz) {
		this.sysInitBiz = sysInitBiz;
	}

	public void setDataName(String dataName) {
		this.dataName = dataName;
	}

}