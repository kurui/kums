package com.kurui.kums.agent.biz;

import java.util.Date;
import java.util.List;

import com.kurui.kums.agent.Agent;
import com.kurui.kums.agent.AgentListForm;
import com.kurui.kums.agent.dao.AgentDAO;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.base.util.time.DateUtil;
import com.kurui.kums.base.util.IdCardUtil;
import com.kurui.kums.library.dao.AreaCodeDAO;

public class AgentBizImp implements AgentBiz {
	private AgentDAO agentDAO;
	private AreaCodeDAO areaCodeDAO;

	public List list(AgentListForm agentListForm) throws AppException {
		return agentDAO.list(agentListForm);
	}
	
	public List listGrade(AgentListForm agentListForm) throws AppException {
		return agentDAO.listGrade(agentListForm);
	}
	
	public List listDirect(AgentListForm agentListForm) throws AppException {
		return agentDAO.listDirect(agentListForm);
	}

	public long delete(long id) throws AppException {
		try {
			agentDAO.delete(id);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public void deleteAgent(Long id) throws AppException {
		Agent agent = getAgentById(id);
		agent.setStatus(Agent.STATES_0);// 将状态变为无效
		update(agent);
	}

	public long save(Agent agent) throws AppException {
		return agentDAO.save(agent);
	}

	public long update(Agent agent) throws AppException {
		return agentDAO.update(agent);
	}

	public long updateStatistic(long agentId)throws AppException{
		 agentDAO.updatePhysicalAmount(agentId);
		 agentDAO.updateVirtualAmount(agentId);
		 agentDAO.updateTotalIntegral(agentId);
		 return 1;
	}
	public Agent getAgentById(long agentId) throws AppException {
		return agentDAO.getAgentById(agentId);
	}
	
	public Date getBirthDateByIdCard(String idCard)throws AppException{
		return IdCardUtil.getBirthDateByIdCard(idCard);
	}
	
	public String getBirthStrByIdCard(String idCard)throws AppException{
		return DateUtil.getDateString(IdCardUtil.getBirthDateByIdCard(idCard),"yyyy-MM-dd");
	}
	
	public long getSexByIdCard(String idCard)throws AppException{
		return IdCardUtil.getSexByIdCard(idCard);
	}
	
	public String getAreaCodeByIdCard(String idCard){
		return IdCardUtil.getAreaCodeByIdCard(idCard);
	}
	
	public String getAreaTextByCode(String idCard)throws AppException{
		String areaCode= IdCardUtil.getAreaCodeByIdCard(idCard);
		return areaCodeDAO.getAreaTextByCode(areaCode);
	}
	
	public List<Agent> getBlurAgentList(String blur)throws AppException{
		return agentDAO.getBlurAgentList(blur);
	}

	public List<Agent> getAgentList() throws AppException {
		return agentDAO.getAgentList();
	}

	public List<Agent> getAgentListByType(Long type) throws AppException {
		return agentDAO.getAgentListByType(type);
	}
	
	
	
	public List<Agent> getValidAgentList() throws AppException{
		return agentDAO.getValidAgentList();
	}

	public void setAgentDAO(AgentDAO agentDAO) {
		this.agentDAO = agentDAO;
	}

	public void setAreaCodeDAO(AreaCodeDAO areaCodeDAO) {
		this.areaCodeDAO = areaCodeDAO;
	}
	
	
}
