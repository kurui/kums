package com.kurui.kums.test.commons;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class ApplicationContextFactory {

	private static Log log = LogFactory.getLog(ApplicationContextFactory.class);

	private static Object initObj = null;
	private static int count = 0;

	public static void init(Object o) {
		if (count > 0) {
			log.error("Can't initialize the application context twice: THIS SHOULD ONLY HAPPEN DURING TESTING");
		}
		initObj = o;
		count++;
	}

	public static ApplicationContext getApplicationContext() {
		if (initObj == null) {
			throw new IllegalStateException(
					"Application context not initialized");
		}
		// else if (initObj instanceof ServletContext){
		// ServletContext servletContext = (ServletContext) initObj;
		// return
		// WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
		//
		// }
		else if (initObj instanceof String) {
			String contextResourceLocation = (String) initObj;
//			return new ClassPathXmlApplicationContext(contextResourceLocation);
			return new FileSystemXmlApplicationContext(contextResourceLocation);
		} else {
			// throw new
			// IllegalStateException("You must initialize the context with a String or ServletContext");
			throw new IllegalStateException(
					"You must initialize the context with a String");
		}
	}

}
