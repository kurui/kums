<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
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
		<script type="text/javascript" language="javascript"
			src="../_js/jquery-1.3.2.min.js"></script>
		<script src="../_js/prototype/common.js" type="text/javascript"></script>

		<script type="text/javascript">
	function add(){
	    document.forms[0].thisAction.value="save";
	    document.forms[0].submit();
	}
	
	function edit(){
	 	if(document.forms[0].selectedItems==null){
			alert("没有数据，无法修改！");
		}else if (sumCheckedBox(document.forms[0].selectedItems)<1){
	   		alert("您还没有选择数据！");
	 	}else if (sumCheckedBox(document.forms[0].selectedItems)>1){
	    	alert("您一次只能选择一条数据！");
	  	}else{
	    	document.forms[0].thisAction.value="edit";
	    	document.forms[0].submit();
	  	}
	}
	
	function del(){	
	 if(document.forms[0].selectedItems==null){
		alert("没有数据，无法修改！");
	 }else if (sumCheckedBox(document.forms[0].selectedItems)<1){
	    alert("您还没有选择数据！");
	 }else if(confirm("您真的要删除选择的这些数据吗？")){
	    document.forms[0].thisAction.value="delete";
	    document.forms[0].submit();
	 }
	}	
	
	function onclickCheckItems(){
		var checkItems=document.forms[0].checkItems;
		if (checkItems==undefined || checkItems.length==undefined){
	    	return;
	  	}	  	
		for(j=0;j<checkItems.length;j++){
	  	  	if(checkItems[j]!=null){	  	  	  					 	
	  	  		var arrayValue=checkItems[j].value;
	  	  		if(arrayValue!=null){
	  	  		//alert(arrayValue);
	  	  			var thObj=document.getElementById(arrayValue+"TH");
	  	  			if(thObj!=null){
	  	  			for(i=1;i<11;i++){	  	  			
		  	  			var tdObj=document.getElementById(arrayValue+"TD"+i);		  	  			
		  	  			if(tdObj!=null){
		  	  				if(checkItems[j].checked){
				  	  			thObj.style.display="";
			  	  				tdObj.style.display="";
				  	  		}else{
				  	  			thObj.style.display="none";
			  	  				tdObj.style.display="none";
				  	  		}
		  	  			}else{
		  	  				//alert(arrayValue+"TD"+i+" Object not found:"+tdObj);
		  	  				return;
		  	  			}		  	  	
	  	  			}	  	  				
	  	  		}else{
	  	  			alert(arrayValue+"TH"+" Object not found:"+thObj);
		  	  		return;
	  	  		}	  	  		
	  	  	}
	  	}
	  }
	}
	</script>
	</head>
	<body>
		<div id="mainContainer">
			<div id="container">
				<html:form action="/agent/agentHabitList.do">
					<html:hidden property="thisAction" />
					<html:hidden property="lastAction" />
					<html:hidden property="intPage" />
					<html:hidden property="pageCount" />
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
									<c:param name="title1" value="客户管理" />
									<c:param name="title2" value="客户习性列表" />
								</c:import>
								<div class="searchBar">
									<table cellpadding="0" cellspacing="0" border="0"
										class="searchPanel">
										<tr>
											<td>
												<html:text property="contactWay" styleClass="colorblue2 p_5"
													style="width:150px;" value="客户" ondblclick="this.value=''" />
											</td>
											<td>
												<input type="submit" name="button" id="button" value="提交"
													class="submit greenBtn" />
											</td>
											<td>
												<input type="checkbox" name="checkItems" value="amuse"
													style="width: 20px;" onclick="onclickCheckItems();"
													checked="checked" />
												娱乐
												<input type="checkbox" name="checkItems" value="reading"
													style="width: 20px;" onclick="onclickCheckItems();"
													checked="checked" />
												阅读
												<input type="checkbox" name="checkItems"
													value="airplaneSeat" style="width: 20px;"
													onclick="onclickCheckItems();" />
												乘机位置
												<input type="checkbox" name="checkItems"
													value="travelAppoint" style="width: 20px;"
													onclick="onclickCheckItems();" checked="checked" />
												想去的地方
												<input type="checkbox" name="checkItems" value="drink"
													onclick="onclickCheckItems();" checked="checked" />
												饮料
												<input type="checkbox" name="checkItems" value="food"
													onclick="onclickCheckItems();" checked="checked" />
												食物
												<input type="checkbox" name="checkItems" value="flower"
													onclick="onclickCheckItems();" checked="checked" />
												花
												<input type="checkbox" name="checkItems"
													value="scaredAnimal" onclick="onclickCheckItems();" />
												害怕动物
												<input type="checkbox" name="checkItems" value="breedAnimal"
													onclick="onclickCheckItems();" />
												养过动物
												<input type="checkbox" name="checkItems" value="filmType"
													onclick="onclickCheckItems();" checked="checked" />
												影视
												<input type="checkbox" name="checkItems" value="sports"
													onclick="onclickCheckItems();" checked="checked" />
												运动
												<input type="checkbox" name="checkItems" value="religion"
													onclick="onclickCheckItems();" />
												宗教/政治倾向
												<input type="checkbox" name="checkItems" value="oppositeSex"
													onclick="onclickCheckItems();" />
												异性
												<input type="checkbox" name="checkItems" value="homoSex"
													onclick="onclickCheckItems();" />
												同性
												<input type="checkbox" name="checkItems" value="bigWish"
													onclick="onclickCheckItems();" />
												愿望
											</td>
										</tr>
									</table>
								</div>
								<table width="100%" cellpadding="0" cellspacing="0" border="0"
									class="dataList">
									<tr>
										<th width="30">
											<div>
												选择
											</div>
										</th>
										<th width="30">
											<div>
												序号
											</div>
										</th>
										<th width="60">
											<div>
												客户
											</div>
										</th>
										<th id="amuseTH">
											<div>
												娱乐
											</div>
										</th>
										<th id="readingTH">
											<div>
												阅读
											</div>
										</th>
										<th id="airplaneSeatTH" style="display: none">
											<div>
												乘机位置
											</div>
										</th>
										<th id="travelAppointTH">
											<div>
												想去的地方
											</div>
										</th>
										<th id="drinkTH">
											<div>
												饮料
											</div>
										</th>
										<th id="foodTH">
											<div>
												食物
											</div>
										</th>
										<th id="flowerTH">
											<div>
												花
											</div>
										</th>
										<th id="scaredAnimalTH" style="display: none">
											<div>
												害怕的
											</div>
										</th>
										<th id="breedAnimalTH">
											<div>
												养过的
											</div>
										</th>
										<th id="filmTypeTH">
											<div>
												影视
											</div>
										</th>
										<th id="sportsTH">
											<div>
												运动
											</div>
										</th>
										<th id="religionTH" style="display: none">
											<div>
												宗教/政治倾向
											</div>
										</th>
										<th id="bigWishTH" style="display: none">
											<div>
												最大愿望
											</div>
										</th>
										<th id="oppositeSexTH" style="display: none">
											<div>
												异性
											</div>
										</th>
										<th id="homoSexTH" style="display: none">
											<div>
												同性
											</div>
										</th>
										<th>
											<div>
												操作
											</div>
										</th>
									</tr>
									<c:forEach var="agentHabit" items="${agentHabitListForm.list}"
										varStatus="status">
										<tr>
											<td>
												<html:multibox property="selectedItems"
													value="${agentHabit.id}"></html:multibox>
											</td>
											<td>
												<c:out
													value="${status.count+(agentHabitListForm.intPage-1)*agentHabitListForm.perPageNum}" />
											</td>
											<td>
												<a
													href="<%=path%>/agent/agentList.do?thisAction=view&id=<c:out value="${agentHabit.agent.id}" />">
													<c:out value="${agentHabit.agent.name}" /> </a>
											</td>
											<td id="amuseTD<c:out value='${status.count}' />">
												<c:out value="${agentHabit.amuse}" />
											</td>
											<td id="readingTD<c:out value='${status.count}' />">
												<c:out value="${agentHabit.reading}" />
											</td>
											<td id="airplaneSeatTD<c:out value='${status.count}' />"
												style="display: none">
												<c:out value="${agentHabit.airplaneSeatInfo}" />
											</td>
											<td id="travelAppointTD<c:out value='${status.count}' />">
												<c:out value="${agentHabit.travelAppoint}" />
											</td>
											<td id="drinkTD<c:out value='${status.count}' />">
												<c:out value="${agentHabit.drink}" />
											</td>
											<td id="foodTD<c:out value='${status.count}' />">
												<c:out value="${agentHabit.food}" />
											</td>
											<td id="flowerTD<c:out value='${status.count}' />">
												<c:out value="${agentHabit.flower}" />
											</td>
											<td id="scaredAnimalTD<c:out value='${status.count}' />"
												style="display: none">
												<c:out value="${agentHabit.scaredAnimal}" />
											</td>
											<td id="breedAnimalTD<c:out value='${status.count}' />"
												style="text-align: left" width="100px">
												<c:out value="${agentHabit.breedAnimal}" />
											</td>
											<td id="filmTypeTD<c:out value='${status.count}' />">
												<c:out value="${agentHabit.filmType}" />
											</td>
											<td id="sportsTD<c:out value='${status.count}' />">
												<c:out value="${agentHabit.sports}" />
											</td>
											<td id="religionTD<c:out value='${status.count}' />"
												style="text-align: left" width="100px" style="display: none">
												<c:out value="${agentHabit.religion}" />
											</td>
											<td id="bigWishTD<c:out value='${status.count}' />"
												style="text-align: left" width="100px" style="display: none">
												<c:out value="${agentHabit.shortBigWish}" />
											</td>
											<td id="oppositeSexTD<c:out value='${status.count}' />"
												style="display: none">
												<c:out value="${agentHabit.oppositeSex}" />
											</td>
											<td id="homoSexTD<c:out value='${status.count}' />"
												style="display: none">
												<c:out value="${agentHabit.homoSex}" />
											</td>
											<td>
												<a
													href="<%=path%>/agent/agentHabitList.do?thisAction=view&id=<c:out value="${agentHabit.id}" />">
													查看</a>
											</td>
										</tr>
									</c:forEach>
								</table>
								<table width="100%" style="margin-top: 5px;">
									<tr>
										<td>
											<input name="label" type="button" class="button1" value="新 增"
												onclick="add();">
											<input name="label" type="button" class="button1" value="编 辑"
												onclick="edit();">
											<input name="label" type="button" class="button1" value="删 除"
												onclick="del();">
										</td>
										<td align="right">
											<div>
												共有记录&nbsp;
												<c:out value="${agentHabitListForm.totalRowCount}"></c:out>
												&nbsp;条&nbsp;&nbsp;&nbsp;&nbsp; [
												<a href="JavaScript:turnToPage(document.forms[0],0)">首页</a>
												|
												<a href="JavaScript:turnToPage(document.forms[0],1)">上一页</a>
												|
												<a href="JavaScript:turnToPage(document.forms[0],2)">下一页</a>
												|
												<a href="JavaScript:turnToPage(document.forms[0],3)"> 末页</a>]
												页数:
												<c:out value="${agentHabitListForm.intPage}" />
												/
												<c:out value="${agentHabitListForm.pageCount}" />
												]
											</div>
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
	</body>
</html>