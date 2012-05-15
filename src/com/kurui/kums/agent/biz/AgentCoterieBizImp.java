package com.kurui.kums.agent.biz;

import java.util.List;

import com.kurui.kums.agent.biz.AgentCoterieBiz;
import com.kurui.kums.agent.AgentCoterie;
import com.kurui.kums.agent.AgentCoterieListForm;
import com.kurui.kums.agent.dao.AgentCoterieDAO;
import com.kurui.kums.base.exception.AppException;

public class AgentCoterieBizImp implements AgentCoterieBiz {
	private AgentCoterieDAO agentCoterieDAO;

	public List list(AgentCoterieListForm agentCoterieListForm) throws AppException {
		return agentCoterieDAO.list(agentCoterieListForm);
	}

	public long delete(long id) throws AppException {
		try {
			agentCoterieDAO.delete(id);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public void deleteAgentCoterie(Long id) throws AppException {
		AgentCoterie agentCoterie = getAgentCoterieById(id);
		agentCoterie.setStatus(AgentCoterie.STATES_0);// 将状态变为无效
		update(agentCoterie);
	}

	public long save(AgentCoterie agentCoterie) throws AppException {
		return agentCoterieDAO.save(agentCoterie);
	}

	public long update(AgentCoterie agentCoterie) throws AppException {
		return agentCoterieDAO.update(agentCoterie);
	}

	public AgentCoterie getAgentCoterieById(long id) throws AppException {
		return agentCoterieDAO.getAgentCoterieById(id);
	}

	public List<AgentCoterie> getAgentCoterieList() throws AppException {
		return agentCoterieDAO.getAgentCoterieList();
	}

	public List<AgentCoterie> getAgentCoterieList(Long type) throws AppException {
		return agentCoterieDAO.getAgentCoterieList(type);
	}

	public void setAgentCoterieDAO(AgentCoterieDAO agentCoterieDAO) {
		this.agentCoterieDAO = agentCoterieDAO;
	}
}
