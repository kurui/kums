package com.kurui.kums.agent;

import java.util.Date;

import com.kurui.kums.agent._entity._AgentResume;
import com.kurui.kums.base.util.DateUtil;

public class AgentResume extends _AgentResume {

	private static final long serialVersionUID = 1L;

	private long agentId = Long.valueOf(0);
	private String agentNo = "";

	private long companyId = Long.valueOf(0);

	// 類型
	public static final long TYPE_1 = 1;// 默認

	// 状态
	public static final long STATES_1 = 1;// 有效
	public static final long STATES_0 = 0;// 无效
	
	public String lastAction="";
	public int intPage;
	public int pageCount;
	

	public String getLastAction() {
		return lastAction;
	}

	public void setLastAction(String lastAction) {
		this.lastAction = lastAction;
	}
	
	

	public int getIntPage() {
		return intPage;
	}

	public void setIntPage(int intPage) {
		this.intPage = intPage;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

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

	private String beginDate = "";
	private String endDate = "";

	public String getBeginDate() {
		String mydate = "";
		if (beginDate == "") {
			if (this.beginTime != null && "".equals(beginTime) == false) {
				Date tempDate = new Date(beginTime.getTime());
				return DateUtil.getDateString(tempDate, "yyyy-MM-dd");
			}
		} else {
			return beginDate;
		}
		return mydate;
	}

	public String getEndDate() {
		String mydate = "";
		if (endDate == "") {
			if (this.endTime != null && "".equals(endTime) == false) {
				Date tempDate = new Date(endTime.getTime());
				return DateUtil.getDateString(tempDate, "yyyy-MM-dd");
			}
		} else {
			return endDate;
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

}
