<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic"%>
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
		<c:import url="../page/importDWR.jsp"></c:import>
		<script type="text/javascript" src="<%=path%>/_js/prototype/common.js"></script>
		<script type="text/javascript" src="<%=path%>/_js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript" src="<%=path%>/_js/tsms/loadAccount.js"></script>
		<script type="text/javascript" src="<%=path%>/_js/base/FormUtil.js"></script>
		<script type="text/javascript"
			src="<%=path%>/_js/calendar/WdatePicker.js"></script>
		<script type="text/javascript">		
		function addAgentId(agentId){	
			//alert("agentId:"+agentId);		
			//document.forms[0].agentId.value=agentId;	
			if(agentId!=null){
				document.forms[0].agentId.value=agentId;	
				agentBiz.getAgentById(agentId,function(agent){
					if(agent!=null){
						document.forms[0].agentNo.value=agent.agentNo+"|"+agent.name;	
					}					
				});
			}
		}		
		
		function addProductId(rowId,productIdValue){		
			var rowObj=document.getElementById("productId"+rowId);		
			if(rowObj==null){
				alert("rowObj is null..rowId:"+rowId+"--productIdValue:"+productIdValue);
			}else{				
				rowObj.value=productIdValue;
				//alert("getProductById-"+productIdValue);			
				productBiz.getProductById(productIdValue,function(product){
					document.getElementById("productName"+rowId).value=product.name;
					document.getElementById("productPrice"+rowId).value=product.price;
					
					onChangeDetailAmount(rowId);
				});
			}
		}		
					
		function selectAgent(){
			openWindow(800,600,'../agent/agentList.do?thisAction=selectFinanceAgent');		
		}
		
		function addAgentSpeed(){
			openWindow(800,600,'../agent/agentList.do?thisAction=saveSpeed');		
		}
		
		function selectProduct(rowId){
			openWindow(800,600,'../market/productList.do?thisAction=selectFinanceProduct&rowId='+rowId);		
		}
		
		</script>
		<style>
.agentBoxDiv {
	width: 150px;
	height: 250 px;
	background-color: #f5f5f5;
	border: 1 px solid Silver;;
	overflow: auto;
	position: absolute;
	z-index: 3;
	top: 12%;
	right: 35%; /*IE*/
}

