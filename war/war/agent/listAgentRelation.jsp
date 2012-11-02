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
<script type="text/javascript" language="javascript"
	src="../_js/jquery-1.3.2.min.js"></script>
<script src="../_js/prototype/common.js" type="text/javascript"></script>

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
	</script>
</head>
<body>
	<div id="mainContainer">
		<div id="container">
			<html:form action="/agent/agentRelationList.do">
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
								<c:param name="title2" value="客户关系列表" />
							</c:import>
							<div class="searchBar">
								<table cellpadding="0" cellspacing="0" border="0"
									class="searchPanel">
									<tr>
										<td>客户(编号/姓名/QQ/EMail/TelPone)：</td>
										<td><html:text property="contactWay"
												styleClass="colorblue2 p_5" style="width:150px;" /></td>
										<td>类型：</td>
										<td><html:select property="relationType"
												styleClass="colorblue2 p_5" style="width:80px;">
												<option value="">-请选择</option>
												<option value="1">上下级</option>
												<option value="11">同级</option>
												<option value="21">密友</option>
												<option value="31">情侣</option>
												<option value="35">夫妻</option>
												<option value="38">子女</option>
												<option value="40">亲属</option>
												<option value="51">门生故吏</option>
											</html:select></td>
										<td>状态：</td>
										<td><html:select property="status"
												styleClass="colorblue2 p_5" style="width:80px;">
												<option value="">全部</option>
												<option value="1">有效</option>
												<option value="2">历史</option>
												<option value="0">无效</option>
											</html:select></td>
										<td><input type="submit" name="button" id="button"
											value="提交" class="submit greenBtn" /></td>
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
										<div>参照系</div>
									</th>
									<th>
										<div>对象</div>
									</th>
									<th>
										<div>关系</div>
									</th>
									<th>
										<div>处理人</div>
									</th>
									<th>
										<div>处理时间</div>
									</th>
									<th>
										<div>状态</div>
									</th>
									<th>
										<div>操作</div>
									</th>
								</tr>
								<c:forEach var="agentRelation"
									items="${agentRelationListForm.list}" varStatus="status">
									<tr>
										<td><html:multibox property="selectedItems"
												value="${agentRelation.id}"></html:multibox></td>
										<td><c:out
												value="${status.count+(agentRelationListForm.intPage-1)*agentRelationListForm.perPageNum}" />
										</td>
										<td><c:out value="${agentRelation.rootAgent.agentNo}" />
											| <a
											href="<%=path%>/agent/agentList.do?thisAction=view&id=<c:out value="${agentRelation.rootAgent.id}" />">
												<c:out value="${agentRelation.rootAgent.name}" />
										</a></td>
										<td><c:out value="${agentRelation.relateAgent.agentNo}" />
											| <a
											href="<%=path%>/agent/agentList.do?thisAction=view&id=<c:out value="${agentRelation.relateAgent.id}" />">
												<c:out value="${agentRelation.relateAgent.name}" />
										</a></td>
										<td><c:out value="${agentRelation.relationTypeInfo}" />
										</td>
										<td><c:out value="${agentRelation.userName}" /></td>
										<td><c:out value="${agentRelation.updateDate}" /></td>
										<td><c:out value="${agentRelation.statusInfo}" /></td>
										<td><a
											href="<%=path%>/agent/agentRelationList.do?thisAction=view&id=<c:out value="${agentRelation.id}" />">
												查看</a>| <a
											href="<%=path%>/agent/agentRelationList.do?thisAction=listGroupChart&agentId=<c:out value="${agentRelation.rootAgent.id}" />">
												谱系</a></td>
									</tr>
								</c:forEach>
							</table>
							<table width="100%" style="margin-top: 5px;">
								<tr>
									<td><input name="label" type="button" class="button1"
										value="新增" onclick="add();"><input name="label" type="button" class="button1"
										value="编辑" onclick="edit();"><input name="label" type="button" class="button1"
										value="删 除" onclick="del();"></td>
									<td align="right">
										<div>
											共有记录&nbsp;
											<c:out value="${agentRelationListForm.totalRowCount}"></c:out>
											&nbsp;条&nbsp;&nbsp;&nbsp;&nbsp; [ <a
												href="JavaScript:turnToPage(document.forms[0],0)">首页</a> | <a
												href="JavaScript:turnToPage(document.forms[0],1)">上一页</a> |
											<a href="JavaScript:turnToPage(document.forms[0],2)">下一页</a>
											| <a href="JavaScript:turnToPage(document.forms[0],3)">
												末页</a>] 页数:
											<c:out value="${agentRelationListForm.intPage}" />
											/
											<c:out value="${agentRelationListForm.pageCount}" />
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
		</div>
	</div>
</body>
</html>