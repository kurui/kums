package com.kurui.kums.transaction.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;

import com.kurui.kums.base.BaseAction;
import com.kurui.kums.base.KumsDataStoreListener;
import com.kurui.kums.base.Inform;
import com.kurui.kums.base.MainTask;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.right.UserRightInfo;
import com.kurui.kums.system.biz.SysInitBiz;
import com.kurui.kums.transaction.Platform;
import com.kurui.kums.transaction.biz.PlatformBiz;

public class PlatformAction extends BaseAction {
	private PlatformBiz platformBiz;
	private SysInitBiz sysInitBiz;

	public ActionForward insert(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		Platform platform = (Platform) form;
		Inform inf = new Inform();
		UserRightInfo uri = (UserRightInfo) request.getSession().getAttribute("URI");
		try {
			Platform pform = new Platform();
			pform.setName(platform.getName());
			pform.setType(platform.getType());
			pform.setDescription(platform.getDescription());
			pform.setUserName(uri.getUser().getUserName());
			pform.setUpdateTime(new Timestamp(System.currentTimeMillis()));
			pform.setStatus(platform.getStatus());
			long num = platformBiz.save(pform);
			// --更新静态库
			KumsDataStoreListener listener = new KumsDataStoreListener(
					sysInitBiz, "Platform");
			MainTask.put(listener);
			//
			if (num > 0) {
				return redirect(pform);
			} else {
				inf.setMessage("您添加交易平台数据失败！");
				inf.setBack(true);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			inf.setBack(true);
		}
		request.setAttribute("inf", inf);
		forwardPage = "inform";
		return (mapping.findForward(forwardPage));
	}

	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		Platform platform = (Platform) form;
		Inform inf = new Inform();
		UserRightInfo uri = (UserRightInfo) request.getSession().getAttribute("URI");
		try {
			if (platform.getId() > 0) {
				Platform pform = platformBiz.getPlatformById(platform.getId());
				pform.setName(platform.getName());
				pform.setType(platform.getType());
				pform.setDescription(platform.getDescription());
				pform.setUserName(uri.getUser().getUserName());
				pform.setUpdateTime(new Timestamp(System.currentTimeMillis()));
				pform.setStatus(platform.getStatus());
				long flag = platformBiz.update(pform);
				// --更新静态库
				KumsDataStoreListener listener = new KumsDataStoreListener(
						sysInitBiz, "Platform");
				MainTask.put(listener);
				//
				if (flag > 0) {
					return redirect(pform);
				} else {
					inf.setMessage("您改交易平台数据失败！");
					inf.setBack(true);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			inf.setBack(true);
		}
		request.setAttribute("inf", inf);
		String forwardPage = "inform";
		return (mapping.findForward(forwardPage));
	}
	
	public ActionRedirect redirect(Platform pform) {
		ActionRedirect redirect = new ActionRedirect(
				"/transaction/platformList.do");

		redirect.addParameter("thisAction", "view");
		redirect.addParameter("id", pform.getId());
		return redirect;
	}


	public void setSysInitBiz(SysInitBiz sysInitBiz) {
		this.sysInitBiz = sysInitBiz;
	}

	public void setPlatformBiz(PlatformBiz platformBiz) {
		this.platformBiz = platformBiz;
	}
}