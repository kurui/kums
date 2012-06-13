package com.kurui.kums.agent;

import java.util.Date;

import com.kurui.kums.agent.entity._AgentHabit;
import com.kurui.kums.base.util.DateUtil;

public class AgentHabit extends _AgentHabit {

	private static final long serialVersionUID = 1L;

	private long agentId = Long.valueOf(0);
	private Agent agent;
	
	public AgentHabit() {
	}

	public AgentHabit(Agent agent) {
		this.agent = agent;
	}
	
	public AgentHabit(Agent agent,AgentHabit agentHabit) {
		this.agent = agent;
		this.id=agentHabit.getId();
		this.airplaneSeat=agentHabit.getAirplaneSeat();
		this.travelAppoint=agentHabit.getTravelAppoint();
		this.drink=agentHabit.getDrink();
		this.food=agentHabit.getFood();
		this.flower=agentHabit.getFlower();
		this.scaredAnimal=agentHabit.getScaredAnimal();
		this.breedAnimal=agentHabit.getBreedAnimal();
		this.filmType=agentHabit.getFilmType();
		this.sports=agentHabit.getSports();
		this.oppositeSex=agentHabit.getOppositeSex();
		this.homoSex=agentHabit.getHomoSex();
		this.bigWish=agentHabit.getBigWish();
		this.religion=agentHabit.getReligion();
		this.amuse=agentHabit.getAmuse();
		this.reading=agentHabit.getReading();
		this.memo=agentHabit.getMemo();
		this.updateTime=agentHabit.getUpdateTime();
		this.status=agentHabit.getStatus();		
		this.agents=agentHabit.agents=agentHabit.getAgents();
	}
	
	

	// 状态
	public static final long STATES_1 = 1;// 有效
	public static final long STATES_0 = 0;// 无效

	// 状态
	public String getStatusInfo() {
		if (this.getStatus() != null) {
			if (this.getStatus() == STATES_1) {
				return "有效";
			} else if (this.getStatus().intValue() == STATES_0) {
				return "无效";
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	public String getAirplaneSeatInfo() {
		if (this.getAirplaneSeat() != null) {
			if (this.getAirplaneSeat().intValue() == 1) {
				return "靠过道";
			} else if (this.getAirplaneSeat().intValue() == 2) {
				return "中间";
			} else if (this.getAirplaneSeat().intValue() == 3) {
				return "靠走廊";
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	public String getShortBigWish() {
		String temp = this.getBigWish();
		if (temp != null) {
			if (temp.length() > 20) {
				temp = temp.substring(0, 10) + "..";
			}
			return temp;
		} else {
			return null;
		}
	}

	public String getUpdateDate() {
		String mydate = "";
		if (this.updateTime != null && "".equals(updateTime) == false) {
			Date tempDate = new Date(updateTime.getTime());
			mydate = DateUtil.getDateString(tempDate, "yyyy-MM-dd HH:mm:ss");
		}
		return mydate;
	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	public long getAgentId() {
		return agentId;
	}

	public void setAgentId(long agentId) {
		this.agentId = agentId;
	}

}
