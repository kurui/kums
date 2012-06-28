<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>

<%
	String path = request.getContextPath();
%>
<html>
	<head>
		<title>main</title>
		<link href="<%=path%>/_css/reset.css" rel="stylesheet" type="text/css" />
		<link href="<%=path%>/_css/global.css" rel="stylesheet"
			type="text/css" />
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
	top: 20%;
	left: 10%;
}
</style>
		<script type="text/javascript">	
		function add()		{	
			var name=document.forms[0].name.value;
			if(name==""){
				alert("请输入名称!")
				return false;
			}
			var thisAction =document.forms[0].thisAction.value;			   
		     document.forms[0].action="<%=path%>/market/product.do?thisAction="+thisAction;
		    document.forms[0].submit();
		}		
	</script>
	</head>

	<div id="treeBox" class="productTypeDiv" style="display: none">
		<input type="button" class="colorblue2 p_5"
			onclick="displayObj('treeBox','none')" value="关闭"></input>
	</div>

	<body>
		<c:import url="../page/mainTitle.jsp" charEncoding="UTF-8">
			<c:param name="title1" value="产品管理" />
			<c:param name="title2" value="编辑产品" />
		</c:import>
		<html:form action="/market/product.do" method="post">
			<div id="mainContainer">
				<div id="container">
					<table width="100%" cellpadding="0" cellspacing="0" border="0">
						<tr>
							<td width="10" height="10" class="tblt"></td>
							<td height="10" class="tbtt"></td>
							<td width="10" height="10" class="tbrt"></td>
						</tr>
						<tr>
							<td width="10" class="tbll"></td>
							<td valign="top" class="body">
								<hr>
								<table width="100%" cellpadding="0" cellspacing="0" border="0"
									class="dataList">
									<tr>
										<td class="lef">
											品名
										</td>
										<td style="text-align: left">
											编号
											<html:text property="no" name="product" value="${product.no}"
												styleClass="colorblue2 p_5" style="width:200px;"></html:text>

											名称
											<html:text property="name" name="product"
												value="${product.name}" styleClass="colorblue2 p_5"
												style="width:200px;"></html:text>
										</td>
									</tr>
									<tr>
										<td class="lef">
											货品
										</td>
										<td style="text-align: left">
											<html:text property="productTypeInfo" name="product"
												styleClass="colorblue2 p_5" style="width: 150px;"
												onclick="displayObj('treeBox','');" />
											<html:hidden property="productType" name="product"></html:hidden>
											&nbsp;
											规格:
											<html:text property="standard" name="product"
												value="${product.standard}" styleClass="colorblue2 p_5"
												style="width:100px;"></html:text>
											&nbsp;剂型:
											<html:select property="classType" name="product"
												styleClass="colorblue2 p_5" style="width:80px;">
												<html:option value="">-请选择-</html:option>
												<html:option value="1">默认</html:option>
											</html:select>

										</td>
									</tr>
									<tr>
										<td class="lef">
											绩效权重
										</td>
										<td style="text-align: left">
											<html:select property="proportion" name="product"
												styleClass="colorblue2 p_5" style="width:80px;">
												<html:option value="1">--100%--</html:option>
												<html:option value="0.8">--80%--</html:option>
												<html:option value="0.5">--50%--</html:option>
											</html:select>
											&nbsp; 零售价
											<html:text property="price" name="product"
												value="${product.price}" styleClass="colorblue2 p_5"
												style="width:80px;"></html:text>
											&nbsp; 进货价
											<html:text property="purchasePrice" name="product"
												value="${product.purchasePrice}" styleClass="colorblue2 p_5"
												style="width:80px;"></html:text>
											&nbsp; 批发价
											<html:text property="tradePrice" name="product"
												value="${product.tradePrice}" styleClass="colorblue2 p_5"
												style="width:80px;"></html:text>
									</tr>
									<tr>
										<td class="lef">
											库存
										</td>
										<td style="text-align: left">
											<html:text property="stockCount" name="product"
												value="${product.stockCount}" styleClass="colorblue2 p_5"
												style="width:80px;"></html:text>
										</td>
									</tr>
									<tr>
										<td class="lef">
											说明
										</td>
										<td style="text-align: left">
											<html:textarea property="memo" name="product"
												value="${product.memo}" styleClass="colorblue2 p_5"
												style="font-size: 12px;width:500px;height:100px"></html:textarea>
										</td>
									</tr>
									<tr>
										<td class="lef">
											类型
										</td>
										<td style="text-align: left">
											<html:select property="type" name="product"
												styleClass="colorblue2 p_5" style="width:80px;">
												<html:option value="">-请选择-</html:option>
												<html:option value="1">自有</html:option>
												<html:option value="11">市场</html:option>
											</html:select>
											&nbsp; &nbsp; &nbsp;状态
											<html:select property="status" name="product"
												styleClass="colorblue2 p_5" style="width:80px;">
												<html:option value="1">有效</html:option>
												<html:option value="0">无效</html:option>
											</html:select>
										</td>
									</tr>
								</table>
								<table width="100%" style="margin-top: 5px;">
									<tr>
										<td align="center">
											<html:hidden property="id" value="${product.id}"></html:hidden>

											<html:hidden property="thisAction" name="product" />
											<input name="label" type="button" class="button1" value="返 回"
												onclick="window.history.back();">
											<input name="label" type="button" class="button1" value="提交"
												onclick="add();">
											<input name="label" type="reset" class="button1" value="重 置">

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
				</div>
			</div>
		</html:form>
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
				    tree.enableThreeStateCheckboxes(true);//设置点根目录全选子目录					
					
					//打开事件监听
					tree.setOnOpenHandler(tonopen);
					tree.setOnClickHandler(tonclick);
					tree.setOnDblClickHandler(tondblclick);
					tree.loadXML("../_xml/ProductTree.xml");		

	function displayObj(objId,displayValue){
		document.getElementById(objId).style.display=displayValue;
	}
	
	function confirmSelectProductType(id,value){
		displayObj("treeBox","none");
		document.forms[0].productTypeInfo.value=value;
		
		document.forms[0].productType.value=id;
	}
	
</script>

</html>