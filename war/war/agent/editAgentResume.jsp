<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%
	String path = request.getContextPath();
%>
<html>
<head>
<title>main</title>
<link href="<%=path%>/_css/reset.css" rel="stylesheet" type="text/css" />
<link href="<%=path%>/_css/global.css" rel="stylesheet" type="text/css" />
<c:import url="../page/importDWR.jsp"></c:import>
<script type="text/javascript" src="<%=path%>/_js/prototype/common.js"></script>
<script type="text/javascript" src="<%=path%>/_js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="<%=path%>/_js/base/FormUtil.js"></script>
<script type="text/javascript"
	src="<%=path%>/_js/calendar/WdatePicker.js"></script>
</head>
<script type="text/javascript">	
		function add(){	
			var thisAction =document.forms[0].thisAction.value;			   
		    document.forms[0].action="<%=path%>/agent/agentResume.do?thisAction="+thisAction;
		    document.forms[0].submit();
		}
	</script>
<body>
	<c:import url="../page/mainTitle.jsp" charEncoding="UTF-8">
		<c:param name="title1" value="客户管理" />
		<c:param name="title2" value="编辑简历" />
	</c:import>
	<html:form action="/agent/agentResume.do" method="post">
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
									<td class="lef">客户</td>
									<td style="text-align: left"><c:out
											value="${agentResume.agent.agentNo}"></c:out> | 
											<a href="<%=path%>/agent/agentList.do?thisAction=view&id=<c:out value="${agentResume.agent.id}"/>">
												<c:out value="${agentResume.agent.name}" />
											</a> <html:hidden
											property="agentId" value="${agentResume.agent.id}"
											name="agentResume" /></td>
								</tr>
								<tr>
									<td class="lef">时间</td>
									<td style="text-align: left"><html:text
											property="beginDate" name="agentResume"
											value="${agentResume.beginDate}" styleClass="colorblue2 p_5"
											style="width:120px;"></html:text> --<html:text
											property="endDate" name="agentResume"
											value="${agentResume.endDate}" styleClass="colorblue2 p_5"
											style="width:120px;"></html:text></td>
								</tr>
								<tr>
									<td class="lef">公司/组织</td>
									<td style="text-align: left"><html:select
											property="companyId" value="${agentResume.company.id}"
											styleClass="colorblue2 p_5" styleId="companyId"
											style="width:200px;">
											<html:option value="0">	请选择</html:option>
											<c:forEach items="${companyList}" var="company">
												<html:option value="${company.id}">
													<c:out value="${company.showName}" />
												</html:option>
											</c:forEach>
										</html:select></td>
								</tr>
								<tr>
									<td class="lef">内容</td>
									<td style="text-align: left"><html:text property="content"
											name="agentResume" value="${agentResume.content}"
											styleClass="colorblue2 p_5" style="width:400px;"></html:text>
									</td>
								</tr>
								<tr>
									<td class="lef">类型</td>
									<td style="text-align: left"><html:select property="type"
											value="${agentResume.type}" name="agentResume"
											styleClass="colorblue2 p_5" style="width:100px;">
											<html:option value="0">-请选择-</html:option>
												<html:option value="1">S-手机</html:option>
												<html:option value="2">G-固定电话</html:option>
												<html:option value="11">EMAIL</html:option>
												<html:option value="12">QQ</html:option>
												<html:option value="21">Z-祖籍</html:option>
												<html:option value="31">S-收货地址</html:option>
												<html:option value="51">J-简历</html:option>
										</html:select></td>
								</tr>

								<tr>
									<td class="lef">状态</td>
									<td style="text-align: left"><html:select
											property="status" value="${agentResume.status}"
											name="agentResume" styleClass="colorblue2 p_5"
											style="width:50px;">
											<html:option value="1">有效</html:option>
											<html:option value="2">历史</html:option>
											<html:option value="0">无效</html:option>
										</html:select></td>
								</tr>
							</table>
							<table width="100%" style="margin-top: 5px;">
								<tr>
									<td>
									<html:hidden property="thisAction" name="agentResume" />

<html:hidden property="id" name="agentResume" />
										<input name="label" type="button" class="button1" value="返 回"
										onclick="window.history.back();"> <input name="label"
										type="button" class="button1" value="提交" onclick="add();">
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