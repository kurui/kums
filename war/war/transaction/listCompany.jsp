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
		<link href="../_js/dhtmlxtree/dhtmlxtree.css" rel="stylesheet" type="text/css" />
		<script src="../_js/dhtmlxtree/dhtmlxcommon.js"></script>
		<script src="../_js/dhtmlxtree/dhtmlxtree.js"></script>
		<script type="text/javascript" language="javascript" src="../_js/jquery-1.3.2.min.js"></script>
		<script src="../_js/prototype/common.js" type="text/javascript"></script>
		<script type="text/javascript">
		function add(){
		    document.forms[0].thisAction.value="save";
		    document.forms[0].type.value=<c:out value='${companyListForm.type}'/>;
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
		<c:choose>
			<c:when test="${companyListForm.type==1}">
				<c:set var="title3" value="集团下属" />
			</c:when>
			<c:when test="${companyListForm.type==2}">
				<c:set var="title3" value="客户公司" />
			</c:when>
			<c:when test="${companyListForm.type==3}">
				<c:set var="title3" value="合作企业" />
			</c:when>
			<c:otherwise>
			</c:otherwise>
		</c:choose>
		<div id="mainContainer">
			<div id="container">
				<html:form action="/transaction/companyList.do">
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
									<c:param name="title1" value="机构管理" />
									<c:param name="title2" value="公司列表" />
									<c:param name="title3" value="${title3}" />
								</c:import>

								<style>
.provideChainDiv {
	width: 250px;
	height: 300 px;
	background-color: #f5f5f5;
	border: 1 px solid Silver;;
	overflow: auto;
	position: absolute;
	z-index: 3;
	top: 10%;
}
</style>
								<script>
	function displayObj(objId,displayValue){
		document.getElementById(objId).style.display=displayValue;
		if(displayValue==""){
			var provideChain=document.forms[0].provideChain;			
			if(provideChain!=null){
				var checkIds=provideChain.value.split(",");
				var len=checkIds.length;
				for(var i=0;i<len;i++){
					var checkId=checkIds[i];
					tree.setCheck(checkId,true);
				}
			}
		}
		openAllItems();
	}
	
	function confirmSelectProvideChain(){
		displayObj("treeContainerObj","none");
		
		var allCheckedItem=tree.getAllChecked();
		//alert(allCheckedItem);
		if(allCheckedItem!=null){
			document.forms[0].provideChain.value=allCheckedItem;
			document.forms[0].submit();
		}		
	}
</script>

								<div id="treeContainerObj" class="provideChainDiv" style="display: none">
									<input type="button" class="colorblue2 p_5" onclick="openAllItems();" value="展开"></input>
									<input type="button" class="colorblue2 p_5" onclick="closeAllItems();" value="折叠"></input>
									<input type="button" class="colorblue2 p_5" onclick="confirmSelectProvideChain();" value="确认"></input>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="button" class="colorblue2 p_5" onclick="displayObj('treeContainerObj','none')" value="关闭"></input>
								</div>

								<div class="searchBar">
									<table cellpadding="0" cellspacing="0" border="0" class="searchPanel">
										<tr>
											<td>
												供应链角色
												<input type="button" value="-请选择-" onclick="displayObj('treeContainerObj','');" class="colorblue2 p_5" style="width: 150px;" />
												<html:hidden property="provideChain" value="${companyListForm.provideChain}"></html:hidden>
											</td>
											<td>
												名称：
											</td>
											<td>
												<html:text property="name" styleClass="colorblue2 p_5" style="width:150px;" />
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
												名称
											</div>
										</th>
										<th>
											<div>
												供应链
											</div>
										</th>
										<th>
											<div>
												法人代表
											</div>
										</th>
										<th>
											<div>
												注册资本
											</div>
										</th>
										<th>
											<div>
												净资产
											</div>
										</th>
										<th>
											<div>
												成立时间
											</div>
										</th>
										<th>
											<div>
												备注
											</div>
										</th>
											<th>
											<div>
												交易次数/关联客户
											</div>
										</th>
										<th>
											<div>
												状态
											</div>
										</th>
									</tr>
									<c:forEach var="company" items="${companyListForm.list}" varStatus="status">
										<tr>
											<td>
												<html:multibox property="selectedItems" value="${company.id}"></html:multibox>
											</td>
											<td>
												<c:out value="${status.count+(companyListForm.intPage-1)*companyListForm.perPageNum}" />
											</td>
											<td>
												<div align="left">
													<a href="<%=path%>/transaction/companyList.do?thisAction=view&id=<c:out value="${company.id}" />"> <c:out value="${company.shortName}" /> </a>
												</div>
											</td>
											<td>
												<c:out value="${company.provideChainInfo}" />
											</td>
											<td>
												<c:out value="${company.deputy}" />
											</td>
											<td style="text-align: right">
												<c:out value="${company.registerCapital}" />
											</td>
											<td>
												<c:out value="${company.netAssetValue}" />
											</td>
											<td>
												<c:out value="${company.registerDate}" />
											</td>
											<td>
												<c:out value="${company.memo}" />
											</td>
												<td>
												<c:out value="${company.financeCount}" />--	<c:out value="${company.agentCount}" />
											</td>
											<td>
												<c:out value="${company.statusInfo}" />
											</td>
										</tr>
									</c:forEach>
								</table>
								<table width="100%" style="margin-top: 5px;">
									<tr>
										<td>
											<html:hidden property="type" />
											<input name="label" type="button" class="button1" value="新 增" onclick="add();">
											<input name="label" type="button" class="button1" value="修 改" onclick="edit();">
											<input name="label" type="button" class="button1" value="删 除" onclick="del();" style="display: none;">
										</td>
										<td align="right">
											<div>
												共有记录&nbsp;
												<c:out value="${companyListForm.totalRowCount}"></c:out>
												&nbsp;条&nbsp;&nbsp;&nbsp;&nbsp; [
												<a href="JavaScript:turnToPage(document.forms[0],0)">首页</a> |
												<a href="JavaScript:turnToPage(document.forms[0],1)">上一页</a> |
												<a href="JavaScript:turnToPage(document.forms[0],2)">下一页</a> |
												<a href="JavaScript:turnToPage(document.forms[0],3)"> 末页</a>] 页数:
												<c:out value="${companyListForm.intPage}" />
												/
												<c:out value="${companyListForm.pageCount}" />
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
<script type="text/javascript">
				function tonclick(id) {
					//alert("Item " + tree.getItemText(id) + " was selected");
				};
				function tondblclick(id) {
					//alert("Item " + tree.getItemText(id) + " was doubleclicked");
				};
				function tondrag(id, id2) {
					return confirm("Do you want to move node " + tree.getItemText(id) + " to item " + tree.getItemText(id2) + "?");
				};
				function tonopen(id, mode) {
					//return confirm("Do you want to " + (mode > 0 ? "close": "open") + " node " + tree.getItemText(id) + "?");
					return true;
				};
				function toncheck(id, state) {
				    //alert("Item " + tree.getItemText(id) + " was " + ((state) ? "checked": "unchecked"));
				};			
	</script>
<script type="text/javascript">				
					tree = new dhtmlXTreeObject("treeContainerObj", "100%", "100%", 0);
					tree.setSkin('dhx_skyblue');									
					tree.setImagePath("../_js/dhtmlxtree/imgs/csh_bluebooks/");
			    	tree.enableCheckBoxes(1);//显示复选框
				    tree.enableThreeStateCheckboxes(true);//设置点根目录全选子目录					
					tree.enableDragAndDrop(1);//设置借点可拖拽
					//打开事件监听
					tree.setOnOpenHandler(tonopen);
					tree.setOnClickHandler(tonclick);
					tree.setOnCheckHandler(toncheck);
					tree.setOnDblClickHandler(tondblclick);
					tree.setDragHandler(tondrag);
					tree.loadXML("../_xml/ProvideChainTree.xml");					
					
		function closeAllItems(){
			tree.closeAllItems(0);;
		}
		
		function openAllItems(){
			tree.openAllItems(0);
		}
		</script>