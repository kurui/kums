<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/fmt.tld" prefix="fmt"%>
<%
	String path = request.getContextPath();
%>
<html>
	<head>
		<title>main</title>
		<link href="../_css/reset.css" rel="stylesheet" type="text/css" />
		<link href="../_css/global.css" rel="stylesheet" type="text/css" />
		<script src="../_js/prototype/common.js" type="text/javascript"></script>
		<script>
  var displayStatus=false;
  function displayStatement(id) {
    var _tr=document.getElementById(id);
    if(_tr){
      if(displayStatus)
        _tr.style.display="";
      else
        _tr.style.display="none";
      displayStatus=!displayStatus;
    } 
  }

  var displayStatus2=false;  
 function displayOrderDetail(id) {
    var _tr=document.getElementById(id);
    if(_tr){
      if(displayStatus2)
        _tr.style.display="";
      else
        _tr.style.display="none";
      displayStatus2=!displayStatus2;
    } 
  }
  
  function editStatement(id)  {
    var url="<%=path%>/finance/statementList.do?thisAction=edit&id="+id;
    openWindow(400,340,url);  
  }
  
   function editOrder(id)  {
    var url="<%=path%>/finance/financeOrderList.do?thisAction=editOrder&id="+id;
     window.location.href=url;
  }
  function editOrderMemo(id)  {
	var url="<%=path%>/finance/financeOrderList.do?thisAction=editOrderMemo&id="+id;
	openWindow(400,340,url);  
  }
