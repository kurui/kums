package com.kurui.kums.agent.util;

import java.util.ArrayList;
import java.util.List;
import com.kurui.kums.agent.Agent;
import com.kurui.kums.agent.DirectLevel;
import com.kurui.kums.base.Constant;

public class AgentStore {
	public static List<Agent> agentList = new ArrayList<Agent>();
	public static List<DirectLevel> directLevelList = new ArrayList<DirectLevel>();


	public static List<Agent> getBlurAgentList(String blur) {
//		System.out.println("getBlurAgentList:" + blur);
		List<Agent> blurAgentList = new ArrayList<Agent>();
		if (Constant.toString(blur).equals("") == false) {
			int j = 0;
			for (int i = 0; i < agentList.size(); i++) {
				if (j > 4) {
					break;
				} else {
					Agent agent = agentList.get(i);
					if (agent != null) {
						String name = Constant.toString(agent.getName());
						String agentNo = Constant.toString(agent.getAgentNo());
						String mobilePhone = Constant.toString(agent
								.getMobilePhone());

						if (name.indexOf(blur) > -1) {
							blurAgentList.add(agent);
						} else if (agentNo.indexOf(blur) > -1) {
							blurAgentList.add(agent);
						} else if (mobilePhone.indexOf(blur) > -1) {
							blurAgentList.add(agent);
						}
					}
				}

				j = blurAgentList.size();
			}
		}
//		System.out.println("result:"+blurAgentList.size());
		return blurAgentList;
	}

	public static List<Agent> getVIPAgentList() {
		List<Agent> agentList = getAgentListByType(Agent.TYPE_1);
		return agentList;
	}

	public static List<Agent> getAgentListByType(long type) {
		List<Agent> tempList = new ArrayList<Agent>();
		for (int i = 0; i < agentList.size(); i++) {
			Agent agent = agentList.get(i);
			if (agent.getType() == type) {
				tempList.add(agent);
			}
		}
		return tempList;
	}

	public static List<DirectLevel> getDirectLevelListByType(long type) {
		List<DirectLevel> tempList = new ArrayList<DirectLevel>();
		for (int i = 0; i < directLevelList.size(); i++) {
			DirectLevel directLevel = directLevelList.get(i);
			if (directLevel.getType() == type) {
				tempList.add(directLevel);
			}
		}
		return tempList;
	}

	public static String getLevelNameById(long id) {
		String name = "";
		if (id > 0) {
			if (directLevelList != null) {
				for (int i = 0; i < directLevelList.size(); i++) {
					DirectLevel directLevel = directLevelList.get(i);
					if (directLevel.getId() > 0) {
						if (id == directLevel.getId()) {
							name = directLevel.getName();
						}
					}
				}
			}
		}
		return name;
	}

	public static List<Agent> getAgentList() {
		return agentList;
	}

	public static void setAgentList(List<Agent> agentList) {
		AgentStore.agentList = agentList;
	}

	public static List<DirectLevel> getDirectLevelList() {
		return directLevelList;
	}

	public static void setDirectLevelList(List<DirectLevel> directLevelList) {
		AgentStore.directLevelList = directLevelList;
	}

}
