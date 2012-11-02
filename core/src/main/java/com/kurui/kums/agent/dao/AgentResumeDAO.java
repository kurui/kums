package com.kurui.kums.agent.dao;

import java.util.List;

import com.kurui.kums.agent.AgentResume;
import com.kurui.kums.agent.AgentResumeListForm;
import com.kurui.kums.base.exception.AppException;

public interface AgentResumeDAO {
	public List list(AgentResumeListForm agentListForm) throws AppException;

	public void delete(long id) throws AppException;

	public long save(AgentResume agentResume) throws AppException;

	public long update(AgentResume agentResume) throws AppException;

	public AgentResume getAgentResumeById(long id) throws AppException;


	public List<AgentResume> getAgentResumeList() throws AppException;

	public List<AgentResume> getAgentResumeList(Long type) throws AppException;

	public List<AgentResume> getValidAgentResumeList() throws AppException;

	public List<AgentResume> getAgentResumeListByAgent(Long agentId) throws AppException;
}
