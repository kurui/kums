﻿<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
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
			
		<c:import url="../page/importDWR.jsp"></c:import>
		<script type="text/javascript" src="<%=path%>/_js/prototype/common.js"></script>
		<script type="text/javascript" src="<%=path%>/_js/jquery-1.3.2.min.js"></script>
			<script type="text/javascript" src="<%=path%>/_js/base/FormUtil.js"></script>
		<script type="text/javascript"
			src="<%=path%>/_js/calendar/WdatePicker.js"></script>
	</head>
	<script type="text/javascript">	
		function add(){	
			var thisAction =document.forms[0].thisAction.value;			   
		    document.forms[0].action="<%=path%>/agent/agentEvent.do?thisAction="+thisAction;
		    document.forms[0].submit();
		}
		
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
		
		function addAgentSpeed(){
			openWindow(800,600,'../agent/agentList.do?thisAction=saveSpeed');		
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
	left: 20%; /*IE*/
}
</style>
	<body>
		<c:import url="../page/mainTitle.jsp" charEncoding="UTF-8">
			<c:param name="title1" value="客户管理" />
			<c:param name="title2" value="编辑客户事件" />
		</c:import>
		<html:form action="/agent/agentEvent.do" method="post">
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
											客户
										</td>
										<td style="text-align: left">
											<html:hidden property="agentId"
												value="${agentEvent.agent.id}" name="agentEvent" />
											<html:text property="agentNo" name="agentEvent" 
											value="${agentEvent.agent.agentNo}|${agentEvent.agent.name}"
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
										<td class="lef">
											状态
										</td>
										<td style="text-align: left">
											<html:select property="status" value="${agentEvent.status}"
												name="agentEvent" styleClass="colorblue2 p_5"
												style="width:80px;">
												<html:option value="1">有效</html:option>
												<html:option value="0">无效</html:option>
											</html:select>
										</td>
									</tr>
									<tr>
										<td class="lef">
											内容
										</td>
										<td style="text-align: left" colspan="3" >
											<html:text property="content" name="agentEvent"
												value="${agentEvent.content}" styleClass="colorblue2 p_5"
												style="width:800px;"></html:text>
										</td>
									</tr>
									<tr>
									<td class="lef">
											处理时间
										</td>
										<td style="text-align: left">
											<html:text property="updateDate" name="agentEvent"
												value="${agentEvent.updateDate}"
												styleClass="colorblue2 p_5" style="width:200px;"
												onfocus="WdatePicker({startDate:'%y-%M-%D',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"></html:text>
										</td>
										<td class="lef">
											类型
										</td>
										<td style="text-align: left">
											<html:select property="type" value="${agentEvent.type}"
												name="agentEvent" styleClass="colorblue2 p_5"
												style="width:100px;">
												<html:option value="0">-请选择-</html:option>
												<html:option value="1">默认</html:option>
											</html:select>
										</td>
									</tr>
									
								</table>
								<table width="100%" style="margin-top: 5px;">
									<tr>
										<td>
											<html:hidden property="id" name="agentEvent"></html:hidden>
											<html:hidden property="thisAction" name="agentEvent" />
											
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
		</html:form>
	</body>
	<script>
			
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
	</script>
</html>