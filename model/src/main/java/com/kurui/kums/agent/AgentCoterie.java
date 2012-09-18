package com.kurui.kums.agent;

import java.util.Date;

import com.kurui.kums.agent.entity._AgentCoterie;
import com.kurui.kums.base.util.DateUtil;

public class AgentCoterie extends _AgentCoterie {

	private static final long serialVersionUID = 1L;

	private long coterieId = Long.valueOf(0);
	private long subAgentId = Long.valueOf(0);
	private String agentNo = "";

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

	public String getCreateDate() {
		String mydate = "";
		if (this.createTime != null && "".equals(createTime) == false) {
			Date tempDate = new Date(createTime.getTime());
			mydate = DateUtil.getDateString(tempDate, "yyyy-MM-dd HH:mm:ss");
		}
		return mydate;
	}

	public String getFromDate() {
		String mydate = "";
		if (this.fromTime != null && "".equals(fromTime) == false) {
			Date tempDate = new Date(fromTime.getTime());
			mydate = DateUtil.getDateString(tempDate, "yyyy-MM-dd HH:mm:ss");
		}
		return mydate;
	}

	public String getExpireDate() {
		String mydate = "";
		if (this.expireTime != null && "".equals(expireTime) == false) {
			Date tempDate = new Date(expireTime.getTime());
			mydate = DateUtil.getDateString(tempDate, "yyyy-MM-dd HH:mm:ss");
		}
		return mydate;
	}

	public long getCoterieId() {
		return coterieId;
	}

	public void setCoterieId(long coterieId) {
		this.coterieId = coterieId;
	}

	public String getAgentNo() {
		return agentNo;
	}

	public void setAgentNo(String agentNo) {
		this.agentNo = agentNo;
	}

	public long getSubAgentId() {
		return subAgentId;
	}

	public void setSubAgentId(long subAgentId) {
		this.subAgentId = subAgentId;
	}

}
