//table.js
//
if (typeof (js) == "undefined") {
	js = function () {
		alert("js");
	};
}
js.table = function () {
	alert("kurui base table.js");
};

js.table.table.style1 = function(){
   $(".dataList tr:even").css({background:"#F8F8F8"});
	$(".dataList tr").hover(function () {
		$(this).css({background:"#CED5E5"});
	}, function () {
		$(this).css({background:"none"});
	});
	$(".dataList tr:even").hover(function () {
		$(this).css({background:"#CED5E5"});
	}, function () {
		$(this).css({background:"#F8F8F8"});
	});
	$(".tfoot").hover(function () {
		$(this).css({background:"#FFF"});
	});
}

