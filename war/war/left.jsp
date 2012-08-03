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
		<title>left</title>
		<link href="<%=path%>/_css/reset.css" rel="stylesheet" type="text/css" />
		<link href="<%=path%>/_css/global.css" rel="stylesheet"
			type="text/css" />
		<script type="text/javascript" language="javascript"
			src="<%=path%>/_js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript" language="javascript"
			src="<%=path%>/_js/goto.js"></script>
	</head>
	<body>
		<div id="mainContainer">
			<div class="fixedSideBar"></div>
			<div id="sideBar">
				<div class="sideBarItem webAdmin">
					<span class="title"> <a
						href="<%=path%>/information/newsList.do?thisAction=listNotice"
						target="mainFrame">系统公告</a> </span>
					<span class="title"><a
						href="user/user.do?thisAction=editMyPassword&userId=<c:out value='${URI.user.userId}'/>"
						target="mainFrame">修改我的密码</a> </span>

					<span class="title"><a
						href="<%=path%>/transaction/accountCheckList.do?thisAction=list"
						target="mainFrame">账户盘点</a> </span>
					<span class="title"><a
						href="<%=path%>/finance/financeOrderList.do?thisAction=addLiveOrder"
						target="mainFrame">管理费用入账</a> </span>
					<span class="title">				
							<a href="<%=path%>/finance/financeOrderList.do?thisAction=listLiveOrder" target="mainFrame">管理费用列表</a>
					</span>
					
					<span class="title">	
							<a href="<%=path%>/transaction/companyList.do?thisAction=list&type=2" target="mainFrame">客户公司列表</a>
					</span>
				</div>
			</div>
			<div class="closeSiseBar">
				<span class="btn"></span>
			</div>
		</div>
	</body>
</html>
