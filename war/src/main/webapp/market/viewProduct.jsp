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
				<html:form action="/market/productList.do">
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
									<c:param name="title1" value="市场管理" />
									<c:param name="title3" value="查看产品" />
								</c:import>
								<hr>
								<table width="100%" cellpadding="0" cellspacing="0" border="0"
									class="dataList">
									<tr>
										<td class="lef">
											品名
										</td>
										<td style="text-align: left" colspan="3">
											<c:out value="${product.no}" />
											|
											<c:out value="${product.name}" />
										</td>
										<td class="lef">
											绩效权重
										</td>
										<td style="text-align: left">
											<c:out value="${product.proportionInfo}"></c:out>
										</td>
									</tr>
									<tr>
										<td class="lef">
											货品类型
										</td>
										<td style="text-align: left">
											<c:out value="${product.productTypeInfo}"></c:out>
										</td>
										<td class="lef">
											规格
										</td>
										<td style="text-align: left">
											<c:out value="${product.standard}"></c:out>
										</td>
										<td class="lef">
											剂型
										</td>
										<td style="text-align: left">
											<c:out value="${product.classTypeInfo}"></c:out>
										</td>

									</tr>
									<tr>

										<td class="lef">
											进货价
										</td>
										<td style="text-align: left">
											<c:out value="${product.purchasePrice}"></c:out>
										</td>
										<td class="lef">
											零售价
										</td>
										<td style="text-align: left">
											<c:out value="${product.price}"></c:out>
										</td>

										<td class="lef">
											批发价
										</td>
										<td style="text-align: left">
											<c:out value="${product.tradePrice}"></c:out>
										</td>
									</tr>
									<tr>
										<td class="lef">
											库存
										</td>
										<td style="text-align: left">
											<c:out value="${product.stockCount}" />
										</td>
										<td class="lef">
											类型
										</td>
										<td style="text-align: left">
											<c:out value="${product.typeInfo}" />
										</td>
										<td class="lef">
											状态
										</td>
										<td style="text-align: left">
											<c:out value="${product.statusInfo}" />
										</td>

									</tr>
									<tr>
										<td class="lef">
											备注
										</td>
										<td style="text-align: left" colspan="5">
											<c:out value="${product.memo}" />
										</td>
									</tr>

								</table>
								<table width="100%" style="margin-top: 5px;">
									<tr>
										<td align="center">
											<html:hidden property="selectedItems" value="${product.id}" />
											<html:hidden property="thisAction" name="product" />

											<input name="label" type="button" class="button1" value="新 增"
												onclick="add();" />
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
		    document.forms[0].action="<%=path%>/market/productList.do?thisAction=save";
		    document.forms[0].submit();
		}
		function edit(){
		    document.forms[0].action="<%=path%>/market/productList.do?thisAction=edit";
		    document.forms[0].submit();
		}
	</script>
</html>
