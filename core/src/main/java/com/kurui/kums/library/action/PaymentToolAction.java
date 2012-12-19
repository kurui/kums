package com.kurui.kums.library.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;

import com.kurui.kums.base.struts.BaseAction;
import com.kurui.kums.base.KumsDataStoreListener;
import com.kurui.kums.base.ui.inform.Inform;
import com.kurui.kums.base.threads.MainTask;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.library.biz.SysInitBiz;
import com.kurui.kums.library.PaymentTool;
import com.kurui.kums.library.biz.PaymentToolBiz;

public class PaymentToolAction extends BaseAction {
	private PaymentToolBiz paymentToolBiz;
	private SysInitBiz sysInitBiz;

	public ActionForward insert(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
//		String forwardPage = "";
		PaymentTool paymentTool = (PaymentTool) form;
		Inform inf = new Inform();
		try {
			PaymentTool pmentTool = new PaymentTool();
			pmentTool.setName(paymentTool.getName());
			pmentTool.setType(paymentTool.getType());
			pmentTool.setStatus(paymentTool.getStatus());
			long num = paymentToolBiz.save(pmentTool);
			// --更新静态库
			KumsDataStoreListener listener = new KumsDataStoreListener(sysInitBiz,
					"PaymentTool");
			MainTask.put(listener);
			//
			if (num > 0) {
				return new ActionRedirect(
						"/library/paymentToolList.do?thisAction=list");
			} else {
				inf.setMessage("您添加支付工具失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			inf.setMessage("异常:"+e.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}

	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		PaymentTool paymentTool = (PaymentTool) form;
		Inform inf = new Inform();
		try {
			if (paymentTool.getId() > 0) {
				PaymentTool pTool = paymentToolBiz
						.getPaymentToolById(paymentTool.getId());
				pTool.setName(paymentTool.getName());
				pTool.setType(paymentTool.getType());
				pTool.setStatus(paymentTool.getStatus());
				long flag = paymentToolBiz.update(pTool);
				// --更新静态库
				KumsDataStoreListener listener = new KumsDataStoreListener(sysInitBiz,
						"PaymentTool");
				MainTask.put(listener);
				//
				if (flag > 0) {
					return new ActionRedirect(
							"/library/paymentToolList.do?thisAction=list");
				} else {
					inf.setMessage("您改支付工具失败！");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			inf.setMessage("异常:"+e.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}

	public void setPaymentToolBiz(PaymentToolBiz paymentToolBiz) {
		this.paymentToolBiz = paymentToolBiz;
	}

	public void setSysInitBiz(SysInitBiz sysInitBiz) {
		this.sysInitBiz = sysInitBiz;
	}
}