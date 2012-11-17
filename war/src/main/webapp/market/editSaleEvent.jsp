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
		<link href="<%=path%>/_css/reset.css" rel="stylesheet" type="text/css" />
		<link href="<%=path%>/_css/global.css" rel="stylesheet"
			type="text/css" />
	</head>
	<script type="text/javascript">	
		function add()		{	
			var name=document.forms[0].name.value;
			if(name==""){
				alert("请输入名称!")
				return false;
			}
			var thisAction =document.forms[0].thisAction.value;			   
		     document.forms[0].action="<%=path%>/market/saleEvent.do?thisAction="+thisAction;
		    document.forms[0].submit();
		}		
	</script>
	<body>
		<c:import url="../page/mainTitle.jsp" charEncoding="UTF-8">
			<c:param name="title1" value="市场管理" />
			<c:param name="title2" value="编辑销售活动" />
		</c:import>
		<html:form action="/market/saleEvent.do" method="post">
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
									<tr>
										<td class="lef">
											名称
										</td>
										<td style="text-align: left">
											<html:text property="name" name="saleEvent"
												value="${saleEvent.name}" styleClass="colorblue2 p_5"
												style="width:200px;"></html:text>
										</td>
									</tr>
									<tr>
										<td class="lef">
											类型
										</td>
										<td style="text-align: left">
											<html:select property="type" name="saleEvent"
												styleClass="colorblue2 p_5" style="width:120px;">
												<html:option value="">-请选择-</html:option>
												<html:option value="1">默认</html:option>
											</html:select>
											状态
											<html:select property="status" name="saleEvent"
												styleClass="colorblue2 p_5" style="width:50px;">
												<html:option value="1">有效</html:option>
												<html:option value="0">无效</html:option>
											</html:select>
									</tr>
									<tr>
										<td class="lef">
											说明
										</td>
										<td style="text-align: left">
											<html:textarea property="memo" name="saleEvent"
												value="${saleEvent.memo}" styleClass="colorblue2 p_5"
												style="width:500px;height:100px"></html:textarea>
										</td>
									</tr>

								</table>
								<table width="100%" style="margin-top: 5px;">
									<tr>
										<td>
											<html:hidden property="id" value="${saleEvent.id}"></html:hidden>

											<html:hidden property="thisAction" name="saleEvent" />
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