package com.kurui.kums.market.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.kurui.kums.base.BaseAction;
import com.kurui.kums.base.Inform;
import com.kurui.kums.base.PerformListener;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.market.PriceIndex;
import com.kurui.kums.market.PriceReference;
import com.kurui.kums.market.PriceReferenceListForm;
import com.kurui.kums.market.biz.PriceIndexBiz;
import com.kurui.kums.market.biz.PriceReferenceBiz;

public class PriceReferenceListAction extends BaseAction {
	private PriceReferenceBiz priceReferenceBiz;
	private PriceIndexBiz priceIndexBiz;

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		PriceReferenceListForm priceReferenceListForm = (PriceReferenceListForm) form;
		if (priceReferenceListForm == null) {
			priceReferenceListForm = new PriceReferenceListForm();
		}
		try {
			priceReferenceListForm.setList(priceReferenceBiz
					.list(priceReferenceListForm));
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("priceReference", priceReferenceListForm);
		return mapping.findForward("listPriceReference");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		PriceReferenceListForm priceReferenceListForm = (PriceReferenceListForm) form;
		if (priceReferenceListForm == null) {
			priceReferenceListForm = new PriceReferenceListForm();
		}
		String forwardPage = "";
		try {
			Long priceReferenceId = priceReferenceListForm.getId();
			long a = System.currentTimeMillis();
			PriceReference priceReference = priceReferenceBiz
					.getPriceReferenceById(priceReferenceId);
			request.setAttribute("priceReference", priceReference);
			new PerformListener("viewPriceReference", a);

			long b = System.currentTimeMillis();
			List<PriceIndex> priceIndexList = priceIndexBiz
					.getPriceIndexByReferenceId(priceReferenceId);
			request.setAttribute("priceIndexList", priceIndexList);
			new PerformListener("getPriceIndexByReferenceId", b);

			long c = System.currentTimeMillis();
			List chartUrlList = priceIndexBiz.listChartByReference(
					priceReferenceId, request, response);
			request.setAttribute("chartUrlList", chartUrlList);
			 new PerformListener("listChartByReference", b);
		} catch (Exception e) {
			e.printStackTrace();
		}
		forwardPage = "viewPriceReference";
		return mapping.findForward(forwardPage);

	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		PriceReferenceListForm priceReferenceListForm = (PriceReferenceListForm) form;

		PriceReference priceReference = new PriceReference();
		priceReference.setThisAction("insert");
		priceReference.setMoney("人民币元");
		priceReference.setUnit("500克");
		priceReference.setType(priceReferenceListForm.getType());
		request.setAttribute("priceReference", priceReference);
		String forwardPage = "editPriceReference";
		return mapping.findForward(forwardPage);
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		PriceReferenceListForm priceReferenceListForm = (PriceReferenceListForm) form;
		long priceReferenceId = priceReferenceListForm.getId();
		if (priceReferenceId < 1) {
			priceReferenceId = priceReferenceListForm.getSelectedItems()[0];
		}

		if (priceReferenceId > 0) {
			PriceReference priceReference = priceReferenceBiz
					.getPriceReferenceById(priceReferenceId);
			priceReference.setThisAction("update");
			request.setAttribute("priceReference", priceReference);
		} else {
			request.setAttribute("priceReference", new PriceReference());
		}
		return mapping.findForward("editPriceReference");
	}

	public ActionForward updatePriceReferenceStore(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws AppException {
		PriceReferenceListForm priceReferenceListForm = (PriceReferenceListForm) form;
		Inform inf = new Inform();
		if (priceReferenceListForm == null) {
			priceReferenceListForm = new PriceReferenceListForm();
		}
		try {

			// priceReferenceBiz.getValidPriceReferenceList();
			priceReferenceBiz.buildPriceReferenceTree();
			inf.setMessage("更新成功");
			return forwardInformPage(inf, mapping, request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("priceReferenceListForm", priceReferenceListForm);
		return mapping.findForward("listPriceReference");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		PriceReferenceListForm priceReferenceListForm = (PriceReferenceListForm) form;
		// String forwardPage = "";
		long id = 0;
		Inform inf = new Inform();
		try {
			for (int i = 0; i < priceReferenceListForm.getSelectedItems().length; i++) {
				id = priceReferenceListForm.getSelectedItems()[i];
				priceReferenceBiz.deletePriceReference(id);
			}
			return list(mapping, form, request, response);
		} catch (Exception ex) {
			inf.setMessage("删除失败" + ex.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}

	public void setPriceReferenceBiz(PriceReferenceBiz priceReferenceBiz) {
		this.priceReferenceBiz = priceReferenceBiz;
	}

	public void setPriceIndexBiz(PriceIndexBiz priceIndexBiz) {
		this.priceIndexBiz = priceIndexBiz;
	}

}