package com.kurui.kums.right.biz;

import java.util.List;

import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.right.License;
import com.kurui.kums.right.LicenseListForm;
import com.kurui.kums.right.dao.LicenseDAO;

public class LicenseBizImp implements LicenseBiz {
	private LicenseDAO licenseDAO;

	public List getLicense(LicenseListForm clf) throws AppException {
		return licenseDAO.getLicense(clf);
	}

	public LicenseDAO getLicenseDAO() {
		return licenseDAO;
	}

	public void setLicenseDAO(LicenseDAO licenseDAO) {
		this.licenseDAO = licenseDAO;
	}

	public License getLicenseById(long id) throws AppException {

		return licenseDAO.getLicenseById(id);
	}

	public long updateInfo(License license) throws AppException {
		return licenseDAO.updateInfo(license);
	}

	public long save(License license) throws AppException {
		return licenseDAO.save(license);
	}

	public void deleteLicenseById(int id) throws AppException {
		licenseDAO.deleteLicenseById(id);
	}

}
