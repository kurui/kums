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
				<html:form action="/transaction/companyList.do">
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
									<c:param name="title1" value="机构管理" />
									<c:param name="title2" value="查看公司" />
								</c:import>
								<hr>
								<table width="100%" cellpadding="0" cellspacing="0" border="0"
									class="dataList">
									<tr>
										<td class="lef">
											名称
										</td>
										<td style="text-align: left">
											<c:out value="${company.name}" />											
										</td>
										
										<td class="lef">
											简称
										</td>
										<td style="text-align: left">
											<c:out value="${company.shortName}" />
											(<c:out value="${company.registerType}" />)
										</td>
									</tr>
									<tr>
										<td class="lef">
											法人代表(授权控制人)
										</td>
										<td style="text-align: left">
											<c:out value="${company.deputy}" />
										</td>
										<td class="lef">
											电话/传真
										</td>
										<td style="text-align: left">
											<c:out value="${company.telephone}" />
										</td>
									</tr>
									<tr>
										<td class="lef">
											注册资本
										</td>
										<td style="text-align: left">
											<c:out value="${company.registerCapital}" />
										</td>
										<td class="lef">
											净资产
										</td>
										<td style="text-align: left">
											<c:out value="${company.netAssetValue}" />
										</td>
									</tr>
									<tr>
										<td class="lef">
											注册地址
										</td>
										<td style="text-align: left">
											<c:out value="${company.registerAddress}" />
										</td>
										<td class="lef">
											经营实体地址
										</td>
										<td style="text-align: left">
											<c:out value="${company.entityAddress}" />
										</td>
									</tr>
									<tr>
										<td class="lef">
											工商登记号
										</td>
										<td style="text-align: left">
											<c:out value="${company.registerCode}" />
										</td>
										<td class="lef">
											税务登记号
										</td>
										<td style="text-align: left">
											<c:out value="${company.revenueCode}" />
										</td>
									</tr>
									<tr>
										<td class="lef">
											法人代码证号
										</td>
										<td style="text-align: left">
											<c:out value="${company.corporationCode}" />
										</td>
										<td class="lef">
											成立时间
										</td>
										<td style="text-align: left">
											<c:out value="${company.registerDate}" />
										</td>
									</tr>
									<tr>
										<td class="lef">
											主营业务
										</td>
										<td style="text-align: left" width="30%">
											<c:out value="${company.mainBusiness}" />
										</td>
										<td class="lef">
											兼营业务
										</td>
										<td style="text-align: left">
											<c:out value="${company.sidelineBusiness}" />
										</td>
									</tr>
									<tr>
										<td class="lef">
											供应链
										</td>
										<td style="text-align: left" colspan="3">
											<c:out value="${company.provideChainInfo}" />
										</td>
									</tr>
									<tr>
										<td class="lef">
											备注
										</td>
										<td style="text-align: left" colspan="3">
											<c:out value="${company.memo}" />
										</td>
									</tr>
									<tr>
										<td class="lef">
											账号
										</td>
										<td style="text-align: left" colspan="3">
											<a
												href="<%=path%>/transaction/companyAccountList.do?thisAction=save&companyId=<c:out value="${company.id}" />&accountType=11">增加账号</a>
											|
											<a
												href="<%=path%>/transaction/companyAccountList.do?thisAction=list&companyId=<c:out value="${company.id}" />">账号列表</a>

										</td>
									</tr>
									<tr>
										<td class="lef">
											类型
										</td>
										<td style="text-align: left">
											<c:out value="${company.typeInfo}" />
										</td>
										<td class="lef">
											状态
										</td>
										<td style="text-align: left">
											<c:out value="${company.statusInfo}" />
										</td>
									</tr>
									<tr>
										<td class="lef">
											修改人
										</td>
										<td style="text-align: left">
											<c:out value="${company.userName}" />
										</td>
										<td class="lef">
											最后更新
										</td>
										<td style="text-align: left">
											<c:out value="${company.updateDate}" />
										</td>
									</tr>
								</table>
								<table width="100%" style="margin-top: 5px;">
									<tr>
										<td align="center">
											<html:hidden property="selectedItems" value="${company.id}" />
											<html:hidden property="thisAction" name="company" />
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
		    var type=<c:out value="${company.type}" />;
		    document.forms[0].action="<%=path%>/transaction/companyList.do?thisAction=save&type="+type;
		    document.forms[0].submit();
		}
		
		function edit(){
		    document.forms[0].action="<%=path%>/transaction/companyList.do?thisAction=edit";
		    document.forms[0].submit();
		}
	</script>
</html>
