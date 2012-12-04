<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ page import="org.jfree.chart.ChartUtilities"%>
<%@ page import="org.jfree.chart.ChartRenderingInfo"%>
<%@page import="java.io.PrintWriter"%>
<%
	String path = request.getContextPath();
%>
<html>
	<head>
		<title>main</title>
		<link href="../_css/reset.css" rel="stylesheet" type="text/css" />
		<link href="../_css/global.css" rel="stylesheet" type="text/css" />
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
								<c:param name="title1" value="金融服务事业部" />
								<c:param name="title2" value="行情中心" />
								<c:param name="title3" value="查看指数" />
							</c:import>
							<hr>
							<table width="100%" cellpadding="0" cellspacing="0" border="0" class="dataList">
								<tr>
									<td class="lef">
										<c:out value="${priceReference.code}" />
									</td>
									<td style="text-align: left">
										<c:out value="${priceReference.name}" />
									</td>
									<td class="lef">
										单位
									</td>
									<td style="text-align: left">
										<c:out value="${priceReference.money}" />
										|
										<c:out value="${priceReference.unit}" />
									</td>
								</tr>
								<tr>
									<td class="lef">
										类型
									</td>
									<td style="text-align: left">
										<c:out value="${priceReference.typeInfo}" />
									</td>
									<td class="lef">
										状态
									</td>
									<td style="text-align: left">
										<c:out value="${priceReference.statusInfo}" />
									</td>
								</tr>
								<tr>
									<td class="lef">
										备注
									</td>
									<td style="text-align: left">
										<c:out value="${priceReference.memo}" />
									</td>
									<td class="lef">
									</td>
									<td style="text-align: left">
									</td>
								</tr>

							</table>
							<table width="100%" style="margin-top: 5px;">
								<tr>
									<td align="center">
										<html:form action="/market/priceReferenceList.do">
											<html:hidden property="selectedItems" value="${priceReference.id}" />
											<html:hidden property="thisAction" name="priceReference" />
											<input name="label" type="button" class="button2" value="新增参照物" onclick="add();" />
											<input name="label" type="button" class="button1" value="返 回" onclick="window.history.back();">
											<input name="label" type="button" class="button1" value="修 改" onclick="edit();" />

										</html:form>
									</td>
								</tr>
							</table>
							<html:form action="/market/priceIndexList.do">
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
									<c:forEach var="priceIndex" items="${priceIndexList}" varStatus="status">
										<tr>
											<td>
												<c:out value="${status.count}" />
											</td>
											<td>
												<html:multibox property="selectedItems" value="${priceIndex.id}" onclick="checkItem(this, 'sele')"></html:multibox>
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
							</html:form>

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
			</div>
		</div>
	</body>
	<script type="text/javascript">
	function add(){
		var type=<c:out value="${priceReference.type}" />
	    document.forms[0].action="<%=path%>/market/priceReferenceList.do?thisAction=save&type="+type;
	    document.forms[0].submit();
	}	
		function edit(){
		    document.forms[0].action="<%=path%>/market/priceReferenceList.do?thisAction=edit";
		    document.forms[0].submit();
		}
	</script>
</html>
