package com.kurui.kums.agent.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;

import com.kurui.kums.base.BaseAction;
import com.kurui.kums.base.Inform;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.right.UserRightInfo;
import com.kurui.kums.agent.DirectLevel;
import com.kurui.kums.agent.biz.DirectLevelBiz;

public class DirectLevelAction extends BaseAction {
	private DirectLevelBiz directLevelBiz;

	public ActionForward insert(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		DirectLevel directLevelForm = (DirectLevel) form;
		Inform inf = new Inform();
		UserRightInfo uri = (UserRightInfo) request.getSession().getAttribute(
				"URI");
		try {
			DirectLevel directLevel = new DirectLevel();
			directLevel.setName(directLevelForm.getName());
			directLevel.setDirectDiscount(directLevelForm.getDirectDiscount());
			directLevel.setNormalDiscount(directLevelForm.getNormalDiscount());
			directLevel.setDirectorDiscount(directLevelForm.getDirectorDiscount());
			directLevel.setManagerDiscount(directLevelForm.getManagerDiscount());
			directLevel.setAdManagerDiscount(directLevelForm.getAdManagerDiscount());
			directLevel.setSeqIndex(directLevelForm.getSeqIndex());
			directLevel.setType(directLevelForm.getType());
			directLevel.setStatus(directLevelForm.getStatus());
			
			long num = directLevelBiz.save(directLevel);

			if (num > 0) {
				ActionRedirect redirect = new ActionRedirect(
						"/agent/directLevelList.do?thisAction=list");
				return redirect;
			} else {
				inf.setMessage("您添加数据异常！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			inf.setMessage("异常:" + e.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}

	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		DirectLevel directLevelForm = (DirectLevel) form;
		
		Inform inf = new Inform();
		UserRightInfo uri = (UserRightInfo) request.getSession().getAttribute(
				"URI");
		try {
			long directLevelId = directLevelForm.getId();
			if (directLevelId > 0) {
				DirectLevel directLevel = directLevelBiz
						.getDirectLevelById(directLevelId);
				directLevel.setName(directLevelForm.getName());
				directLevel.setDirectDiscount(directLevelForm
						.getDirectDiscount());
				directLevel.setNormalDiscount(directLevelForm.getNormalDiscount());
				directLevel.setDirectorDiscount(directLevelForm.getDirectorDiscount());
				directLevel.setManagerDiscount(directLevelForm.getManagerDiscount());
				directLevel.setAdManagerDiscount(directLevelForm.getAdManagerDiscount());
				directLevel.setSeqIndex(directLevelForm.getSeqIndex());
				directLevel.setType(directLevelForm.getType());
				directLevel.setStatus(directLevelForm.getStatus());
				

				long flag = directLevelBiz.update(directLevel);

				if (flag > 0) {
					return new ActionRedirect(
							"/agent/directLevelList.do?thisAction=list");
				} else {
					inf.setMessage("修改数据异常!");
				}
			} else {
				inf.setMessage("缺少directLevelId");
			}
		} catch (Exception e) {
			e.printStackTrace();
			inf.setMessage("异常:" + e.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}

	public void setDirectLevelBiz(DirectLevelBiz directLevelBiz) {
		this.directLevelBiz = directLevelBiz;
	}

}