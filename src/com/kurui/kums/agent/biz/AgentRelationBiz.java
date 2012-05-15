package com.kurui.kums.agent.biz;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import com.kurui.kums.agent.AgentRelation;
import com.kurui.kums.agent.AgentRelationListForm;
import com.kurui.kums.base.exception.AppException;

public interface AgentRelationBiz {

	public HttpServletRequest listAgentGroup(
			AgentRelationListForm agentRelationListForm,
			HttpServletRequest request) throws AppException;

	public List list(AgentRelationListForm agentRelationListForm)
			throws AppException;

	public long delete(long id) throws AppException;

	public void deleteAgentRelation(Long id) throws AppException;

	public long save(AgentRelation agentRelation) throws AppException;

	public long update(AgentRelation agentRelation) throws AppException;

	public AgentRelation getAgentRelationById(long id) throws AppException;

	public List<AgentRelation> getAgentRelationList() throws AppException;

	public List<AgentRelation> getAgentRelationList(Long type)
			throws AppException;

}
