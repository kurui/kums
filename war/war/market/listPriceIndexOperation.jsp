<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/fmt.tld" prefix="fmt"%>
<script type="text/javascript">
	function add(){
	    document.forms[0].thisAction.value="save";
	    document.forms[0].submit();
	}
	
	function addDestPriceIndex(priceReferenceTypes){
		document.forms[0].priceReferenceTypes.value=priceReferenceTypes;
	    document.forms[0].thisAction.value="saveDest";
	    document.forms[0].submit();
	}	
	
	function addBatch(priceReferenceTypes){
		document.forms[0].priceReferenceTypes.value=priceReferenceTypes;
	    document.forms[0].thisAction.value="saveBatch";
	    document.forms[0].submit();
	}	
	
	function editBatch(){
	    document.forms[0].thisAction.value="editBatch";
	    document.forms[0].submit();
	}	
	
	function addAppoint(priceReferenceId){
		document.forms[0].priceReferenceId.value=priceReferenceId;
	    document.forms[0].thisAction.value="saveAppoint";
	    document.forms[0].submit();
	}	
	
	function listChart()	{	
	 if(document.forms[0].selectedItems==null){
		alert("没有选项");
	 }else if (sumCheckedBox(document.forms[0].selectedItems)<1){
	 	alert("请选择指数项！");
	 } else{
	    document.forms[0].thisAction.value="listChart";
	    document.forms[0].submit();
	  }
	}
	
	function edit()	{
		if(document.forms[0].selectedItems==null){
			alert("没有数据，无法修改！");
		}else if (sumCheckedBox(document.forms[0].selectedItems)<1){
	   		alert("您还没有选择数据！");
		}else if (sumCheckedBox(document.forms[0].selectedItems)>1){
	      	alert("您一次只能选择一个数据！");
		}else {
	    	document.forms[0].thisAction.value="edit";
	    	document.forms[0].submit();
	  	}
	}
	
	function editBatch()	{
		if(document.forms[0].selectedItems==null){
			alert("没有数据，无法修改！");
		}else if (sumCheckedBox(document.forms[0].selectedItems)<1){
	   		alert("您还没有选择数据！");
		}else {
	    	document.forms[0].thisAction.value="editBatch";
	    	document.forms[0].submit();
	  	}
	}
	
	function del()	{	
	 if(document.forms[0].selectedItems==null){
		alert("没有数据，无法修改！");
	 }else if (sumCheckedBox(document.forms[0].selectedItems)<1)
	    alert("您还没有选择数据！");
	 else if(confirm("您真的要删除选择的这些数据吗？")){
	    document.forms[0].thisAction.value="delete";
	    document.forms[0].submit();
	  }
	}	
	
	</script>
<input name="label" type="button" class="button1" value="新 增"
	onclick="add();" />
<input name="label" type="button" class="button1" value="修 改"
	onclick="edit();" />
<input name="label" type="button" class="button1" value="批量修改"
	onclick="editBatch();" />
<input name="label" type="button" class="button1" value="删 除"
	onclick="del();" />
<input name="label" type="button" class="button1" value="新增利率"
	onclick="addBatch(1);" />
<input name="label" type="button" class="button1" value="新增外汇"
	onclick="addBatch(10);" />
<input name="label" type="button" class="button1" value="RMB中间价"
	onclick="addBatch(11);" />
<input name="label" type="button" class="button1" value="新增股指"
	onclick="addBatch(20);" />
<input name="label" type="button" class="button1" value="新增期货"
	onclick="addBatch(30);" />
<input name="label" type="button" class="button1" value="新增现货"
	onclick="addBatch(40);" />

<input name="label" type="button" class="button1" value="新增终端"
	onclick="addDest(100);" style="display: none" />
<input name="label" type="button" class="button1" value="新增终端"
	onclick="addBatch(100);">
<html:hidden property="priceReferenceId"></html:hidden>
<html:hidden property="priceReferenceTypes"></html:hidden>

