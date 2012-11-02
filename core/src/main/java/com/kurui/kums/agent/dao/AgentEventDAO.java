package com.kurui.kums.agent.dao;

import java.util.List;

import com.kurui.kums.agent.AgentEvent;
import com.kurui.kums.agent.AgentEventListForm;
import com.kurui.kums.base.exception.AppException;

public interface AgentEventDAO {
	public List list(AgentEventListForm agentListForm) throws AppException;

	public void delete(long id) throws AppException;

	public long save(AgentEvent agentEvent) throws AppException;

	public long update(AgentEvent agentEvent) throws AppException;

	public AgentEvent getAgentEventById(long id) throws AppException;

	public AgentEvent getAgentEventByAgentId(long agentId) throws AppException;

	public List<AgentEvent> getAgentEventList() throws AppException;

	public List<AgentEvent> getAgentEventList(Long type) throws AppException;

	public List<AgentEvent> getValidAgentEventList() throws AppException;
}
