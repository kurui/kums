<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%
	String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<link href="<%=path%>/_css/reset.css" rel="stylesheet" type="text/css" />
		<link href="<%=path%>/_css/global.css" rel="stylesheet"
			type="text/css" />
		<script type="text/javascript" language="javascript"
			src="<%=path%>/_js/jquery-1.3.2.min.js"></script>
		<script src="<%=path%>/_js/prototype/common.js" type="text/javascript"></script>
	</head>
	<body>
		<div id="mainContainer">
			<div id="container">
			
			<iframe src="http://192.168.150.10:8080/kums/information/newsList.do?thisAction=listNotice" frameborder="0" scrolling="none" ALIGN="left" width="300px;" height="151px;"></iframe>
			</div>
		</div>
	</body>
</html>
