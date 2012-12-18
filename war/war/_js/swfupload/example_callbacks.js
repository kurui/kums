
function fileQueued(file, queuelength) {
	var listingfiles = document.getElementById("SWFUploadFileListingFiles");

	if(!listingfiles.getElementsByTagName("ul")[0]) {
		
		var info = document.createElement("h4");
		info.appendChild(document.createTextNode("�ļ��б�"));
		
		listingfiles.appendChild(info);
		
		var ul = document.createElement("ul")
		listingfiles.appendChild(ul);
	}
	
	listingfiles = listingfiles.getElementsByTagName("ul")[0];
	
	var li = document.createElement("li");
	li.id = file.id;
	li.className = "SWFUploadFileItem";
	li.innerHTML = file.name + " <span class='progressBar' id='" + file.id + "progress'></span><a id='" + file.id + "deletebtn' class='cancelbtn' href='javascript:swfu.cancelFile(\"" + file.id + "\");'><!-- IE --></a>";

	listingfiles.appendChild(li);
	
	var queueinfo = document.getElementById("queueinfo");
	queueinfo.innerHTML = queuelength + " �ļ�ѡ��";
	document.getElementById(swfu.movieName + "UploadBtn").style.display = "block";
	document.getElementById("cancelqueuebtn").style.display = "block";
}

function uploadFileCancelled(file, queuelength) {
	var li = document.getElementById(file.id);
	li.innerHTML = file.name + " - cancelled";
	li.className = "SWFUploadFileItem uploadCancelled";
	var queueinfo = document.getElementById("queueinfo");
	queueinfo.innerHTML = queuelength + " �ļ�ѡ��";
}

function uploadFileStart(file, position, queuelength) {
	var div = document.getElementById("queueinfo");
	div.innerHTML = "�ϴ� ��:" + position + " / " + queuelength;

	var li = document.getElementById(file.id);
	li.className += " fileUploading";
}

function uploadProgress(file, bytesLoaded) {

	var progress = document.getElementById(file.id + "progress");
	var percent = Math.ceil((bytesLoaded / file.size) * 200)
	progress.style.background = "#f0f0f0 url(/images/progressbar.png) no-repeat -" + (200 - percent) + "px 0";
}

function uploadError(errno) {
	// SWFUpload.debug(errno);
}

function uploadFileComplete(file) {
	var li = document.getElementById(file.id);
	li.className = "SWFUploadFileItem uploadCompleted";
}

function cancelQueue() {
	swfu.cancelQueue();
	document.getElementById(swfu.movieName + "UploadBtn").style.display = "none";
	document.getElementById("cancelqueuebtn").style.display = "none";
}

function uploadQueueComplete(file) {
	var div = document.getElementById("queueinfo");
	div.innerHTML = "�ϴ��ɹ�!"
	document.getElementById("cancelqueuebtn").style.display = "none";
	//startDoParseFile();
}