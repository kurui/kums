<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%
	String path = request.getContextPath();
%>
<html>
	<head>
		<title>left</title>
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
					<c:check code="ss01">
						<span class="title"><a href="#" onclick="showUL('ulPCAccount')">机构管理</a> </span>
						<ul class="contents" id="ulPCAccount" style="display: none">
							<li>
								<a href="<%=path%>/transaction/platComAccountList.do?thisAction=list" target="mainFrame">平台账号列表</a>
							</li>

							<li>
								<a href="<%=path%>/transaction/platformList.do?thisAction=list" target="mainFrame">交易平台列表</a>
							</li>
							<li>
								<a href="<%=path%>/transaction/companyList.do?thisAction=list&type=1" target="mainFrame">集团旗下公司列表</a>
							</li>
							<li>
								<a href="<%=path%>/transaction/paymentToolList.do?thisAction=list" target="mainFrame">支付工具列表</a>
							</li>
							<li>
								<a href="<%=path%>/transaction/accountList.do?thisAction=list&type=1&status=1" target="mainFrame">账号列表</a>
							</li>
						</ul>
					</c:check>

					<span class="title"><a href="#" onclick="showUL('ulDataType')">数据字典管理</a> </span>
					<ul class="contents" id="ulDataType">
						<li>
							<a href="<%=path%>/transaction/dataTypeList.do?thisAction=list&level=1" target="mainFrame">数据字典列表</a>
						</li>
					</ul>

					<span class="title"><a href="#" onclick="showUL('ulSystemData')">系统数据管理</a> </span>
					<ul class="contents" id="ulSystemData">
						<li>
							<a href="<%=path%>/system/editSystemData.jsp" target="mainFrame">更新系统数据</a>
						</li>
					</ul>

					<span class="title"><a href="#" onclick="showUL('ulLog')">日志管理</a> </span>
					<ul class="contents" id="ulLog" style="display: none">
						<li>
							<a href="<%=path%>/system/loginloglist.do?thisAction=list&locate=2" target="mainFrame">登录日志</a>
						</li>
						<li>
							<a href="<%=path%>/system/operateLoglist.do?thisAction=list" target="mainFrame">操作日志</a>
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
