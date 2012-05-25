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
		<script type="text/javascript" language="javascript" src="../_js/jquery-1.3.2.min.js"></script>
		<script src="../_js/prototype/common.js" type="text/javascript"></script>
		<script src="../_js/calendar/WdatePicker.js" type="text/javascript"></script>

		<script type="text/javascript">
	function add(){
	    document.forms[0].thisAction.value="save";
	    document.forms[0].submit();
	}
	function edit(){
		if(document.forms[0].selectedItems==null){
			alert("没有数据，无法修改！");
		}else
		  if (sumCheckedBox(document.forms[0].selectedItems)<1)
		    alert("您还没有选择数据！");
		  else if (sumCheckedBox(document.forms[0].selectedItems)>1)
		    alert("您一次只能选择一个数据！");
		  else {
		    document.forms[0].thisAction.value="edit";
		    document.forms[0].submit();
	   }
	}
	
	function del(){
		 if(document.forms[0].selectedItems==null){
		alert("没有数据，无法修改！");
	 }else if (sumCheckedBox(document.forms[0].selectedItems)<1)
	    alert("您还没有选择数据！");
	  else if(confirm("您真的要删除选择的这些数据吗？")){
	    document.forms[0].thisAction.value="delete";
	    document.forms[0].submit();
	  }
	}			
	</script>
	</head>
	<body>
		<div id="mainContainer">
			<div id="container">
				<html:form action="/transaction/platComAccountList.do">
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
									<c:param name="title1" value="平台账号管理" />
									<c:param name="title2" value="平台公司账号列表" />
								</c:import>

								<div class="searchBar">
									<table cellpadding="0" cellspacing="0" border="0" class="searchPanel">
										<tr>
											<td>
												交易平台名称：
											</td>
											<td>
												<html:select property="platformName" styleClass="colorblue2 p_5" style="width:150px;">
													<option value="">
														请选择
													</option>
													<c:forEach items="${platformList}" var="platform">
														<html:option value="${platform.id}">
															<c:out value="${platform.showName}" />
														</html:option>
													</c:forEach>
												</html:select>
											</td>
											<td>
												公司名称：
											</td>
											<td>
												<html:select property="companyName" styleClass="colorblue2 p_5" style="width:150px;">
													<option value="">
														请选择
													</option>
													<c:forEach items="${companyList}" var="company">
														<c:if test="${company.type==1}">
															<html:option value="${company.id}">
																<c:out value="${company.showName}" />
															</html:option>
														</c:if>
													</c:forEach>
												</html:select>
											</td>
											<td>
												账户：
											</td>
											<td>
												<html:select property="accountName" styleClass="colorblue2 p_5" style="width:150px;">
													<option value="">
														请选择
													</option>
													<c:forEach items="${accountList}" var="account">
														<html:option value="${account.id}">
															<c:out value="${account.showName}" />
														</html:option>
													</c:forEach>
												</html:select>
											</td>

											<td>
												<input type="submit" name="button" id="button" value="提交" class="submit greenBtn" />
											</td>
										</tr>
									</table>
								</div>
								<table width="100%" cellpadding="0" cellspacing="0" border="0" class="dataList">
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
												交易平台
											</div>
										</th>
										<th>
											<div>
												平台类型
											</div>
										</th>

										<th width="230">
											<div>
												公司名称
											</div>
										</th>
										<th>
											<div>
												账户名称
											</div>
										</th>
										<th>
											<div>
												账户用途
											</div>
										</th>
										<th>
											<div>
												修改者
											</div>
										</th>
										<th>
											<div>
												修改时间
											</div>
										</th>
										<th>
											<div>
												状态
											</div>
										</th>
									</tr>
									<c:forEach var="pla" items="${platComAccountListForm.list}" varStatus="sta">
										<tr>
											<td>
												<html:multibox property="selectedItems" value="${pla.id}"></html:multibox>
											</td>
											<td>
												<c:out value="${sta.count+(platComAccountListForm.intPage-1)*platComAccountListForm.perPageNum}" />
											</td>
											<td>
												<a href="<%=path%>/transaction/platformList.do?thisAction=view&id=<c:out value="${pla.platform.id}" />"> <c:out value="${pla.platform.name}" /> </a>
											</td>
											<td>
												<c:out value="${pla.platform.typeInfo}" />
												(
												<c:out value="${pla.platform.drawTypeInfo}" />
												)
											</td>
											<td>
												<a href="<%=path%>/transaction/companyList.do?thisAction=view&id=<c:out value="${pla.company.id}" />"> <c:out value="${pla.company.name}" /> </a>
											</td>
											<td>
												<a href="<%=path%>/transaction/accountList.do?thisAction=view&id=<c:out value="${pla.account.id}" />"> <c:out value="${pla.account.name}" /> </a>
											</td>

											<td>
												<c:if test="${pla.type==1}">
													<font color="red"> <c:out value="${pla.typeCaption}" /> </font>
												</c:if>
												<c:if test="${pla.type!=1}">
													<c:out value="${pla.typeCaption}" />
												</c:if>
											</td>
											<td>

												<c:out value="${pla.userName}" />

											</td>
											<td>

												<c:out value="${pla.updateDate}" />

											</td>
											<td>
												<c:out value="${pla.statusInfo}" />
											</td>
										</tr>
									</c:forEach>

								</table>

								<table width="100%" style="margin-top: 5px;">
									<tr>
										<td>
											<input name="label" type="button" class="button1" value="新 增" onclick="add();" />
											<input name="label" type="button" class="button1" value="修 改" onclick="edit();" />
											<input name="label" type="button" class="button1" value="删 除" onclick="del();" style="display: none;" />
										</td>
										<td align="right">
											<div>
												共有记录&nbsp;
												<c:out value="${platComAccountListForm.totalRowCount}"></c:out>
												&nbsp;条&nbsp;&nbsp;&nbsp;&nbsp; [
												<a href="JavaScript:turnToPage(document.forms[0],0)">首页</a> |
												<a href="JavaScript:turnToPage(document.forms[0],1)">上一页</a> |
												<a href="JavaScript:turnToPage(document.forms[0],2)">下一页</a> |
												<a href="JavaScript:turnToPage(document.forms[0],3)"> 末页</a>] 页数:
												<c:out value="${platComAccountListForm.intPage}" />
												/
												<c:out value="${platComAccountListForm.pageCount}" />
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
