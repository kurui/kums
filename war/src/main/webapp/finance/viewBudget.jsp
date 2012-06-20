<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%	String path = request.getContextPath();
%>
<html>
	<head>
		<title>main</title>
		<link href="../_css/reset.css" rel="stylesheet" type="text/css" />
		<link href="../_css/global.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=path%>/_js/prototype/common.js"></script>
		<script type="text/javascript" src="<%=path%>/_js/base/FormUtil.js"></script>
		<script type="text/javascript">
		function edit(){
		    document.forms[0].action="<%=path%>/finance/budgetList.do?thisAction=edit";
		    document.forms[0].submit();
		}
		
		function addBudgetOrder(){
			var budgetId=<c:out value="${budget.id}" />
	    	document.forms[0].action="<%=path%>/finance/budgetOrderList.do?thisAction=save&budgetId="+budgetId;
	    	document.forms[0].submit();
		}	
		
		function stopBudgetOrder(id){	
			var budgetId=<c:out value="${budget.id}" />		
	    	document.forms[1].action="<%=path%>/finance/budgetOrderList.do?thisAction=stop&budgetId="+budgetId+"&id="+id;
	    	document.forms[1].submit();
		}
		
		function affirmSettlementBudgetOrder(id){		
			var budgetId=<c:out value="${budget.id}" />	
	    	document.forms[1].action="<%=path%>/finance/budgetOrderList.do?thisAction=affirmSettlement&budgetId="+budgetId+"&id="+id;
	    	document.forms[1].submit();
		}
		function editBudgetOrder(id){	
		    var budgetId=<c:out value="${budget.id}" />			
	    	document.forms[1].action="<%=path%>/finance/budgetOrderList.do?thisAction=edit&id="+id;
	    	document.forms[1].submit();
		}
		
		function deleteBudgetOrder(id)	{
		    var budgetId=<c:out value="${budget.id}" />						
		  if(confirm("您真的要删除吗？")){
		   document.forms[1].action="<%=path%>/finance/budgetOrderList.do?thisAction=delete&budgetId="+budgetId+"&id="+id;
	    	document.forms[1].submit();
		  }
		}
	</script>
	</head>
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
								<c:param name="title1" value="财务管理" />
								<c:param name="title2" value="预算管理" />
								<c:param name="title3" value="查看预算案" />
							</c:import>
							<hr>
							<table width="100%" cellpadding="0" cellspacing="0" border="0"
								class="dataList">
								<tr>
									<td class="lef">
										预算案
									</td>
									<td style="text-align: left">
										<c:out value="${budget.no}" />
										|
										<c:out value="${budget.name}" />

									</td>
									<td class="lef">
										负责人
									</td>
									<td style="text-align: left">
										<c:out value="${budget.companyNo}" />
										|
										<c:out value="${budget.entryOperator}" />
									</td>
								</tr>
								<tr>
									<td class="lef">
										启动时间
									</td>
									<td style="text-align: left">
										<c:out value="${budget.beginDate}" />
									</td>
									<td class="lef">
										完成时间
									</td>
									<td style="text-align: left">
										<c:out value="${budget.endDate}" />
									</td>
								</tr>
								<tr>
									<td class="lef">
										类型
									</td>
									<td style="text-align: left">
										<c:out value="${budget.typeInfo}" />
									</td>
									<td class="lef">
										状态
									</td>
									<td style="text-align: left">
										<c:out value="${budget.statusInfo}" />
									</td>
								</tr>

							</table>
							<table width="100%" style="margin-top: 5px;">
								<tr>
									<td align="center">
										<html:form action="/finance/budgetList.do">
											<html:hidden property="selectedItems" value="${budget.id}" />
											<input name="label" type="button" class="button1" value="返 回"
												onclick="window.history.back();">
											<input name="label" type="button" class="button1"
												value="新增明细" onclick="addBudgetOrder()" />
											<input name="label" type="button" class="button1" value="修 改"
												onclick="edit();" />
										</html:form>
									</td>

								</tr>
							</table>

							<html:form action="/finance/budgetOrderList.do">
								<table width="100%" cellpadding="0" cellspacing="0" border="0"
									class="dataList">
									<tr>
										<th width="35">
											<div>
												&nbsp;序号
											</div>
										</th>
										<th>
											<div>
												项目
											</div>
										</th>
										<th>
											<div>
												预算金额
											</div>
										</th>
										<th>
											<div>
												决算金额
											</div>
										</th>
										<th>
											<div>
												预算日期
											</div>
										</th>
										<th>
											<div>
												决算日期
											</div>
										</th>
										<th>
											<div>
												备注
											</div>
										</th>
										<th>
											<div>
												状态
											</div>
										</th>
										<th>
											<div>
												操作
											</div>
										</th>
									</tr>
									<c:forEach var="budgetOrder" items="${budgetOrderList}"
										varStatus="status">
										<tr>
											<td>
												<c:out value="${status.count}" />
											</td>
											<td>
												<c:out value="${budgetOrder.name}" />
											</td>
											<td>
												<c:out value="${budgetOrder.budgetAmount}" />
											</td>

											<td>
												<c:out value="${budgetOrder.statementAmount}" />
											</td>
											<td>
												<c:out value="${budgetOrder.budgetDate}" />
											</td>
											<td>
												<c:out value="${budgetOrder.statementDate}" />
											</td>
											<td style="text-align: left">
												<c:out value="${budgetOrder.memo}" />
											</td>
											<td>
												<c:out value="${budgetOrder.statusInfo}" />
											</td>
											<td>
												<c:if test="${budgetOrder.status==1}" >
												<a href="#"
													onclick="stopBudgetOrder('<c:out value="${budgetOrder.id}" />')">中止</a>
												
												<a href="#"
													onclick="affirmSettlementBudgetOrder('<c:out value="${budgetOrder.id}" />')">决算</a>
												</c:if>
												
												<c:if test="${budgetOrder.status!=0}" >
												<a href="#"
													onclick="editBudgetOrder('<c:out value="${budgetOrder.id}" />')">编辑</a>
													
												<a href="#"
													onclick="deleteBudgetOrder('<c:out value="${budgetOrder.id}" />')">删除</a>
													</c:if>

											</td>
										</tr>
									</c:forEach>
									<tr>
										<td colspan="2">
											<div align="center">
												<font>合计</font>
											</div>
										</td>
										<td style="text-align: right;">
											<c:out value="${budget.totalBudgetAmount}" />
											&nbsp;
										</td>
										<td colspan="6"></td>
									</tr>
								</table>
							</html:form>
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