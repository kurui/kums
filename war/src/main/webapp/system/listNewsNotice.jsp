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
		<script type="text/javascript" language="javascript"
			src="<%=path%>/_js/jquery-1.3.2.min.js"></script>
		<script src="<%=path%>/_js/prototype/common.js" type="text/javascript"></script>
	</head>
	<body>
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
						<c:param name="title2" value="系统公告" />
					</c:import>
					<table width="100%" cellpadding="0" cellspacing="0" border="0"
						class="dataList">
						<tr style="display: none">
							<th width="35">
								<div>
									&nbsp;序号
								</div>
							</th>
							<th>
								<div>
									标题
								</div>
							</th>
							<th>
								<div>
									更新时间
								</div>
							</th>
						</tr>
						<c:forEach var="info" items="${nlf.list}" varStatus="status">
							<tr>
								<td>
									<c:out value="${status.count+(nlf.intPage-1)*nlf.perPageNum}" />
								</td>
								<td>
									<html:link
										page="/information/newsList.do?thisAction=view&id=${info.id}">
										<c:out value="${info.title}" />
									</html:link>
								</td>
								<td>
									<c:out value="${info.createDate}" />
								</td>
							</tr>
						</c:forEach>
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
	</body>
</html>
