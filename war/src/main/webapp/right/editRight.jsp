<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/tld/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<html>
	<head>
		<title>编辑权限</title>
		<link href="../_css/reset.css" rel="stylesheet" type="text/css" />
		<link href="../_css/global.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" language="javascript"
			src="../_js/jquery-1.3.2.min.js"></script>
		<script src="../_js/prototype/common.js" type="text/javascript"></script>
		<script>
   function add(){
	   if (document.forms[0].rightName=="") {
	     alert("系统角色权限名称不能为空!");
	     return;
	   } else if (document.forms[0].rightCode=="") {
	     alert("系统角色权限代码不能为空!");
	     return;
	   }
	   document.forms[0].submit();	 
	}	 
</script>
	</head>
	<body class="body_m">
		<c:import url="/page/mainTitle.jsp" charEncoding="UTF-8">
			<c:param name="title1" value="权限管理" />
			<c:param name="title2" value="添加权限" />
		</c:import>
		<html:form action="/right/roleright.do">
			<html:hidden property="thisAction" name="rrf" />
			<html:hidden property="roleID" value="${rrf.roleID}" />

			<table width="100%" cellspacing="1" class="table_li">
				<tr>
					<td class="table_ltitle">
						系统角色权限名称
					</td>
					<td class="table_content">
						<html:text name="rrf" property="rightName" styleClass="TextInput"
							size="40" />
					</td>
				</tr>
				<tr>
					<td class="table_ltitle">
						系统角色权限代码
					</td>
					<td class="table_content">
						<html:text name="rrf" property="rightCode" styleClass="TextInput"
							size="40" />
					</td>
				</tr>
				<tr>
					<td class="table_ltitle">
						系统角色权限事件
					</td>
					<td class="table_content">
						<html:text name="rrf" property="rightAction"
							styleClass="TextInput" size="40" />
					</td>
				</tr>
				<tr>
					<td class="table_ltitle">
						系统角色权限方法
					</td>
					<td class="table_content">
						<html:text name="rrf" property="rightEvent" styleClass="TextInput"
							size="40" />
					</td>
				</tr>
				<tr>
					<td class="table_ltitle">
						系统角色权限描述
					</td>
					<td class="table_content">
						<html:text name="rrf" property="rightDescription"
							styleClass="TextInput" size="40" />
					</td>
				</tr>
			</table>

			<table width="100%" style="margin-top: 5px;">
				<tr>
					<td align="center">
						<input name="label" type="button" class="button1" value="返 回"
							onclick="window.history.back();">
						<input type="button" class="button1" onclick="add();" value="保 存">
						<input name="label" type="reset" class="button1" value="重 置">
					</td>
				</tr>
			</table>

		</html:form>

	</body>
</html>