package com.kurui.kums.library.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.base.struts.BaseAction;
import com.kurui.kums.base.ui.inform.Inform;
import com.kurui.kums.library.ImageLibrary;
import com.kurui.kums.library.ImageLibraryListForm;
import com.kurui.kums.library.biz.ImageLibraryBiz;

public class ImageLibraryListAction extends BaseAction {
	private ImageLibraryBiz imageLibraryBiz;

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		ImageLibraryListForm imageLibraryListForm = (ImageLibraryListForm) form;
		if (imageLibraryListForm == null) {
			imageLibraryListForm = new ImageLibraryListForm();
		}
		try {
			imageLibraryListForm.setList(imageLibraryBiz.list(imageLibraryListForm));
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("imageLibraryListForm", imageLibraryListForm);
				
		return mapping.findForward("listImageLibrary");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		ImageLibraryListForm imageLibraryListForm = (ImageLibraryListForm) form;
		try {
			Long imageLibraryId = imageLibraryListForm.getId();
			ImageLibrary imageLibrary = imageLibraryBiz.getImageLibraryById(imageLibraryId);
			request.setAttribute("imageLibrary", imageLibrary);
		} catch (Exception e) {
			e.printStackTrace();
		}
		forwardPage = "viewImageLibrary";
		return mapping.findForward(forwardPage);

	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		ImageLibraryListForm imageLibraryListForm = (ImageLibraryListForm) form;

		ImageLibrary imageLibrary = new ImageLibrary();
		imageLibrary.setThisAction("insert");
	
		imageLibrary.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		imageLibrary.setType(ImageLibrary.TYPE_1);
		imageLibrary.setStatus(ImageLibrary.STATES_1);
		
		request.setAttribute("imageLibrary", imageLibrary);
		
		
		String forwardPage = "editImageLibrary";

		return mapping.findForward(forwardPage);
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		ImageLibraryListForm imageLibraryListForm = (ImageLibraryListForm) form;

		long imageLibraryId = imageLibraryListForm.getId();
		if (imageLibraryId < 1) {
			imageLibraryId = imageLibraryListForm.getSelectedItems()[0];
		}

		if (imageLibraryId > 0) {
			ImageLibrary imageLibrary = imageLibraryBiz.getImageLibraryById(imageLibraryId);
			imageLibrary.setThisAction("update");
			request.setAttribute("imageLibrary", imageLibrary);
			imageLibrary.setType(ImageLibrary.TYPE_1);
			imageLibrary.setStatus(ImageLibrary.STATES_1);
		} else {
			request.setAttribute("imageLibrary", new ImageLibrary());
		}
		
		return mapping.findForward("editImageLibrary");
	}



	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		ImageLibraryListForm imageLibraryListForm = (ImageLibraryListForm) form;
		// String forwardPage = "";
		long id = 0;
		Inform inf = new Inform();
		try {
			for (int i = 0; i < imageLibraryListForm.getSelectedItems().length; i++) {
				id = imageLibraryListForm.getSelectedItems()[i];
				if (id > 0) {
					imageLibraryBiz.deleteImageLibrary(id);
				}
			}
			return list(mapping, form, request, response);
		} catch (Exception ex) {
			inf.setMessage("删除失败" + ex.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}

	public void setImageLibraryBiz(ImageLibraryBiz imageLibraryBiz) {
		this.imageLibraryBiz = imageLibraryBiz;
	}


}