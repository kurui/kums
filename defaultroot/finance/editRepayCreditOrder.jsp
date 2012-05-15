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
		<link href="../_css/reset.css" rel="stylesheet" type="text/css" />
		<link href="../_css/global.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=path%>/_js/prototype/common.js"></script>		
		<script type="text/javascript" src="<%=path%>/_js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript" src="<%=path%>/_js/base/FormUtil.js"></script>
		<script type="text/javascript" src="<%=path%>/_js/calendar/WdatePicker.js"></script>
		<c:import url="../page/importDWR.jsp"></c:import>
	</head>
	<body>
		<c:set var="title1" value="财务管理" />
		<c:set var="title2" value="出纳管理" />
		<c:choose>
			<c:when test="${financeOrder.tranType==1011}">
				<c:set var="title3" value="债务还款入账" />
			</c:when>
			<c:when test="${financeOrder.tranType==2011}">
				<c:set var="title3" value="债权收款入账" />
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
									<c:if test="${!empty financeOrder.agent}">
										<tr>
											<td class="lef">
												客户(
												<c:out value="${financeOrder.agent.agentNo}"></c:out>
												)
											</td>
											<td style="text-align: left">
												<c:out value="${financeOrder.agent.name}"></c:out>
											</td>
										</tr>
									</c:if>
									<logic:equal value="1011" property="tranType" name="financeOrder">
										<tr>
											<td class="lef">
												还款账号
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
									<logic:equal value="2011" property="tranType" name="financeOrder">
										<tr>
											<td class="lef">
												收款账号
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
												onfocus="WdatePicker({startDate:'%y-%M-%D',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" />
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
										<td class="lef">
											是否结束
										</td>
										<td style="text-align: left">
											<html:radio property="finishedStatus" value="10" name="financeOrder" styleClass="colorblue2 p_5">还有余款,下次支付</html:radio>
											<html:radio property="finishedStatus" value="11" name="financeOrder" styleClass="colorblue2 p_5">本次将全部付清</html:radio>

										</td>
									</tr>
									<tr>
										<td colspan="2">
											<html:hidden property="id" value="${financeOrder.id}" name="financeOrder"></html:hidden>
											<html:hidden property="tranType" value="${financeOrder.tranType}" name="financeOrder"></html:hidden>
											<input name="label" id="submitCreateButton" type="button" class="button1" value="确 定" onclick="editRepayCreditOrder();" onkeypress="keypressCreateOrder(event);">
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
		function editRepayCreditOrder(){		
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
         	 
         	// window.close();           
		}  
		</script>
	</body>
</html>
