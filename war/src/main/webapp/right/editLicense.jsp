<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>

<html>
<head>
<title>main</title>
<link href="../_css/reset.css" rel="stylesheet" type="text/css" />
<link href="../_css/global.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" language="javascript"
	src="../_js/jquery-1.3.1.min.js"></script>
<script src="../_js/common.js" type="text/javascript"></script>
<script>				
						
			function add(){
			    document.forms[0].submit();
			}						
		</script>
</head>
<body>
	<div id="mainContainer" style="height: 1000px;">
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
							<c:param name="title1" value="授权管理" />
							<c:param name="title2" value="编辑授权信息" />
						</c:import>
						<hr> <html:form action="/right/license.do">
							<html:hidden property="id" value="${license.id}"></html:hidden>
							<html:hidden property="thisAction" value="${license.thisAction}"></html:hidden>
							<table width="100%" cellpadding="0" cellspacing="0" border="0"
								class="dataList">
								<tr>
									<td>授权编号</td>
									<td><html:text property="licenseNo" name="license"
											styleClass="colorblue2 p_5" style="width:300px;"></html:text>
									</td>
								</tr>
								<tr>
									<td>公司组织编号</td>
									<td><html:text property="companyNo" name="license"
											styleClass="colorblue2 p_5" style="width:300px;"></html:text>
									</td>
								</tr>
								<tr>
									<td>备注</td>
									<td><html:text property="memo" name="license"
											styleClass="colorblue2 p_5"></html:text></td>
								</tr>
								<tr>
									<td>授权类型</td>
									<td><html:radio property="licenseType" value="0" name="license">试用</html:radio>
										<html:radio property="licenseType" value="1" name="license">正式</html:radio>
									</td>
								</tr>
									<tr>
									<td>状态</td>
									<td><html:radio property="status" value="0" name="license">不显示</html:radio>
										<html:radio property="status" value="1" name="license">显示</html:radio>
									</td>
								</tr>
								<tr>
									<td>&nbsp;</td>
									<td><input type="button" value="返 回" class="button1"
										onclick="window.history.back();" /> <input name="label"
										type="button" class="button1" value="提 交" onclick="add();" />
										<input name="label" type="reset" class="button1" value="重 置">

									</td>

								</tr>
								
							</table>
						</html:form>
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
</body>
</html>