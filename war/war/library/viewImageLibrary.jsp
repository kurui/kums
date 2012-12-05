<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%
	String path = request.getContextPath();
%>
<html>
	<head>
		<title>main</title>
		<link href="../_css/reset.css" rel="stylesheet" type="text/css" />
		<link href="../_css/global.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
	</script>
	</head>
	<body>
		<div id="mainContainer">
			<div id="container">
				<html:form action="/library/imageLibraryList.do">
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
									<c:param name="title1" value="核心资源库" />
									<c:param name="title2" value="查看图片" />
								</c:import>
								<hr>
								<table width="100%" cellpadding="0" cellspacing="0" border="0"
									class="dataList">
									<tr>
										<td class="lef">
											名称
										</td>
										<td style="text-align: left">
											<c:out value="${imageLibrary.name}" />											
										</td>
											<td class="lef">
											更新时间
										</td>
										<td style="text-align: left">
											<c:out value="${imageLibrary.updateDate}" />
										</td>
									</tr>					
									
									<tr>
										<td style="text-align: left" colspan="4">
											<img  src="<%=path%>/library/imageLibraryList.do?thisAction=viewImage&id=<c:out value="${imageLibrary.id}" />">
										</td>
									</tr>	
									<tr>
										<td class="lef">
											备注
										</td>
										<td style="text-align: left" colspan="3">
											<c:out value="${imageLibrary.memo}" />
										</td>
									</tr>									
									<tr>
										<td class="lef">
											类型
										</td>
										<td style="text-align: left">
											<c:out value="${imageLibrary.typeInfo}" />
										</td>
										<td class="lef">
											状态
										</td>
										<td style="text-align: left">
											<c:out value="${imageLibrary.statusInfo}" />
										</td>
									</tr>									
								</table>
								<table width="100%" style="margin-top: 5px;">
									<tr>
										<td align="center">
											<html:hidden property="selectedItems" value="${imageLibrary.id}" />
											<html:hidden property="thisAction" name="imageLibrary" />
											<input name="label" type="button" class="button1" value="新 增"
												onclick="add();">
											<input name="label" type="button" class="button1" value="返 回"
												onclick="window.history.back();">

											<input name="label" type="button" class="button1" value="修 改"
												onclick="edit();">

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
		function add(){		   
		    var type=<c:out value="${imageLibrary.type}" />;
		    document.forms[0].action="<%=path%>/library/imageLibraryList.do?thisAction=save";
		    document.forms[0].submit();
		}
		
		function edit(){
		    document.forms[0].action="<%=path%>/library/imageLibraryList.do?thisAction=edit";
		    document.forms[0].submit();
		}
	</script>
</html>
