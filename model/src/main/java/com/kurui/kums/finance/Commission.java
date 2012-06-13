package com.kurui.kums.finance;

import java.util.Date;
import com.kurui.kums.base.util.DateUtil;
import com.kurui.kums.finance.entity._Commission;

public class Commission extends _Commission {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long financeOrderId = Long.valueOf(0);
	private Long crossAgentId = Long.valueOf(0);
	private Long saleEventId = Long.valueOf(0);
	
	public static final long TYPE_1 = 1;// 默认

	// 状态
	public static final long STATES_1 = 1;// 有效
	public static final long STATES_0 = 0;// 无效
	
	

	public String getFormatCreateTime() {
		String mydate = "";
		if (this.createTime != null && "".equals(createTime) == false) {
			Date tempDate = new Date(createTime.getTime());
			mydate = DateUtil.getDateString(tempDate, "yyyy-MM-dd HH:mm:ss");
		}
		return mydate;
	}

	public String getTypeInfo() {
		if (this.getType() != null) {
			if (this.getType() == TYPE_1) {
				return "默认";
			} else {
				return "";
			}
		} else {
			return "";
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

	public Long getFinanceOrderId() {
		return financeOrderId;
	}

	public void setFinanceOrderId(Long financeOrderId) {
		this.financeOrderId = financeOrderId;
	}

	public Long getCrossAgentId() {
		return crossAgentId;
	}

	public void setCrossAgentId(Long crossAgentId) {
		this.crossAgentId = crossAgentId;
	}

	public Long getSaleEventId() {
		return saleEventId;
	}

	public void setSaleEventId(Long saleEventId) {
		this.saleEventId = saleEventId;
	}

}
