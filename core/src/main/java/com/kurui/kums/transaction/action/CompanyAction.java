package com.kurui.kums.transaction.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;

import com.kurui.kums.base.KumsDataStoreListener;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.base.struts.BaseAction;
import com.kurui.kums.base.threads.MainTask;
import com.kurui.kums.base.ui.inform.Inform;
import com.kurui.kums.base.util.StringUtil;
import com.kurui.kums.right.UserRightInfo;
import com.kurui.kums.system.biz.SysInitBiz;
import com.kurui.kums.transaction.Company;
import com.kurui.kums.transaction.biz.CompanyBiz;

public class CompanyAction extends BaseAction {
	private CompanyBiz companyBiz;
	private SysInitBiz sysInitBiz;

	public ActionForward insert(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		UserRightInfo uri = (UserRightInfo) request.getSession().getAttribute(
				"URI");

		// String forwardPage = "";
		Company companyForm = (Company) form;
		Inform inf = new Inform();
		try {
			Company company = new Company();
			company.setName(companyForm.getName());
			company.setShortName(companyForm.getShortName());
			
			String provideChain = StringUtil.getStringByArray(companyForm
					.getProvideChainGroup(), ",");
			company.setProvideChain(provideChain);
			company.setDeputy(companyForm.getDeputy());
			company.setRegisterType(companyForm.getRegisterType());
			company.setRegisterAddress(companyForm.getRegisterAddress());
			company.setEntityAddress(companyForm.getEntityAddress());
			company.setRegisterCode(companyForm.getRegisterCode());
			company.setRevenueCode(companyForm.getRevenueCode());
			company.setCorporationCode(companyForm.getCorporationCode());			
			company.setRegisterCapital(companyForm.getRegisterCapital());
			company.setNetAssetValue(companyForm.getNetAssetValue());
			company.setMainBusiness(companyForm.getMainBusiness());
			company.setSidelineBusiness(companyForm.getSidelineBusiness());
			company.setTelephone(companyForm.getTelephone());
			company.setRegisterDate(companyForm.getRegisterDate());			
			company.setMemo(companyForm.getMemo());
			company.setType(companyForm.getType());
			company.setStatus(companyForm.getStatus());
			company.setUpdateTime(new Timestamp(System.currentTimeMillis()));
			company.setUserName(uri.getUser().getUserName());
			long num = companyBiz.save(company);

			if (num > 0) {
				// --更新静态库
				KumsDataStoreListener listener = new KumsDataStoreListener(sysInitBiz,
						"Company");
				MainTask.put(listener);
				// ---------
				return redirect(company);
			} else {
				inf.setMessage("添加公司数据失败！");
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
		Company companyForm = (Company) form;
		Inform inf = new Inform();
		UserRightInfo uri = (UserRightInfo) request.getSession().getAttribute(
				"URI");
		try {
			if (companyForm.getId() > 0) {
				Company company = companyBiz
						.getCompanyById(companyForm.getId());
				company.setName(companyForm.getName());
				company.setShortName(companyForm.getShortName());
				
				String provideChain = StringUtil.getStringByArray(companyForm
						.getProvideChainGroup(), ",");
				company.setProvideChain(provideChain);
				company.setDeputy(companyForm.getDeputy());
				company.setRegisterType(companyForm.getRegisterType());
				company.setRegisterAddress(companyForm.getRegisterAddress());
				company.setEntityAddress(companyForm.getEntityAddress());
				company.setRegisterCode(companyForm.getRegisterCode());
				company.setRevenueCode(companyForm.getRevenueCode());
				company.setCorporationCode(companyForm.getCorporationCode());			
				company.setRegisterCapital(companyForm.getRegisterCapital());
				company.setNetAssetValue(companyForm.getNetAssetValue());
				company.setMainBusiness(companyForm.getMainBusiness());
				company.setSidelineBusiness(companyForm.getSidelineBusiness());
				company.setTelephone(companyForm.getTelephone());
				company.setRegisterDate(companyForm.getRegisterDate());			
				company.setMemo(companyForm.getMemo());
				company.setType(companyForm.getType());
				company.setStatus(companyForm.getStatus());
				company
						.setUpdateTime(new Timestamp(System.currentTimeMillis()));
				company.setUserName(uri.getUser().getUserName());
				long flag = companyBiz.update(company);
				// --更新静态库
				KumsDataStoreListener listener = new KumsDataStoreListener(sysInitBiz,
						"Company");
				MainTask.put(listener);
				// ---------
				if (flag > 0) {					
					return redirect(company);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			inf.setMessage("异常:" + e.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}

	public ActionRedirect redirect(Company company) {
		ActionRedirect redirect = new ActionRedirect(
				"/transaction/companyList.do");
		redirect.addParameter("thisAction", "view");
		redirect.addParameter("id", company.getId());
		return redirect;
	}

	public void setCompanyBiz(CompanyBiz companyBiz) {
		this.companyBiz = companyBiz;
	}

	public void setSysInitBiz(SysInitBiz sysInitBiz) {
		this.sysInitBiz = sysInitBiz;
	}
}