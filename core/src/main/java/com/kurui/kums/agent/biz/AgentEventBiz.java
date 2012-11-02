package com.kurui.kums.agent.biz;

import java.util.List;

import com.kurui.kums.agent.AgentEvent;
import com.kurui.kums.agent.AgentEventListForm;
import com.kurui.kums.base.exception.AppException;

public interface AgentEventBiz {

	public List list(AgentEventListForm agentEventListForm) throws AppException;

	public long delete(long id) throws AppException;

	public void deleteAgentEvent(Long id) throws AppException;

	public long save(AgentEvent agentEvent) throws AppException;

	public long update(AgentEvent agentEvent) throws AppException;

	public AgentEvent getAgentEventById(long id) throws AppException;

	public List<AgentEvent> getAgentEventList() throws AppException;

	public List<AgentEvent> getAgentEventList(Long type) throws AppException;

}
