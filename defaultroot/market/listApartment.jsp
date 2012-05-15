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
				<html:form action="/market/apartmentList.do">
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
									<c:param name="title1" value="地产事业部" />
									<c:param name="title2" value="物业单元列表" />
								</c:import>

								<div id="searchBarObj" class="searchBar">
									<table cellpadding="0" cellspacing="0" border="0"
										class="searchPanel">
										<tr>
											<td>
												<html:text property="name" name="apartmentListForm"
													styleClass="colorblue2 p_5" style="width: 150px;" />
											</td>
											<td>
												<html:select property="estateDishId"
													value="${apartment.estateDish.id}" name="apartment"
													styleClass="colorblue2 p_5" style="width:100px;">
													<html:option value="0">
														楼盘
													</html:option>
													<c:forEach items="${estateDishList}" var="estateDish">
														<html:option value="${estateDish.id}">
															<c:out value="${estateDish.name}" />
														</html:option>
													</c:forEach>
												</html:select>
											</td>
											<td>
												<html:select property="transactionType"
													name="apartmentListForm" styleClass="colorblue2 p_5"
													style="width:80px;">
													<html:option value="0">交易类型</html:option>
													<html:option value="1">出售</html:option>
													<html:option value="11">求购</html:option>
													<html:option value="21">出租</html:option>
													<html:option value="31">求租</html:option>
												</html:select>
											</td>
											<td>
												<html:select property="businessType"
													name="apartmentListForm" styleClass="colorblue2 p_5"
													style="width:50px;">
													<html:option value="0">用途</html:option>
													<html:option value="1">住宅</html:option>
													<html:option value="11">商业</html:option>
												</html:select>
											</td>
											<td>
												开始:
												<html:text property="startDate" styleClass="colorblue2 p_5"
													style="width:120px;"
													onfocus="WdatePicker({startDate:'%y-%M-%D 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})"
													readonly="true" />
											</td>
											<td>
												结束
												<html:text property="endDate" styleClass="colorblue2 p_5"
													style="width:120px;"
													onfocus="WdatePicker({startDate:'%y-%M-%D 23:59:59',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})"
													readonly="true" />
											</td>
											<td>
												<html:select property="orderBy" name="apartmentListForm"
													styleClass="colorblue2 p_5" style="width:80px;">
													<html:option value="0">排序</html:option>
													<html:option value="snatchTime">按时间</html:option>
													<html:option value="estateDish">按楼盘</html:option>
													<html:option value="connection">按联系方式</html:option>
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
												楼盘
											</div>
										</th>
										<th>
											<div>
												物业单元
											</div>
										</th>
										<th>
											<div>
												交易
											</div>
										</th>
										<th>
											<div>
												用途
											</div>
										</th>
										<th>
											<div>
												户型| 面积
											</div>
										</th>

										<th>
											<div>
												报价时间
											</div>
										</th>
										<th>
											<div>
												报价
											</div>
										</th>
										<th>
											<div>
												均价
											</div>
										</th>
										<th>
											<div>
												业主|联系人|联系方式
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
										<th>
											<div>
												操作
											</div>
										</th>
									</tr>
									<c:forEach var="apartment" items="${apartmentListForm.list}"
										varStatus="status">
										<tr>
											<td>
												<html:multibox property="selectedItems"
													value="${apartment.id}"></html:multibox>
											</td>
											<td>
												<c:out
													value="${status.count+(apartmentListForm.intPage-1)*apartmentListForm.perPageNum}" />
											</td>
											<td>
												<a
													href="<%=path%>/market/estateDishList.do?thisAction=view&id=<c:out value="${apartment.estateDish.id}" />">
													<c:out value="${apartment.estateDish.name}" /> </a>
											</td>
											<td>
												<a
													href="<%=path%>/market/apartmentList.do?thisAction=view&id=<c:out value="${apartment.id}" />">
													<c:out value="${apartment.no}" /> </a> |
												<c:out value="${apartment.name}" />
											</td>
											<td>
												<c:out value="${apartment.transactionTypeInfo}" />
											</td>
											<td>
												<c:out value="${apartment.businessTypeInfo}" />
											</td>
											<td style="text-align: right">
												<c:out value="${apartment.houseType}" />
												|
												<c:out value="${apartment.area}" />
											</td>
											<td>
												<c:out value="${apartment.snatchDate}" />
											</td>
											<td style="text-align: right">
												<c:out value="${apartment.quotePrice}" />
											</td>
											<td style="text-align: right">
												<c:out value="${apartment.averageAreaPrice}" />
											</td>
											<td style="text-align: right">
												<c:out value="${apartment.owner}" />
												|
												<c:out value="${apartment.linkman}" />
												|
												<c:out value="${apartment.connection}" />
											</td>
											<td>
												<c:out value="${apartment.typeInfo}" />
											</td>
											<td>
												<c:out value="${apartment.statusInfo}" />
											</td>
											<td>
												<a
													href="<%=path%>/market/apartmentList.do?thisAction=save&estateDishId=<c:out value="${apartment.estateDish.id}" />">新增</a>
											</td>
										</tr>
									</c:forEach>

								</table>

								<table width="100%" style="margin-top: 5px;">
									<tr>
										<td>
											<input name="label" type="button" class="button1" value="新 增"
												onclick="add();">
											<input name="label" type="button" class="button1" value="修 改"
												onclick="edit();">
											<input name="label" type="button" class="button1" value="删 除"
												onclick="del();">
										</td>
										<td align="right">
											<div>
												共有记录&nbsp;
												<c:out value="${apartmentListForm.totalRowCount}"></c:out>
												&nbsp;条&nbsp;&nbsp;&nbsp;&nbsp; [
												<a href="JavaScript:turnToPage(document.forms[0],0)">首页</a>
												|
												<a href="JavaScript:turnToPage(document.forms[0],1)">上一页</a>
												|
												<a href="JavaScript:turnToPage(document.forms[0],2)">下一页</a>
												|
												<a href="JavaScript:turnToPage(document.forms[0],3)"> 末页</a>]
												页数:
												<c:out value="${apartmentListForm.intPage}" />
												/
												<c:out value="${apartmentListForm.pageCount}" />
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