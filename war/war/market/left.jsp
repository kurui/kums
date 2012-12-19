<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%
	String path = request.getContextPath();
%>
<c:if test="${URI==null}">
	<script language="JavaScript">
   				top.location="../login.jsp" 
			</script>
</c:if>
<html>
	<head>
		<title>主营业务</title>
		<link href="<%=path%>/_css/reset.css" rel="stylesheet" type="text/css" />
		<link href="<%=path%>/_css/global.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" language="javascript" src="<%=path%>/_js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript" language="javascript" src="<%=path%>/_js/goto.js"></script>
		<script type="text/javascript" language="javascript" src="<%=path%>/_js/base/menu.js"></script>
	</head>
	<body>
		<div id="mainContainer">
			<div class="fixedSideBar"></div>
			<div id="sideBar">
				<div class="sideBarItem webAdmin">
					<span class="title"><a href="#" onClick="showUL('ulMainOrder')">销售管理</a> </span>
					<ul id="ulMainOrder" class="contents">
					<li>
							<a href="../finance/financeOrderList.do?thisAction=addMainOrder&companyId=577&tranType=1591" target="mainFrame">保险销售入账</a>
						</li>
						<li>
							<a href="../finance/financeOrderList.do?thisAction=addMainOrder&companyId=533&tranType=1585" target="mainFrame">软件开发劳务入账</a>
						</li>
						<li>
							<a href="../finance/financeOrderList.do?thisAction=addMainOrder&companyId=532&tranType=1570" target="mainFrame">旅游事业部入账</a>
						</li>
						<li>
							<a href="../finance/financeOrderList.do?thisAction=addMainOrder&companyId=531&tranType=1501" target="mainFrame">保健品销售入账</a>
						</li>
						<li>
							<a href="../finance/financeOrderList.do?thisAction=addMainOrder&companyId=530&tranType=1560" target="mainFrame">电子商务入账</a>
						</li>
						<li>
							<a href="<%=path%>/finance/financeOrderList.do?thisAction=listMainOrder" target="mainFrame">主营业务列表</a>
						</li>
					</ul>
					<span class="title"><a href="#" onclick="showUL('ulProduct')">产品管理</a> </span>
					<ul class="contents" id="ulProduct">
						<li>
							<a href="<%=path%>/market/productList.do?thisAction=list&type=1" target="mainFrame">自有产品列表</a>
						</li>
						<li>
							<a href="<%=path%>/market/productList.do?thisAction=list&type=11" target="mainFrame">市场产品列表</a>
						</li>
						<li>
							<a href="<%=path%>/market/saleEventList.do?thisAction=list" target="mainFrame">活动列表</a>
						</li>
					</ul>
					<span class="title"><a href="#" onclick="showUL('ulEstateDish')">地产事业部</a> </span>
					<ul class="contents" id="ulEstateDish">
						<li>
							<a href="<%=path%>/market/estateDishList.do?thisAction=list" target="mainFrame">楼盘列表</a>
						</li>
						<li>
							<a href="<%=path%>/market/apartmentList.do?thisAction=list" target="mainFrame">物业单元列表</a>
						</li>
					</ul>

					<span class="title" onClick="showUL('ulCompany')"><a href="<%=path%>/library/companyList.do?thisAction=list&type=2" target="mainFrame">供应链管理</a> </span>
					<ul id="ulCompany" class="contents" style="">
						<li>
							<a href="<%=path%>/library/companyList.do?thisAction=list&type=2" target="mainFrame">客户公司列表</a>
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
		</script>
	</body>
</html>