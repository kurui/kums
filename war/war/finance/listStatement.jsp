<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/tld/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>

<%
	String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<link href="../_css/reset.css" rel="stylesheet" type="text/css" />
		<link href="../_css/global.css" rel="stylesheet" type="text/css" />
		<script src="../_js/prototype/common.js" type="text/javascript"></script>
		<script src="../_js/calendar/WdatePicker.js" type="text/javascript"></script>
	</head>
	<body>
		<div id="mainContainer">
			<div id="container">
				<html:form action="/finance/statementList.do?thisAction=list"
					method="post">
					<html:hidden property="thisAction" />
					<html:hidden property="lastAction" />
					<html:hidden property="intPage" />
					<html:hidden property="pageCount" />

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
									<c:param name="title1" value="结算管理" />
									<c:param name="title2" value="结算列表" />
								</c:import>

								<div class="searchBar">
									<table cellpadding="0" cellspacing="0" border="0"
										class="searchPanel">
										<tr>
											<td>
												结算单号:
											</td>
											<td>
												<html:text property="statementNo"
													styleClass="colorblue2 p_5" style="width:150px;" />
											</td>
											<td>
												<input type="submit" name="button" id="button" value="提交"
													class="submit greenBtn" />
											</td>
										</tr>
									</table>
								</div>
								<table width="100%" cellpadding="0" cellspacing="0" border="0"
									class="dataList">
									<tr>
										<th width="35">
											<div>
												&nbsp;序号
											</div>
										</th>
										<th>
											<div>
												结算单号
											</div>
										</th>
										<th>
											<div>
												交易类型
											</div>
										</th>
										<th>
											<div>
												付款账户
											</div>
										</th>
										<th>
											<div>
												收款账户
											</div>
										</th>

										<th>
											<div>
												金额
											</div>
										</th>

										<th>
											<div>
												结算状态
											</div>
										</th>
										<th>
											<div>
												订单类型
											</div>
										</th>
										<th>
											<div>
												修改
											</div>
										</th>
									</tr>
									<c:forEach var="info" items="${ulf.list}" varStatus="sta">
										<tr>
											<td>
												<c:out value="${sta.count+(ulf.intPage-1)*ulf.perPageNum}" />
											</td>
											<td>
												<a
													href="<%=path%>/finance/statementList.do?thisAction=view&id=<c:out value="${info.id}" />">
													<c:out value="${info.statementNo}" /> </a>
											</td>
											<td>
												<c:out value="${info.typeInfo}" />
												|
												<c:out value="${info.orderSubtypeText}" />
											</td>
											<td>
												<c:if test="${!empty info.fromAccount}">
													<c:out value="${info.fromAccount.name}" />
												</c:if>
											</td>
											<td>
												<c:if test="${!empty info.toAccount}">
													<c:out value="${info.toAccount.name}" />
												</c:if>
											</td>
											<td>
												<c:out value="${info.totalAmount}" />
											</td>
											<td>
												<c:out value="${info.statusInfo}" />
											</td>
											<td>
												<c:out value="${info.orderTypeInfo}" />
											</td>
											<td>
												<a
													href="<%=path%>/finance/financeOrderList.do?thisAction=view&id=<c:out value="${info.orderId}" />">查看订单</a>
											</td>
										</tr>
									</c:forEach>
									<tr>
										<td>
											<div align="center">
												<font>合计</font>
											</div>
										</td>
										<td>
											&nbsp;
										</td>
										<td>
											&nbsp;
										</td>
										<td>
											&nbsp;
										</td>
										<td>
											&nbsp;
										</td>
										<td>
											<c:out value="${ulf.totalValue1}" />
										</td>
										<td>
											&nbsp;
										</td>
										<td>
											&nbsp;
										</td>
									</tr>
								</table>

								<table width="100%" style="margin-top: 5px;">
									<tr>

										<td align="right">
											<div>
												共有记录&nbsp;
												<c:out value="${ulf.totalRowCount}"></c:out>
												&nbsp;条&nbsp;&nbsp;&nbsp;&nbsp; [
												<a href="JavaScript:turnToPage(document.forms[0],0)">首页</a>
												|
												<a href="JavaScript:turnToPage(document.forms[0],1)">上一页</a>
												|
												<a href="JavaScript:turnToPage(document.forms[0],2)">下一页</a>
												|
												<a href="JavaScript:turnToPage(document.forms[0],3)"> 末页</a>]
												页数:
												<c:out value="${ulf.intPage}" />
												/
												<c:out value="${ulf.pageCount}" />
												]
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