.productBoxDiv {
	width: 200px;
	height: 250 px;
	background-color: #f5f5f5;
	border: 1 px solid Silver;;
	overflow: auto;
	position: absolute;
	z-index: 3;
}
</style>
	</head>
	<body>
		<c:set var="title1" value="销售管理" />
		<c:set var="title2" value="主营业务" />
		<c:choose>
			<c:set var="title3" value="${financeOrder.tranTypeText}" />
		</c:choose>
		<div id="mainContainer">
			<div id="container">
				<html:form action="/finance/financeOrder.do">
					<html:hidden property="id" value="${financeOrder.id}"></html:hidden>
					<html:hidden property="thisAction" name="financeOrder" />
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
									<c:param name="title1" value="${title1}" />
									<c:param name="title2" value="${title2}" />
									<c:param name="title3" value="${title3}" />
								</c:import>
								<table width="100%" cellpadding="0" cellspacing="0" border="0"
									class="dataList">
									<tr>
										<td class="lef">
											入账日期
										</td>
										<td style="text-align: left">
											<html:text property="businessDate"
												value="${financeOrder.businessDate}"
												styleClass="colorblue2 p_5" style="width:200px;"
												onfocus="WdatePicker({startDate:'%y-%M-%D',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" />
										</td>
										<td class="lef">
											客户
										</td>
										<td style="text-align: left">
											<html:hidden property="agentId" name="financeOrder" />
											<html:text property="agentNo" name="financeOrder"
												styleClass="colorblue2 p_5" style="width:200px;"
												ondblclick="this.value='';" onkeyup="onChangeSelectAgent();" />

											<input name="label" type="button" class="button1"
												value="选择客户" onclick="selectAgent();">
											<input name="label" type="button" class="button1"
												value="快速建档" onclick="addAgentSpeed();">
											<div id="agentBox" class="agentBoxDiv" style="display: none">
												<table id="tableBlurAgent" cellpadding="0" cellspacing="0"
													border="0" class="dataList" width="80%">
												</table>
											</div>
										</td>
									</tr>
									<tr>
										<td class="lef">
											交易平台
										</td>
										<td style="text-align: left">
											<html:select property="platformId" name="financeOrder"
												styleClass="colorblue2 p_5" styleId="platform_Id"
												style="width:200px;">
												<c:forEach items="${inPlatformList}" var="platform">
													<html:option value="${platform.id}">
														<c:out value="${platform.showName}" />
													</html:option>
												</c:forEach>
											</html:select>
										</td>
										<td class="lef">
											收款账号
										</td>
										<td style="text-align: left">
											<html:select property="accountId" name="financeOrder"
												styleClass="colorblue2 p_5" styleId="account_Id"
												style="width:200px;">
												<c:forEach items="${inAccountList}" var="account">
													<html:option value="${account.id}">
														<c:out value="${account.showName}" />
													</html:option>
												</c:forEach>
											</html:select>
										</td>
									</tr>
									<tr>
										<td class="lef">
											分管部门
										</td>
										<td style="text-align: left">
											<html:select property="companyId" styleId="companyObj"
												styleClass="colorblue2 p_5" style="width:100px;">
												<html:option value="0">-请选择-</html:option>
											</html:select>
											<html:select property="tranType"
												value="${financeOrder.tranType}" name="financeOrder"
												styleClass="colorblue2 p_5" styleId="account_Id"
												style="width:100px;">
												<c:forEach items="${tranTypeList}" var="dataType">
													<html:option value="${dataType.no}">
														<c:out value="${dataType.name}" />
													</html:option>
												</c:forEach>
											</html:select>
										</td>
										<td class="lef">
											手续费
										</td>
										<td style="text-align: left">
											<html:text property="handlingCharge" name="financeOrder"
												styleClass="colorblue2 p_5" style="width:200px;" />
										</td>
									</tr>
									<tr>
										<td class="lef">
											说明
										</td>
										<td style="text-align: left">
											<html:text property="memo" styleClass="colorblue2 p_5"
												style="width:200px;" ondblclick="this.value=''"
												onkeypress="keypressCreateOrder();" />
										</td>
										<td class="lef">
											应收金额
										</td>
										<td style="text-align: left">
											<html:text property="totalAmount" name="financeOrder"
												styleClass="colorblue2 p_5" style="width:200px;" />
										</td>
									</tr>
									<tr>
										<td colspan="4">
											<html:hidden property="id" value="${financeOrder.id}"></html:hidden>
											<input name="label" type="button" class="button1"
												value="新增明细" onclick="addRow('table1');">
											<input name="label" id="submitCreateButton" type="button"
												class="button1" value="提  交" onclick="editCreditOrder();"
												onkeypress="keypressCreateOrder(event);">
										</td>
									</tr>
								</table>
								<table id="table1" cellpadding="0" cellspacing="0" border="0"
									class="dataList" width="80%">
								</table>
								<div id="productBox" class="productBoxDiv" style="display: none">
									<table id="tableBlurProduct" cellpadding="0" cellspacing="0"
										border="0" class="dataList" width="90%"></table>
								</div>
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
		<script type="text/javascript">		   
		window.onload=initOnload;
		
		function initOnload(){
			printCompanyList("companyObj");	
			addRow('table1');
		}   
		function editCreditOrder(){		
			var agentId=document.forms[0].agentId.value;
			if((agentId=="")||(agentId<1)){
		   		alert("客户不能为空");
		   		return false;
			} 

			var totalAmount = document.forms[0].totalAmount.value.trim();
			totalAmount=totalAmount.replace(/\，/g,""); //去除 ，
			if(!isNum(totalAmount)||totalAmount==""){
				alert("请正确填写金额!");
				return false;
		 	}
		 	 
			if(setSubmitButtonDisable('submitCreateButton')){   
				var thisAction =document.forms[0].thisAction.value;		
		    	document.forms[0].action="../finance/financeOrder.do?thisAction="+thisAction;
		    	trim(document.forms[0]);
            	document.forms[0].submit();   
         	 }             
		}  		
		
		function printCompanyList(selectObjId){	
			 var selectOptionValue="<c:out value='${financeOrder.companyId}' ></c:out>";
			 //alert("selectOptionValue:"+selectOptionValue);
			 companyBiz.getCompanyList(1,1,function(companyList){
			 	var selectObj = document.getElementById(selectObjId);
			 	 selectObj.options.length=0;
			 	 selectObj.options.add(new Option("-请选择-",""));
				for(var i=0;i<companyList.length;i++){
					//alert(companyList[i].id);
					if(selectOptionValue!=null&&selectOptionValue==companyList[i].id){
			          option = new Option(companyList[i].name,companyList[i].id,true,true);
			         // selectObj.disabled=true;//将导致companyId属性值无法提交?
			         }else{
			          option = new Option(companyList[i].name,companyList[i].id);
			        }			   		
		         	selectObj.options.add(option);    
		        }
			 });
		}		
		
		var  maxRow=11;   
		var rowHeadHtml="<tr><th><div>品名规格</div></th><th><div>单价</div></th>";
			rowHeadHtml+="<th><div>数量</div></th><th><div>金额</div></th>";
			rowHeadHtml+="<th><div>备注</div></th><th><div>操作</div></th></tr>";
			
		function addRow(tableId){
			var tableObj= $("#"+tableId);
			//alert(tableObj); 
			var rowHtml="";		
			rowHtml+="<tr id='row"+maxRow+"' >";
			rowHtml+="<td>";
			rowHtml+="<input type='hidden' name='productIds' id='productId"+maxRow+"' style='width:30px;' />";
			rowHtml+="<input type='text' name='productNames' id='productName"+maxRow+"' class='colorblue2 p_5'	style='width:150px;'";
			rowHtml+=" ondblclick='this.value='';' onkeyup='onChangeSelectProduct("+maxRow+",event);'  /><a href='#' onclick='selectProduct("+maxRow+")'>选择</a>";
			rowHtml+="</td>";
			rowHtml+="<td><input type='text' name='productPrices' id='productPrice"+maxRow+"' onChange='onChangeDetailAmount("+maxRow+");' class='colorblue2 p_5'	style='width:80px;' /></td>";
			rowHtml+="<td><input type='text' name='productCounts' id='productCount"+maxRow+"' value='1' onChange='onChangeDetailAmount("+maxRow+");'   class='colorblue2 p_5'	style='width:80px;' /></td>";
			rowHtml+="<td><input type='text' name='detailAmounts'  id='detailAmount"+maxRow+"' onChange='onChangeTotalAmount();'  class='colorblue2 p_5'	style='width:80px;' /></td>";
			rowHtml+="<td><input type='text' name='detailMemos' id='detailMemo"+maxRow+"' class='colorblue2 p_5'	style='width:150px;' /></td>";
			rowHtml+=" <td><a href='#'  onclick='delRow(this);'>删除</a></td>";			
			rowHtml+="</tr>";	
			if(maxRow==11){
				tableObj.append(rowHeadHtml);
				tableObj.append(rowHtml);
			}else{
				tableObj.append(rowHtml);
			}		

			//alert(rowHtml);	
											
	  		maxRow++;		  		
		}	  
		
		function onChangeDetailAmount(rowId){			
			var detailAmountValue=0;
			var count=document.getElementById("productCount"+rowId).value;
			var price=document.getElementById("productPrice"+rowId).value;
			
			detailAmountValue=(count*1)*(price*1)*1;
			//alert("detailAmountValue:"+detailAmountValue);
			document.getElementById("detailAmount"+rowId).value=detailAmountValue;
			
			onChangeTotalAmount();
		}
		
		function onChangeTotalAmount(){
			var totalAmountValue=0;
			//alert("totalAmountValue:"+totalAmountValue);
			var amountArray=document.getElementsByName("detailAmounts");
			if(amountArray!=null){
				var arrayLen=amountArray.length;
				if(arrayLen>0){					
					for(var i=0;i<arrayLen;i++){
						var tempAmount=amountArray[i].value;
						if(tempAmount==""){
							tempAmount="0";
						}	
						totalAmountValue+=1*tempAmount;					
					}
				}
			}
			//alert(totalAmountValue);
			document.forms[0].totalAmount.value=totalAmountValue;
		}
		
		//====================onChange Agent begin=========
		function onChangeSelectAgent(){
			$("#tableBlurAgent").empty();
			displayObj("agentBox","");		
			var agentNo=document.forms[0].agentNo.value;
			if(agentNo!=null){
				agentStore.getBlurAgentList(agentNo,function(blurAgentList){
					//alert("blurAgentList:"+blurAgentList);
					if(blurAgentList!=null){
						for(var i=0;i<blurAgentList.length;i++){
							var agent=blurAgentList[i];
							if(agent!=null){					
								var info="<a href='#' onclick='confirmSelectAgent("+agent.id+");'>"+agent.agentNo+"</a><br/>";
								info=info+agent.name+"|"+agent.typeInfo;
								addBlurAgentRow('tableBlurAgent',info,i);
							}							
						}						
					}				
				});
			}
		}
		
		function addBlurAgentRow(tableId,info,maxRow){
			var tableObj= $("#"+tableId);
			var rowHtml="";		
			rowHtml+="<tr id='row"+maxRow+"' >";
			rowHtml+="<td>"+info+"</td>";	
			rowHtml+="</tr>";
			tableObj.append(rowHtml);  		
		}
		
	function confirmSelectAgent(agentId){
		displayObj("agentBox","none");	
		addAgentId(agentId);	
	}
	
	//====================onChange Product begin=========
			function onChangeSelectProduct(maxRow,event){
			$("#tableBlurProduct").empty();
			drawProductDiv(event);
			var productNo=document.getElementById("productName"+maxRow).value;
			if(productNo!=null){
				productStore.getBlurProductList(productNo,function(blurProductList){
					//alert("blurProductList:"+blurProductList);
					if(blurProductList!=null){
						for(var i=0;i<blurProductList.length;i++){
							var product=blurProductList[i];
							if(product!=null){					
								var info="<a href='#' onclick='confirmSelectProduct("+maxRow+","+product.id+");'>"+product.no+"</a><br/>";
								info=info+product.name+"|"+product.typeInfo;
								addBlurProductRow('tableBlurProduct',info,i);
							}							
						}						
					}				
				});
			}
		}
		
		function drawProductDiv(event){
			var mX = event.x ? event.x : event.pageX; 
			var mY = event.y ? event.y : event.pageY; 			
		
			//alert("x,y:"+mX+"--"+mY);
			document.getElementById("productBox").style.left=mX;   
    		document.getElementById("productBox").style.top=mY+300;    
			displayObj("productBox","");	
			
		}
		
		function addBlurProductRow(tableId,info,maxRow){
			var tableObj= $("#"+tableId);
			var rowHtml="";		
			rowHtml+="<tr id='row"+maxRow+"' >";
			rowHtml+="<td>"+info+"</td>";	
			rowHtml+="</tr>";
			tableObj.append(rowHtml);  		
		}
		
	function confirmSelectProduct(rowId,productId){
		//alert(rowId+"---"+productId);
		displayObj("productBox","none");	
		addProductId(rowId,productId);	
	}
		</script>
	</body>
</html>