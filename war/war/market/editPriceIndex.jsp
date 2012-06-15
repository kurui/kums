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
		<link href="../_css/reset.css" rel="stylesheet" type="text/css" />
		<link href="../_css/global.css" rel="stylesheet" type="text/css" />
		<script src="../_js/calendar/WdatePicker.js" type="text/javascript"></script>
		<script src="../_js/base/FormUtil.js" type="text/javascript"></script>
	</head>

	<script type="text/javascript">	
		function add()		{	
			var name=document.forms[0].name.value;
			if(name==""){
				alert("请输入名称!")
				return false;
			}
			var thisAction =document.forms[0].thisAction.value;			   
		    document.forms[0].action="<%=path%>/market/priceIndex.do?thisAction="+thisAction;
		    document.forms[0].submit();
		}		
	</script>
	<body>
		<c:import url="../page/mainTitle.jsp" charEncoding="UTF-8">
			<c:param name="title1" value="财务管理" />
			<c:param name="title2" value="编辑参考系数" />
		</c:import>
		<html:form action="/market/priceIndex.do" method="post">
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
									<html:hidden property="id" value="${priceIndex.id}"></html:hidden>
									<tr>
										<td class="lef">
											参照系数
										</td>
										<td style="text-align: left">
											<html:select property="priceReferenceId"
												value="${priceIndex.priceReference.id}" name="priceIndex"
												styleClass="colorblue2 p_5" style="width:200px;">
												<c:forEach items="${priceReferenceList}" var="p">
													<html:option value="${p.id}">
														<c:out value="${p.name}" />
													</html:option>
												</c:forEach>
											</html:select>
										</td>
									</tr>
									<tr>
										<td class="lef">
											采集地
										</td>
										<td style="text-align: left">
											<html:select property="gatherCompanyId"
												value="${priceIndex.gatherCompany.id}" name="priceIndex"
												styleClass="colorblue2 p_5" style="width:200px;">
												<html:option value="0">-请选择-</html:option>
												<c:forEach items="${gatherCompanyList}" var="company">
													<html:option value="${company.id}">
														<c:out value="${company.shortName}" />
													</html:option>
												</c:forEach>
											</html:select>
										</td>
									</tr>
									<tr>
										<td class="lef">
											价格
										</td>
										<td style="text-align: left">
											<html:text property="price" name="priceIndex"
												value="${priceIndex.price}" styleClass="colorblue2 p_5"
												style="width:200px;"></html:text>
										</td>
									</tr>
									<tr>
										<td class="lef">
											最高
										</td>
										<td style="text-align: left">
											<html:text property="maxPrice" name="priceIndex"
												value="${priceIndex.maxPrice}" styleClass="colorblue2 p_5"
												style="width:200px;"></html:text>
										</td>
									</tr>
									<tr>
										<td class="lef">
											最低
										</td>
										<td style="text-align: left">
											<html:text property="minPrice" name="priceIndex"
												value="${priceIndex.minPrice}" styleClass="colorblue2 p_5"
												style="width:200px;"></html:text>
										</td>
									</tr>
									<tr>
										<td class="lef">
											涨跌
										</td>
										<td style="text-align: left">
											<html:text property="range" name="priceIndex"
												value="${priceIndex.range}" styleClass="colorblue2 p_5"
												style="width:200px;"></html:text>
										</td>
									</tr>
									<tr>
										<td class="lef">
											时间
										</td>
										<td style="text-align: left">
											<html:text property="snatchTime" name="priceIndex"
												value="${priceIndex.snatchTime}" styleClass="colorblue2 p_5"
												style="width:200px;"
												onfocus="WdatePicker({startDate:'%y-%M-%D 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})"
												readonly="true"></html:text>
										</td>
									</tr>
									<tr>
										<td class="lef">
											说明
										</td>
										<td style="text-align: left">
											<html:text property="memo" name="priceIndex"
												value="${priceIndex.memo}" styleClass="colorblue2 p_5"
												style="width:200px;"></html:text>
										</td>
									</tr>
									<tr>
										<td class="lef">
											状态
										</td>
										<td style="text-align: left">

											<html:select property="status" name="priceIndex"
												styleClass="colorblue2 p_5" style="width:50px;">
												<html:option value="1">有效</html:option>
												<html:option value="0">无效</html:option>
											</html:select>
										</td>
									</tr>
								</table>
								<table width="100%" style="margin-top: 5px;">
									<tr>
										<td>
											<html:hidden property="thisAction" name="priceIndex" />
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
</html>