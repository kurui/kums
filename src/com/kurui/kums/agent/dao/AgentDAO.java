package com.kurui.kums.agent.dao;

import java.util.List;

import com.kurui.kums.agent.Agent;
import com.kurui.kums.agent.AgentListForm;
import com.kurui.kums.base.exception.AppException;

public interface AgentDAO {
	public List list(AgentListForm agentListForm) throws AppException;

	public List listGrade(AgentListForm agentListForm) throws AppException;

	public List listDirect(AgentListForm agentListForm) throws AppException;

	public void delete(long id) throws AppException;

	public long save(Agent agent) throws AppException;

	public long update(Agent agent) throws AppException;

	public void updatePhysicalAmount(long agentId) throws AppException;

	public void updateVirtualAmount(long agentId) throws AppException;

	public void updateTotalIntegral(long agentId) throws AppException;

	public Agent getAgentById(long agentId) throws AppException;

	public Agent getAgentByNo(String agentNo) throws AppException;

	public List<Agent> getAgentList() throws AppException;

	public List<Agent> getAgentList(Long type) throws AppException;

	public List<Agent> getValidAgentList() throws AppException;
}
