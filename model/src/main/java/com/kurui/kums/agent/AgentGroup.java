package com.kurui.kums.agent;

import java.util.ArrayList;

public class AgentGroup {
	private long rootAgentId = Long.valueOf(0);
	private Agent rootAgent;
	private ArrayList<Agent> subAgentList = new ArrayList<Agent>();

	public AgentGroup() {
	}

	public AgentGroup(long agentId) {
		rootAgentId = agentId;
	}

	public AgentGroup(Agent agent, ArrayList<Agent> agentList) {
		if (agent != null) {
			rootAgentId = agent.getId();
		}
		rootAgent = agent;
		subAgentList = agentList;
	}

	public long getRootAgentId() {
		return rootAgentId;
	}

	public void setRootAgentId(long rootAgentId) {
		this.rootAgentId = rootAgentId;
	}

	public ArrayList<Agent> getSubAgentList() {
		return subAgentList;
	}

	public void setSubAgentList(ArrayList<Agent> subAgentList) {
		this.subAgentList = subAgentList;
	}

	public Agent getRootAgent() {
		return rootAgent;
	}

	public void setRootAgent(Agent rootAgent) {
		this.rootAgent = rootAgent;
	}

}
