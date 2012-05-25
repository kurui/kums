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
		<script type="text/javascript" src="<%=path%>/_js/prototype/common.js"></script>
		<script type="text/javascript" src="<%=path%>/_js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript" src="<%=path%>/_js/base/FormUtil.js"></script>
		<script type="text/javascript"
			src="<%=path%>/_js/calendar/WdatePicker.js"></script>
		<script type="text/javascript">
	function updateCommissionData(){
		 document.forms[0].thisAction.value="updateCommissionData";
	     document.forms[0].submit();
	}	
		
	function add(){
	    document.forms[0].thisAction.value="save";
	    document.forms[0].submit();
	}	

	function edit()	{
		if(document.forms[0].selectedItems==null){
			alert("没有数据，无法修改！");
		}else if (sumCheckedBox(document.forms[0].selectedItems)<1){
	   		alert("您还没有选择数据！");
		}else if (sumCheckedBox(document.forms[0].selectedItems)>1){
	      	alert("您一次只能选择一个数据！");
		}else {
	    	document.forms[0].thisAction.value="edit";
	    	document.forms[0].submit();
	  	}
	}
	
	function del()	{	
	 if(document.forms[0].selectedItems==null){
		alert("没有数据，无法修改！");
	 }else if (sumCheckedBox(document.forms[0].selectedItems)<1){
	 	alert("您还没有选择数据！");
	 }else if(confirm("您真的要删除选择的这些数据吗？")){
	    document.forms[0].thisAction.value="delete";
	    document.forms[0].submit();
	  }
	}	
	</script>
	</head>
	<body>
		<div id="mainContainer">
			<div id="container">
				<html:form action="/finance/commissionList.do">
					<html:hidden property="thisAction" />
					<html:hidden property="lastAction" />
					<html:hidden property="intPage" />
					<html:hidden property="pageCount" />

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
									<c:param name="title2" value="佣金管理" />
									<c:param name="title3" value="佣金明细列表" />
								</c:import>

								<div class="searchBar">
									<table cellpadding="0" cellspacing="0" border="0"
										class="searchPanel">
										<tr>
											<td>
												分销商：
											</td>
											<td>
												<html:text property="agentContactWay"
													styleClass="colorblue2 p_5" style="width:120px;" />
											</td>
											<td>
												订单号：
											</td>
											<td>
												<html:text property="orderNo" styleClass="colorblue2 p_5"
													style="width:120px;" />
											</td>
											<td>
												<input type="submit" name="button" id="button" value="提交"
													class="submit greenBtn" />
												<input name="label" type="button" class="button1"
													value="计算佣金" onclick="updateCommissionData();">
											</td>
										</tr>
									</table>
									<hr />
								</div>
								<table width="100%" cellpadding="0" cellspacing="0" border="0"
									class="dataList">
									<tr>
										<th width="60">
											<div>
												&nbsp;请选择
											</div>
										</th>
										<th width="35">
											<div>
												&nbsp;序号
											</div>
										</th>
										<th>
											<div>
												分销商
											</div>
										</th>
										<th>
											<div>
												订单
											</div>
										</th>
										<th>
											<div>
												佣金
											</div>
										</th>
										<th>
											<div>
												活动
											</div>
										</th>

										<th>
											<div>
												备注
											</div>
										</th>
										<th>
											<div>
												类型
											</div>
										</th>
										<th>
											<div>
												时间
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
									<c:forEach var="commission" items="${commissionListForm.list}"
										varStatus="status">
										<tr>
											<td>
												<html:multibox property="selectedItems"
													value="${commission.id}"></html:multibox>
											</td>
											<td>
												<c:out value="${status.count}" />
											</td>
											<td>
												<c:if test="${!empty commission.crossAgent}">
													<a
														href="<%=path%>/agent/agentList.do?thisAction=view&id=<c:out value="${commission.crossAgent.id}" />">
														<c:out value="${commission.crossAgent.name}" /> </a>
												</c:if>
											</td>
											<td>
												<c:if test="${!empty commission.financeOrder}">
													<a
														href="<%=path%>/finance/financeOrderList.do?thisAction=view&id=<c:out value="${commission.financeOrder.id}" />">
														<c:out value="${commission.financeOrder.orderNo}" /> </a>
												</c:if>
											</td>
											<td>
												<c:out value="${commission.totalAmount}" />
											</td>
											<td>
												<c:if test="${!empty commission.saleEvent}">
													<a
														href="<%=path%>/market/saleEventList.do?thisAction=view&id=<c:out value="${commission.saleEvent.id}" />">
														<c:out value="${commission.saleEvent.name}" /> </a>
												</c:if>
											</td>
											<td style="text-align: left">
												<c:out value="${commission.memo}" />
											</td>
											<td>
												<c:out value="${commission.typeInfo}" />
											</td>
											<td>
												<c:out value="${commission.formatCreateTime}" />
											</td>
											<td>
												<c:out value="${commission.statusInfo}" />
											</td>
											<td>
											</td>
										</tr>
									</c:forEach>
									<tr>
										<td colspan="4">
											<div align="center">
												<font>合计</font>
											</div>
										</td>
										<td style="text-align: right;">
											<c:out value="${commissionListForm.totalValue1}" />
											&nbsp;
										</td>
										<td colspan="6"></td>
									</tr>
								</table>

								<table width="100%" style="margin-top: 5px;">
									<tr>
										<td>
											<input name="label" type="button" class="button1" value="新 增"
												onclick="add();">
											<input name="label" type="button" class="button1" value="修 改"
												onclick="edit();">
											<input name="label" type="button" class="button1" value="删 除"
												onclick="del();">
										</td>
										<td align="right">
											<div>
												共有记录&nbsp;
												<c:out value="${commissionListForm.totalRowCount}"></c:out>
												&nbsp;条&nbsp;&nbsp;&nbsp;&nbsp; [
												<a href="JavaScript:turnToPage(document.forms[0],0)">首页</a>
												|
												<a href="JavaScript:turnToPage(document.forms[0],1)">上一页</a>
												|
												<a href="JavaScript:turnToPage(document.forms[0],2)">下一页</a>
												|
												<a href="JavaScript:turnToPage(document.forms[0],3)"> 末页</a>]
												页数:
												<c:out value="${commissionListForm.intPage}" />
												/
												<c:out value="${commissionListForm.pageCount}" />
												]
											</div>
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
	</body>
</html>