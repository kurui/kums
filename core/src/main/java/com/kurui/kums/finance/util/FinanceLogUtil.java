package com.kurui.kums.finance.util;

import java.io.File;

import com.kurui.kums.base.file.LogUtil;
import com.kurui.kums.base.Constant;

/**
 * 业务操作日志
 */
public class FinanceLogUtil extends LogUtil {

	@SuppressWarnings("unchecked")
	public FinanceLogUtil(boolean isSystemOut, boolean isPrintClass,
			Class classIn, String des) {
		super(isSystemOut, isPrintClass, classIn, des);
	}

	@SuppressWarnings("unchecked")
	public void init(boolean isSystemOut, boolean isPrintClass, Class useClass) {
		setInputValue(isSystemOut, isPrintClass, useClass);

		String cbstLogFilePath = File.separator + "opt" + File.separator
				+ "IBM" + File.separator + "WebSphere" + File.separator
				+ "AppServer" + File.separator + "profiles" + File.separator
				+ "AppSrv01" + File.separator + "logs" + File.separator
				+ "kums";// 设置日志目录;
		
		cbstLogFilePath=Constant.PROJECT_LOG_PATH;
		
		String cbstLogName = "finance.log";// 名称项目

		arrangePrintLog(cbstLogFilePath, cbstLogName);
	}
}
