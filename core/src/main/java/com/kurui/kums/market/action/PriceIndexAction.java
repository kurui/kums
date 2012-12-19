package com.kurui.kums.market.action;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;

import com.kurui.kums.base.struts.BaseAction;
import com.kurui.kums.base.ui.inform.Inform;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.base.Constant;
import com.kurui.kums.market.PriceIndex;
import com.kurui.kums.market.PriceReference;
import com.kurui.kums.market.biz.PriceIndexBiz;
import com.kurui.kums.market.biz.PriceReferenceBiz;
import com.kurui.kums.right.UserRightInfo;
import com.kurui.kums.library.Company;
import com.kurui.kums.library.biz.CompanyBiz;

public class PriceIndexAction extends BaseAction {
	private PriceIndexBiz priceIndexBiz;
	private PriceReferenceBiz priceReferenceBiz;
	private CompanyBiz companyBiz;

	public ActionForward insert(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		PriceIndex pform = (PriceIndex) form;
		Inform inf = new Inform();
		UserRightInfo uri = (UserRightInfo) request.getSession().getAttribute(
				"URI");
		try {
			PriceIndex priceIndex = new PriceIndex();
			long priceReferenceId = Constant
					.toLong(pform.getPriceReferenceId());
			PriceReference priceReference = priceReferenceBiz
					.getPriceReferenceById(priceReferenceId);
			priceIndex.setPriceReference(priceReference);

			long gatherCompanyId = Constant.toLong(pform.getGatherCompanyId());
			if (gatherCompanyId > 0) {
				Company gatherCompany = companyBiz
						.getCompanyById(gatherCompanyId);
				priceIndex.setGatherCompany(gatherCompany);
			}

			priceIndex.setPrice(pform.getPrice());
			priceIndex.setMaxPrice(pform.getMaxPrice());
			priceIndex.setMinPrice(pform.getMinPrice());
			priceIndex.setRange(pform.getRange());
			priceIndex.setSnatchTime(pform.getSnatchTime());
			priceIndex.setEntryOperator(uri.getUser().getUserNo());
			priceIndex.setMemo(pform.getMemo());
			priceIndex.setStatus(pform.getStatus());

			long num = priceIndexBiz.save(priceIndex);

			if (num > 0) {
				return redirect(priceIndex);
			} else {
				inf.setMessage("您添加参照系数失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			inf.setMessage("异常:" + e.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}

	public ActionForward updateBatch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		PriceIndex pform = (PriceIndex) form;
		Inform inf = new Inform();
		UserRightInfo uri = (UserRightInfo) request.getSession().getAttribute(
				"URI");
		try {

			Long[] priceIndexIds = pform.getPriceIndexIds();
			Long[] priceReferenceIds = pform.getPriceReferenceIds();
			Long[] gatherCompanyIds = pform.getGatherCompanyIds();
			BigDecimal[] prices = pform.getPrices();
			BigDecimal[] maxPrices = pform.getMaxPrices();
			BigDecimal[] minPrices = pform.getMinPrices();
			BigDecimal[] ranges = pform.getRanges();
			Timestamp[] snatchTimes = pform.getSnatchTimes();
			String[] memos = pform.getMemos();
			Long[] statuss = pform.getStatuss();

			if (priceReferenceIds != null) {
				for (int i = 0; i < priceReferenceIds.length; i++) {
					long priceIndexId = Constant.toLong(priceIndexIds[i]);

					if (priceIndexId > 0) {
						PriceIndex priceIndex = priceIndexBiz
								.getPriceIndexById(priceIndexId);
						long priceReferenceId = Constant
								.toLong(priceReferenceIds[i]);
						if (priceReferenceId > 0) {
							PriceReference priceReference = priceReferenceBiz
									.getPriceReferenceById(priceReferenceId);
							priceIndex.setPriceReference(priceReference);
						} else {
							System.out.println("priceReferenceId is null");
						}

						long gatherCompanyId = Constant
								.toLong(gatherCompanyIds[i]);
						if (gatherCompanyId > 0) {
							Company gatherCompany = companyBiz
									.getCompanyById(gatherCompanyId);
							priceIndex.setGatherCompany(gatherCompany);
						}
						priceIndex.setPrice(Constant.toBigDecimal(prices[i]));
						priceIndex.setMaxPrice(Constant
								.toBigDecimal(maxPrices[i]));
						priceIndex.setMinPrice(Constant
								.toBigDecimal(minPrices[i]));
						priceIndex.setRange(Constant.toBigDecimal(ranges[i]));
						priceIndex.setSnatchTime(snatchTimes[i]);
						priceIndex.setMemo(memos[i]);
						// priceIndex.setStatus(statuss[i]);
//						priceIndex.setStatus(PriceIndex.STATES_1);
						priceIndex.setEntryOperator(uri.getUser().getUserNo());
						priceIndexBiz.update(priceIndex);
					} else {
						PriceIndex priceIndex = new PriceIndex();
						long priceReferenceId = Constant
								.toLong(priceReferenceIds[i]);
						if (priceReferenceId > 0) {
							PriceReference priceReference = priceReferenceBiz
									.getPriceReferenceById(priceReferenceId);
							priceIndex.setPriceReference(priceReference);
						} else {
							System.out.println("priceReferenceId is null");
						}

						long gatherCompanyId = Constant
								.toLong(gatherCompanyIds[i]);
						if (gatherCompanyId > 0) {
							Company gatherCompany = companyBiz
									.getCompanyById(gatherCompanyId);
							priceIndex.setGatherCompany(gatherCompany);
						}
						priceIndex.setPrice(Constant.toBigDecimal(prices[i]));
						priceIndex.setMaxPrice(Constant
								.toBigDecimal(maxPrices[i]));
						priceIndex.setMinPrice(Constant
								.toBigDecimal(minPrices[i]));
						priceIndex.setRange(Constant.toBigDecimal(ranges[i]));
						priceIndex.setSnatchTime(snatchTimes[i]);
						priceIndex.setMemo(memos[i]);
						// priceIndex.setStatus(statuss[i]);
						priceIndex.setStatus(PriceIndex.STATES_1);
						priceIndex.setEntryOperator(uri.getUser().getUserNo());
						priceIndexBiz.save(priceIndex);
					}
				}
				return redirectList(pform);
			} else {
				inf.setMessage("priceReferenceIds为空");
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
		PriceIndex pform = (PriceIndex) form;
		Inform inf = new Inform();
		UserRightInfo uri = (UserRightInfo) request.getSession().getAttribute(
				"URI");
		try {
			if (pform.getId() > 0) {
				PriceIndex priceIndex = priceIndexBiz.getPriceIndexById(pform
						.getId());
				long priceReferenceId = pform.getPriceReferenceId();
				PriceReference priceReference = priceReferenceBiz
						.getPriceReferenceById(priceReferenceId);
				priceIndex.setPriceReference(priceReference);

				long gatherCompanyId = Constant.toLong(pform
						.getGatherCompanyId());
				if (gatherCompanyId > 0) {
					Company gatherCompany = companyBiz
							.getCompanyById(gatherCompanyId);
					priceIndex.setGatherCompany(gatherCompany);
				}

				priceIndex.setPrice(pform.getPrice());
				priceIndex.setMaxPrice(pform.getMaxPrice());
				priceIndex.setMinPrice(pform.getMinPrice());
				priceIndex.setSnatchTime(pform.getSnatchTime());
				priceIndex.setEntryOperator(uri.getUser().getUserNo());
				priceIndex.setMemo(pform.getMemo());
				priceIndex.setStatus(pform.getStatus());

				long flag = priceIndexBiz.update(priceIndex);

				if (flag > 0) {
					return redirect(priceIndex);
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

	public ActionRedirect redirect(PriceIndex priceIndex) {
		ActionRedirect redirect = new ActionRedirect(
				"/market/priceIndexList.do");
		redirect.addParameter("thisAction", "view");
		redirect.addParameter("id", priceIndex.getId());
		return redirect;
	}

	public ActionRedirect redirectList(PriceIndex priceIndex) {
		ActionRedirect redirect = new ActionRedirect(
				"/market/priceIndexList.do");
		redirect.addParameter("thisAction", "list");
		return redirect;
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