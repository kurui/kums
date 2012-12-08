<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>

<%
	String path = request.getContextPath();
%>
<html>
<head>
<link href="<%=path%>/_css/reset.css" rel="stylesheet" type="text/css" />
<link href="<%=path%>/_css/global.css" rel="stylesheet" type="text/css" />
<script src="../_js/prototype.js" type="text/javascript"></script>
<script src="../_js/prototype/common.js" type="text/javascript"></script>
<script type="text/javascript" src="../_js/jquery-1.3.2.min.js"></script>

<script>
 function submitForm() {
 	if(document.forms[0].thisAction.value=="insert"){
 		if (valid()){
  	  		document.forms[0].submit();	
  	  	}
  	}else{  	
  	  document.forms[0].submit();	
  	}
 }
 
 function valid() {
   if(document.forms[0].uploadFile.value==""){
     alert("附件名不能为空！");
     document.forms[0].uploadFile.focus();
     return false;
   }else{
     return true; 
   }
}

 function onchangeUploadFile() {
 	var filePath=document.forms[0].uploadFile.value;
 	//alert(filePath);
 	var beginIndex=filePath.lastIndexOf("\\");
 	var endIndex=filePath.length;

 	filePath=filePath.substr(beginIndex+1,endIndex);
 	
 	document.forms[0].name.value=filePath;
 }
</script>
</head>

<body>

	<html:form method="post" action="/library/imageLibrary.do"
		enctype="multipart/form-data">
		<html:hidden property="id" />
		<html:hidden property="thisAction" />
		<html:hidden property="tableName" />
		<html:hidden property="rowId" />
		
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
						<td valign="top" class="body"><c:import
								url="../page/mainTitle.jsp" charEncoding="UTF-8">
								<c:param name="title1" value="核心资源库" />
								<c:param name="title2" value="编辑图片" />
							</c:import>
							<hr>
							<table width="100%" cellpadding="0" cellspacing="0" border="0"
								class="dataList">
								<tr>
									<td class="lef">名称</td>
									<td style="text-align: left"><html:text property="name"
											name="imageLibrary" styleClass="colorblue2 p_5"
											style="width:200px;"></html:text></td>
											<td class="lef">说明</td>
									<td style="text-align: left"><html:text property="memo"
											name="imageLibrary" styleClass="colorblue2 p_5"
											style="width:200px;"></html:text></td>
									<td><input type="button" class="button1"
										value="返 回" onclick="window.history.back();"> <input
										name="label" type="button" class="button1" value="完 成"
										onclick="submitForm();"></td>
								</tr>
								<c:if test="${imageLibrary.thisAction=='insert' or imageLibrary.thisAction=='insertDependent'}">
									<tr>
										<td class="lef">选择上传图片</td>
										<td style="text-align: left"><html:file
												property="uploadFile" name="imageLibrary"
												styleClass="colorblue2 p_5" size="40" onchange="onchangeUploadFile();" />
									</tr>
								</c:if>
								<c:if test="${imageLibrary.thisAction=='update'}">
									<tr>
										<td style="text-align: left" colspan="5"><img
											src="<%=path%>/library/imageLibraryList.do?thisAction=viewImage&id=<c:out value="${imageLibrary.id}" />">
										</td>
									</tr>
								</c:if>
								
								<tr>
									<td colspan="5"><input type="button" class="button1"
										value="返 回" onclick="window.history.back();"> </td>
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
			</div>
		</div>
	</html:form>

</body>
</html>
