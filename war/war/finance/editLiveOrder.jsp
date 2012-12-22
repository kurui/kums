<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>

<%
	String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<link href="<%=path%>/_css/reset.css" rel="stylesheet" type="text/css" />
		<link href="<%=path%>/_css/global.css" rel="stylesheet" type="text/css" />
		<c:import url="../page/importDWR.jsp"></c:import>
		<script type="text/javascript" src="<%=path%>/_js/prototype/common.js"></script>
		<script type="text/javascript" src="<%=path%>/_js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript" src="<%=path%>/_js/tsms/loadAccount.js"></script>
		<script type="text/javascript" src="<%=path%>/_js/base/FormUtil.js"></script>
		<script type="text/javascript" src="<%=path%>/_js/base/select.js"></script>
		<script type="text/javascript" src="<%=path%>/_js/calendar/WdatePicker.js"></script>
		<style>
.cussentCompanyBoxDiv {
	width: 200px;
	height: 250 px;
	background-color: #f5f5f5;
	border: 1 px solid Silver;;
	overflow: auto;
	position: absolute;
	z-index: 3;
}

.cussentProductBoxDiv {
	width: 200px;
	height: 250 px;
	background-color: #f5f5f5;
	border: 1 px solid Silver;;
	overflow: auto;
	position: absolute;
	z-index: 3;
}
</style>
<script type="text/javascript">
	function addCussentCompanyId(rowId,cussentCompanyIdValue){		
			var rowObj=document.getElementById("cussentCompanyId"+rowId);	
			if(rowObj==null){
				alert("rowObj is null..rowId:"+rowId+"--cussentCompanyIdValue:"+cussentCompanyIdValue);
			}else{				
				rowObj.value=cussentCompanyIdValue;
				//alert("getCompanyById-"+cussentCompanyIdValue);			
					companyBiz.getCompanyById(cussentCompanyIdValue,function(cussentCompany){
						document.getElementById("cussentCompanyName"+rowId).value=cussentCompany.name;	
						document.getElementById("cussentCompanyId"+rowId).value=cussentCompany.id;					
				});
			}
		}	
	function addCussentProductId(rowId,cussentProductIdValue){		
			var rowObj=document.getElementById("cussentProductId"+rowId);	
			if(rowObj==null){
				alert("rowObj is null..rowId:"+rowId+"--cussentProductIdValue:"+cussentProductIdValue);
			}else{				
				rowObj.value=cussentProductIdValue;
				//alert("getProductById-"+cussentProductIdValue);			
					productBiz.getProductById(cussentProductIdValue,function(cussentProduct){
						document.getElementById("cussentProductName"+rowId).value=cussentProduct.name;	
						document.getElementById("cussentProductId"+rowId).value=cussentProduct.id;					
				});
			}
		}	
