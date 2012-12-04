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
		<script src="../_js/calendar/WdatePicker.js" type="text/javascript"></script>
		<script type="text/javascript">
		
	</script>
	</head>
	<body>
		<div id="mainContainer">
			<div id="container">
				<html:form action="/market/priceIndexList.do">
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
									<c:param name="title1" value="财务管理" />
									<c:param name="title2" value="物价指数图表" />
								</c:import>

								<table width="100%" cellpadding="0" cellspacing="0" border="0"
									class="dataList">
									<div>
										<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
											codebase="../cabs/flash/swflash.cab#version=7,0,19,0"
											width="740" height="260">
											<param name="movie" value="./swf/open-flash-chart.swf" />
											<param name="FlashVars"
												value="data=./swf/draw.html?arg=3|2011-08-07,,,,,2011-08-12,,,,,2011-08-17,,,,,2011-08-22,,,,,2011-08-27,,,,,2011-09-01,,,,,|PV,UV,IP|dot,dot,dot|0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,5,12,6,7,1,1,2,0,0,3,0|0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,11,5,5,1,1,1,0,0,2,0|0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,11,5,5,1,1,1,0,0,2,0|12" />
											<param name="quality" value="high" />
											<param name="WMODE" value="transparent" />
											<embed src="./swf/open-flash-chart.swf" WMODE="transparent"
												FlashVars="data=./swf/draw.html?arg=3|2011-08-07,,,,,2011-08-12,,,,,2011-08-17,,,,,2011-08-22,,,,,2011-08-27,,,,,2011-09-01,,,,,|PV,UV,IP|dot,dot,dot|0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,5,12,6,7,1,1,2,0,0,3,0|0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,11,5,5,1,1,1,0,0,2,0|0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,11,5,5,1,1,1,0,0,2,0|12"
												quality="high"
												pluginspage="http://www.macromedia.com/go/getflashplayer"
												type="application/x-shockwave-flash" width="740"
												height="260"></embed>
										</object>
									</div>
									</div>
								</table>
							</td>
						</tr>
					</table>
				</html:form>
			</div>
		</div>
	</body>
</html>