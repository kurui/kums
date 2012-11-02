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
		<script src="../_js/prototype/common.js" type="text/javascript"></script>

		<script type="text/javascript">
	function selectAll(){
		var selectedItems = document.forms[0].selectedItems;
		for(i=0;i<selectedItems.length;i++){
			selectedItems[i].checked = true;
		}
	}
	function selectNone(){
		var selectedItems = document.forms[0].selectedItems;
		for(i=0;i<selectedItems.length;i++){
			selectedItems[i].checked = false;
		}
	}
	
	function add()	{
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
	
	function sendMessage(){
		if (sumCheckedBox(document.forms[0].selectedItems)<1){
	   	 	alert("请选择需要发送短信的记录");
	   	 	return;
	    }
		 document.forms[0].thisAction.value="sendMessagePage";
		 document.forms[0].submit();
	}
	</script>
	</head>
	<body>
		<div id="mainContainer">
			<div id="container">
				<html:form action="/agent/agentList.do">
					<html:hidden property="thisAction" />
					<html:hidden property="lastAction" />
					<html:hidden property="intPage" />
					<html:hidden property="pageCount" />
					<html:hidden property="operatorObject" value="team" />

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
									<c:param name="title2" value="客户列表" />
								</c:import>
								<div class="searchBar">
									<table cellpadding="0" cellspacing="0" border="0"
										class="searchPanel">
										<tr>

											<td>
												客户(编号/姓名/QQ/EMail/TelPone)：
											</td>
											<td>
												<html:text property="contactWay" styleClass="colorblue2 p_5"
													style="width:150px;" />
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
												类型
											</div>
										</th>
										<th>
											<div>
												客户
											</div>
										</th>
										<th>
											<div>
												信用额度|消费积分
											</div>
										</th>
										<th>
											<div>
												修改时间
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
											<td style="text-align: left">
												<c:out value="${agent.sexInfo}" />

												<c:if test="${!empty agent.directLevel}">
													<c:out value="${agent.directLevel.name}" />
												</c:if>
											</td>
											<td style="text-align: left">
												<c:out value="${agent.agentNo}" />
												|
												<a
													href="<%=path%>/agent/agentList.do?thisAction=view&id=<c:out value="${agent.id}" />">
													<c:out value="${agent.name}" /> </a>
											</td>
											
											<td>
												<c:out value="${agent.creditAmount}" />
												|
												<c:out value="${agent.totalIntegral}" />
											</td>
											<td>
												<c:out value="${agent.updateDate}" />
											</td>
											<td>
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
											<input name="label" type="button" class="button1" value="删 除"
												onclick="del();" style="display: none;">
										</td>
										<td align="right">
											<div>
												共有记录&nbsp;
												<c:out value="${agentListForm.totalRowCount}"></c:out>
												&nbsp;条&nbsp;&nbsp;&nbsp;&nbsp; [
												<a href="JavaScript:turnToPage(document.forms[0],0)">首页</a>
												|
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
	</body>
</html>