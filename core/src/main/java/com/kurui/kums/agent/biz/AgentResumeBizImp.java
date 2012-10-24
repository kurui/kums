package com.kurui.kums.agent.biz;

import java.util.List;

import com.kurui.kums.agent.AgentResume;
import com.kurui.kums.agent.AgentResumeListForm;
import com.kurui.kums.agent.dao.AgentResumeDAO;
import com.kurui.kums.base.exception.AppException;

public class AgentResumeBizImp implements AgentResumeBiz {
	private AgentResumeDAO agentResumeDAO;

	public List list(AgentResumeListForm agentResumeListForm)
			throws AppException {
		return agentResumeDAO.list(agentResumeListForm);
	}

	public long delete(long id) throws AppException {
		try {
			agentResumeDAO.delete(id);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public void deleteAgentResume(Long id) throws AppException {
		AgentResume agentResume = getAgentResumeById(id);
		agentResume.setStatus(AgentResume.STATES_0);// 将状态变为无效
		update(agentResume);
	}

	public long save(AgentResume agentResume) throws AppException {
		return agentResumeDAO.save(agentResume);
	}

	public long update(AgentResume agentResume) throws AppException {
		return agentResumeDAO.update(agentResume);
	}

	public AgentResume getAgentResumeById(long id) throws AppException {
		return agentResumeDAO.getAgentResumeById(id);
	}

	public List<AgentResume> getAgentResumeList() throws AppException {
		return agentResumeDAO.getAgentResumeList();
	}

	public List<AgentResume> getAgentResumeList(Long type) throws AppException {
		return agentResumeDAO.getAgentResumeList(type);
	}

	public void setAgentResumeDAO(AgentResumeDAO agentResumeDAO) {
		this.agentResumeDAO = agentResumeDAO;
	}
}
