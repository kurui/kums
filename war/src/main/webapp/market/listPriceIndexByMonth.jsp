<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<link href="<%=path%>/_css/reset.css" rel="stylesheet" type="text/css" />
		<link href="<%=path%>/_css/global.css" rel="stylesheet"
			type="text/css" />
		<script type="text/javascript" src="<%=path%>/_js/prototype/common.js"></script>
		<script type="text/javascript" src="<%=path%>/_js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript" src="<%=path%>/_js/base/FormUtil.js"></script>
		<script type="text/javascript"
			src="<%=path%>/_js/calendar/WdatePicker.js"></script>
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
									<c:param name="title3" value="指数月明细列表" />
								</c:import>

								<c:import url="listPriceIndexSearchToolBar.jsp"></c:import>

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
												名称
											</div>
										</th>
										<c:forEach var="timePhase" items="${timePhase}"
											varStatus="status">
											<th>
												<div>
													<c:out value="${timePhase}" />
													<c:out value="${timePhaseUnit}" />
												</div>
											</th>
										</c:forEach>
									</tr>
									<c:forEach var="dataList" items="${phaseData}"
										varStatus="status2">
										<tr>
											<td>
												<c:out value="${status2.count}" />
											</td>
											<c:forEach var="priceIndex" begin="1" items="${dataList}"
												varStatus="status3">
												<c:if test="${status3.count==1}">
													<td>
														<a
															href="../market/priceReferenceList.do?thisAction=view&id=<c:out value="${dataList[0]}" />"><c:out
																value="${dataList[1]}" /> </a>
													</td>
												</c:if>
												<c:if test="${status3.count>1}">
													<td>
														<c:out value="${dataList[status3.count]}" />
													</td>
												</c:if>


											</c:forEach>
										</tr>
									</c:forEach>

								</table>
								<table width="100%" style="margin-top: 5px;">
									<tr>
										<td>
											<c:import url="listPriceIndexOperation.jsp"></c:import>
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