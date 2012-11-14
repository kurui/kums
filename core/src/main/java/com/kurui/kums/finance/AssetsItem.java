package com.kurui.kums.finance;

import java.math.BigDecimal;
import java.util.Date;

import com.kurui.kums.base.util.time.DateUtil;
import com.kurui.kums.finance._entity._AssetsItem;
import com.kurui.kums.transaction.util.DataTypeStore;

public class AssetsItem extends _AssetsItem {

	private static final long serialVersionUID = 1L;

	private long agentId = Long.valueOf(0);
	private String financeOrderIdGroup="";
	private String assetsItemIdGroup="";


	public static final long TYPE_1 = 1;// 默认

	// 状态
	public static final long STATES_1 = 1;// 有效
	public static final long STATES_0 = 0;// 无效
	
	public AssetsItem(){
		
	}
	
	public AssetsItem(String itemType/*,String itemTypeName*/,Long itemCount,BigDecimal itemValuation){
		this.itemType=itemType;
		this.itemCount=itemCount;
		this.valuation=itemValuation;
		
	}
	
	public String getItemTypeName() {
		if (this.getItemType() != null) {
			return DataTypeStore.getDataTypeNameByNo(this.getItemType());
		} else {
			return null;
		}
	}

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
	public String getLastDeprecDate() {
		String mydate = "";
		if (this.lastDeprecTime != null && "".equals(lastDeprecTime) == false) {
			Date tempDate = new Date(lastDeprecTime.getTime());
			mydate = DateUtil.getDateString(tempDate, "yyyy-MM-dd");
		}
		return mydate;
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

	public String getFinanceOrderIdGroup() {
		return financeOrderIdGroup;
	}

	public void setFinanceOrderIdGroup(String financeOrderIdGroup) {
		this.financeOrderIdGroup = financeOrderIdGroup;
	}
	
	
	public String getAssetsItemIdGroup() {
		return assetsItemIdGroup;
	}

	public void setAssetsItemIdGroup(String assetsItemIdGroup) {
		this.assetsItemIdGroup = assetsItemIdGroup;
	}

}
