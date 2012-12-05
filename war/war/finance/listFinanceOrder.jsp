<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/fmt.tld" prefix="fmt"%>
<%
	String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>订单管理</title>
		<link href="../_css/reset.css" rel="stylesheet" type="text/css" />
		<link href="../_css/global.css" rel="stylesheet" type="text/css" />
		<c:import url="../page/importDWR.jsp"></c:import>
<script type="text/javascript" src="<%=path%>/_js/prototype/common.js"></script>
<script type="text/javascript" src="<%=path%>/_js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="<%=path%>/_js/base/FormUtil.js"></script>
<script type="text/javascript" src="<%=path%>/_js/calendar/WdatePicker.js"></script>

		<script>
    function selectRecent(){
        var tempDay="<c:out value='${param.recentlyDay}'/>";
        if(tempDay==null||tempDay==""){
        	tempDay="1";
        }
       	var ifRecentlyObj=document.getElementById("ifRecentlyObj");
      	if(ifRecentlyObj.checked){
      		ifRecentlyObj.Checked=false;
      		ifRecentlyObj.value="0";      		
      		document.getElementById("recentlyDayId").value=tempDay;
      	}else{
      		ifRecentlyObj.Checked=true;
      		ifRecentlyObj.value="1";
      		document.getElementById("recentlyDayId").value="";
      	}      	
      }
      
      	$(function() {		
      	   var tempDay="<c:out value='${ulf.recentlyDay}'/>";
        	if(tempDay==null||tempDay==""){
        		tempDay="1";
        	}
        	
		  var recentlyDayValue=$("#recentlyDayId").val();		  
		  
		  if(recentlyDayValue==0){
		   $("#recentlyDayId").val('');
		   $("#ifRecentlyObj").attr("checked","");
		  }
		  else
		  {
		    $("#recentlyDayId").val(tempDay);
		  }
		});
		
		function goMyIntPage(form)
		{
		  
		  var _key;
	     document.onkeyup = function(e){
		if (e == null) { // ie
			_key = event.keyCode;
		} else { // firefox
			_key = e.which;
		} 
 
		if(_key == 13){
		   if(form.myIntPage.value>0)
		     form.myIntPage.value=form.myIntPage.value-1
		  form.intPage.value=form.myIntPage.value;		  
		  turnToPage(document.forms[0],2);
		}
	};
		  
		 
		}
		  
      </script>
		<style type="text/css">
.dataList th {
	padding: 0;
	margin: 0;
	text-align: center;
}

.dataList td {
	text-align: center;
	border: 1px solid #dedfe1;
	font-size: 12px;
	empty-cells: show;
	padding: 0;
	margin: 0;
	line-height: 22px;
}

.dataList .operationArea a {
	color: #005c9c;
	text-decoration: underline;
}

