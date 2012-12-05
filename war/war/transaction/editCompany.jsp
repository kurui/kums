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
		<link href="<%=path%>/_css/reset.css" rel="stylesheet" type="text/css" />
		<link href="<%=path%>/_css/global.css" rel="stylesheet"
			type="text/css" />
		<script src="../_js/prototype/common.js" type="text/javascript"></script>
		<script type="text/javascript"
			src="<%=path%>/_js/calendar/WdatePicker.js"></script>
		<script src="../_js/base/FormUtil.js" type="text/javascript"></script>
	</head>
	<script type="text/javascript">
		function add(){	
			var name=document.forms[0].name.value;
			if(name==""){
				alert("请输入公司名称!")
				return false;
			}
			var thisAction =document.forms[0].thisAction.value;			   
		    document.forms[0].action="<%=path%>/transaction/company.do?thisAction="+thisAction;
		   document.forms[0].submit();
		}	
	</script>
	<body>
		<c:import url="../page/importDWR.jsp"></c:import>
		<c:import url="../page/mainTitle.jsp" charEncoding="UTF-8">
			<c:param name="title1" value="机构管理" />
			<c:param name="title2" value="编辑公司" />
		</c:import>
		<html:form action="/transaction/company.do" method="post">
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
									<tr>
										<td class="lef">
											名称
										</td>
										<td style="text-align: left">
											<html:text property="name" name="company"
												value="${company.name}" styleClass="colorblue2 p_5"
												style="width:200px;"></html:text>
											简称
											<html:text property="shortName" name="company"
												value="${company.shortName}" styleClass="colorblue2 p_5"
												style="width:80px;"></html:text>
										</td>
										<td class="lef">
											注册类型
										</td>
										<td style="text-align: left">
											<html:text property="registerType" name="company"
												value="${company.registerType}" styleClass="colorblue2 p_5"
												style="width:200px;"></html:text>
										</td>
									</tr>
									<tr>
										<td class="lef">
											法人代表(授权控制人)
										</td>
										<td style="text-align: left">
											<html:text property="deputy" name="company"
												value="${company.deputy}" styleClass="colorblue2 p_5"
												style="width:200px;"></html:text>
										</td>
										<td class="lef">
											电话/传真
										</td>
										<td style="text-align: left">
											<html:text property="telephone" name="company"
												value="${company.telephone}" styleClass="colorblue2 p_5"
												style="width:200px;"></html:text>
										</td>
									</tr>
									<tr>
										<td class="lef">
											注册资本
										</td>
										<td style="text-align: left">
											<html:text property="registerCapital" name="company"
												value="${company.registerCapital}"
												styleClass="colorblue2 p_5" style="width:200px;"></html:text>
										</td>
										<td class="lef">
											净资产
										</td>
										<td style="text-align: left">
											<html:text property="netAssetValue" name="company"
												value="${company.netAssetValue}" styleClass="colorblue2 p_5"
												style="width:200px;"></html:text>
										</td>
									</tr>
									<tr>
										<td class="lef">
											注册地址
										</td>
										<td style="text-align: left">
											<html:text property="registerAddress" name="company"
												value="${company.registerAddress}"
												styleClass="colorblue2 p_5" style="width:200px;"></html:text>
										</td>
										<td class="lef">
											经营实体地址
										</td>
										<td style="text-align: left">
											<html:text property="entityAddress" name="company"
												value="${company.entityAddress}" styleClass="colorblue2 p_5"
												style="width:200px;"></html:text>
										</td>
									</tr>
									<tr>
										<td class="lef">
											工商登记号
										</td>
										<td style="text-align: left">
											<html:text property="registerCode" name="company"
												value="${company.registerCode}" styleClass="colorblue2 p_5"
												style="width:200px;"></html:text>
										</td>
										<td class="lef">
											税务登记号
										</td>
										<td style="text-align: left">
											<html:text property="revenueCode" name="company"
												value="${company.revenueCode}" styleClass="colorblue2 p_5"
												style="width:200px;"></html:text>
										</td>
									</tr>
									<tr>
										<td class="lef">
											法人代码证号
										</td>
										<td style="text-align: left">
											<html:text property="corporationCode" name="company"
												value="${company.corporationCode}"
												styleClass="colorblue2 p_5" style="width:200px;"></html:text>
										</td>
										<td class="lef">
											成立时间
										</td>
										<td style="text-align: left">
											<html:text property="registerDate" name="company"
												value="${company.registerDate}" styleClass="colorblue2 p_5"
												style="width:200px;"
												onfocus="WdatePicker({startDate:'%y-%M-%D',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"
												readonly="true" />
										</td>
									</tr>
									<tr>
										<td class="lef">
											主营业务
										</td>
										<td style="text-align: left">
											<html:text property="mainBusiness" name="company"
												value="${company.mainBusiness}" styleClass="colorblue2 p_5"
												style="width:200px;"></html:text>
										</td>
										<td class="lef">
											兼营业务
										</td>
										<td style="text-align: left">
											<html:text property="sidelineBusiness" name="company"
												value="${company.sidelineBusiness}"
												styleClass="colorblue2 p_5" style="width:200px;"></html:text>
										</td>
									</tr>
									<tr>
										<td class="lef">
											供应链
										</td>
										<td style="text-align: left" colspan="3">
											<span id="provideChainObj"></span>
										</td>
									</tr>
									<tr>
										<td class="lef">
											备注
										</td>
										<td style="text-align: left">
											<html:text property="memo" name="company"
												value="${company.memo}" styleClass="colorblue2 p_5"
												style="width:400px;"></html:text>
										</td>
										<td class="lef">
											状态
										</td>
										<td style="text-align: left">
											<html:select property="status" name="company"
												styleClass="colorblue2 p_5" style="width:50px;">
												<html:option value="1">有效</html:option>
												<html:option value="0">无效</html:option>
											</html:select>
										</td>
									</tr>
								</table>
								<table width="100%" style="margin-top: 5px;">
									<tr>
										<td align="center">
											<html:hidden property="id" value="${company.id}"></html:hidden>
											<html:hidden property="type" value="${company.type}"></html:hidden>
											<html:hidden property="thisAction" name="company" />

											<input name="label" type="button" class="button1" value="返 回"
												onclick="window.history.back();">
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
			<script type="text/javascript">	
		window.onload=initOnload;
		
		function initOnload(){
			printCheckBox('provideChainObj');							
		}
		
		function printCheckBox(objId){		
			 var checkBoxSpan = document.getElementById(objId);	
			 var htmlContent="";
			 var provideChain="<c:out value='${company.provideChain}' />";
		
			 dataTypeBiz.getSubDataTypeList(53,function(dataTypeList){			 
				for(var i=0;i<dataTypeList.length;i++){	
					var dataTypeNo=dataTypeList[i].no;	
					var dataTypeName=dataTypeList[i].name;		
					htmlContent+="<input type='checkBox' name='provideChainGroup'  value='"+dataTypeNo+"'";
					
					var valueArray=provideChain.split(",");
					for(j=0;j<valueArray.length;j++){
	  	  				if(valueArray[j]!=null){	  	  	  					 	
	  	  					var arrayValue=valueArray[j];
	  	  					if(dataTypeNo==arrayValue){  
								htmlContent+=" checked='checked' ";
								break;
	  	  				 	}
	  	  			 	}	  	  			
	  	  		     }
				 htmlContent+=">"+dataTypeName+"</input>";
				 
				 if((i+1)%10==0){
				 	htmlContent+="<br/>";
				 }
		        }
		         //alert(htmlContent);
		         checkBoxSpan.innerHTML=htmlContent;
			 });	 
		}
		</script>
		</html:form>
	</body>
</html>