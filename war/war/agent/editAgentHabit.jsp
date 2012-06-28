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
		<script src="<%=path%>/_js/base/FormUtil.js" type="text/javascript"></script>
	</head>
	<script type="text/javascript">	
		function add(){	
			var thisAction =document.forms[0].thisAction.value;			   
		    document.forms[0].action="<%=path%>/agent/agentHabit.do?thisAction="+thisAction;
		    document.forms[0].submit();
		}
	</script>
	<body>
		<c:import url="../page/mainTitle.jsp" charEncoding="UTF-8">
			<c:param name="title1" value="客户管理" />
			<c:param name="title2" value="编辑客户习性" />
		</c:import>
		<html:form action="/agent/agentHabit.do" method="post">
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
											<c:out value="${agentHabit.agent.agentNo}"></c:out>
											|
											<c:out value="${agentHabit.agent.name}"></c:out>
											<html:hidden property="agentId"
												value="${agentHabit.agent.id}" name="agentHabit" />
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
										<td class="lef">
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
												<html:text property="drink" name="agentHabit"
													value="${agentHabit.drink}" styleClass="colorblue2 p_5"
													style="width:200px;"></html:text>
											</td>
											<td class="lef">
												食物
											</td>
											<td style="text-align: left">
												<html:text property="food" name="agentHabit"
													value="${agentHabit.food}" styleClass="colorblue2 p_5"
													style="width:200px;"></html:text>
											</td>
										</tr>
									</tbody>
									<tr>
										<td class="lef">
											<a href="#" onclick="showElement('diningInfoBody')">动物/植物</a>
										</td>
										<td style="text-align: left">
										</td>
										<td class="lef">
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
												<html:text property="flower" name="agentHabit"
													value="${agentHabit.flower}" styleClass="colorblue2 p_5"
													style="width:200px;"></html:text>
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
												<html:text property="breedAnimal" name="agentHabit"
													value="${agentHabit.breedAnimal}"
													styleClass="colorblue2 p_5" style="width:200px;"></html:text>
											</td>
											<td class="lef">
												害怕的动物
											</td>
											<td style="text-align: left">
												<html:text property="scaredAnimal" name="agentHabit"
													value="${agentHabit.scaredAnimal}"
													styleClass="colorblue2 p_5" style="width:200px;"></html:text>
											</td>
										</tr>
									</tbody>
									<tr>
										<td class="lef">
											<a href="#" onclick="showElement('funInfoBody')">娱乐</a>
										</td>
										<td style="text-align: left">
											<html:text property="amuse" name="agentHabit"
												value="${agentHabit.amuse}" styleClass="colorblue2 p_5"
												style="width:200px;"></html:text>
										</td>
										<td class="lef">
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
												<html:text property="filmType" name="agentHabit"
													value="${agentHabit.filmType}" styleClass="colorblue2 p_5"
													style="width:200px;"></html:text>
											</td>
											<td class="lef">
												阅读
											</td>
											<td style="text-align: left">
												<html:text property="reading" name="agentHabit"
													value="${agentHabit.reading}" styleClass="colorblue2 p_5"
													style="width:200px;"></html:text>
											</td>
										</tr>
										<tr>
											<td class="lef">
												喜欢的运动
											</td>
											<td style="text-align: left">
												<html:text property="sports" name="agentHabit"
													value="${agentHabit.sports}" styleClass="colorblue2 p_5"
													style="width:200px;"></html:text>
											</td>
											<td class="lef">
												宗教/政治倾向
											</td>
											<td style="text-align: left">
												<html:text property="religion" name="agentHabit"
													value="${agentHabit.religion}" styleClass="colorblue2 p_5"
													style="width:200px;"></html:text>
											</td>
										</tr>
									</tbody>
									<tr>
										<td class="lef">
											<a href="#" onclick="showElement('travelInfoBody')">旅行信息</a>
										</td>
										<td style="text-align: left">
										</td>
										<td class="lef">
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
												<html:select property="airplaneSeat"
													value="${agentHabit.airplaneSeat}" name="agentHabit"
													styleClass="colorblue2 p_5" style="width:100px;">
													<html:option value="0">-请选择-</html:option>
													<html:option value="1">靠过道</html:option>
													<html:option value="2">中间</html:option>
													<html:option value="3">靠窗户</html:option>
												</html:select>
											</td>
											<td class="lef">
												喜欢的地方
											</td>
											<td style="text-align: left">
												<html:text property="travelAppoint" name="agentHabit"
													value="${agentHabit.travelAppoint}"
													styleClass="colorblue2 p_5" style="width:200px;"></html:text>
											</td>
										</tr>
									</tbody>
									<tr>
										<td class="lef">
											<a href="#" onclick="showElement('sexInfoBody')">性</a>
										</td>
										<td style="text-align: left">
										</td>
										<td class="lef">
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
												<html:text property="oppositeSex" name="agentHabit"
													value="${agentHabit.oppositeSex}"
													styleClass="colorblue2 p_5" style="width:200px;"></html:text>
											</td>
											<td class="lef">
												对同性恋的态度
											</td>
											<td style="text-align: left">
												<html:text property="homoSex" name="agentHabit"
													value="${agentHabit.homoSex}" styleClass="colorblue2 p_5"
													style="width:200px;"></html:text>
											</td>
										</tr>
									</tbody>
									<tr>
										<td class="lef">
											最大的愿望
										</td>
										<td style="text-align: left">
											<html:text property="bigWish" name="agentHabit"
												value="${agentHabit.bigWish}" styleClass="colorblue2 p_5"
												style="width:200px;"></html:text>
										</td>
										<td class="lef">
										</td>
										<td style="text-align: left">
										</td>
									</tr>
									<tr>
										<td class="lef">
											状态
										</td>
										<td style="text-align: left">
											<html:select property="status" value="${agentHabit.status}"
												name="agentHabit" styleClass="colorblue2 p_5"
												style="width:50px;">
												<html:option value="1">有效</html:option>
												<html:option value="0">无效</html:option>
											</html:select>
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
											<html:textarea property="memo" name="agentHabit"
												value="${agentHabit.memo}" styleClass="colorblue2 p_5"
												style="width:500px;height:50px"></html:textarea>
										</td>
									</tr>
								</table>
								<table width="100%" style="margin-top: 5px;">
									<tr align="center">
										<td>
											<html:hidden property="id" name="agentHabit"></html:hidden>
											<html:hidden property="thisAction" name="agentHabit" />
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