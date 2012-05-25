<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/tld/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%
	String path = request.getContextPath();
%>
<c:if test="${URI==null}">
	<script language="JavaScript">
   	top.location="<%=path%>/login.jsp" 
	</script>
</c:if>
<html>
	<head>
		<title>酷睿天下集团信息平台</title>
		<link href="_css/reset.css" rel="stylesheet" type="text/css" />
		<link href="_css/global.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" language="javascript" src="./_js/jquery-1.3.2.min.js"></script>
	</head>
	<body>
		<div id="header">
			<div class="logo">
				<!-- -->
				<img src="./_img/kurui/kuruiLogo2.png" alt="酷睿天下" class="imgLogo" />
				<span class="sysTitle">酷睿天下集团信息平台</span>
			</div>
			<div class="mainNav">
				<ul class="navContent">
					<li>
						<a href="left.jsp" target="leftFrame">首页</a>
					</li>
					<c:check code="ra01-ra10">
						<li>
							<a href="agent/left.jsp" target="leftFrame">大客户部</a>
						</li>
					</c:check>
					<c:check code="sj01,sj10,sj20,sj30">
						<li>
							<a href="finance/left.jsp" target="leftFrame">金融服务事业部</a>
						</li>
					</c:check>
					<li>
						<a href="market/left.jsp" target="leftFrame">主营业务</a>
					</li>

					<li>
						<a href="report/left.jsp" target="leftFrame">报表管理</a>
					</li>

					<c:check code="se01">
						<li>
							<a href="right/left.jsp" target="leftFrame">权限管理</a>
						</li>
					</c:check>

					<c:check code="ss01,sa01-sa20">
						<li>
							<a href="system/left.jsp" target="leftFrame">系统管理</a>
						</li>
					</c:check>

					<c:check code="sa01-sa50">
						<li>
							<a href="esb/left.jsp" target="leftFrame">企业服务总线</a>
						</li>
					</c:check>
				</ul>
				<ul class="userPanel">
					<li>
						欢迎：
						<FONT color="red"> <c:out value="${URI.user.userName}" /> </FONT>
					</li>
					<li>
						<a href="user/user.do?thisAction=exit">退出</a>
					</li>
				</ul>
			</div>
		</div>
	</body>
</html>
