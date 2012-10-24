package com.kurui.kums.agent.dao;

import java.util.List;

import com.kurui.kums.agent.AgentContact;
import com.kurui.kums.agent.AgentContactListForm;
import com.kurui.kums.base.exception.AppException;

public interface AgentContactDAO {
	public List list(AgentContactListForm agentListForm) throws AppException;

	public void delete(long id) throws AppException;

	public long save(AgentContact agentContact) throws AppException;

	public long update(AgentContact agentContact) throws AppException;

	public AgentContact getAgentContactById(long id) throws AppException;

	public AgentContact getAgentContactByAgentId(long agentId) throws AppException;

	public List<AgentContact> getAgentContactList() throws AppException;

	public List<AgentContact> getAgentContactList(Long type) throws AppException;

	public List<AgentContact> getValidAgentContactList() throws AppException;
}
