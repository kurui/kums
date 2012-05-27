package com.kurui.kums.market.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;

import com.kurui.kums.base.BaseAction;
import com.kurui.kums.base.Inform;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.market.SaleEvent;
import com.kurui.kums.market.biz.SaleEventBiz;
import com.kurui.kums.right.UserRightInfo;

public class SaleEventAction extends BaseAction {
	private SaleEventBiz saleEventBiz;

	public ActionForward insert(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		SaleEvent saleEventForm = (SaleEvent) form;
		Inform inf = new Inform();
		UserRightInfo uri = (UserRightInfo) request.getSession().getAttribute(
				"URI");
		try {
			SaleEvent saleEvent = new SaleEvent();

			saleEvent.setName(saleEventForm.getName());
			saleEvent.setMemo(saleEventForm.getMemo());
			saleEvent.setType(saleEventForm.getType());
			saleEvent.setStatus(saleEventForm.getStatus());
			long num = saleEventBiz.save(saleEvent);

			if (num > 0) {
				return new ActionRedirect(
						"/market/saleEventList.do?thisAction=list");
			} else {
				inf.setMessage("您添加数据失败！");
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
		SaleEvent saleEventForm = (SaleEvent) form;
		Inform inf = new Inform();
		UserRightInfo uri = (UserRightInfo) request.getSession().getAttribute(
				"URI");
		try {
			if (saleEventForm.getId() > 0) {
				SaleEvent saleEvent = saleEventBiz
						.getSaleEventById(saleEventForm.getId());
				saleEvent.setName(saleEventForm.getName());
				saleEvent.setMemo(saleEventForm.getMemo());
				saleEvent.setType(saleEventForm.getType());
				saleEvent.setStatus(saleEventForm.getStatus());

				long flag = saleEventBiz.update(saleEvent);

				if (flag > 0) {
					return new ActionRedirect(
							"/market/saleEventList.do?thisAction=list");
				} else {
					inf.setMessage("您修改数据失败！");
				}
			} else {
				inf.setMessage("缺少ID");
			}
		} catch (Exception e) {
			e.printStackTrace();
			inf.setMessage("异常:" + e.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}

	public void setSaleEventBiz(SaleEventBiz saleEventBiz) {
		this.saleEventBiz = saleEventBiz;
	}
}