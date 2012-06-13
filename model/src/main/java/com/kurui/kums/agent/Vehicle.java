package com.kurui.kums.agent;

import java.util.Date;

import com.kurui.kums.agent.entity._Vehicle;
import com.kurui.kums.base.util.DateUtil;

public class Vehicle extends _Vehicle {

	private static final long serialVersionUID = 1L;

	private long agentId = Long.valueOf(0);

	public static final long TYPE_1 = 1;// 默认

	// 状态
	public static final long STATES_1 = 1;// 有效
	public static final long STATES_0 = 0;// 无效

	public String getTypeInfo() {
		if (this.getType() != null) {
			if (this.getType() == TYPE_1) {
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

	public String getUpdateDate() {
		String mydate = "";
		if (this.updateTime != null && "".equals(updateTime) == false) {
			Date tempDate = new Date(updateTime.getTime());
			mydate = DateUtil.getDateString(tempDate, "yyyy-MM-dd");
		}
		return mydate;
	}

	public long getAgentId() {
		return agentId;
	}

	public void setAgentId(long agentId) {
		this.agentId = agentId;
	}

}
