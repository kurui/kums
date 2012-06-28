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
		     document.forms[0].action="<%=path%>/market/estateDish.do?thisAction="+thisAction;
		    document.forms[0].submit();
		}		
	</script>
	<body>
		<c:import url="../page/mainTitle.jsp" charEncoding="UTF-8">
			<c:param name="title1" value="地产事业部" />
			<c:param name="title2" value="编辑楼盘" />
		</c:import>
		<html:form action="/market/estateDish.do" method="post">
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
									<c:if test="${estateDish.thisAction eq 'update'}">
										<tr>
											<td class="lef">
												编号
											</td>
											<td style="text-align: left">
												<c:out value="${estateDish.no}" />
											</td>
											<td class="lef">
											</td>
											<td style="text-align: left">
											</td>
										</tr>
									</c:if>
									<tr>
										<td class="lef">
											名称
										</td>
										<td style="text-align: left">
											<html:text property="name" name="estateDish"
												value="${estateDish.name}" styleClass="colorblue2 p_5"
												style="width:200px;"></html:text>
											<html:hidden property="id" value="${estateDish.id}"></html:hidden>
										</td>
										<td class="lef">
											类型
										</td>
										<td style="text-align: left">
											<html:select property="type" name="estateDish"
												styleClass="colorblue2 p_5" style="width:120px;">
												<html:option value="">-请选择-</html:option>
												<html:option value="1">住宅</html:option>
												<html:option value="11">写字楼</html:option>
											</html:select>
										</td>
									</tr>
									<tr>
										<td class="lef">
											开盘时间
										</td>
										<td style="text-align: left">
											<html:text property="beginDate" name="estateDish"
												value="${estateDish.beginDate}" styleClass="colorblue2 p_5"
												style="width:120px;"
												onfocus="WdatePicker({startDate:'%y-%M-%D',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"
												readonly="true"></html:text>
										</td>
										<td class="lef">
											入住时间
										</td>
										<td style="text-align: left">
											<html:text property="entranceDate" name="estateDish"
												value="${estateDish.entranceDate}"
												styleClass="colorblue2 p_5" style="width:120px;"
												onfocus="WdatePicker({startDate:'%y-%M-%D',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"
												readonly="true"></html:text>
										</td>
									</tr>
									<tr>
										<td class="lef">
											开发商
										</td>
										<td style="text-align: left">
											<html:select property="companyId"
												value="${estateDish.company.id}" name="estateDish"
												styleClass="colorblue2 p_5" style="width:200px;">
												<html:option value="0">-开发商-</html:option>
												<c:forEach items="${companyList}" var="company">
													<html:option value="${company.id}">
														<c:out value="${company.name}" />
													</html:option>
												</c:forEach>
											</html:select>
										</td>
										<td class="lef">
										</td>
										<td style="text-align: left">
										</td>
									</tr>
									<tr>
										<td class="lef">
											地址
										</td>
										<td style="text-align: left" colspan="3">
											<html:text property="address" name="estateDish"
												value="${estateDish.address}" styleClass="colorblue2 p_5"
												style="width:300px;"></html:text>
										</td>
									</tr>
									<tr>
										<td class="lef">
											说明
										</td>
										<td style="text-align: left" colspan="3">
											<html:textarea property="memo" name="estateDish"
												value="${estateDish.memo}" styleClass="colorblue2 p_5"
												style="font-size: 12px;width:500px;height:150px;"></html:textarea>
										</td>
									</tr>
									<tr>
										<td class="lef">
											状态
										</td>
										<td style="text-align: left">

											<html:select property="status" name="estateDish"
												styleClass="colorblue2 p_5" style="width:50px;">
												<html:option value="1">有效</html:option>
												<html:option value="0">无效</html:option>
											</html:select>
										</td>
										<td class="lef">
										</td>
										<td style="text-align: left">
										</td>
									</tr>
								</table>
								<table width="100%" style="margin-top: 5px;">
									<tr>
										<td align="center">
											<html:hidden property="thisAction" name="estateDish" />
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