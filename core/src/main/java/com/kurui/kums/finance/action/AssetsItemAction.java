package com.kurui.kums.finance.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;

import com.kurui.kums.agent.Agent;
import com.kurui.kums.agent.biz.AgentBiz;
import com.kurui.kums.base.BaseAction;
import com.kurui.kums.base.Inform;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.finance.AssetsItem;
import com.kurui.kums.finance.biz.AssetsItemBiz;

public class AssetsItemAction extends BaseAction {
	private AgentBiz agentBiz;
	private AssetsItemBiz assetsItemBiz;

	public ActionForward insert(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		AssetsItem assetsItemForm = (AssetsItem) form;
		Inform inf = new Inform();

		try {
			long agentId = assetsItemForm.getAgentId();

			if (agentId > 0) {
				Agent agent = agentBiz.getAgentById(agentId);
				if (agent != null) {

					AssetsItem assetsItem = new AssetsItem();
					assetsItem.setName(assetsItemForm.getName());
					assetsItem.setItemNo(assetsItemForm.getItemNo());
					assetsItem.setItemType(assetsItemForm.getItemType());
					assetsItem.setItemCount(assetsItemForm.getItemCount());	

					assetsItem.setMemo(assetsItemForm.getMemo());

					assetsItem.setType(AssetsItem.TYPE_1);
					assetsItem.setStatus(AssetsItem.STATES_1);

					long newAssetsItemId = assetsItemBiz.save(assetsItem);

					if (newAssetsItemId > 0) {
						return redirect(assetsItem);
					} else {
						inf.setMessage("保存车辆异常");
					}
				} else {
					inf.setMessage("客户不能为空");
				}
			} else {
				inf.setMessage("客户ID不能为空");
			}
		} catch (Exception e) {
			e.printStackTrace();
			inf.setMessage("增加客户账号：" + e.getMessage());
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
				AssetsItem assetsItem = assetsItemBiz.getAssetsItemById(assetsItemId);

				long agentId = assetsItemForm.getAgentId();
				if (agentId > 0) {
					Agent agent = agentBiz.getAgentById(agentId);
					if (agent != null) {
						assetsItem.setName(assetsItemForm.getName());
						assetsItem.setItemNo(assetsItemForm.getItemNo());
						assetsItem.setItemType(assetsItemForm.getItemType());
						assetsItem.setItemCount(assetsItemForm.getItemCount());						

						assetsItem.setMemo(assetsItemForm.getMemo());

						assetsItem.setType(AssetsItem.TYPE_1);
						assetsItem.setStatus(AssetsItem.STATES_1);
						assetsItem.setStatus(assetsItemForm.getStatus());
						long flag = assetsItemBiz.update(assetsItem);

						if (flag > 0) {
							return redirect(assetsItem);
						} else {
							inf.setMessage("修改资产项目异常!");
						}
					} else {
						inf.setMessage("缺少agent");
					}
				} else {
					inf.setMessage("缺少agentId");
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
		ActionRedirect redirect = new ActionRedirect("/finance/assetsItemList.do");
		redirect.addParameter("thisAction", "view");
		redirect.addParameter("id", assetsItem.getId());
		return redirect;
	}

	public void setAgentBiz(AgentBiz agentBiz) {
		this.agentBiz = agentBiz;
	}

	public void setAssetsItemBiz(AssetsItemBiz assetsItemBiz) {
		this.assetsItemBiz = assetsItemBiz;
	}

}