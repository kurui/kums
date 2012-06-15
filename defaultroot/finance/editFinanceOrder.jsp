<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
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
		<c:import url="../page/importDWR.jsp"></c:import>
		<script type="text/javascript" src="<%=path%>/_js/prototype/common.js"></script>
		<script type="text/javascript" src="<%=path%>/_js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript" src="<%=path%>/_js/base/FormUtil.js"></script>
		<script type="text/javascript" src="<%=path%>/_js/calendar/WdatePicker.js"></script>
	</head>
	<body>
		<c:import url="../page/mainTitle.jsp" charEncoding="UTF-8">
			<c:param name="title1" value="财务管理" />
			<c:param name="title2" value="编辑订单" />
		</c:import>
		<html:form action="finance/financeOrder.do" method="post">
			<html:hidden property="thisAction" name="financeOrder"></html:hidden>
			<table cellpadding="0" cellspacing="0" border="0" class="dataList2">
				<tr>
					<th width="80px">
						科目
					</th>
					<th width="80px">
						往来单位/客户
					</th>
					<th>
						平台/交易地点
					</th>
					<th>
						记账单位
					</th>
					<th>
						账单号
					</th>
					<th>
						手续费
					</th>
					<th>
						业务时间
					</th>
					<th>
						备注
					</th>
					<th>
						状态
					</th>
				</tr>
				<c:forEach var="order" items="${financeOrderList}" varStatus="status">
					<tr>
						<td align="center">
							<input type="hidden" name="financeOrderIds" value="<c:out value='${order.id}'/>" />
							<html:select property="tranTypes" name="financeOrder" value="${order.tranType}" styleClass="colorblue2 p_5" style="width:120px;">
								<html:option value="0">-请选择-</html:option>
								<c:forEach items="${tranTypeList}" var="dataType">
									<html:option value="${dataType.no}">
										<c:out value="${dataType.name}" />
									</html:option>
								</c:forEach>
							</html:select>
						</td>
						<td align="center">
							<c:if test="${!empty order.cussentCompany}">
								<c:out value="${order.cussentCompany.shortName}" />
							</c:if>
							<c:if test="${!empty order.agent}">
								&nbsp;
								<c:out value="${order.agent.name}" />
							</c:if>
						</td>
						<td>
							<html:select property="platformIds" name="financeOrder" value="${order.platform.id}" styleClass="colorblue2 p_5" style="width:120px;">
								<html:option value="0">-请选择-</html:option>
								<c:forEach items="${platformList}" var="platform">
									<html:option value="${platform.id}">
										<c:out value="${platform.name}" />
									</html:option>
								</c:forEach>
							</html:select>
						</td>
						<td>
							<html:select property="companyIds" name="financeOrder" value="${order.company.id}" styleClass="colorblue2 p_5" style="width:150px;">
								<html:option value="0">-请选择-</html:option>
								<c:forEach items="${groupCompanyList}" var="company">
									<html:option value="${company.id}">
										<c:out value="${company.name}" />
									</html:option>
								</c:forEach>
							</html:select>
						</td>
						<td>
							<html:text property="outOrderNos" name="financeOrder" value="${order.outOrderNo}" styleId="outOrderNo<c:out value='${status.count}'/>" styleClass="colorblue2 p_5" style="width:160px;" />
						</td>
						<td>
							<html:text property="handlingCharges" value="${order.handlingCharge}" name="financeOrder" styleId="handlingCharge<c:out value='${status.count}'/>" styleClass="colorblue2 p_5"
								style="width:50px;" />
						</td>
						<td>
							<html:text property="businessDates" value="${order.businessDate}" styleId="entryOrderDate<c:out value='${status.count}'/>"
								onfocus="WdatePicker({startDate:'%y-%M-%D',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" styleClass="colorblue2 p_5" style="width: 140px;" />
						</td>
						<td>
							<html:text property="memo" value="${order.memo}" name="financeOrder" styleId="memo<c:out value='${status.count}'/>" styleClass="colorblue2 p_5" style="width:200px;" />
						</td>
						<td>
							<font style="color: red"><c:out value="${order.statusText}"></c:out> </font>
						</td>
					</tr>
					<tr id="payId<c:out value='${status.count}'/>">
						<td colspan="10">
							<table width="100%" bgcolor="#CCCCCC" cellpadding="0" cellspacing="0" border="0" class="dataList">
								<c:forEach items="${statementList}" var="statement" varStatus="sstatus">
									<c:if test="${statement.orderId==order.id}">
										<tr>
											<td colspan="3"></td>
											<td>
												<c:out value='${sstatus.count}' />
											</td>
											<td width="120">
												<a href="<%=path%>/finance/statementList.do?thisAction=view&id=<c:out value="${statement.id}" />"> <c:out value="${statement.statementNo}" /> </a>
											</td>
											<td width="80">
												<c:if test="${statement.type==1}">
													<c:out value="${statement.toAccount.name}" />
												</c:if>
												<c:if test="${statement.type==2}">
													<c:out value="${statement.fromAccount.name}" />
												</c:if>
											</td>
											<td>
												<c:out value="${statement.orderSubtypeText}" />
											</td>

											<td>
												<c:out value="${statement.totalAmount}" />
											</td>
											<td>
												<c:out value="${statement.sysUser.userName}" />
											</td>
											<td>
												<c:out value="${statement.formatStatementTime}" />
											</td>
											<td>
												<c:out value="${statement.statusInfo}" />
											</td>
											<td>
												<c:out value="${statement.memo}" />
											</td>
											<td id="<c:out value='${statement.orderId}'/>">
												<c:check code="sb50">
													<c:if test="${statement.status!=8}">
														<a href="#" onclick="editStatement('<c:out value="${statement.id}"/>')">修改</a>
													</c:if>
												</c:check>
											</td>
										</tr>
									</c:if>
								</c:forEach>
							</table>
						</td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="5" align="right">
						<input name="label" type="button" class="button1" value="返 回" onclick="window.history.back();">
						<input name="label" type="button" id="submitCreateButton" class="button1" value="保 存" onclick="updateOrder();">

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
		<script type="text/javascript">
		function updateOrder(){
			if(checkUpdate()){
				if(setSubmitButtonDisable('submitCreateButton')){   
				//var thisAction =document.forms[0].thisAction.value;		
		    	document.forms[0].action="../finance/financeOrder.do?thisAction=updateOrder";		    	
		    	trim(document.forms[0]);		    	
            	document.forms[0].submit();  
         	 }         
			}	
		}		  

  function editStatement(id)  {
    var url="<%=path%>/finance/statementList.do?thisAction=edit&id="+id;
    openWindow(400,340,url);  
  }

  function editOrderMemo(id)  {
	var url="<%=path%>/finance/financeOrderList.do?thisAction=editOrderMemo&id="+id;
	openWindow(400,340,url);  
  }
  
              
         function checkUpdate(){ 
              var handlingCharges=$("input[name='handlingCharges']");   
               if(checkNumArray(handlingCharges,"手续费必须是数字!")==false){
                  return false;
              }           
              return true;  
         }
         
	 var displayFlag=false;	 
	 function displayStatement(id)  {
    	var _tr=document.getElementById(id);
    	if(_tr)    {
	      	if(displayFlag){
	      		_tr.style.display="";
	      	}else{
        		_tr.style.display="none";
      			displayFlag=!displayFlag;
      		}
    	} 
  	}
		</script>
	</body>
</html>
