<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ page import="org.jfree.chart.ChartUtilities"%>
<%@ page import="org.jfree.chart.ChartRenderingInfo"%>
<%@page import="java.io.PrintWriter"%>
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
		<script type="text/javascript">
	
	</script>
	</head>
	<body>
		<div id="mainContainer">
			<div id="container">
				<html:form action="/finance/financeOrderList.do">
					<html:hidden property="thisAction" />
					<html:hidden property="lastAction" />
					<html:hidden property="intPage" />
					<html:hidden property="pageCount" />

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
									<c:param name="title2" value="管理费用统计图表" />
								</c:import>

								<c:import url="listSearchToolBar.jsp"></c:import>

								<P ALIGN="CENTER">
									<c:forEach var="chartUrl" items="${chartUrlList}" varStatus="status">
										<img src="<c:out value="${chartUrl}" ></c:out>" border="0" usemap="#map0">
									</c:forEach>
								</P>
								<%
									ChartRenderingInfo info = (ChartRenderingInfo) request
												.getAttribute("ChartRenderingInfo");
										PrintWriter w = new PrintWriter(out);// 输出MAP信息
										if (info != null) {
											ChartUtilities.writeImageMap(w, "map0", info, false);
										}
								%>

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