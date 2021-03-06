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
import com.kurui.kums.base.util.StringUtil;
import com.kurui.kums.finance.AssetsItem;
import com.kurui.kums.finance.FinanceOrder;
import com.kurui.kums.finance.biz.AssetsItemBiz;
import com.kurui.kums.finance.biz.FinanceOrderBiz;

public class AssetsItemAction extends BaseAction {
	private AssetsItemBiz assetsItemBiz;
	private FinanceOrderBiz financeOrderBiz;
	private KumsNoUtil noUtil;

	public ActionForward editBatchReset(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		AssetsItem assetsItemForm = (AssetsItem) form;
		Inform inf = new Inform();

		try {
			String assetsItemIdGroup = assetsItemForm.getAssetsItemIdGroup();

			if (StringUtil.isEmpty(assetsItemIdGroup) == false) {
				String[] assetsItemIds = StringUtil.getSplitString(
						assetsItemIdGroup, ",");

				if (assetsItemIds != null && assetsItemIds.length > 0) {
					for (int i = 0; i < assetsItemIds.length; i++) {
						long assetsItemId = Long.parseLong(assetsItemIds[i]);
						AssetsItem assetsItem = assetsItemBiz
								.getAssetsItemById(assetsItemId);

						if (assetsItem != null) {
							assetsItem
									.setItemType(assetsItemForm.getItemType());
							assetsItem
									.setAreaCode(assetsItemForm.getAreaCode());
							assetsItem.setMemo(assetsItem.getMemo()
									+ assetsItemForm.getMemo());// 追加Memo

							assetsItem.setType(assetsItemForm.getType());
							assetsItem.setStatus(assetsItemForm.getStatus());
							assetsItem.setUpdateTime(new Timestamp(System
									.currentTimeMillis()));
							assetsItemBiz.update(assetsItem);
						}

					}
				}

			}

			inf.setMessage("设置资产项目成功");
			inf.setForwardPage("/finance/assetsItemList.do");
			inf.setParamId("thisAction");
			inf.setParamValue("list");
			inf.setParamId("status");
			inf.setParamValue(AssetsItem.STATES_1 + "");
			inf.setClose(true);

		} catch (Exception e) {
			e.printStackTrace();
			inf.setMessage("增加资产项目：" + e.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}

	public ActionForward insertAsFinance(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws AppException {
		AssetsItem assetsItemForm = (AssetsItem) form;
		Inform inf = new Inform();

		try {

			String financeOrderIdGroup = assetsItemForm
					.getFinanceOrderIdGroup();

			if (StringUtil.isEmpty(financeOrderIdGroup) == false) {
				String[] financeOrderIds = StringUtil.getSplitString(
						financeOrderIdGroup, ",");
				if (financeOrderIds != null && financeOrderIds.length > 0) {
					for (int i = 0; i < financeOrderIds.length; i++) {
						long financeOrderId = Long
								.parseLong(financeOrderIds[i]);
						FinanceOrder financeOrder = financeOrderBiz
								.getFinanceOrderById(financeOrderId);

						if (financeOrder != null) {
							AssetsItem assetsItem = new AssetsItem();
							assetsItem.setFinanceOrderId(financeOrderId);
							assetsItem.setName(financeOrder.getMemo());
							assetsItem.setItemNo(noUtil.getAssetsItemNo());
							assetsItem.setItemType(null);
							assetsItem.setItemCount(Long.valueOf(1));
							assetsItem.setValuation(financeOrder
									.getTotalAmount());
							assetsItem.setAreaCode("珠海");
							assetsItem.setMemo(financeOrder.getTranTypeText()
									+ "," + financeOrder.getMemo());

							assetsItem.setType(AssetsItem.TYPE_1);
							assetsItem.setStatus(AssetsItem.STATES_1);
							assetsItem.setUpdateTime(new Timestamp(System
									.currentTimeMillis()));
							assetsItem.setLastDeprecTime(assetsItem
									.getUpdateTime());
							assetsItemBiz.save(assetsItem);
						}

					}
				}

			}

			inf.setMessage("转存资产项目成功");
			inf.setForwardPage("/finance/assetsItemList.do");
			inf.setParamId("thisAction");
			inf.setParamValue("list");
			inf.setClose(true);

		} catch (Exception e) {
			e.printStackTrace();
			inf.setMessage("增加资产项目：" + e.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}

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
			assetsItem.setValuation(assetsItemForm.getValuation());
			assetsItem.setAreaCode(assetsItemForm.getAreaCode());

			assetsItem.setMemo(assetsItemForm.getMemo());

			assetsItem.setType(AssetsItem.TYPE_1);
			assetsItem.setStatus(AssetsItem.STATES_1);
			assetsItem.setUpdateTime(new Timestamp(System.currentTimeMillis()));
			assetsItem.setLastDeprecTime(assetsItem.getUpdateTime());
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
				assetsItem.setValuation(assetsItemForm.getValuation());
				assetsItem.setAreaCode(assetsItemForm.getAreaCode());

				assetsItem.setMemo(assetsItemForm.getMemo());

				assetsItem.setType(AssetsItem.TYPE_1);
				assetsItem.setStatus(AssetsItem.STATES_1);
				assetsItem.setStatus(assetsItemForm.getStatus());
				assetsItem.setUpdateTime(new Timestamp(System
						.currentTimeMillis()));

				long flag = assetsItemBiz.update(assetsItem);

				if (flag > 0) {
					// return redirect(assetsItem);

					String forwardUrl = "/finance/assetsItemList.do?thisAction=list";

					if (assetsItemForm.getLastAction() != "") {
						if ("viewALL".equals(assetsItemForm.getLastAction())) {
							forwardUrl = "/agent/assetsItemList.do?thisAction=view&id="
									+ assetsItem.getId();
						} else if ("list"
								.equals(assetsItemForm.getLastAction())) {
							forwardUrl += "&intPage="
									+ assetsItemForm.getIntPage();
							forwardUrl += "&pageCount="
									+ assetsItemForm.getPageCount();
						}
					}

					return new ActionRedirect(forwardUrl);

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

	public void setFinanceOrderBiz(FinanceOrderBiz financeOrderBiz) {
		this.financeOrderBiz = financeOrderBiz;
	}

}