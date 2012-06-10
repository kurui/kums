package com.kurui.kums.transaction.biz;

import java.util.List;

import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.transaction.DataType;
import com.kurui.kums.transaction.DataTypeListForm;

public interface DataTypeBiz {

	public List list(DataTypeListForm form) throws AppException;

	public void buildProductTree() throws AppException;
	public void buildProvideChainTree() throws AppException;
	public void delete(long id) throws AppException;

	public void deleteDataType(Long id) throws AppException;

	public long save(DataType DataType) throws AppException;

	public long update(DataType DataType) throws AppException;

	public DataType getDataTypeById(long Id) throws AppException;

	public DataType getDataTypeByNo(String No) throws AppException;

	public List<DataType> getDataTypeList() throws AppException;

	public List<DataType> getValidDataTypeList() throws AppException;

	public List<DataType> getSubDataTypeList(String no) throws AppException;

	public List<DataType> getSupDataTypeList(String no) throws AppException;

	public List<DataType> getSameLevelDataTypeList(long DataTypeNo)
			throws AppException;

	public void refactorDataTypeTree()throws AppException;

}
