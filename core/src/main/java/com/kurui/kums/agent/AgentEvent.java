package com.kurui.kums.agent;

import java.util.Date;

import com.kurui.kums.agent._entity._AgentEvent;
import com.kurui.kums.base.util.time.DateUtil;
import com.kurui.kums.right.UserStore;

public class AgentEvent extends _AgentEvent {

	private static final long serialVersionUID = 1L;

	private long agentId = Long.valueOf(0);
	private String agentNo = "";

	// 類型
	public static final long TYPE_1 = 1;// 默認

	// 状态
	public static final long STATES_1 = 1;// 有效
	public static final long STATES_0 = 0;// 无效

	public String getTypeInfo() {
		if (this.getType() != null) {
			if (this.getType() == STATES_1) {
				return "默认";
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
		if (userNo != null && "".equals(userNo.trim()) == false) {
			return UserStore.getUserNameByNo(userNo);
		} else {
			return "";
		}
	}

	private String updateDate = "";

	public String getUpdateDate() {
		String mydate = "";
		if (updateDate != "") {
			return updateDate;
		} else {
			if (this.updateTime != null && "".equals(updateTime) == false) {
				Date tempDate = new Date(updateTime.getTime());
				mydate = DateUtil
						.getDateString(tempDate, "yyyy-MM-dd");
			}
		}
		return mydate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public long getAgentId() {
		return agentId;
	}

	public void setAgentId(long agentId) {
		this.agentId = agentId;
	}

	public String getAgentNo() {
		return agentNo;
	}

	public void setAgentNo(String agentNo) {
		this.agentNo = agentNo;
	}

}
