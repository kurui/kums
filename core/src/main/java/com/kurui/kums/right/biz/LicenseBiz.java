package com.kurui.kums.right.biz;

import java.util.List;

import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.right.License;
import com.kurui.kums.right.LicenseListForm;

public interface LicenseBiz {
	public List getLicense(LicenseListForm clf) throws AppException;

	public License getLicenseById(long id) throws AppException;

	public long updateInfo(License license) throws AppException;

	public long save(License license) throws AppException;

	public void deleteLicenseById(int id) throws AppException;

	public License getClientLicense() throws AppException;

}
