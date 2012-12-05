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
		<c:import url="../page/importDWR.jsp"></c:import>
		<script type="text/javascript" src="<%=path%>/_js/prototype/common.js"></script>
		<script type="text/javascript" src="<%=path%>/_js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript" src="<%=path%>/_js/tsms/loadAccount.js"></script>
		<script type="text/javascript" src="<%=path%>/_js/base/FormUtil.js"></script>
		<script type="text/javascript" src="<%=path%>/_js/base/select.js"></script>


		<script type="text/javascript"
			src="<%=path%>/_js/calendar/WdatePicker.js"></script>
	</head>

	<script type="text/javascript">	
		function add()		{
			var thisAction =document.forms[0].thisAction.value;		
			//alert("thisAction:"+thisAction);	   
		    document.forms[0].action="<%=path%>/market/priceIndex.do?thisAction="+thisAction;
		    document.forms[0].submit();
		}		
	</script>
	<body>
		<c:import url="../page/mainTitle.jsp" charEncoding="UTF-8">
			<c:param name="title1" value="金融服务事业部" />
			<c:param name="title2" value="行情中心" />
			<c:param name="title3" value="编辑价格系数" />
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
									<c:forEach var="priceIndex" items="${priceIndexList}"
										varStatus="status">
										<tr>
											<td>
												<html:hidden property="priceIndexIds"
													value="${priceIndex.id}"></html:hidden>
												<html:select property="priceReferenceIds"
													styleId="priceReferenceSelectObj"
													value="${priceIndex.priceReference.id}" name="priceIndex"
													styleClass="colorblue2 p_5" style="width:100px;">
													<c:forEach items="${priceReferenceList}"
														var="priceReference">
														<html:option value="${priceReference.id}">
															<c:out value="${priceReference.name}" />
														</html:option>
													</c:forEach>
												</html:select>
											</td>
											<td>
												<html:select property="gatherCompanyIds"
													styleId="gatherCompanySelectObj"
													value="${priceIndex.gatherCompany.id}" name="priceIndex"
													styleClass="colorblue2 p_5" style="width:100px;">
													<html:option value="0">-采集地-</html:option>
													<c:forEach items="${gatherCompanyList}" var="company">
														<html:option value="${company.id}">
															<c:out value="${company.shortName}" />
														</html:option>
													</c:forEach>
												</html:select>
											</td>
											<td>
												价格
												<html:text property="prices" name="priceIndex"
													value="${priceIndex.price}" styleClass="colorblue2 p_5"
													style="width:50px;"></html:text>
											</td>
											<td>
												最高
												<html:text property="maxPrices" name="priceIndex"
													value="${priceIndex.maxPrice}" styleClass="colorblue2 p_5"
													style="width:50px;"></html:text>
											</td>
											<td>
												最低
												<html:text property="minPrices" name="priceIndex"
													value="${priceIndex.minPrice}" styleClass="colorblue2 p_5"
													style="width:50px;"></html:text>
											</td>
											<td>
												涨跌
												<html:text property="ranges" name="priceIndex"
													value="${priceIndex.range}" styleClass="colorblue2 p_5"
													style="width:50px;"></html:text>
											</td>
											<td>
												<html:text property="snatchTimes" name="priceIndex"
													value="${priceIndex.snatchTime}"
													styleClass="colorblue2 p_5" style="width:120px;"
													onfocus="WdatePicker({startDate:'%y-%M-%D 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})"
													readonly="true"></html:text>
											</td>
											<td>
												说明
												<html:text property="memos" name="priceIndex"
													value="${priceIndex.memo}" styleClass="colorblue2 p_5"
													style="width:80px;"></html:text>
											</td>
											<td>
											</td>
										</tr>
									</c:forEach>
									<tr>
										<td>
											<a href="#"
												onclick="setSameSelectValue('priceReferenceSelectObj');">同一参照物</a>
										</td>
										<td>

											<a href="#"
												onclick="setSameSelectValue('gatherCompanySelectObj');">同一采集地</a>
										</td>
										<td colspan="4">
										</td>
										<td>
											<a href="#" onclick="setSameValue('snatchTimes');">同一天</a>
										</td>
										<td colspan="2">
										</td>
									</tr>
									<tbody id="table1">

									</tbody>
								</table>
								<table width="100%" style="margin-top: 5px;">
									<tr>
										<td align="center">
											<html:hidden property="thisAction" value="${thisAction}"
												name="priceIndex" />

											<input name="label" type="button" class="button1" value="返 回"
												onclick="window.history.back();">
											<input name="label" type="button" class="button1"
												value="新增明细" onclick="addRow('table1');">
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
		<script type="text/javascript">
		
			var  maxRow=11;   
			var rowHeadHtml="";
		function addRow(tableId){
			var tableObj= $("#"+tableId);
			//alert(tableObj); 
			var rowHtml="<tr id='row"+maxRow+"' >";
			rowHtml+="<td><input type='hidden' name='priceIndexIds' class='colorblue2 p_5'/>";
			rowHtml+="<select id='priceReferenceSelectObj"+maxRow+"' name='priceReferenceIds' class='colorblue2 p_5'	style='width:100px;' >";
			rowHtml+="<option value='0' >-参照物-</option>";		
			rowHtml+="</select></td>";		
				rowHtml+="<td><select id='gatherCompanySelectObj"+maxRow+"' name='gatherCompanyIds' class='colorblue2 p_5'	style='width:100px;' >";
			//rowHtml+="<option value='0' >采集地</option>";		
			rowHtml+="</select></td>";		
			rowHtml+="<td>价格<input type='text' name='prices' value='0' class='colorblue2 p_5'	style='width:50px;' /></td>";
			rowHtml+="<td>最高<input type='text' name='maxPrices' value='0' class='colorblue2 p_5'	style='width:50px;' /></td>";
			rowHtml+="<td>最低<input type='text' name='minPrices' value='0' class='colorblue2 p_5'	style='width:50px;' /></td>";
			rowHtml+="<td>涨跌<input type='text' name='ranges' value='0' class='colorblue2 p_5'	style='width:50px;' /></td>";
			rowHtml+="<td><input type='text' name='snatchTimes' class='colorblue2 p_5'	style='width:120px;' onfocus=\"WdatePicker({startDate:'%y-%M-%D',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})\" /></td>";
			rowHtml+="<td>说明<input type='text' name='memos' class='colorblue2 p_5'	style='width:80px;' /></td>";
			rowHtml+=" <td><a href='#'  onclick='delRow(this);'>删除</a></td>";			
			rowHtml+="</tr>";		
				
			//alert(rowHtml);
			printPriceReferenceList('priceReferenceSelectObj'+maxRow);
			printGatherCompanyList('gatherCompanySelectObj'+maxRow);			
			
			if(maxRow==11){
				tableObj.append(rowHeadHtml);
				tableObj.append(rowHtml);
			}else{
				tableObj.append(rowHtml);
			}		
	  		maxRow++;		
	  		
	  		setSameValue("snatchTimes");  		
		}	
		
		function printPriceReferenceList(selectObjId){
			 priceReferenceBiz.getPriceReferenceListByType("<c:out value='${priceReferenceTypes}' />",function(priceReferenceList){
			 	var selectObj = document.getElementById(selectObjId);
			 	selectObj.options.length=0;
				for(var i=0;i<priceReferenceList.length;i++){		
		   		 	option = new Option(priceReferenceList[i].name,priceReferenceList[i].id);
		         	selectObj.options.add(option);
		        }
			 });
		}
		
		function printGatherCompanyList(selectObjId){
			    companyBiz.getCompanyListByProvideChain("5100",function(gatherCompanyList){
			 	var selectObj = document.getElementById(selectObjId);
			 	selectObj.options.length=0;
			 	option = new Option("采集地","0");
		        selectObj.options.add(option);
				for(var i=0;i<gatherCompanyList.length;i++){		
		   		 	option = new Option(gatherCompanyList[i].shortName,gatherCompanyList[i].id);
		         	selectObj.options.add(option);
		        }
			 });
		}
		
		
		function setSameSelectValue(selectObjId){
			var selectedValues=js.select.getSelectedValues(selectObjId);
			//alert("---"+selectedValues);			
		
			for(var i=11;i<maxRow;i++){
				//alert("selectObjId+i:"+selectObjId+i);
				js.select.markSelected(selectObjId+i,selectedValues);
			}
		}
		</script>
	</body>
</html>