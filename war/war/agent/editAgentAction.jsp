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
		<link href="<%=path%>/_css/reset.css" rel="stylesheet" type="text/css" />
		<link href="<%=path%>/_css/global.css" rel="stylesheet"
			type="text/css" />
	</head>
	<script type="text/javascript">	
		function add(){	
			var thisAction =document.forms[0].thisAction.value;			   
		    document.forms[0].action="<%=path%>/agent/agentAction.do?thisAction="+thisAction;
		    document.forms[0].submit();
		}
	</script>
	<body>
		<c:import url="../page/mainTitle.jsp" charEncoding="UTF-8">
			<c:param name="title1" value="客户管理" />
			<c:param name="title2" value="编辑客户行为" />
		</c:import>
		<html:form action="/agent/agentAction.do" method="post">
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
											客户
										</td>
										<td style="text-align: left">
											<c:out value="${agentAction.agent.agentNo}"></c:out>
											|
											<c:out value="${agentAction.agent.name}"></c:out>
											<html:hidden property="agentId"
												value="${agentAction.agent.id}" name="agentAction" />
										</td>
									</tr>
									<tr>
										<td class="lef">
											内容
										</td>
										<td style="text-align: left">
											<html:text property="content" name="agentAction"
												value="${agentAction.content}" styleClass="colorblue2 p_5"
												style="width:200px;"></html:text>
										</td>
									</tr>
									<tr>
										<td class="lef">
											类型
										</td>
										<td style="text-align: left">
											<html:select property="type" value="${agentAction.type}"
												name="agentAction" styleClass="colorblue2 p_5"
												style="width:100px;">
												<html:option value="0">-请选择-</html:option>
												<html:option value="1">test1</html:option>
												<html:option value="2">test2</html:option>
											</html:select>
										</td>
									</tr>
									<tr>
										<td class="lef">
											处理时间
										</td>
										<td style="text-align: left">
											<html:text property="updateDate" name="agentAction"
												value="${agentAction.updateDate}"
												styleClass="colorblue2 p_5" style="width:200px;"></html:text>
										</td>
									</tr>
									<tr>
										<td class="lef">
											状态
										</td>
										<td style="text-align: left">
											<html:select property="accountStatus"
												value="${agentAction.status}" name="agentAction"
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
											<html:hidden property="thisAction" name="agentAction" />
											
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