</script>
	</head>

	<body>
		<div id="mainContainer">
			<div id="container">
				<html:form action="/finance/financeOrder.do">
					<html:hidden property="thisAction" name="financeOrder"></html:hidden>
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
									<c:param name="title1" value="财务管理" />
									<c:param name="title2" value="出纳管理" />
									<c:param name="title3" value="管理费用入账" />
								</c:import>

								<table id="table1" cellpadding="0" cellspacing="0" border="0" class="dataList" width="100%">
								</table>
								<table width="100%">
									<tr>
										<td colspan="2">
											合计:
											<span id='thisTotalAmount' style='color: Red;'></span>
										</td>
										<td>
										</td>
									<tr>
										<td width="10"></td>
									</tr>
									<tr>
										<td>
											<a href="#" onclick="setSameValue('cussentCompanyIds');setSameValue('cussentCompanyNames');">同一单位</a>
										</td>
										<td>
											<input name="label" type="button" class="button1" value="新增明细" onclick="addRow('table1');">
										</td>
										<td>
											<a href="#" onclick="setSameSelectValue('tranTypeSelectObj');">同一科目</a>
										</td>
										<td>
											<input type="text" name="outOrderNos" onclick="this.value='';" onmouseout="if(this.value==''){this.value='账单号'}" class="colorblue2 p_5"	style="width:150px;" value="账单号" />
			<input name="label" type="button" id="addLiveOrderButton" class="button1" value="保 存" onclick="addLiveOrder();" onkeypress="keypressAddLiveOrder(event);">
											
										</td>
										<td>
											<input name="label" type="button" class="button1" value="新增明细" onclick="addRow('table1');">											
										</td>
										<td>
											<a href="#" onclick="setSameValue('businessDates');">同一天</a>
										</td>
									</tr>
									<tr>
										<td width="10" class="tbrr"></td>
									</tr>
								</table>
								<div id="cussentCompanyBox" class="cussentCompanyBoxDiv" style="display: none">
									<table id="tableBlurCussentCompany" cellpadding="0" cellspacing="0" border="0" class="dataList" width="90%"></table>
								</div>
								<div id="cussentProductBox" class="cussentProductBoxDiv" style="display: none">
									<table id="tableBlurCussentProduct" cellpadding="0" cellspacing="0" border="0" class="dataList" width="90%"></table>
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
			addRow('table1');
		}
		
		function addLiveOrder(){ 
				var totalAmounts=$("input[name='totalAmount']"); 
                if(checkNumArray(totalAmounts,"请正确填写金额!")==false){
                    return false;
              	}  
				if(setSubmitButtonDisable('addLiveOrderButton')){   
					var thisAction =document.forms[0].thisAction.value;	
				    document.forms[0].action="financeOrder.do?thisAction="+thisAction;
				    trim(document.forms[0]);
		            document.forms[0].submit();   
		          }             
		}
		
		var  maxRow=11;   
		var rowHeadHtml="<tr><th><div>往来单位</div></th><th><div>产品</div></th><th><div>科目</div></th>";
			rowHeadHtml+="<th><div>金额</div></th><th><div>备注</div></th>";
			rowHeadHtml+="<th><div>入账日期</div></th><th><div>操作</div></th></tr>";
		function addRow(tableId){
			var tableObj= $("#"+tableId);
			//alert(tableObj); 
			var rowHtml="<tr id='row"+maxRow+"' >";
				rowHtml+="<td>";
			rowHtml+="<input type='hidden' name='cussentCompanyIds' id='cussentCompanyId"+maxRow+"' style='width:30px;' />";
			rowHtml+="<input type='text' name='cussentCompanyNames' id='cussentCompanyName"+maxRow+"' class='colorblue2 p_5'	style='width:150px;'";
			rowHtml+=" ondblclick='this.value='';' onkeyup='onChangeSelectCussentCompany("+maxRow+",event);'  /><a href='#' onclick='selectCussentCompany("+maxRow+")'>选择</a>";
			rowHtml+="</td>";
				rowHtml+="<td>";
			rowHtml+="<input type='hidden' name='cussentProductIds' id='cussentProductId"+maxRow+"' style='width:30px;' />";
			rowHtml+="<input type='text' name='cussentProductNames' id='cussentProductName"+maxRow+"' value='考虑如何与OrderDetail融合' class='colorblue2 p_5'	style='width:150px;'";
			rowHtml+=" ondblclick='this.value='';' onkeyup='onChangeSelectCussentProduct("+maxRow+",event);'  /><a href='#' onclick='selectCussentProduct("+maxRow+")'>选择</a>";
			rowHtml+="</td>";
			
			rowHtml+="<td><select id='tranTypeSelectObj"+maxRow+"' name='tranTypes' class='colorblue2 p_5'	style='width:100px;' >";
			rowHtml+="<option value='0' >-请选择-</option>";		
			rowHtml+="</select></td>";			
			rowHtml+="<td><input type='text' name='totalAmounts' onchange='onChangeTotalAmount();' class='colorblue2 p_5'	style='width:80px;' /></td>";
			rowHtml+="<td><input type='text' name='memos' class='colorblue2 p_5'	style='width:150px;' /></td>";
			rowHtml+="<td><input type='text' name='businessDates' value='<c:out value='${financeOrder.businessDate}' />' class='colorblue2 p_5'	style='width:120px;' onfocus=\"WdatePicker({startDate:'%y-%M-%D',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})\" /></td>";
			rowHtml+=" <td><a href='#'  onclick='delRow(this);'>删除</a></td>";			
			rowHtml+="</tr>";			
				
			//alert(rowHtml);
			printTranTypeList('tranTypeSelectObj'+maxRow);
			
			if(maxRow==11){
				tableObj.append(rowHeadHtml);
				tableObj.append(rowHtml);
			}else{
				tableObj.append(rowHtml);
			}		
	  		maxRow++;		  		
		}	  
		
		function printTranTypeList(selectObjId){
			 dataTypeBiz.getSubDataTypeList(1201,function(dataTypeList){
			 	var selectObj = document.getElementById(selectObjId);
			 	selectObj.options.length=0;
				for(var i=0;i<dataTypeList.length;i++){		
		   		 	option = new Option(dataTypeList[i].showName,dataTypeList[i].no);
		         	selectObj.options.add(option);
		        }
			 });
		}
		
		function onChangeTotalAmount(){
			var totalAmountValue=0;
			var amountArray=document.getElementsByName("totalAmounts");
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
			document.getElementById("thisTotalAmount").innerHTML=totalAmountValue;
		}
		
		function setSameSelectValue(selectObjId){
			var selectedValues=js.select.getSelectedValues(selectObjId+"11");
			//alert("---"+selectedValues);			
		
			for(var i=11;i<maxRow;i++){
				//alert("selectObjId+i:"+selectObjId+i);
				js.select.markSelected(selectObjId+i,selectedValues);
			}
		}
		
		//====================onChange CussentCompany begin=========
		function onChangeSelectCussentCompany(maxRow,event){			
			$("#tableBlurCussentCompany").empty();
			drawCussentCompanyDiv(event);
			var cussentCompanyNo=document.getElementById("cussentCompanyName"+maxRow).value;
			if(cussentCompanyNo!=null){
			//alert("cussentCompanyNo:"+cussentCompanyNo);
				platComAccountStore.getBlurCompanyList(cussentCompanyNo,function(blurCussentCompanyList){
					//alert("blurCussentCompanyList:"+blurCussentCompanyList);
					if(blurCussentCompanyList!=null&&blurCussentCompanyList.length>0){
						for(var i=0;i<blurCussentCompanyList.length;i++){
							var cussentCompany=blurCussentCompanyList[i];
							if(cussentCompany!=null){					
								var info="<a href='#' onclick='confirmSelectCussentCompany("+maxRow+","+cussentCompany.id+");'>";
								info=info+cussentCompany.shortName+"</a><br/>";								
								//alert(info);
								addBlurCussentCompanyRow('tableBlurCussentCompany',info,i);
							}							
						}						
					}else{
							displayObj("cussentCompanyBox","none");	
					}				
				});
			}else{
				alert("cussentCompanyNo is null");
			}
		}
		
		function drawCussentCompanyDiv(event){
			var mX = event.x ? event.x : event.pageX; 
			var mY = event.y ? event.y : event.pageY; 			
		
			//alert("x,y:"+mX+"--"+mY);
			
			document.getElementById("cussentCompanyBox").style.left=mX-25;   
    		document.getElementById("cussentCompanyBox").style.top=mY+30;    
			displayObj("cussentCompanyBox","");	
			
		}
		
		function addBlurCussentCompanyRow(tableId,info,maxRow){
			var tableObj= $("#"+tableId);
			var rowHtml="";		
			rowHtml+="<tr id='row"+maxRow+"' >";
			rowHtml+="<td>"+info+"</td>";	
			rowHtml+="</tr>";
			tableObj.append(rowHtml);  		
		}
		
	function confirmSelectCussentCompany(rowId,cussentCompanyId){
		//alert(rowId+"---"+cussentCompanyId);
		displayObj("cussentCompanyBox","none");	
		addCussentCompanyId(rowId,cussentCompanyId);	
	}
	
	
	//====================onChange CussentProduct begin=========
		function onChangeSelectCussentProduct(maxRow,event){			
			$("#tableBlurCussentProduct").empty();
			drawCussentProductDiv(event);
			var cussentProductNo=document.getElementById("cussentProductName"+maxRow).value;
			if(cussentProductNo!=null){
			//alert("cussentProductNo:"+cussentProductNo);
				productStore.getBlurProductList(cussentProductNo,function(blurCussentProductList){
					//alert("blurCussentProductList:"+blurCussentProductList);
					if(blurCussentProductList!=null&&blurCussentProductList.length>0){
						for(var i=0;i<blurCussentProductList.length;i++){
							var cussentProduct=blurCussentProductList[i];
							if(cussentProduct!=null){					
								var info="<a href='#' onclick='confirmSelectCussentProduct("+maxRow+","+cussentProduct.id+");'>";
								info=info+cussentProduct.name+"</a><br/>";								
								//alert(info);
								addBlurCussentProductRow('tableBlurCussentProduct',info,i);
							}							
						}						
					}else{
							displayObj("cussentProductBox","none");	
					}				
				});
			}else{
				alert("cussentProductNo is null");
			}
		}
		
		function drawCussentProductDiv(event){
			var mX = event.x ? event.x : event.pageX; 
			var mY = event.y ? event.y : event.pageY; 			
		
			//alert("x,y:"+mX+"--"+mY);
			
			document.getElementById("cussentProductBox").style.left=mX-25;   
    		document.getElementById("cussentProductBox").style.top=mY+30;    
			displayObj("cussentProductBox","");	
			
		}
		
		function addBlurCussentProductRow(tableId,info,maxRow){
			var tableObj= $("#"+tableId);
			var rowHtml="";		
			rowHtml+="<tr id='row"+maxRow+"' >";
			rowHtml+="<td>"+info+"</td>";	
			rowHtml+="</tr>";
			tableObj.append(rowHtml);  		
		}
		
	function confirmSelectCussentProduct(rowId,cussentProductId){
		//alert(rowId+"---"+cussentProductId);
		displayObj("cussentProductBox","none");	
		addCussentProductId(rowId,cussentProductId);	
	}
	
	</script>
	</body>
</html>
