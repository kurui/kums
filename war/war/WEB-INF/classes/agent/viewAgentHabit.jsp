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
				<html:form action="/agent/agentHabitList.do">
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
									<c:param name="title2" value="查看客户习性" />
								</c:import>
								<hr>
								<table width="100%" cellpadding="0" cellspacing="0" border="0"
									class="dataList">
									<tr>
										<td class="lef">
											<c:out value="${agentHabit.agent.agentNo}" />
										</td>
										<td style="text-align: left">
											<c:out value="${agentHabit.agent.name}" />
											|
											<c:out value="${agentHabit.agent.typeInfo}" />
										</td>
										<td class="lef">
										</td>
										<td style="text-align: left">
										</td>
									</tr>
									<tr>
										<td class="lef">
											<a href="#" onclick="showElement('diningInfoBody')">餐饮信息</a>
										</td>
										<td style="text-align: left">
										</td>
									</tr>
									<tbody id="diningInfoBody" style="display: ''">
										<tr>
											<td class="lef">
												饮料
											</td>
											<td style="text-align: left">
												<c:out value="${agentHabit.drink}"></c:out>
											</td>
											<td class="lef">
												食物
											</td>
											<td style="text-align: left">
												<c:out value="${agentHabit.food}"></c:out>
											</td>
										</tr>
									</tbody>

									<tr>
										<td class="lef">
											<a href="#" onclick="showElement('diningInfoBody')">动物/植物</a>
										</td>
										<td style="text-align: left">
										</td>
									</tr>
									<tbody id="diningInfoBody" style="display: ''">
										<tr>
											<td class="lef">
												喜欢的花
											</td>
											<td style="text-align: left">
												<c:out value="${agentHabit.flower}"></c:out>
											</td>
											<td class="lef">
											</td>
											<td style="text-align: left">
											</td>
										</tr>
										<tr>
											<td class="lef">
												养过的动物
											</td>
											<td style="text-align: left">
												<c:out value="${agentHabit.breedAnimal}"></c:out>
											</td>
											<td class="lef">
												害怕的动物
											</td>
											<td style="text-align: left">
												<c:out value="${agentHabit.scaredAnimal}"></c:out>
											</td>
										</tr>
									</tbody>

									<tr>
										<td class="lef">
											<a href="#" onclick="showElement('funInfoBody')">娱乐/意识形态</a>
										</td>
										<td style="text-align: left">
										</td>
									</tr>
									<tbody id="funInfoBody" style="display: ''">
										<tr>
											<td class="lef">
												喜欢的影视
											</td>
											<td style="text-align: left">
												<c:out value="${agentHabit.filmType}"></c:out>
											</td>
											<td class="lef">
												阅读
											</td>
											<td style="text-align: left">
												<c:out value="${agentHabit.reading}"></c:out>
											</td>
										</tr>
										<tr>
											<td class="lef">
												喜欢的运动
											</td>
											<td style="text-align: left">
												<c:out value="${agentHabit.sports}"></c:out>
											</td>
											<td class="lef">
												宗教/政治倾向
											</td>
											<td style="text-align: left">
												<c:out value="${agentHabit.religion}"></c:out>
											</td>
										</tr>
									</tbody>
									<tr>
										<td class="lef">
											<a href="#" onclick="showElement('travelInfoBody')">旅行信息</a>
										</td>
										<td style="text-align: left">
										</td>
									</tr>
									<tbody id="travelInfoBody" style="display: ''">
										<tr>
											<td class="lef">
												乘机位置
											</td>
											<td style="text-align: left">
												<c:out value="${agentHabit.airplaneSeatInfo}"></c:out>
											</td>
											<td class="lef">
												喜欢的地方
											</td>
											<td style="text-align: left">
												<c:out value="${agentHabit.travelAppoint}"></c:out>
											</td>
										</tr>
									</tbody>
									<tr>
										<td class="lef">
											<a href="#" onclick="showElement('sexInfoBody')">性</a>
										</td>
										<td style="text-align: left">
										</td>
									</tr>
									<tbody id="funInfoBody" style="display: ''">
										<tr>
											<td class="lef">
												喜欢的异性
											</td>
											<td style="text-align: left">
												<c:out value="${agentHabit.oppositeSex}"></c:out>
											</td>
											<td class="lef">
												对同性恋的态度
											</td>
											<td style="text-align: left">
												<c:out value="${agentHabit.homoSex}"></c:out>
											</td>
										</tr>
									</tbody>
									<tr>
										<td class="lef">
											最大的愿望
										</td>
										<td style="text-align: left">
											<c:out value="${agentHabit.bigWish}"></c:out>
										</td>
										<td class="lef">
										</td>
										<td style="text-align: left">
										</td>
									</tr>
									<tr>
										<td class="lef">
											其它
										</td>
										<td style="text-align: left" colspan="3">
											<c:out value="${agentHabit.memo}"></c:out>
										</td>
									</tr>
									<tr>
										<td class="lef">
											更新时间
										</td>
										<td style="text-align: left">
											<c:out value="${agentHabit.formatUpdateTime}" />
										</td>
										<td class="lef">
											状态
										</td>
										<td style="text-align: left">
											<c:out value="${agentHabit.statusInfo}" />
										</td>
									</tr>
								</table>
								<table width="100%" style="margin-top: 5px;">
									<tr>
										<td align="center">
											<input name="label" type="button" class="button1" value="返 回"
												onclick="window.history.back();">
											<input name="label" type="button" class="button1" value="编 辑"
												onclick="edit(<c:out value='${agentHabit.id}' />);">

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
   			 var url="../agent/agentHabitList.do?thisAction=edit&id="+id;
    		 window.location.href=url;
 		}
		</script>
	</body>
</html>