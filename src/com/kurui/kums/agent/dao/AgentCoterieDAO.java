package com.kurui.kums.agent.dao;

import java.util.List;

import com.kurui.kums.agent.AgentCoterie;
import com.kurui.kums.agent.AgentCoterieListForm;
import com.kurui.kums.base.exception.AppException;

public interface AgentCoterieDAO {
	public List list(AgentCoterieListForm agentListForm) throws AppException;

	public void delete(long id) throws AppException;

	public long save(AgentCoterie agentCoterie) throws AppException;

	public long update(AgentCoterie agentCoterie) throws AppException;

	public AgentCoterie getAgentCoterieById(long id) throws AppException;

	public AgentCoterie getAgentCoterieBySubAgentId(long subAgentId)
			throws AppException;

	public List<AgentCoterie> getAgentCoterieList() throws AppException;

	public List<AgentCoterie> getAgentCoterieList(Long type)
			throws AppException;

	public List<AgentCoterie> getValidAgentCoterieList() throws AppException;
}
