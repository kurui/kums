<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>

<%
	String path = request.getContextPath();
%>
<html>
	<head>
		<title>main</title>
		<link href="../_css/reset.css" rel="stylesheet" type="text/css" />
		<link href="../_css/global.css" rel="stylesheet" type="text/css" />
		<script src="../_js/calendar/WdatePicker.js" type="text/javascript"></script>
		<script src="../_js/base/FormUtil.js" type="text/javascript"></script>
	</head>
	<script type="text/javascript">	
		function add()		{	
			var thisAction =document.forms[0].thisAction.value;			   
		    document.forms[0].action="<%=path%>/finance/budgetOrder.do?thisAction="+thisAction;
		    document.forms[0].submit();
		}		
	</script>
	<body>
	<c:choose>
      <c:when test="${budgetOrder.thisAction=='update'}">
        <c:set var="title2" value="编辑预算明细"/>
      </c:when>
      <c:when test="${budgetOrder.thisAction=='settlement'}">
        <c:set var="title2" value="決算"/>
      </c:when>
 </c:choose>
		<c:import url="../page/mainTitle.jsp" charEncoding="UTF-8">
			<c:param name="title1" value="财务管理" />
			<c:param name="title2" value="${title2}" />
		</c:import>
		<html:form action="/finance/budgetOrder.do" method="post">
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

								<hr>
								<table width="100%" cellpadding="0" cellspacing="0" border="0" class="dataList">
									<html:hidden property="id" value="${budgetOrder.id}"></html:hidden>
									<html:hidden property="budgetId" value="${budgetOrder.budget.id}"></html:hidden>
									<tr>
										<td class="lef">
											预算案
										</td>
										<td style="text-align: left">
											<c:out value="${budgetOrder.budget.no}" />
											<a href="<%=path%><c:out value="${budgetOrder.budget.id}" />"><c:out value="${budgetOrder.budget.name}" /> </a>
										</td>
									</tr>
									<tr>
										<td class="lef">
											项目
										</td>
										<td style="text-align: left">
											<html:text property="name" name="budgetOrder" value="${budgetOrder.name}" styleClass="colorblue2 p_5" style="width:200px;"></html:text>
										</td>
									</tr>
									<tr>
										<td class="lef">
											预算金额
										</td>
										<td style="text-align: left">
											<html:text property="budgetAmount" name="budgetOrder" value="${budgetOrder.budgetAmount}" styleClass="colorblue2 p_5" style="width:200px;"></html:text>
										</td>
									</tr>
									<tr>
										<td class="lef">
											说明
										</td>
										<td style="text-align: left">
											<html:text property="memo" name="budgetOrder" value="${budgetOrder.memo}" styleClass="colorblue2 p_5" style="width:200px;"></html:text>
										</td>
									</tr>
									<tr>
										<td class="lef">
											状态
										</td>
										<td style="text-align: left">
											<html:select property="status" name="budgetOrder" styleClass="colorblue2 p_5" style="width:50px;">
												<html:option value="1">有效</html:option>
												<html:option value="0">无效</html:option>
											</html:select>
										</td>
									</tr>
								</table>
								<table width="100%" style="margin-top: 5px;">
									<tr>
										<td align="right">
											<html:hidden property="thisAction" name="budgetOrder" />
											<input name="label" type="button" class="button1" value="返 回" onclick="window.history.back();">
											<input name="label" type="button" class="button1" value="提 交" onclick="add();">

											<input name="label" type="button" class="button1" value="决 算" onclick="">

											<input name="label" type="reset" class="button1" value="重 置">

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
				</div>
			</div>
		</html:form>
	</body>
</html>