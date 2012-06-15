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
				<html:form action="/market/apartmentList.do">
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
									<c:param name="title2" value="查看物业单元" />
								</c:import>
								<hr>
								<table width="100%" cellpadding="0" cellspacing="0" border="0"
									class="dataList">
									<tr>
										<td class="lef">
											<c:out value="${apartment.no}" />
										</td>
										<td style="text-align: left">
											<c:out value="${apartment.estateDish.name}" />
											&nbsp;&nbsp;[
											<c:out value="${apartment.estateDish.typeInfo}" />
											]
										</td>
										<td class="lef">
											物业单元
										</td>
										<td style="text-align: left">
											<c:out value="${apartment.transactionTypeInfo}" />
											|
											<c:out value="${apartment.businessTypeInfo}" />
											|
											<c:out value="${apartment.statusInfo}" />
										</td>
									</tr>
									<tr>
										<td class="lef">
											报价时间
										</td>
										<td style="text-align: left">
											<c:out value="${apartment.snatchDate}" />
										</td>
										<td class="lef">
											报价
										</td>
										<td style="text-align: left">
											<c:out value="${apartment.quotePrice}" />
											&nbsp;&nbsp;均价
											<c:out value="${apartment.averageAreaPrice}" />
										</td>
									</tr>
									<tr>
										<td class="lef">
											户型
										</td>
										<td style="text-align: left">
											<c:out value="${apartment.houseType}" />
										</td>
										<td class="lef">
											面积
										</td>
										<td style="text-align: left">
											<c:out value="${apartment.area}" />
										</td>
									</tr>

									<tr>
										<td class="lef">
											业主
										</td>
										<td style="text-align: left">
											<c:out value="${apartment.owner}" />
											联系人
											<c:out value="${apartment.linkman}" />
										</td>
										<td class="lef">
											联系方式
										</td>
										<td style="text-align: left">
											<c:out value="${apartment.connection}" />
										</td>
									</tr>
									<tr>
										<td class="lef">
											备注
										</td>
										<td style="text-align: left" colspan="3">
											<c:out value="${apartment.memo}" escapeXml="false" />
										</td>
									</tr>
								</table>
								<table width="100%" style="margin-top: 5px;">
									<tr>
										<td align="center">
											<html:hidden property="selectedItems" value="${apartment.id}" />
											<html:hidden property="thisAction" name="apartment" />

											<input name="label" type="button" class="button1"
												value="新增楼盘" onclick="addEstateDish();" />
											<input name="label" type="button" class="button1"
												value="新增单元" onclick="add();" />
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
		function addEstateDish(){	
			 	document.forms[0].action="<%=path%>/market/estateDishList.do?thisAction=save";
		    	document.forms[0].submit();	   
		}
	
		function add(){		 
			var estateDishId="<c:out value='${apartment.estateDish.id}' />";
			if(estateDishId!=null){
			 	document.forms[0].action="<%=path%>/market/apartmentList.do?thisAction=save&estateDishId="+estateDishId;
		    	document.forms[0].submit();
			}else{
				alert("参数缺失:estateDishId");
			}		   
		}
		
		function edit(){
		    document.forms[0].action="<%=path%>/market/apartmentList.do?thisAction=edit";
		    document.forms[0].submit();
		}
	</script>
</html>