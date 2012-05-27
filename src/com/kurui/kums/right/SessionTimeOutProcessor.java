package com.kurui.kums.right;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.RequestProcessor;
import org.apache.struts.config.ModuleConfig;

import com.kurui.kums.base.Constant;
import com.kurui.kums.right.UserRightInfo;

public class SessionTimeOutProcessor extends RequestProcessor {

	public boolean processPreprocess(HttpServletRequest request,
			HttpServletResponse response) {
		// System.out.println("----processPreprocess()--");
		UserRightInfo uri;
		if (request.getSession().getAttribute("URI") != null)
			uri = (UserRightInfo) request.getSession().getAttribute("URI");
		else
			uri = null;
		String actionName = request.getQueryString();
		if (actionName == null)
			actionName = "";
//		String path = request.getServletPath();

		String url = request.getServletPath() + "?" + actionName;
		String temp = "";
		if (uri == null || uri.getUser() == null) {
			try {
				for (int i = 0; i < Constant.url.size(); i++) {
					if (Constant.url.get(i).endsWith(".*"))
						temp = Constant.url.get(i).replace("*", "do");
					else
						temp = Constant.url.get(i).replace(".",
								".do?thisAction=");
					// System.out.println("Constant.url:" + temp);
					if (url.indexOf(temp) >= 0){						
						return true;
					}
				}

				response.sendRedirect(request.getContextPath() + "/login.jsp");

			} catch (IOException e) {
				e.printStackTrace();
			}
			return false;
		} else {
//			System.out.println("url:\n"+url);
//			System.out.println(request.getServletPath());
			// if(uri.hasRight(rightName, event))
			return true;
		}
	}

	@Override
	public void init(ActionServlet servlet, ModuleConfig moduleConfig)
			throws ServletException {
		super.init(servlet, moduleConfig);
	}
}
