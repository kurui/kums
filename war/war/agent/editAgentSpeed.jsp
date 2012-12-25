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
		<script src="../_js/prototype/common.js" type="text/javascript"></script>
		<script src="../_js/base/FormUtil.js" type="text/javascript"></script>
	</head>
	<script type="text/javascript">
		function addAgent(){	
			var name=document.forms[0].name.value;
			if(name==""){
				alert("请输入客户姓名")
				return false;
			}
			var thisAction =document.forms[0].thisAction.value;			   
		    document.forms[0].action="<%=path%>/agent/agent.do?thisAction="+thisAction;
		    document.forms[0].submit();
		}		
	</script>
	<body>
		<html:form action="/agent/agent.do" method="post">
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
											客户名称
										</td>
										<td style="text-align: left">
											<html:text property="name" name="agent" value="${agent.name}"
												styleClass="colorblue2 p_5" style="width:200px;"></html:text>
											|
											<html:hidden property="id" value="${agent.id}"></html:hidden>

											<html:select property="type" name="agent"
												styleClass="colorblue2 p_5" style="width:80px;">
													<html:option value="5">
														SVIP客户
													</html:option>
													<html:option value="10">
														VIP客户
													</html:option>
													<html:option value="30">
														主要客户
													</html:option>
													<html:option value="35">
														普通客户
													</html:option>
													<html:option value="41">
														小客户
													</html:option>
													<html:option value="110">
														潜在客户
													</html:option>
													<html:option value="121">
														网络客户
													</html:option>
											</html:select>
										</td>
									</tr>
									<tr>
										<td class="lef">
											性别
										</td>
										<td style="text-align: left">
											<html:radio property="sex" name="agent" value="1"
												styleClass="colorblue2 p_5" style="width:20px;">男</html:radio>
											<html:radio property="sex" name="agent" value="2"
												styleClass="colorblue2 p_5" style="width:20px;">女</html:radio>
										</td>
									</tr>
									<tbody id="contactWayBody">										
										<tr>
											<td class="lef">
												备注
											</td>
											<td style="text-align: left">
												<html:text property="memo" name="agent"
													styleClass="colorblue2 p_5" style="width:500px;"></html:text>
											</td>
										</tr>
										<tr>
											<td class="lef">
												分管部门
											</td>
											<td style="text-align: left">
												<html:select property="companyId" name="agent"
													styleClass="colorblue2 p_5" style="width:200px;">
													<c:forEach items="${companyList}" var="company">
														<html:option value="${company.id}">
															<c:out value="${company.name}" />
														</html:option>
													</c:forEach>
												</html:select>
											</td>
										</tr>
										<tr>
											<td class="lef">
												状态
											</td>
											<td style="text-align: left">
												<html:select property="status" name="agent"
													styleClass="colorblue2 p_5" style="width:50px;">
													<html:option value="1">有效</html:option>
													<html:option value="0">无效</html:option>
												</html:select>
											</td>
										</tr>
								</table>
								<table width="100%" style="margin-top: 5px;">
									<tr>
										<td align="center">
											<html:hidden property="thisAction" name="agent" />
											<html:hidden property="addType" name="agent" />
												<input name="label" type="button" class="button1" value="返 回"
												onclick="window.history.back();">
											<input name="label" type="button" class="button1" value="提交"
												onclick="addAgent();">
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


