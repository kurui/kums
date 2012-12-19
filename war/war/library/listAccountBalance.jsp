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
		<script type="text/javascript" language="javascript"
			src="../_js/jquery-1.3.2.min.js"></script>
		<script src="../_js/prototype/common.js" type="text/javascript"></script>
		<script src="../_js/calendar/WdatePicker.js" type="text/javascript"></script>
		<script type="text/javascript">
			function exportAccountBalance(){
				document.forms[0].action="<%=path%>/library/accountList.do?thisAction=downloadAccountBalance";
				document.forms[0].submit();
			}
		</script>
	</head>
	<body>
		<div id="mainContainer">
			<div id="container">
				<html:form
					action="/library/accountList.do?thisAction=listAccountBanlance"
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
									<c:param name="title2" value="账户余额列表" />																						
								</c:import>
								<br />
								<table cellpadding="0" cellspacing="0" border="0"
									class="searchPanel">
									<tr>
										<td>
											<input type="button" class="button1" value="导 出"
												onclick="exportAccountBalance()" />
										</td>
									</tr>
								</table>
								<table width="100%" cellpadding="0" cellspacing="0" border="0"
									class="dataList">
									<tr>
										<th>
											<div>
												序号
											</div>
										</th>
										<th>
											<div>
												账号
											</div>
										</th>
										<th>
											<div>
												金额
											</div>
										</th>
									</tr>
									<c:forEach var="acc" items="${accountListForm.list}"
										varStatus="sta">
										<tr>
											<td>
												<c:out
													value="${sta.count+(accountListForm.intPage-1)*accountListForm.perPageNum}" />
											</td>
											<td>
												<a
													href="<%=path%>/library/accountList.do?thisAction=view&id=<c:out value="${acc.id}" />">
													<c:out value="${acc.name}" /> </a>
											</td>
											<td>
												<c:out value="${acc.totalAmount}" />
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
											<c:out value="${accountListForm.totalValue1}" />
										</td>
									</tr>
								</table>
								<table width="100%" style="margin-top: 5px;">
									<tr>

										<td align="right">
											<div>
												共有记录&nbsp;
												<c:out value="${accountListForm.totalRowCount}"></c:out>
												&nbsp;条&nbsp;&nbsp;&nbsp;&nbsp; [
												<a href="JavaScript:turnToPage(document.forms[0],0)">首页</a>
												|
												<a href="JavaScript:turnToPage(document.forms[0],1)">上一页</a>
												|
												<a href="JavaScript:turnToPage(document.forms[0],2)">下一页</a>
												|
												<a href="JavaScript:turnToPage(document.forms[0],3)"> 末页</a>]
												页数:
												<c:out value="${accountListForm.intPage}" />
												/
												<c:out value="${accountListForm.pageCount}" />
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
