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
									<c:param name="title1" value="金融服务事业部" />
									<c:param name="title2" value="报表管理" />
									<c:param name="title3" value="资产负债表" />
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
											</td>
										</tr>
									</table>
								</div>
								<table width="70%" cellpadding="0" cellspacing="0" border="0"
									class="dataList">
									<tr>
										<th colspan="2">
											资产负债表
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
															科目编号
														</div>
													</th>
													<th>
														<div>
															科目全名
														</div>
													</th>
													<th>
														<div>
															累计总额
														</div>
													</th>
												</tr>
												<c:forEach var="balance" items="${leftList}"
													varStatus="status">
													<tr>
														<td>
															<c:out value="${status.count}" />
														</td>
														<td>
															<c:out value="${balance.itemKey}" />
														</td>
														<td>
															<c:out value="${balance.itemName}" />
														</td>
														<td>
															<c:out value="${balance.itemAmount}" />
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
															科目编号
														</div>
													</th>
													<th>
														<div>
															科目全名
														</div>
													</th>
													<th>
														<div>
															累计总额
														</div>
													</th>
												</tr>
												<c:forEach var="balance" items="${rightList}"
													varStatus="status">
													<tr>
														<td>
															<c:out value="${status.count}" />
														</td>
														<td>
															<c:out value="${balance.itemKey}" />
														</td>
														<td>
															<c:out value="${balance.itemName}" />
														</td>
														<td>
															<c:out value="${balance.itemAmount}" />
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