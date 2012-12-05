<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/tld/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%
	String path = request.getContextPath();
%>
<html>
	<head>
		<TITLE></TITLE>
		<title>main</title>
		<link href="../_css/reset.css" rel="stylesheet" type="text/css" />
		<link href="../_css/global.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" language="javascript"
			src="../_js/jquery-1.3.2.min.js"></script>
		<script src="../_js/prototype/common.js" type="text/javascript"></script>
		<script src="../_js/prototype/prototype.js" type="text/javascript"></script>
		<script src="../_js/base/select.js" type="text/javascript"></script>
		<script>
  function submitForm(){
     document.forms[0].thisAction.value="editAgentGroup";
     document.forms[0].submit();
  }
   
  function saveForm() {
     //document.forms[0].thisAction.value="updaterole4user";
      document.forms[0].action="<%=path%>/agent/agentRelation.do?thisAction=updateAgentGroup";
     
     document.forms[0].count.value=$('rightRoleID').options.length;
     js.select.selectAll($('rightRoleID'));
     document.forms[0].submit();
  }
   
 function moveAll(obj1,obj2) {
   js.select.moveAll(obj1,obj2); 
   js.select.sort(obj2);
 } 
 
 function moveLeft2Right(obj1,obj2) {
   js.select.moveSelected(obj1,obj2); 
   js.select.sort(obj2);
 }
 
 function moveRight2Left(obj2,obj1) {
   js.select.moveSelected(obj2,obj1);
   js.select.sort(obj2);
 }

</script>
	</head>
	<body class="body_m">
		<c:import url="/page/mainTitle.jsp" charEncoding="UTF-8">
			<c:param name="title" value="客户管理" />
			<c:param name="title1" value="上下级管理" />
		</c:import>
		<html:form action="/agent/agentRelationList.do">
			<html:hidden property="thisAction" />
			<html:hidden property="agentIds" />
			<html:hidden property="intPage" />
			<html:hidden property="pageCount" />
			<html:hidden property="count" />
			<table width="100%" height="45" class="table_sc">
				<tr>
					<td>
						<div align="center">
							客户
						</div>
					</td>
					<td>
						<div align="left">
							<html:select property="agentId" onchange="submitForm();">
								<html:options collection="agentlist" property="value"
									labelProperty="label" />
							</html:select>
						</div>
					</td>
				</tr>
			</table>
			<table width="100%" cellspacing="1" class="table_li">
				<tr class="table_li1">
					<td>
						<div align="center">
							客户列表
						</div>
					</td>
					<td>
						<div align="center"></div>
					</td>
					<td>
						<div align="center">
							选中的客户关系人
						</div>
					</td>
				</tr>
				<tr class="table_li2">
					<td rowspan="4" class="cGray">
						<html:select property="leftRoleID" styleId="leftRoleID" size="10"
							multiple="true" style="width:200px">
							<html:options collection="agentlist1" property="value"
								labelProperty="label" />
						</html:select>
					</td>
					<td class="cGray">
						<input type="button" class="button1"
							onclick="moveAll($('leftRoleID'),$('rightRoleID'));" value="全部右移">
					</td>
					<td rowspan="4" class="cGray">
						<html:select property="rightRoleID" styleId="rightRoleID"
							size="10" multiple="true" style="width:200px">
							<html:options collection="agentlist2" property="value"
								labelProperty="label" />
						</html:select>
					</td>
				</tr>
				<tr class="table_li2">
					<td class="cGray">
						<input type="button" class="button1"
							onclick="moveLeft2Right($('leftRoleID'),$('rightRoleID'));"
							value="右  移">
					</td>
				</tr>
				<tr class="table_li2">
					<td class="cGray">
						<input type="button" class="button1"
							onclick="moveRight2Left($('rightRoleID'),$('leftRoleID'));"
							value="左  移">
					</td>
				</tr>
				<tr class="table_li2">
					<td class="cGray">
						<input type="button" class="button1"
							onclick="moveAll($('rightRoleID'),$('leftRoleID'));" value="全部左移">
					</td>
				</tr>
			</table>
			<table width="100%" style="margin-top: 5px;">
				<tr align="center">
					<td>
						<input type="button" class="button1" onclick="saveForm();"
							value="保 存">
						<input type="button" class="button1" onClick="submitForm();"
							value="重 置">
					</td>
				</tr>
			</table>
		</html:form>
	</body>
</html>