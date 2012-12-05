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
		<script type="text/javascript" language="javascript"
			src="../_js/jquery-1.3.2.min.js"></script>
		<script src="../_js/prototype/common.js" type="text/javascript"></script>
		<script type="text/javascript" src="<%=path%>/_js/base/FormUtil.js"></script>
		<script type="text/javascript"
			src="<%=path%>/_js/calendar/WdatePicker.js"></script>
		<script type="text/javascript">
	function exportReport(){
	    document.forms[0].thisAction.value="save";
	    document.forms[0].submit();
	}
	</script>
	</head>
	<body>
		<div id="mainContainer">
			<div id="container">
				<html:form action="/report/cashFlowList.do">
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
									<c:param name="title1" value="金融服务事业部" />
									<c:param name="title2" value="报表管理" />
									<c:param name="title3" value="资金流水" />
								</c:import>
								<div class="searchBar">
									<table cellpadding="0" cellspacing="0" border="0"
										class="searchPanel">
										<tr>
											<td>
												<input name="label" type="button" class="button1"
													value="导 出" onclick="exportReport();">
											</td>
											<td>
												<html:text property="startDate" styleClass="colorblue2 p_5"
													style="width:120px;"
													onfocus="WdatePicker({startDate:'%y-%M-%D 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})"
													readonly="true" />
											</td>
											<td>
												<html:text property="endDate" styleClass="colorblue2 p_5"
													style="width:120px;"
													onfocus="WdatePicker({startDate:'%y-%M-%D 23:59:59',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})"
													readonly="true" />
											</td>
											<td>
												<input type="submit" name="button" id="button" value="提交"
													class="submit greenBtn" />
											</td>
											<td>
												<c:if test="${cashFlowListForm.thisAction=='listChart'}">
													<input type="button" name="button" alt="统计图表"
														class="btn_select_model_2_2" />
													<input type="button" name="button" alt="明细账"
														class="btn_select_model_1_1" onclick="selectList();" />
												</c:if>
												<c:if test="${cashFlowListForm.thisAction=='list'}">
													<input type="button" name="button" alt="统计图表"
														class="btn_select_model_2_1" onclick="selectChart();" />
													<input type="button" name="button" alt="明细账"
														class="btn_select_model_1_2" />
												</c:if>
												<script type="text/javascript">
												function selectChart(){
													 document.forms[0].thisAction.value="listChart";
								    				 document.forms[0].submit();
												}
												function selectList(){
													 document.forms[0].thisAction.value="list";
								    				 document.forms[0].submit();
												}
												</script>
											</td>
										</tr>
									</table>
								</div>
								<table width="100%" cellpadding="0" cellspacing="0" border="0"
									class="dataList">
									<tr>
										<th width="60">
											<div>
												序号
											</div>
										</th>
										<th>
											<div>
												流水号
											</div>
										</th>
										<th>
											<div>
												日期
											</div>
										</th>
										<th>
											<div>
												科目
											</div>
										</th>
										<th>
											<div>
												收入
											</div>
										</th>
										<th>
											<div>
												支出
											</div>
										</th>
										<th>
											<div>
												往来单位
											</div>
										</th>
										<th>
											<div>
												往来账户
											</div>
										</th>
										<th>
											<div>
												摘要
											</div>
										</th>
										<th>
											<div>
												操作
											</div>
										</th>
									</tr>
									<c:forEach var="cashFlow" items="${cashFlowListForm.list}"
										varStatus="status">
										<tr>
											<td>
												<c:out
													value="${status.count+(cashFlowListForm.intPage-1)*cashFlowListForm.perPageNum}" />
											</td>
											<td>
												<c:out value="${cashFlow.businessNo}" />
											</td>
											<td>
												<c:out value="${cashFlow.businessDate}" />
											</td>
											<td>
												<c:out value="${cashFlow.itemName}" />
											</td>
											<td>
												<c:out value="${cashFlow.inAmount}" />
											</td>
											<td>
												<c:out value="${cashFlow.outAmount}" />
											</td>
											<td>
												<c:out value="${cashFlow.cussentCompany}" />
											</td>
											<td>
												<c:out value="${cashFlow.cussentAccount}" />
											</td>
											<td>
												<c:out value="${cashFlow.summary}" />
											</td>
											<td>
												<a
													href="<%=path%>/finance/financeOrderList.do?thisAction=listFinanceOrder">
													明细</a>
											</td>
										</tr>
									</c:forEach>
									<tr>
										<td colspan="2">
											<div align="center">
												<font>合计</font>
											</div>
										</td>
										<td colspan="2"></td>
										<td style="text-align: right;">
											<c:out value="${cashFlowListForm.totalValue1}" />
											&nbsp;
										</td>
										<td style="text-align: right;">
											<c:out value="${cashFlowListForm.totalValue2}" />
											&nbsp;
										</td>
										<td colspan="1"></td>
									</tr>
								</table>
								<table width="100%" style="margin-top: 5px;">
									<tr>
										<td>

										</td>
										<td align="right">
											<div>
												共有记录&nbsp;
												<c:out value="${cashFlowListForm.totalRowCount}"></c:out>
												&nbsp;条&nbsp;&nbsp;&nbsp;&nbsp; [
												<a href="JavaScript:turnToPage(document.forms[0],0)">首页</a>
												|
												<a href="JavaScript:turnToPage(document.forms[0],1)">上一页</a>
												|
												<a href="JavaScript:turnToPage(document.forms[0],2)">下一页</a>
												|
												<a href="JavaScript:turnToPage(document.forms[0],3)"> 末页</a>]
												页数:
												<c:out value="${cashFlowListForm.intPage}" />
												/
												<c:out value="${cashFlowListForm.pageCount}" />
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