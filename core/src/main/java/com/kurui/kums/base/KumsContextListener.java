package com.kurui.kums.base;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.kurui.kums.base.chart.ChartStore;
import com.kurui.kums.library.biz.SysInitBiz;
import com.kurui.kums.library.biz.SysInitBizImp;

public class KumsContextListener implements ServletContextListener {
	static Logger logger = Logger.getLogger(SysInitBizImp.class.getName());

	public void contextInitialized(ServletContextEvent event) {
		SysInitBiz sysInitBiz;
		logger.info("init data,please waiting a few minutes.");
		try {
			contextInitPath(event);

			ApplicationContext applicationContext = WebApplicationContextUtils
					.getWebApplicationContext(event.getServletContext());
			sysInitBiz = (SysInitBiz) applicationContext.getBean("sysInitBiz");

			sysInitBiz.initMainTask();
			sysInitBiz.updateDataStore();
		} catch (Exception ex) {
			logger.error("init data fails... " + ex.getMessage());
		}
	}

	public void contextInitPath(ServletContextEvent event) {
		ServletContext sc = event.getServletContext();

		Constant.SERVLET_PATH = (new StringBuilder(String.valueOf(sc
				.getRealPath("/")))).toString();

		Constant.SERVLET_XML_PATH = (new StringBuilder(String
				.valueOf(Constant.SERVLET_PATH))).append("_xml").append(
				File.separator).toString();

		Constant.SERVLET_CHART_PATH = (new StringBuilder(String
				.valueOf(Constant.SERVLET_PATH))).append("chart").append(
				File.separator).toString();

		Constant.WEB_INFO_PATH = (new StringBuilder(String
				.valueOf(Constant.SERVLET_PATH))).append("WEB-INF").append(
				File.separator).toString();
		Constant.PROJECT_UPLOAD_PATH = (new StringBuilder(String
				.valueOf(Constant.SERVLET_PATH))).append("upload").append(
				File.separator).toString();
		Constant.PROJECT_LOG_PATH = (new StringBuilder(String
				.valueOf(Constant.SERVLET_PATH))).append("log").append(
				File.separator).toString();

		event.getServletContext().setAttribute("PROJECT_UPLOAD_PATH",
				Constant.PROJECT_UPLOAD_PATH);
		event.getServletContext().setAttribute("contextPath",
				Constant.SERVLET_PATH);
	}

	public void contextDestroyed(ServletContextEvent event) {
		logger.info("destroy data,please waiting a few minutes.");
		ChartStore.deleteHistory(event);
		// FileUtil.delAllFile(Constant.SERVLET_CHART_PATH);

	}
}