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
	
	function listFinanceForAssetsItem(){
			openWindow(800,600,'../finance/financeOrderList.do?thisAction=listFinanceForAssetsItem&tranTypes=120121,120131,120135');
								  	
		}
		
	function addFinanceOrderId(financeOrderId){	
			alert("financeOrderId:"+financeOrderId);		
			//document.forms[0].financeOrderId.value=financeOrderId;
			
		}	
		
	function batchReset(){
		var selectedItems=document.forms[0].selectedItems;
		var selectedItemIds=getSelectedItemIds(selectedItems);
		if(selectedItemIds==null&selectedItemIds==""){
			alert("请选择明细");
		}else{
			openWindow(800,600,'../finance/assetsItemList.do?thisAction=batchReset&assetsItemIdGroup='+selectedItemIds);
		}
	}
	
		function editAssetsItem(id){
			if(id!=null&&id>0){
				assetsItemBiz.getAssetsItemById(id,function(assetsItemObj){
					if(assetsItemObj!=null){
						document.forms["editAssetsItemForm"].id.value=assetsItemObj.id;
						document.forms["editAssetsItemForm"].name.value=assetsItemObj.name;
						document.forms["editAssetsItemForm"].memo.value=assetsItemObj.memo;
						document.forms["editAssetsItemForm"].itemCount.value=assetsItemObj.itemCount;
						document.forms["editAssetsItemForm"].valuation.value=assetsItemObj.valuation;
						document.forms["editAssetsItemForm"].areaCode.value=assetsItemObj.areaCode;					
						
						
						js.select.markSelected(document.forms["editAssetsItemForm"].itemType,assetsItemObj.itemType);	
						js.select.markSelected(document.forms["editAssetsItemForm"].type,assetsItemObj.type);
						js.select.markSelected(document.forms["editAssetsItemForm"].status,assetsItemObj.status);	
						
						document.forms["editAssetsItemForm"].thisAction.value="update";
						document.getElementById("editAssetsItemTable").style.display="";
					}
				});
			}				
		}
		
		function updateAssetsItem(){	
			document.forms["editAssetsItemForm"].lastAction.value="list";
			document.forms["editAssetsItemForm"].intPage.value=<c:out value="${assetsItemListForm.intPage}" />;
			document.forms["editAssetsItemForm"].pageCount.value=<c:out value="${assetsItemListForm.pageCount}" />;
			trim(document.forms["editAssetsItemForm"]);
			document.forms["editAssetsItemForm"].submit();
		}
		
		function cancelEditAssetsItem(){
			document.getElementById('editAssetsItemTable').style.display='none'
		}
	</script>
