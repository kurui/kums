package com.kurui.kums.agent.dao;

import java.util.List;

import com.kurui.kums.agent.AgentAction;
import com.kurui.kums.agent.AgentActionListForm;
import com.kurui.kums.base.exception.AppException;

public interface AgentActionDAO {
	public List list(AgentActionListForm agentListForm) throws AppException;

	public void delete(long id) throws AppException;

	public long save(AgentAction agentAction) throws AppException;

	public long update(AgentAction agentAction) throws AppException;

	public AgentAction getAgentActionById(long id) throws AppException;

	public AgentAction getAgentActionByAgentId(long agentId) throws AppException;

	public List<AgentAction> getAgentActionList() throws AppException;

	public List<AgentAction> getAgentActionList(Long type) throws AppException;

	public List<AgentAction> getValidAgentActionList() throws AppException;
}
