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
		    document.forms[0].action="<%=path%>/market/saleEventList.do?thisAction=edit";
		    document.forms[0].submit();
		}
	</script>
	</head>
	<body>
		<div id="mainContainer">
			<div id="container">
				<html:form action="/market/saleEventList.do">
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
									<c:param name="title1" value="市场管理" />
									<c:param name="title2" value="查看活动" />
								</c:import>
								<hr>
								<table width="100%" cellpadding="0" cellspacing="0" border="0"
									class="dataList">
									<tr>
										<td class="lef">
											名称
										</td>
										<td style="text-align: left">
											<c:out value="${saleEvent.name}" />
										</td>
									</tr>
									<tr>
										<td class="lef">
											备注
										</td>
										<td style="text-align: left">
											<c:out value="${saleEvent.memo}" />
										</td>
									</tr>
									<tr>
										<td class="lef">
											类型
										</td>
										<td style="text-align: left">
											<c:out value="${saleEvent.typeInfo}" />
										</td>
									</tr>
									<tr>
										<td class="lef">
											状态
										</td>
										<td style="text-align: left">
											<c:out value="${saleEvent.statusInfo}" />
										</td>
									</tr>

								</table>
								<table width="100%" style="margin-top: 5px;">
									<tr>
										<td align="center">
											<html:hidden property="selectedItems" value="${saleEvent.id}" />
											<html:hidden property="thisAction" name="saleEvent" />
											<input name="label" type="button" class="button1" value="返 回"
												onclick="window.history.back();">
											<input name="label" type="button" class="button1" value="修 改"
												onclick="edit();" />
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