.wordWrap {
	word-wrap: break-word;
	word-break: break-all;
	overflow: hidden;
	-moz-binding: url('../_css/wordwrap.xml#wordwrap');
}
</style>
	</head>
	<body>
		<c:choose>
			<c:when test="${param.orderType==91}">
				<c:set var="title" value="正常订单管理" />
				<c:choose>
					<c:when test="${param.statusGroup=='1'}">
						<c:set var="subtitle" value="待处理新订单" />
						<script>setSelectedMenu("ulEditNormal",1);</script>
					</c:when>
				</c:choose>
			</c:when>
			<c:otherwise>
			</c:otherwise>
		</c:choose>
		<div id="mainContainer">
			<div id="container">
				<html:form
					action="/finance/financeOrderList.do">
					<html:hidden property="thisAction" />
					<html:hidden property="lastAction" />
					<html:hidden property="intPage" />
					<html:hidden property="pageCount" />
					<html:hidden property="statusGroup" />
					<html:hidden property="showType" />

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
									<c:param name="title2" value="${title}" />
									<c:param name="title3" value="${subtitle}" />
								</c:import>
								<jsp:include page="listSearchToolBar.jsp"></jsp:include>
								<table id="myTable" cellpadding="0" cellspacing="0" border="0"
									class="dataList" width="99%">
									<tr>
										<th>
											<div>
												业务日期
											</div>
										</th>
										<th class="tdPlatform wordWrap">
											<div>
												平台
											</div>
										</th>
										<th class="tdAmount wordWrap">
											<div>
												金额
											</div>
										</th>
										<th class="tdType wordWrap">
											<div>
												类型
											</div>
										</th>
										<th class="tdStatus wordWrap" style="width: 8%;">
											<div>
												订单状态
											</div>
										</th>
										<th class="tdOperator wordWrap">
											<div>
												负责人
											</div>
										</th>


										<th class="tdOrderTime wordWrap" style="width: 60px;">
											<div>
												流水号
											</div>
										</th>
										<th class="tdAction wordWrap" colspan="2"
											style="width: 160px;">
											<div>
												修改
											</div>
										</th>

									</tr>
									<c:forEach var="groupInfo" items="${ulf.list}"
										varStatus="status">
										<tr>
											<td>
												<div>
													<c:out value="${groupInfo.saleOrder.businessDate}" />
												</div>
											</td>
											<td class="tdPlatform wordWrap">
												<div>
													<c:if test="${!empty groupInfo.saleOrder.platform}">
														<c:out value="${groupInfo.saleOrder.platform.name}" />
													</c:if>
												</div>
											</td>
											<td class="tdAmount wordWrap">
												<div>
													<c:out value="${groupInfo.saleOrder.totalAmount}" />
												</div>
											</td>
											<td class="tdType wordWrap">
												<div>
													<c:out value="${groupInfo.saleOrder.tranTypeText}" />
												</div>
											</td>
											<td class="tdStatus wordWrap">
												<div>
													<c:out value="${groupInfo.saleOrder.statusText}"
														escapeXml="false" />
												</div>
											</td>
											<td class="tdOperator wordWrap">
												<div>
													<c:out value="${groupInfo.saleOrder.showEntryOperatorName}" />
												</div>
											</td>


											<td class="tdOrderTime wordWrap" style="width: 60px;">
												<div>
													<a
														href="<%=path%>/finance/financeOrderList.do?thisAction=view&id=<c:out value='${groupInfo.saleOrder.id}' />"><c:out
															value="${groupInfo.saleOrder.orderNo}" /> </a>
												</div>
											</td>
											<td class="tdAction0 wordWrap" style="width: 90px;">
												<div>
													<c:out value='${groupInfo.saleOrder.tradeOperate}'
														escapeXml="false" />
												</div>
											</td>
											<td class="tdAction1 wordWrap" style="width: 70px;">
												<div>
													<c:out value='${groupInfo.saleOrder.commonOperateText}'
														escapeXml="false" />
												</div>
											</td>
											<!-- 加载参数 -->

											<input type="hidden"
												id="companyName<c:out value='${groupInfo.saleOrder.id}'/>"
												value="<c:out value="${groupInfo.saleOrder.company.name}" />
                " />
										</tr>
										<c:if test="${groupInfo.orderCount>1}">
											<c:forEach var="info" begin="1"
												items="${groupInfo.orderList}" varStatus="status3">
												<c:if test="${info.businessType==2}">
													<tr style="background-color: #CCCCCC">
												</c:if>
												<c:if test="${info.businessType!=2}">
													<tr>
												</c:if>
												<td>
													<div>
														<c:out value="${info.businessDate}" />

													</div>
												</td>
												<td class="tdPlatform wordWrap">
													<div>
														<c:if test="${!empty info.platform}">
															<c:out value="${info.platform.name}" />
														</c:if>
													</div>
												</td>

												<td class="tdAmount wordWrap">
													<div>
														<c:out value="${info.totalAmount}" />
													</div>
												</td>
												<td class="tdType wordWrap">
													<div>
														<c:out value="${info.tranTypeText}" />
													</div>
												</td>
												<td class="tdStatus wordWrap">
													<div>
														<c:out value="${info.statusText}" />
													</div>
												</td>
												<td class="tdOperator wordWrap">
													<div>
														<c:out value="${info.showEntryOperatorName}" />
													</div>
												</td>
												<c:if test="${param.orderType==91}">
													<td class="tdPayer wordWrap">
														<div>
															<c:if test="${!empty info.showPayOperator}">
																<c:out value="${info.showPayOperatorName}" />
															</c:if>
														</div>
													</td>
												</c:if>
												<td class="tdOrderTime wordWrap" style="width: 60px;">
													<div>
														<a
															href="<%=path%>/finance/financeOrderList.do?thisAction=view&id=<c:out value='${info.id}' />"><c:out
																value="${info.orderNo}" /> </a>
													</div>
												</td>
												<td class="tdAction0 wordWrap" style="width: 90px;">
													<div>
														<c:out value='${info.tradeOperate}' escapeXml="false" />
													</div>
												</td>
												<td class="tdAction1 wordWrap" style="width: 70px;">
													<div>
														<c:out value='${info.commonOperateText}' escapeXml="false" />
													</div>
												</td>
												<!-- 加载参数 -->

												<input type="hidden"
													id="companyName<c:out value='${info.id}'/>"
													value="<c:out value="${info.company.name}" />
                  " />
											</c:forEach>
										</c:if>
									</c:forEach>
									<tr>
										<td colspan="2">
											<div align="center">
												<font>合计</font>
											</div>
										</td>
										<td style="text-align: right;">
											<c:out value="${ulf.totalValue1}" />
											&nbsp;
										</td>
									</tr>
								</table>
								<table width="100%" style="margin-top: 5px;">
									<tr>
										<td></td>
										<td align="right">
											<div>
												共有订单&nbsp;
												<c:out value="${ulf.groupCount}" />
												&nbsp;条 明细&nbsp;
												<c:out value="${ulf.totalRowCount}"></c:out>
												&nbsp;条&nbsp;&nbsp;&nbsp;&nbsp; [
												<a href="JavaScript:turnToPage(document.forms[0],0)">首页</a>
												|
												<a href="JavaScript:turnToPage(document.forms[0],1)">上一页</a>
												|
												<a href="JavaScript:turnToPage(document.forms[0],2)">下一页</a>
												|
												<a href="JavaScript:turnToPage(document.forms[0],3)"> 末页</a>]
												页数:
												<c:out value="${ulf.intPage}" />
												/
												<c:out value="${ulf.pageCount}" />
												] 第
												<input type="text" name="myIntPage"
													value="<c:out value='${ulf.intPage}'/>"
													style="width: 20px;" onkeyup="goMyIntPage(this.form)">
												页
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
