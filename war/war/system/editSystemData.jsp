<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic"%>
<%@ page import="com.kurui.kums.base.KumsOnlineCounter"%>
<%
	String path = request.getContextPath();
%>

<html>
	<head>
		<title>main</title>
		<link href="../_css/reset.css" rel="stylesheet" type="text/css" />
		<link href="../_css/global.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" language="javascript"
			src="../_js/jquery-1.3.2.min.js"></script>
		<script src="../_js/prototype/common.js" type="text/javascript"></script>
		<script>	
			function updatePriceReferenceTree(){
				var thisAction="updatePriceReferenceStore";
				var formObj=document.getElementById("priceReferenceListForm");
				if(formObj!=null){
					formObj.thisAction.value=thisAction;
			   		formObj.submit();
				}
			}	
			
			function updateProductTree(){
				var thisAction="updateDataTypeStore";
				var formObj=document.getElementById("dataTypeListForm");
				if(formObj!=null){
					formObj.thisAction.value=thisAction;
			   		formObj.submit();
				}
			}	
			
			function refactorDataType(){
				var thisAction="refactorDataTypeTree";
				var formObj=document.getElementById("refactorDataTypeForm");
				if(formObj!=null){
					formObj.thisAction.value=thisAction;
			   		formObj.submit();
				}
			}	
									
		</script>
	</head>
	<body>
		<div id="mainContainer" style="height: 1000px;">
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
								<c:param name="title1" value="系统管理"></c:param>
								<c:param name="title1" value="更新系统数据"></c:param>
							</c:import>
							<hr>

							<table width="100%" cellpadding="0" cellspacing="0" border="0"
								class="dataList">
								<tr>
									<td class="lef">
										PriceReference
									</td>
									<td style="text-align: left">
										<html:form styleId="priceReferenceListForm" action="/market/priceReferenceList.do">
											<html:hidden property="thisAction" ></html:hidden>
											<input name="label" type="button" class="button2"
												value="更新参照物树" onclick="updatePriceReferenceTree();" />
										</html:form>
									</td>
								</tr>
								<tr>
									<td class="lef">
										DataType
									</td>
									<td style="text-align: left">
										<html:form styleId="dataTypeListForm" action="/transaction/dataTypeList.do">
											<html:hidden property="thisAction" ></html:hidden>
											<input name="label" type="button" class="button2"
												value="更新产品树" onclick="updateProductTree();" />
										</html:form>
										<html:form styleId="refactorDataTypeForm" action="/transaction/dataTypeList.do">
											<html:hidden property="thisAction" ></html:hidden>
											<input name="label" type="button" class="button2"
												value="重构Lft和rgt" onclick="refactorDataType();" />
										</html:form>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<hr>
									</td>
								</tr>
								<tr>
									<td class="lef">
										在线用户
									</td>
									<td style="text-align: left">
										 <%=KumsOnlineCounter.getOnlineSession()%> 人
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<input type="button" value="返 回" class="button1"
											onclick="window.history.back();" />
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
	</body>
</html>