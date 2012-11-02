<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%
	String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link href="../_css/reset.css" rel="stylesheet" type="text/css" />
<link href="../_css/global.css" rel="stylesheet" type="text/css" />
<c:import url="../page/importDWR.jsp"></c:import>
<script type="text/javascript" src="<%=path%>/_js/prototype/common.js"></script>
<script type="text/javascript" src="<%=path%>/_js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="<%=path%>/_js/base/FormUtil.js"></script>
<script type="text/javascript" src="<%=path%>/_js/base/select.js"></script>
<script type="text/javascript"
	src="<%=path%>/_js/calendar/WdatePicker.js"></script>
	
	
<script type="text/javascript">
	function add(){
	    document.forms[0].thisAction.value="save";
	    document.forms[0].submit();
	}
	
	function edit(){
	 	if(document.forms[0].selectedItems==null){
			alert("没有数据，无法修改！");
		}else if (sumCheckedBox(document.forms[0].selectedItems)<1){
	   		alert("您还没有选择数据！");
	 	}else if (sumCheckedBox(document.forms[0].selectedItems)>1){
	    	alert("您一次只能选择一条数据！");
	  	}else{
	    	document.forms[0].thisAction.value="edit";
	    	document.forms[0].submit();
	  	}
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
	
		function editAgentResume(id){
			if(id!=null&&id>0){
				agentResumeBiz.getAgentResumeById(id,function(agentResumeObj){
					if(agentResumeObj!=null){
						document.forms["editAgentResumeForm"].id.value=agentResumeObj.id;
						document.forms["editAgentResumeForm"].beginDate.value=agentResumeObj.beginDate;
						document.forms["editAgentResumeForm"].endDate.value=agentResumeObj.endDate;
						
						document.forms["editAgentResumeForm"].position.value=agentResumeObj.position;
						document.forms["editAgentResumeForm"].content.value=agentResumeObj.content;
						js.select.markSelected(document.forms["editAgentResumeForm"].companyId,agentResumeObj.companyId);	
						js.select.markSelected(document.forms["editAgentResumeForm"].type,agentResumeObj.type);
						js.select.markSelected(document.forms["editAgentResumeForm"].status,agentResumeObj.status);	
						
						document.forms["editAgentResumeForm"].thisAction.value="update";
						document.getElementById("editAgentResumeTable").style.display="";
					}
				});
			}				
		}
		
		function updateAgentResume(){	
			document.forms["editAgentResumeForm"].lastAction.value="list";
			document.forms["editAgentResumeForm"].intPage.value=<c:out value="${agentResumeListForm.intPage}" />;
			document.forms["editAgentResumeForm"].pageCount.value=<c:out value="${agentResumeListForm.pageCount}" />;
			trim(document.forms["editAgentResumeForm"]);
			document.forms["editAgentResumeForm"].submit();
		}
		
		function cancelEditAgentResume(){
			document.getElementById('editAgentResumeTable').style.display='none'
		}
			

	</script>

</head>
<body>
	<div id="mainContainer">
		<div id="container">

			<html:form action="/agent/agentResumeList.do" styleId="listAgentResumeForm">
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
						<td valign="top" class="body"><c:import
								url="../page/mainTitle.jsp" charEncoding="UTF-8">
								<c:param name="title1" value="客户管理" />
								<c:param name="title2" value="客户简历列表" />
							</c:import>
							<div class="searchBar">
								<table cellpadding="0" cellspacing="0" border="0"
									class="searchPanel">
									<tr>
										<td>关键字：</td>
										<td><html:text property="keywords"
												styleClass="colorblue2 p_5" style="width:150px;" /></td>
										<!-- <td style="text-align: left"><html:select
												property="companyId" styleClass="colorblue2 p_5"
												styleId="companyId" style="width:120px;" onchange="javascript:document.forms[0].submit();">
												<html:option value="0">
										请选择
									</html:option>
												<c:forEach items="${companyList}" var="company" >
													<html:option value="${company.id}" >
														<c:out value="${company.showName}" />
													</html:option>
												</c:forEach>
											</html:select>
										</td>
										-->
										<td>	<jsp:include page="../transaction/listSearchCompanyBar.jsp"></jsp:include>
										</td>
										<td>类型：</td>
										<td><html:select property="type"
												styleClass="colorblue2 p_5" style="width:80px;">
												<html:option value="">
														请选择
													</html:option>
												<html:option value="1">
														当前
													</html:option><html:option value="2">
														历史
													</html:option>
											</html:select></td>
										<td>状态：</td>
										<td><html:select property="status"
												styleClass="colorblue2 p_5" style="width:80px;">
												<html:option value="">
														请选择
													</html:option>
												<html:option value="1">
														有效
													</html:option>
												<html:option value="0">
														无效
													</html:option>
											</html:select></td>
										<td><input type="submit" name="button" id="button"
											value="提交" class="submit greenBtn" /></td>
										<td><input name="label" type="button" class="button1"
											value="返 回" onclick="window.history.back();"></td>
									</tr>
								</table>
							</div>
							<table width="100%" cellpadding="0" cellspacing="0" border="0"
								class="dataList">
								<tr>
									<th width="60">
										<div>&nbsp;请选择</div>
									</th>
									<th width="35">
										<div>&nbsp;序号</div>
									</th>
									<th>
										<div>客户</div>
									</th>
									<th colspan="2">
										<div>时间</div>
									</th>
									<th>
										<div>公司</div>
									</th>
									<th>
										<div>职务</div>
									</th>
									<th>
										<div>说明</div>
									</th>
									<th>
										<div>类型</div>
									</th>
									<th>
										<div>状态</div>
									</th>
									<th>
										<div>操作</div>
									</th>
								</tr>
								<c:forEach var="agentResume" items="${agentResumeListForm.list}"
									varStatus="status">
									<tr>
										<td><html:multibox property="selectedItems"
												value="${agentResume.id}"></html:multibox></td>
										<td><c:out
												value="${status.count+(agentResumeListForm.intPage-1)*agentResumeListForm.perPageNum}" />
										</td>
										<td style="text-align: left"><c:out
												value="${agentResume.agent.agentNo}" /> |<a
											href="<%=path%>/agent/agentList.do?thisAction=view&id=<c:out value="${agentResume.agent.id}"/>">
												<c:out value="${agentResume.agent.name}" />
										</a></td>


										<td><c:out value="${agentResume.beginDate}" /></td>

										<td><c:out value="${agentResume.endDate}" /></td>
										<td><a
											href="<%=path%>/transaction/companyList.do?thisAction=view&id=<c:out value="${agentResume.company.id}"/>">
												<c:out value="${agentResume.company.name}" />
										</a></td>


										<td><c:out value="${agentResume.position}" /></td>
										<td><c:out value="${agentResume.content}" /></td>

										<td><c:out value="${agentResume.typeInfo}" /></td>
										<td><c:out value="${agentResume.statusInfo}" /></td>
										<td><a
											href="<%=path%>/agent/agentResumeList.do?thisAction=view&id=<c:out value="${agentResume.id}" />">
												查看</a> <a
											href="<%=path%>/agent/agentResumeList.do?thisAction=viewALL&agentId=<c:out value="${agentResume.agent.id}" />">
												个人全部</a> <a href="#"
											onclick="editAgentResume('<c:out value="${agentResume.id}"/>')">编辑</a>

										</td>
									</tr>
								</c:forEach>
							</table>
							<table width="100%" style="margin-top: 5px;">
								<tr>
									<td><input name="label" type="button" class="button1"
										value="新 增" onclick="add();"> <input name="label"
										type="button" class="button1" value="编 辑" onclick="edit();">
										<input name="label" type="button" class="button1" value="删 除"
										onclick="del();"></td>
									<td align="right">
										<div>
											共有记录&nbsp;
											<c:out value="${agentResumeListForm.totalRowCount}"></c:out>
											&nbsp;条&nbsp;&nbsp;&nbsp;&nbsp; [ <a
												href="JavaScript:turnToPage(document.forms[0],0)">首页</a> | <a
												href="JavaScript:turnToPage(document.forms[0],1)">上一页</a> |
											<a href="JavaScript:turnToPage(document.forms[0],2)">下一页</a>
											| <a href="JavaScript:turnToPage(document.forms[0],3)">
												末页</a>] 页数:
											<c:out value="${agentResumeListForm.intPage}" />
											/
											<c:out value="${agentResumeListForm.pageCount}" />
											]
										</div>
									</td>
								</tr>
							</table>
							<div class="clear"></div></td>
						<td width="10" class="tbrr"></td>
					</tr>
					<tr>
						<td width="10" class="tblb"></td>
						<td class="tbbb"></td>
						<td width="10" class="tbrb"></td>
					</tr>
				</table>
			</html:form>
			<html:form action="/agent/agentResume.do"
				styleId="editAgentResumeForm">
				<table width="100%" cellpadding="0" cellspacing="0" border="0"
					class="dataList" id="editAgentResumeTable" style="display: none">
					<th>
						<div>时间</div>
					</th>
					<th>
						<div>公司/组织</div>
					</th>
					<th>
						<div>职务</div>
					</th>
					<th>
						<div>说明</div>
					</th>
					<th>
						<div>状态</div>
					</th>
					<th>
						<div>操作</div>
					</th>


					<tr>
						<td style="text-align: left">
						<html:hidden property="id"	value="" /> <html:hidden
								property="agentId" value="" /> <html:text property="beginDate"
								styleClass="colorblue2 p_5" style="width:100px;"
								onfocus="WdatePicker({startDate:'%y-%M-%D',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" />
							- <html:text property="endDate" styleClass="colorblue2 p_5"
								style="width:100px;"
								onfocus="WdatePicker({startDate:'%y-%M-%D',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" />

						</td>
						<td style="text-align: left"><html:select
								property="companyId" styleClass="colorblue2 p_5"
								styleId="companyId" style="width:120px;">
								<html:option value="0">
										请选择
									</html:option>
								<c:forEach items="${companyList}" var="company">
									<html:option value="${company.id}">
										<c:out value="${company.showName}" />
									</html:option>
								</c:forEach>
							</html:select></td>
						<td style="text-align: left"><html:text property="position" name="agentResume"
								styleClass="colorblue2 p_5" style="width:150px;"></html:text></td>

						<td style="text-align: left"><html:text property="content"
								styleClass="colorblue2 p_5" style="width:220px;"></html:text></td>

						<td style="text-align: left"><html:select property="type"styleClass="colorblue2 p_5"
								style="width:50px;">
								<html:option value="0">-请选择-</html:option>
								<html:option value="2">历史</html:option>
								<html:option value="1">当前</html:option>
							</html:select> <html:select property="status" styleClass="colorblue2 p_5"
								style="width:50px;">
								<html:option value="1">有效</html:option>
								<html:option value="0">无效</html:option>
							</html:select></td>
						<td>
						<html:hidden property="thisAction" value="" />
						<html:hidden property="lastAction" value="" />
						<html:hidden property="intPage" value="" />
						<html:hidden property="pageCount" value="" />
						
						<input name="label" type="button" class="button1"
							value="保存" onclick="updateAgentResume();"> <input
							name="label" type="button" class="button1" value="取消"
							onclick="cancelEditAgentResume();"></td>
					</tr>
				</table>
			</html:form>
		</div>
	</div>
</body>
</html>