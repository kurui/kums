package com.kurui.kums.library.biz;

import java.util.List;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.library.DataType;
import com.kurui.kums.library.DataTypeListForm;
import com.kurui.kums.library.dao.DataTypeDAO;

public class DataTypeBizImp implements DataTypeBiz {
	private DataTypeDAO dataTypeDAO;

	public List list(DataTypeListForm DataTypeForm) throws AppException {
		return dataTypeDAO.list(DataTypeForm);
	}
	
	public void buildManageExpenseTree() throws AppException {
		dataTypeDAO.buildManageExpenseTree();
	}

	public void buildProductTree() throws AppException {
		dataTypeDAO.buildProductTree();
	}
	
	public void buildProvideChainTree() throws AppException{
		dataTypeDAO.buildProvideChainTree();
	}
	
	public void refactorDataTypeTree() throws AppException{
		dataTypeDAO.refactorDataTypeTree();
	}

	public void delete(long id) throws AppException {
		try {
			dataTypeDAO.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteDataType(Long DataTypeId) throws AppException {
		DataType DataType = getDataTypeById(DataTypeId);
		DataType.setStatus(DataType.STATES_0);// 将状态变为无效
		update(DataType);
	}

	public long save(DataType DataType) throws AppException {
		return dataTypeDAO.save(DataType);
	}

	public long update(DataType DataType) throws AppException {
		return dataTypeDAO.update(DataType);
	}

	public DataType getDataTypeById(long id) throws AppException {
		return dataTypeDAO.getDataTypeById(id);
	}

	public DataType getDataTypeByNo(String no) throws AppException {
		return dataTypeDAO.getDataTypeByNo(no);
	}

	public List<DataType> getDataTypeList() throws AppException {
		return dataTypeDAO.getDataTypeList();
	}

	public List<DataType> getValidDataTypeList() throws AppException {
		return dataTypeDAO.getValidDataTypeList();
	}

	public List<DataType> getSubDataTypeList(String no) throws AppException {
		return dataTypeDAO.getSubDataTypeList(no);
	}

	public List<DataType> getSupDataTypeList(String no) throws AppException {
		return dataTypeDAO.getSupDataTypeList(no);
	}

	public List<DataType> getSameLevelDataTypeList(long DataTypeNo)
			throws AppException {
		return dataTypeDAO.getSameLevelDataTypeList(DataTypeNo);
	}

	public void setDataTypeDAO(DataTypeDAO dataTypeDAO) {
		this.dataTypeDAO = dataTypeDAO;
	}

}
