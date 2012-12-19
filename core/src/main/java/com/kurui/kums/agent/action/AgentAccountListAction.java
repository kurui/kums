package com.kurui.kums.agent.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.kurui.kums.agent.Agent;
import com.kurui.kums.agent.AgentAccount;
import com.kurui.kums.agent.AgentAccountListForm;
import com.kurui.kums.agent.biz.AgentAccountBiz;
import com.kurui.kums.agent.biz.AgentBiz;
import com.kurui.kums.base.struts.BaseAction;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.library.Account;
import com.kurui.kums.library.PaymentTool;
import com.kurui.kums.library.biz.PaymentToolBiz;

public class AgentAccountListAction extends BaseAction {
	private AgentAccountBiz agentAccountBiz;
	private AgentBiz agentBiz;
	private PaymentToolBiz paymentToolBiz;

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		AgentAccountListForm agentAccountListForm = (AgentAccountListForm) form;

		if (agentAccountListForm == null) {
			agentAccountListForm = new AgentAccountListForm();
		}
		try {
			List<AgentAccount> agentAccountList = agentAccountBiz
					.list(agentAccountListForm);
			agentAccountListForm.setList(agentAccountList);

			List<PaymentTool> paymentToolList = paymentToolBiz
					.getPaymentToolList();
			request.setAttribute("paymentToolList", paymentToolList);
			
			long agentId=agentAccountListForm.getAgentId();
			if(agentId>0){
				Agent agent=agentBiz.getAgentById(agentId);
				request.setAttribute("agent",agent);
			}			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("agentAccountListForm", agentAccountListForm);
		return mapping.findForward("listAgentAccount");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		AgentAccountListForm alf = (AgentAccountListForm) form;
		AgentAccount agentAccount = new AgentAccount();
		agentAccount.setAccountType(alf.getAccountType());
		
		long agentId = alf.getAgentId();
		Agent agent = agentBiz.getAgentById(agentId);
		agentAccount.setAgent(agent);

		Account account = new Account();
		account.setBusinessType(Account.BUSINESS_TYPE_1);
		account.setPersonalType(Account.PERSONAL_TYPE_1);
		account.setTranType(Account.TRAN_TYPE_3);
		account.setType(Account.TYPE_11);
		account.setStatus(Account.STATES_1);
		
		agentAccount.setAccount(account);

		List<PaymentTool> paymentToolList = paymentToolBiz.getPaymentToolList();
		request.setAttribute("paymentToolList", paymentToolList);

		agentAccount.setThisAction("insert");
		request.setAttribute("agentAccount", agentAccount);

		String forwardPage = "editAgentAccount";
		return mapping.findForward(forwardPage);
	}

	public void setPaymentToolBiz(PaymentToolBiz paymentToolBiz) {
		this.paymentToolBiz = paymentToolBiz;
	}

	public void setAgentAccountBiz(AgentAccountBiz agentAccountBiz) {
		this.agentAccountBiz = agentAccountBiz;
	}

	public void setAgentBiz(AgentBiz agentBiz) {
		this.agentBiz = agentBiz;
	}
}