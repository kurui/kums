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
				<html:form action="/market/priceIndexList.do">
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
									<c:param name="title1" value="金融服务事业部" />
									<c:param name="title2" value="行情中心" />
									<c:param name="title3" value="查看物价指数" />
								</c:import>
								<hr>
								<table width="100%" cellpadding="0" cellspacing="0" border="0" class="dataList">
									<tr>
										<td class="lef">
											名称
										</td>
										<td style="text-align: left">
											<c:out value="${priceIndex.priceReference.name}" />
											&nbsp;&nbsp;[
											<c:out value="${priceIndex.priceReference.typeInfo}" />
											]
										</td>
									</tr>
									<tr>
										<td class="lef">
											价格
										</td>
										<td style="text-align: left">
											<c:out value="${priceIndex.price}" />
										</td>
									</tr>
									<tr>
										<td class="lef">
											最高
										</td>
										<td style="text-align: left">
											<c:out value="${priceIndex.maxPrice}" />
										</td>
									</tr>
									<tr>
										<td class="lef">
											最低
										</td>
										<td style="text-align: left">
											<c:out value="${priceIndex.minPrice}" />
										</td>
									</tr>
									<tr>
										<td class="lef">
											涨跌
										</td>
										<td style="text-align: left">
											<c:out value="${priceIndex.rangeInfo}" />
										</td>
									</tr>
									<tr>
										<td class="lef">
											单位
										</td>
										<td style="text-align: left">
											<c:out value="${priceIndex.priceReference.money}" />
											|
											<c:out value="${priceIndex.priceReference.unit}" />
										</td>
									</tr>
									<tr>
										<td class="lef">
											时间
										</td>
										<td style="text-align: left">
											<c:out value="${priceIndex.showSnatchTime}" />
										</td>
									</tr>
									<tr>
										<td class="lef">
											备注
										</td>
										<td style="text-align: left">
											<c:out value="${priceIndex.memo}" />
										</td>
									</tr>
									<tr>
										<td class="lef">
											状态
										</td>
										<td style="text-align: left">
											<c:out value="${priceIndex.statusInfo}" />
										</td>
									</tr>

								</table>
								<table width="100%" style="margin-top: 5px;">
									<tr>
										<td align="center">
											<html:hidden property="selectedItems" value="${priceIndex.id}" />
											<html:hidden property="thisAction" name="priceIndex" />
											<input name="label" type="button" class="button1" value="新 增" onclick="add();" />
											<input name="label" type="button" class="button1" value="返 回" onclick="window.history.back();">
											<input name="label" type="button" class="button1" value="修 改" onclick="edit();" />
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
			var priceReferenceId="<c:out value='${priceIndex.priceReference.id}' />";
			var priceReferenceType="<c:out value='${priceIndex.priceReference.type}' />";
			if(priceReferenceId!=null){
			 	document.forms[0].action="<%=path%>/market/priceIndexList.do?thisAction=saveDest&priceReferenceTypes="+priceReferenceType+"&priceReferenceId="+priceReferenceId;
		    	document.forms[0].submit();
			}else{
				alert("参数缺失:priceReferenceId");
			}		   
		}
		
		function edit(){
		    document.forms[0].action="<%=path%>/market/priceIndexList.do?thisAction=edit";
		    document.forms[0].submit();
		}
	</script>
</html>