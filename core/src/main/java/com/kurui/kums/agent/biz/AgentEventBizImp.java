package com.kurui.kums.agent.biz;

import java.util.List;

import com.kurui.kums.agent.biz.AgentEventBiz;
import com.kurui.kums.agent.AgentEvent;
import com.kurui.kums.agent.AgentEventListForm;
import com.kurui.kums.agent.dao.AgentEventDAO;
import com.kurui.kums.base.exception.AppException;

public class AgentEventBizImp implements AgentEventBiz {
	private AgentEventDAO agentEventDAO;

	public List list(AgentEventListForm agentEventListForm)
			throws AppException {
		return agentEventDAO.list(agentEventListForm);
	}

	public long delete(long id) throws AppException {
		try {
			agentEventDAO.delete(id);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public void deleteAgentEvent(Long id) throws AppException {
		AgentEvent agentEvent = getAgentEventById(id);
		agentEvent.setStatus(AgentEvent.STATES_0);// 将状态变为无效
		update(agentEvent);
	}

	public long save(AgentEvent agentEvent) throws AppException {
		return agentEventDAO.save(agentEvent);
	}

	public long update(AgentEvent agentEvent) throws AppException {
		return agentEventDAO.update(agentEvent);
	}

	public AgentEvent getAgentEventById(long id) throws AppException {
		return agentEventDAO.getAgentEventById(id);
	}

	public List<AgentEvent> getAgentEventList() throws AppException {
		return agentEventDAO.getAgentEventList();
	}

	public List<AgentEvent> getAgentEventList(Long type) throws AppException {
		return agentEventDAO.getAgentEventList(type);
	}

	public void setAgentEventDAO(AgentEventDAO agentEventDAO) {
		this.agentEventDAO = agentEventDAO;
	}
}
