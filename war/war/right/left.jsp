<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
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

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>权限管理</title>
		<link href="../_css/reset.css" rel="stylesheet" type="text/css" />
		<link href="../_css/global.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" language="javascript"
			src="../_js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript" language="javascript"
			src="../_js/goto.js"></script>
		<script type="text/javascript" language="javascript"
			src="../_js/base/menu.js"></script>
	</head>
	<body>
		<div id="mainContainer">
			<div class="fixedSideBar"></div>
			<div id="sideBar">
				<div class="sideBarItem webAdmin">
					<span class="title"><a href="#" onclick="showUL('ulUser')">用户管理</a>
					</span>
					<ul class="contents" id="ulUser" style="">
						<li>
							<a href="<%=path%>/user/userlist.do?thisAction=list&userStatus=1"
								target="mainFrame">用户列表</a>
						</li>
					</ul>
					<span class="title"><a href="#">权限管理</a> </span>
					<ul class="contents">
						
							<ul class="contents">
								<li>
									<a href="<%=path%>/right/rolelist.do?thisAction=listRole"
										target="mainFrame">角色列表</a>
								</li>
								<li>
									<a href="<%=path%>/right/rolelist.do?thisAction=editrole4user"
										target="mainFrame">给用户授角色</a>
								</li>
								<li>
									<a href="<%=path%>/right/rolelist.do?thisAction=edituser4role"
										target="mainFrame">给角色分配用户</a>
								</li>
							</ul>
							<c:check code="se01,se02">
						</c:check>
				</div>
			</div>
			<div class="closeSiseBar">
				<span class="btn"></span>
			</div>
		</div>
		<script type="text/javascript" language="javascript">
      initMenu("sideBar");
	</script>
	</body>
</html>
