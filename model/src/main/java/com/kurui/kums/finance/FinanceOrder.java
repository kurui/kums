package com.kurui.kums.finance;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.kurui.kums.base.Constant;
import com.kurui.kums.base.MyLabel;
import com.kurui.kums.base.Operate;
import com.kurui.kums.base.util.DateUtil;
import com.kurui.kums.base.util.StringUtil;
import com.kurui.kums.finance.entity._FinanceOrder;
import com.kurui.kums.right.UserRightInfo;
import com.kurui.kums.right.UserStore;
import com.kurui.kums.transaction.util.DataTypeStore;

public class FinanceOrder extends _FinanceOrder {
	private static final long serialVersionUID = 1L;
	private String forwardPage;
	private long groupId;

	private Long platComAccountId = Long.valueOf(0);
	private Long platformId = Long.valueOf(0);
	private Long companyId = Long.valueOf(0);
	private Long accountId = Long.valueOf(0);
	private Long agentId = Long.valueOf(0);
	private String agentNo = "";
	private String agentName = "";
	private String businessDate = "";
	private String maturityDate = "";
	private Long operateLogType = Long.valueOf(0);

	private Long[] financeOrderIds = new Long[0];
	private Long[] platformIds = new Long[0];
	private Long[] companyIds = new Long[0];
	private Long[] cussentCompanyIds = new Long[0];
	private BigDecimal[] handlingCharges = new BigDecimal[0];
	private String[] outOrderNos = new String[0];
	private Timestamp[] businessTimes = new Timestamp[0];
	private String[] businessDates = new String[0];
	private String[] memos = new String[0];
	private Long[] tranTypes = new Long[0];
	private BigDecimal[] totalAmounts = new BigDecimal[0];

	private Long[] productIds = new Long[0];
	private String[] productNames = new String[0];
	private BigDecimal[] productPrices = new BigDecimal[0];
	private BigDecimal[] productCounts = new BigDecimal[0];
	private BigDecimal[] detailAmounts = new BigDecimal[0];
	private String[] detailMemos = new String[0];

	private String operatorName = "";
	private Operate operate = new Operate();
	private Operate commonOperate = new Operate();
	private Operate teamCommonOperate = new Operate();

	private UserRightInfo uri = new UserRightInfo();
	private String path;

	private Long finishedStatus = Long.valueOf(0);

	// 业务类型
	public static final long BUSINESSTYPE_1 = 1;// 销售 借项
	public static final long BUSINESSTYPE_2 = 2;// 采购 贷项

	// 交易类型
	// 管理费用
	public static String TRAN_TYPE_GROUP_1201 = "";

	// 主营业务
	public static String TRAN_TYPE_GROUP_15 = "";
	public static final long TRANTYPE_1501 = 1501;// 保健品销售
	public static final long TRANTYPE_1560 = 1560;// 充值销售
	public static final long TRANTYPE_1565 = 1565;// 网购销售(实物)
	public static final long TRANTYPE_1570 = 1570;// 机票销售
	public static final long TRANTYPE_1580 = 1580;// 软件销售
	public static final long TRANTYPE_1590 = 1590;// 金融衍生品销售
	public static final long TRANTYPE_1591 = 1591;// 保险销售

	// 债务
	public static String TRAN_TYPE_GROUP_1400 = "";
	public static final long TRANTYPE_1401 = 1401;// 借入债务
	public static final long TRANTYPE_1411 = 1411;// 偿还债务

	// 债权
	public static String TRAN_TYPE_GROUP_1300 = "";
	public static final long TRANTYPE_1301 = 1301;// 借出债权
	public static final long TRANTYPE_1311 = 1311;// 收回债权

	// 订单状态
	public static final long STATUS_1 = 1;// 新订单
	public static final long STATUS_10 = 10;// 交易成功
	public static final long STATUS_11 = 11;// 交易结束

	public static final long STATUS_88 = 88;// 已经删除

	public FinanceOrder() {

	}

	public FinanceOrder(long groupId) {
		this.orderGroup.setId(groupId);
	}

	public Long getOrderType() {
		return getOrderTypeByTranType(this.tranType);
	}

