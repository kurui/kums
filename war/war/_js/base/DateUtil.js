//////////////////////////////////获取系统时间///////////////////////////////////
function showLocale(objD) {
	var str, colorhead, colorfoot;
	var yy = objD.getYear();
	if (yy < 1900) {
		yy = yy + 1900;
	}
	var MM = objD.getMonth() + 1;
	if (MM < 10) {
		MM = "0" + MM;
	}
	var dd = objD.getDate();
	if (dd < 10) {
		dd = "0" + dd;
	}
	var hh = objD.getHours();
	if (hh < 10) {
		hh = "0" + hh;
	}
	var mm = objD.getMinutes();
	if (mm < 10) {
		mm = "0" + mm;
	}
	var ss = objD.getSeconds();
	if (ss < 10) {
		ss = "0" + ss;
	}
	var ww = objD.getDay();
	if (ww == 0) {
		colorhead = "<font color=\"#FF0000\">";
	}
	if (ww > 0 && ww < 6) {
		colorhead = "<font color=\"#373737\">";
	}
	if (ww == 6) {
		colorhead = "<font color=\"#008000\">";
	}
	if (ww == 0) {
		ww = "\u661f\u671f\u65e5";
	}
	if (ww == 1) {
		ww = "\u661f\u671f\u4e00";
	}
	if (ww == 2) {
		ww = "\u661f\u671f\u4e8c";
	}
	if (ww == 3) {
		ww = "\u661f\u671f\u4e09";
	}
	if (ww == 4) {
		ww = "\u661f\u671f\u56db";
	}
	if (ww == 5) {
		ww = "\u661f\u671f\u4e94";
	}
	if (ww == 6) {
		ww = "\u661f\u671f\u516d";
	}
	colorfoot = "</font>";
///	str = colorhead + yy + "-" + MM + "-" + dd + " " + hh + ":" + mm + ":" + ss + "  " + ww + colorfoot;
	str = yy + "-" + MM + "-" + dd + " " + hh + ":" + mm + ":" + ss;
	return (str);
}  

function getTodayDate(c)
{
  var today=new Date();
  var year=today.getYear();
  var month=today.getMonth();
  var day=today.getDate();
  return year+c+getTwoNum(month+1)+c+getTwoNum(day)
	
}

function getDayNum(endDate,startDate)
{
  //endDate,startDate��2002-12-18��ʽ  
       var  aDate,  oDate1,  oDate2,  iDays  
       aDate  =  endDate.split("-")  
       oDate1  =  new  Date(aDate[1]+ '-'  +  aDate[2]  +  '-'  +  aDate[0])  //ת��Ϊ12-18-2002��ʽ  
       aDate  =  startDate.split("-")  
       oDate2  =  new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0])  
 
	 iDays  =  parseInt((oDate1  -  oDate2)  /  1000  /  60  /  60  /24)    //�����ĺ�����ת��Ϊ����  
   //  iDays  =  parseInt(Math.abs(oDate1  -  oDate2)  /  1000  /  60  /  60  /24)    //�����ĺ�����ת��Ϊ����  
	 return  iDays  	
}

function getNextDate(startDate,sDay)
{
  var aDate  =  startDate.split("-")  
  oDate2= new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0])  
  oDate2.setDate(oDate2.getDate()+sDay);
  var year=oDate2.getYear();
  var month=oDate2.getMonth();
  var day=oDate2.getDate();
  return year+'-'+getTwoNum(month+1)+'-'+getTwoNum(day)
}


//当前日期
function showLocaleDate(objDate) {
	var str, colorhead, colorfoot;
	var yy = objDate.getYear();
	if (yy < 1900) {
		yy = yy + 1900;
	}
	var MM = objDate.getMonth() + 1;
	if (MM < 10) {
		MM = "0" + MM;
	}
	var dd = objDate.getDate();
	if (dd < 10) {
		dd = "0" + dd;
	}
	str = yy + "-" + MM + "-" + dd;
	return (str);
}  

//前一天
function showLocaleYesterday() {
	var curDate = new Date();
	var preDate = new Date(curDate.getTime() - 24 * 60 * 60 * 1000);  //前一天
	//var nextDate = new Date(curDate.getTime() + 24*60*60*1000);  //后一天 	
	var str, colorhead, colorfoot;
	var yy = preDate.getYear();
	if (yy < 1900) {
		yy = yy + 1900;
	}
	var MM = preDate.getMonth() + 1;
	if (MM < 10) {
		MM = "0" + MM;
	}
	var dd = preDate.getDate();
	if (dd < 10) {
		dd = "0" + dd;
	}
	str = yy + "-" + MM + "-" + dd;
	return (str);
}

