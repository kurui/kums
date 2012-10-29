<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%
	String path = request.getContextPath();
%>
<html>
	<head>
		<title>main</title>
		<link href="<%=path%>/_css/reset.css" rel="stylesheet" type="text/css" />
		<link href="<%=path%>/_css/global.css" rel="stylesheet"
			type="text/css" />
				<c:import url="../page/importDWR.jsp"></c:import>
		<script type="text/javascript" src="<%=path%>/_js/prototype/common.js"></script>
		<script type="text/javascript" src="<%=path%>/_js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript" src="<%=path%>/_js/base/FormUtil.js"></script>
			<script type="text/javascript" src="<%=path%>/_js/base/select.js"></script>
		<script type="text/javascript">
		
		function addAgentContactForm(){
				document.forms["editAgentContactForm"].id.value="";
				document.forms["editAgentContactForm"].agentId.value="<c:out value="${agent.id}"/>";
				
				document.forms["editAgentContactForm"].tag.value="";
				document.forms["editAgentContactForm"].content.value="";	
				document.forms["editAgentContactForm"].thisAction.value="insert";
					
				document.getElementById("editAgentContactTable").style.display="";			
		}
		
		function editAgentContact(id){
			if(id!=null&&id>0){
				agentContactBiz.getAgentContactById(id,function(agentContactObj){
					if(agentContactObj!=null){
						document.forms["editAgentContactForm"].id.value=agentContactObj.id;
						document.forms["editAgentContactForm"].tag.value=agentContactObj.tag;
						document.forms["editAgentContactForm"].content.value=agentContactObj.content;
						
						js.select.markSelected(document.forms["editAgentContactForm"].type,agentContactObj.type);
						js.select.markSelected(document.forms["editAgentContactForm"].status,agentContactObj.status);	
						
						document.forms["editAgentContactForm"].thisAction.value="update";
						document.getElementById("editAgentContactTable").style.display="";
					}
				});
			}				
		}
		
		function updateAgentContact(){		
			document.forms["editAgentContactForm"].lastAction.value="viewALL";
			
			trim(document.forms["editAgentContactForm"]);
			document.forms["editAgentContactForm"].submit();
		}
		
		function cancelEditAgentContact(){
			document.getElementById("searchBar").style.display="";
			document.getElementById('editAgentContactTable').style.display='none'
		}
		
		function del(){	
			 if(document.forms[0].selectedItems==null){
				alert("没有数据，无法修改！");
			 }else if (sumCheckedBox(document.forms[0].selectedItems)<1){
			    alert("您还没有选择数据！");
			 }else if(confirm("您真的要删除选择的这些数据吗？")){
			    document.forms[0].thisAction.value="delete";
			    document.forms[0].submit();
			 }
	}

	</script>
	</head>
	<body>
		<div id="mainContainer">
			<div id="container">
				<html:form action="/agent/agentContactList.do"  styleId="listAgentContactForm">
				<html:hidden property="thisAction" />
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
									<c:param name="title1" value="客户管理" />
									<c:param name="title2" value="查看联系信息" />
								</c:import>
								<hr>
								<table width="100%" cellpadding="0" cellspacing="0" border="0"
									class="dataList">
									<tr>
										<td>
										<div
											style="height: 100%; width: 100%; vertical-align: center; padding-top: 7px;">
											<input type="checkbox"
												onclick="checkAll(this, 'selectedItems')" name="sele" />									</div>
									
										</td>
										<td colspan="7">
											<c:out value="${agent.agentNo}" />|<c:out value="${agent.name}" />
										</td>
									</tr>
									<c:forEach var="agentContact" items="${agentContactList}" varStatus="status">
										<tr>
										<td>
												<html:multibox property="selectedItems" value="${agentContact.id}"  onclick="checkItem(this, 'sele')"></html:multibox>
											</td>
											<td>
												<c:out value="${status.count}" />
											</td>
											<td>
												<c:out value="${agentContact.typeInfo}" />
											</td>
											<td>
												<c:out value="${agentContact.content}" />
											</td>
											<td>
												<c:out value="${agentContact.tag}" />
											</td>
											<td>
												<c:out value="${agentContact.updateDate}" />
											</td>
											<td>
												<c:out value="${agentContact.statusInfo}" />
											</td>
											<td>
												<a href="<%=path%>/agent/agentContactList.do?thisAction=view&id=<c:out value="${agentContact.id}" />">查看</a>
											<!-- 	<a href="<%=path%>/agent/agentContactList.do?thisAction=edit&id=<c:out value="${agentContact.id}" />">编辑</a>
											 -->	<a href="#" onclick="editAgentContact('<c:out value="${agentContact.id}"/>')">编辑</a>
											
											</td>
										</tr>
									</c:forEach>
								</table>
								<table width="100%" style="margin-top: 5px;">
									<tr>
										<td align="center">
											<input name="label" type="button" class="button1" value="删 除" onclick="del();">
										
											<input name="label" type="button" class="button1" value="返回"
												onclick="window.history.back();">
											<input name="label" type="button" class="button1" value="新增"
												onclick="addAgentContactForm();">
											
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
				<html:form action="/agent/agentContact.do" styleId="editAgentContactForm">
								<table width="100%" cellpadding="0" cellspacing="0" border="0"
									class="dataList" id="editAgentContactTable" style="display: none">
									<tr>
										<td style="text-align: left" colspan="3">
											<html:hidden property="id" />
											<html:hidden property="agentId" />
										</td>
										<td class="lef">
											类型
										</td>
										<td style="text-align: left">
											<html:select property="type" 
												name="agentContact" styleClass="colorblue2 p_5"
												style="width:100px;">
												<html:option value="0">-请选择-</html:option>
												<html:option value="1">S-手机</html:option>
												<html:option value="2">G-固定电话</html:option>
												<html:option value="11">EMAIL</html:option>
												<html:option value="12">QQ</html:option>
												<html:option value="21">Z-祖籍</html:option>
												<html:option value="31">S-收货地址</html:option>
												
											</html:select>
											
											<html:text property="tag" name="agentContact"
												value="${agentContact.tag}" styleClass="colorblue2 p_5"
												style="width:200px;"></html:text>备注
										</td>
										<td class="lef">
											内容
										</td>
										<td style="text-align: left">
											<html:text property="content" name="agentContact"
												value="${agentContact.content}" styleClass="colorblue2 p_5"
												style="width:200px;"></html:text>
										</td>
											<td class="lef">
											状态
										</td>
										<td style="text-align: left">
											<html:select property="status"
												value="${agentContact.status}" name="agentContact"
												styleClass="colorblue2 p_5" style="width:50px;">
												<html:option value="1">有效</html:option>
												<html:option value="0">无效</html:option>
											</html:select>
										</td>
										<td>
												<html:hidden property="thisAction" value="" />
												<html:hidden property="lastAction" value="" />
											<input name="label" type="button" class="button1" value="保存"
												onclick="updateAgentContact();">
											<input name="label" type="button" class="button1" value="取消"
												onclick="cancelEditAgentContact();">
										</td>
									</tr>
								</table>
							</html:form>
			</div>
		</div>
		<script type="text/javascript">
		function edit(id){
   			 var url="../agent/agentContactList.do?thisAction=edit&id="+id;
    		 window.location.href=url;
 		}
		</script>
	</body>
</html>