package com.kurui.kums.agent;

import java.util.Date;

import com.kurui.kums.agent._entity._AgentContact;
import com.kurui.kums.base.util.DateUtil;

public class AgentContact extends _AgentContact {

	private static final long serialVersionUID = 1L;

	private long agentId = Long.valueOf(0);
	private String agentNo="";
	

	public static final long TYPE_1 = 1;// 手机
	public static final long TYPE_2 = 2;//固定电话
	public static final long TYPE_11 = 11;//EMAIL
	public static final long TYPE_12 = 12;//QQ
	public static final long TYPE_15 = 15;//网址	
	public static final long TYPE_21 = 21;//祖籍
	public static final long TYPE_31 = 31;//收货地址
	
	

	// 状态
	public static final long STATES_1 = 1;// 有效
	public static final long STATES_0 = 0;// 无效
	
	public String getTypeInfo() {
		if (this.getType() != null) {
			if (this.getType() == TYPE_1) {
				return "手机";
			} else if (this.getType() == TYPE_2) {
				return "固定电话";
			}else if (this.getType() == TYPE_11) {
				return "EMAIL";
			}else if (this.getType() == TYPE_12) {
				return "QQ";
			}else if (this.getType() == TYPE_15) {
				return "网址";
			}else if (this.getType() == TYPE_21) {
				return "祖籍";
			}else if (this.getType() == TYPE_31) {
				return "收货地址";
			}
			else{
				return "未定义";
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
			}else if (this.getStatus().intValue() == STATES_0) {
				return "无效";
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
	
	private String updateDate="";
	
	public String getUpdateDate() {
		String mydate = "";
		if (this.updateTime != null && "".equals(updateTime) == false) {
			Date tempDate = new Date(updateTime.getTime());
			mydate = DateUtil.getDateString(tempDate, "yyyy-MM-dd HH:mm:ss");
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
