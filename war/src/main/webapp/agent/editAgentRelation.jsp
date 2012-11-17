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
		<script src="<%=path%>/_js/base/FormUtil.js" type="text/javascript"></script>
		<script type="text/javascript" src="<%=path%>/_js/prototype/common.js"></script>
	</head>
	<script type="text/javascript">	
		function add(){	
			var thisAction =document.forms[0].thisAction.value;			   
		    document.forms[0].action="<%=path%>/agent/agentRelation.do?thisAction="+thisAction;
		    document.forms[0].submit();
		}
		
			function addAgentId(agentId){	
			//alert("agentId:"+agentId);		
			if(agentId!=null){
				document.forms[0].relateAgentId.value=agentId;	
				agentBiz.getAgentById(agentId,function(agentObj){
					if(agentObj!=null){
						document.forms[0].agentNo.value=agentObj.agentNo+"|"+agentObj.name;	
					}					
				});
			}
		}		
			
		function selectAgent(){	
			openWindow(800,600,'../agent/agentList.do?thisAction=selectFinanceAgent');		
		}
		
		function addAgentSpeed(){	
			openWindow(800,600,'../agent/agentList.do?thisAction=saveSpeed');		
		}
	</script>
	<body>
		<c:import url="../page/mainTitle.jsp" charEncoding="UTF-8">
			<c:param name="title1" value="客户管理" />
			<c:param name="title2" value="编辑客户关系" />
		</c:import>
		<html:form action="/agent/agentRelation.do" method="post">
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
											参照系
										</td>
										<td style="text-align: left">
											<c:out value="${agentRelation.rootAgent.agentNo}"></c:out>
											|
											<c:out value="${agentRelation.rootAgent.name}"></c:out>
										</td>
									</tr>
									<tr>
										<td class="lef">
											对象
										</td>
										<td style="text-align: left">
											<c:if test="${! empty agentRelation.relateAgent}">
											<c:out value="${agentRelation.relateAgent.agentNo}"></c:out>
											|
											<c:out value="${agentRelation.relateAgent.name}"></c:out>
											</c:if>
											<c:if test="${ empty agentRelation.relateAgent}">
											<html:text property="agentNo" styleClass="colorblue2 p_5"
													readonly="true" />
												<input name="label" type="button" class="button1"
													value="选择客户" onclick="selectAgent();">
												<input name="label" type="button" class="button1"
													value="快速建档" onclick="addAgentSpeed();">
											</c:if>
										</td>
									</tr>
									<tr>
										<td class="lef">
											类型
										</td>
										<td style="text-align: left">
											<html:select property="relationType"
												value="${agentRelation.relationType}" name="agentRelation"
												styleClass="colorblue2 p_5" style="width:100px;">
												<html:option value="0">-请选择-</html:option>
												<html:option value="1">上下级</html:option>
												<html:option value="11">同级</html:option>
												<html:option value="21">密友</html:option>
												<html:option value="31">情侣</html:option>
												<html:option value="35">夫妻</html:option>
												<html:option value="38">子女</html:option>
												<html:option value="40">亲属</html:option>
												<html:option value="51">门生故吏</html:option>
											</html:select>
										</td>
									</tr>
									<tr>
										<td class="lef">
											处理时间
										</td>
										<td style="text-align: left">
											<html:text property="updateDate" name="agentRelation"
												value="${agentRelation.updateDate}"
												styleClass="colorblue2 p_5" style="width:200px;"></html:text>
										</td>
									</tr>
									<tr>
										<td class="lef">
											状态
										</td>
										<td style="text-align: left">
											<html:select property="status"
												value="${agentRelation.status}" name="agentRelation"
												styleClass="colorblue2 p_5" style="width:50px;">
												<html:option value="1">有效</html:option>
												<html:option value="2">历史</html:option>
												<html:option value="0">无效</html:option>
											</html:select>
										</td>
									</tr>
								</table>
								<table width="100%" style="margin-top: 5px;">
									<tr>
										<td>
											<html:hidden property="id" value="${agentRelation.id}"
												name="agentRelation" />
											<html:hidden property="rootAgentId" value="${agentRelation.rootAgent.id}"
												name="agentRelation" />
											<html:hidden property="relateAgentId" value="${agentRelation.relateAgent.id}"
												name="agentRelation" />
												
											<html:hidden property="thisAction" name="agentRelation" />
											
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
</html>