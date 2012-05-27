<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/fmt.tld" prefix="fmt"%>

<link href="../_js/dhtmlxtree/dhtmlxtree.css" rel="stylesheet" type="text/css" />
<script src="../_js/dhtmlxtree/dhtmlxcommon.js"></script>
<script src="../_js/dhtmlxtree/dhtmlxtree.js"></script>

<style>
.referenceTypeDiv {
	width: 250px;
	height: 300 px;
	background-color: #f5f5f5;
	border: 1 px solid Silver;;
	overflow: auto;
	position: absolute;
	z-index: 3;
	top: 10%;
}
</style>

<div id="treeBox" class="referenceTypeDiv" style="display: none">
	<input type="button" class="colorblue2 p_5" onclick="openAllItems();" value="展开"></input>
	<input type="button" class="colorblue2 p_5" onclick="closeAllItems();" value="折叠"></input>
	<input type="button" class="colorblue2 p_5" onclick="confirmSelectReference();" value="确认"></input>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<input type="button" class="colorblue2 p_5" onclick="displayObj('treeBox','none')" value="关闭"></input>
</div>

<div id="searchBarObj" class="searchBar">
	<table cellpadding="0" cellspacing="0" border="0" class="searchPanel">
		<tr>
			<td>
				<html:text property="priceReferenceNames" name="priceIndexListForm" value="-请选择参照物-" styleClass="colorblue2 p_5" style="width: 150px;" onclick="displayObj('treeBox','');" />
				<!-- onblur="displayObj('treeBox','none');" 缺陷, 展开树也会触发失去焦点 -->

				<html:hidden property="priceReferenceIds" value="${priceIndexListForm.priceReferenceIds}"></html:hidden>
			</td>
			<td style="display: none">
				<html:radio property="rapid" name="priceIndexListForm" value="thisweek" />
				本周
				<html:radio property="rapid" name="priceIndexListForm" value="lastweek" />
				上周
				<html:radio property="rapid" name="priceIndexListForm" value="thismonth" />
				本月
				<html:radio property="rapid" name="priceIndexListForm" value="last30" />
				最近30天 &nbsp; &nbsp;&nbsp;
			</td>
			<td>
				<html:select property="year" value="${priceIndexListForm.year}" onchange="resetYear();">
					<html:option value="0">
														请选择
													</html:option>
					<html:option value="2010">
														2010
													</html:option>
					<html:option value="2011">
														2011
													</html:option>
					<html:option value="2012">
														2012
													</html:option>
				</html:select>
				<html:select property="month" value="${priceIndexListForm.month}" onchange="resetMonth();">
					<html:option value="0">月
													</html:option>
					<html:option value="1">
														01
													</html:option>
					<html:option value="2">
														02
													</html:option>
					<html:option value="3">
														03
													</html:option>
					<html:option value="4">
														04
													</html:option>
					<html:option value="5">
														05
													</html:option>
					<html:option value="6">
														06
													</html:option>
					<html:option value="7">
														07
													</html:option>
					<html:option value="8">
														08
													</html:option>
					<html:option value="9">
														09
													</html:option>
					<html:option value="10">
														10
													</html:option>
					<html:option value="11">
														11
													</html:option>
					<html:option value="12">
														12
													</html:option>
				</html:select>
			</td>
			<td>
				开始:
				<html:text property="startDate" styleClass="colorblue2 p_5" style="width:120px;" onfocus="WdatePicker({startDate:'%y-%M-%D 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})"
					readonly="true" />
			</td>
			<td>
				结束
				<html:text property="endDate" styleClass="colorblue2 p_5" style="width:120px;" onfocus="WdatePicker({startDate:'%y-%M-%D 23:59:59',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})"
					readonly="true" />
			</td>
			<td>
				<input type="submit" name="button" id="button" value="提交" class="submit greenBtn" />
			</td>
			<td>
				<c:if test="${priceIndexListForm.thisAction eq 'listChart'}">
					<input type="button" name="button" alt="统计图表" class="btn_select_model_2_2" />
					<input type="button" name="button" alt="明细账" class="btn_select_model_1_1" onclick="selectList();" />
				</c:if>
				<c:if test="${priceIndexListForm.thisAction eq 'list'}">
					<input type="button" name="button" alt="统计图表" class="btn_select_model_2_1" onclick="selectChart();" />
					<input type="button" name="button" alt="明细账" class="btn_select_model_1_2" />
				</c:if>
				<script type="text/javascript">
					function selectChart(){
						 document.forms[0].thisAction.value="listChart";
	    				 document.forms[0].submit();
					}
					function selectList(){
						 document.forms[0].thisAction.value="list";
	    				 document.forms[0].submit();
					}
				</script>
			</td>
		</tr>
	</table>
