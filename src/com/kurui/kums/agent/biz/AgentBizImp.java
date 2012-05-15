package com.kurui.kums.agent.biz;

import java.util.List;
import com.kurui.kums.agent.biz.AgentBiz;
import com.kurui.kums.agent.Agent;
import com.kurui.kums.agent.AgentListForm;
import com.kurui.kums.agent.dao.AgentDAO;
import com.kurui.kums.base.exception.AppException;

public class AgentBizImp implements AgentBiz {
	private AgentDAO agentDAO;

	public List list(AgentListForm agentListForm) throws AppException {
		return agentDAO.list(agentListForm);
	}
	
	public List listGrade(AgentListForm agentListForm) throws AppException {
		return agentDAO.listGrade(agentListForm);
	}
	
	public List listDirect(AgentListForm agentListForm) throws AppException {
		return agentDAO.listDirect(agentListForm);
	}

	public long delete(long id) throws AppException {
		try {
			agentDAO.delete(id);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public void deleteAgent(Long id) throws AppException {
		Agent agent = getAgentById(id);
		agent.setStatus(Agent.STATES_0);// 将状态变为无效
		update(agent);
	}

	public long save(Agent agent) throws AppException {
		return agentDAO.save(agent);
	}

	public long update(Agent agent) throws AppException {
		return agentDAO.update(agent);
	}

	public long updateStatistic(long agentId)throws AppException{
		 agentDAO.updatePhysicalAmount(agentId);
		 agentDAO.updateVirtualAmount(agentId);
		 agentDAO.updateTotalIntegral(agentId);
		 return 1;
	}
	public Agent getAgentById(long agentId) throws AppException {
		return agentDAO.getAgentById(agentId);
	}

	public List<Agent> getAgentList() throws AppException {
		return agentDAO.getAgentList();
	}

	public List<Agent> getAgentList(Long type) throws AppException {
		return agentDAO.getAgentList(type);
	}
	
	public List<Agent> getValidAgentList() throws AppException{
		return agentDAO.getValidAgentList();
	}

	public void setAgentDAO(AgentDAO agentDAO) {
		this.agentDAO = agentDAO;
	}
}
