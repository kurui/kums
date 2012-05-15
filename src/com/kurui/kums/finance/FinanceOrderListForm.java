package com.kurui.kums.finance;

import com.kurui.kums.base.ListActionForm;
import com.kurui.kums.base.Constant;
import com.kurui.kums.base.util.DateUtil;

public class FinanceOrderListForm extends ListActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id = Long.valueOf(0);
	public String userNo;
	public String groupMarkNo;// 关联订单号
	public String orderNo;// 订单号
	public Long agentId = Long.valueOf(0);// 客户
	public String operatorName;// 修改人

	private long platformId = Long.valueOf(0);// 平台id
	private long companyId = Long.valueOf(0);

	private long tranType = Long.valueOf(0);// 交易类型

	private String statusGroup;

	private long fromPlatformId;// 买入
	private long toPlatformId;// 卖出
	private long fromAccountId = Long.valueOf(0);// 付款
	private long toAccountId = Long.valueOf(0);// 收款
	private Long recentlyDay;// 是否查询最近

	private long groupId;
	private long groupCount = Long.valueOf(0);
	private long orderType = Long.valueOf(0);

	private String forwardPage = "";
	private String forwardPageFlag = "";

	private String platformIds = "";;
	private String accountIds = "";;

	private String companyType = "";
	private String companyStatus = "";

	private String memo = "";

	private String showType = "listData";// listChart listData

	public String getTranTypeGroup() {
		if (this.orderType == Statement.ORDERTYPE_1201) {
			return FinanceOrder.TRAN_TYPE_GROUP_1201;
		} else if (this.orderType == Statement.ORDERTYPE_15) {
			return FinanceOrder.TRAN_TYPE_GROUP_15;
		} else if (this.orderType == Statement.ORDERTYPE_1400) {
			return FinanceOrder.TRAN_TYPE_GROUP_1400;
		} else if (this.orderType == Statement.ORDERTYPE_1300) {
			return FinanceOrder.TRAN_TYPE_GROUP_1300;
		}
		return "";
	}

	public String[] getDaysArray() {
		String[] days = null;
		recentlyDay = Constant.toLong(recentlyDay);
		if (recentlyDay > 0) {
			days = DateUtil.getLastDays(recentlyDay.intValue());
		}

		if ("".equals(startDate) == false && "".equals(endDate) == false) {
			startDate = DateUtil.getDateString(startDate,
					"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd");
			endDate = DateUtil.getDateString(endDate, "yyyy-MM-dd HH:mm:ss",
					"yyyy-MM-dd");
			days = DateUtil.getDaysOfStartEnd(startDate, endDate);
		}
		return days;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public long getTranType() {
		return tranType;
	}

	public void setTranType(long tranType) {
		this.tranType = tranType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserNo() {
		return userNo;
	}

	public long getPlatformId() {
		return platformId;
	}

	public void setPlatformId(long platformId) {
		this.platformId = platformId;
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	public String getCompanyStatus() {
		return companyStatus;
	}

	public void setCompanyStatus(String companyStatus) {
		this.companyStatus = companyStatus;
	}

	public String getForwardPage() {
		return forwardPage;
	}

	public void setForwardPage(String forwardPage) {
		this.forwardPage = forwardPage;
	}

	public String getGroupMarkNo() {
		return groupMarkNo;
	}

	public void setGroupMarkNo(String groupMarkNo) {
		this.groupMarkNo = groupMarkNo;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public long getFromAccountId() {
		return fromAccountId;
	}

	public void setFromAccountId(long fromAccountId) {
		this.fromAccountId = fromAccountId;
	}

	public long getToAccountId() {
		return toAccountId;
	}

	public void setToAccountId(long toAccountId) {
		this.toAccountId = toAccountId;
	}

	public String getPlatformIds() {
		return platformIds;
	}

	public void setPlatformIds(String platformIds) {
		this.platformIds = platformIds;
	}

	public String getAccountIds() {
		return accountIds;
	}

	public void setAccountIds(String accountIds) {
		this.accountIds = accountIds;
	}

	public long getFromPlatformId() {
		return fromPlatformId;
	}

	public void setFromPlatformId(long fromPlatformId) {
		this.fromPlatformId = fromPlatformId;
	}

	public long getToPlatformId() {
		return toPlatformId;
	}

	public void setToPlatformId(long toPlatformId) {
		this.toPlatformId = toPlatformId;
	}

	public long getOrderType() {
		return orderType;
	}

	public void setOrderType(long orderType) {
		this.orderType = orderType;
	}

	public Long getAgentId() {
		return agentId;
	}

	public void setAgentId(Long agentId) {
		this.agentId = agentId;
	}

	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	public long getGroupCount() {
		return groupCount;
	}

	public void setGroupCount(long groupCount) {
		this.groupCount = groupCount;
	}

	public String getForwardPageFlag() {
		return forwardPageFlag;
	}

	public void setForwardPageFlag(String forwardPageFlag) {
		this.forwardPageFlag = forwardPageFlag;
	}

	public Long getRecentlyDay() {

		return recentlyDay;
	}

	public void setRecentlyDay(Long recentlyDay) {
		this.recentlyDay = recentlyDay;
	}

	public String getStatusGroup() {
		return statusGroup;
	}

	public void setStatusGroup(String statusGroup) {
		this.statusGroup = statusGroup;
	}

	public String getShowType() {
		return showType;
	}

	public void setShowType(String showType) {
		this.showType = showType;
	}

}