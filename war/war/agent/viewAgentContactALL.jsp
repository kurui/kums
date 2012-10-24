<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/struts-html-el.tld" prefix="html"%>
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
		<script src="<%=path%>/_js/base/FormUtil.js" type="text/javascript"></script>
	</head>
	<body>
		<div id="mainContainer">
			<div id="container">
				<html:form action="/agent/agentContactList.do">
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
									<c:param name="title1" value="客户管理" />
									<c:param name="title2" value="查看联系信息" />
								</c:import>
								<hr>
								<table width="100%" cellpadding="0" cellspacing="0" border="0"
									class="dataList">
									<c:forEach var="agentContact" items="${agentContactList}" varStatus="status">
										<tr>
											<td>
												<html:multibox property="selectedItems" value="${agentContact.id}"></html:multibox>
											</td>
											<td>
												<c:out value="${status.count}" />
											</td>
											<td>
												<c:out value="${agentContact.agent.agentNo}" />
												|
												<c:out value="${agentContact.agent.name}" />
											</td>
											<td>
												<c:out value="${agentContact.typeInfo}" />
											</td>
											<td>
												<c:out value="${agentContact.content}" />
											</td>
											<td>
												<c:out value="${agentContact.tag}" />
											</td>
											<td>
												<c:out value="${agentContact.updateDate}" />
											</td>
											<td>
												<c:out value="${agentContact.statusInfo}" />
											</td>
											<td>
												<a href="<%=path%>/agent/agentContactList.do?thisAction=view&id=<c:out value="${agentContact.id}" />"> 查看</a>
												<a href="<%=path%>/agent/agentContactList.do?thisAction=edit&id=<c:out value="${agentContact.id}" />"> 编辑</a>
											
											</td>
										</tr>
									</c:forEach>
								</table>
								<table width="100%" style="margin-top: 5px;">
									<tr>
										<td align="center">
											<input name="label" type="button" class="button1" value="返 回"
												onclick="window.history.back();">
											<input name="label" type="button" class="button1" value="编 辑"
												onclick="edit(<c:out value='${agentContact.id}' />);">
											
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
		<script type="text/javascript">
		function edit(id){
   			 var url="../agent/agentContactList.do?thisAction=edit&id="+id;
    		 window.location.href=url;
 		}
		</script>
	</body>
</html>