<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/tld/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>

<html>
	<head>
		<title>left</title>
		<link href="../_css/reset.css" rel="stylesheet" type="text/css" />
		<link href="../_css/global.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" language="javascript"
			src="./_js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript" language="javascript"
			src="./_js/goto.js"></script>
	</head>
	<body>
		<div id="mainContainer">
			<div class="fixedSideBar"></div>
			<div id="sideBar">
				<div class="sideBarItem webAdmin">
					<span class="title"><a href="../workflow/workflowList.do"
						target="mainFrame">流程建模</a> </span>
					<span class="title"><a href="../workflow/workflowList.do"
						target="mainFrame">流程引擎</a> </span>
					<span class="title"><a
						href="../workflow/workflowList.do?thisAction=listTask"
						target="mainFrame">流程监控</a> </span>

				</div>
			</div>
			<div class="closeSiseBar">
				<span class="btn"></span>
			</div>
		</div>
	</body>
</html>
