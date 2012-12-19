<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/fmt.tld" prefix="fmt"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>管理费用</title>
<link href="<%=path%>/_css/reset.css" rel="stylesheet" type="text/css" />
<link href="<%=path%>/_css/global.css" rel="stylesheet" type="text/css" />
<c:import url="../page/importDWR.jsp"></c:import>
<script type="text/javascript" src="<%=path%>/_js/prototype/common.js"></script>
<script type="text/javascript" src="<%=path%>/_js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="<%=path%>/_js/base/FormUtil.js"></script>
<script type="text/javascript"
	src="<%=path%>/_js/calendar/WdatePicker.js"></script>
<style type="text/css">
.dataList th {
	padding: 0;
	margin: 0;
	text-align: center;
}

.dataList td {
	text-align: center;
	border: 1px solid #dedfe1;
	font-size: 12px;
	empty-cells: show;
	padding: 0;
	margin: 0;
	line-height: 22px;
}

.dataList .operationArea a {
	color: #005c9c;
	text-decoration: underline;
}

.wordWrap {
	word-wrap: break-word;
	word-break: break-all;
	overflow: hidden;
	-moz-binding: url('../_css/wordwrap.xml#wordwrap');
}
</style>
<script>
	function confirmFinance(){
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
					
			if(values.indexOf(",", values.length-1)>1){				
				values=values.substring(0,values.length-1);
			}			
			
			openWindow(800,600,'../finance/assetsItem.do?thisAction=insertAsFinance&financeOrderIdGroup='+values);
			
    		window.close();
		}   
</script>
</head>
<body>
	<div id="mainContainer">
		<div id="container">
			<html:form action="/finance/financeOrderList.do">
				<html:hidden property="thisAction" />
				<html:hidden property="lastAction" />
				<html:hidden property="intPage" />
				<html:hidden property="pageCount" />
				<html:hidden property="tranTypes" />
				
				<table width="100%" cellpadding="0" cellspacing="0">
					<tr>
						<td width="10" height="10" class="tblt"></td>
						<td height="10" class="tbtt"></td>
						<td width="10" height="10" class="tbrt"></td>
					</tr>
					<tr>
						<td width="10" class="tbll"></td>
						<td valign="top" class="body"><c:import
								url="../page/mainTitle.jsp" charEncoding="UTF-8">
								<c:param name="title1" value="财务管理" />
								<c:param name="title2" value="账单管理" />
								<c:param name="title3" value="管理费用列表" />
							</c:import>
							<hr>
							<div id="searchBarObj" class="searchBar">
								<table id="searchBarObj" cellpadding="0" cellspacing="0"
									border="0" class="searchPanel" style="width: 100%;">
									<tr>
										<td>开始 <html:text property="startDate"
												styleClass="colorblue2 p_5" style="width:120px;"
												onfocus="WdatePicker({startDate:'%y-%M-%D 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})"
												readonly="true" /> 结束 <html:text property="endDate"
												styleClass="colorblue2 p_5" style="width:120px;"
												onfocus="WdatePicker({startDate:'%y-%M-%D 23:59:59',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})"
												readonly="true" />
										</td>
										<td>排序 <html:select property="orderBy"
												styleClass="colorblue2 p_5" style="width:80px;">
												<html:option value="businessTime">业务时间</html:option>
												<html:option value="updateTime">修改时间</html:option>
											</html:select>
										</td>
									</tr>
								</table>
							</div>

							<table id="myTable" cellpadding="0" cellspacing="0" border="0"
								class="dataList" width="99%">
								<tr>
									<th>
										<div
											style="height: 100%; width: 100%; vertical-align: center; padding-top: 7px;">
											<input type="checkbox"
												onclick="checkAll(this, 'selectedItems')" name="sele" /> 全选
										</div>
									</th>
									<th>
										<div>日期</div>
									</th>
									<th>
										<div>类型</div>
									</th>
									<th>
										<div>金额</div>
									</th>
									<th>
										<div>往来单位</div>
									</th>
									<th>
										<div>说明</div>
									</th>
									<th>
										<div>状态</div>
									</th>
									<th>
										<div>负责人</div>
									</th>

									<th>
										<div>流水号</div>
									</th>
								</tr>
								<c:forEach var="financeOrder" items="${ulf.list}"
									varStatus="status">
									<tr>
										<td><html:multibox property="selectedItems"
												value="${financeOrder.id}" onclick="checkItem(this, 'sele')">
											</html:multibox></td>
										<td>
											<div>
												<c:out value="${financeOrder.businessDate}" />
											</div>
										</td>

										<td>
											<div>
												<c:out value="${financeOrder.tranTypeText}" />
											</div>
										</td>
										<td>
											<div>
												<c:out value="${financeOrder.totalAmount}" />
											</div>
										</td>
										<td>
											<div>
												<a
													href="<%=path%>/library/companyList.do?thisAction=view&id=<c:out value="${financeOrder.cussentCompany.id}" />">
													<c:out value="${financeOrder.cussentCompany.shortName}" />
												</a>
											</div>
										</td>
										<td style="text-align: left">
											<div>
												<c:out value="${financeOrder.memo}" />
											</div>
										</td>
										<td>
											<div>
												<c:out value="${financeOrder.statusText}" escapeXml="false" />
											</div>
										</td>
										<td>
											<div>
												<c:out value="${financeOrder.showEntryOperatorName}" />
											</div>
										</td>


										<td>
											<div>
												<a
													href="<%=path%>/finance/financeOrderList.do?thisAction=view&id=<c:out value='${financeOrder.id}' />"><c:out
														value="${financeOrder.orderNo}" /> </a>
											</div>
										</td>
									</tr>
								</c:forEach>
							</table>
							<table width="100%" style="margin-top: 5px;">
								<tr>
									<td><input name="label" type="button" class="button1"
										value="确定" onclick="confirmFinance();"></td>
									<td align="right">
										<div>
											共有明细&nbsp;
											<c:out value="${ulf.totalRowCount}"></c:out>
											&nbsp;条&nbsp;&nbsp;&nbsp;&nbsp; [ <a
												href="JavaScript:turnToPage(document.forms[0],0)">首页</a> | <a
												href="JavaScript:turnToPage(document.forms[0],1)">上一页</a> |
											<a href="JavaScript:turnToPage(document.forms[0],2)">下一页</a>
											| <a href="JavaScript:turnToPage(document.forms[0],3)">
												末页</a>] 页数:
											<c:out value="${ulf.intPage}" />
											/
											<c:out value="${ulf.pageCount}" />
											] 第 <input type="text" name="myIntPage"
												value="<c:out value='${ulf.intPage}'/>" style="width: 20px;"
												onkeyup="goMyIntPage(this.form)"> 页
										</div>
									</td>
								</tr>
							</table>
							<div class="clear"></div></td>
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
