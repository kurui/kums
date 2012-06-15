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
				<html:form action="/finance/orderDetailList.do?thisAction=list"
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
									<c:param name="title1" value="财务管理" />
									<c:param name="title2" value="订单明细列表" />
								</c:import>

								<div class="searchBar">
									<table cellpadding="0" cellspacing="0" border="0"
										class="searchPanel">
										<tr>
											<td>
												产品号:
											</td>
											<td>
												<html:text property="productNo" styleClass="colorblue2 p_5"
													style="width:150px;" />
											</td>
											<td>
												订单号:
											</td>
											<td>
												<html:text property="orderNo" styleClass="colorblue2 p_5"
													style="width:150px;" />
											</td>
											<td>
												排序:
											</td>
											<td>
												<html:select property="orderBy" styleClass="colorblue2 p_5"
													style="width:150px;">
													<html:option value="">-请选择-</html:option>
													<html:option value="product">-产品-</html:option>
													<html:option value="agent">-客户-</html:option>
												</html:select>
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
												产品
											</div>
										</th>
										<th>
											<div>
												客户
											</div>
										</th>
										<th>
											<div>
												单价
											</div>
										</th>
										<th>
											<div>
												数量
											</div>
										</th>
										<th>
											<div>
												金额
											</div>
										</th>
										<th>
											<div>
												备注
											</div>
										</th>
										<th>
											<div>
												状态
											</div>
										</th>
										<th>
											<div>
												操作
											</div>
										</th>
									</tr>
									<c:forEach items="${orderDetailListForm.list}"
										var="orderDetail" varStatus="detailStatus">
										<tr>
											<td>
												<c:out value='${detailStatus.count}' />
											</td>
											<td style="text-align: left">
												<c:out value="${orderDetail.product.no}" />
												<a
													href="../market/productList.do?thisAction=view&id=<c:out value="${orderDetail.product.id}" />">
													<c:out value="${orderDetail.product.name}" /> </a>
											</td>
											<td style="text-align: left">
												<c:out value="${orderDetail.financeOrder.agent.agentNo}" />
												<a
													href="../agent/agentList.do?thisAction=view&id=<c:out value="${orderDetail.financeOrder.agent.id}" />">
													<c:out value="${orderDetail.financeOrder.agent.name}" /> </a>
											</td>
											<td>

												<c:out value="${orderDetail.product.price}" />
											</td>
											<td>

												<c:out value="${orderDetail.productCount}" />
											</td>
											<td>
												<c:out value="${orderDetail.detailAmount}" />
											</td>

											<td>
												<c:out value="${orderDetail.memo}" />
											</td>
											<td>
												<c:out value="${orderDetail.statusInfo}" />
											</td>
											<td>
												<a
													href="../finance/financeOrderList.do?thisAction=view&id=<c:out value="${orderDetail.financeOrder.id}" />">
													查看</a>
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
											<c:out value="${orderDetailListForm.totalValue1}" />
										</td>
										<td>
											<c:out value="${orderDetailListForm.totalValue2}" />
										</td>
										<td>
											<c:out value="${orderDetailListForm.totalValue3}" />
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
