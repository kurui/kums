package com.kurui.kums.finance.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.kurui.kums.base.BaseAction;
import com.kurui.kums.base.Constant;
import com.kurui.kums.base.Inform;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.finance.AssetsItem;
import com.kurui.kums.finance.AssetsItemListForm;
import com.kurui.kums.finance.biz.AssetsItemBiz;
import com.kurui.kums.transaction.DataType;
import com.kurui.kums.transaction.biz.DataTypeBiz;

public class AssetsItemListAction extends BaseAction {
	private AssetsItemBiz assetsItemBiz;
	private DataTypeBiz dataTypeBiz;

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		AssetsItemListForm assetsItemListForm = (AssetsItemListForm) form;
		if (assetsItemListForm == null) {
			assetsItemListForm = new AssetsItemListForm();
		}
		try {
			List<AssetsItem> assetsItemList = assetsItemBiz
					.list(assetsItemListForm);
			assetsItemListForm.setList(assetsItemList);

			request.setAttribute("assetsItemListForm", assetsItemListForm);
			
			List<DataType> itemTypeList=dataTypeBiz.getSubDataTypeList("55");//资产项目
			request.setAttribute("itemTypeList", itemTypeList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("assetsItemListForm", assetsItemListForm);
		return mapping.findForward("listAssetsItem");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		AssetsItemListForm assetsItemListForm = (AssetsItemListForm) form;
		try {
			long assetsItemId = Constant.toLong(assetsItemListForm.getId());
			AssetsItem assetsItem = assetsItemBiz
					.getAssetsItemById(assetsItemId);
			request.setAttribute("assetsItem", assetsItem);

		} catch (Exception e) {
			e.printStackTrace();
		}
		forwardPage = "viewAssetsItem";
		return mapping.findForward(forwardPage);
	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		AssetsItemListForm alf = (AssetsItemListForm) form;
		AssetsItem assetsItem = new AssetsItem();
		assetsItem.setItemCount(Long.valueOf(1));

		assetsItem.setThisAction("insert");
		request.setAttribute("assetsItem", assetsItem);
		
		List<DataType> itemTypeList=dataTypeBiz.getSubDataTypeList("55");//资产项目
		request.setAttribute("itemTypeList", itemTypeList);

		String forwardPage = "editAssetsItem";
		return mapping.findForward(forwardPage);
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		Inform inf = new Inform();
		AssetsItemListForm assetsItemListForm = (AssetsItemListForm) form;

		long assetsItemId = assetsItemListForm.getId();
		if (assetsItemId < 1) {
			assetsItemId = assetsItemListForm.getSelectedItems()[0];
		}

		if (assetsItemId > 0) {
			AssetsItem assetsItem = assetsItemBiz
					.getAssetsItemById(assetsItemId);
			assetsItem.setThisAction("update");
			
			

			request.setAttribute("assetsItem", assetsItem);
			
			List<DataType> itemTypeList=dataTypeBiz.getSubDataTypeList("55");//资产项目
			request.setAttribute("itemTypeList", itemTypeList);
		} else {
			inf.setMessage("缺少assetsItemId");
			return forwardInformPage(inf, mapping, request);
		}
		return mapping.findForward("editAssetsItem");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		AssetsItemListForm assetsItemListForm = (AssetsItemListForm) form;
		String forwardPage = "";
		long id = 0;
		Inform inf = new Inform();
		int message = 0;
		try {
			for (int i = 0; i < assetsItemListForm.getSelectedItems().length; i++) {
				id = assetsItemListForm.getSelectedItems()[i];
				if (id > 0) {
					assetsItemBiz.deleteAssetsItem(id);
				}
			}
			return list(mapping, form, request, response);
		} catch (Exception ex) {
			ex.printStackTrace();
			inf.setMessage("删除失败" + ex.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}

	public void setAssetsItemBiz(AssetsItemBiz assetsItemBiz) {
		this.assetsItemBiz = assetsItemBiz;
	}

	public void setDataTypeBiz(DataTypeBiz dataTypeBiz) {
		this.dataTypeBiz = dataTypeBiz;
	}


}