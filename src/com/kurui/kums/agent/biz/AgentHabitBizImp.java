package com.kurui.kums.agent.biz;

import java.util.List;

import com.kurui.kums.agent.biz.AgentHabitBiz;
import com.kurui.kums.agent.AgentHabit;
import com.kurui.kums.agent.AgentHabitListForm;
import com.kurui.kums.agent.dao.AgentHabitDAO;
import com.kurui.kums.base.exception.AppException;

public class AgentHabitBizImp implements AgentHabitBiz {
	private AgentHabitDAO agentHabitDAO;

	public List list(AgentHabitListForm agentHabitListForm) throws AppException {
		return agentHabitDAO.list(agentHabitListForm);
	}

	public long delete(long id) throws AppException {
		try {
			agentHabitDAO.delete(id);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public void deleteAgentHabit(Long id) throws AppException {
		AgentHabit agentHabit = getAgentHabitById(id);
		agentHabit.setStatus(AgentHabit.STATES_0);// 将状态变为无效
		update(agentHabit);
	}

	public long save(AgentHabit agentHabit) throws AppException {
		return agentHabitDAO.save(agentHabit);
	}

	public long update(AgentHabit agentHabit) throws AppException {
		return agentHabitDAO.update(agentHabit);
	}

	public AgentHabit getAgentHabitById(long id) throws AppException {
		return agentHabitDAO.getAgentHabitById(id);
	}

	public List<AgentHabit> getAgentHabitList() throws AppException {
		return agentHabitDAO.getAgentHabitList();
	}

	public List<AgentHabit> getAgentHabitList(Long type) throws AppException {
		return agentHabitDAO.getAgentHabitList(type);
	}

	public void setAgentHabitDAO(AgentHabitDAO agentHabitDAO) {
		this.agentHabitDAO = agentHabitDAO;
	}
}
