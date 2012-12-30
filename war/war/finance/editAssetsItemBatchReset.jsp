<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%
	String path = request.getContextPath();
%>
<html>
<head>
<title>main</title>
<link href="<%=path%>/_css/reset.css" rel="stylesheet" type="text/css" />
<link href="<%=path%>/_css/global.css" rel="stylesheet" type="text/css" />
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
</head>
<script type="text/javascript">	
		function add(){	
			var thisAction =document.forms[0].thisAction.value;			   
		    document.forms[0].action="<%=path%>/finance/assetsItem.do?thisAction="+thisAction;
		    document.forms[0].submit();
		}
	
	</script>
<body>
	<c:import url="../page/mainTitle.jsp" charEncoding="UTF-8">
		<c:param name="title1" value="财务管理" />
		<c:param name="title2" value="批量编辑资产项目" />
	</c:import>
	<html:form action="/finance/assetsItem.do" method="post">
		<html:hidden property="assetsItemIdGroup" />
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
									<td class="lef">追加说明</td>
									<td style="text-align: left" colspan="3"><html:text
											property="memo" name="assetsItem" value="${assetsItem.memo}"
											styleClass="colorblue2 p_5" style="width:500px;"></html:text>
									</td>
								</tr>
								<tr>
									<td class="lef">资产类型</td>
									<td style="text-align: left"><html:select
											property="itemType" value="${assetsItem.itemType}"
											name="assetsItem" styleClass="colorblue2 p_5"
											style="width:100px;">
											<html:option value="">-资产项目-</html:option>
											<c:forEach items="${itemTypeList}" var="itemType">
												<html:option value="${itemType.no}">
													<c:out value="${itemType.showName}" />
												</html:option>
											</c:forEach>
										</html:select> <html:select property="type" value="${assetsItem.type}"
											name="assetsItem" styleClass="colorblue2 p_5"
											style="width:80px;">
											<html:option value="1">默认</html:option>
										</html:select></td>
								</tr>
								<tr>
										<td class="lef">地点</td>
									<td style="text-align: left"><html:text
											property="areaCode" value="${assetsItem.areaCode}"
											name="areaCode" styleClass="colorblue2 p_5"
											style="width:50px;" /></td>
									<td class="lef">状态</td>
									<td style="text-align: left"><html:select
											property="status" value="${assetsItem.status}"
											name="assetsItem" styleClass="colorblue2 p_5"
											style="width:80px;">
											<html:option value="1">有效</html:option>
											<html:option value="0">无效</html:option>
										</html:select></td>
								</tr>
							
							</table>
							<table width="100%" style="margin-top: 5px;">
								<tr align="center">
									<td><html:hidden property="id" name="assetsItem"></html:hidden>
										<html:hidden property="thisAction" name="assetsItem" /> <input
										name="label" type="button" class="button1" value="关闭"
										onclick="window.close();"> <input name="label"
										type="button" class="button1" value="提交" onclick="add();">
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