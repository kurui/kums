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
		<link 	href="../_js/dhtmlxtree/dhtmlxtree.css"  rel="stylesheet" type="text/css" />

	</head>
	<body>
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
								<c:import url="../page/mainTitle.jsp" charEncoding="UTF-8">
									<c:param name="title1" value="客户管理" />
									<c:param name="title2" value="客户关系图谱" />
								</c:import>
								
								<script src="../_js/dhtmlxtree/dhtmlxcommon.js"></script>
								<script src="../_js/dhtmlxtree/dhtmlxtree.js"></script>

								<table>
									<tr>
										<td>
											<div id="treeboxbox_tree"
												style="width: 250px; height: 518px; background-color: #f5f5f5; border: 1px solid Silver;; overflow: auto;" />
										</td>
										<td rowspan="2" style="padding-left: 25" valign="top">
										</td>
									</tr>
									<tr>
										<td>
											&nbsp;
										</td>
									</tr>
								</table>
								<script>
									tree = new dhtmlXTreeObject("treeboxbox_tree", "100%", "100%", 0);
									tree.setSkin('dhx_skyblue');
									tree.setImagePath("../_js/dhtmlxtree/imgs/csh_bluebooks/");
									tree.enableCheckBoxes(1);//显示复选框
			    tree.enableThreeStateCheckboxes(true);//设置点根目录全选子目录	
				//打开事件监听
				tree.enableDragAndDrop(1);
				tree.setOnOpenHandler(tonopen);
									
				tree.setOnClickHandler(tonclick);
				tree.setOnCheckHandler(toncheck);
				tree.setOnDblClickHandler(tondblclick);
				tree.setDragHandler(tondrag);	
									//tree.loadXML("../_xml/tree3.xml");
									//tree.loadXML("../_xml/PriceReferenceTree.xml");
									tree.loadXML("../_xml/ProductTree.xml");
									
									
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
				    alert(id+"--Item " + tree.getItemText(id) + " was " + ((state) ? "checked": "unchecked"));
				};
								</script>				
								
							</td>
						</tr>
					</table>
			</div>
		</div>
	</body>
</html>