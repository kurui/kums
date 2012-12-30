package com.kurui.kums.agent.biz;

import java.util.Date;
import java.util.List;

import com.kurui.kums.agent.Agent;
import com.kurui.kums.agent.AgentListForm;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.base.util.IdCardUtil;

public interface AgentBiz {

	public List list(AgentListForm agentListForm) throws AppException;

	public List listGrade(AgentListForm agentListForm) throws AppException;

	public List listDirect(AgentListForm agentListForm) throws AppException;

	public long delete(long id) throws AppException;

	public void deleteAgent(Long id) throws AppException;

	public long save(Agent agent) throws AppException;

	public long update(Agent agent) throws AppException;

	public long updateStatistic(long agentId) throws AppException;

	public Agent getAgentById(long agentId) throws AppException;
	
	public Date getBirthDateByIdCard(String idCard)throws AppException;
	
	public String getBirthStrByIdCard(String idCard)throws AppException;
	
	public long getSexByIdCard(String idCard)throws AppException;
	
	public String getAreaCodeByIdCard(String idCard);
	
	public String getAreaTextByCode(String idCard)throws AppException;

	public List<Agent> getAgentList() throws AppException;

	public List<Agent> getAgentListByType(Long type) throws AppException;
	public List<Agent> getBlurAgentList(String blur) throws AppException;

	public List<Agent> getValidAgentList() throws AppException;

}