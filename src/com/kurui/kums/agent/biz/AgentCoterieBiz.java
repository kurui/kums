package com.kurui.kums.agent.biz;

import java.util.List;

import com.kurui.kums.agent.AgentCoterie;
import com.kurui.kums.agent.AgentCoterieListForm;
import com.kurui.kums.base.exception.AppException;

public interface AgentCoterieBiz {

	public List list(AgentCoterieListForm agentCoterieListForm) throws AppException;

	public long delete(long id) throws AppException;

	public void deleteAgentCoterie(Long id) throws AppException;

	public long save(AgentCoterie agentCoterie) throws AppException;

	public long update(AgentCoterie agentCoterie) throws AppException;

	public AgentCoterie getAgentCoterieById(long id) throws AppException;

	public List<AgentCoterie> getAgentCoterieList() throws AppException;

	public List<AgentCoterie> getAgentCoterieList(Long type) throws AppException;

}
