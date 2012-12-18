<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%
	String path = request.getContextPath();
%>
<html>
<head>
<title>SWFUpload Demos - SWFObject Demo</title>
<link  rel="stylesheet" type="text/css"  href="../_css/swfupload.css"/>
<script type="text/javascript" src="<%=path%>/_js/swfupload/swfupload.swfobject.js"></script>

<script type="text/javascript"
	src="<%=path%>/_js/swfupload/swfupload.js"></script>
<script type="text/javascript"
	src="<%=path%>/_js/swfupload/swfupload.queue.js"></script>
<script type="text/javascript"
	src="<%=path%>/_js/swfupload/fileprogress.js"></script>
<script type="text/javascript" src="<%=path%>/_js/swfupload/handlers.js"></script>
<script type="text/javascript">
var swfu;

window.onload = function () {

	var settings = {
		upload_url : "http://www.swfupload.org/upload.php",
	flash_url : "http://localhost:8080/kums/demo/swfupload.swf",

	file_post_name : "Filedata",
	post_params : {
		"post_param_name_1" : "post_param_value_1",
		"post_param_name_2" : "post_param_value_2",
		"post_param_name_n" : "post_param_value_n"
	},
	use_query_string : false,
	requeue_on_error : false,
	http_success : [201, 202],
	assume_success_timeout : 0,
	file_types : "*.jpg;*.gif",
	file_types_description: "Web Image Files",
	file_size_limit : "1024",
	file_upload_limit : 10,
	file_queue_limit : 2,

	debug : false,
	
	prevent_swf_caching : false,
	preserve_relative_urls : false,
	
	button_placeholder_id : "SWFUploadFileListingFiles",
	button_image_url : "http://www.swfupload.org/button_sprite.png",
	button_width : 61,
	button_height : 22,
	button_text : "<b>Click</b> <span class='redText'>here</span>",
	button_text_style : ".redText { color: #FF0000; }",
	button_text_left_padding : 3,
	button_text_top_padding : 2,
	button_action : SWFUpload.BUTTON_ACTION.SELECT_FILES,
	button_disabled : false,
	button_cursor : SWFUpload.CURSOR.HAND,
	button_window_mode : SWFUpload.WINDOW_MODE.TRANSPARENT,
	
	//swfupload_loaded_handler : swfupload_loaded_function,
	//file_dialog_start_handler : file_dialog_start_function,
	//file_queued_handler : file_queued_function,
	//file_queue_error_handler : file_queue_error_function,
	//file_dialog_complete_handler : file_dialog_complete_function,
	//upload_start_handler : upload_start_function,
	//upload_progress_handler : upload_progress_function,
	//upload_error_handler : upload_error_function,
	//upload_success_handler : upload_success_function,
	//upload_complete_handler : upload_complete_function,
	//debug_handler : debug_function,
	
	custom_settings : {
		custom_setting_1 : "custom_setting_value_1",
		custom_setting_2 : "custom_setting_value_2",
		custom_setting_n : "custom_setting_value_n",
	}

	};

	swfu = new SWFUpload(settings);
}

</script>
</head>
<body><p>请选择文件</p>
	<div id="SWFUploadTarget">
		<form action="upload.jsp" method="post" enctype="multipart/form-data">
		</form>
	</div>
	<h4 id="queueinfo">未选择文件</h4>
	<div id="SWFUploadFileListingFiles"></div>
	<br class="clr" />
	<a class="swfuploadbtn" id="cancelqueuebtn"
		href="#">删除所有已选择文件</a>
</body>
</html>
