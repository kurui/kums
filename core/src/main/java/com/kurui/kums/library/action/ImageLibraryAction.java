package com.kurui.kums.library.action;

import java.io.InputStream;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.kurui.kums.base.struts.BaseAction;
import com.kurui.kums.base.ui.inform.Inform;
import com.kurui.kums.library.ImageLibrary;
import com.kurui.kums.library.biz.ImageLibraryBiz;

public class ImageLibraryAction extends BaseAction {
	private ImageLibraryBiz imageLibraryBiz;

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ImageLibrary theForm = (ImageLibrary) form;

		ImageLibrary imageLibrary=new ImageLibrary();
		imageLibrary.setName(theForm.getName());
		imageLibrary.setMemo(theForm.getMemo());
		imageLibrary.setType(ImageLibrary.TYPE_1);
		imageLibrary.setStatus(ImageLibrary.STATES_1);
		imageLibrary.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		
		String forwardPage = this.saveFile(theForm,imageLibrary, request);

		return mapping.findForward(forwardPage);
	}

	private String saveFile(ImageLibrary theForm,ImageLibrary imageLibrary, HttpServletRequest request) {
		FormFile file = theForm.getUploadFile();

		try {

			// only write files out that are less than 1MB
			if (file.getFileSize() < (4 * 1024000)) {
				InputStream stream = file.getInputStream();		
				
				 
				byte[] buf = new byte[10240];  
				int len;  
				while((len = stream.read(buf)) > 0)  
				{  
					imageLibrary.setContent(buf);
				}  
				stream.close();  
				
				imageLibraryBiz.save(imageLibrary);

			} else {
				Inform inf = new Inform();
				inf.setMessage("上传的文件不能大于" + "4M");
				inf.setBack(true);
				request.setAttribute("inf", inf);
				return "inform";
			}

			// close the stream
			theForm.setThisAction("");
			request.setAttribute("uf", theForm);
			return "editImageLibrary";
		} catch (Exception ex) {
			Inform inf = new Inform();
			inf.setMessage("上传异常:" + ex);
			inf.setBack(true);
			request.setAttribute("inf", inf);
			return "inform";
		}

	}

	public void setImageLibraryBiz(ImageLibraryBiz imageLibraryBiz) {
		this.imageLibraryBiz = imageLibraryBiz;
	}
	
	
}
