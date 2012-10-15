<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
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


</head>
<body>
	<div id="mainContainer">
		<div id="container">
			<html:form action="/finance/assetsItemList.do">
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
						<td valign="top" class="body"><c:import
								url="../page/mainTitle.jsp" charEncoding="UTF-8">
								<c:param name="title1" value="财务管理" />
								<c:param name="title2" value="资产项目列表" />
							</c:import>
							<div class="searchBar">
								<table cellpadding="0" cellspacing="0" border="0"
									class="searchPanel">
									<tr>
										<td><input name="label" type="button" class="button1"
											value="新 增" onclick="add();"></td>
										<td><input name="label" type="button" class="button1"
											value="返 回" onclick="window.history.back();"></td>
									</tr>
								</table>
							</div>
							<table width="100%" cellpadding="0" cellspacing="0" border="0"
								class="dataList">
								<tr>
									<th>
										<div>序号</div>
									</th>
									<th>
										<div>项目类型</div>
									</th>
									<th>
										<div>数量</div>
									</th>
									<th>
										<div>估价</div>
									</th>
								</tr>
								<c:forEach var="assetsItem" items="${assetsItemListForm.list}"
									varStatus="status">
									<tr>
										<td><c:out value="${status.count}" /></td>
										<td><c:out value="${assetsItem.itemTypeName}" /></td>
										<td><c:out value="${assetsItem.itemCount}" /></td>
										<td><c:out value="${assetsItem.valuation}" /></td>
									</tr>
								</c:forEach>
								<tr>
										<td colspan="3">
											<div align="center">
												<font>合计</font>
											</div>
										</td>
										<td style="text-align: right;">
											<c:out value="${assetsItemListForm.totalValue1}" />
											&nbsp;
										</td>
									</tr>
							</table>
							<table width="100%" style="margin-top: 5px;">
								<tr>
								</tr>
							</table>
							<div class="clear"></div></td>
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