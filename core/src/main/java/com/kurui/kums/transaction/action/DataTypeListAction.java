package com.kurui.kums.transaction.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.kurui.kums.base.BaseAction;
import com.kurui.kums.base.Inform;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.transaction.DataType;
import com.kurui.kums.transaction.DataTypeListForm;
import com.kurui.kums.transaction.biz.DataTypeBiz;
import com.kurui.kums.transaction.util.DataTypeStore;

public class DataTypeListAction extends BaseAction {
	private DataTypeBiz dataTypeBiz;

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		DataTypeListForm dataTypeListForm = (DataTypeListForm) form;
		if (dataTypeListForm == null) {
			dataTypeListForm = new DataTypeListForm();
		}
		try {
			dataTypeListForm.setList(dataTypeBiz.list(dataTypeListForm));
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("dataTypeListForm", dataTypeListForm);
		return mapping.findForward("listDataType");
	}

	// 上级
	public ActionForward listSup(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		DataTypeListForm dataTypeListForm = (DataTypeListForm) form;
		if (dataTypeListForm == null) {
			dataTypeListForm = new DataTypeListForm();
		}
		Inform inf = new Inform();
		try {
			String no = dataTypeListForm.getNo();
			List dataTypeList = dataTypeBiz.getSupDataTypeList(no);

			if (dataTypeList.size() > 0) {
				dataTypeListForm.setList(dataTypeList);
			} else {
				inf.setMessage("没有上级类型");
				inf.setClose(true);
				return forwardInformPage(inf, mapping, request);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("dataTypeListForm", dataTypeListForm);
		return mapping.findForward("listDataType");
	}

	// 下级
	public ActionForward listSub(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		DataTypeListForm dataTypeListForm = (DataTypeListForm) form;
		if (dataTypeListForm == null) {
			dataTypeListForm = new DataTypeListForm();
		}
		Inform inf = new Inform();
		try {
			String no = dataTypeListForm.getNo();

			List dataTypeList = dataTypeBiz.getSubDataTypeList(no);

			if (dataTypeList.size() > 0) {
				dataTypeListForm.setList(dataTypeList);
			} else {
				inf.setMessage("没有下级类型");
				inf.setClose(true);
				return forwardInformPage(inf, mapping, request);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("dataTypeListForm", dataTypeListForm);
		return mapping.findForward("listDataType");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		DataTypeListForm dataTypeListForm = (DataTypeListForm) form;
		try {
			Long dataTypeId = dataTypeListForm.getId();
			DataType dataType = dataTypeBiz.getDataTypeById(dataTypeId);
			if (dataType != null) {
				DataType supDataType = dataTypeBiz.getDataTypeByNo(dataType
						.getSuperNo());
				dataType.setSupDataType(supDataType);

				List subDataTypeList = dataTypeBiz.getSubDataTypeList(dataType
						.getNo());
				request.setAttribute("subDataTypeList", subDataTypeList);
			}

			request.setAttribute("dataType", dataType);

		} catch (Exception e) {
			e.printStackTrace();
		}
		forwardPage = "viewDataType";
		return mapping.findForward(forwardPage);

	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		DataType dataType = new DataType();
		dataType.setThisAction("insert");
		dataType.setStatus(DataType.STATES_1);

		request.setAttribute("dataType", dataType);

		List<DataType> dataTypeList = dataTypeBiz.getValidDataTypeList();

		request.setAttribute("dataTypeList", dataTypeList);

		String forwardPage = "editDataType";

		return mapping.findForward(forwardPage);
	}

	// 新增下级
	public ActionForward saveAppoint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		DataTypeListForm dataTypeListForm = (DataTypeListForm) form;
		String dataTypeNo = dataTypeListForm.getNo();

		DataType dataType = new DataType();
		dataType.setThisAction("insert");
		dataType.setSuperNo(dataTypeNo);
		dataType.setStatus(DataType.STATES_1);

		request.setAttribute("dataType", dataType);

		List<DataType> dataTypeList = new ArrayList<DataType>();
		DataType tempDataType = dataTypeBiz.getDataTypeByNo(dataTypeNo);
		dataTypeList.add(tempDataType);
		request.setAttribute("dataTypeList", dataTypeList);

		String forwardPage = "editDataType";

		return mapping.findForward(forwardPage);
	}

	// 新增同级
	public ActionForward saveSameLevel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		DataTypeListForm dataTypeListForm = (DataTypeListForm) form;
		String dataTypeNo = dataTypeListForm.getSuperNo();

		DataType dataType = new DataType();
		dataType.setThisAction("insert");
		dataType.setSuperNo(dataTypeNo);
		dataType.setStatus(DataType.STATES_1);

		request.setAttribute("dataType", dataType);

		List<DataType> dataTypeList = new ArrayList<DataType>();
		DataType tempDataType = dataTypeBiz.getDataTypeByNo(dataTypeNo);
		dataTypeList.add(tempDataType);
		request.setAttribute("dataTypeList", dataTypeList);

		String forwardPage = "editDataType";

		return mapping.findForward(forwardPage);
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		DataTypeListForm dataTypeListForm = (DataTypeListForm) form;
		long dataTypeId = dataTypeListForm.getSelectedItems()[0];
		if (dataTypeId > 0) {
			DataType dataType = dataTypeBiz.getDataTypeById(dataTypeId);
			dataType.setThisAction("update");
			request.setAttribute("dataType", dataType);
		} else {
			request.setAttribute("dataType", new DataType());
		}

		List<DataType> dataTypeList = dataTypeBiz.getValidDataTypeList();
		request.setAttribute("dataTypeList", dataTypeList);

		return mapping.findForward("editDataType");
	}

	public ActionForward updateDataTypeStore(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws AppException {
		DataTypeListForm dataTypeListForm = (DataTypeListForm) form;
		Inform inf = new Inform();
		if (dataTypeListForm == null) {
			dataTypeListForm = new DataTypeListForm();
		}
		try {
			DataTypeStore.dataTypeList = dataTypeBiz.getValidDataTypeList();
			dataTypeBiz.buildProductTree();
			dataTypeBiz.buildProvideChainTree();
			inf.setMessage("更新成功");
			return forwardInformPage(inf, mapping, request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("dataTypeListForm", dataTypeListForm);
		return mapping.findForward("listPriceReference");
	}

	public ActionForward refactorDataTypeTree(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws AppException {
		DataTypeListForm dataTypeListForm = (DataTypeListForm) form;
		Inform inf = new Inform();
		if (dataTypeListForm == null) {
			dataTypeListForm = new DataTypeListForm();
		}
		try {
			DataTypeStore.dataTypeList = dataTypeBiz.getValidDataTypeList();
			dataTypeBiz.refactorDataTypeTree();
			inf.setMessage("更新成功");
			return forwardInformPage(inf, mapping, request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("dataTypeListForm", dataTypeListForm);
		return mapping.findForward("listDataType");
	}
	
	
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		DataTypeListForm dataTypeListForm = (DataTypeListForm) form;
		// String forwardPage = "";
		long id = 0;
		Inform inf = new Inform();
		try {
			for (int i = 0; i < dataTypeListForm.getSelectedItems().length; i++) {
				id = dataTypeListForm.getSelectedItems()[i];
				if (id > 0) {
					dataTypeBiz.deleteDataType(id);
				}
			}
			return list(mapping, form, request, response);
		} catch (Exception ex) {
			inf.setMessage("删除失败" + ex.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}

	public void setDataTypeBiz(DataTypeBiz dataTypeBiz) {
		this.dataTypeBiz = dataTypeBiz;
	}

}