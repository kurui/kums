<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%
	String path = request.getContextPath();
%>
<html>
	<head>
		<title>金融服务事业部</title>
		<link href="../_css/reset.css" rel="stylesheet" type="text/css" />
		<link href="../_css/global.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" language="javascript" src="../_js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript" language="javascript" src="../_js/goto.js"></script>
		<script type="text/javascript" language="javascript" src="../_js/base/menu.js"></script>

		<c:if test="${URI==null}">
			<script language="JavaScript">
   				top.location="../login.jsp" 
			</script>
		</c:if>
	</head>
	<body>
		<div id="mainContainer">
			<div class="fixedSideBar"></div>
			<div id="sideBar">
				<div class="sideBarItem webAdmin">
					<c:check code="sj01">
						<span class="title"><a href="<%=path%>/market/priceIndexList.do?thisAction=list" target="mainFrame" onclick="showUL('ulPriceIndex')">行情中心</a> </span>
						<ul id="ulPriceIndex" class="contents" style="display: none">
							<li>
								<a href="<%=path%>/market/priceIndexList.do?thisAction=list" target="mainFrame">物价指数列表</a>
							</li>
							<li>
								<a href="<%=path%>/market/priceIndexList.do?thisAction=listByPhaseTime" target="mainFrame">物价指数-按月对比</a>
							</li>
							<li>
								<a href="<%=path%>/market/priceReferenceList.do?thisAction=list" target="mainFrame">物价参照物列表</a>
							</li>
							<li>
								<a href="../transaction/companyList.do?thisAction=list&type=2&provideChain=5100" target="mainFrame">数据采集点列表</a>
							</li>
						</ul>
					</c:check>
					<c:check code="sj10">
						<span class="title"><a href="<%=path%>/finance/financeOrderList.do?thisAction=listMainOrder" target="mainFrame" onclick="showUL('ulAssert')">资产业务</a> </span>
						<ul class="contents" id="ulAssert" style="display: none">
							<li>
								<a href="<%=path%>/transaction/accountCheckList.do?thisAction=list" target="mainFrame">账户上下班交接</a>
							</li>
							<li>
								<a href="<%=path%>/finance/financeOrderList.do?thisAction=listMainOrder" target="mainFrame">主营业务列表</a>
							</li>
							<li style="display: none">
								<a href="#" target="mainFrame">额度申请</a>
							</li>
							<li style="display: none">
								<a href="#" target="mainFrame">额度调整</a>
							</li>
							<li style="display: none">
								<a href="#" target="mainFrame">风险预警</a>
							</li>
							<li>
								<a href="<%=path%>/finance/financeOrderList.do?thisAction=addCreditOrder&tranType=1301" target="mainFrame">贷款入账</a>
							</li>
							<li>
								<a href="<%=path%>/finance/financeOrderList.do?thisAction=listCreditOrder&orderType=1300" target="mainFrame">债权列表</a>
							</li>
							<li>
								<a href="<%=path%>/finance/assetsItemList.do?thisAction=list" target="mainFrame">资产项目列表</a>
							</li>
						</ul>
					</c:check>
					<c:check code="sj20">
						<span class="title"><a href="<%=path%>/finance/financeOrderList.do?thisAction=listLiveOrder" target="mainFrame" onclick="showUL('ulDebt')">负债业务</a> </span>
						<ul class="contents" id="ulDebt" style="display: none">
							<li>
								<a href="<%=path%>/finance/financeOrderList.do?thisAction=addLiveOrder" target="mainFrame">管理费用入账</a>
							</li>
							<li>
								<a href="<%=path%>/finance/financeOrderList.do?thisAction=listLiveOrder" target="mainFrame">管理费用列表</a>
							</li>
							<li>
								<a href="<%=path%>/finance/financeOrderList.do?thisAction=addCreditOrder&tranType=1401" target="mainFrame">借款入账</a>
							</li>
							<li>
								<a href="<%=path%>/finance/financeOrderList.do?thisAction=listCreditOrder&orderType=1400" target="mainFrame">债务列表</a>
							</li>
						</ul>
					</c:check>
					<c:check code="sj30">
						<span class="title"><a href="#" target="mainFrame" onclick="showUL('ulBudget')">预算管理</a> </span>
						<ul class="contents" id="ulBudget">
							<li>
								<a href="<%=path%>/finance/budgetList.do?thisAction=list" target="mainFrame">预算案列表</a>
							</li>
						</ul>
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