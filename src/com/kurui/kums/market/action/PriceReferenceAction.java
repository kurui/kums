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
import com.kurui.kums.market.PriceReference;
import com.kurui.kums.market.biz.PriceReferenceBiz;
import com.kurui.kums.right.UserRightInfo;

public class PriceReferenceAction extends BaseAction {
	private PriceReferenceBiz priceReferenceBiz;

	public ActionForward insert(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		PriceReference pform = (PriceReference) form;
		Inform inf = new Inform();
		UserRightInfo uri = (UserRightInfo) request.getSession().getAttribute(
				"URI");
		try {
			PriceReference priceReference = new PriceReference();
			priceReference.setName(pform.getName());
			priceReference.setCode(pform.getCode());
			priceReference.setMoney(pform.getMoney());
			priceReference.setUnit(pform.getUnit());
			priceReference.setMemo(pform.getMemo());
			priceReference.setType(pform.getType());
			priceReference.setStatus(pform.getStatus());

			long num = priceReferenceBiz.save(priceReference);

			if (num > 0) {
				return redirect(priceReference);
			} else {
				inf.setMessage("您添加参照系数失败！");
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
		PriceReference pform = (PriceReference) form;
		Inform inf = new Inform();
		UserRightInfo uri = (UserRightInfo) request.getSession().getAttribute(
				"URI");
		try {
			if (pform.getId() > 0) {
				PriceReference priceReference = priceReferenceBiz
						.getPriceReferenceById(pform.getId());
				priceReference.setName(pform.getName());
				priceReference.setCode(pform.getCode());
				priceReference.setMoney(pform.getMoney());
				priceReference.setUnit(pform.getUnit());
				priceReference.setMemo(pform.getMemo());
				priceReference.setType(pform.getType());
				priceReference.setStatus(pform.getStatus());

				long flag = priceReferenceBiz.update(priceReference);

				if (flag > 0) {
					return redirect(priceReference);
				} else {
					inf.setMessage("您修改参照系数失败！");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			inf.setMessage("异常:" + e.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}
	
	public ActionRedirect redirect(PriceReference priceReference) {
		ActionRedirect redirect = new ActionRedirect(
				"/market/priceReferenceList.do");
		redirect.addParameter("thisAction", "view");
		redirect.addParameter("id", priceReference.getId());
		return redirect;
	}

	public void setPriceReferenceBiz(PriceReferenceBiz priceReferenceBiz) {
		this.priceReferenceBiz = priceReferenceBiz;
	}
}