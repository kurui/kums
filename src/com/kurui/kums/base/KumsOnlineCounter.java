package com.kurui.kums.base;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class KumsOnlineCounter extends HttpServlet implements HttpSessionListener,
		HttpSessionAttributeListener, HttpSessionBindingListener {
	public KumsOnlineCounter() {
		System.out.println("OnlineCounter initialized.");
	}

	private static final long serialVersionUID = 1L;

	private static int sessionCounter = 0;

	private static int attributeCounter = 0;

	public void sessionCreated(HttpSessionEvent se) {
		sessionCounter++;
		System.out.println("session created");
	}

	public void sessionDestroyed(HttpSessionEvent se) {
		sessionCounter--;
		System.out.println("session destroied");
	}

	public void attributeAdded(HttpSessionBindingEvent se) {
		attributeCounter++;
		System.out.println("attribute added");
	}

	public void attributeRemoved(HttpSessionBindingEvent se) {
		attributeCounter--;
		System.out.println("attribute removed");
	}

	public void attributeReplaced(HttpSessionBindingEvent se) {
		System.out.println(se.getName() + " replaced");
	}

	public void valueBound(HttpSessionBindingEvent event) {
		System.out.println(event.getName() + "_Bound_" + event.getValue());
	}

	public void valueUnbound(HttpSessionBindingEvent event) {
		System.out.println(event.getName() + "_Unbound_" + event.getValue());
	}

	public static int getOnlineSession() {
		return sessionCounter;
	}

	public static int getOnlineAttribute() {
		return attributeCounter;
	}
}
