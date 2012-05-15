package com.kurui.kums.base.chart;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class ChartStore implements HttpSessionBindingListener, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List chartNames;

	public ChartStore() {
		chartNames = new ArrayList();
	}

	public void addChart(String filename) {
		chartNames.add(filename);
	}

	public boolean isChartAvailable(String filename) {
		return chartNames.contains(filename);
	}

	public void valueBound(HttpSessionBindingEvent httpsessionbindingevent) {
	}

	public void valueUnbound(HttpSessionBindingEvent event) {
		Iterator iter = chartNames.listIterator();
		do {
			if (!iter.hasNext())
				break;
			String filename = (String) iter.next();
			File file = new File(System.getProperty("java.io.tmpdir"), filename);
			if (file.exists())
				file.delete();
		} while (true);
	}

	// ============================
	public static void deleteHistory(ServletContextEvent event) {
		ChartStore chartStore = (ChartStore) event.getServletContext()
				.getAttribute("ChartStore");
		
		
	}

}
