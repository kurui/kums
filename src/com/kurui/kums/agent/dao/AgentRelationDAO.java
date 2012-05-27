package com.kurui.kums.agent.dao;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.kurui.kums.agent.Agent;
import com.kurui.kums.agent.AgentRelation;
import com.kurui.kums.agent.AgentRelationListForm;
import com.kurui.kums.base.exception.AppException;

public interface AgentRelationDAO {
	public HttpServletRequest listAgentGroup(
			AgentRelationListForm agentRelationListForm,
			HttpServletRequest request);

	// 直属上级
	public Agent getSupAgent(long agentId) throws AppException;

	// 下级
	public ArrayList<Agent> getSubAgentList(long agentId) throws AppException;

	public List list(AgentRelationListForm agentListForm) throws AppException;

	public void delete(long id) throws AppException;

	public long save(AgentRelation agentRelation) throws AppException;

	public long update(AgentRelation agentRelation) throws AppException;

	public AgentRelation getAgentRelationById(long id) throws AppException;

	public AgentRelation getAgentRelationByRootAgentId(long rootAgentId)
			throws AppException;

	public List<AgentRelation> getAgentRelationList() throws AppException;

	public List<AgentRelation> getAgentRelationList(Long type)
			throws AppException;

	public List<AgentRelation> getValidAgentRelationList() throws AppException;
}
