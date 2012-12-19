package com.kurui.kums.market.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;

import com.kurui.kums.base.struts.BaseAction;
import com.kurui.kums.base.ui.inform.Inform;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.base.util.time.DateUtil;
import com.kurui.kums.base.util.KumsNoUtil;
import com.kurui.kums.market.EstateDish;
import com.kurui.kums.market.biz.EstateDishBiz;
import com.kurui.kums.right.UserRightInfo;
import com.kurui.kums.library.Company;
import com.kurui.kums.library.biz.CompanyBiz;

public class EstateDishAction extends BaseAction {
	private EstateDishBiz estateDishBiz;
	private CompanyBiz companyBiz;
	private KumsNoUtil noUtil;

	public ActionForward insert(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		EstateDish pform = (EstateDish) form;
		Inform inf = new Inform();
		UserRightInfo uri = (UserRightInfo) request.getSession().getAttribute(
				"URI");
		try {
			EstateDish estateDish = new EstateDish();
			estateDish.setNo(noUtil.getEstateDishNo());
			estateDish.setName(pform.getName());
			long companyId = pform.getCompanyId();
			if (companyId > 0) {
				Company company = companyBiz.getCompanyById(companyId);
				estateDish.setCompany(company);
			}
			estateDish.setBeginTime(DateUtil.getTimestamp(pform.getBeginDate(),
					"yyyy-MM-dd"));
			estateDish.setEntranceTime(DateUtil.getTimestamp(pform
					.getEntranceDate(), "yyyy-MM-dd"));
			estateDish.setAddress(pform.getAddress());
			estateDish.setMemo(pform.getMemo());
			estateDish.setType(pform.getType());
			estateDish.setStatus(pform.getStatus());

			long num = estateDishBiz.save(estateDish);

			if (num > 0) {
				return redirect(estateDish);
			} else {
				inf.setMessage("您添加楼盘失败！");
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
		EstateDish pform = (EstateDish) form;
		Inform inf = new Inform();
		UserRightInfo uri = (UserRightInfo) request.getSession().getAttribute(
				"URI");
		try {
			if (pform.getId() > 0) {
				EstateDish estateDish = estateDishBiz.getEstateDishById(pform
						.getId());
				
				String no = estateDish.getNo();
				if (no==null||"".equals(no)) {
					estateDish.setNo(noUtil.getEstateDishNo());
				}
				estateDish.setName(pform.getName());
				long companyId = pform.getCompanyId();
				if (companyId > 0) {
					Company company = companyBiz.getCompanyById(companyId);
					estateDish.setCompany(company);
				}
				estateDish.setBeginTime(DateUtil.getTimestamp(pform
						.getBeginDate(), "yyyy-MM-dd"));
				estateDish.setEntranceTime(DateUtil.getTimestamp(pform
						.getEntranceDate(), "yyyy-MM-dd"));
					
				estateDish.setAddress(pform.getAddress());
				estateDish.setMemo(pform.getMemo());
				estateDish.setType(pform.getType());
				estateDish.setStatus(pform.getStatus());
				long flag = estateDishBiz.update(estateDish);

				if (flag > 0) {
					return redirect(estateDish);
				} else {
					inf.setMessage("您修改楼盘失败！");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			inf.setMessage("异常:" + e.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}

	public ActionRedirect redirect(EstateDish pform) {
		ActionRedirect redirect = new ActionRedirect(
				"/market/estateDishList.do");
		redirect.addParameter("thisAction", "view");
		redirect.addParameter("id", pform.getId());
		return redirect;
	}

	public void setEstateDishBiz(EstateDishBiz estateDishBiz) {
		this.estateDishBiz = estateDishBiz;
	}

	public void setCompanyBiz(CompanyBiz companyBiz) {
		this.companyBiz = companyBiz;
	}

	public void setNoUtil(KumsNoUtil noUtil) {
		this.noUtil = noUtil;
	}
	
	
}