<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%
	String path = request.getContextPath();
%>
<html>
<head>
<title>main</title>
<link href="<%=path%>/_css/reset.css" rel="stylesheet" type="text/css" />
<link href="<%=path%>/_css/global.css" rel="stylesheet" type="text/css" />
<script src="<%=path%>/_js/base/FormUtil.js" type="text/javascript"></script>
</head>
<body>
	<div id="mainContainer">
		<div id="container">
			<html:form action="/agent/agentCoterieList.do">
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
								<c:param name="title1" value="客户管理" />
								<c:param name="title2" value="查看客户圈" />
							</c:import>
							<hr>
							<table width="100%" cellpadding="0" cellspacing="0" border="0"
								class="dataList">
								<tr>
									<td class="lef">客户圈</td>
									<td style="text-align: left"><c:out
											value="${coterie.name}" /></td>
								</tr>
								<tr>
									<td class="lef"><c:out
											value="${coterie.rootAgent.agentNo}" /></td>
									<td style="text-align: left"><c:out
											value="${coterie.rootAgent.name}" /> | <c:out
											value="${coterie.rootAgent.typeInfo}" /></td>
								</tr>
								<tr>
									<td class="lef">说明</td>
									<td style="text-align: left"><c:out
											value="${coterie.memo}" /></td>
								</tr>
								<tr>
									<td class="lef">状态</td>
									<td style="text-align: left"><c:out
											value="${coterie.statusInfo}" /></td>
								</tr>
							</table>
							<table width="100%" style="margin-top: 5px;">
								<tr>
									<td align="center"><input name="label" type="button"
										class="button1" value="返 回" onclick="window.history.back();">
										<input name="label" type="button" class="button1" value="编辑圈子"
										onclick="edit(<c:out value='${coterie.id}' />);">
										<input name="label" type="button" class="button1"
										value="增加成员" onclick="addAgentCoterie();"> 
										</td>
								</tr>
							</table>
							<table width="100%" cellpadding="0" cellspacing="0" border="0"
								class="dataList">
								<tr>
									<th width="60">
										<div>&nbsp;请选择</div>
									</th>
									<th width="35">
										<div>&nbsp;序号</div>
									</th>
									<th>
										<div>圈子成员</div>
									</th>
								</tr>
								<c:forEach var="agent" items="${agentList}" varStatus="status">
									<tr>
										<td><html:multibox property="selectedItems"
												value="${agent.id}"></html:multibox></td>
										<td><c:out value="${status.count}" /></td>
										<td style="text-align: left"><c:out
												value="${agent.agentNo}" /> | <a
											href="<%=path%>/agent/agentList.do?thisAction=view&id=<c:out value="${agent.id}" />">
												<c:out value="${agent.name}" />
										</a></td>
									</tr>
								</c:forEach>
							</table>
							<table width="100%" style="margin-top: 5px;">
								<tr>
									<td>
									<input
										name="label" type="button" class="button1" value="删 除成员"
										onclick="delAgentCoterie();">
									</td>
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
	<script type="text/javascript">
		function edit(id){
   			 var url="../agent/coterieList.do?thisAction=edit&id="+id;
    		 window.location.href=url;
 		}
 		
 		function addAgentCoterie(){
   			 var url="../agent/agentCoterieList.do?thisAction=save&coterieId="+<c:out
											value="${coterie.id}" />;
    		 window.location.href=url;
 		}
		</script>
</body>
</html>