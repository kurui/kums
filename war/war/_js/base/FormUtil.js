
function checkStringArray(arry, msg) {
	for (var i = 0; i < arry.length; i++) {
		if (arry[i].value == "" || arry[i].value == null) {
			alert(msg);
			return false;
		}
	}
}

function checkSelectArray(arry, msg) {
	if (arry && arry.length > 0) {
		for (var i = 0; i < arry.length; i++) {
			if (arry[i].value == "" || arry[i].value == null) {
				alert(msg);
				return false;
			}
		}
	} else {
		alert(msg);
		return false;
	}
}
		
//验证金额
function checkNumArray(arry, msg) {
	for (var i = 0; i < arry.length; i++) {
		if (arry[i].value == "" || arry[i].value == null) {
			alert(msg);
			return false;
		} else {
			if (!isNum(arry[i].value)) {
				alert(msg);
				return false;
			}
		}
	}
}

//是否只包含数字
function isNum(b) {
	var re = /^([1-9][0-9]*|0)(\.[0-9]{0,2})?$/;
	return (re.test(b));
}


function trim(form) {
	for (i = 0; i < form.elements.length; i++) {
		if (form.elements[i].tagName.toLowerCase() == "input" || form.elements[i].tagName.toLowerCase() == "select") {
			form.elements[i].value = form.elements[i].value.trim();
		}
	}
}

	function setSameValue(objName){
			var objValue="";
			var objs=document.getElementsByName(objName);
			if(objs!=null){
				for(var i=0;i<objs.length;i++){
					if(i==0){
						objValue=objs[i].value;
					}else{
						objs[i].value=objValue;
					}
				}
			}			
		}

	function displayObj(objId,displayValue){
		document.getElementById(objId).style.display=displayValue;
	}

function setSubmitButtonDisable(objId) {
	var submitButtonObj = document.getElementById(objId);
	//alert(objId);
	if (submitButtonObj != null) {
		//objId.disable=true;//在type=button时使用会有不起作用的情况，原因不详
		submitButtonObj.style.display = "none";
		
		//alert(objId.disable);
		return true;
	} else {
		return false;
	}
}
function setDefaultValue(objId, defaultValue) {
	var obj = document.getElementById(objId + "");
	obj.value = defaultValue;
}
function checkAll(e, itemName) {
	var aa = document.getElementsByName(itemName);
	for (var i = 0; i < aa.length; i++) {
		aa[i].checked = e.checked;
	}
}
function checkItem(e, allName) {
	var all = document.getElementsByName(allName)[0];
	if (!e.checked) {
		all.checked = false;
	} else {
		var aa = document.getElementsByName(e.name);
		for (var i = 0; i < aa.length; i++) {
			if (!aa[i].checked) {
				return;
			}
		}
		all.checked = true;
	}
}
function showElement(ulId) {
	var selectedUL = document.getElementById(ulId);
	if (selectedUL.style.display == "") {
		selectedUL.style.display = "none";
	} else {
		selectedUL.style.display = "";
	}
}
function keypressCreateOrder(e) {
	var isie = (document.all) ? true : false;//判断是IE内核还是Mozilla  
	var key;
	if (isie) {
		key = window.event.keyCode;
	} else {
		key = e.which;
	}
	if (key == 13) {
		createOrder();
	}
}
function goMyIntPage(form) {
	var _key;
	document.onkeyup = function (e) {
		if (e == null) { // ie
			_key = event.keyCode;
		} else { // firefox
			_key = e.which;
		}
		if (_key == 13) {
			if (form.myIntPage.value > 0) {
				form.myIntPage.value = form.myIntPage.value - 1;
			}
			form.intPage.value = form.myIntPage.value;
			turnToPage(document.forms[0], 2);
		}
	};
}

	function  isExistEmail(destArray,email) {
         	var flag = false;
         	for (var i = 0; i < destArray.length; i++) {
            	 if (email==destArray[i]) {
                	 flag = true;
             	}
         	}
         	return flag;
     	}

		    //删除行
		function delRow(_this){
			//alert($(_this).get(0));
			$(_this).parent().parent().remove();				
		}
