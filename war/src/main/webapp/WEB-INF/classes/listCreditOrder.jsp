<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/fmt.tld" prefix="fmt"%>
<%
	String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>债权债务管理</title>
		<link href="<%=path%>/_css/reset.css" rel="stylesheet" type="text/css" />
		<link href="<%=path%>/_css/global.css" rel="stylesheet" type="text/css" />
		<c:import url="../page/importDWR.jsp"></c:import>
		<c:import url="../page/importJQuery.jsp"></c:import>
		<script type="text/javascript" src="<%=path%>/_js/prototype/common.js"></script>
		<script type="text/javascript" src="<%=path%>/_js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript" src="<%=path%>/_js/base/FormUtil.js"></script>
		<script type="text/javascript" src="<%=path%>/_js/calendar/WdatePicker.js"></script>
	</head>
	<body>
		<c:choose>
			<c:when test="${param.orderType==1000}">
				<c:set var="subtitle" value="债务列表" />
			</c:when>
			<c:when test="${param.orderType==2000}">
				<c:set var="subtitle" value="债权列表" />
			</c:when>
			<c:otherwise>
			</c:otherwise>
		</c:choose>
		<div id="mainContainer">
			<div id="container">
				<html:form action="/finance/financeOrderList.do">
					<html:hidden property="thisAction" />
					<html:hidden property="lastAction" />
					<html:hidden property="intPage" />
					<html:hidden property="pageCount" />
					<html:hidden property="statusGroup" />
					<html:hidden property="showType" />

					<table width="100%" cellpadding="0" cellspacing="0" border="0">
						<tr>
							<td width="10" height="10" class="tblt"></td>
							<td height="10" class="tbtt"></td>
							<td width="10" height="10" class="tbrt"></td>
						</tr>
						<tr>
							<td width="10" class="tbll"></td>
							<td valign="top" class="body">
								<c:import url="../page/mainTitle.jsp" charEncoding="UTF-8">
									<c:param name="title1" value="财务管理" />
									<c:param name="title2" value="账单管理" />
									<c:param name="title3" value="${subtitle}" />
								</c:import>
								<jsp:include page="listSearchToolBar.jsp"></jsp:include>
								<table id="myTable" cellpadding="0" cellspacing="0" border="0" class="dataList" width="99%">
									<tr>
										<c:if test="${param.orderType==1400}">
											<th>
												<div>
													债权人
												</div>
										</c:if>
										<c:if test="${param.orderType==1300}">
											<th>
												<div>
													债务人
												</div>
										</c:if>

										<th>
											<div>
												科目
											</div>
										</th>
										<th>
											<div>
												金额
											</div>
										</th>
										<th>
											<div>
												手续费
											</div>
										</th>
										<th>
											<div>
												日期
											</div>
										</th>
										<th>
											<div>
												状态
											</div>
										</th>
										<th>
											<div>
												流水号
											</div>
										</th>
										<th colspan="2" style="width: 160px;">
											<div>
												操作
											</div>
										</th>
									</tr>
									<c:forEach var="groupInfo" items="${ulf.list}" varStatus="status">
										<c:choose>
											<c:when test="${ulf.orderType==1400}">
												<c:set var="firstOrder" value="${groupInfo.saleOrder}" />
											</c:when>
											<c:when test="${ulf.orderType==1300}">
												<c:set var="firstOrder" value="${groupInfo.buyOrder}" />
											</c:when>
											<c:otherwise>
											</c:otherwise>
										</c:choose>
										<c:if test="${firstOrder.businessType==1}">
											<tr style="background-color: #CCCCCC">
										</c:if>
										<c:if test="${firstOrder.businessType!=1}">
											<tr>
										</c:if>
										<td rowspan="<c:out value="${groupInfo.orderCount}" />">
											<div>
												<c:if test="${!empty firstOrder.agent}">
													<c:out value="${firstOrder.agent.name}" />
												</c:if>
											</div>
										</td>
										<td>
											<div>
												<c:out value="${firstOrder.tranTypeText}" />
											</div>
										</td>
										<td>
											<div>
												<c:out value="${firstOrder.totalAmount}" />
											</div>
										</td>
										<td>
											<div>
												<c:out value="${firstOrder.handlingCharge}" />
											</div>
										</td>
										<td>
											<div>
												<c:out value="${firstOrder.businessDate}" />
												<br />
												<c:out value="${firstOrder.maturityDate}" />
												到期
											</div>
										</td>
										<td>
											<div>
												<c:out value="${firstOrder.statusText}" escapeXml="false" />
											</div>
										</td>

										<td style="width: 60px;">
											<div>
												<a href="<%=path%>/finance/financeOrderList.do?thisAction=view&id=<c:out value='${firstOrder.id}' />"><c:out value="${firstOrder.orderNo}" /> </a>
											</div>
										</td>
										<td style="width: 90px;">
											<div>
												<c:out value='${firstOrder.tradeOperate}' escapeXml="false" />
											</div>
										</td>
										<td style="width: 70px;">
											<div>

												<c:out value='${firstOrder.commonOperateText}' escapeXml="false" />
											</div>
										</td>
										<!-- 加载参数 -->
										<input type="hidden" id="agentName<c:out value='${firstOrder.id}'/>" value="<c:out value="${firstOrder.agent.name}" /> " />
										</tr>
										<c:if test="${groupInfo.orderCount>1}">
											<c:forEach var="info" begin="1" items="${groupInfo.orderList}" varStatus="status3">
												<c:if test="${info.businessType==1}">
													<tr style="background-color: #CCCCCC">
												</c:if>
												<c:if test="${info.businessType!=1}">
													<tr>
												</c:if>

												<td>
													<div>
														<c:out value="${info.tranTypeText}" />
													</div>
												</td>
												<td>
													<div>
														<c:out value="${info.totalAmount}" />
													</div>
												</td>
												<td>
													<div>
														<c:out value="${info.handlingCharge}" />
													</div>
												</td>
												<td>
													<div>
														<c:out value="${info.businessDate}" />
													</div>
												</td>

												<td>
													<div>
														<c:out value="${info.statusText}" escapeXml="false" />
													</div>
												</td>
												<td style="width: 60px;">
													<div>
														<a href="<%=path%>/finance/financeOrderList.do?thisAction=view&id=<c:out value='${info.id}' />"><c:out value="${info.orderNo}" /> </a>
													</div>
												</td>
												<td style="width: 90px;">
													<div>
														<c:out value='${info.tradeOperate}' escapeXml="false" />
													</div>
												</td>
												<td style="width: 70px;">
													<div>

														<c:out value='${info.commonOperateText}' escapeXml="false" />
													</div>
												</td>
												</tr>
												<!-- 加载参数 -->

												<input type="hidden" id="agentName<c:out value='${info.id}'/>" value="<c:out value="${info.agent.name}" />  " />
											</c:forEach>
										</c:if>
									</c:forEach>
								</table>
								<table width="100%" style="margin-top: 5px;">
									<tr>
										<td></td>
										<td align="right">
											<div>
												共有订单&nbsp;
												<c:out value="${ulf.groupCount}" />
												&nbsp;条 明细&nbsp;
												<c:out value="${ulf.totalRowCount}"></c:out>
												&nbsp;条&nbsp;&nbsp;&nbsp;&nbsp; [
												<a href="JavaScript:turnToPage(document.forms[0],0)">首页</a> |
												<a href="JavaScript:turnToPage(document.forms[0],1)">上一页</a> |
												<a href="JavaScript:turnToPage(document.forms[0],2)">下一页</a> |
												<a href="JavaScript:turnToPage(document.forms[0],3)"> 末页</a>] 页数:
												<c:out value="${ulf.intPage}" />
												/
												<c:out value="${ulf.pageCount}" />
												] 第
												<input type="text" name="myIntPage" value="<c:out value='${ulf.intPage}'/>" style="width: 20px;" onkeyup="goMyIntPage(this.form)">
												页
											</div>
										</td>
									</tr>
								</table>
								<div class="clear"></div>

							</td>
							<td width="10" class="tbrr"></td>
						</tr>
						<tr>
							<td width="10" class="tblb"></td>
							<td class="tbbb"></td>
							<td width="10" class="tbrb"></td>
						</tr>
					</table>
				</html:form>
			</div>
		</div>
	</body>
</html>