	public static Long getOrderTypeByTranType(Long tranType) {
		String tranTypeStr = Constant.toLong(tranType) + "";
		Long orderType = Long.valueOf(0);

		if (StringUtil.containsExistString(tranTypeStr, TRAN_TYPE_GROUP_1201,
				",")) {
			orderType = Statement.ORDERTYPE_1201;
		}
		if (StringUtil
				.containsExistString(tranTypeStr, TRAN_TYPE_GROUP_15, ",")) {
			orderType = Statement.ORDERTYPE_15;
		}
		if (StringUtil.containsExistString(tranTypeStr, TRAN_TYPE_GROUP_1400,
				",")) {
			orderType = Statement.ORDERTYPE_1400;
		}
		if (StringUtil.containsExistString(tranTypeStr, TRAN_TYPE_GROUP_1300,
				",")) {
			orderType = Statement.ORDERTYPE_1300;
		}
		return orderType;
	}

	public String getBusinessTypeText() {
		String businessTypeText = "";
		if (this.getBusinessType() != null) {
			if (this.getBusinessType() == BUSINESSTYPE_1) {
				businessTypeText = "销售";
			} else if (this.getBusinessType() == BUSINESSTYPE_2) {
				businessTypeText = "采购";
			} else {
				businessTypeText = "";
			}
		} else {
			businessTypeText = "";
		}
		return businessTypeText;
	}

	public String getTranTypeText() {
		String tranTypeText = "";
		if (this.getTranType() != null) {
			tranTypeText = DataTypeStore.getDataTypeNameByNo(this.getTranType()
					+ "");
		} else {
			tranTypeText = "";
		}
		return tranTypeText;
	}

	public String getOrderNo() {
		if (this.orderNo == null || this.orderNo.equals("")) {
			return "O000000000000";
		}
		return orderNo;
	}

	public Long getoperateLogType() {
		return operateLogType;
	}

	public void setoperateLogType(Long operateLogType) {
		this.operateLogType = operateLogType;
	}

	public String getRebateText() {
		return "";
	}

	public java.math.BigDecimal getTotalAmount() {
		if (this.totalAmount == null) {
			return new BigDecimal(0);
		}
		return this.totalAmount.abs();
	}

	public String getStatusText() {
		String statusText = "";
		statusText = getStatusTextByValue(this.getStatus());
		return statusText;
	}

	public static String getStatusTextByValue(Long status) {
		String statusText = "";
		if (status != null) {
			if (status == STATUS_1) {
				statusText = "新订单";
			} else if (status == STATUS_10) {
				statusText = "交易成功";
			} else if (status == STATUS_11) {
				statusText = "交易结束";
			} else {
				statusText = "未定义状态";
			}
		} else {
			statusText = "";
		}
		return statusText;
	}

	// 修改人（录单人）
	public String getShowEntryOperator() {
		return getEntryOperator();
	}

	// 修改人（录单人）
	public String getShowEntryOperatorName() {
		String entryOperator = getShowEntryOperator();
		if (entryOperator != null && "".equals(entryOperator) == false) {
			return UserStore.getUserNameByNo(entryOperator);
		}
		return "";
	}

	public String getBusinessDate() {
		String mydate = "";
		if (this.businessTime != null && "".equals(businessTime) == false) {
			Date tempDate = new Date(businessTime.getTime());
			mydate = DateUtil.getDateString(tempDate, "yyyy-MM-dd");
		}
		return mydate;
	}

	public void setBusinessDate(String businessDate) {
		this.businessDate = businessDate;
	}

	public String getMaturityDate() {
		if (this.maturityTime != null && "".equals(maturityTime) == false) {
			Date tempDate = new Date(maturityTime.getTime());
			String mydate = DateUtil.getDateString(tempDate, "yyyy-MM-dd");
			return mydate;
		}
		return maturityDate;
	}

	public void setMaturityDate(String maturityDate) {
		this.maturityDate = maturityDate;
	}

	public java.math.BigDecimal getHandlingCharge() {
		if (this.handlingCharge == null) {
			return BigDecimal.ZERO;
		}
		return this.handlingCharge;
	}

