<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>.
<%
	String path = request.getContextPath();
%>
<html>
	<head>
		<title>main</title>
		<link href="../_css/reset.css" rel="stylesheet" type="text/css" />
		<link href="../_css/global.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
	</script>
	</head>
	<body>
		<div id="mainContainer">
			<div id="container">
				<html:form action="/transaction/accountList.do">
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
									<c:param name="title1" value="机构管理" />
									<c:param name="title2" value="查看账号" />
								</c:import>
								<hr>
								<table width="100%" cellpadding="0" cellspacing="0" border="0" class="dataList">
									<tr>
										<td class="lef">
											支付工具名称
										</td>
										<td style="text-align: left">
											<c:out value="${account.paymentTool.name}" />
										</td>
									</tr>
									<tr>
										<td class="lef">
											开户行
										</td>
										<td style="text-align: left">
											<c:out value="${account.accountAddress}" />
										</td>
									</tr>
									<tr>
										<td class="lef">
											名称
										</td>
										<td style="text-align: left">
											<c:out value="${account.name}" />
										</td>
									</tr>
									<tr>
										<td class="lef">
											账号
										</td>
										<td style="text-align: left">
											<c:out value="${account.accountNo}" />
										</td>
									</tr>
									<tr>
										<td class="lef">
											业务类型
										</td>
										<td style="text-align: left">
											<c:out value="${account.businessTypeInfo}" />
										</td>
									</tr>
									<tr>
										<td class="lef">
											公私
										</td>
										<td style="text-align: left">
											<c:out value="${account.personalTypeInfo}" />
										</td>
									</tr>
									<tr>
										<td class="lef">
											交易用途
										</td>
										<td style="text-align: left">
											<c:out value="${account.tranTypeInfo}" />
										</td>
									</tr>
									<tr>
										<td class="lef">
											类型
										</td>
										<td style="text-align: left">
											<c:out value="${account.typeInfo}" />
										</td>
									</tr>
									<tr>
										<td class="lef">
											状态
										</td>
										<td style="text-align: left">
											<c:out value="${account.statusInfo}" />
										</td>
									</tr>

								</table>
								<table width="100%" style="margin-top: 5px;">
									<tr>
										<td align="center">
											<html:hidden property="selectedItems" value="${account.id}" />
											<html:hidden property="thisAction" name="account" />
											<input name="label" type="button" class="button1" value="新 增" onclick="add();" />
											<input name="label" type="button" class="button1" value="返 回" onclick="window.history.back();">
											<input name="label" type="button" class="button1" value="修 改" onclick="edit();" />
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
	<script type="text/javascript">
		function add(){		   
		    document.forms[0].action="<%=path%>/transaction/accountList.do?thisAction=save";
		    document.forms[0].submit();
		}
		function edit(){
		    document.forms[0].action="<%=path%>/transaction/accountList.do?thisAction=edit";
		    document.forms[0].submit();
		}
	</script>
</html>
