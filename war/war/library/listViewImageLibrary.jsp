﻿<%@ page contentType="text/html;charset=UTF-8" language="java"
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
<script type="text/javascript" src="<%=path%>/_js/prototype/common.js"></script>
<script type="text/javascript" src="<%=path%>/_js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="<%=path%>/_js/base/FormUtil.js"></script>
<script type="text/javascript"
	src="<%=path%>/_js/calendar/WdatePicker.js"></script>
<c:import url="../page/importDWR.jsp"></c:import>
<script type="text/javascript">
	function add(){
	    document.forms[0].thisAction.value="save";
	    document.forms[0].submit();
	}	
	
	function edit()	{
		if(document.forms[0].selectedItems==null){
			alert("没有数据，无法修改！");
		}else if (sumCheckedBox(document.forms[0].selectedItems)<1){
	   		alert("您还没有选择数据！");
		}else if (sumCheckedBox(document.forms[0].selectedItems)>1){
	      	alert("您一次只能选择一个数据！");
		}else {
	    	document.forms[0].thisAction.value="edit";
	    	document.forms[0].submit();
	  	}
	}
	
	function del()	{	
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
			<html:form action="/library/imageLibraryList.do">
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
								<c:param name="title1" value="核心服务库" />
								<c:param name="title2" value="图片橱窗" />
							</c:import>



							<table width="100%" cellpadding="0" cellspacing="0" border="0"
								class="dataList">

								<c:forEach var="imageLibrary"
									items="${imageLibraryListForm.list}" varStatus="status">
									<c:if test="${status.count==1}">
										<tr>
									</c:if>
									<c:if test="${status.count>1}">
										<c:if test="${status.count%5==0}">
											<tr>
										</c:if>
									</c:if>
									<td><img width="50" height="50"
										src="<%=path%>/library/imageLibraryList.do?thisAction=viewImage&height=50&width=50&id=<c:out value="${imageLibrary.id}" />">
										<html:multibox property="selectedItems"
											value="${imageLibrary.id}"></html:multibox> <a
										href="<%=path%>/library/imageLibraryList.do?thisAction=view&id=<c:out value="${imageLibrary.id}" />"><c:out
												value="${imageLibrary.name}" /></a></td>
									<c:if test="${status.count==1}">
										</tr>
									</c:if>
									<c:if test="${status.count>1}">
										<c:if test="${status.count%5==0}">
											</tr>
										</c:if>
									</c:if>
								</c:forEach>

							</table>

							<table width="100%" style="margin-top: 5px;">
								<tr>
									<td><input name="label" type="button" class="button1"
										value="新 增" onclick="add();"> <input name="label"
										type="button" class="button1" value="修 改" onclick="edit();">
										<input name="label" type="button" class="button1" value="删 除"
										onclick="del();"></td>
									<td align="right">
										<div>
											共有记录&nbsp;
											<c:out value="${imageLibraryListForm.totalRowCount}"></c:out>
											&nbsp;条&nbsp;&nbsp;&nbsp;&nbsp; [ <a
												href="JavaScript:turnToPage(document.forms[0],0)">首页</a> | <a
												href="JavaScript:turnToPage(document.forms[0],1)">上一页</a> |
											<a href="JavaScript:turnToPage(document.forms[0],2)">下一页</a>
											| <a href="JavaScript:turnToPage(document.forms[0],3)">
												末页</a>] 页数:
											<c:out value="${imageLibraryListForm.intPage}" />
											/
											<c:out value="${imageLibraryListForm.pageCount}" />
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