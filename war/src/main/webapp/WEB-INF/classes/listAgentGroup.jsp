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
		<link href="../_js/dhtmlxtree/dhtmlxtree.css" rel="stylesheet"
			type="text/css" />
		<script src="../_js/dhtmlxtree/dhtmlxcommon.js"></script>
		<script src="../_js/dhtmlxtree/dhtmlxtree.js"></script>
		<script type="text/javascript" language="javascript"
			src="../_js/jquery-1.3.2.min.js"></script>
		<script src="../_js/prototype/common.js" type="text/javascript"></script>


		
		<script type="text/javascript">	
	</script>
	</head>
	<body>
		<div id="mainContainer">
			<div id="container">
				<html:form action="/agent/agentRelationList.do">
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
									<c:param name="title1" value="客户管理" />
									<c:param name="title2" value="客户关系图谱" />
								</c:import>
								<div class="searchBar">
									<table cellpadding="0" cellspacing="0" border="0"
										class="searchPanel">
										<tr>
											<td>
												&nbsp;
											</td>
										</tr>
									</table>
								</div>

								<table width="100%" cellpadding="0" cellspacing="0" border="0">
									<tr align="center">
										<td>
											<div id="treeContainerObj"
												style="width: 250px; height: 218px; background-color: #f5f5f5; border: 1px solid Silver;; overflow: auto;" />
										</td>
										<td rowspan="2" style="padding-left: 25" valign="top">
										</td>
										<td>
											<span id="logarea"></span>
										</td>
									</tr>
								</table>
								<script type="text/javascript">
								function doLog(str) {
								    var log = document.getElementById("logarea");
								    log.innerHTML = log.innerHTML + str + "<br/>";
								    log.scrollTop = log.scrollHeight;
								}
								function tonclick(id) {
								    doLog("Item " + tree.getItemText(id) + " was selected");
								};
								function tondblclick(id) {
								    doLog("Item " + tree.getItemText(id) + " was doubleclicked");
								};
								function tondrag(id, id2) {
								    return confirm("Do you want to move node " + tree.getItemText(id) + " to item " + tree.getItemText(id2) + "?");
								};
								function tonopen(id, mode) {
								    //return confirm("Do you want to " + (mode > 0 ? "close": "open") + " node " + tree.getItemText(id) + "?");
									return true;
								};
								function toncheck(id, state) {
								    doLog("Item " + tree.getItemText(id) + " was " + ((state) ? "checked": "unchecked"));
								};
								
								</script>

								<table width="100%" cellpadding="0" cellspacing="0" border="0">
									<tr align="center">
										<td>
											<input name="label" type="button" class="button1" value="返 回"
												onclick="window.history.back();">
											<input name="label" type="button" class="button1" value="删 除"
												onclick="del();" style="display: none">
										</td>
									</tr>
									<tr>
										<td>
											&nbsp;
										</td>
									</tr>
								</table>

								<table width="100%" cellpadding="0" cellspacing="0" border="0"
									style="margin-top: 5px;">
									<tr align="center">
										<td>
											<div style="background: #B1D7FE; border: #2C99EC 1px solid;">
												<a
													href="<%=path%>/agent/agentList.do?thisAction=view&id=<c:out value="${rootAgent.id}" />">
													<c:out value="${rootAgent.name}" /> </a>
												<br />
												级别：
												<c:if test="${rootAgent.directLevel!=null}">
													<c:out value="${rootAgent.directLevel.name}" />
												</c:if>
												<br />
												销售额：
												<c:out value="${rootAgent.physicalAmount}" />
												<br />
												<c:out value="${rootAgent.memo}" />
											</div>
										</td>
									</tr>
								</table>
								<table width="100%" cellpadding="0" cellspacing="0" border="0"
									class="dataList">
									<c:forEach var="subAgentList" items="${lists}"
										varStatus="status">
										<tr>

											<c:forEach var="subAgent" items="${subAgentList}"
												varStatus="status2">
												<td>
													<div
														style="background: #B1D7FE; border: #2C99EC 1px solid;">
														<a
															href="<%=path%>/agent/agentList.do?thisAction=view&id=<c:out value="${subAgent.id}" />">
															<c:out value="${subAgent.name}" /> </a>
														<br />
														评级：
														<c:out value="${subAgent.grade}" />
														<br />
														销售额：
														<c:out value="${subAgent.physicalAmount}" />
														<br />
														<c:out value="${subAgent.memo}" />
													</div>
												</td>
											</c:forEach>
										</tr>
									</c:forEach>
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
		<script>		
				tree = new dhtmlXTreeObject("treeContainerObj", "100%", "100%", 0);
				tree.setSkin('dhx_skyblue');									
				tree.setImagePath("../_js/dhtmlxtree/imgs/csh_bluebooks/");
		    	tree.enableCheckBoxes(1);//显示复选框
			    tree.enableThreeStateCheckboxes(true);//设置点根目录全选子目录	
				//打开事件监听
				tree.enableDragAndDrop(1);
				tree.setOnOpenHandler(tonopen);
									
				tree.attachEvent("onOpenEnd", function(nodeId, event) {
				    doLog("An id of open item is " + nodeId);
				});
				tree.setOnClickHandler(tonclick);
				tree.setOnCheckHandler(toncheck);
				tree.setOnDblClickHandler(tondblclick);
				tree.setDragHandler(tondrag);
									
										
				var relationTreeFileName="<c:out value='${relationTreeFileName}' ></c:out>";	
									//relationTreeFileName="20120612180420.xml";										
				tree.loadXML("../_xml/"+relationTreeFileName);
		</script>
	</body>
</html>