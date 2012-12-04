<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/tld/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<html>
	<head>
		<TITLE></TITLE>
		<script type="text/javascript" language="javascript"
			src="../_js/base/table.js"></script>
		<script src="../_js/prototype/common.js" type="text/javascript"></script>
		<script language="JavaScript">
<!--
 		window.onload=initOnload;

function initOnload(){
	js.table.table.style1();
}
-->
</script>
	</head>

	<body class="body_m">
		<c:import url="/page/mainTitle.jsp" charEncoding="UTF-8">
			<c:param name="title1" value="权限管理" />
			<c:param name="title2" value="查看角色" />
		</c:import>
		<html:form action="/right/rolelist.do">
			<html:hidden property="thisAction" value="" />
			<table width="100%" cellspacing="1" class="table_li">
				<tr align="center" class="table_title">
					<td>
						<div align="center">
							No.
						</div>
					</td>
					<td>
						<div align="center"></div>
					</td>
					<td>
						<div align="center">
							用户角色
						</div>
					</td>
					<td>
						<div align="center">
							描 述
						</div>
					</td>
				</tr>
				<c:forEach var="info" items="${ulf.list}" varStatus="status">
					<tr>
						<td class="table_content" width="20">
							<div align="center">
								<c:out value="${status.count}" />
							</div>
						</td>
						<td class="table_content" width="20">
							<html:multibox property="selectedItems" styleClass="cb"
								value="${info.roleID}" />
						</td>
						<td class="table_content">
							<a
								href="role.do?thisAction=view&roleID=<c:out value='${info.roleID}' />"><c:out
									value="${info.roleName}" /> </a>
						</td>
						<td class="table_content">
							<c:out value="${info.roleDescription}" />
						</td>
					</tr>
				</c:forEach>
			</table>
			<table width="100%" style="margin-top: 5px;">
				<tr>
					<td>
						<a href="javascript:addRole()"><html:img
								page="/theme/images/new.gif" border="0" /> </a>
						<a href="javascript:editRole()"><html:img
								page="/theme/images/edit.gif" border="0" /> </a>
						<a href="javascript:delRole()"><html:img
								page="/theme/images/delete.gif" border="0" /> </a>
					</td>
				</tr>
			</table>
		</html:form>
	</body>
</html>