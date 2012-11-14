package com.kurui.kums.transaction.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;

import com.kurui.kums.base.struts.BaseAction;
import com.kurui.kums.base.ui.inform.Inform;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.transaction.PaymentTool;
import com.kurui.kums.transaction.PaymentToolListForm;
import com.kurui.kums.transaction.biz.PaymentToolBiz;

public class PaymentToolListAction extends BaseAction {
	private PaymentToolBiz paymentToolBiz;

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		PaymentToolListForm paymentToolListForm = (PaymentToolListForm) form;
		if (paymentToolListForm == null) {
			paymentToolListForm = new PaymentToolListForm();
		}
		try {
			paymentToolListForm.setList(paymentToolBiz
					.list(paymentToolListForm));
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("paymentToolListForm", paymentToolListForm);
		return mapping.findForward("listPaymentTool");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		try {
			PaymentToolListForm paymentToolListForm = (PaymentToolListForm) form;
			long paymentToolId = paymentToolListForm.getId();
			PaymentTool paymentTool = paymentToolBiz
					.getPaymentToolById(paymentToolId);
			request.setAttribute("paymentTool", paymentTool);

		} catch (Exception e) {
			e.printStackTrace();
		}
		forwardPage = "viewPaymentTool";
		return mapping.findForward(forwardPage);
	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		PaymentTool paymentTool = new PaymentTool();
		paymentTool.setThisAction("insert");
		request.setAttribute("paymentTool", paymentTool);
		String forwardPage = "editpaymentTool";
		return mapping.findForward(forwardPage);
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		PaymentToolListForm paymentToolListForm = (PaymentToolListForm) form;
		long paymentToolId = paymentToolListForm.getSelectedItems()[0];
		if (paymentToolId > 0) {
			PaymentTool paymentTool = paymentToolBiz
					.getPaymentToolById(paymentToolId);
			paymentTool.setThisAction("update");
			request.setAttribute("paymentTool", paymentTool);
		} else {
			request.setAttribute("paymentTool", new PaymentTool());
		}
		return mapping.findForward("editpaymentTool");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		PaymentToolListForm paymentToolListForm = (PaymentToolListForm) form;
		String forwardPage = "";
		Inform inf = new Inform();
		try {
			for (int i = 0; i < paymentToolListForm.getSelectedItems().length; i++) {
				long id = paymentToolListForm.getSelectedItems()[i];
				if (id > 0) {
					paymentToolBiz.deletePaymentTool(id);
				} else {
					inf.setMessage("不能获取ID,删除失败!");
				}
			}
			return list(mapping, form, request, response);
		} catch (Exception e) {
			e.printStackTrace();
			inf.setMessage("异常:"+e.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}

	public void setPaymentToolBiz(PaymentToolBiz paymentToolBiz) {
		this.paymentToolBiz = paymentToolBiz;
	}
}