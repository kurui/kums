<%@ page language="java" pageEncoding="UTF-8"%>

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
		<script type="text/javascript">
		function editAddress(id){
			if(id!=null&&id>0){
				agentBiz.getAgentById(id,function(agentObj){
					if(agentObj!=null){
						document.forms["editAgentForm"].id.value=agentObj.id;
						document.forms["editAgentForm"].agentNo.value=agentObj.agentNo+"|"+agentObj.name;
						document.forms["editAgentForm"].position.value=agentObj.position;
						document.forms["editAgentForm"].qqCode.value=agentObj.qqCode;
						document.forms["editAgentForm"].mobilePhone.value=agentObj.mobilePhone;
						document.forms["editAgentForm"].memo.value=agentObj.memo;						
						
						document.getElementById("searchBar").style.display="none";
						document.getElementById("editAddressTable").style.display="";
					}
				});
			}				
		}
		
		function updateAddress(){			
			document.forms["editAgentForm"].thisAction.value="updateAddress";
			document.forms["editAgentForm"].submit();
		}
		
		function cancelEditAddress(){
			document.getElementById("searchBar").style.display="";
			document.getElementById('editAddressTable').style.display='none'
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
								<c:param name="title1" value="客户管理" />
								<c:param name="title2" value="通讯录" />
							</c:import>

							<html:form action="/agent/agent.do" styleId="editAgentForm">
								<table width="100%" cellpadding="0" cellspacing="0" border="0"
									class="dataList" id="editAddressTable" style="display: none">
									<tr>
										<td style="text-align: left" colspan="3">
											<html:text property="agentNo" name="agent"
												styleClass="colorblue2 p_5" style="width:180px;" disabled="true"></html:text>
											<html:hidden property="id"></html:hidden>
											<html:hidden property="thisAction" />
										</td>
										<td>
											<html:text property="position" name="agent"
												styleClass="colorblue2 p_5" style="width:200px;"></html:text>
										</td>
										<td>
											<html:text property="qqCode" name="agent"
												styleClass="colorblue2 p_5" style="width:180px;"></html:text>
										</td>
										<td>
											<html:text property="mobilePhone" name="agent"
												styleClass="colorblue2 p_5" style="width:200px;"></html:text>
										</td>
										<td>
											<html:text property="memo" name="agent"
												styleClass="colorblue2 p_5" style="width:200px;display:none"></html:text>
										</td>

										<td>
											<input name="label" type="button" class="button1" value="保存"
												onclick="updateAddress();">
											<input name="label" type="button" class="button1" value="取消"
												onclick="cancelEditAddress();">
										</td>
									</tr>
								</table>
							</html:form>

							<div class="searchBar" id="searchBar">
								<html:form action="/agent/agentList.do" styleId="listAgentForm">
									<html:hidden property="thisAction" />
									<html:hidden property="lastAction" />
									<html:hidden property="intPage" />
									<html:hidden property="pageCount" />
									<table cellpadding="0" cellspacing="0" border="0"
										class="searchPanel">
										<tr>
											<td>
												分管部门：
											</td>
											<td>
												<html:select property="companyId"
													styleClass="colorblue2 p_5" style="width:120px;">
													<html:option value="">
														请选择
													</html:option>
													<c:forEach items="${companyList}" var="company">
														<html:option value="${company.id}">
															<c:out value="${company.name}" />
														</html:option>
													</c:forEach>
												</html:select>
											</td>
											<td>
												客户(编号/姓名/QQ/EMail/TelPone)：
											</td>
											<td>
												<html:text property="contactWay" styleClass="colorblue2 p_5"
													style="width:150px;" />
											</td>
											<td>
												类型：
											</td>
											<td>
												<html:select property="type" styleClass="colorblue2 p_5"
													style="width:80px;">
													<html:option value="0">
														请选择
													</html:option>
													<html:option value="1">
														VIP客户
													</html:option>
													<html:option value="2">
														主要客户
													</html:option>
													<html:option value="3">
														普通客户
													</html:option>
													<html:option value="4">
														小客户
													</html:option>
													<html:option value="11">
														潜在客户
													</html:option>
												</html:select>
											</td>
											<td>
												<input type="submit" name="button" id="button" value="提交"
													class="submit greenBtn" />
											</td>
										</tr>

									</table>
							</div>
							<table width="100%" cellpadding="0" cellspacing="0" border="0"
								class="dataList">
								<tr>
									<th width="60">
										<div>
											&nbsp;请选择
										</div>
									</th>
									<th width="35">
										<div>
											&nbsp;序号
										</div>
									</th>
									<th>
										<div>
											客户
										</div>
									</th>
									<th>
										<div>
											职位
										</div>
									</th>
									<th>
										<div>
											QQ
										</div>
									</th>
									<th>
										<div>
											手机
										</div>
									</th>
									<th style="display: none">
										<div>
											备注
										</div>
									</th>
									<th>
										<div>
											操作
										</div>
									</th>
								</tr>
								<c:forEach var="agent" items="${agentListForm.list}"
									varStatus="status">
									<tr>
										<td>
											<html:multibox property="selectedItems" value="${agent.id}"></html:multibox>
										</td>
										<td>
											<c:out
												value="${status.count+(agentListForm.intPage-1)*agentListForm.perPageNum}" />
										</td>
										<td>
											<div align="left">
												<c:out value="${agent.agentNo}" />
												|
												<a
													href="<%=path%>/agent/agentList.do?thisAction=view&id=<c:out value="${agent.id}" />">
													<c:out value="${agent.name}" /> </a>
											</div>
										</td>
										<td>
											<c:out value="${agent.position}" />
										</td>

										<td>
											<c:out value="${agent.qqCode}" />
										</td>
										<td>
											<c:out value="${agent.mobilePhone}" />
										</td>
										<td style="display: none">
											<c:out value="${agent.memo}" />
										</td>
										<td>
											<a href="#"
												onclick="editAddress('<c:out value="${agent.id}" />')">修改</a>
										</td>

									</tr>
								</c:forEach>
							</table>
							<table width="100%" style="margin-top: 5px;">
								<tr>
									<td>
										<input name="label" type="button" class="button1" value="全选"
											onclick="selectAll();">
										<input name="label" type="button" class="button1" value="全不选"
											onclick="selectNone();">
										<input name="label" type="button" class="button1" value="新 增"
											onclick="add();">
										<input name="label" type="button" class="button1" value="修 改"
											onclick="edit();">
										<input name="label" type="button" class="button1" value="发短信"
											onclick="sendMessage();">
									</td>
									<td align="right">
										<div>
											共有记录&nbsp;
											<c:out value="${agentListForm.totalRowCount}"></c:out>
											&nbsp;条&nbsp;&nbsp;&nbsp;&nbsp; [
											<a href="JavaScript:turnToPage(document.forms[0],0)">首页</a> |
											<a href="JavaScript:turnToPage(document.forms[0],1)">上一页</a>
											|
											<a href="JavaScript:turnToPage(document.forms[0],2)">下一页</a>
											|
											<a href="JavaScript:turnToPage(document.forms[0],3)"> 末页</a>]
											页数:
											<c:out value="${agentListForm.intPage}" />
											/
											<c:out value="${agentListForm.pageCount}" />
											]
										</div>
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
			</div>
		</div>
		<script type="text/javascript">
	function selectAll(){
		var selectedItems = document.forms["listAgentForm"].selectedItems;
		for(i=0;i<selectedItems.length;i++){
			selectedItems[i].checked = true;
		}
	}
	function selectNone(){
		var selectedItems = document.forms["listAgentForm"].selectedItems;
		for(i=0;i<selectedItems.length;i++){
			selectedItems[i].checked = false;
		}
	}
	
	function add()	{
	    document.forms["listAgentForm"].thisAction.value="save";
	    document.forms["listAgentForm"].submit();
	}
	
	function edit(){
	 	if(document.forms["listAgentForm"].selectedItems==null){
			alert("没有数据，无法修改！");
		}else if (sumCheckedBox(document.forms["listAgentForm"].selectedItems)<1){
	   		alert("您还没有选择数据！");
	 	}else if (sumCheckedBox(document.forms["listAgentForm"].selectedItems)>1){
	    	alert("您一次只能选择一条数据！");
	  	}else{
	    	document.forms["listAgentForm"].thisAction.value="edit";
	    	document.forms["listAgentForm"].submit();
	  	}
	}
	
	function del(){	
	 if(document.forms["listAgentForm"].selectedItems==null){
		alert("没有数据，无法修改！");
	 }else if (sumCheckedBox(document.forms["listAgentForm"].selectedItems)<1){
	    alert("您还没有选择数据！");
	 }else if(confirm("您真的要删除选择的这些数据吗？")){
	    document.forms["listAgentForm"].thisAction.value="delete";
	    document.forms["listAgentForm"].submit();
	  }
	}
	
	function sendMessage(){
		if (sumCheckedBox(document.forms["listAgentForm"].selectedItems)<1){
	   	 	alert("请选择需要发送短信的记录");
	   	 	return;
	    }
		 document.forms["listAgentForm"].thisAction.value="sendMessagePage";
		 document.forms["listAgentForm"].submit();
	}
	</script>
	</body>
</html>