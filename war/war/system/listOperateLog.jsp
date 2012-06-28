<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<link href="../_css/reset.css" rel="stylesheet" type="text/css" />
		<link href="../_css/global.css" rel="stylesheet" type="text/css" />
		<script src="../_js/prototype/common.js" type="text/javascript"></script>
		<script src="../_js/calendar/WdatePicker.js" type="text/javascript"></script>
	</head>

	<body>
		<div id="mainContainer">
			<div id="container">
				<html:form action="/system/operateLoglist.do?thisAction=list">
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
									<c:param name="title1" value="系统管理" />
									<c:param name="title2" value="操作日志" />
								</c:import>
								<div class="searchBar">
									<table cellpadding="0" cellspacing="0" border="0"
										class="searchPanel">
										<tr>
											<td>
												帐号
											</td>
											<td>
												<html:text property="userNo" styleClass="colorblue2 p_5" />
											</td>
											<td>
												日期:
												<html:text property="startDate" styleClass="colorblue2 p_5"
													style="width:150px;"
													onfocus="WdatePicker({startDate:'%y-%M-%D',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"
													readonly="true" />
												-
												<html:text property="endDate" styleClass="colorblue2 p_5"
													style="width:150px;"
													onfocus="WdatePicker({startDate:'%y-%M-%D',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"
													readonly="true" />
											</td>

											<td>
												<input type="submit" name="button" id="button" value="提交"
													class="submit greenBtn" />
											</td>
										</tr>
									</table>
								</div>
								<table width="100%" cellpadding="0" cellspacing="0" border="0"
									class="dataList">
									<tr>
										<th>
											序号
										</th>
										<th width="230">
											<div>
												操作员
											</div>
										</th>
										<th>
											<div>
												业务流水号
											</div>
										</th>
										<th>
											<div>
												业务
											</div>
										</th>
										<th>
											<div>
												操作
											</div>
										</th>
										<th>
											<div>
												IP
											</div>
										</th>
										<th>
											<div>
												日期
											</div>
										</th>
									</tr>
									<c:forEach var="info" items="${tllf.list}"  varStatus="status">
										<tr>
											<td>
												<c:out
													value="${status.count+(tllf.intPage-1)*tllf.perPageNum}" />
											</td>
											<td>
												<c:out value="${info.sysUser.userNo}" />
											</td>
											<td>
												<c:out value="${info.operateLogDetail.orderNo}" />
											</td>
											<td>
												<c:out value="${info.orderTypeInfo}" />
											</td>
											<td>
												<c:out value="${info.typeInfo}" />
											</td>
											<td>
												<c:out value="${info.operateLogDetail.ip}" />
											</td>
											<td>
												<c:out value="${info.formatOptTime}" />
											</td>
										</tr>
									</c:forEach>

								</table>

								<table width="100%" style="margin-top: 5px;">
									<tr>
										<td></td>
										<td align="right">
											<div>
												共有记录&nbsp;
												<c:out value="${tllf.totalRowCount}"></c:out>
												&nbsp;条&nbsp;&nbsp;&nbsp;&nbsp; [
												<a href="JavaScript:turnToPage(document.forms[0],0)">首页</a>
												|
												<a href="JavaScript:turnToPage(document.forms[0],1)">上一页</a>
												|
												<a href="JavaScript:turnToPage(document.forms[0],2)">下一页</a>
												|
												<a href="JavaScript:turnToPage(document.forms[0],3)"> 末页</a>]
												页数:
												<c:out value="${tllf.intPage}" />
												/
												<c:out value="${tllf.pageCount}" />
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
