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
		    document.forms[0].action="<%=path%>/agent/agentCoterie.do?thisAction="+thisAction;
		    document.forms[0].submit();
		}
		
		function addAgentId(agentId){	
			//alert("agentId:"+agentId);		
			//document.forms[0].agentId.value=agentId;	
			if(agentId!=null){
				document.forms[0].subAgentId.value=agentId;	
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
			<c:param name="title2" value="编辑圈中客户" />
		</c:import>
		<html:form action="/agent/agentCoterie.do" method="post">
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
											客户圈
										</td>
										<td style="text-align: left">
											<c:out value="${agentCoterie.coterie.name}"></c:out>
											<html:hidden property="coterieId"
												value="${agentCoterie.coterie.id}" name="agentCoterie" />
										</td>
									</tr>
									<tr>
										<td class="lef">
											客户
										</td>
										<td style="text-align: left">
											<c:if test="${! empty agentCoterie.subAgent}">
												<c:out value="${agentCoterie.subAgent.agentNo}"></c:out>
											|
											<c:out value="${agentCoterie.subAgent.name}"></c:out>

											</c:if>
											<html:hidden property="subAgentId"
												value="${agentCoterie.subAgent.id}" name="agentCoterie" />
											<c:if test="${ empty agentCoterie.subAgent}">
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
											状态
										</td>
										<td style="text-align: left">
											<html:select property="status" value="${agentCoterie.status}"
												name="agentCoterie" styleClass="colorblue2 p_5"
												style="width:50px;">
												<html:option value="1">有效</html:option>
												<html:option value="0">无效</html:option>
											</html:select>
										</td>
									</tr>
								</table>
								<table width="100%" style="margin-top: 5px;">
									<tr align="center">
										<td>
											<html:hidden property="id" name="agentCoterie"></html:hidden>
											<html:hidden property="thisAction" name="agentCoterie" />
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