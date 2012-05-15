package com.kurui.kums.agent.biz;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.kurui.kums.agent.biz.AgentRelationBiz;
import com.kurui.kums.agent.Agent;
import com.kurui.kums.agent.AgentRelation;
import com.kurui.kums.agent.AgentRelationListForm;
import com.kurui.kums.agent.dao.AgentDAO;
import com.kurui.kums.agent.dao.AgentRelationDAO;
import com.kurui.kums.base.exception.AppException;

public class AgentRelationBizImp implements AgentRelationBiz {
	private AgentDAO agentDAO;
	private AgentRelationDAO agentRelationDAO;
	static Logger logger = Logger.getLogger(AgentRelationBizImp.class.getName());

	public HttpServletRequest listAgentGroup(
			AgentRelationListForm agentRelationListForm,
			HttpServletRequest request) {
		return agentRelationDAO.listAgentGroup(agentRelationListForm, request);
	}
	
	protected void printListArray(ArrayList<ArrayList<Agent>> lists) {
		for (int i = 0; i < lists.size(); i++) {
			ArrayList<Agent> rootAgentList = lists.get(i);
			logger.info((i + 1) + "--级,数量：" + rootAgentList.size());
			for (int j = 0; j < rootAgentList.size(); j++) {
				logger.info(rootAgentList.get(j).getName());
			}
		}
	}

	public List list(AgentRelationListForm agentRelationListForm)
			throws AppException {
		return agentRelationDAO.list(agentRelationListForm);
	}

	public long delete(long id) throws AppException {
		try {
			agentRelationDAO.delete(id);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public void deleteAgentRelation(Long id) throws AppException {
		AgentRelation agentRelation = getAgentRelationById(id);
		agentRelation.setStatus(AgentRelation.STATES_0);// 将状态变为无效
		update(agentRelation);
	}

	public long save(AgentRelation agentRelation) throws AppException {
		return agentRelationDAO.save(agentRelation);
	}

	public long update(AgentRelation agentRelation) throws AppException {
		return agentRelationDAO.update(agentRelation);
	}

	public AgentRelation getAgentRelationById(long id) throws AppException {
		return agentRelationDAO.getAgentRelationById(id);
	}

	public List<AgentRelation> getAgentRelationList() throws AppException {
		return agentRelationDAO.getAgentRelationList();
	}

	public List<AgentRelation> getAgentRelationList(Long type)
			throws AppException {
		return agentRelationDAO.getAgentRelationList(type);
	}

	public void setAgentDAO(AgentDAO agentDAO) {
		this.agentDAO = agentDAO;
	}

	public void setAgentRelationDAO(AgentRelationDAO agentRelationDAO) {
		this.agentRelationDAO = agentRelationDAO;
	}
}
