<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%
	String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<link href="../_css/reset.css" rel="stylesheet" type="text/css" />
		<link href="../_css/global.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" language="javascript"
			src="../_js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript" src="../_js/prototype/common.js"></script>
		<script type="text/javascript">		
		function confirmProduct(){
			var values="";			
			var selectedItems=document.forms[0].selectedItems;
			var chklength=selectedItems.length;
			
			if(chklength>1){				
				for(var i=0;i<chklength;i++){
					var chk=document.forms[0].selectedItems[i];
					if(chk.checked){
						values += chk.value+",";
					}				
				}
			}else{
				if(selectedItems!=null){
					values=selectedItems.value;
				}					
			}	
			
			if(values.lastIndexOf(",")>0){
				values=values.substring(0,values.lastIndexOf(","));
			}			

			var rowId="<c:out value="${productListForm.rowId}"></c:out>";
			
			opener.addProductId(rowId,values);
    		window.close();
		}   		
	</script>
	</head>
	<body>
		<div id="mainContainer">
			<div id="container">
				<html:form action="/market/productList.do">
					<html:hidden property="thisAction" />
					<html:hidden property="lastAction" />
					<html:hidden property="intPage" />
					<html:hidden property="pageCount" />

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
									<c:param name="title1" value="产品管理" />
									<c:param name="title2" value="产品列表" />
								</c:import>
								<div class="searchBar">
									<table cellpadding="0" cellspacing="0" border="0"
										class="searchPanel">
										<tr>
											<td>
												名称：
											</td>
											<td>
												<html:text property="name" styleClass="colorblue2 p_5"
													style="width:150px;" />
											</td>
											<td>
												类型：
											</td>
											<td>
												<html:select property="type" value="${productListForm.type}"
													styleClass="colorblue2 p_5" style="width:180px;">
													<html:option value="">-请选择-</html:option>
													<html:option value="1">默认</html:option>
												</html:select>
											</td>
										
											<td>
											<html:hidden property="rowId"></html:hidden>
												<input type="submit" name="button" id="button" value="提交"
													class="submit greenBtn" />
											</td>
										</tr>
									</table>
								</div>
								<table width="100%" cellpadding="0" cellspacing="0" border="0"
									class="dataList">
									<tr>
										<th width="60">
											<div>
												&nbsp;请选择
											</div>
										</th>
										<th width="35">
											<div>
												&nbsp;序号
											</div>
										</th>
										<th>
											<div>
												名称
											</div>
										</th>
										<th>
											<div>
												规格
											</div>
										</th>
										<th>
											<div>
												售价
											</div>
										</th>
										<th>
											<div>
												绩效权重
											</div>
										</th>
										<th>
											<div>
												剂型
											</div>
										</th>
										<th>
											<div>
												类型
											</div>
										</th>
										<th>
											<div>
												备注
											</div>
										</th>
										<th>
											<div>
												状态
											</div>
										</th>
									</tr>
									<c:forEach var="product" items="${productListForm.list}"
										varStatus="status">
										<tr>
											<td>
												<html:multibox property="selectedItems"
													value="${product.id}"></html:multibox>
											</td>
											<td>
												<c:out
													value="${status.count+(productListForm.intPage-1)*productListForm.perPageNum}" />
											</td>
											<td style="text-align: left">
												<c:out value="${product.no}" />
												|
												<a
													href="<%=path%>/market/productList.do?thisAction=view&id=<c:out value="${product.id}" />">
													<c:out value="${product.name}" /> </a>
											</td>
											<td style="text-align: right">
												<c:out value="${product.standard}" />
											</td>
											<td>
												<c:out value="${product.price}" />
											</td>
											<td>
												<c:out value="${product.proportionInfo}" />
											</td>
											<td>
												<c:out value="${product.classTypeInfo}" />
											</td>
											<td>
												<c:out value="${product.typeInfo}" />
											</td>
											<td>
												<c:out value="${product.memo}" />
											</td>
											<td>
												<c:out value="${product.statusInfo}" />
											</td>
										</tr>
									</c:forEach>
								</table>

								<table width="100%" style="margin-top: 5px;">
									<tr>
										<td>
											<input name="label" type="button" class="button1" value="确定"
													onclick="confirmProduct();">
										</td>
										<td align="right">
											<div>
												共有记录&nbsp;
												<c:out value="${productListForm.totalRowCount}"></c:out>
												&nbsp;条&nbsp;&nbsp;&nbsp;&nbsp; [
												<a href="JavaScript:turnToPage(document.forms[0],0)">首页</a>
												|
												<a href="JavaScript:turnToPage(document.forms[0],1)">上一页</a>
												|
												<a href="JavaScript:turnToPage(document.forms[0],2)">下一页</a>
												|
												<a href="JavaScript:turnToPage(document.forms[0],3)"> 末页</a>]
												页数:
												<c:out value="${productListForm.intPage}" />
												/
												<c:out value="${productListForm.pageCount}" />
												]
											</div>
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
</html>
