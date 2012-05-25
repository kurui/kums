<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%
	String path = request.getContextPath();
%>
<html>
	<head>
		<title>企业服务总线</title>
		<link href="<%=path%>/_css/reset.css" rel="stylesheet" type="text/css" />
		<link href="<%=path%>/_css/global.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" language="javascript" src="<%=path%>/_js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript" language="javascript" src="<%=path%>/_js/goto.js"></script>
		<script type="text/javascript" language="javascript" src="<%=path%>/_js/base/menu.js"></script>
		<c:if test="${URI==null}">
			<script language="JavaScript">
   	top.location="<%=path%>/login.jsp" 
	</script>
		</c:if>
	</head>
	<body>
		<div id="mainContainer">
			<div class="fixedSideBar"></div>
			<div id="sideBar">
				<div class="sideBarItem webAdmin">
					<span class="title"><a href="#" onclick="showUL('ulInformation')">流程引擎服务</a> </span>
					<ul class="contents" id="ulInformation" style="">
						<li>
							<a href="../workflow/workflowList.do" target="mainFrame">流程建模</a>
						</li>
						<li>
							<a href="../workflow/workflowList.do" target="mainFrame">流程引擎</a>
						</li>
						<li>
							<a href="../workflow/workflowList.do?thisAction=listTask" target="mainFrame">流程监控</a>
						</li>
					</ul>

					<span class="title"><a href="#" onclick="showUL('ulInformation')">新闻服务</a> </span>
					<ul class="contents" id="ulInformation" style="">
						<li>
							<a href="<%=path%>/information/newsList.do?thisAction=list" target="mainFrame">新闻列表</a>
						</li>
					</ul>

					<span class="title"><a href="#" onclick="showUL('ulMessage')">消息服务</a> </span>
					<ul class="contents" id="ulMessage">
						<li>
							<a href="<%=path%>/message/messageList.do?thisAction=listBaseCase" target="mainFrame">测试用例列表</a>
						</li>
					</ul>

					<span class="title"><a href="#" onclick="showUL('ulOutInterface')">外部系统服务</a> </span>
					<ul class="contents" id="ulOutInterface" style="">
						<li>
							<a href="#" target="mainFrame">电子邮件</a>
						</li>
						<li>
							<a href="#" target="mainFrame">短信平台</a>
						</li>
					</ul>


				</div>
			</div>
			<div class="closeSiseBar">
				<span class="btn"></span>
			</div>
		</div>
		<script type="text/javascript" language="javascript">
      initMenu("sideBar");
      		<c:if test="${URI==null}">
   	top.location="../login.jsp" 
</c:if>
</script>
	</body>
</html>
