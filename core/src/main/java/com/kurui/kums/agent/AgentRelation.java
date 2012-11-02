package com.kurui.kums.agent;

import java.util.Date;

import com.kurui.kums.agent._entity._AgentRelation;
import com.kurui.kums.base.util.DateUtil;
import com.kurui.kums.right.UserStore;

public class AgentRelation extends _AgentRelation {

	private static final long serialVersionUID = 1L;
	
	private long agentId = Long.valueOf(0);
	private long rootAgentId = Long.valueOf(0);
	private long relateAgentId = Long.valueOf(0);
	
	private String agentNo="";

	// 关系类型
	//(直销商体系)
	public static final long RELATION_TYPE_1 = 1;// 上下级
	public static final long RELATION_TYPE_11 = 11;// 同级
	//特殊关系人
	public static final long RELATION_TYPE_21 = 21;// 密友
	public static final long RELATION_TYPE_31 = 31;// 情侣
	public static final long RELATION_TYPE_35 = 35;// 夫妻
	public static final long RELATION_TYPE_38 = 38;// 子女
	public static final long RELATION_TYPE_40 = 40;// 亲属	
	public static final long RELATION_TYPE_51 = 51;// 门生故吏
	

	// 状态
	public static final long STATES_1 = 1;// 有效
	public static final long STATES_2 = 2;// 历史
	public static final long STATES_0 = 0;// 无效	
	

	// 关系类型
	public String getRelationTypeInfo() {
		if (this.getRelationType() != null) {
			if (this.getRelationType() == RELATION_TYPE_1) {
				return "上下级";
			} else if (this.getRelationType().intValue() == RELATION_TYPE_11) {
				return "同级";
			}  else if (this.getRelationType().intValue() == RELATION_TYPE_21) {
				return "密友";
			} else if (this.getRelationType().intValue() == RELATION_TYPE_31) {
				return "情侣";
			}  else if (this.getRelationType().intValue() == RELATION_TYPE_35) {
				return "夫妻";
			}else if (this.getRelationType().intValue() == RELATION_TYPE_38) {
				return "子女";
			} else if (this.getRelationType().intValue() == RELATION_TYPE_40) {
				return "亲属";
			}else if (this.getRelationType().intValue() == RELATION_TYPE_51) {
				return "门生故吏";
			}else {
				return null;
			}
		} else {
			return null;
		}
	}
	

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
	
	

	public String getAgentNo() {
		return agentNo;
	}

	public void setAgentNo(String agentNo) {
		this.agentNo = agentNo;
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
	public String getStatusInfo() {
		if (this.getStatus() != null) {
			if (this.getStatus() == STATES_1) {
				return "有效";
			} else if (this.getStatus().intValue() == STATES_2) {
				return "历史";
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

	private String updateDate="";
	public String getUpdateDate() {
		String mydate = "";
		if (updateDate  != "") {
			return updateDate;
		} else {
		if (this.updateTime != null && "".equals(updateTime) == false) {
			Date tempDate = new Date(updateTime.getTime());
			mydate = DateUtil.getDateString(tempDate, "yyyy-MM-dd");
		}}
		return mydate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
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
