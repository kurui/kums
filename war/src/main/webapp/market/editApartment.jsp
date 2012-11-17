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
		    document.forms[0].action="<%=path%>/market/apartment.do?thisAction="+thisAction;
		    document.forms[0].submit();
		}		
	</script>
	<body>
		<c:import url="../page/mainTitle.jsp" charEncoding="UTF-8">
			<c:param name="title1" value="地产事业部" />
			<c:param name="title2" value="编辑物业单元" />
		</c:import>
		<html:form action="/market/apartment.do" method="post">
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
								<table width="100%" cellpadding="0" cellspacing="0" border="0" class="dataList">
									<html:hidden property="id" value="${apartment.id}"></html:hidden>
									<c:if test="${apartment.thisAction eq 'update'}">
										<tr>
											<td class="lef">
												编号
											</td>
											<td style="text-align: left">
												<c:out value="${apartment.no}" />
											</td>
											<td class="lef">
											</td>
											<td style="text-align: left">
											</td>
										</tr>
									</c:if>
									<tr>
										<td class="lef">
											楼盘
										</td>
										<td style="text-align: left">
											<html:select property="estateDishId" value="${apartment.estateDish.id}" name="apartment" styleClass="colorblue2 p_5" style="width:200px;">
												<c:forEach items="${estateDishList}" var="estateDish">
													<html:option value="${estateDish.id}">
														<c:out value="${estateDish.name}" />
													</html:option>
												</c:forEach>
											</html:select>
										</td>
										<td class="lef">
											交易类型
										</td>
										<td style="text-align: left">
											<html:select property="transactionType" name="apartment" styleClass="colorblue2 p_5" style="width:50px;">
												<html:option value="1">出售</html:option>
												<html:option value="11">求购</html:option>
												<html:option value="21">出租</html:option>
												<html:option value="31">求租</html:option>
											</html:select>

											用途
											<html:select property="businessType" name="apartment" styleClass="colorblue2 p_5" style="width:50px;">
												<html:option value="1">住宅</html:option>
												<html:option value="11">商业</html:option>
											</html:select>

											状态
											<html:select property="status" name="apartment" styleClass="colorblue2 p_5" style="width:50px;">
												<html:option value="1">有效</html:option>
												<html:option value="0">无效</html:option>
											</html:select>
										</td>
									</tr>

									<tr>

										<td class="lef">
											户型
										</td>
										<td style="text-align: left">
											<html:text property="houseType" name="apartment" value="${apartment.houseType}" styleClass="colorblue2 p_5" style="width:200px;"></html:text>
										</td>
										<td class="lef">
											面积
										</td>
										<td style="text-align: left">
											<html:text property="area" name="apartment" value="${apartment.area}" styleClass="colorblue2 p_5" style="width:200px;"></html:text>
											m2
										</td>
									</tr>
									<tr>
										<td class="lef">
											报价时间
										</td>
										<td style="text-align: left">
											<html:text property="snatchDate" name="apartment" value="${apartment.snatchDate}" styleClass="colorblue2 p_5" style="width:200px;"
												onfocus="WdatePicker({startDate:'%y-%M-%D',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" readonly="true"></html:text>
										</td>

										<td class="lef">
											价格
										</td>
										<td style="text-align: left">
											报价
											<html:text property="quotePrice" name="apartment" value="${apartment.quotePrice}" styleClass="colorblue2 p_5" style="width:60px;"></html:text>

											均价
											<html:text property="averageAreaPrice" name="apartment" value="${apartment.averageAreaPrice}" styleClass="colorblue2 p_5" style="width:50px;"></html:text>
											/m2
										</td>
									</tr>

									<tr>
										<td class="lef">
											业主
										</td>
										<td style="text-align: left">
											<html:text property="owner" name="apartment" value="${apartment.owner}" styleClass="colorblue2 p_5" style="width:80px;"></html:text>

											联系人
											<html:text property="linkman" name="apartment" value="${apartment.linkman}" styleClass="colorblue2 p_5" style="width:80px;"></html:text>
										</td>
										<td class="lef">
											联系方式
										</td>
										<td style="text-align: left">
											<html:text property="connection" name="apartment" value="${apartment.connection}" styleClass="colorblue2 p_5" style="width:200px;"></html:text>
										</td>
									</tr>
									<tr>
										<td class="lef">
											说明
										</td>
										<td style="text-align: left" colspan="3">
											<html:textarea property="memo" name="apartment" value="${apartment.memo}" styleClass="colorblue2 p_5" style="font-size: 12px;width:500px;height:100px"></html:textarea>
										</td>
									</tr>
								</table>
								<table width="100%" style="margin-top: 5px;">
									<tr>
										<td align="center">
											<html:hidden property="thisAction" name="apartment" />
											<input name="label" type="button" class="button1" value="返 回" onclick="window.history.back();">
											<input name="label" type="button" class="button1" value="提交" onclick="add();">
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