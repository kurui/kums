package com.kurui.kums.right.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.kurui.kums.base.BaseAction;
import com.kurui.kums.base.Inform;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.base.util.DateUtil;
import com.kurui.kums.right.License;
import com.kurui.kums.right.SysUser;
import com.kurui.kums.right.UserRightInfo;
import com.kurui.kums.right.biz.LicenseBiz;

public class LicenseAction extends BaseAction {
	private License license;
	private LicenseBiz licenseBiz;

	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		License license = (License) form;
		Inform inf = new Inform();
		try {
			License tempLicense = licenseBiz.getLicenseById(license.getId());
			
			tempLicense.setLicenseNo(license.getLicenseNo());
			tempLicense.setCompanyNo(license.getCompanyNo());
			tempLicense.setNotafter(DateUtil.getTimestamp(license.getUpdateDate(), "yyyy-mm-dd"));
			tempLicense.setLicenseType(license.getLicenseType());
			tempLicense.setStaffNum(license.getStaffNum());
			tempLicense.setMemo(license.getMemo());
			tempLicense.setStatus(license.getStatus());
			tempLicense.setUpdateTime(new Timestamp(System.currentTimeMillis()));
			licenseBiz.updateInfo(tempLicense);
			request.setAttribute("license", tempLicense);

			inf.setMessage("您已经成功更新了授权！");
			inf.setForwardPage("/right/licenseList.do");
			inf.setParamId("thisAction");
			inf.setParamValue("list");

		} catch (Exception ex) {
			inf.setMessage("更新授权出错！错误信息是：" + ex.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}

	public ActionForward insert(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {		
		License license = (License) form;
		String forwardPage = "";
		
		Inform inf = new Inform();
			try {
				License tempLicense = new License();
				tempLicense.setLicenseNo(license.getLicenseNo());
				tempLicense.setCompanyNo(license.getCompanyNo());
				tempLicense.setIssued(new Timestamp(System.currentTimeMillis()));
				tempLicense.setNotafter(DateUtil.getTimestamp(license.getUpdateDate(), "yyyy-mm-dd"));
				tempLicense.setLicenseType(license.getLicenseType());
				tempLicense.setStaffNum(license.getStaffNum());
				tempLicense.setMemo(license.getMemo());
				tempLicense.setStatus(license.getStatus());
				tempLicense.setUpdateTime(new Timestamp(System.currentTimeMillis()));
			
				licenseBiz.save(tempLicense);
				request.setAttribute("license", tempLicense);

				inf.setMessage("您已经成功添加了授权！");
				inf.setForwardPage("/right/licenseList.do");
				inf.setParamId("thisAction");
				inf.setParamValue("list");
			} catch (Exception ex) {
				inf.setMessage("添加授权出错！错误信息是：" + ex.getMessage());
			}
		return forwardInformPage(inf, mapping, request);
	}
	
	public SysUser getUserByURI(HttpServletRequest request) {
		UserRightInfo uri = (UserRightInfo) request.getSession().getAttribute(
				"URI");
		if (uri != null && uri.getUser() != null)
			return uri.getUser();
		else {
			return null;
		}
	}

	public LicenseBiz getLicenseBiz() {
		return licenseBiz;
	}

	public void setLicenseBiz(LicenseBiz licenseBiz) {
		this.licenseBiz = licenseBiz;
	}

	public License getLicense() {
		return license;
	}

	public void setLicense(License license) {
		this.license = license;
	}
}
