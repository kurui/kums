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
		
		function addAgentResumeForm(){
				document.forms["editAgentResumeForm"].id.value="";
				document.forms["editAgentResumeForm"].agentId.value="<c:out value="${agent.id}"/>";
				
				document.forms["editAgentResumeForm"].tag.value="";
				document.forms["editAgentResumeForm"].content.value="";	
				document.forms["editAgentResumeForm"].thisAction.value="insert";
					
				document.getElementById("editAgentResumeTable").style.display="";			
		}
		
		function editAgentResumeForm(id){
			if(id!=null&&id>0){
				agentResumeBiz.getAgentResumeById(id,function(agentResumeObj){
					if(agentResumeObj!=null){
						document.forms["editAgentResumeForm"].id.value=agentResumeObj.id;
						document.forms["editAgentResumeForm"].tag.value=agentResumeObj.tag;
						document.forms["editAgentResumeForm"].content.value=agentResumeObj.content;
						
						js.select.markSelected(document.forms["editAgentResumeForm"].type,agentResumeObj.type);
						js.select.markSelected(document.forms["editAgentResumeForm"].status,agentResumeObj.status);	
						
						document.forms["editAgentResumeForm"].thisAction.value="update";
						document.getElementById("editAgentResumeTable").style.display="";
					}
				});
			}				
		}
		
		function updateAgentResume(){		
			document.forms["editAgentResumeForm"].submit();
		}
		
		function cancelEditAgentResume(){
			document.getElementById("searchBar").style.display="";
			document.getElementById('editAgentResumeTable').style.display='none'
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
				<html:form action="/agent/agentResumeList.do"  styleId="listAgentResumeForm">
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
									<c:param name="title2" value="查看简历" />
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
									<c:forEach var="agentResume" items="${agentResumeList}" varStatus="status">
										<tr>
										<td>
												<html:multibox property="selectedItems" value="${agentResume.id}"  onclick="checkItem(this, 'sele')"></html:multibox>
											</td>
											<td>
												<c:out value="${status.count}" />
											</td>
											<td>
												<c:out value="${agentResume.typeInfo}" />
											</td>
											<td>
												<c:out value="${agentResume.content}" />
											</td>
											<td>
												<c:out value="${agentResume.tag}" />
											</td>
											<td>
												<c:out value="${agentResume.updateDate}" />
											</td>
											<td>
												<c:out value="${agentResume.statusInfo}" />
											</td>
											<td>
												<a href="<%=path%>/agent/agentResumeList.do?thisAction=view&id=<c:out value="${agentResume.id}" />">查看</a>
											<!-- 	<a href="<%=path%>/agent/agentResumeList.do?thisAction=edit&id=<c:out value="${agentResume.id}" />">编辑</a>
											 -->	<a href="#" onclick="editAgentResumeForm('<c:out value="${agentResume.id}"/>')">编辑</a>
											
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
												onclick="addAgentResumeForm();">
											
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
				<html:form action="/agent/agentResume.do" styleId="editAgentResumeForm">
								<table width="100%" cellpadding="0" cellspacing="0" border="0"
									class="dataList" id="editAgentResumeTable" style="display: none">
									<tr>
										<td style="text-align: left" colspan="3">
											<html:hidden property="id"></html:hidden>
											<html:hidden property="thisAction" />
											<html:hidden property="agentId"></html:hidden>
										</td>
										<td class="lef">
											类型
										</td>
										<td style="text-align: left">
											<html:select property="type" 
												name="agentResume" styleClass="colorblue2 p_5"
												style="width:100px;">
												<html:option value="0">-请选择-</html:option>
												<html:option value="1">S-手机</html:option>
												<html:option value="2">G-固定电话</html:option>
												<html:option value="11">EMAIL</html:option>
												<html:option value="12">QQ</html:option>
												<html:option value="21">Z-祖籍</html:option>
												<html:option value="31">S-收货地址</html:option>
												
											</html:select>
											
											<html:text property="tag" name="agentResume"
												value="${agentResume.tag}" styleClass="colorblue2 p_5"
												style="width:200px;"></html:text>备注
										</td>
										<td class="lef">
											内容
										</td>
										<td style="text-align: left">
											<html:text property="content" name="agentResume"
												value="${agentResume.content}" styleClass="colorblue2 p_5"
												style="width:200px;"></html:text>
										</td>
											<td class="lef">
											状态
										</td>
										<td style="text-align: left">
											<html:select property="status"
												value="${agentResume.status}" name="agentResume"
												styleClass="colorblue2 p_5" style="width:50px;">
												<html:option value="1">有效</html:option>
												<html:option value="0">无效</html:option>
											</html:select>
										</td>
										<td>
											<input name="label" type="button" class="button1" value="保存"
												onclick="updateAgentResume();">
											<input name="label" type="button" class="button1" value="取消"
												onclick="cancelEditAgentResume();">
										</td>
									</tr>
								</table>
							</html:form>
			</div>
		</div>
		<script type="text/javascript">
		function edit(id){
   			 var url="../agent/agentResumeList.do?thisAction=edit&id="+id;
    		 window.location.href=url;
 		}
		</script>
	</body>
</html>