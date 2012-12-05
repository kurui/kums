<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<%@ taglib uri="/WEB-INF/tld/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<link href="../_css/reset.css" rel="stylesheet" type="text/css" />
		<link href="../_css/global.css" rel="stylesheet" type="text/css" />
		<script src="../_js/prototype/common.js" type="text/javascript"></script>
		<script type='text/javascript' src='<%=path%>/dwr/interface/platComAccountStore.js'></script>
		<script type='text/javascript' src='<%=path%>/dwr/engine.js'></script>
		<script type='text/javascript' src='<%=path%>/dwr/util.js'></script>
		<script type="text/javascript" src="../_js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript" src="../_js/tsms/loadAccount.js"></script>
		<script type="text/javascript" src="../_js/base/FormUtil.js"></script>
		<script type="text/javascript">		
		   $(function(){		
			  loadPlatList('platform_Id','company_Id','account_Id');
			 // loadPlatListByType('platform_Id','company_Id','account_Id','1');			  
			  var onfoucsObj=document.getElementById("platform_Id");
			  onfoucsObj.focus();
			});
	</script>
	</head>
	<body>
		<div id="mainContainer">
			<div id="container">
				<html:form action="/finance/financeOrder.do">
					<html:hidden property="forwardPage" value="addOrder" />
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
									<c:param name="title2" value="收支明细录入" />
								</c:import>
								<table>
									<tr>
										<td>
											平台
										</td>
										<td>
											<html:select property="platformId" styleClass="colorblue2 p_5" styleId="platform_Id" style="width:100px;"
												onchange="loadCompanyList('platform_Id','company_Id','account_Id')">
												<option value="">
													请选择
												</option>
											</html:select>
										</td>
										<td>
											公司
										</td>
										<td>
											<html:select property="companyId" styleClass="colorblue2 p_5" styleId="company_Id" style="width:150px;"
												onchange="loadAccount('platform_Id','company_Id','account_Id')">
												<option value="">
													请选择
												</option>
											</html:select>
										</td>
										<td>
											账户
										</td>
										<td>
											<html:select property="accountId" styleClass="colorblue2 p_5" styleId="account_Id" style="width:100px;">
												<option value="">
													请选择
												</option>
											</html:select>
										</td>
										<td>
											订单号
											<html:text property="airOrderNo" styleClass="colorblue2 p_5" style="width:150px;" onkeypress="keypressCreateOrder();" />
										</td>
										<td>
											金额
											<html:text property="totalAmount" styleClass="colorblue2 p_5" style="width:100px;" onkeypress="keypressCreateOrder();" />
										</td>
										<td>
											<input name="label" id="submitCreateButton" type="button" class="button1" value="创 建" onclick="createOrder();"
												onkeypress="keypressCreateOrder(event);">
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
		<script type="text/javascript">		      
//是否只包含数字
function isNum(b){
	var re=/^([1-9][0-9]*|0)(\.[0-9]{0,2})?$/;
	return(re.test(b));
}
				
function createOrder(){
		var airOrderNo = document.forms[0].airOrderNo.value;
		         
		var totalAmount = document.forms[0].totalAmount.value.trim();
		totalAmount=totalAmount.replace(/\，/g,""); //去除 ，
		
		var account_IdVal=$('#account_Id').val();
		if(account_IdVal==""){
		   alert("账户不能为空！请确认或联系管理员添加");
		   return false;
		} 
		        
		if(airOrderNo==""){
		    alert("请正确填写订单号!");
		    return false;
		 }
		 if(!isNum(totalAmount)||totalAmount==""){
			alert("请正确填写金额!");
			return false;
		 } 
		if(setSubmitButtonDisable('submitCreateButton')){   
		    document.forms[0].action="airticketOrder.do?thisAction=createOrder";
		    trim(document.forms[0]);
            document.forms[0].submit();   
          }             
}
		      
		      function keypressCreateOrder(e){
				var isie = (document.all) ? true : false;//判断是IE内核还是Mozilla  
				var key;  
				if (isie)  {
				  key = window.event.keyCode;
				}else{
				    key = e.which;	   
				}
				if(key==13){
					createOrder();
				} 	
			}
		</script>
	</body>
</html>
