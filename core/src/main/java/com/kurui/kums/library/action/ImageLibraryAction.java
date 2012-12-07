package com.kurui.kums.library.action;

import java.io.InputStream;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;
import org.apache.struts.upload.FormFile;

import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.base.struts.BaseAction;
import com.kurui.kums.base.ui.inform.Inform;
import com.kurui.kums.library.ImageDependent;
import com.kurui.kums.library.ImageLibrary;
import com.kurui.kums.library.biz.ImageDependentBiz;
import com.kurui.kums.library.biz.ImageLibraryBiz;

public class ImageLibraryAction extends BaseAction {
	private ImageLibraryBiz imageLibraryBiz;
	private ImageDependentBiz imageDependentBiz;

	public ActionForward insert(ActionMapping mapping, ActionForm form,
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
	
	//保存图片和业务表的关联
	public ActionForward insertDependent(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ImageLibrary theForm = (ImageLibrary) form;

		ImageLibrary imageLibrary=new ImageLibrary();
		imageLibrary.setName(theForm.getName());
		imageLibrary.setMemo(theForm.getMemo());
		imageLibrary.setType(ImageLibrary.TYPE_1);
		imageLibrary.setStatus(ImageLibrary.STATES_1);
		imageLibrary.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		
		String forwardPage = this.saveFile(theForm,imageLibrary, request);
		
		
		ImageDependent imageDependent=new ImageDependent();
		imageDependent.setImageLibrary(imageLibrary);
		imageDependent.setTableName(theForm.getTableName());
		imageDependent.setRowId(theForm.getRowId());
		imageDependent.setType(ImageDependent.TYPE_1);
		imageDependent.setStatus(ImageDependent.STATES_1);
		imageDependent.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		try {
			imageDependentBiz.save(imageDependent);
		} catch (AppException e) {
			e.printStackTrace();
		}
		
		return mapping.findForward(forwardPage);
	}

	private String saveFile(ImageLibrary theForm,ImageLibrary imageLibrary, HttpServletRequest request) {
		FormFile file = theForm.getUploadFile();

		try {
			// only write files out that are less than 1MB
			if (file.getFileSize() < (4 * 1024000)) {
				InputStream in = file.getInputStream();						
				 
				byte[] buf = new byte[in.available()];  
				in.read(buf); //将文件读入byte[]
				in.close();  
				
				imageLibrary.setContent(buf);
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
			request.setAttribute("imageLibrary", imageLibrary);
			return "viewImageLibrary";
		} catch (Exception ex) {
			Inform inf = new Inform();
			inf.setMessage("上传异常:" + ex);
			inf.setBack(true);
			request.setAttribute("inf", inf);
			return "inform";
		}

	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws AppException {
		ImageLibrary theForm = (ImageLibrary) form;

		long imageLibraryId=theForm.getId();
		ImageLibrary imageLibrary=imageLibraryBiz.getImageLibraryById(imageLibraryId);
		
		imageLibrary.setName(theForm.getName());
		imageLibrary.setMemo(theForm.getMemo());
		imageLibrary.setType(ImageLibrary.TYPE_1);
		imageLibrary.setStatus(ImageLibrary.STATES_1);
		imageLibrary.setUpdateTime(new Timestamp(System.currentTimeMillis()));

		return redirect(imageLibrary);
	}
	
	public ActionRedirect redirect(ImageLibrary imageLibrary) {
		ActionRedirect redirect = new ActionRedirect(
				"/library/imageLibraryList.do");
		redirect.addParameter("thisAction", "view");
		redirect.addParameter("id", imageLibrary.getId());
		return redirect;
	}

	public void setImageLibraryBiz(ImageLibraryBiz imageLibraryBiz) {
		this.imageLibraryBiz = imageLibraryBiz;
	}

	public void setImageDependentBiz(ImageDependentBiz imageDependentBiz) {
		this.imageDependentBiz = imageDependentBiz;
	}
	
	
	
	
}