	public Long getPlatComAccountId() {
		return platComAccountId;
	}

	public void setPlatComAccountId(Long platComAccountId) {
		this.platComAccountId = platComAccountId;
	}

	public Long getPlatformId() {
		return platformId;
	}

	public void setPlatformId(Long platformId) {
		this.platformId = platformId;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public Long[] getCussentCompanyIds() {
		return cussentCompanyIds;
	}

	public void setCussentCompanyIds(Long[] cussentCompanyIds) {
		this.cussentCompanyIds = cussentCompanyIds;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getAgentNo() {
		return agentNo;
	}

	public void setAgentNo(String agentNo) {
		this.agentNo = agentNo;
	}

	public Long getAgentId() {
		return agentId;
	}

	public void setAgentId(Long agentId) {
		this.agentId = agentId;
	}

	public String getForwardPage() {
		return forwardPage;
	}

	public void setForwardPage(String forwardPage) {
		this.forwardPage = forwardPage;
	}

	public String[] getOutOrderNos() {
		return outOrderNos;
	}

	public void setOutOrderNos(String[] outOrderNos) {
		this.outOrderNos = outOrderNos;
	}

	public String[] getMemos() {
		return memos;
	}

	public void setMemos(String[] memos) {
		this.memos = memos;
	}

	public Long[] getPlatformIds() {
		return platformIds;
	}

	public void setPlatformIds(Long[] platformIds) {
		this.platformIds = platformIds;
	}

	public Long[] getFinanceOrderIds() {
		return financeOrderIds;
	}

	public void setFinanceOrderIds(Long[] financeOrderIds) {
		this.financeOrderIds = financeOrderIds;
	}

	public Long[] getCompanyIds() {
		return companyIds;
	}

	public void setCompanyIds(Long[] companyIds) {
		this.companyIds = companyIds;
	}

	public BigDecimal[] getHandlingCharges() {
		return handlingCharges;
	}

	public void setHandlingCharges(BigDecimal[] handlingCharges) {
		this.handlingCharges = handlingCharges;
	}

	public Timestamp[] getBusinessTimes() {
		return businessTimes;
	}

	public void setBusinessTimes(Timestamp[] businessTimes) {
		this.businessTimes = businessTimes;
	}

	public String[] getBusinessDates() {
		return businessDates;
	}

	public void setBusinessDates(String[] businessDates) {
		this.businessDates = businessDates;
	}

	public Long[] getTranTypes() {
		return tranTypes;
	}

	public void setTranTypes(Long[] tranTypes) {
		this.tranTypes = tranTypes;
	}

	public BigDecimal[] getTotalAmounts() {
		return totalAmounts;
	}

	public void setTotalAmounts(BigDecimal[] totalAmounts) {
		this.totalAmounts = totalAmounts;
	}

	public Operate getTeamCommonOperate() {
		return teamCommonOperate;
	}

	public void setTeamCommonOperate(Operate teamCommonOperate) {
		this.teamCommonOperate = teamCommonOperate;
	}

	public Long[] getProductIds() {
		return productIds;
	}

	public void setProductIds(Long[] productIds) {
		this.productIds = productIds;
	}

	public String[] getProductNames() {
		return productNames;
	}

	public void setProductNames(String[] productNames) {
		this.productNames = productNames;
	}

	public BigDecimal[] getProductPrices() {
		return productPrices;
	}

	public void setProductPrices(BigDecimal[] productPrices) {
		this.productPrices = productPrices;
	}

	public BigDecimal[] getProductCounts() {
		return productCounts;
	}

	public void setProductCounts(BigDecimal[] productCounts) {
		this.productCounts = productCounts;
	}

	public BigDecimal[] getDetailAmounts() {
		return detailAmounts;
	}

	public void setDetailAmounts(BigDecimal[] detailAmounts) {
		this.detailAmounts = detailAmounts;
	}

	public String[] getDetailMemos() {
		return detailMemos;
	}

	public void setDetailMemos(String[] detailMemos) {
		this.detailMemos = detailMemos;
	}

	public Operate getOperate() {
		return operate;
	}

	public void setOperate(Operate operate) {
		this.operate = operate;
	}

	public UserRightInfo getUri() {
		return uri;
	}

	public void setUri(UserRightInfo uri) {
		this.uri = uri;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getGroupMarkNo() {
		if (this.orderGroup == null) {
			return "GMN";
		}
		return this.getOrderGroup().getNo();
	}

	public String getSubGroupMark() {// 小组
		if (this.orderGroup == null)
			return "FF";
		else
			return this.orderGroup.getId() + "-" + getSubGroupMarkNo();
	}

	public String getGroupMark() {// 大组
		if (this.orderGroup == null)
			return "FF";
		else
			return this.orderGroup.getId() + "--";
	}

	public String getOutOrderNo() {
		if (this.outOrderNo == null) {
			return "";
		}
		return this.outOrderNo;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getMemo() {
		if (this.memo == null) {
			return "";
		}
		return this.memo;
	}

	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	public Operate getCommonOperate() {
		return commonOperate;
	}

	public void setCommonOperate(Operate commonOperate) {
		this.commonOperate = commonOperate;
	}

	public void getGeneralOperate(List<MyLabel> myLabels) {

		// 未还债务
		if (this.tranType == TRANTYPE_1401 && this.status == STATUS_10) {
			// if (uri.hasRight("sb43")) {
			MyLabel ml = new MyLabel();
			StringBuffer sb = new StringBuffer();
			sb.append("onclick=\"");
			sb
					.append("openWindow(500,500,'../finance/financeOrderList.do?thisAction=addRepayCreditOrder");
			sb.append("&id=" + this.id);
			sb.append("')");
			sb.append("\"");
			ml.setEvents(sb.toString());
			ml.setLabText("[还款]");
			ml.setEndText("<br/>");
			myLabels.add(ml);
			operate.setMyLabels(myLabels);
			// }
		}

		// 未收债权
		if (this.tranType == TRANTYPE_1301 && this.status == STATUS_10) {
			// if (uri.hasRight("sb43")) {
			MyLabel ml = new MyLabel();
			StringBuffer sb = new StringBuffer();
			sb.append("onclick=\"");
			sb
					.append("openWindow(500,500,'../finance/financeOrderList.do?thisAction=addRepayCreditOrder");
			sb.append("&id=" + this.id);
			sb.append("')");
			sb.append("\"");
			ml.setEvents(sb.toString());
			ml.setLabText("[收款]");
			ml.setEndText("<br/>");
			myLabels.add(ml);
			operate.setMyLabels(myLabels);
			// }
		}

		// /待处理新订单
		// if (this.tranType == 1 && this.status == 1) {
		// if (uri.hasRight("sb43")) {
		// MyLabel ml = new MyLabel();
		// StringBuffer sb = new StringBuffer();
		// sb.append("onclick=\"");
		// sb.append("showDiv17(");
		// sb.append("'" + this.id + "'");
		// sb.append(")\"");
		// ml.setEvents(sb.toString());
		// ml.setLabText("[取消出票]");
		// ml.setEndText("<br/>");
		// myLabels.add(ml);
		// operate.setMyLabels(myLabels);
		// }
		// if (uri.hasRight("sb42")) {
		// MyLabel ml2 = new MyLabel();
		// StringBuffer sb = new StringBuffer();
		// sb.append("onclick=\"");
		// sb.append("showDiv9(");
		// sb.append("'" + this.id + "',");
		// sb.append("'" + this.totalAmount + "',");
		// sb.append("'" + this.getEntryOrderDate() + "'");
		// sb.append(")\"");
		// ml2.setEvents(sb.toString());
		// ml2.setLabText("[申请支付]");
		// ml2.setEndText("<br/>");
		// myLabels.add(ml2);
		// operate.setMyLabels(myLabels);
		// }
		// }
	}

	/**
	 * 管理修改
	 */
	public void getGenalManageLabel(List<MyLabel> myLabels) {
		// if (uri.hasRight("sb81")) {
		MyLabel ml = new MyLabel();
		ml.setHref(this.path
				+ "/finance/financeOrderList.do?thisAction=editOrder&id="
				+ this.id);
		ml.setLabText("[编辑]");
		// ml.setEndText("<br/>");
		myLabels.add(ml);
		commonOperate.setMyLabels(myLabels);
		// }

		// if (uri.hasRight("sb82")) {
		MyLabel m2 = new MyLabel();
		StringBuffer sb = new StringBuffer();
		sb.append("onclick=\"");
		sb.append("return confirm('确定删除吗?');");
		sb.append("\"");
		m2.setEvents(sb.toString());

		m2
				.setHref(this.path
						+ "/finance/financeOrderList.do?thisAction=deleteFinanceOrder&id="
						+ this.id + "&forwardPageFlag=General");
		m2.setLabText("[删除]");
		m2.setEndText("<br/>");
		myLabels.add(m2);
		commonOperate.setMyLabels(myLabels);
		// }
	}

	/**
	 * 备注修改
	 */
	public void getRemarkLabel(List<MyLabel> myLabels) {
		if (uri.hasRight("sb30")) {
			MyLabel ml = new MyLabel();
			StringBuffer sb = new StringBuffer();
			sb.append("onclick=\"");
			sb.append("showDiv11(");
			sb.append("'" + this.id + "'");
			sb.append(")\"");
			ml.setEvents(sb.toString());
			if (this.memo != null) {
				ml.setLabText("<font color=\"red\">[备注]</font>");
			} else {
				ml.setLabText("[备注]");
			}
			// ml.setEndText("<br/>");
			myLabels.add(ml);

			commonOperate.setMyLabels(myLabels);
		}
	}

	/**
	 * 关联订单
	 */
	// public void getRelateLabel(List<MyLabel> myLabels) {
	// MyLabel ml = new MyLabel();
	// ml.setHref(this.path
	// + "/finance/financeOrderList.do?thisAction=processing&id="
	// + this.id);
	// ml.setLabText("[关联]");
	// ml.setEndText("<br/>");
	// myLabels.add(ml);
	// commonOperate.setMyLabels(myLabels);
	// }
	public String getCommonOperateText() {
		List<MyLabel> myLabels = new ArrayList<MyLabel>();
		if (this.tranType == null || this.status == null) {
			return "";
		} else {
			getGenalManageLabel(myLabels);
			// getRemarkLabel(myLabels);
			// getRelateLabel(myLabels);
		}
		String commonOperateText = commonOperate.getOperateText();
		return commonOperateText;
	}

	// 没有关联修改链接
	public String getCommonOperateTextNoRelate() {
		List<MyLabel> myLabels = new ArrayList<MyLabel>();
		if (this.tranType == null || this.status == null) {
			return "";
		} else {
			getGenalManageLabel(myLabels);
			getRemarkLabel(myLabels);
		}
		String commonOperateText = commonOperate.getOperateText();
		// System.out.println(commonOperateText);
		return commonOperateText;
	}

	public String getTradeOperate() {
		List<MyLabel> myLabels = new ArrayList<MyLabel>();
		if (this.tranType == null) {
			System.out.println("order id:" + this.id + "tranType is null");
			return "";
		}

		if (this.status == null) {
			System.out.println("order id:" + this.id + "status is null");
			return "";
		}

		getGeneralOperate(myLabels);

		String operateText = operate.getOperateText();

		return operateText;
	}

	public String getGroupOrderNo() {
		if (this.orderGroup != null) {
			return this.orderGroup.getNo() + this.getSubGroupMarkNo();
		}
		return "";
	}

	public static String GeneralManagePath = "/finance/financeOrderList.do?thisAction=listFinanceOrder";
	public static String LiveManagePath = "/finance/financeOrderList.do?thisAction=listLiveOrder";
	public static String MainManagePath = "/finance/financeOrderList.do?thisAction=listMainOrder";
	public static String CreditManagePath = "/finance/financeOrderList.do?thisAction=listCreditOrder";
	public static String ViewPath = "/finance/financeOrderList.do?thisAction=view";

	public Long getFinishedStatus() {
		return finishedStatus;
	}

	public void setFinishedStatus(Long finishedStatus) {
		this.finishedStatus = finishedStatus;
	}

}
