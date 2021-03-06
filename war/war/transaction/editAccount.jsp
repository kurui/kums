﻿<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%
	String path = request.getContextPath();
%>
<html>
	<head>
		<title>main</title>
		<link href="<%=path%>/_css/reset.css" rel="stylesheet" type="text/css" />
		<link href="<%=path%>/_css/global.css" rel="stylesheet"
			type="text/css" />
	</head>
	<script type="text/javascript">	
		function add(){	
			var name=document.forms[0].name.value;
			if(name==""){
				alert("请输入账户名称!")
				return false;
			}
			var thisAction =document.forms[0].thisAction.value;			   
		    document.forms[0].action="<%=path%>/transaction/account.do?thisAction="+thisAction;
		    document.forms[0].submit();
		}
	</script>
	<body>
		<c:import url="../page/mainTitle.jsp" charEncoding="UTF-8">
			<c:param name="title1" value="机构管理" />
			<c:param name="title2" value="编辑账号" />
		</c:import>
		<html:form action="/transaction/account.do" method="post">
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
								<table width="100%" cellpadding="0" cellspacing="0" border="0"
									class="dataList">
									<tr>
										<td class="lef">
											支付工具名称
										</td>
										<td style="text-align: left">
											<html:select property="paymentToolId" name="account"
												styleClass="colorblue2 p_5" style="width:200px;">
												<c:forEach items="${paymentToolList}" var="p">
													<html:option value="${p.id}">
														<c:out value="${p.name}" />
													</html:option>
												</c:forEach>
											</html:select>
										</td>
									</tr>
									<tr>
										<td class="lef">
											开户行
										</td>
										<td style="text-align: left">
											<html:text property="accountAddress" name="account"
												value="${account.accountAddress}"
												styleClass="colorblue2 p_5" style="width:200px;"></html:text>
										</td>
									</tr>
									<tr>
										<td class="lef">
											户名
										</td>
										<td style="text-align: left">
											<html:text property="name" name="account"
												value="${account.name}" styleClass="colorblue2 p_5"
												style="width:200px;"></html:text>
											<html:hidden property="id" value="${account.id}"></html:hidden>
										</td>
									</tr>
									<tr>
										<td class="lef">
											账号
										</td>
										<td style="text-align: left">
											<html:text property="accountNo" name="account"
												value="${account.accountNo}" styleClass="colorblue2 p_5"
												style="width:200px;"></html:text>
										</td>
									</tr>
									<tr>
										<td class="lef">
											业务类型:
										</td>
										<td style="text-align: left">
											<html:select property="businessType"
												styleClass="colorblue2 p_5" style="width:80px;">
												<html:option value="0">请选择	</html:option>
												<html:option value="1">活期</html:option>
												<html:option value="2">定期</html:option>
												<html:option value="11">贷款</html:option>
												<html:option value="21">证券交易</html:option>
											</html:select>
										</td>
									</tr>
									<tr>
										<td class="lef">
											公私类型:
										</td>
										<td style="text-align: left">
											<html:select property="personalType"
												styleClass="colorblue2 p_5" style="width:80px;">
												<html:option value="0">请选择	</html:option>
												<html:option value="1">个人</html:option>
												<html:option value="11">企业基本</html:option>
												<html:option value="12">企业一般</html:option>
											</html:select>
										</td>
									</tr>
									<tr>
										<td class="lef">
											交易用途
										</td>
										<td style="text-align: left">

											<html:select property="tranType" name="account"
												styleClass="colorblue2 p_5" style="width:100px;">
												<html:option value="1">付款</html:option>
												<html:option value="2">收款</html:option>
												<html:option value="3">收付</html:option>
											</html:select>
										</td>
									</tr>
									<tr>
										<td class="lef">
											说明
										</td>
										<td style="text-align: left">
											<html:text property="description" name="account"
												value="${account.description}" styleClass="colorblue2 p_5"
												style="width:200px;"></html:text>
										</td>
									</tr>
									<tr>
										<td class="lef">
											状态
										</td>
										<td style="text-align: left">
											<html:select property="status" name="account"
												styleClass="colorblue2 p_5" style="width:50px;">
												<html:option value="1">有效</html:option>
												<html:option value="0">无效</html:option>
											</html:select>
										</td>
									</tr>
								</table>
								<table width="100%" style="margin-top: 5px;">
									<tr>
										<td>
											<html:hidden property="thisAction" name="account" />
											<input name="label" type="button" class="button1" value="返 回"
												onclick="window.history.back();">
											<input name="label" type="button" class="button1" value="提交"
												onclick="add();">
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


