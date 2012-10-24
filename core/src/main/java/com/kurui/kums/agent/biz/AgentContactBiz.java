package com.kurui.kums.agent.biz;

import java.util.List;

import com.kurui.kums.agent.AgentContact;
import com.kurui.kums.agent.AgentContactListForm;
import com.kurui.kums.base.exception.AppException;

public interface AgentContactBiz {

	public List list(AgentContactListForm agentContactListForm) throws AppException;

	public long delete(long id) throws AppException;

	public void deleteAgentContact(Long id) throws AppException;

	public long save(AgentContact agentContact) throws AppException;

	public long update(AgentContact agentContact) throws AppException;

	public AgentContact getAgentContactById(long id) throws AppException;

	public List<AgentContact> getAgentContactList() throws AppException;

	public List<AgentContact> getAgentContactList(Long type) throws AppException;
	public List<AgentContact> getAgentContactListByAgent(Long agentId) throws AppException;
}
