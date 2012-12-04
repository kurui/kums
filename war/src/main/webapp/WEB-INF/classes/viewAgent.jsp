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
		<script type="text/javascript" src="<%=path%>/_js/prototype/common.js"></script>
<script type="text/javascript" src="<%=path%>/_js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript">
		function openResume(agentId){
			var url="../agent/agentResumeList.do?thisAction=viewALL&agentId="+agentId;
			openWindow(800,600,url);										
		}
		
		
		</script>
	</head>
	<body>
		<div id="mainContainer">
			<div id="container">
				<html:form action="/agent/agentList.do">
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
									<c:param name="title2" value="查看客户信息" />
								</c:import>
								<hr>
								<table width="100%" cellpadding="0" cellspacing="0" border="0"
									class="dataList">
									<tr>
										<td class="lef">
											<c:out value="${agent.agentNo}" />
										</td>
										<td style="text-align: left" colspan="3">
											<c:out value="${agent.name}" />
											|
											<c:out value="${agent.typeInfo}" />
											|
											<c:out value="${agent.loyalIndexInfo}" />
											|
											<c:out value="${agent.friendIndexInfo}" />
											|
											<c:out value="${agent.assetIndexInfo}" />
											|
											<c:out value="${agent.specialIndexInfo}" />


											<c:if test="${!empty agent.directLevel}">
											|<c:out value="${agent.directLevel.name}" />
											|<a
													href="<%=path%>/agent/agentRelationList.do?thisAction=listGroupChart&agentId=<c:out value="${agent.id}" />">
													谱系</a>
											</c:if>
											|<a
													href="<%=path%>/agent/coterieList.do?thisAction=save&rootAgentId=<c:out value="${agent.id}" />">
													圈子</a>|<a
													href="<%=path%>/agent/agentRelationList.do?thisAction=save&rootAgentId=<c:out value="${agent.id}" />">
													增加特别关系人</a>
										</td>
									</tr>
									<tr>
										<td class="lef">
											<a href="#" onclick="showElement('personalBody')">个人信息</a>
										</td>
										<td style="text-align: left" colspan="3"></td>
									</tr>
									<tbody id="personalBody" style="display: none">
										<tr>
											<td class="lef">
												身份证号
											</td>
											<td style="text-align: left">
												<c:out value="${agent.cardNo}" />
											</td>
											<td class="lef">
												性别/婚姻
											</td>
											<td style="text-align: left">
												<c:out value="${agent.sexInfo}" />
												|
												<c:out value="${agent.marriage}" />
											</td>
										</tr>
										<tr>
											<td class="lef">
												生日/年龄
											</td>
											<td style="text-align: left">
												<c:out value="${agent.birthday}" />
											</td>
										</tr>
										<tr>
											<td class="lef">
												关系特征
											</td>
											<td style="text-align: left">
												<c:out value="${agent.stampTypeInfo}" />
											</td>
											<td class="lef">
											</td>
											<td style="text-align: left">
											</td>
										</tr>
									</tbody>
									<tr>
										<td class="lef">
											<a href="#" onclick="showElement('workInfoBody')">简历</a>
										</td>
										<td style="text-align: left" colspan="3">
											<a href="#" onclick="openResume(<c:out value="${agent.id}" />)" >详细</a>
										
										</td>
									</tr>
									<tbody id="workInfoBody" >
										<tr>
											<td class="lef">
												特长
											</td>
											<td style="text-align: left">
												<c:out value="${agent.strongSuit}" />
											</td>
											<td class="lef">
												语言
											</td>
											<td style="text-align: left">
												<c:out value="${agent.language}" />
											</td>
										</tr>
									</tbody>
									<tr>
										<td class="lef">
											<a href="#" onclick="showElement('creditInfoBody')">资产负债</a>
										</td>
										<td style="text-align: left" colspan="3">
											<a
												href="<%=path%>/agent/agentAccountList.do?thisAction=list&agentId=<c:out value="${agent.id}" />">账号列表</a>
											<a
												href="<%=path%>/agent/vehicleList.do?thisAction=list&agentId=<c:out value="${agent.id}" />">车辆列表</a>

											<a
												href="<%=path%>/agent/shareHolderList.do?thisAction=list&agentId=<c:out value="${agent.id}" />">关联公司</a>
											<a
												href="<%=path%>/finance/financeOrderList.do?thisAction=addMainOrder&companyId=530&tranType=601">业务录入</a>
										</td>
									</tr>
									<tbody id="creditInfoBody" style="display: none">
										<tr>
											<td class="lef">
												资产
											</td>
											<td style="text-align: left" colspan="3">
												<c:out value="${agent.assetInfo}" />
											</td>
										</tr>
										<tr>
											<td class="lef">
												信贷
											</td>
											<td style="text-align: left" colspan="3">
												额度:
												<c:out value="${agent.creditAmount}" />
												&nbsp; 应收：&nbsp; 应付: &nbsp;
												<c:out value="${agent.creditInfo}" />
											</td>
										</tr>
										<c:forEach var="info" items="${creditOrderList}"
											varStatus="status">
											<tr>
												<td class="lef">
													<c:out value="${status.count}" />
												</td>
												<td style="text-align: left" colspan="3">
													<c:out value="${info.tranTypeText}" />
													<c:if test="${!empty info.account}">
														<c:out value="${info.account.showName}" />
													</c:if>
													金额：
													<c:out value="${info.totalAmount}" />
													| 手续费：
													<c:out value="${info.handlingCharge}" />
													| 发生时间
													<c:out value="${info.businessDate}" />
													| 到期日
													<c:out value="${info.maturityDate}" />
													|

													<c:out value="${info.statusText}" escapeXml="false" />
													<a
														href="<%=path%>//finance/financeOrderList.do?thisAction=view&id=<c:out value="${info.id}" />">
														查看</a>
												</td>
										</c:forEach>
									</tbody>
									<tr>
										<td class="lef">
											<a href="#" onclick="showElement('healthInfoBody')">健康信息</a>
										</td>
										<td style="text-align: left" colspan="3"></td>
									</tr>
									<tbody id="healthInfoBody" style="display: none '">
										<tr>
											<td class="lef">
												概况
											</td>
											<td style="text-align: left" colspan="3">
												<c:out value="${agent.healthInfo}" />
											</td>
										</tr>
									</tbody>
									<tr>
										<td class="lef">
											<a href="#" onclick="showElement('orderInfoBody')">业务明细</a>
										</td>
										<td style="text-align: left" colspan="3">
											实物金额:
											<c:out value="${agent.physicalAmount}" />
											&nbsp; 虚拟金额:
											<c:out value="${agent.virtualAmount}" />
											&nbsp; 消费积分:
											<c:out value="${agent.totalIntegral}" />
										</td>
									</tr>
									<tbody id="orderInfoBody" style="display: none">
										<c:forEach var="info" items="${mainOrderList}"
											varStatus="status">
											<tr>
												<td class="lef">
													<c:out value="${status.count}" />
												</td>
												<td style="text-align: left" colspan="3">
													<c:out value="${info.tranTypeText}" />
													<c:if test="${!empty info.platform}">
														<c:out value="${info.platform.name}" />
													</c:if>
													金额：
													<c:out value="${info.totalAmount}" />
													| 发生时间
													<c:out value="${info.businessDate}" />
													|

													<c:out value="${info.statusText}" escapeXml="false" />
													<a
														href="<%=path%>/finance/financeOrderList.do?thisAction=view&id=<c:out value="${info.id}" />">
														查看</a>
												</td>
										</c:forEach>
									</tbody>
									<tr>
										<td class="lef">
											备注
										</td>
										<td style="text-align: left" colspan="3">
											<c:out value="${agent.memo}" />
										</td>
									</tr>
									<tr>
										<td class="lef">
											分管部门
										</td>
										<td style="text-align: left">
											<c:out value="${agent.company.name}" />
										</td>
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
										<td align="center" colspan="4">
											<input name="label" type="button" class="button1" value="返 回"
												onclick="window.history.back();">
											<input name="label" type="button" class="button1" value="新 增"
												onclick="add();">
											<input name="label" type="button" class="button1" value="编 辑"
												onclick="edit(<c:out value='${agent.id}' />);">
											<input name="label" type="button" class="button1"
												value="更新统计"
												onclick="updateStatistic(<c:out value='${agent.id}' />);">
											<input name="label" type="button" class="button1" value="习 性"
												onclick="window.location.href='<%=path%>/agent/agentHabitList.do?thisAction=save&agentId=<c:out value="${agent.id}" />'">
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
   			 var url="../agent/agentList.do?thisAction=save";
    		 window.location.href=url;
 		}
		function edit(id){
   			 var url="../agent/agentList.do?thisAction=edit&id="+id;
    		 window.location.href=url;
 		}
 		
 		function updateStatistic(id){
 		 	 var url="../agent/agent.do?thisAction=updateStatistic&id="+id;
    		 window.location.href=url;
 		}
 		
		</script>
	</body>
</html>