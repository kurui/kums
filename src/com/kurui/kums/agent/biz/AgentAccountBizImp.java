package com.kurui.kums.agent.biz;

import java.util.List;
import com.kurui.kums.agent.AgentAccount;
import com.kurui.kums.agent.AgentAccountListForm;
import com.kurui.kums.agent.dao.AgentAccountDAO;
import com.kurui.kums.base.exception.AppException;

public class AgentAccountBizImp implements AgentAccountBiz {
	private AgentAccountDAO agentAccountDAO;

	public List list(AgentAccountListForm agentAccountListForm)
			throws AppException {
		return agentAccountDAO.list(agentAccountListForm);
	}

	public void delete(long id) throws AppException {
		agentAccountDAO.delete(id);
	}

	public long save(AgentAccount agentAccount) throws AppException {
		return agentAccountDAO.save(agentAccount);
	}

	public long update(AgentAccount agentAccount) throws AppException {
		return agentAccountDAO.update(agentAccount);
	}

	public AgentAccount getAgentAccountByAgentId(long agentId)
			throws AppException {
		return agentAccountDAO.getAgentAccountByAgentId(agentId);
	}

	public AgentAccount getAgentAccountByAccountId(long accountId)
			throws AppException {
		return agentAccountDAO.getAgentAccountByAccountId(accountId);
	}

	public List<AgentAccount> getAgentAccountList() throws AppException {
		return agentAccountDAO.getAgentAccountList();
	}

	public List<AgentAccount> getAgentAccountList(Long type)
			throws AppException {
		return agentAccountDAO.getAgentAccountList(type);
	}

	public List<AgentAccount> getValidAgentAccountList() throws AppException {
		return agentAccountDAO.getValidAgentAccountList();
	}

	public void setAgentAccountDAO(AgentAccountDAO agentAccountDAO) {
		this.agentAccountDAO = agentAccountDAO;
	}

}
