<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>

<%@page import="com.kurui.kums.agent.Agent" %>
<%
	String path = request.getContextPath();
	Agent agent=(Agent)request.getAttribute("agent");
	//使用弹出窗增加新的Agent后，mapping到此取不到id,agentNo，什么原因，待研究
	//out.print("jsp:"+agent.getAgentNo());
	//out.print("jsp3:"+agent.getId());
%>
<html>
	<head>
		<title>main</title>
		<link href="../_css/reset.css" rel="stylesheet" type="text/css" />
		<link href="../_css/global.css" rel="stylesheet" type="text/css" />
		<script src="../_js/base/FormUtil.js" type="text/javascript"></script>
	</head>
	<script type="text/javascript">
	function confirmAgent(){
			//values="<c:out value='${agent.agentNo}' />"; 
			//alert(values);//为什么就是取不到id,agentNo
			values="<c:out value='${agentId}' />"; 			
			//alert(values);
			opener.addAgentId(values);
    		window.close();
	}
	</script>
	<body>
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
							<c:import url="../page/mainTitle.jsp" charEncoding="UTF-8">
								<c:param name="title1" value="客户管理" />
								<c:param name="title2" value="快速建档" />
								<c:param name="title3" value="查看客户信息" />
							</c:import>
							<hr>
							<table width="100%" cellpadding="0" cellspacing="0" border="0"
								class="dataList">
								<tr>
									<td class="lef">
										<c:out value="${agent.agentNo}" />
									</td>
									<td style="text-align: left">
										<c:out value="${agent.name}" />
										|
										<c:out value="${agent.typeInfo}" />
									</td>
								</tr>

								<tr>
									<td class="lef">
										QQ
									</td>
									<td style="text-align: left">
										<c:out value="${agent.qqCode}" />
									</td>
								</tr>
								<tr>
									<td class="lef">
										Email
									</td>
									<td style="text-align: left">
										<c:out value="${agent.email}" />
									</td>
								</tr>
								<tr>
									<td class="lef">
										手机
									</td>
									<td style="text-align: left">
										<c:out value="${agent.mobilePhone}" />
									</td>
								</tr>
								<tr>
									<td class="lef">
										收货地址
									</td>
									<td style="text-align: left">
										<c:out value="${agent.address}" />
									</td>
								</tr>
								<tr>
									<td class="lef">
										分管部门|创建地
									</td>
									<td style="text-align: left">
										<c:out value="${agent.company.name}" />
										|
										<c:out value="${agent.knowPlace}" />
									</td>
								</tr>
								<tr>
									<td class="lef">
										状态
									</td>
									<td style="text-align: left">
										<c:out value="${agent.statusInfo}" />
									</td>
								</tr>
							</table>
							<table width="100%" style="margin-top: 5px;">
								<tr>
									<td align="center">
										<input name="label" type="button" class="button1" value="确 定"
											onclick="confirmAgent();">
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
	</body>
</html>