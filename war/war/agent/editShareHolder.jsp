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
		<c:import url="../page/importDWR.jsp"></c:import>
		<script type="text/javascript" src="<%=path%>/_js/prototype/common.js"></script>
		<script type="text/javascript" src="<%=path%>/_js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript" src="<%=path%>/_js/base/FormUtil.js"></script>
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
	left: 12%;
	/*IE*/
}
</style>
		<script type="text/javascript">	
		
		function add(){	
			var capitalAmount=document.forms[0].capitalAmount.value;
			if(capitalAmount==""){
				alert("请输入投资额!")
				return false;
			}
			var thisAction =document.forms[0].thisAction.value;			   
		    document.forms[0].action="<%=path%>/agent/shareHolder.do?thisAction="+thisAction;
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
	</head>

	<body>
		<c:import url="../page/mainTitle.jsp" charEncoding="UTF-8">
			<c:param name="title1" value="财务管理" />
			<c:param name="title2" value="客户账户" />
			<c:param name="title3" value="编辑账户" />
		</c:import>
		<html:form action="/agent/shareHolder.do" method="post">
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
											股东
										</td>
										<td style="text-align: left">
											<html:hidden property="agentId"
												value="${shareHolder.agent.id}" name="shareHolder" />
											<html:text property="agentNo"
												value="${shareHolder.agent.name}" name="shareHolder"
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
											公司
										</td>
										<td style="text-align: left">
											<html:select property="companyId" name="shareHolder"
												value="${shareHolder.company.id}" styleClass="colorblue2 p_5" style="width:200px;">
												<c:forEach items="${companyList}" var="company">
													<html:option value="${company.id}">
														<c:out value="${company.name}" />
													</html:option>
												</c:forEach>
											</html:select>
										</td>
									</tr>
									<tr>
										<td class="lef">
											投资额
										</td>
										<td style="text-align: left">
											<html:text property="capitalAmount" name="shareHolder"
												value="${shareHolder.capitalAmount}"
												styleClass="colorblue2 p_5" style="width:200px;"></html:text>
										</td>
									</tr>
									<tr>
										<td class="lef">
											说明
										</td>
										<td style="text-align: left">
											<html:text property="memo" name="shareHolder"
												value="${shareHolder.memo}" styleClass="colorblue2 p_5"
												style="width:200px;"></html:text>
										</td>
									</tr>
									<tr>
										<td class="lef">
											控制权
										</td>
										<td style="text-align: left">
											<html:select property="controlType"
												value="${shareHolder.controlType}" name="shareHolder"
												styleClass="colorblue2 p_5" style="width:100px;">
												<html:option value="1">绝对控股</html:option>
												<html:option value="2">相对控股</html:option>
												<html:option value="3">参股</html:option>
												<html:option value="11">关联</html:option>
											</html:select>
										</td>
									</tr>
									<tr>
										<td class="lef">
											状态
										</td>
										<td style="text-align: left">
											<html:select property="status" value="${shareHolder.status}"
												name="shareHolder" styleClass="colorblue2 p_5"
												style="width:50px;">
												<html:option value="1">有效</html:option>
												<html:option value="0">无效</html:option>
											</html:select>
										</td>
									</tr>
								</table>
								<table width="100%" style="margin-top: 5px;">
									<tr>
										<td>
											<html:hidden property="id" name="shareHolder"></html:hidden>
											<html:hidden property="thisAction" name="shareHolder" />
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
		<script type="text/javascript">
		function onChangeSelectAgent(){
			$("#tableBlurAgent").empty();
			displayObj("agentBox","");		
			var agentNo=document.forms[0].agentNo.value;
			if(agentNo!=null){
				agentBiz.getBlurAgentList(agentNo,function(blurAgentList){
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
	</body>
</html>