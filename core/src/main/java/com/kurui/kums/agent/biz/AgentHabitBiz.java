package com.kurui.kums.agent.biz;

import java.util.List;

import com.kurui.kums.agent.AgentHabit;
import com.kurui.kums.agent.AgentHabitListForm;
import com.kurui.kums.base.exception.AppException;

public interface AgentHabitBiz {

	public List list(AgentHabitListForm agentHabitListForm) throws AppException;

	public long delete(long id) throws AppException;

	public void deleteAgentHabit(Long id) throws AppException;

	public long save(AgentHabit agentHabit) throws AppException;

	public long update(AgentHabit agentHabit) throws AppException;

	public AgentHabit getAgentHabitById(long id) throws AppException;

	public List<AgentHabit> getAgentHabitList() throws AppException;

	public List<AgentHabit> getAgentHabitList(Long type) throws AppException;

}
