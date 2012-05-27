package com.kurui.kums.agent.biz;

import java.util.List;

import com.kurui.kums.agent.biz.AgentActionBiz;
import com.kurui.kums.agent.AgentAction;
import com.kurui.kums.agent.AgentActionListForm;
import com.kurui.kums.agent.dao.AgentActionDAO;
import com.kurui.kums.base.exception.AppException;

public class AgentActionBizImp implements AgentActionBiz {
	private AgentActionDAO agentActionDAO;

	public List list(AgentActionListForm agentActionListForm)
			throws AppException {
		return agentActionDAO.list(agentActionListForm);
	}

	public long delete(long id) throws AppException {
		try {
			agentActionDAO.delete(id);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public void deleteAgentAction(Long id) throws AppException {
		AgentAction agentAction = getAgentActionById(id);
		agentAction.setStatus(AgentAction.STATES_0);// 将状态变为无效
		update(agentAction);
	}

	public long save(AgentAction agentAction) throws AppException {
		return agentActionDAO.save(agentAction);
	}

	public long update(AgentAction agentAction) throws AppException {
		return agentActionDAO.update(agentAction);
	}

	public AgentAction getAgentActionById(long id) throws AppException {
		return agentActionDAO.getAgentActionById(id);
	}

	public List<AgentAction> getAgentActionList() throws AppException {
		return agentActionDAO.getAgentActionList();
	}

	public List<AgentAction> getAgentActionList(Long type) throws AppException {
		return agentActionDAO.getAgentActionList(type);
	}

	public void setAgentActionDAO(AgentActionDAO agentActionDAO) {
		this.agentActionDAO = agentActionDAO;
	}
}
