<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/tld/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>

<c:if test="${URI==null}">
	<script language="JavaScript">
   	top.location="login.jsp" 
	</script>
</c:if>

<html>
	<head>
		<title>酷睿天下集团信息平台</title>
		<link href="_css/reset.css" rel="stylesheet" type="text/css" />
		<link href="_css/global.css" rel="stylesheet" type="text/css" />
		<link href="http://192.168.150.10:8080/kums/_img/kurui/KR-HD.gif" rel="shortcut icon" /> 
		<script src="_js/common.js" type="text/javascript"></script>
		<script language="JavaScript">
<!-- 
  //window.location.href=returnHttps(self.location.href);
  //alert(returnHttps(self.location.href));
  
 if (self!=top){
  top.location=self.location;   
 }
-->
</script>
	</head>
	<frameset rows="70px,*,0" frameborder="NO" border="0" framespacing="0">
		<frame src="../top.jsp" name="topFrame" scrolling="NO" noresize>
		<frameset rows="*,4%" frameborder="NO" border="0" framespacing="0">
			<frameset id="x" cols="170px,*" frameborder="NO" border="0"
				framespacing="0">
				<frame style="width: 100%" src="../left.jsp" name="leftFrame"
					scrolling="NO" noresize>
				<frame style="width: 100%" src="../notice.jsp" name="mainFrame">
			</frameset>
			<frame src="../bottom.jsp" name="bottomFrame" scrolling="NO" noresize>
		</frameset>
	</frameset>
	<noframes>
		<body>
		</body>
</html>