</head>
<body>
	<div id="mainContainer">
		<div id="container">
		
			<html:form action="/finance/assetsItemList.do" styleId="listAssetsItemForm">
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
								<c:param name="title1" value="财务管理" />
								<c:param name="title2" value="资产项目列表" />
							</c:import>
							<div class="searchBar">
								<table cellpadding="0" cellspacing="0" border="0"
									class="searchPanel">
									<tr>
										<td>搜索(编号/名称)：</td>
										<td><html:text property="contactWay"
												styleClass="colorblue2 p_5" style="width:150px;"></html:text>
										</td>
										<td><html:select property="itemType"
												styleClass="colorblue2 p_5" style="width:80px;">
												<html:option value="">-资产项目-</html:option>
												<html:option value="NONE">-未定义-</html:option>
												<c:forEach items="${itemTypeList}" var="itemType">
													<html:option value="${itemType.no}">
														<c:out value="${itemType.name}" />
													</html:option>
												</c:forEach>
											</html:select></td>
										<td>状态: <html:select property="status"
												styleClass="colorblue2 p_5" style="width:80px;">
												<html:option value="1">有效</html:option>
												<html:option value="0">无效</html:option>
											</html:select>
										</td>

										<td><input type="submit" name="button" id="button"
											value="提交" class="submit greenBtn" /></td>
										<td><a
											href="../finance/assetsItemList.do?thisAction=listSTA">统计</a></td>
									</tr>
								</table>
							</div>
							<table width="100%" cellpadding="0" cellspacing="0" border="0"
								class="dataList">
								<tr>
									<th>
										<div
											style="height: 100%; width: 100%; vertical-align: center; padding-top: 7px;">
											<input type="checkbox"
												onclick="checkAll(this, 'selectedItems')" name="sele" />
										</div>
									</th>
									<th width="35">
										<div>&nbsp;序号</div>
									</th>
									<th>
										<div>项目编号</div>
									</th>
									<th>
										<div>项目名称</div>
									</th>
									<th>
										<div>项目类型</div>
									</th>
									<th>
										<div>数量</div>
									</th>
									<th>
										<div>估价</div>
									</th>
									<th>
										<div>地点</div>
									</th>
									<th>
										<div>备注</div>
									</th>
									<th>
										<div>最后折旧</div>
									</th>
									<th>
										<div>状态</div>
									</th>
									<th>
										<div>操作</div>
									</th>
								</tr>
								<c:forEach var="assetsItem" items="${assetsItemListForm.list}"
									varStatus="status">
									<tr>
										<td><html:multibox property="selectedItems"
												value="${assetsItem.id}" onclick="checkItem(this, 'sele')"></html:multibox></td>
										<td><c:out
												value="${status.count+(assetsItemListForm.intPage-1)*assetsItemListForm.perPageNum}" />
										</td>
										<td><a
											href="<%=path%>/finance/assetsItemList.do?thisAction=view&id=<c:out value="${assetsItem.id}" />">
												<c:out value="${assetsItem.itemNo}" />
										</a></td>

										<td><c:out value="${assetsItem.name}" /></td>
										<td><c:out value="${assetsItem.itemTypeName}" /></td>
										<td><c:out value="${assetsItem.itemCount}" /></td>
										<td><c:out value="${assetsItem.valuation}" /></td>
										<td><c:out value="${assetsItem.areaCode}" /></td>

										<td><c:out value="${assetsItem.memo}" /></td>
										<td><c:out value="${assetsItem.lastDeprecDate}" /></td>
										<td><c:out value="${assetsItem.statusInfo}" /></td>
										<td><a href="#"
											onclick="editAssetsItem('<c:out value="${assetsItem.id}"/>')">编辑</a>
										</td>
									</tr>
								</c:forEach>
								<tr>
									<td colspan="6">
										<div align="center">
											<font>合计</font>
										</div>
									</td>
									<td style="text-align: right;"><c:out
											value="${assetsItemListForm.totalValue1}" /> &nbsp;</td>
									<td colspan="4">
										<div align="center"></div>
									</td>
								</tr>
							</table>
							<table width="100%" style="margin-top: 5px;">
								<tr>
									<td><input name="label" type="button" class="button1"
										value="新 增" onclick="add();"> <input name="label"
										type="button" class="button1" value="编 辑" onclick="edit();">
										<input name="label" type="button" class="button1" value="删 除"
										onclick="del();"> <input name="label" type="button"
										class="button3" value="从日记账中选择"
										onclick="listFinanceForAssetsItem();"> <input
										name="label" type="button" class="button1" value="批量设置"
										onclick="batchReset();"></td>
									<td align="right">
										<div>
											共有记录&nbsp;
											<c:out value="${assetsItemListForm.totalRowCount}"></c:out>
											&nbsp;条&nbsp;&nbsp;&nbsp;&nbsp; [ <a
												href="JavaScript:turnToPage(document.forms[0],0)">首页</a> | <a
												href="JavaScript:turnToPage(document.forms[0],1)">上一页</a> |
											<a href="JavaScript:turnToPage(document.forms[0],2)">下一页</a>
											| <a href="JavaScript:turnToPage(document.forms[0],3)">
												末页</a>] 页数:
											<c:out value="${assetsItemListForm.intPage}" />
											/
											<c:out value="${assetsItemListForm.pageCount}" />
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
			<html:form action="/finance/assetsItem.do"
				styleId="editAssetsItemForm">
				<table width="100%" cellpadding="0" cellspacing="0" border="0"
					class="dataList" id="editAssetsItemTable" style="display: none">
					<th>
						<div>项目</div>
					</th>
					<th>
						<div>资产类型</div>
					</th>
					<th>
						<div>数量</div>
					</th>
					<th>
						<div>估价</div>
					</th>
					<th>
						<div>地点</div>
					</th>
					<th>
						<div>备注</div>
					</th>

					<th>
						<div>状态</div>
					</th>
					<th>
						<div>操作</div>
					</th>
					<tr>
						<td style="text-align: left"><html:hidden property="id"
								value="" />
							<html:text property="name" name="assetsItem"
								styleClass="colorblue2 p_5" style="width:200px;"></html:text></td>
						<td><html:select property="itemType"
								styleClass="colorblue2 p_5" style="width:80px;">
								<html:option value="">-资产项目-</html:option>
								<html:option value="NONE">-未定义-</html:option>
								<c:forEach items="${itemTypeList}" var="itemType">
									<html:option value="${itemType.no}">
										<c:out value="${itemType.name}" />
									</html:option>
								</c:forEach>
							</html:select><td>数量：<html:text property="itemCount"
								styleClass="colorblue2 p_5" style="width:30px;"></html:text>
						</td><td><html:text property="valuation"
								styleClass="colorblue2 p_5" style="width:50px;"></html:text></td>
						<td><html:text property="areaCode"
								styleClass="colorblue2 p_5" style="width:50px;"></html:text></td>
						<td><html:text property="memo" styleClass="colorblue2 p_5"
								style="width:200px;" ondblclick="this.value='';"></html:text></td>


						<td><html:select property="type" styleClass="colorblue2 p_5"
								style="width:50px;">
								<html:option value="1">默认</html:option>
							</html:select><html:select property="status" styleClass="colorblue2 p_5"
								style="width:50px;">
								<html:option value="1">有效</html:option>
								<html:option value="0">无效</html:option>
							</html:select></td>
						<td><html:hidden property="thisAction" value="" /> <html:hidden
								property="lastAction" value="" /> <html:hidden
								property="intPage" value="" /> <html:hidden
								property="pageCount" value="" /> <input name="label"
							type="button" class="button1" value="保存"
							onclick="updateAssetsItem();"> <input name="label"
							type="button" class="button1" value="取消"
							onclick="cancelEditAssetsItem();"></td>
					</tr>
				</table>
			</html:form>
		</div>
	</div>
</body>
</html>