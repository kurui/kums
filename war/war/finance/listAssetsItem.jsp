<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
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
				<html:form action="/finance/assetsItemList.do">
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
							<td valign="top" class="body">
								<c:import url="../page/mainTitle.jsp" charEncoding="UTF-8">
									<c:param name="title1" value="财务管理" />
									<c:param name="title2" value="资产项目列表" />
								</c:import>
								<div class="searchBar">
									<table cellpadding="0" cellspacing="0" border="0"
										class="searchPanel">
										<tr>
											<td>
												搜索(编号/名称)：
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
												项目编号
											</div>
										</th>
										<th>
											<div>
												项目名称
											</div>
										</th>
										<th>
											<div>
												项目类型
											</div>
										</th>
										<th>
											<div>
												数量
											</div>
										</th>										
										<th>
											<div>
												备注
											</div>
										</th>
										<th>
											<div>
												状态
											</div>
										</th>
									</tr>
									<c:forEach var="assetsItem" items="${assetsItemListForm.list}"
										varStatus="status">
										<tr>
											<td>
												<html:multibox property="selectedItems"
													value="${assetsItem.id}"></html:multibox>
											</td>
											<td>
												<c:out
													value="${status.count+(assetsItemListForm.intPage-1)*assetsItemListForm.perPageNum}" />
											</td>
											<td>
												<a
													href="<%=path%>/finance/assetsItemList.do?thisAction=view&id=<c:out value="${assetsItem.id}" />">
													<c:out value="${assetsItem.itemNo}" /> </a>
											</td>
											
											<td>
												<c:out value="${assetsItem.name}" />
											</td>
											<td>
												<c:out value="${assetsItem.itemType}" />
											</td>
											<td>
												<c:out value="${assetsItem.itemCount}" />
											</td>
											<td>
												<c:out value="${assetsItem.memo}" />
											</td>
											<td>
												<c:out value="${assetsItem.statusInfo}" />
											</td>
										</tr>
									</c:forEach>
								</table>
								<table width="100%" style="margin-top: 5px;">
									<tr>
										<td>
											<input name="label" type="button" class="button1" value="新 增"
												onclick="add();">
											<input name="label" type="button" class="button1" value="编 辑"
												onclick="edit();">
											<input name="label" type="button" class="button1" value="删 除"
												onclick="del();">
										</td>
										<td align="right">
											<div>
												共有记录&nbsp;
												<c:out value="${assetsItemListForm.totalRowCount}"></c:out>
												&nbsp;条&nbsp;&nbsp;&nbsp;&nbsp; [
												<a href="JavaScript:turnToPage(document.forms[0],0)">首页</a>
												|
												<a href="JavaScript:turnToPage(document.forms[0],1)">上一页</a>
												|
												<a href="JavaScript:turnToPage(document.forms[0],2)">下一页</a>
												|
												<a href="JavaScript:turnToPage(document.forms[0],3)"> 末页</a>]
												页数:
												<c:out value="${assetsItemListForm.intPage}" />
												/
												<c:out value="${assetsItemListForm.pageCount}" />
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