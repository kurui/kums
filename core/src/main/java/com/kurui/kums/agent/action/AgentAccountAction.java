package com.kurui.kums.agent.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;

import com.kurui.kums.agent.Agent;
import com.kurui.kums.agent.AgentAccount;
import com.kurui.kums.agent.biz.AgentAccountBiz;
import com.kurui.kums.agent.biz.AgentBiz;
import com.kurui.kums.base.BaseAction;
import com.kurui.kums.base.Inform;
import com.kurui.kums.base.KumsDataStoreListener;
import com.kurui.kums.base.MainTask;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.system.biz.SysInitBiz;
import com.kurui.kums.transaction.Account;
import com.kurui.kums.transaction.PaymentTool;
import com.kurui.kums.transaction.biz.AccountBiz;
import com.kurui.kums.transaction.biz.PaymentToolBiz;

public class AgentAccountAction extends BaseAction {
	private AccountBiz accountBiz;
	private AgentBiz agentBiz;
	private AgentAccountBiz agentAccountBiz;
	private PaymentToolBiz paymentToolBiz;
	private SysInitBiz sysInitBiz;

	public ActionForward insert(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
//		String forwardPage = "";
		AgentAccount agentAccountForm = (AgentAccount) form;
		Inform inf = new Inform();
//		UserRightInfo uri = (UserRightInfo) request.getSession().getAttribute(
//				"URI");
		try {
			long agentId = agentAccountForm.getAgentId();

			if (agentId > 0) {				
				Agent agent = agentBiz.getAgentById(agentId);
				long paymentToolId = agentAccountForm.getPaymentToolId();
				if (paymentToolId > 0) {
					Account account = new Account();
					PaymentTool paymentTool = paymentToolBiz
							.getPaymentToolById(paymentToolId);
					account.setName(agentAccountForm.getAccountName());
					account.setAccountNo(agentAccountForm.getAccountNo());
					account.setAccountAddress(agentAccountForm.getAccountAddress());
					
					account.setBusinessType(agentAccountForm.getAccountBusinessType());
					account.setPersonalType(agentAccountForm.getAccountPersonalType());
					account.setTranType(agentAccountForm.getAccountTranType());
					account.setType(Account.TYPE_11);
					account.setDescription(agentAccountForm
							.getAccountDescription());
					account.setStatus(agentAccountForm.getAccountStatus());
					account.setPaymentTool(paymentTool);
					long newAccountId = accountBiz.save(account);					
					if(newAccountId>0){		
						AgentAccount agentAccount = new AgentAccount();
						agent=agentBiz.getAgentById(agentId);
						agentAccount.setAgent(agent);
						account=accountBiz.getAccountById(newAccountId);
						agentAccount.setAccount(account);
						
						agentAccount.setStatus(AgentAccount.STATES_1);

						long newAgentAccountId = agentAccountBiz.save(agentAccount);
						
						// --更新静态库
						KumsDataStoreListener listener = new KumsDataStoreListener(
								sysInitBiz, "Account");
						MainTask.put(listener);
						// ---------
						
						if (newAgentAccountId > 0) {
							ActionRedirect redirect = new ActionRedirect(
									"/agent/agentAccountList.do?thisAction=list");
							return redirect;
						} else {
							inf.setMessage("保存客户账号异常");
						}
					}else{
						inf.setMessage("账号保存异常");
					}					
				}else{
					inf.setMessage("支付工具ID不能为空");
				}
			}else{
				inf.setMessage("客户ID不能为空");
			}
		} catch (Exception e) {
			e.printStackTrace();
			inf.setMessage("增加客户账号："+e.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}

	public void setSysInitBiz(SysInitBiz sysInitBiz) {
		this.sysInitBiz = sysInitBiz;
	}

	public void setPaymentToolBiz(PaymentToolBiz paymentToolBiz) {
		this.paymentToolBiz = paymentToolBiz;
	}

	public void setAccountBiz(AccountBiz accountBiz) {
		this.accountBiz = accountBiz;
	}

	public void setAgentBiz(AgentBiz agentBiz) {
		this.agentBiz = agentBiz;
	}

	public void setAgentAccountBiz(AgentAccountBiz agentAccountBiz) {
		this.agentAccountBiz = agentAccountBiz;
	}

}
