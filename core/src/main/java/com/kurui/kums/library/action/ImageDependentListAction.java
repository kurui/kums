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
import com.kurui.kums.library.ImageDependent;
import com.kurui.kums.library.ImageDependentListForm;
import com.kurui.kums.library.biz.ImageDependentBiz;

public class ImageDependentListAction extends BaseAction {
	private ImageDependentBiz imageDependentBiz;

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		ImageDependentListForm imageDependentListForm = (ImageDependentListForm) form;
		if (imageDependentListForm == null) {
			imageDependentListForm = new ImageDependentListForm();
		}
		try {
			imageDependentListForm.setList(imageDependentBiz
					.list(imageDependentListForm));
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("imageDependentListForm", imageDependentListForm);

		return mapping.findForward("listImageDependent");
	}

	
	
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		ImageDependentListForm imageDependentListForm = (ImageDependentListForm) form;

		ImageDependent imageDependent = new ImageDependent();
		imageDependent.setThisAction("insert");

		imageDependent.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		imageDependent.setType(ImageDependent.TYPE_1);
		imageDependent.setStatus(ImageDependent.STATES_1);

		request.setAttribute("imageDependent", imageDependent);

		String forwardPage = "editImageDependent";

		return mapping.findForward(forwardPage);
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		ImageDependentListForm imageDependentListForm = (ImageDependentListForm) form;

		long imageDependentId = imageDependentListForm.getId();
		if (imageDependentId < 1) {
			imageDependentId = imageDependentListForm.getSelectedItems()[0];
		}

		if (imageDependentId > 0) {
			ImageDependent imageDependent = imageDependentBiz
					.getImageDependentById(imageDependentId);
			imageDependent.setThisAction("update");
			request.setAttribute("imageDependent", imageDependent);
			imageDependent.setType(ImageDependent.TYPE_1);
			imageDependent.setStatus(ImageDependent.STATES_1);
		} else {
			request.setAttribute("imageDependent", new ImageDependent());
		}

		return mapping.findForward("editImageDependent");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		ImageDependentListForm imageDependentListForm = (ImageDependentListForm) form;
		// String forwardPage = "";
		long id = 0;
		Inform inf = new Inform();
		try {
			for (int i = 0; i < imageDependentListForm.getSelectedItems().length; i++) {
				id = imageDependentListForm.getSelectedItems()[i];
				if (id > 0) {
					imageDependentBiz.deleteImageDependent(id);
				}
			}
			return list(mapping, form, request, response);
		} catch (Exception ex) {
			inf.setMessage("删除失败" + ex.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}


	public void setImageDependentBiz(ImageDependentBiz imageDependentBiz) {
		this.imageDependentBiz = imageDependentBiz;
	}

}