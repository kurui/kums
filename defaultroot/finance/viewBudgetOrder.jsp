<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%
	String path = request.getContextPath();
%>
<html>
	<head>
		<title>main</title>
		<link href="../_css/reset.css" rel="stylesheet" type="text/css" />
		<link href="../_css/global.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
		function edit(){
		    document.forms[0].action="<%=path%>/finance/budgetOrderList.do?thisAction=edit";
		    document.forms[0].submit();
		}
	</script>
	</head>
	<body>
		<div id="mainContainer">
			<div id="container">
				<html:form action="/finance/budgetOrderList.do">
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
									<c:param name="title2" value="预算管理" />
									<c:param name="title3" value="查看预算明细" />
								</c:import>
								<hr>
								<table width="100%" cellpadding="0" cellspacing="0" border="0"
									class="dataList">
									<c:if test="${!empty budgetOrder.budget}">
										<tr>
											<td class="lef">
												预算单号
											</td>
											<td style="text-align: left">
												<a
													href="../finance/budgetList.do?thisAction=view&id=<c:out value="${budgetOrder.budget.id}" />"><c:out
														value="${budgetOrder.budget.name}" /> </a>
											</td>
										</tr>
									</c:if>
									<tr>
										<td class="lef">
											项目
										</td>
										<td style="text-align: left">
											<c:out value="${budgetOrder.name}" />
										</td>
									</tr>
									<tr>
										<td class="lef">
											预算金额
										</td>
										<td style="text-align: left">
											<c:out value="${budgetOrder.budgetAmount}" />
										</td>
									</tr>
									<tr>
										<td class="lef">
											决算金额
										</td>
										<td style="text-align: left">
											<c:out value="${budgetOrder.statementAmount}" />
										</td>
									</tr>

									<tr>
										<td class="lef">
											预算时间
										</td>
										<td style="text-align: left">
											<c:out value="${budgetOrder.budgetDate}" />
										</td>
									</tr>
									<tr>
										<td class="lef">
											决算时间
										</td>
										<td style="text-align: left">
											<c:out value="${budgetOrder.statementDate}" />
										</td>
									</tr>

									<tr>
										<td class="lef">
											备注
										</td>
										<td style="text-align: left">
											<c:out value="${budgetOrder.memo}" />
										</td>
									</tr>
									<tr>
										<td class="lef">
											状态
										</td>
										<td style="text-align: left">
											<c:out value="${budgetOrder.statusInfo}" />
										</td>
									</tr>

								</table>
								<table width="100%" style="margin-top: 5px;">
									<tr>
										<td align="center">
											<html:hidden property="selectedItems"
												value="${budgetOrder.id}" />
											<input name="label" type="button" class="button1" value="返 回"
												onclick="window.history.back();">
												<input name="label" type="button" class="button1"
													value="修 改" onclick="edit();" />
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