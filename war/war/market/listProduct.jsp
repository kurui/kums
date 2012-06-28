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
		<script type="text/javascript" src="../_js/prototype/common.js"></script>
		<link href="../_js/dhtmlxtree/dhtmlxtree.css" rel="stylesheet"
			type="text/css" />
		<script src="../_js/dhtmlxtree/dhtmlxcommon.js"></script>
		<script src="../_js/dhtmlxtree/dhtmlxtree.js"></script>
		<style>
.productTypeDiv {
	width: 200px;
	height: 200 px;
	background-color: #f5f5f5;
	border: 1 px solid Silver;;
	overflow: auto;
	position: absolute;
	z-index: 3;
	top: 10%;
	left: 30%;
}
</style>
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
	<div id="treeBox" class="productTypeDiv" style="display: none">
		<input type="button" class="colorblue2 p_5"
			onclick="displayObj('treeBox','none')" value="关闭"></input>
		<input type="button" class="colorblue2 p_5" onclick="openAllItems();"
			value="展开"></input>
		<input type="button" class="colorblue2 p_5" onclick="closeAllItems();"
			value="折叠"></input>
		<input type="button" class="colorblue2 p_5"
			onclick="confirmSelectProductType();" value="确认"></input>
	</div>
	<body>
		<div id="mainContainer">
			<div id="container">
				<html:form action="/market/productList.do">
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
									<c:param name="title2" value="产品列表" />
								</c:import>
								<div class="searchBar">
									<table cellpadding="0" cellspacing="0" border="0"
										class="searchPanel">
										<tr>
											<td>
												名称：
											</td>
											<td>
												<html:text property="name" styleClass="colorblue2 p_5"
													style="width:150px;" />
											</td>
											<td>
												货品类型：
											</td>
											<td>
												<html:text property="productTypesInfo"
													value="${productListForm.productTypesInfo}"
													onclick="displayObj('treeBox','');"
													styleClass="colorblue2 p_5" style="width:100px;" />
												<html:hidden property="productTypes"
													value="${productListForm.productTypesInfo}" />
											</td>
											<td>
												渠道：
											</td>
											<td>
												<html:select property="type" value="${productListForm.type}"
													styleClass="colorblue2 p_5" style="width:80px;">
													<html:option value="">--请选择--</html:option>
													<html:option value="1">--自有--</html:option>
													<html:option value="11">--市场--</html:option>
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
												名称
											</div>
										</th>
										<th>
											<div>
												规格
											</div>
										</th>
										<th>
											<div>
												零售价
											</div>
										</th>
										<th>
											<div>
												进货价
											</div>
										</th>
										<th>
											<div>
												批发价
											</div>
										</th>
										<th>
											<div>
												绩效权重
											</div>
										</th>
										<th>
											<div>
												剂型
											</div>
										</th>
										<th>
											<div>
												库存
											</div>
										</th>
									</tr>
									<c:forEach var="product" items="${productListForm.list}"
										varStatus="status">
										<tr>
											<td>
												<html:multibox property="selectedItems"
													value="${product.id}"></html:multibox>
											</td>
											<td>
												<c:out
													value="${status.count+(productListForm.intPage-1)*productListForm.perPageNum}" />
											</td>
											<td style="text-align: left">
												<c:out value="${product.no}" />
												|
												<a
													href="<%=path%>/market/productList.do?thisAction=view&id=<c:out value="${product.id}" />">
													<c:out value="${product.name}" /> </a>
											</td>
											<td style="text-align: right">
												<c:out value="${product.standard}" />
											</td>
											<td width="50px">
												<c:out value="${product.price}" />
											</td>
											<td>
												<c:out value="${product.purchasePrice}" />
											</td>
											<td>
												<c:out value="${product.tradePrice}" />
											</td>

											<td>
												<c:out value="${product.proportionInfo}" />
											</td>
											<td>
												<c:out value="${product.classTypeInfo}" />
											</td>
											<td>
												<c:out value="${product.stockCount}" />
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
												<c:out value="${productListForm.totalRowCount}"></c:out>
												&nbsp;条&nbsp;&nbsp;&nbsp;&nbsp; [
												<a href="JavaScript:turnToPage(document.forms[0],0)">首页</a>
												|
												<a href="JavaScript:turnToPage(document.forms[0],1)">上一页</a>
												|
												<a href="JavaScript:turnToPage(document.forms[0],2)">下一页</a>
												|
												<a href="JavaScript:turnToPage(document.forms[0],3)"> 末页</a>]
												页数:
												<c:out value="${productListForm.intPage}" />
												/
												<c:out value="${productListForm.pageCount}" />
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
	<script type="text/javascript">
				function tonclick(id) {
					//alert(id+"--Item " + tree.getItemText(id) + " was selected");
				};
				function tondblclick(id) {
					//alert(id+"---Item " + tree.getItemText(id) + " was doubleclicked");
					var value=tree.getItemText(id);
					confirmSelectProductType(id,value);
				};
				function tonopen(id, mode) {
					return true;
				};		
	</script>
	<script type="text/javascript">				
					tree = new dhtmlXTreeObject("treeBox", "100%", "100%", 0);
					tree.setSkin('dhx_skyblue');									
					tree.setImagePath("../_js/dhtmlxtree/imgs/csh_bluebooks/");
					tree.enableCheckBoxes(1);//显示复选框
				    tree.enableThreeStateCheckboxes(true);//设置点根目录全选子目录					
					
					//打开事件监听
					tree.setOnOpenHandler(tonopen);
					tree.setOnClickHandler(tonclick);
					tree.setOnDblClickHandler(tondblclick);
					tree.loadXML("../_xml/ProductTree.xml");		

	function displayObj(objId,displayValue){
		document.getElementById(objId).style.display=displayValue;
	}
	
	
		function displayObj(objId,displayValue){
		document.getElementById(objId).style.display=displayValue;
		if(displayValue==""){
			setCheckedTree();
		}
	}
	
	function setCheckedTree(){
		var productTypes=document.forms[0].productTypes.value;			
			if(productTypes!=null){
				var checkIds=productTypes.split(",");			
				var len=checkIds.length;
				for(var i=0;i<len;i++){
					var checkId=checkIds[i];
					//alert("checkId:"+checkId);
					tree.setCheck(checkId,true);						
				}
			}			
	}
	
	function confirmSelectProductType(){
		displayObj("treeBox","none");
		
		var allCheckedItem=tree.getAllChecked();
		//alert(allCheckedItem);
		if(allCheckedItem!=null){
			document.forms[0].productTypes.value=allCheckedItem;
			document.forms[0].submit();
		}		
	}	
	
</script>
</html>
