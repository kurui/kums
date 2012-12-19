<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%
	String path = request.getContextPath();
%>
<html>
	<head>
		<title>main</title>
		<link href="../_css/reset.css" rel="stylesheet" type="text/css" />
		<link href="../_css/global.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
		function addSameLevel(){
		    document.forms[0].action="<%=path%>/library/dataTypeList.do?thisAction=saveSameLevel&superNo="+<c:out value="${dataType.superNo}" />;
		    document.forms[0].submit();
		}
		
		function addAppoint(){
		    document.forms[0].action="<%=path%>/library/dataTypeList.do?thisAction=saveAppoint&no="+<c:out value="${dataType.no}" />;
		    document.forms[0].submit();
		}
		
		function edit(){
		    document.forms[0].action="<%=path%>/library/dataTypeList.do?thisAction=edit";
		    document.forms[0].submit();
		}
		
		
		function editItem(id,superNo,no,name){
			//alert(id+"-"+no+"-"+name);
			document.forms[1].id.value=id;
			document.forms[1].superNo.value=superNo;			
			document.forms[1].no.value=no;
			document.forms[1].name.value=name;
			document.forms[1].thisAction.value="updateItem";	
			document.getElementById("editTable").style.display="";		
		}
		
		function deleteItem(id,superNo)	{	
			document.forms[1].id.value=id;
			document.forms[1].superNo.value=superNo;
		  if(confirm("您真的要删除吗？")){
		    document.forms[1].thisAction.value="deleteItem";
		    document.forms[1].submit();
		  }
		}
		
		function addItem()		{		   		
			document.forms[1].no.value="";
			document.forms[1].name.value="";	
			document.forms[1].superNo.value='<c:out value="${dataType.no}" />';
		    document.forms[1].thisAction.value="insertItem";
			document.getElementById("editTable").style.display="";	
		}
		
		function saveItem()		{
			var thisAction =document.forms[1].thisAction.value;			   
		    document.forms[1].thisAction.value=thisAction;		   
		    document.forms[1].submit();
		}
	</script>
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
								<c:param name="title1" value="系统管理" />
								<c:param name="title2" value="查看交易类型" />
							</c:import>
							<hr>
							<table width="100%" cellpadding="0" cellspacing="0" border="0"
								class="dataList">

								<tr>
									<td class="lef">
										项目
									</td>
									<td style="text-align: left">
										<c:if test="${!empty dataType.supDataType}">
											<a
												href="<%=path%>/library/dataTypeList.do?thisAction=view&id=<c:out value="${dataType.supDataType.id}" />">
												<c:out value="${dataType.supDataType.name}" /> <c:out
													value="${dataType.supDataType.no}" /> </a> >>
											</c:if>
										<a href="#"><c:out value="${dataType.name}" /> <c:out
												value="${dataType.no}" /> </a>
									</td>
									<td class="lef">
										状态
									</td>
									<td style="text-align: left">
										<c:out value="${dataType.createDate}" />
										<c:out value="${dataType.statusInfo}" />
									</td>
								</tr>
								<tr>
									<td class="lef">
										备注
									</td>
									<td style="text-align: left" colspan="3">
										<c:out value="${dataType.memo}" />
									</td>
								</tr>
							</table>
							<table width="100%" style="margin-top: 5px;">
								<tr>
									<td align="center">
										<html:form action="/library/dataTypeList.do">
											<html:hidden property="selectedItems" value="${dataType.id}" />

											<html:hidden property="thisAction" />
											<input name="label" type="button" class="button1"
												value="新增同级" onclick="addSameLevel();" />
											<input name="label" type="button" class="button1"
												value="新增下级" onclick="addAppoint();" style="display: none" />
											<input name="label" type="button" class="button1"
												value="新增下级" onclick="addItem()" />
											<input name="label" type="button" class="button1" value="返 回"
												onclick="window.history.back();">
											<input name="label" type="button" class="button1" value="修 改"
												onclick="edit();" />
										</html:form>
									</td>
								</tr>
								<tr>
									<td style="text-align: left" colspan="4">
										&nbsp;
									</td>
								</tr>
								<tbody id="editTable" style="display: none; width: 50%;">
									<html:form action="/library/dataType.do">
										<html:hidden property="id" name="dataType" />
										<html:hidden property="thisAction" name="dataType" />
										<html:hidden property="superNo" name="dataType" />
										<tr>
											<td class="lef">
												代码
												<html:text property="no" name="dataType"
													styleClass="colorblue2 p_5" style="width:200px;"></html:text>
											</td>
										</tr>
										<tr>
											<td class="lef">
												名称
												<html:text property="name" name="dataType"
													styleClass="colorblue2 p_5" style="width:200px;"></html:text>

												<input name="label" type="button" class="button1"
													value="保 存" onclick="saveItem();" />
											</td>
										</tr>
									</html:form>
								</tbody>
							</table>
							<table width="100%" cellpadding="0" cellspacing="0" border="0"
								class="dataList">
								<c:forEach var="dataType" items="${subDataTypeList}"
									varStatus="status">

									<tr>
										<td>
											<a
												href="<%=path%>/library/dataTypeList.do?thisAction=view&id=<c:out value="${dataType.id}" />">
												<c:out value="${dataType.name}" /> </a>
										</td>
										<td>
											<c:out value="${dataType.no}" />
											<a href="#"
												onclick="editItem('<c:out value="${dataType.id}" />','<c:out value="${dataType.superNo}" />','<c:out value="${dataType.no}" />','<c:out value="${dataType.name}" />')">编辑</a>
											<a href="#"
												onclick="deleteItem('<c:out value="${dataType.id}" />','<c:out value="${dataType.superNo}" />')">删除</a>
										</td>
										<td>
											<a
												href="<%=path%>/library/dataTypeList.do?thisAction=listSub&no=<c:out value="${dataType.no}" />">下级</a>|
											<a
												href="<%=path%>/library/dataTypeList.do?thisAction=saveSameLevel&superNo=<c:out value="${dataType.superNo}" />">新增同级</a>|
											<a
												href="<%=path%>/library/dataTypeList.do?thisAction=saveAppoint&no=<c:out value="${dataType.no}" />">新增下级</a>
										</td>
										<td>
											<c:out value="${dataType.memo}" />
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

			</div>
		</div>
	</body>
</html>