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
		<title>管理费用</title>
		<link href="<%=path%>/_css/reset.css" rel="stylesheet" type="text/css" />
		<link href="<%=path%>/_css/global.css" rel="stylesheet" type="text/css" />
		<c:import url="../page/importDWR.jsp"></c:import>
		<script type="text/javascript" src="<%=path%>/_js/prototype/common.js"></script>
		<script type="text/javascript" src="<%=path%>/_js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript" src="<%=path%>/_js/base/FormUtil.js"></script>
		<script type="text/javascript" src="<%=path%>/_js/calendar/WdatePicker.js"></script>
		<style type="text/css">
.dataList th {
	padding: 0;
	margin: 0;
	text-align: center;
}

.dataList td {
	text-align: center;
	border: 1px solid #dedfe1;
	font-size: 12px;
	empty-cells: show;
	padding: 0;
	margin: 0;
	line-height: 22px;
}

.dataList .operationArea a {
	color: #005c9c;
	text-decoration: underline;
}

.wordWrap {
	word-wrap: break-word;
	word-break: break-all;
	overflow: hidden;
	-moz-binding: url('../_css/wordwrap.xml#wordwrap');
}
</style>
	</head>
	<body>
		<div id="mainContainer">
			<div id="container">
				<html:form action="/finance/financeOrderList.do">
					<html:hidden property="thisAction" />
					<html:hidden property="lastAction" />
					<html:hidden property="intPage" />
					<html:hidden property="pageCount" />
					<html:hidden property="statusGroup" />
					<html:hidden property="showType" />
					<table width="100%" cellpadding="0" cellspacing="0">
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
									<c:param name="title3" value="管理费用列表" />
								</c:import>
								<hr>
								<jsp:include page="listSearchToolBar.jsp"></jsp:include>
								<table id="myTable" cellpadding="0" cellspacing="0" border="0" class="dataList" width="99%">
									<tr>
										<th>
											<div>
												日期
											</div>
										</th>
										<th class="tdType wordWrap">
											<div>
												类型
											</div>
										</th>
										<th class="tdAmount wordWrap">
											<div>
												金额
											</div>
										</th>
										<th>
											<div>
												往来单位
											</div>
										</th>
										<th>
											<div>
												说明
											</div>
										</th>
										<th class="tdStatus wordWrap" style="width: 8%;">
											<div>
												状态
											</div>
										</th>
										<th class="tdOperator wordWrap">
											<div>
												负责人
											</div>
										</th>

										<th class="tdOrderTime wordWrap" style="width: 60px;">
											<div>
												流水号
											</div>
										</th>
										<th class="tdAction wordWrap" colspan="2" style="width: 160px;">
											<div>
												操作
											</div>
										</th>

									</tr>
									<c:forEach var="financeOrder" items="${ulf.list}" varStatus="status">
										<tr>
											<td>
												<div>
													<c:out value="${financeOrder.businessDate}" />
												</div>
											</td>

											<td class="tdType wordWrap">
												<div>
													<c:out value="${financeOrder.tranTypeText}" />
												</div>
											</td>
											<td class="tdAmount wordWrap">
												<div>
													<c:out value="${financeOrder.totalAmount}" />
												</div>
											</td>
											<td>
												<div>
													<a href="<%=path%>/transaction/companyList.do?thisAction=view&id=<c:out value="${financeOrder.cussentCompany.id}" />"> <c:out value="${financeOrder.cussentCompany.shortName}" />
													</a>
												</div>
											</td>
											<td style="text-align: left">
												<div>
													<c:out value="${financeOrder.memo}" />
												</div>
											</td>
											<td class="tdStatus wordWrap">
												<div>
													<c:out value="${financeOrder.statusText}" escapeXml="false" />
												</div>
											</td>
											<td class="tdOperator wordWrap">
												<div>
													<c:out value="${financeOrder.showEntryOperatorName}" />
												</div>
											</td>


											<td class="tdOrderTime wordWrap" style="width: 60px;">
												<div>
													<a href="<%=path%>/finance/financeOrderList.do?thisAction=view&id=<c:out value='${financeOrder.id}' />"><c:out value="${financeOrder.orderNo}" /> </a>
												</div>
											</td>
											<td class="tdAction0 wordWrap" style="width: 90px;">
												<div>
													<c:out value='${financeOrder.tradeOperate}' escapeXml="false" />
												</div>
											</td>
											<td class="tdAction1 wordWrap" style="width: 70px;">
												<div>
													<c:out value='${financeOrder.commonOperateText}' escapeXml="false" />
												</div>
											</td>
											<!-- 加载参数 -->

											<input type="hidden" id="companyName<c:out value='${financeOrder.id}'/>" value="<c:out value="${financeOrder.company.name}" />
                " />
										</tr>
									</c:forEach>
									<tr>
										<td colspan="2">
											<div align="center">
												<font>合计</font>
											</div>
										</td>
										<td style="text-align: right;">
											<c:out value="${ulf.totalValue1}" />
											&nbsp;
										</td>
										<td colspan="6"></td>
									</tr>
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
