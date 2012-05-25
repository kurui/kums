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
				<html:form action="/agent/vehicleList.do">
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
									<c:param name="title2" value="查看车辆信息" />
								</c:import>
								<hr>
								<table width="100%" cellpadding="0" cellspacing="0" border="0"
									class="dataList">
									<tr>
										<td class="lef">
											<c:out value="${vehicle.agent.agentNo}" />
										</td>
										<td style="text-align: left">
											<c:out value="${vehicle.agent.name}" />
											|
											<c:out value="${vehicle.agent.typeInfo}" />
										</td>
										<td class="lef">
										</td>
										<td style="text-align: left">
										</td>
									</tr>
									<tr>
										<td class="lef">
											号牌
										</td>
										<td style="text-align: left">
											<c:out value="${vehicle.carNo}" />
										</td>
										<td class="lef">
											车型
										</td>
										<td style="text-align: left">
											<c:out value="${vehicle.carType}" />
										</td>
									</tr>
									<tr>
										<td class="lef">
											发动机号
										</td>
										<td style="text-align: left">
											<c:out value="${vehicle.engineNo}" />
										</td>
										<td class="lef">
											车架号
										</td>
										<td style="text-align: left">
											<c:out value="${vehicle.carcaseNo}" />
										</td>
									</tr>
									<tr>
										<td class="lef">
											颜色
										</td>
										<td style="text-align: left">
											<c:out value="${vehicle.color}" />
										</td>
										<td class="lef">
											座位人数
										</td>
										<td style="text-align: left">
											<c:out value="${vehicle.seatCount}" />
										</td>
									</tr>									
									<tr>
										<td class="lef">
											更新时间
										</td>
										<td style="text-align: left">
											<c:out value="${vehicle.updateDate}" />
										</td>
										<td class="lef">
											状态
										</td>
										<td style="text-align: left">
											<c:out value="${vehicle.statusInfo}" />
										</td>
									</tr>
									<tr>
										<td class="lef">
											备注
										</td>
										<td style="text-align: left" colspan="3">
											<c:out value="${vehicle.memo}" />
										</td>
									</tr>
								</table>
								<table width="100%" style="margin-top: 5px;">
									<tr>
										<td align="center">
											<input name="label" type="button" class="button1" value="新 增"
												onclick="add();">
											<input name="label" type="button" class="button1" value="返 回"
												onclick="window.history.back();">
											<input name="label" type="button" class="button1" value="编 辑"
												onclick="edit(<c:out value='${vehicle.id}' />);">

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
			function add(){
   			 var url="../agent/vehicleList.do?thisAction=save";
    		 window.location.href=url;
 		}
		function edit(id){
   			 var url="../agent/vehicleList.do?thisAction=edit&id="+id;
    		 window.location.href=url;
 		}
		</script>
	</body>
</html>