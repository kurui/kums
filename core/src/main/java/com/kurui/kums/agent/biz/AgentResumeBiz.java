package com.kurui.kums.agent.biz;

import java.util.List;

import com.kurui.kums.agent.AgentResume;
import com.kurui.kums.agent.AgentResumeListForm;
import com.kurui.kums.base.exception.AppException;

public interface AgentResumeBiz {

	public List list(AgentResumeListForm agentResumeListForm) throws AppException;

	public long delete(long id) throws AppException;

	public void deleteAgentResume(Long id) throws AppException;

	public long save(AgentResume agentResume) throws AppException;

	public long update(AgentResume agentResume) throws AppException;

	public AgentResume getAgentResumeById(long id) throws AppException;

	public List<AgentResume> getAgentResumeList() throws AppException;

	public List<AgentResume> getAgentResumeList(Long type) throws AppException;

	public List<AgentResume> getAgentResumeListByAgent(Long agentId)throws AppException;

}
