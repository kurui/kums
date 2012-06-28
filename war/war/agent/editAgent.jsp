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
		<c:import url="../page/importDWR.jsp"></c:import>
		<script src="../_js/prototype/common.js" type="text/javascript"></script>
		<script src="../_js/base/FormUtil.js" type="text/javascript"></script>
	</head>
	<script type="text/javascript">
		function add(){	
			var name=document.forms[0].name.value;
			if(name==""){
				alert("请输入客户姓名")
				return false;
			}
			var thisAction =document.forms[0].thisAction.value;			   
		    document.forms[0].action="<%=path%>/agent/agent.do?thisAction="+thisAction;
		    document.forms[0].submit();
		}		
		
			
		function parseIdCard(){
			var idCard=document.forms[0].cardNo.value;
			
			if(idCard!=""&&idCard.length>14){
				agentBiz.getSexByIdCard(idCard,function(sexValue){
					setRadioByValue(document.forms[0].sex,sexValue);
				});
				
				agentBiz.getBirthStrByIdCard(idCard,function(birthday){
					document.forms[0].birthday.value=birthday;
				});
				
				agentBiz.getAreaTextByCode(idCard,function(areaText){
					document.forms[0].nativeCountry.value=areaText
				});
				
			}
		}
	</script>
	<body>
		<div id="mainContainer">
			<div id="container">
				<html:form action="/agent/agent.do" method="post">
					<table width="100%" cellpadding="0" cellspacing="0" border="0">
						<tr>
							<td width="10" height="10" class="tblt"></td>
							<td height="10" class="tbtt"></td>
							<td width="10" height="10" class="tbrt"></td>
						</tr>
						<tr>
							<td width="10" class="tbll"></td>
							<td valign="top" class="body">
								<table width="100%" cellpadding="0" cellspacing="0" border="0" class="dataList">
									<tr>
										<td class="lef">
											客户名称
										</td>
										<td style="text-align: left" colspan="3">
											<html:text property="name" name="agent" value="${agent.name}" styleClass="colorblue2 p_5" style="width:200px;"></html:text>
											|
											<html:hidden property="id" value="${agent.id}"></html:hidden>

											<html:select property="type" name="agent" styleClass="colorblue2 p_5" style="width:80px;">
												<html:option value="0">-请选择-</html:option>
												<html:option value="1">VIP客户</html:option>
												<html:option value="2">主要客户</html:option>
												<html:option value="3">普通客户</html:option>
												<html:option value="4">小客户</html:option>
												<html:option value="11">潜在客户</html:option>
											</html:select>
											|
											<html:select property="directLevelId" value="${agent.directLevel.id}" name="agent" styleClass="colorblue2 p_5" style="width:80px;">
												<html:option value="">-直销商级别-</html:option>
												<c:forEach items="${directLevelList}" var="directLevel">
													<html:option value="${directLevel.id}">
														<c:out value="${directLevel.name}" />
													</html:option>
												</c:forEach>
											</html:select>
											|
											<html:select property="loyalIndex" value="${agent.loyalIndex}" name="agent" styleClass="colorblue2 p_5" style="width:80px;">
												<html:option value="">-忠诚度-</html:option>
												<c:forEach items="${loyalIndexList}" var="dataType">
													<html:option value="${dataType.no}">
														<c:out value="${dataType.name}" />
													</html:option>
												</c:forEach>
											</html:select>
											|
											<html:select property="friendIndex" value="${agent.friendIndex}" name="agent" styleClass="colorblue2 p_5" style="width:80px;">
												<html:option value="">-亲密度-</html:option>
												<c:forEach items="${friendIndexList}" var="dataType">
													<html:option value="${dataType.no}">
														<c:out value="${dataType.name}" />
													</html:option>
												</c:forEach>
											</html:select>
											|
											<html:select property="assetIndex" value="${agent.assetIndex}" name="agent" styleClass="colorblue2 p_5" style="width:80px;">
												<html:option value="">-资本量-</html:option>
												<c:forEach items="${assetIndexList}" var="dataType">
													<html:option value="${dataType.no}">
														<c:out value="${dataType.name}" />
													</html:option>
												</c:forEach>
											</html:select>
											|
											<html:select property="specialIndex" value="${agent.specialIndex}" name="agent" styleClass="colorblue2 p_5" style="width:80px;">
												<html:option value="">-专业能力-</html:option>
												<c:forEach items="${specialIndexList}" var="dataType">
													<html:option value="${dataType.no}">
														<c:out value="${dataType.name}" />
													</html:option>
												</c:forEach>
											</html:select>
										</td>
									</tr>
									<tr>
										<td class="lef">
											<a href="#" onclick="showElement('personalBody')">个人信息</a>
										</td>
										<td style="text-align: left" colspan="3">
											<c:out value="${agent.birthday}"></c:out>
										</td>
									</tr>
									<tbody id="personalBody" style="display: ''">
										<tr>
											<td class="lef">
												证件号
											</td>
											<td style="text-align: left">
												<html:text property="cardNo" name="agent" value="${agent.cardNo}" onkeyup="parseIdCard();" styleClass="colorblue2 p_5" style="width:200px;"></html:text>
												生日:
												<html:text property="birthday" name="agent" value="${agent.birthday}" styleClass="colorblue2 p_5" style="width:80px;"></html:text>
											</td>
											<td class="lef">
												性别
											</td>
											<td style="text-align: left">
												<html:radio property="sex" name="agent" value="1" styleClass="colorblue2 p_5" style="width:20px;">男</html:radio>
												<html:radio property="sex" name="agent" value="2" styleClass="colorblue2 p_5" style="width:20px;">女</html:radio>
												婚姻:
												<html:text property="marriage" name="agent" value="${agent.marriage}" styleClass="colorblue2 p_5" style="width:80px;"></html:text>
											</td>
										</tr>
										<tr>
											<td class="lef">
												民族
											</td>
											<td style="text-align: left">
												<html:text property="people" name="agent" value="${agent.people}" styleClass="colorblue2 p_5" style="width:50px;"></html:text>
												祖籍:
												<html:text property="nativeCountry" name="agent" value="${agent.nativeCountry}" styleClass="colorblue2 p_5" style="width:100px;"></html:text>
											</td>
											<td class="lef">
												关系特征
											</td>
											<td style="text-align: left">
												<html:checkbox property="stampGroupItem" name="agent" value="1" styleClass="colorblue2 p_5" style="width:20px;">同学</html:checkbox>
												<html:checkbox property="stampGroupItem" name="agent" value="2" styleClass="colorblue2 p_5" style="width:20px;">同事</html:checkbox>
											</td>
										</tr>
									</tbody>
									<tr>
										<td class="lef">
											<a href="#" onclick="showElement('contactWayBody')">联系方式</a>
										</td>
										<td style="text-align: left" colspan="3">
											<c:out value="${agent.mobilePhone}"></c:out>
										</td>
									</tr>
									<tbody id="contactWayBody" style="display: ''">
										<tr>
											<td class="lef">
												QQ
											</td>
											<td style="text-align: left">
												<html:text property="qqCode" name="agent" value="${agent.qqCode}" styleClass="colorblue2 p_5" style="width:200px;"></html:text>
												EMail:
												<html:text property="email" name="agent" value="${agent.email}" styleClass="colorblue2 p_5" style="width:200px;"></html:text>
											</td>
											<td class="lef">
												手机号码
											</td>
											<td style="text-align: left">
												<html:text property="mobilePhone" name="agent" value="${agent.mobilePhone}" styleClass="colorblue2 p_5" style="width:200px;"></html:text>
											</td>
										<tr>
											<td class="lef">
												收货地址
											</td>
											<td style="text-align: left" colspan="3">
												<html:text property="address" name="agent" value="${agent.address}" styleClass="colorblue2 p_5" style="width:800px;"></html:text>
											</td>
										</tr>
									</tbody>
									<tr>
										<td class="lef">
											<a href="#" onclick="showElement('workInfoBody')">职业信息</a>
										</td>
										<td style="text-align: left" colspan="3">
									</tr>
									<tbody id="workInfoBody" style="display: none">
										<tr>
											<td class="lef">
												工作单位
											</td>
											<td style="text-align: left">
												<html:text property="workPlace" name="agent" value="${agent.workPlace}" styleClass="colorblue2 p_5" style="width:200px;"></html:text>
											</td>
											<td class="lef">
												职位
											</td>
											<td style="text-align: left">
												<html:text property="position" name="agent" styleClass="colorblue2 p_5" style="width:200px;"></html:text>
											</td>
										</tr>
										<tr>
											<td class="lef">
												特长
											</td>
											<td style="text-align: left">
												<html:text property="strongSuit" name="agent" styleClass="colorblue2 p_5" style="width:200px;"></html:text>
											</td>
											<td class="lef">
												语言
											</td>
											<td style="text-align: left">
												<html:text property="language" name="agent" value="${agent.language}" styleClass="colorblue2 p_5" style="width:200px;"></html:text>
											</td>
										</tr>
									</tbody>
									<tr>
										<td class="lef">
											<a href="#" onclick="showElement('creditInfoBody')">资产负债</a>
										</td>
										<td style="text-align: left" colspan="3">
									</tr>
									<tbody id="creditInfoBody" style="display: none">
										<tr>
											<td class="lef">
												资产
											</td>
											<td style="text-align: left" colspan="3">
												<html:text property="assetInfo" name="agent" value="${agent.assetInfo}" styleClass="colorblue2 p_5" style="width:800px;"></html:text>
											</td>
										</tr>
										<tr>
											<td class="lef">
												信贷
											</td>
											<td style="text-align: left" colspan="3">
												额度：
												<html:text property="creditInfo" name="agent" value="${agent.creditAmount}" styleClass="colorblue2 p_5" style="width:50px;"></html:text>
												<html:text property="creditInfo" name="agent" value="${agent.creditInfo}" styleClass="colorblue2 p_5" style="width:200px;"></html:text>
											</td>
										</tr>
										<tr>
											<td class="lef">
												健康信息
											</td>
											<td style="text-align: left" colspan="3">
												<html:text property="healthInfo" name="agent" value="${agent.healthInfo}" styleClass="colorblue2 p_5" style="width:500px;"></html:text>
											</td>
										</tr>
									</tbody>
									<tr>
										<td class="lef">
											备注
										</td>
										<td style="text-align: left" colspan="3">
											<html:text property="memo" name="agent" styleClass="colorblue2 p_5" style="width:800px;"></html:text>
										</td>
									</tr>
									<tr>
										<td class="lef">
											分管部门|客户来源
										</td>
										<td style="text-align: left">
											<html:select property="companyId" name="agent" styleClass="colorblue2 p_5" style="width:120px;">
												<c:forEach items="${companyList}" var="company">
													<html:option value="${company.id}">
														<c:out value="${company.name}" />
													</html:option>
												</c:forEach>
											</html:select>
											|
											<html:text property="knowPlace" name="agent" value="${agent.knowPlace}" styleClass="colorblue2 p_5" style="width:100px;"></html:text>
										</td>
										<td class="lef">
											状态
										</td>
										<td style="text-align: left">
											<html:select property="status" name="agent" styleClass="colorblue2 p_5" style="width:50px;">
												<html:option value="1">有效</html:option>
												<html:option value="0">无效</html:option>
											</html:select>
										</td>
									</tr>
								</table>
								<table width="100%" style="margin-top: 5px;">
									<tr>
										<td align="center" colspan="4">
											<html:hidden property="thisAction" name="agent" />
											<input name="label" type="button" class="button1" value="返 回" onclick="window.history.back();">
											<input name="label" type="button" class="button1" value="提交" onclick="add();">
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
				</html:form>
			</div>
		</div>

		<script type="text/javascript">
		window.onload=initOnload;
		
		function setStampGroup(){
			var obj=document.forms[0].stampGroupItem;
			var stampGroup="<c:out value='${agent.stampGroup}' />";
			
			if(obj!=null && stampGroup!=null && stampGroup!=""){
				setCheckBoxByValue(obj,stampGroup);
			}			 
		}
		
		function initOnload(){
			setStampGroup();
		}
		</script>
	</body>
</html>


