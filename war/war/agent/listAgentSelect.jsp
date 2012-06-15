<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%
	String path = request.getContextPath();
%>
<html>
	<head>
		<title>选择商户</title>
		<link href="../_css/reset.css" rel="stylesheet" type="text/css" />
		<link href="../_css/global.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" language="javascript"
			src="../_js/jquery-1.3.2.min.js"></script>
		<script src="../_js/prototype/common.js" type="text/javascript"></script>
		<script src="../_js/base/FormUtil.js" type="text/javascript"></script>

		<script type="text/javascript"> 			
		function confirmAgent(){
			var values="";			
			var selectedItems=document.forms[0].selectedItems;
			var chklength=selectedItems.length;
			
			if(chklength>1){				
				for(var i=0;i<chklength;i++){
					var chk=document.forms[0].selectedItems[i];
					if(chk.checked){
						values += chk.value+",";
					}				
				}	
			}else{
				if(selectedItems!=null){
					values=selectedItems.value;
				}					
			}
					
			if(values.indexOf(",", values.length-1)>1){				
				values=values.substring(0,values.length-1);
			}			
			opener.addAgentId(values);
    		window.close();
		}   
		
	 </script>
	</head>
	<body>
		<div id="mainContainer">
			<div id="container">
				<html:form action="/agent/agentList.do">
					<html:hidden property="thisAction" name="agentListForm" />
					<html:hidden property="lastAction" name="agentListForm" />
					<html:hidden property="intPage" name="agentListForm" />
					<html:hidden property="pageCount" name="agentListForm" />
					<table width="100%" cellpadding="0" cellspacing="0" border="0">
						<tr>
							<td width="10" height="10" class="tblt"></td>
							<td height="10" class="tbtt"></td>
							<td width="10" height="10" class="tbrt"></td>
						</tr>
						<tr>
							<td width="10" class="tbll"></td>
							<td valign="top" class="body">
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
												类型
											<td>
												<html:select property="type" styleClass="colorblue2 p_5"
													style="width:80px;">
													<html:option value="">
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
												<input type="submit" name="button" id="button" value="查询"
													class="submit greenBtn" />
											</td>
										</tr>
									</table>
								</div>
								<table width="100%" cellpadding="0" cellspacing="0" border="0"
									class="dataList">
									<tr>
										<th>
											<div>
												序号
											</div>
										</th>
										<th>
											<div
												style="height: 100%; width: 100%; vertical-align: center; padding-top: 7px;">
												<input type="checkbox"
													onclick="checkAll(this, 'selectedItems')" name="sele" />
												全选
											</div>
										</th>
										<th>
											<div>
												客户编号
											</div>
										</th>
										<th>
											<div>
												名称
											</div>
										</th>
										<th>
											<div>
												联系方式
											</div>
										</th>
										<th>
											<div>
												类型
											</div>
										</th>
										<th>
											<div>
												状态
											</div>
										</th>
									</tr>
									<c:forEach var="info" items="${agentListForm.list}"
										varStatus="status">
										<tr>
											<td>
												<c:out
													value="${status.count+(agentListForm.intPage-1)*agentListForm.perPageNum}" />

											</td>
											<td>
												<html:multibox property="selectedItems" value="${info.id}"
													onclick="checkItem(this, 'sele')">
												</html:multibox>
											</td>
											<td>
												<c:out value="${info.agentNo}" />
											</td>
											<td>
												<c:out value="${info.name}" />
											</td>
											<td>
												<c:out value="${info.mobilePhone}" />
											</td>
											<td>
												<c:out value="${info.typeInfo}" />
											</td>
											<td>
												<c:out value="${info.statusInfo}" />
											</td>
										</tr>
									</c:forEach>
								</table>

								<div class="splitLine"></div>
								<div class="">
									<table width="100%" style="margin-top: 5px;">
										<tr>
											<td>
												<input name="label" type="button" class="button1" value="确定"
													onclick="confirmAgent();">
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
													<a href="JavaScript:turnToPage(document.forms[0],3)">
														末页</a>] 页数:
													<c:out value="${agentListForm.intPage}" />
													/
													<c:out value="${agentListForm.pageCount}" />
													]
												</div>
											</td>
										</tr>
									</table>
									<div class="clear"></div>
								</div>
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
