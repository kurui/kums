package com.kurui.kums.agent.biz;

import java.util.List;

import com.kurui.kums.agent.biz.AgentContactBiz;
import com.kurui.kums.agent.AgentContact;
import com.kurui.kums.agent.AgentContactListForm;
import com.kurui.kums.agent.dao.AgentContactDAO;
import com.kurui.kums.base.exception.AppException;

public class AgentContactBizImp implements AgentContactBiz {
	private AgentContactDAO agentContactDAO;

	public List list(AgentContactListForm agentContactListForm)
			throws AppException {
		return agentContactDAO.list(agentContactListForm);
	}

	public long delete(long id) throws AppException {
		try {
			agentContactDAO.delete(id);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public void deleteAgentContact(Long id) throws AppException {
		AgentContact agentContact = getAgentContactById(id);
		agentContact.setStatus(AgentContact.STATES_0);// 将状态变为无效
		update(agentContact);
	}

	public long save(AgentContact agentContact) throws AppException {
		return agentContactDAO.save(agentContact);
	}

	public long update(AgentContact agentContact) throws AppException {
		return agentContactDAO.update(agentContact);
	}

	public AgentContact getAgentContactById(long id) throws AppException {
		return agentContactDAO.getAgentContactById(id);
	}

	public List<AgentContact> getAgentContactList() throws AppException {
		return agentContactDAO.getAgentContactList();
	}

	public List<AgentContact> getAgentContactList(Long type) throws AppException {
		return agentContactDAO.getAgentContactList(type);
	}

	public void setAgentContactDAO(AgentContactDAO agentContactDAO) {
		this.agentContactDAO = agentContactDAO;
	}
}
