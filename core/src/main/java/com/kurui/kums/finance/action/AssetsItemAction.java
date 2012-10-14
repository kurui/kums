package com.kurui.kums.finance.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;

import com.kurui.kums.base.BaseAction;
import com.kurui.kums.base.Inform;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.base.util.KumsNoUtil;
import com.kurui.kums.finance.AssetsItem;
import com.kurui.kums.finance.biz.AssetsItemBiz;
import com.kurui.kums.transaction.biz.DataTypeBiz;

public class AssetsItemAction extends BaseAction {
	private AssetsItemBiz assetsItemBiz;
	private KumsNoUtil noUtil;
	private DataTypeBiz dataTypeBiz;

	public ActionForward insert(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		AssetsItem assetsItemForm = (AssetsItem) form;
		Inform inf = new Inform();

		try {
			AssetsItem assetsItem = new AssetsItem();
			assetsItem.setName(assetsItemForm.getName());
			assetsItem.setItemNo(noUtil.getAssetsItemNo());
			assetsItem.setItemType(assetsItemForm.getItemType());
			assetsItem.setItemCount(assetsItemForm.getItemCount());

			assetsItem.setMemo(assetsItemForm.getMemo());

			assetsItem.setType(AssetsItem.TYPE_1);
			assetsItem.setStatus(AssetsItem.STATES_1);
			assetsItem.setUpdateTime(new Timestamp(System.currentTimeMillis()));
			long newAssetsItemId = assetsItemBiz.save(assetsItem);

			if (newAssetsItemId > 0) {
				return redirect(assetsItem);
			} else {
				inf.setMessage("保存资产项目");
			}

		} catch (Exception e) {
			e.printStackTrace();
			inf.setMessage("增加资产项目：" + e.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}

	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		AssetsItem assetsItemForm = (AssetsItem) form;
		Inform inf = new Inform();
		try {
			long assetsItemId = assetsItemForm.getId();
			if (assetsItemId > 0) {
				AssetsItem assetsItem = assetsItemBiz
						.getAssetsItemById(assetsItemId);

				assetsItem.setName(assetsItemForm.getName());
				assetsItem.setItemType(assetsItemForm.getItemType());
				assetsItem.setItemCount(assetsItemForm.getItemCount());

				assetsItem.setMemo(assetsItemForm.getMemo());

				assetsItem.setType(AssetsItem.TYPE_1);
				assetsItem.setStatus(AssetsItem.STATES_1);
				assetsItem.setStatus(assetsItemForm.getStatus());
				assetsItem.setUpdateTime(new Timestamp(System.currentTimeMillis()));
				long flag = assetsItemBiz.update(assetsItem);

				if (flag > 0) {
					return redirect(assetsItem);
				} else {
					inf.setMessage("修改资产项目异常!");
				}

			} else {
				inf.setMessage("缺少assetsItemId");
			}
		} catch (Exception e) {
			e.printStackTrace();
			inf.setMessage("异常:" + e.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}

	public ActionRedirect redirect(AssetsItem assetsItem) {
		ActionRedirect redirect = new ActionRedirect(
				"/finance/assetsItemList.do");
		redirect.addParameter("thisAction", "view");
		redirect.addParameter("id", assetsItem.getId());
		return redirect;
	}

	public void setAssetsItemBiz(AssetsItemBiz assetsItemBiz) {
		this.assetsItemBiz = assetsItemBiz;
	}
	
	public void setNoUtil(KumsNoUtil noUtil) {
		this.noUtil = noUtil;
	}

	public void setDataTypeBiz(DataTypeBiz dataTypeBiz) {
		this.dataTypeBiz = dataTypeBiz;
	}
	
	

}