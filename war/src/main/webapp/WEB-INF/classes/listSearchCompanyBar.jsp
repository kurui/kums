<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/tld/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>

<html:hidden property="companyId"  />
<html:text property="companyNo" styleClass="colorblue2 p_5"
	style="width:150px;" ondblclick="this.value='';"
	onkeyup="onChangeSelectCompany();"  />
<div id="companyBox" class="companyBoxDiv" style="display: none">
	<table id="tableBlurCompany" cellpadding="0" cellspacing="0" border="0"
		class="dataList" width="100%">
	</table>
</div>
<script>	
				//====================onChange Company begin=========
		function onChangeSelectCompany(){
			$("#tableBlurCompany").empty();
			displayObj("companyBox","");		
			var companyNo=document.forms[0].companyNo.value;
			if(companyNo!=null){
				platComAccountStore.getBlurCompanyList(companyNo,function(blurCompanyList){
					//alert("blurCompanyList:"+blurCompanyList);
					if(blurCompanyList!=null){
						if(blurCompanyList.length>0){					
						for(var i=0;i<blurCompanyList.length;i++){
							var company=blurCompanyList[i];
							if(company!=null){					
								var info="<a href='#' onclick='confirmSelectCompany("+company.id+");'>"+company.name+"</a><br/>";
								
								addBlurCompanyRow('tableBlurCompany',info,i);
							}							
						}	
						
						}else{
							displayObj("companyBox","none");	
						}					
					}else{
						displayObj("companyBox","none");	
					}			
				});
			}
		}
		
		function addBlurCompanyRow(tableId,info,maxRow){
			var tableObj= $("#"+tableId);
			var rowHtml="";		
			rowHtml+="<tr id='row"+maxRow+"' >";
			rowHtml+="<td>"+info+"</td>";	
			rowHtml+="</tr>";
			tableObj.append(rowHtml);  		
		}
		
	function confirmSelectCompany(companyId){
		displayObj("companyBox","none");	
		addCompanyId(companyId);	
		
	}
	
		function addCompanyId(companyId){	
			//alert("companyId:"+companyId);		
			//document.forms[0].companyId.value=companyId;	
			if(companyId!=null){
				document.forms[0].companyId.value=companyId;	
				companyBiz.getCompanyById(companyId,function(company){
					if(company!=null){
						document.forms[0].companyNo.value=company.name;	
						
						document.forms[0].submit();
					}					
				});
			}
		}	
	</script>
<style>
.companyBoxDiv {
	width: 200px;
	height: 250 px;
	background-color: #f5f5f5;
	border: 1 px solid Silver;;
	overflow: auto;
	position: absolute;
	z-index: 3;
	top: 12%;
	left: 20%; /*IE*/
}
</style>