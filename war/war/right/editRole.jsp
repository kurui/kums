<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/tld/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<html>
	<head>
		<title>编辑角色</title>
		<link href="../_css/reset.css" rel="stylesheet" type="text/css" />
		<link href="../_css/global.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" language="javascript"
			src="../_js/jquery-1.3.2.min.js"></script>
		<script src="../_js/prototype/common.js" type="text/javascript"></script>
		<script>
  function add() {
   if (document.forms[0].roleName.value=="")   {
     alert("角色名称不能为空!");
     return;
   }
   document.forms[0].submit();	 
  }
</script>
	</head>
	<body class="body_m">
		<c:import url="/page/mainTitle.jsp" charEncoding="UTF-8">
			<c:param name="title1" value="权限管理" />
			<c:param name="title2" value="编辑角色" />
		</c:import>

		<html:form action="/right/role.do">
			<html:hidden name="rf" property="thisAction" />
			<html:hidden name="rf" property="roleID" />
			<table width="100%" cellspacing="1" class="table_li">
				<tr>
					<td class="table_ltitle">
						用户角色名称
					</td>
					<td class="table_content">
						<html:text name="rf" property="roleName" styleClass="TextInput"
							size="40" />
					</td>
				</tr>
				<c:if
					test="${rf.thisAction=='updateRole' or rf.thisAction=='insertRole'}">
					<tr>
						<td class="table_ltitle">
							用户角色代码
						</td>
						<td class="table_content">
							<html:text name="rf" property="roleKey" styleClass="TextInput"
								size="40" />
						</td>
					</tr>
				</c:if>
				<tr>
					<td class="table_ltitle">
						用户角色描述
					</td>
					<td class="table_content">
						<html:text name="rf" property="roleDescription"
							styleClass="TextInput" size="40" />
					</td>
				</tr>
			</table>
			<table width="100%" style="margin-top: 5px;">
				<tr>
					<td align="center">
						<input type="button" class="button1" onclick="add();" value="保 存">
						<input name="label" type="reset" class="button1" value="重 置">
					</td>
				</tr>
			</table>
		</html:form>
	</body>
</html>