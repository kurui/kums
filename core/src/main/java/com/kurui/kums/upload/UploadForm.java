package com.kurui.kums.upload;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class UploadForm extends ActionForm implements Cloneable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected FormFile uploadFile;
	protected int objId = 0;
	protected String path = "";
	protected String thisAction = "";
	protected String fileKey = "";
	protected String listAttachName = "listattch";
	protected ArrayList listAttach = new ArrayList();

	public String getListAttachName() {
		return listAttachName;
	}

	public void setListAttachName(String listAttachName) {
		this.listAttachName = listAttachName;
	}

	public ArrayList getListAttach() {
		return listAttach;
	}

	public int getListAttachNum() {

		if (listAttach != null)
			return listAttach.size();
		else
			return 0;
	}

	public void setListAttach(ArrayList listAttach) {
		this.listAttach = listAttach;
	}

	public void setUploadFile(FormFile uploadFile) {
		this.uploadFile = uploadFile;
	}

	public FormFile getUploadFile() {
		return uploadFile;
	}

	public int getObjId() {
		return objId;
	}

	public void setObjId(int objId) {
		this.objId = objId;
	}

	public String getPath() {
		return path;

	}

	public void setPath(String path) {
		this.path = path;

	}

	public String getThisAction() {
		return thisAction;
	}

	public void setThisAction(String thisAction) {
		this.thisAction = thisAction;
	}

	public String getFileKey() {
		return fileKey;
	}

	public void setFileKey(String fileKey) {
		this.fileKey = fileKey;
	}

	public Object clone() {
		Object o = null;
		try {
			o = super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return o;
	}

}