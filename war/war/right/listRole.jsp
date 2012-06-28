<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/tld/struts-html-el.tld" prefix="html"%>
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
  function addRole() {
    document.forms[0].thisAction.value="addRole";
    document.forms[0].submit();
  }
  
  function editRole(){
	  document.forms[0].thisAction.value="editRole";	 
      document.forms[0].submit();
  }
	
  function delRole()  {
    document.forms[0].thisAction.value="delRole";
    if(confirm("您真的要删除系统角色吗？")){
    	  document.forms[0].submit();
    }
  }
  
  function addRight(){
    document.forms[0].thisAction.value="addRight";
    document.forms[0].submit();
  }
  
  function editRight()  {
	  if (sumCheckedBox(document.forms[0].selectedRightItems)>1){
	  	  alert("您至多只能选择一个用户角色进行修改!");
	  } else if (sumCheckedBox(document.forms[0].selectedRightItems)<1){
	    alert("您还没有选择给哪个用户角色修改!");
	  }else{	
	  	//alert("editRight");
	  	document.forms[0].thisAction.value="editRight";	 
      	document.forms[0].submit();
      }
  }
	
  function delRight()  {
	if (sumCheckedBox(document.forms[0].selectedRightItems)<1)
	  alert("您还没有选择要删除的用户角色!");
	else if (confirm("你真的要删除选择的用户角色吗？"))
	{
	  document.forms[0].thisAction.value="delRight";
	  document.forms[0].submit();
	}
  }
</script>
	</head>

	<body class="body_m">
		<c:import url="/page/mainTitle.jsp" charEncoding="UTF-8">
			<c:param name="title1" value="权限管理" />
			<c:param name="title2" value="系统角色" />
		</c:import>
		<html:form action="/right/rolelist.do">
			<html:hidden property="thisAction" value="" />
			<table class="table_in">
				<tr>
					<td>
						<table class="table_in">
							<tr align="center" class="table_ltitle">
								<td>
									<div align="center">
										系统角色
									</div>
								</td>
								<td>
									<div align="center">
										<c:out value="${ulf.roleName}" />
										-角色属性
									</div>
								</td>
							</tr>
							<tr>
								<td valign="top">
									<table class="list_table">
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
													用户代码
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
													<html:radio property="roleID" value="${info.roleID}" />
												</td>
												<td class="table_content">
													<a
														href="rolelist.do?roleID=<c:out value='${info.roleID}' />&thisAction=listRole"><c:out
															value="${info.roleName}" /> </a>
												</td>
												<td class="table_content">
													<c:out value="${info.roleKey}" />
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
												<input type="button" class="button1" onclick="addRole()"
													value="新 增">
												<input type="button" class="button1" onclick="editRole()"
													value="修 改">
												<input type="button" class="button1" onclick="delRole()"
													value="删 除">
											</td>
										</tr>
									</table>

								</td>
								<td valign="top">
									<table class="table_in">
										<tr>
											<td>
												<table class="list_table">
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
																权限名称
															</div>
														</td>
														<td>
															<div align="center">
																权限代码
															</div>
														</td>
														<td>
															<div align="center">
																权限事件
															</div>
														</td>
														<td>
															<div align="center">
																权限方法
															</div>
														</td>
														<td>
															<div align="center">
																描述
															</div>
														</td>
													</tr>
													<c:forEach var="info" items="${slist}" varStatus="status">
														<tr>
															<td class="table_content" width="20">
																<div align="center">
																	<c:out value="${status.count}" />
																</div>
															</td>
															<td class="table_content" width="20">
																<html:multibox property="selectedRightItems"
																	styleClass="cb" value="${info.rightKey}" />
															</td>
															<td class="table_content">
																<c:out value="${info.rightName}" />
															</td>
															<td class="table_content">
																<c:out value="${info.rightCode}" />
															</td>
															<td class="table_content">
																<c:out value="${info.rightAction}" />
															</td>
															<td class="table_content">
																<c:out value="${info.rightEvent}" />
															</td>
															<td class="table_content">
																<c:out value="${info.rightDescription}" />
															</td>
														</tr>
													</c:forEach>
												</table>
											</td>
										</tr>
									</table>
									<table width="100%" style="margin-top: 5px;">
										<tr>
											<td>
												<input type="button" class="button1" onclick="addRight();"
													value="新 增">
												<input type="button" class="button1" onclick="editRight();"
													value="修 改">
												<input type="button" class="button1" onclick="delRight();"
													value="删 除">
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</html:form>

	</body>
</html>
