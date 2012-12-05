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
	</head>
	<body>
		<div id="mainContainer">
			<div id="container">
				<html:form action="/market/estateDishList.do">
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
									<c:param name="title1" value="地产事业部" />
									<c:param name="title2" value="查看楼盘" />
								</c:import>
								<hr>
								<table width="100%" cellpadding="0" cellspacing="0" border="0"
									class="dataList">
									<tr>
										<td class="lef">
											<c:out value="${estateDish.no}" />
										</td>
										<td style="text-align: left">
											<c:out value="${estateDish.company.shortName}" />
											|
											<c:out value="${estateDish.name}" />
										</td>
										<td class="lef">
											类型
										</td>
										<td style="text-align: left">
											<c:out value="${estateDish.typeInfo}" />

											<c:out value="${estateDish.statusInfo}" />
										</td>
									</tr>
									<tr>
										<td class="lef">
											开盘时间
										</td>
										<td style="text-align: left">
											<c:out value="${estateDish.beginDate}" />
										</td>
										<td class="lef">
											入住时间
										</td>
										<td style="text-align: left">
											<c:out value="${estateDish.entranceDate}" />
										</td>
									</tr>
									<tr>
										<td class="lef">
											地址
										</td>
										<td style="text-align: left" colspan="3">
											<c:out value="${estateDish.address}" />
										</td>
									</tr>
									<tr>
										<td class="lef">
											备注
										</td>
										<td style="text-align: left" colspan="3">
											<c:out value="${estateDish.memo}" />
										</td>
									</tr>
								</table>
								<table width="100%" style="margin-top: 5px;">
									<tr>
										<td align="center">
											<html:hidden property="selectedItems"
												value="${estateDish.id}" />
											<html:hidden property="thisAction" name="estateDish" />

											<input name="label" type="button" class="button1"
												value="新增楼盘" onclick="add();" />
											<input name="label" type="button" class="button1"
												value="新增单元" onclick="addApartment();" />
											<input name="label" type="button" class="button1" value="返 回"
												onclick="window.history.back();">
											<input name="label" type="button" class="button1" value="修 改"
												onclick="edit();" />
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
		var type=<c:out value="${estateDish.type}" />
	    document.forms[0].action="<%=path%>/market/estateDishList.do?thisAction=save&type="+type;
	    document.forms[0].submit();
	}	
	
	function edit(){
	    document.forms[0].action="<%=path%>/market/estateDishList.do?thisAction=edit";
	    document.forms[0].submit();
	}
		
	function addApartment(){
		var estateDishId=<c:out value="${estateDish.id}" />
	    document.forms[0].action="<%=path%>/market/apartmentList.do?thisAction=save&estateDishId="+estateDishId;
	    document.forms[0].submit();
	}	
	</script>
</html>
