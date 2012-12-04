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
		<script type="text/javascript" src="<%=path%>/_js/calendar/WdatePicker.js"></script>
		<c:import url="../page/importDWR.jsp"></c:import>

	</head>
	<body>
		<div id="mainContainer">
			<div id="container">
				<html:form action="/market/priceIndexList.do">
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
									<c:param name="title2" value="行情中心" />
									<c:param name="title3" value="指数明细列表" />
								</c:import>

								<c:import url="listPriceIndexSearchToolBar.jsp"></c:import>

								<table width="100%" cellpadding="0" cellspacing="0" border="0" class="dataList">
									<tr>
										<th width="35">
											<div>
												&nbsp;序号
											</div>
										</th>
										<th width="30">
											<div>
												<input type="checkbox" onclick="checkAll(this, 'selectedItems')" name="sele" />
											</div>
										</th>
										<th>
											<div>
												名称
											</div>
										</th>
										<th>
											<div>
												最高|平均|最低
											</div>
										</th>
										<th>
											<div>
												涨跌
											</div>
										</th>
										<th>
											<div>
												备注
											</div>
										</th>
										<th>
											<div>
												时间
											</div>
										</th>
										<th>
											<div>
												采集地
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
									<c:forEach var="priceIndex" items="${priceIndexListForm.list}" varStatus="status">
										<tr>
											<td>
												<c:out value="${status.count+(priceIndexListForm.intPage-1)*priceIndexListForm.perPageNum}" />
											</td>
											<td>
												<html:multibox property="selectedItems" value="${priceIndex.id}" onclick="checkItem(this, 'sele')"></html:multibox>
											</td>
											<td>
												<a href="<%=path%>/market/priceReferenceList.do?thisAction=view&id=<c:out value="${priceIndex.priceReference.id}" />"> <c:out value="${priceIndex.priceReference.name}" /> </a>
											</td>
											<td>
												<font style="color: red;"> <c:out value="${priceIndex.maxPrice}" /> </font>

												<c:out value="${priceIndex.price}" />

												<font style="color: green;"> <c:out value="${priceIndex.minPrice}" /> </font>
											</td>
											<td>
												<c:out value="${priceIndex.rangeHtml}" escapeXml="false" />
											</td>
											<td>
												<c:out value="${priceIndex.memo}" />
											</td>
											<td>
												<c:out value="${priceIndex.snatchDate}" />
											</td>
											<td>
												<c:out value="${priceIndex.gatherCompany.shortName}" />
											</td>
											<td>
												<c:out value="${priceIndex.statusInfo}" />
											</td>
											<td>
												<a href="<%=path%>/market/priceIndexList.do?thisAction=view&id=<c:out value="${priceIndex.id}" />">查看</a> |
												<a href="<%=path%>/market/priceIndexList.do?thisAction=saveAppoint&priceReferenceId=<c:out value="${priceIndex.priceReference.id}" />">新增</a>
											</td>
										</tr>
									</c:forEach>

								</table>

								<table width="100%" style="margin-top: 5px;">
									<tr>
										<td>
											<c:import url="listPriceIndexOperation.jsp"></c:import>
										</td>
										<td align="right">
											<div>
												共有记录&nbsp;
												<c:out value="${priceIndexListForm.totalRowCount}"></c:out>
												&nbsp;条&nbsp;&nbsp;&nbsp;&nbsp; [
												<a href="JavaScript:turnToPage(document.forms[0],0)">首页</a> |
												<a href="JavaScript:turnToPage(document.forms[0],1)">上一页</a> |
												<a href="JavaScript:turnToPage(document.forms[0],2)">下一页</a> |
												<a href="JavaScript:turnToPage(document.forms[0],3)"> 末页</a>] 页数:
												<c:out value="${priceIndexListForm.intPage}" />
												/
												<c:out value="${priceIndexListForm.pageCount}" />
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