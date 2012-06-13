package com.kurui.kums.agent;

import java.util.Date;

import com.kurui.kums.agent.entity._AgentRelation;
import com.kurui.kums.base.util.DateUtil;
import com.kurui.kums.right.UserStore;

public class AgentRelation extends _AgentRelation {

	private static final long serialVersionUID = 1L;
	
	private long agentId = Long.valueOf(0);
	private long rootAgentId = Long.valueOf(0);
	private long relateAgentId = Long.valueOf(0);

	// 关系类型
	public static final long RELATION_TYPE_1 = 1;// 上下级
	public static final long RELATION_TYPE_11 = 11;// 同级

	// 状态
	public static final long STATES_1 = 1;// 有效
	public static final long STATES_0 = 0;// 无效

	public String agentIds = "";
	private String[] leftUserID = new String[0];
	private String[] rightUserID = new String[0];
	private String[] leftRoleID = new String[0];
	private String[] rightRoleID = new String[0];
	private String[] selectedRoleItems = new String[0];
	private String[] selectedRightItems = new String[0];
	private String selectedRightItemsStr = "";
	private int selectedRightItem = -1;
	private String xml = "";
	private int count = 0;

	public String getAgentIds() {
		return agentIds;
	}

	public void setAgentIds(String agentIds) {
		this.agentIds = agentIds;
	}

	public String[] getLeftUserID() {
		return leftUserID;
	}

	public void setLeftUserID(String[] leftUserID) {
		this.leftUserID = leftUserID;
	}

	public String[] getRightUserID() {
		return rightUserID;
	}

	public void setRightUserID(String[] rightUserID) {
		this.rightUserID = rightUserID;
	}

	public String[] getLeftRoleID() {
		return leftRoleID;
	}

	public void setLeftRoleID(String[] leftRoleID) {
		this.leftRoleID = leftRoleID;
	}

	public String[] getRightRoleID() {
		return rightRoleID;
	}

	public void setRightRoleID(String[] rightRoleID) {
		this.rightRoleID = rightRoleID;
	}

	public String[] getSelectedRoleItems() {
		return selectedRoleItems;
	}

	public void setSelectedRoleItems(String[] selectedRoleItems) {
		this.selectedRoleItems = selectedRoleItems;
	}

	public String[] getSelectedRightItems() {
		return selectedRightItems;
	}

	public void setSelectedRightItems(String[] selectedRightItems) {
		this.selectedRightItems = selectedRightItems;
	}

	public String getSelectedRightItemsStr() {
		return selectedRightItemsStr;
	}

	public void setSelectedRightItemsStr(String selectedRightItemsStr) {
		this.selectedRightItemsStr = selectedRightItemsStr;
	}

	public int getSelectedRightItem() {
		return selectedRightItem;
	}

	public void setSelectedRightItem(int selectedRightItem) {
		this.selectedRightItem = selectedRightItem;
	}

	public String getXml() {
		return xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	// 状态
	public String getRelationTypeInfo() {
		if (this.getRelationType() != null) {
			if (this.getRelationType() == RELATION_TYPE_1) {
				return "上下级";
			} else if (this.getRelationType().intValue() == RELATION_TYPE_11) {
				return "同级";
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

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

	public String getUserName() {
		if (userNo != null && "".equals(userNo) == false) {
			return UserStore.getUserNameByNo(userNo);
		} else {
			return "";
		}
	}

	public String getUpdateDate() {
		String mydate = "";
		if (this.updateTime != null && "".equals(updateTime) == false) {
			Date tempDate = new Date(updateTime.getTime());
			mydate = DateUtil.getDateString(tempDate, "yyyy-MM-dd");
		}
		return mydate;
	}

	public long getRootAgentId() {
		return rootAgentId;
	}

	public void setRootAgentId(long rootAgentId) {
		this.rootAgentId = rootAgentId;
	}

	public long getRelateAgentId() {
		return relateAgentId;
	}

	public void setRelateAgentId(long relateAgentId) {
		this.relateAgentId = relateAgentId;
	}

	public long getAgentId() {
		return agentId;
	}

	public void setAgentId(long agentId) {
		this.agentId = agentId;
	}

}
