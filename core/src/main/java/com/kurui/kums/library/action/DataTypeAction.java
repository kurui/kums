package com.kurui.kums.library.action;

import java.sql.Timestamp;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;

import com.kurui.kums.base.struts.BaseAction;
import com.kurui.kums.base.KumsDataStoreListener;
import com.kurui.kums.base.ui.inform.Inform;
import com.kurui.kums.base.threads.MainTask;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.right.UserRightInfo;
import com.kurui.kums.system.biz.SysInitBiz;
import com.kurui.kums.library.DataType;
import com.kurui.kums.library.biz.DataTypeBiz;
import com.kurui.kums.library.util.DataTypeStore;

public class DataTypeAction extends BaseAction {
	private DataTypeBiz dataTypeBiz;
	private SysInitBiz sysInitBiz;

	public ActionForward insert(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		DataType pform = (DataType) form;
		Inform inf = new Inform();
		try {
			DataType tempDataType = dataTypeBiz.getDataTypeByNo(pform.getNo());
			if (tempDataType != null) {
				inf.setMessage(pform.getNo() + "-已被-" + tempDataType.getName()
						+ "-使用");
			} else {
				DataType dataType = new DataType();
				dataType.setNo(pform.getNo());
				dataType.setName(pform.getName());
				dataType.setSuperNo(pform.getSuperNo());
				dataType
						.setCreateTime(new Timestamp(System.currentTimeMillis()));
				dataType.setMemo(pform.getMemo());
				dataType.setStatus(pform.getStatus());

				long num = dataTypeBiz.save(dataType);
				// --更新静态库
				KumsDataStoreListener listener = new KumsDataStoreListener(sysInitBiz,
						"DataType");
				MainTask.put(listener);
				// ---------
				if (num > 0) {
					return redirect(dataType);
				} else {
					inf.setMessage("您添加数据类型失败！");
				}
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
		DataType pform = (DataType) form;
		Inform inf = new Inform();
		UserRightInfo uri = (UserRightInfo) request.getSession().getAttribute(
				"URI");
		try {
			if (pform.getId() > 0) {
				DataType dataType = dataTypeBiz.getDataTypeById(pform.getId());
				dataType.setNo(pform.getNo());
				dataType.setName(pform.getName());
				dataType.setSuperNo(pform.getSuperNo());
				dataType
						.setCreateTime(new Timestamp(System.currentTimeMillis()));
				dataType.setMemo(pform.getMemo());
				dataType.setStatus(pform.getStatus());

				long flag = dataTypeBiz.update(dataType);

				if (flag > 0) {
					return redirect(dataType);
				} else {
					inf.setMessage("您修改数据类型失败！");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			inf.setMessage("异常:" + e.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}

	public ActionForward insertItem(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		DataType pform = (DataType) form;
		Inform inf = new Inform();
		try {
			DataType tempDataType = dataTypeBiz.getDataTypeByNo(pform.getNo());
			if (tempDataType != null) {
				inf.setMessage(pform.getNo() + "-已被-" + tempDataType.getName()
						+ "-使用");
			} else {
				DataType dataType = new DataType();
				dataType.setNo(pform.getNo());
				dataType.setName(pform.getName());

				dataType.setSuperNo(pform.getSuperNo());

				dataType.setStatus(DataType.STATES_1);
				dataType
						.setCreateTime(new Timestamp(System.currentTimeMillis()));
				long num = dataTypeBiz.save(dataType);
				// --更新静态库
				KumsDataStoreListener listener = new KumsDataStoreListener(sysInitBiz,
						"DataType");
				MainTask.put(listener);
				// ---------
				if (num > 0) {
					return redirectSuper(dataType);
				} else {
					inf.setMessage("您添加数据类型失败！");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			inf.setMessage("异常:" + e.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}

	public ActionForward updateItem(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		DataType pform = (DataType) form;
		Inform inf = new Inform();
		UserRightInfo uri = (UserRightInfo) request.getSession().getAttribute(
				"URI");
		try {
			if (pform.getId() > 0) {
				DataType dataType = dataTypeBiz.getDataTypeById(pform.getId());
				dataType.setNo(pform.getNo());
				dataType.setName(pform.getName());
				dataType.setSuperNo(pform.getSuperNo());
				long flag = dataTypeBiz.update(dataType);

				if (flag > 0) {
					return redirectSuper(dataType);
				} else {
					inf.setMessage("您修改数据类型失败！");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			inf.setMessage("异常:" + e.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}

	public ActionForward deleteItem(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		DataType pform = (DataType) form;
		Inform inf = new Inform();
		try {
			if (pform.getId() > 0) {
				dataTypeBiz.deleteDataType(pform.getId());
			}

			String superNo = pform.getSuperNo();
			DataType dataType = dataTypeBiz.getDataTypeByNo(superNo);
			ActionRedirect redirect = new ActionRedirect(
					"/library/dataTypeList.do");

			redirect.addParameter("thisAction", "view");
			redirect.addParameter("id", dataType.getId());
			return redirect;
		} catch (Exception ex) {
			inf.setMessage("删除失败" + ex.getMessage());
		}

		return forwardInformPage(inf, mapping, request);
	}

	public ActionRedirect redirect(DataType dataType) {
		ActionRedirect redirect = new ActionRedirect(
				"/library/dataTypeList.do");
		// redirect.addParameter("thisAction", "listSub");
		// redirect.addParameter("no", dataType.getSuperNo());

		redirect.addParameter("thisAction", "view");
		redirect.addParameter("id", dataType.getId());

		return redirect;
	}

	public ActionRedirect redirectSuper(DataType dataType) {
		ActionRedirect redirect = new ActionRedirect(
				"/library/dataTypeList.do");

		redirect.addParameter("thisAction", "view");
		redirect.addParameter("id", DataTypeStore.getDataTypeIdByNo(dataType
				.getSuperNo()));
		return redirect;
	}

	public void setDataTypeBiz(DataTypeBiz dataTypeBiz) {
		this.dataTypeBiz = dataTypeBiz;
	}

	public void setSysInitBiz(SysInitBiz sysInitBiz) {
		this.sysInitBiz = sysInitBiz;
	}

}