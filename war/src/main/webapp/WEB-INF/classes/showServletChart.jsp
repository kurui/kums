<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ page import="org.jfree.chart.ChartRenderingInfo"%>
<%@ page import="org.jfree.chart.ChartUtilities"%>
<%@ page import="java.io.PrintWriter"%>
<html>
	<head>
	</head>
	<body>
		<%
			ChartRenderingInfo info = (ChartRenderingInfo) request
					.getAttribute("ChartRenderingInfo");
			PrintWriter w = new PrintWriter(out);// 输出MAP信息
			ChartUtilities.writeImageMap(w, "map0", info, false);
		%>
		<P ALIGN="CENTER">
			<img src="<c:out value="${graphURL}" ></c:out>" width=500 height=300
				border=0 usemap="#map0">
		</P>
	</body>
</html>