</script>
	</head>
	<body>
		<div id="mainContainer">
			<div id="container">
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
								<c:param name="title2" value="查看详细信息" />
							</c:import>
							<table width="100%" cellpadding="0" cellspacing="0" border="0"
								class="dataList">
								<tr>
									<th>
										<div>
											科目
										</div>
									</th>
									<th>
										<div>
											往来单位(客户)
										</div>
									</th>
									<th>
										<div>
											平台
										</div>
									</th>
									<th>
										<div>
											记账单位
										</div>
									</th>
									<th>
										<div>
											外部订单号
										</div>
									</th>
									<th>
										<div>
											手续费
										</div>
									</th>
									<th>
										<div>
											业务时间
										</div>
									</th>
									<th>
										<div>
											状态
										</div>
									</th>
								</tr>
								<c:forEach items="${financeOrderList}" var="order"
									varStatus="status">
									<tr>
										<td>
											<c:out value="${order.tranTypeText}" />
											(
											<c:out value="${order.businessTypeText}" />
											)
										</td>
										<td>
											<c:out value="${order.cussentCompany.shortName}" /> &nbsp;<c:out value="${order.agent.name}" />
										</td>
										<td>
											<c:out value="${order.platform.name}" />
										</td>
										<td>
											<c:out value="${order.company.name}" />
										</td>
										<td>
											<c:out value="${order.outOrderNo}" />
										</td>
										<td>
											<c:out value="${order.handlingCharge}" />
										</td>
										<td>
											<c:out value="${order.businessDate}" />
										</td>
										<td>
											<c:out value="${order.statusText}" />
										</td>
									</tr>
									<tr>
										<td>
											备注
										</td>
										<td colspan="7">
											<font color="red"><c:out value="${order.memo}" /> </font>&nbsp;&nbsp;&nbsp;&nbsp;

											<a href="#"
												onclick="editOrderMemo('<c:out value="${order.id}"/>')">修改备注</a>
											<a href="#"
												onclick="displayOrderDetail('orderDetailRow<c:out value="${status.count}"/>')">查看明细内容</a>

										</td>
									</tr>

									<tr id="orderDetailRow<c:out value='${status.count}'/>">
										<td colspan="13">
											<table width="100%" class="dataList" cellpadding="0"
												cellspacing="0" border="0">
												<c:forEach items="${orderDetailList}" var="orderDetail"
													varStatus="detailStatus">
													<c:if test="${orderDetail.financeOrder.id==order.id}">
														<tr>
															<td colspan="13" style="text-align: left">
																<c:out value='${detailStatus.count}' />

																<c:out value="${orderDetail.product.no}" />
																<a
																	href="../market/productList.do?thisAction=view&id=<c:out value="${orderDetail.product.id}" />">
																	<c:out value="${orderDetail.product.name}" /> </a> 数量：
																<c:out value="${orderDetail.productCount}" />

																金额：
																<c:out value="${orderDetail.detailAmount}" />

																备注：
																<c:out value="${orderDetail.memo}" />
															</td>
														</tr>
													</c:if>
												</c:forEach>
											</table>
										</td>
									</tr>


									<tr id="payId<c:out value='${status.count}'/>">
										<td colspan="3">
											流水号：
											<c:out value="${order.orderNo}" />
										</td>
										<td colspan="10">
											<table width="100%" bgcolor="#CCCCCC" cellpadding="0"
												cellspacing="0" border="0" class="dataList">
												<c:forEach items="${statementList}" var="statement"
													varStatus="sstatus">
													<c:if test="${statement.orderId==order.id}">
														<tr>
															<td>
																<c:out value='${sstatus.count}' />
															</td>
															<td width="120">
																<a
																	href="<%=path%>/finance/statementList.do?thisAction=view&id=<c:out value="${statement.id}" />">
																	<c:out value="${statement.statementNo}" /> </a>
															</td>
															<td>
																<c:out value="${statement.orderSubtypeText}" />
															</td>
															<td width="200">
																<c:if test="${statement.type==1}">
																	<c:out value="${statement.toAccount.showName}" />
																</c:if>
																<c:if test="${statement.type==2}">
																	<c:out value="${statement.fromAccount.showName}" />
																</c:if>
															</td>
															<td>
																<c:out value="${statement.totalAmount}" />
															</td>
															<td>
																<c:out value="${statement.statementDate}" />
															</td>

															<td>
																<c:out value="${statement.statusInfo}" />
															</td>
															<td>
																<c:out value="${statement.sysUser.userName}" />
															</td>
															<td>
																<c:out value="${statement.memo}" />
															</td>
															<td id="<c:out value='${statement.orderId}'/>">
																<c:if test="${statement.status!=8}">
																	<a href="#"
																		onclick="editStatement('<c:out value="${statement.id}"/>')">修改</a>
																</c:if>
															</td>
														</tr>
													</c:if>
												</c:forEach>
											</table>
										</td>
									</tr>
								</c:forEach>
							</table>
							<table width="100%" style="margin-top: 5px;">
								<tr align="center">
									<td>
										<input name="label" type="button" class="button1" value="返 回"
											onclick="window.history.back();">

										<input name="label" type="button" class="button1" value="编辑订单"
											onclick="editOrder('<c:out value="${financeOrder.id}"/>')">


									</td>
								</tr>
							</table>
							<div class="clear"></div>
							<br />
							<br />
							修改记录
							<table width="100%" cellpadding="0" cellspacing="0" border="0"
								class="dataList">
								<tr>
									<th>
										<div>
											修改时间
										</div>
									</th>
									<th>
										<div>
											操作员
										</div>
									</th>
									<th>
										<div>
											类型
										</div>
									</th>
									<th>
										<div>
											内容
										</div>
									</th>
								</tr>
								<c:forEach items="${operateLogList}" var="log">
									<tr>
										<td width="140">
											<c:out value="${log.formatOptTime}" />
										</td>
										<td width="60">
											<c:out value="${log.sysUser.userName}" />
										</td>
										<td width="120">
											<c:out value="${log.typeInfo}" />
										</td>
										<td>
											<div align="left">
												<c:out value="${log.operateLogDetail.content}" />
											</div>
										</td>
									</tr>
								</c:forEach>
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
			</div>
		</div>

	</body>
</html>
