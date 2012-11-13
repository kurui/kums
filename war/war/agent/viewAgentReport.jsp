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

		<script type="text/javascript">

	</script>
	</head>
	<body>
		<div id="mainContainer">
			<div id="container">
				<html:form action="/report/balanceList.do">
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
									<c:param name="title1" value="大客户部" />
									<c:param name="title2" value="报表管理" />
									<c:param name="title3" value="仪表盘" />
								</c:import>
								<div class="searchBar">
									<table cellpadding="0" cellspacing="0" border="0"
										class="searchPanel">
										<tr>
											<td>
											</td>
										</tr>
									</table>
								</div>
								<table width="70%" cellpadding="0" cellspacing="0" border="0"
									class="dataList">
									<tr>
										<th colspan="2">
											客户级别-性别
										</th>
										<th colspan="2">
											关联公司分布
										</th>
										
									</tr>
									<tr>
										<td valign="top">
										<table width="100%" cellpadding="0" cellspacing="0"
												border="0" class="dataList">
												<tr>
													<th width="60">
														<div>
															行号
														</div>
													</th>
													<th>
														<div>
															客户级别
														</div>
													</th>
													<th>
														<div>
															数量
														</div>
													</th>
													
													<th>
														<div>
															百分比
														</div>
													</th>
												</tr>
												<c:forEach var="data" items="${typeList}"
													varStatus="status">
													<tr>
														<td>
															<c:out value="${status.count}" />
														</td>
														<td>
															<c:out value="${data.itemName}" />
														</td>
														<td>
															<c:out value="${data.itemValue}" />
														</td>
														<td>
														</td>
													</tr>
												</c:forEach>
											</table>
											<table width="100%" cellpadding="0" cellspacing="0"
												border="0" class="dataList">
												<tr>
													<th width="60">
														<div>
															行号
														</div>
													</th>
													<th>
														<div>
															性别
														</div>
													</th>
													<th>
														<div>
															数量
														</div>
													</th>
													<th>
														<div>
															百分比
														</div>
													</th>
												</tr>
												<c:forEach var="data" items="${sexList}"
													varStatus="status">
													<tr>
														<td>
															<c:out value="${status.count}" />
														</td>
														<td>
															<c:out value="${data.itemName}" />
														</td>
														<td>
															<c:out value="${data.itemValue}" />
														</td>
													</tr>
												</c:forEach>
											</table>
										</td>
										<td valign="top">
											<table width="100%" cellpadding="0" cellspacing="0"
												border="0" class="dataList">
												<tr>
													<th width="60">
														<div>
															行号
														</div>
													</th>
													<th>
														<div>
															客户级别
														</div>
													</th>
													<th>
														<div>
															性别
														</div>
													</th>
													<th>
														<div>
															数量
														</div>
													</th>
													
													<th>
														<div>
															百分比
														</div>
													</th>
												</tr>
												<c:forEach var="data" items="${typeSexList}"
													varStatus="status">
													<tr>
														<td >
															<c:out value="${status.count}" />
														</td>
														<td>
															<c:out value="${data.typeInfo}" />
														</td>
														<td>
															<c:out value="${data.sexInfo}" />
														</td>
														<td>
															<c:out value="${data.itemValue}" />
														</td>
														<td>
														</td>
													</tr>
												</c:forEach>
											</table>
										</td>
										<td valign="top">
										<table width="100%" cellpadding="0" cellspacing="0"
												border="0" class="dataList">
												<tr>
													<th width="60">
														<div>
															行号
														</div>
													</th>
													<th>
														<div>
															公司
														</div>
													</th>
													<th>
														<div>
															数量
														</div>
													</th>
													
													<th>
														<div>
															百分比
														</div>
													</th>
												</tr>
												<c:forEach var="data" items="${companyList}"
													varStatus="status">
													<tr>
														<td>
															<c:out value="${status.count}" />
														</td>
														<td>
															<c:out value="${data.companyName}" />
														</td>
														<td>
															<c:out value="${data.itemValue}" />
														</td>
														<td>
														</td>
													</tr>
												</c:forEach>
											</table>
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