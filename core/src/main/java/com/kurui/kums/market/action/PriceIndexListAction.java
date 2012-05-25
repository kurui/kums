package com.kurui.kums.market.action;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.kurui.kums.base.BaseAction;
import com.kurui.kums.base.Inform;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.base.util.ArrayUtil;
import com.kurui.kums.base.util.CurrentDate;
import com.kurui.kums.base.util.StringUtil;
import com.kurui.kums.market.PriceIndex;
import com.kurui.kums.market.PriceIndexListForm;
import com.kurui.kums.market.PriceReference;
import com.kurui.kums.market.biz.PriceIndexBiz;
import com.kurui.kums.market.biz.PriceReferenceBiz;
import com.kurui.kums.transaction.Company;
import com.kurui.kums.transaction.biz.CompanyBiz;

public class PriceIndexListAction extends BaseAction {
	private PriceIndexBiz priceIndexBiz;
	private PriceReferenceBiz priceReferenceBiz;
	private CompanyBiz companyBiz;

	public ActionForward addPriceIndexChart(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws AppException {
		PriceIndexListForm priceIndexListForm = (PriceIndexListForm) form;
		// String forwardPage = "";
		long id = 0;
		Inform inf = new Inform();
		try {
			for (int i = 0; i < priceIndexListForm.getSelectedItems().length; i++) {
				id = priceIndexListForm.getSelectedItems()[i];
				if (id > 0) {
					System.out.println("----------------");
				}
			}
			return list(mapping, form, request, response);
		} catch (Exception ex) {
			inf.setMessage("绘制图表失败" + ex.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}

	public ActionForward listChart(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		PriceIndexListForm priceIndexListForm = (PriceIndexListForm) form;
		if (priceIndexListForm == null) {
			priceIndexListForm = new PriceIndexListForm();
		}

		priceIndexListForm = setSearchToolBar(priceIndexListForm);
		try {
			List chartUrlList = priceIndexBiz.listChart(priceIndexListForm,
					request, response);
			request.setAttribute("chartUrlList", chartUrlList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("priceIndexListForm", priceIndexListForm);
		return mapping.findForward("listPriceIndexChart");
	}

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		PriceIndexListForm priceIndexListForm = (PriceIndexListForm) form;
		if (priceIndexListForm == null) {
			priceIndexListForm = new PriceIndexListForm();
		}
		try {
			priceIndexListForm = setSearchToolBar(priceIndexListForm);
			priceIndexListForm.setList(priceIndexBiz.list(priceIndexListForm));

		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("priceIndexListForm", priceIndexListForm);
		return mapping.findForward("listPriceIndex");
	}

	public ActionForward listByPhaseTime(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws AppException {
		PriceIndexListForm priceIndexListForm = (PriceIndexListForm) form;
		if (priceIndexListForm == null) {
			priceIndexListForm = new PriceIndexListForm();
		}
		try {
			priceIndexListForm = setSearchToolBar(priceIndexListForm);
			ArrayList<String[]> phaseData = priceIndexBiz.listByPhaseTime(
					priceIndexListForm, request);
			request.setAttribute("phaseData", phaseData);

		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("priceIndexListForm", priceIndexListForm);
		return mapping.findForward("listPriceIndexByMonth");
	}

	private PriceIndexListForm setSearchToolBar(
			PriceIndexListForm priceIndexListForm) throws AppException {

		if (priceIndexListForm.getRapid() == "") {
			// priceIndexListForm.setRapid("last30");
		}
		CurrentDate currentDate = new CurrentDate();
		if (priceIndexListForm.getYear() == "") {
			priceIndexListForm.setYear(String.valueOf(currentDate.getYear()));
		}
		if (priceIndexListForm.getMonth() == "") {
			priceIndexListForm.setMonth(currentDate.getMonthInt() + "");
		}

		return priceIndexListForm;
	}

	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		PriceIndexListForm priceIndexListForm = (PriceIndexListForm) form;
		try {
			Long priceIndexId = priceIndexListForm.getId();
			PriceIndex priceIndex = priceIndexBiz
					.getPriceIndexById(priceIndexId);
			request.setAttribute("priceIndex", priceIndex);
		} catch (Exception e) {
			e.printStackTrace();
		}
		forwardPage = "viewPriceIndex";
		return mapping.findForward(forwardPage);

	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		PriceIndexListForm priceIndexListForm = (PriceIndexListForm) form;

		PriceIndex priceIndex = new PriceIndex();
		priceIndex.setThisAction("insert");
		PriceReference priceReference = new PriceReference();
		priceReference.setId(priceIndexListForm.getPriceReferenceId());
		priceIndex.setPriceReference(priceReference);
		priceIndex.setPrice(BigDecimal.ZERO);
		priceIndex.setMaxPrice(BigDecimal.ZERO);
		priceIndex.setMinPrice(BigDecimal.ZERO);
		priceIndex.setRange(BigDecimal.ZERO);
		priceIndex.setSnatchTime(new Timestamp(System.currentTimeMillis()));

		request.setAttribute("priceIndex", priceIndex);
		String forwardPage = "editPriceIndex";

		request = loadDataStore(request);

		return mapping.findForward(forwardPage);
	}

	public ActionForward saveDest(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		PriceIndexListForm priceIndexListForm = (PriceIndexListForm) form;

		String priceReferenceTypes = priceIndexListForm
				.getPriceReferenceTypes();

		PriceIndex priceIndex = new PriceIndex();
		priceIndex.setThisAction("insert");
		priceIndex.setPrice(BigDecimal.ZERO);
		priceIndex.setMaxPrice(BigDecimal.ZERO);
		priceIndex.setMinPrice(BigDecimal.ZERO);
		priceIndex.setSnatchTime(new Timestamp(System.currentTimeMillis()));

		request.setAttribute("priceIndex", priceIndex);
		String forwardPage = "editPriceIndex";

		List<PriceReference> priceReferenceList = priceReferenceBiz
				.getPriceReferenceListByType(priceReferenceTypes);
		request.setAttribute("priceReferenceList", priceReferenceList);

		List<Company> gatherCompanyList = companyBiz
				.getCompanyListByProvideChain("5100");// 数据采集点
		request.setAttribute("gatherCompanyList", gatherCompanyList);

		return mapping.findForward(forwardPage);
	}

	public ActionForward saveAppoint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		PriceIndexListForm priceIndexListForm = (PriceIndexListForm) form;

		Long priceReferenceId = priceIndexListForm.getPriceReferenceId();
		PriceReference priceReference = priceReferenceBiz
				.getPriceReferenceById(priceReferenceId);

		PriceIndex priceIndex = new PriceIndex();
		if (priceIndex != null) {
			priceIndex.setPriceReference(priceReference);
		}

		priceIndex.setThisAction("insert");
		priceIndex.setPrice(BigDecimal.ZERO);
		priceIndex.setMaxPrice(BigDecimal.ZERO);
		priceIndex.setMinPrice(BigDecimal.ZERO);
		priceIndex.setSnatchTime(new Timestamp(System.currentTimeMillis()));

		request.setAttribute("priceIndex", priceIndex);
		String forwardPage = "editPriceIndex";

		request = loadDataStore(request);

		return mapping.findForward(forwardPage);
	}

	public ActionForward saveBatch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		PriceIndexListForm priceIndexListForm = (PriceIndexListForm) form;

		PriceIndex priceIndex = new PriceIndex();
		priceIndex.setPrice(BigDecimal.ZERO);
		priceIndex.setMaxPrice(BigDecimal.ZERO);
		priceIndex.setMinPrice(BigDecimal.ZERO);
		priceIndex.setSnatchTime(new Timestamp(System.currentTimeMillis()));

		List<PriceIndex> priceIndexList = new ArrayList<PriceIndex>();
		priceIndexList.add(priceIndex);
		request.setAttribute("priceIndexList", priceIndexList);

		request.setAttribute("thisAction", "updateBatch");

		String priceReferenceTypes = priceIndexListForm
				.getPriceReferenceTypes();
		List<PriceReference> priceReferenceList = priceReferenceBiz
				.getPriceReferenceListByType(priceReferenceTypes);
		request.setAttribute("priceReferenceList", priceReferenceList);
		request.setAttribute("priceReferenceTypes", priceReferenceTypes);

		List<Company> gatherCompanyList = companyBiz
				.getCompanyListByProvideChain("5100");// 数据采集点
		request.setAttribute("gatherCompanyList", gatherCompanyList);

		String forwardPage = "editPriceIndexBatch";

		return mapping.findForward(forwardPage);
	}

	public ActionForward editBatch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		PriceIndexListForm priceIndexListForm = (PriceIndexListForm) form;

		int[] indexIds = priceIndexListForm.getSelectedItems();
		String priceIndexIds = StringUtil.getStringByArray(indexIds, ",");
		List<PriceIndex> priceIndexList = priceIndexBiz
				.getPriceIndexByIds(priceIndexIds);
		request.setAttribute("priceIndexList", priceIndexList);

		request.setAttribute("thisAction", "updateBatch");

		List<PriceReference> priceReferenceList = priceReferenceBiz
				.getPriceReferenceListByIndexIds(indexIds);
		request.setAttribute("priceReferenceList", priceReferenceList);

		List<Company> gatherCompanyList = companyBiz
				.getCompanyListByProvideChain("5100");// 数据采集点
		request.setAttribute("gatherCompanyList", gatherCompanyList);

		String forwardPage = "editPriceIndexBatch";

		return mapping.findForward(forwardPage);
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		PriceIndexListForm priceIndexListForm = (PriceIndexListForm) form;

		long priceIndexId = priceIndexListForm.getId();
		if (priceIndexId < 1) {
			priceIndexId = priceIndexListForm.getSelectedItems()[0];
		}

		if (priceIndexId > 0) {
			PriceIndex priceIndex = priceIndexBiz
					.getPriceIndexById(priceIndexId);
			priceIndex.setThisAction("update");
			request.setAttribute("priceIndex", priceIndex);
		} else {
			request.setAttribute("priceIndex", new PriceIndex());
		}
		request = loadDataStore(request);
		return mapping.findForward("editPriceIndex");
	}

	public HttpServletRequest loadDataStore(HttpServletRequest request)
			throws AppException {
		List<PriceReference> priceReferenceList = priceReferenceBiz
				.getPriceReferenceList();
		request.setAttribute("priceReferenceList", priceReferenceList);

		List<Company> gatherCompanyList = companyBiz
				.getCompanyListByProvideChain("5100");// 数据采集点
		request.setAttribute("gatherCompanyList", gatherCompanyList);
		return request;
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		PriceIndexListForm priceIndexListForm = (PriceIndexListForm) form;
		// String forwardPage = "";
		long id = 0;
		Inform inf = new Inform();
		try {
			for (int i = 0; i < priceIndexListForm.getSelectedItems().length; i++) {
				id = priceIndexListForm.getSelectedItems()[i];
				if (id > 0) {
					priceIndexBiz.deletePriceIndex(id);
				}
			}
			return list(mapping, form, request, response);
		} catch (Exception ex) {
			inf.setMessage("删除失败" + ex.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}

	public void setPriceIndexBiz(PriceIndexBiz priceIndexBiz) {
		this.priceIndexBiz = priceIndexBiz;
	}

	public void setPriceReferenceBiz(PriceReferenceBiz priceReferenceBiz) {
		this.priceReferenceBiz = priceReferenceBiz;
	}

	public void setCompanyBiz(CompanyBiz companyBiz) {
		this.companyBiz = companyBiz;
	}

}