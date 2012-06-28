<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/tld/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<html>
	<head>
		<title>closeOpen</title>
	</head>
	<body>
		<div style="float: left;">
			<img src="./_img/closeSideBarPointer.gif" alt="Go"
				style="position: absolute; top: 40%; float: left;"
				onclick="closeOpen();">
		</div>
	</body>
	<script type="text/javascript">
	function closeOpen(){
		var leftWidth=parent.document.getElementById('middle');
		if(leftWidth.cols=="15%,1%,*"){				
			leftWidth.cols="1%,1%,*";
		}else{
			leftWidth.cols="15%,1%,*";
		}
	}	
</script>
</html>
