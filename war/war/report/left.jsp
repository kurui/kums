<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>

<%
	String path = request.getContextPath();
%>

<c:if test="${URI==null}">
	<script>top.location="../login.jsp" ;</script>
</c:if>
<html>
	<head>
		<title>报表管理</title>
		<link href="../_css/reset.css" rel="stylesheet" type="text/css" />
		<link href="../_css/global.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" language="javascript" src="../_js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript" language="javascript" src="../_js/goto.js"></script>
		<script type="text/javascript" language="javascript" src="../_js/base/menu.js"></script>
	</head>
	<body>
		<div id="mainContainer">
			<div class="fixedSideBar"></div>
			<div id="sideBar">
				<div class="sideBarItem webAdmin">
					<span class="title"><a href="#" onclick="showUL('ulBusiness')">常用报表</a> </span>
					<ul class="contents" id="ulBusiness">
						<li>
							<a href="#" target="mainFrame">销售报表</a>
						</li>
						<li>
							<a href="#" target="mainFrame">绩效统计</a>
						</li>
						<li>
							<a href="<%=path%>/report/balanceList.do?thisAction=listEarnings" target="mainFrame">利润表</a>
						</li>
						<li>
							<a href="<%=path%>/report/balanceList.do?thisAction=listEquity" target="mainFrame">资产负债表</a>
						</li>
						<li>
							<a href="<%=path%>/report/creditReportList.do?thisAction=list&type=1" target="mainFrame">应收欠款报告</a>
						</li>
						<li>
							<a href="<%=path%>/report/creditReportList.do?thisAction=list&type=11" target="mainFrame">应付欠款报告</a>
						</li>
					</ul>
					<span class="title"><a href="#" onclick="showUL('ulDetail')">台帐明细</a> </span>
					<ul class="contents" id="ulDetail">
						<li>
							<a href="<%=path%>/report/cashFlowList.do?thisAction=list" target="mainFrame">资金流水</a>
						</li>
						<li>
							<a href="<%=path%>/finance/statementList.do?thisAction=list&status1=1,2" target="mainFrame">结算列表</a>
						</li>
						<li>
							<a href="<%=path%>/finance/orderDetailList.do?thisAction=list" target="mainFrame">订单明细列表</a>
						</li>
						<li>
							<a href="#" target="mainFrame">授信额度台帐</a>
						</li>
						<li>
							<a href="<%=path%>/agent/agentAccountList.do?thisAction=list" target="mainFrame">客户账号列表</a>
						</li>
						<li>
							<a href="<%=path%>/transaction/companyAccountList.do?thisAction=list" target="mainFrame">公司账号列表</a>
						</li>
					</ul>
					<span class="title"><a href="#" onclick="showUL('ulRTemplate')">模版管理</a> </span>
					<ul class="contents" id="ulRTemplate">
						<li>
							<a href="#" target="mainFrame">模版列表</a>
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
