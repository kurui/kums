package com.kurui.kums.agent.dao;

import java.util.List;

import com.kurui.kums.agent.AgentAccount;
import com.kurui.kums.agent.AgentAccountListForm;
import com.kurui.kums.base.exception.AppException;

public interface AgentAccountDAO {
	public List list(AgentAccountListForm agentListForm) throws AppException;

	public void delete(long id) throws AppException;

	public long save(AgentAccount agentAccount) throws AppException;

	public long update(AgentAccount agentAccount) throws AppException;

	public AgentAccount getAgentAccountByAgentId(long agentId) throws AppException;

	public AgentAccount getAgentAccountByAccountId(long accountId)
			throws AppException;

	public List<AgentAccount> getAgentAccountList() throws AppException;

	public List<AgentAccount> getAgentAccountList(Long type)
			throws AppException;

	public List<AgentAccount> getValidAgentAccountList() throws AppException;
}
