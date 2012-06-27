package com.kurui.kums.transaction.dao;

import java.util.List;

import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.transaction.AreaCode;
import com.kurui.kums.transaction.AreaCodeListForm;

public interface AreaCodeDAO {

	public List list(AreaCodeListForm AreaCodeForm) throws AppException;

	public void delete(long id) throws AppException;

	public long save(AreaCode AreaCode) throws AppException;

	public long update(AreaCode AreaCode) throws AppException;

	public AreaCode getAreaCodeById(long AreaCodeId) throws AppException;

	public String getAreaTextByCode(String areaCode)throws AppException;

	public List<AreaCode> getValidAreaCodeList() throws AppException;


}
