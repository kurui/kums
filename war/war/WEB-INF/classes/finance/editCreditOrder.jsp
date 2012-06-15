<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>

<%
	String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<link href="<%=path%>/_css/reset.css" rel="stylesheet" type="text/css" />
		<link href="<%=path%>/_css/global.css" rel="stylesheet" type="text/css" />
		<c:import url="../page/importDWR.jsp"></c:import>
		<script type="text/javascript" src="<%=path%>/_js/prototype/common.js"></script>
		<script type="text/javascript" src="<%=path%>/_js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript" src="<%=path%>/_js/base/FormUtil.js"></script>
		<script type="text/javascript" src="<%=path%>/_js/calendar/WdatePicker.js"></script>

		<script type="text/javascript">
		function  isExistEmail(destArray,email) {
         	var flag = false;
         	for (var i = 0; i < destArray.length; i++) {
            	 if (email==destArray[i]) {
                	 flag = true;
             	}
         	}
         	return flag;
     	}
		
		function addAgentId(agentId){	
			//alert("agentId:"+agentId);		
			//document.forms[0].agentId.value=agentId;	
			if(agentId!=null){
				document.forms[0].agentId.value=agentId;	
				agentBiz.getAgentById(agentId,function(agentObj){
					if(agentObj!=null){
						document.forms[0].agentNo.value=agentObj.agentNo+"|"+agentObj.name;	
					}					
				});
			}
		}		
			
		function selectAgent(){	
			openWindow(800,600,'../agent/agentList.do?thisAction=selectFinanceAgent');		
		}
		
		function addAgentSpeed(){	
			openWindow(800,600,'../agent/agentList.do?thisAction=saveSpeed');		
		}
		</script>
	</head>
	<body>
		<c:set var="title1" value="财务管理" />
		<c:set var="title2" value="出纳管理" />
		<c:choose>
			<c:when test="${financeOrder.tranType==1001}">
				<c:set var="title3" value="借款入账" />
			</c:when>
			<c:when test="${financeOrder.tranType==2001}">
				<c:set var="title3" value="贷款入账" />
			</c:when>
		</c:choose>
		<div id="mainContainer">
			<div id="container">
				<html:form action="/finance/financeOrder.do">
					<html:hidden property="id" value="${financeOrder.id}"></html:hidden>
					<html:hidden property="thisAction" name="financeOrder" />
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
									<c:param name="title1" value="${title1}" />
									<c:param name="title2" value="${title2}" />
									<c:param name="title3" value="${title3}" />
								</c:import>
								<table width="100%" cellpadding="0" cellspacing="0" border="0" class="dataList">
									<tr>
										<td class="lef">
											客户
										</td>
										<td style="text-align: left">
											<html:hidden property="agentId" name="financeOrder" />
											<html:text property="agentNo" name="financeOrder" styleClass="colorblue2 p_5" readonly="true" />

											<input name="label" type="button" class="button1" value="选择客户" onclick="selectAgent();">
											<input name="label" type="button" class="button1" value="快速建档" onclick="addAgentSpeed();">
										</td>
									</tr>
									<logic:equal value="1001" property="tranType" name="financeOrder">
										<tr>
											<td class="lef">
												借款账号
											</td>
											<td style="text-align: left">
												<html:select property="accountId" name="financeOrder" styleClass="colorblue2 p_5" styleId="account_Id" style="width:200px;">
													<c:forEach items="${inAccountList}" var="account">
														<html:option value="${account.id}">
															<c:out value="${account.showName}" />
														</html:option>
													</c:forEach>
												</html:select>
											</td>
										</tr>
									</logic:equal>
									<logic:equal value="2001" property="tranType" name="financeOrder">
										<tr>
											<td class="lef">
												贷款账号
											</td>
											<td style="text-align: left">
												<html:select property="accountId" name="financeOrder" styleClass="colorblue2 p_5" styleId="account_Id" style="width:200px;">
													<c:forEach items="${outAccountList}" var="account">
														<html:option value="${account.id}">
															<c:out value="${account.showName}" />
														</html:option>
													</c:forEach>
												</html:select>
											</td>
										</tr>
									</logic:equal>
									<tr>
										<td class="lef">
											金额
										</td>
										<td style="text-align: left">
											<html:text property="totalAmount" name="financeOrder" styleClass="colorblue2 p_5" style="width:200px;" />
										</td>
									</tr>
									<tr>
										<td class="lef">
											手续费
										</td>
										<td style="text-align: left">
											<html:text property="handlingCharge" name="financeOrder" styleClass="colorblue2 p_5" style="width:200px;" />
										</td>
									</tr>
									<tr>
										<td class="lef">
											入账日期
										</td>
										<td style="text-align: left">
											<html:text property="businessDate" value="${financeOrder.businessDate}" name="financeOrder" styleClass="colorblue2 p_5" style="width:200px;"
												onfocus="WdatePicker({startDate:'%y-%M-%D',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" readonly="true" />
										</td>
									</tr>
									<tr>
										<td class="lef">
											到期日
										</td>
										<td style="text-align: left">
											<html:text property="maturityDate" value="${financeOrder.maturityDate}" name="financeOrder" styleClass="colorblue2 p_5" style="width:200px;"
												onfocus="WdatePicker({startDate:'%y-%M-%D',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" readonly="true" />
										</td>
									</tr>
									<tr>
										<td class="lef">
											提前预警
										</td>
										<td style="text-align: left">
											<html:text property="warningDays" value="${financeOrder.warningDays}" name="financeOrder" styleClass="colorblue2 p_5" style="width:200px;" />
											天
										</td>
									</tr>
									<tr>
										<td class="lef">
											说明
										</td>
										<td style="text-align: left">
											<html:text property="memo" name="financeOrder" styleClass="colorblue2 p_5" style="width:200px;" onkeypress="keypressCreateOrder();" />
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<html:hidden property="id" value="${financeOrder.id}" name="financeOrder"></html:hidden>
											<html:hidden property="tranType" value="${financeOrder.tranType}" name="financeOrder"></html:hidden>
											<input name="label" id="submitCreateButton" type="button" class="button1" value="创 建" onclick="editCreditOrder();" onkeypress="keypressCreateOrder(event);">
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
		function editCreditOrder(){		
			var agentId=document.forms[0].agentId.value;
			//alert(agentId);
			//alert(agentId>0);
			if((agentId=="")||(agentId<1)){
		   		alert("客户不能为空");
		   		return false;
			} 

			var totalAmount = document.forms[0].totalAmount.value.trim();
			totalAmount=totalAmount.replace(/\，/g,""); //去除 ，
			if(!isNum(totalAmount)||totalAmount==""){
				alert("请正确填写金额!");
				return false;
		 	}
		 	 
			if(setSubmitButtonDisable('submitCreateButton')){   
				var thisAction =document.forms[0].thisAction.value;		
		    	document.forms[0].action="../finance/financeOrder.do?thisAction="+thisAction;
		    	trim(document.forms[0]);
            	document.forms[0].submit();   
         	 }             
		}  
		</script>
	</body>
</html>
