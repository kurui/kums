package com.kurui.kums.agent.biz;

import java.util.List;

import com.kurui.kums.agent.AgentAction;
import com.kurui.kums.agent.AgentActionListForm;
import com.kurui.kums.base.exception.AppException;

public interface AgentActionBiz {

	public List list(AgentActionListForm agentActionListForm) throws AppException;

	public long delete(long id) throws AppException;

	public void deleteAgentAction(Long id) throws AppException;

	public long save(AgentAction agentAction) throws AppException;

	public long update(AgentAction agentAction) throws AppException;

	public AgentAction getAgentActionById(long id) throws AppException;

	public List<AgentAction> getAgentActionList() throws AppException;

	public List<AgentAction> getAgentActionList(Long type) throws AppException;

}
