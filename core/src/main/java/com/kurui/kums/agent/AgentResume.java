package com.kurui.kums.agent;

import java.util.Date;

import com.kurui.kums.agent._entity._AgentResume;
import com.kurui.kums.base.util.DateUtil;

public class AgentResume extends _AgentResume {

	private static final long serialVersionUID = 1L;

	private long agentId = Long.valueOf(0);
	private String agentNo = "";

	public long companyId=Long.valueOf(0);
	public String companyNo="";

	// 類型
	public static final long TYPE_1 = 1;// 当前
	public static final long TYPE_2 = 2;// 历史

	// 状态
	public static final long STATES_1 = 1;// 有效
	public static final long STATES_0 = 0;// 无效
	
	public String getTypeInfo() {
		if (this.getType() != null) {
			if (this.getType() == TYPE_1) {
				return "当前";
			} else if (this.getType() == TYPE_2) {
				return "历史";
			}else {
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

	private String beginDate = "";
	private String endDate = "";

	public String getBeginDate() {
		String mydate = "";
		if (beginDate != "") {
			return beginDate;
		}else{
			if (this.beginTime != null && "".equals(beginTime) == false) {
				Date tempDate = new Date(beginTime.getTime());
				return DateUtil.getDateString(tempDate, "yyyy-MM-dd");
			}
		}
		return mydate;
	}

	public String getEndDate() {
		String mydate = "";
		if (endDate != "") {
			return endDate;
		}else{
			if (this.endTime != null && "".equals(endTime) == false) {
				Date tempDate = new Date(endTime.getTime());
				return DateUtil.getDateString(tempDate, "yyyy-MM-dd");
			}
		} 

		return mydate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public long getAgentId() {
		return agentId;
	}

	public void setAgentId(long agentId) {
		this.agentId = agentId;
	}

	public long getCompanyId() {
		if(company!=null){
			return company.getId();
		}
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public String getAgentNo() {
		return agentNo;
	}

	public void setAgentNo(String agentNo) {
		this.agentNo = agentNo;
	}

	public String getCompanyNo() {
		if(company!=null){
			return company.getName();
		}
		return companyNo;
	}

	public void setCompanyNo(String companyNo) {
		this.companyNo = companyNo;
	}

	
}
