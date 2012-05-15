package com.kurui.kums.agent.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;
import com.kurui.kums.agent.Agent;
import com.kurui.kums.agent.AgentHabit;
import com.kurui.kums.agent.ShareHolder;
import com.kurui.kums.agent.biz.ShareHolderBiz;
import com.kurui.kums.agent.biz.AgentBiz;
import com.kurui.kums.base.BaseAction;
import com.kurui.kums.base.Inform;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.right.UserRightInfo;
import com.kurui.kums.transaction.Company;
import com.kurui.kums.transaction.biz.CompanyBiz;

public class ShareHolderAction extends BaseAction {
	private AgentBiz agentBiz;
	private CompanyBiz companyBiz;
	private ShareHolderBiz shareHolderBiz;

	public ActionForward insert(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		ShareHolder shareHolderForm = (ShareHolder) form;
		Inform inf = new Inform();

		try {
			long agentId = shareHolderForm.getAgentId();

			if (agentId > 0) {
				Agent agent = agentBiz.getAgentById(agentId);

				long companyId = shareHolderForm.getCompanyId();
				if (companyId > 0) {
					Company company = companyBiz.getCompanyById(companyId);

					ShareHolder shareHolder = new ShareHolder();
					shareHolder.setAgent(agent);
					shareHolder.setCompany(company);
					shareHolder.setCapitalAmount(shareHolderForm
							.getCapitalAmount());
					shareHolder.setControlType(shareHolderForm.getControlType());
					shareHolder.setMemo(shareHolderForm.getMemo());
					shareHolder.setStatus(ShareHolder.STATES_1);

					long newShareHolderId = shareHolderBiz.save(shareHolder);

					if (newShareHolderId > 0) {
						ActionRedirect redirect = new ActionRedirect(
								"/agent/shareHolderList.do?thisAction=list");
						return redirect;
					} else {
						inf.setMessage("保存客户账号异常");
					}
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
		ShareHolder shareHolderForm = (ShareHolder) form;
		Inform inf = new Inform();
		try {
			long shareHolderId = shareHolderForm.getId();
			if (shareHolderId > 0) {
				ShareHolder shareHolder = shareHolderBiz
						.getShareHolderById(shareHolderId);

				long agentId = shareHolderForm.getAgentId();
				if (agentId > 0) {
					Agent agent = agentBiz.getAgentById(agentId);

					long companyId = shareHolderForm.getCompanyId();
					if (companyId > 0) {

						Company company = companyBiz.getCompanyById(companyId);
						shareHolder.setAgent(agent);
						shareHolder.setCompany(company);
						shareHolder.setCapitalAmount(shareHolderForm
								.getCapitalAmount());
						shareHolder.setControlType(shareHolderForm.getControlType());
						shareHolder.setMemo(shareHolderForm.getMemo());
						shareHolder.setStatus(shareHolderForm.getStatus());
						long flag = shareHolderBiz.update(shareHolder);

						if (flag > 0) {
							return new ActionRedirect(
									"/agent/shareHolderList.do?thisAction=list");
						} else {
							inf.setMessage("修改股东数据异常!");
						}
					} else {
						inf.setMessage("缺少companyId");
					}
				} else {
					inf.setMessage("缺少agentId");
				}
			} else {
				inf.setMessage("缺少shareHolderId");
			}
		} catch (Exception e) {
			e.printStackTrace();
			inf.setMessage("异常:" + e.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}

	public void setAgentBiz(AgentBiz agentBiz) {
		this.agentBiz = agentBiz;
	}

	public void setCompanyBiz(CompanyBiz companyBiz) {
		this.companyBiz = companyBiz;
	}

	public void setShareHolderBiz(ShareHolderBiz shareHolderBiz) {
		this.shareHolderBiz = shareHolderBiz;
	}

}