</div>
<div id="showSearcheBarObj" style="display: none; float: right">
	<a href="#" onclick="shrinkSearchBar()">显示搜索栏</a>
</div>
<script type="text/javascript">
     function shrinkSearchBar(){
      	var searchBarObj=document.getElementById("searchBarObj");
      	var showSearcheBarObj=document.getElementById("showSearcheBarObj");
      	
      	if(searchBarObj.style.display==''){
      		searchBarObj.style.display='none';
      		showSearcheBarObj.style.display='';
      	}else{
      		searchBarObj.style.display='';
      		showSearcheBarObj.style.display='none';
      	}      	
      }
</script>

<script type="text/javascript">	
		window.onload=initOnload;
		
		function initOnload(){		
			
		}			
		
		function resetRapId(){
			document.forms[0].rapid.value="";
		 	var rapid=document.forms[0].rapid;
		 	alert("resetRapId:"+rapid.value);
		 	if(rapid!=null){
		 		var rapidLen=rapid.length;		
		 		for(var i=0;i<rapidLen;i++){
		 			//rapid[i].value="";
		 		}
		 	}
		}
		
		function resetYear(){
		 	var year=document.forms[0].year.value;		
		 	if(year=="0"){
		 		document.forms[0].month.value="";	
		 		document.forms[0].startDate.value="";	
		 		document.forms[0].endDate.value="";			 		
		 	}
		}
		
		function resetMonth(){
		 	var month=document.forms[0].month.value;		
		 	if(month=="0"){
		 		document.forms[0].startDate.value="";	
		 		document.forms[0].endDate.value="";			 		
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
					document.forms[0].submit();
				} 	
			}
	</script>

<script type="text/javascript">
				function tonclick(id) {
					//alert("Item " + tree.getItemText(id) + " was selected");
				};
				function tondblclick(id) {
					//alert("Item " + tree.getItemText(id) + " was doubleclicked");
				};
				function tondrag(id, id2) {
					return confirm("Do you want to move node " + tree.getItemText(id) + " to item " + tree.getItemText(id2) + "?");
				};
				function tonopen(id, mode) {
					//return confirm("Do you want to " + (mode > 0 ? "close": "open") + " node " + tree.getItemText(id) + "?");
					return true;
				};
				function toncheck(id, state) {
				    //alert("Item " + tree.getItemText(id) + " was " + ((state) ? "checked": "unchecked"));
				};			
	</script>
<script type="text/javascript">				
					tree = new dhtmlXTreeObject("treeBox", "100%", "100%", 0);
					tree.setSkin('dhx_skyblue');									
					tree.setImagePath("../_js/dhtmlxtree/imgs/csh_bluebooks/");
			    	tree.enableCheckBoxes(1);//显示复选框
				    tree.enableThreeStateCheckboxes(true);//设置点根目录全选子目录					
					tree.enableDragAndDrop(1);//设置借点可拖拽
					//打开事件监听
					tree.setOnOpenHandler(tonopen);
					tree.setOnClickHandler(tonclick);
					tree.setOnCheckHandler(toncheck);
					tree.setOnDblClickHandler(tondblclick);
					tree.setDragHandler(tondrag);
					tree.loadXML("../_xml/PriceReferenceTree.xml");		
				
					
		function closeAllItems(){
			tree.closeAllItems(0);
		}
		
		function openAllItems(){
			tree.openAllItems(0);
		}

	function displayObj(objId,displayValue){
		document.getElementById(objId).style.display=displayValue;
		if(displayValue==""){
			setCheckedTree();
		}
	}
	
	function setCheckedTree(){
		var priceReferenceIds=document.forms[0].priceReferenceIds.value;			
			if(priceReferenceIds!=null){
				var checkIds=priceReferenceIds.split(",");			
				var len=checkIds.length;
				for(var i=0;i<len;i++){
					var checkId=checkIds[i];
					//alert("checkId:"+checkId);
					tree.setCheck(checkId,true);						
				}
			}			
	}
	
	function confirmSelectReference(){
		displayObj("treeBox","none");
		
		var allCheckedItem=tree.getAllChecked();
		//alert(allCheckedItem);
		if(allCheckedItem!=null){
			document.forms[0].priceReferenceIds.value=allCheckedItem;
			document.forms[0].submit();
		}		
	}	
</script>



