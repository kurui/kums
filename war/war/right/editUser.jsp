﻿<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>

<html>
	<head>
		<title>main</title>
		<link href="../_css/reset.css" rel="stylesheet" type="text/css" />
		<link href="../_css/global.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" language="javascript"
			src="../_js/jquery-1.3.2.min.js"></script>
		<script src="../_js/prototype/common.js" type="text/javascript"></script>

		<script>
String.prototype.Trim   =   function()   
  {   
  return   this.replace(/(^\s*)|(\s*$)/g,   "");   
  }
function addUser()
{
	var username = document.forms[0].userName.value.Trim();
	var userno = document.forms[0].userNo.value.Trim();
	var email=document.forms[0].userEmail.value.Trim();
	if(username.length==0||userno.length==0||email.length==0){
		alert("请填写完整信息!");
	}else if(!(/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(email))){
		alert("Email格式错误！");
	}
	else{
	var thisaction=document.forms[0].thisAction.value;
	document.forms[0].action="user.do?thisAction="+thisaction+"";
    document.forms[0].submit();
    }
}

function editPassword()
{
	document.forms[0].action="user.do?thisAction=editPassword";
    document.forms[0].submit();
}
</script>
	</head>
	<body>
		<div id="mainContainer">
			<div id="container">
				<html:form action="/user/user.do">
					<html:hidden property="thisAction" name="user" />
					<html:hidden property="userId" name="user" />
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
									<c:param name="title1" value="用户管理" />
									<c:param name="title2" value="编辑用户信息" />																			
								</c:import>							
								
								<hr>
								<table width="100%" cellpadding="0" cellspacing="0" border="0"
									class="dataList">
									<tr>
										<td class="lef">
											名字
										</td>
										<td style="text-align: left">
											<html:text property="userName" name="user"
												styleClass="colorblue2 p_5" />
											<font color="red">*</font>
										</td>
									</tr>
									<tr>
										<td class="lef">
											登录帐号
										</td>
										<td style="text-align: left">
											<html:text property="userNo" name="user"
												styleClass="colorblue2 p_5" />
											<font color="red">*</font>
										</td>
									</tr>
									<tr>
										<td class="lef">
											状态
										</td>
										<td style="text-align: left">
											<html:radio property="userStatus" value="1" name="user">
					启用</html:radio>
											<html:radio property="userStatus" value="2" name="user">停用</html:radio>
										</td>
									</tr>
									<tr>
										<td class="lef">
											Email
										</td>
										<td style="text-align: left">
											<html:text property="userEmail" name="user"
												styleClass="colorblue2 p_5" />
											<font color="red">*</font>
										</td>
									</tr>
									<tr>
										<td class="lef">
											部门
										</td>
										<td style="text-align: left">
											<html:radio property="userDepart" value="1" name="user">总经办</html:radio>
											<html:radio property="userDepart" value="2" name="user">财务部</html:radio>
											<html:radio property="userDepart" value="3" name="user">大客户部</html:radio>
											<html:radio property="userDepart" value="4" name="user">人事部</html:radio>
											<html:radio property="userDepart" value="21" name="user">软件事业组</html:radio>
											<html:radio property="userDepart" value="31" name="user">电子商务部</html:radio>
											<html:radio property="userDepart" value="41" name="user">旅游事业部</html:radio>	
											<html:radio property="userDepart" value="41" name="user">天狮事业部</html:radio>																						
											<html:radio property="userDepart" value="51" name="user">地产事业部</html:radio>
										</td>
									</tr>
									<logic:equal value="update" property="thisAction" name="user">
										<c:check code="sa03">
											<tr>
												<td class="lef">
													<font color="red">重置密码</font>
												</td>
												<td style="text-align: left">
													<a href="javascript:editPassword();">修改密码</a>
												</td>
											</tr>
										</c:check>
									</logic:equal>
								</table>
								<table width="100%" style="margin-top: 5px;">
									<tr>
										<td>
											<input name="label" type="button" class="button1" value="提 交"
												onclick="addUser();">
											<input name="label" type="reset" class="button1" value="重 置">
											<input name="label" type="button" class="button1" value="取消"
												onclick="javascript:history.back();">
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</html:form>
			</div>
		</div>
	</body>
</html>
