package com.kurui.kums.transaction.dao;

import java.util.List;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.transaction.DataType;
import com.kurui.kums.transaction.DataTypeListForm;

public interface DataTypeDAO {

	public List list(DataTypeListForm tlf) throws AppException;

	public void buildProductTree() throws AppException;
	public void buildProvideChainTree() throws AppException;

	public void delete(long id) throws AppException;

	public long save(DataType dataType) throws AppException;

	public long update(DataType dataType) throws AppException;

	public DataType getDataTypeById(long id) throws AppException;

	public DataType getDataTypeByNo(String no) throws AppException;

	public List<DataType> getDataTypeList() throws AppException;

	public List<DataType> getSupDataTypeList(String no) throws AppException;

	public List<DataType> getSubDataTypeList(String no) throws AppException;

	public List<DataType> getSameLevelDataTypeList(long dataTypeNo)
			throws AppException;

	public List<DataType> getValidDataTypeList() throws AppException;

}
