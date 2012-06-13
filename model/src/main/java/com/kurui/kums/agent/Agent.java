package com.kurui.kums.agent;

import java.math.BigDecimal;
import java.util.Date;

import com.kurui.kums.base.Constant;
import com.kurui.kums.base.util.DateUtil;
import com.kurui.kums.base.util.StringUtil;
import com.kurui.kums.transaction.util.DataTypeStore;
import com.kurui.kums.agent.entity._Agent;

public class Agent extends _Agent {
	private static final long serialVersionUID = 1L;
	private long accountId = Long.valueOf(0);
	private long companyId = Long.valueOf(0);// 外键 公司ID
	private long directLevelId = Long.valueOf(0);
	private String[] stampGroupItem;
	private String addType = "";//

	// 关系特征
	public static final long STAMP_TYPE_1 = 1;// 同学
	public static final long STAMP_TYPE_2 = 2;// 同事
	public static final long STAMP_TYPE_3 = 3;// 网友

	// 类型
	public static final long TYPE_1 = 1;// VIP
	public static final long TYPE_2 = 2;// 主要
	public static final long TYPE_3 = 3;// 普通
	public static final long TYPE_4 = 4;// 小客户
	public static final long TYPE_11 = 11;// 潜在客户
	public static final long TYPE_80 = 80;// 无

	// sex
	public static final long SEX_0 = 0;// 未知
	public static final long SEX_1 = 1;// 男
	public static final long SEX_2 = 2;// 女

	// marriage
	public static final long marriage_0 = 0;// 未知
	public static final long marriage_1 = 1;// 单身
	public static final long marriage_2 = 2;// 有男/女朋友
	public static final long marriage_3 = 3;// 已婚
	public static final long marriage_4 = 3;// 已育

	// 状态
	public static final long STATES_1 = 1;// 有效
	public static final long STATES_0 = 0;// 无效
	// private String charSet = "GBK";

	public String getStampTypeInfo() {
		String info = "";
		if (this.stampGroup != null) {
			stampGroupItem = StringUtil.getSplitString(this.stampGroup, ",");
			if (stampGroupItem != null) {
				for (int i = 0; i < stampGroupItem.length; i++) {
					Long item = Constant.toLong(stampGroupItem[i]);
					String itemInfo = getStampTypeInfoByValue(item);
					if (itemInfo != "") {
						info += itemInfo + ",";
					}
				}
				if (info.indexOf(",", info.length() - 1) > 1) {
					info = info.substring(0, info.length() - 1);
				}
			}
		}
		return info;
	}

	public String getStampTypeInfoByValue(Long type) {
		if (type != null) {
			if (type.intValue() == STAMP_TYPE_1) {
				return "同学";
			} else if (type.intValue() == STAMP_TYPE_2) {
				return "同事";
			} else if (type.intValue() == STAMP_TYPE_3) {
				return "网友";
			} else {
				return "";
			}
		} else {
			return "";
		}
	}

	public String getTypeInfo() {
		if (this.getType() != null) {
			if (this.getType().intValue() == TYPE_1) {
				return "VIP客户";
			} else if (this.getType().intValue() == TYPE_2) {
				return "主要客户";
			} else if (this.getType().intValue() == TYPE_3) {
				return "普通客户";
			} else if (this.getType().intValue() == TYPE_4) {
				return "小客户";
			} else if (this.getType().intValue() == TYPE_11) {
				return "潜在客户";
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	public String getLoyalIndexInfo() {
		return DataTypeStore.getDataTypeNameByNo(loyalIndex+"");
	}

	public String getAssetIndexInfo() {
		return DataTypeStore.getDataTypeNameByNo(assetIndex+"");
	}

	public String getFriendIndexInfo() {
		return DataTypeStore.getDataTypeNameByNo(friendIndex+"");
	}

	public String getSpecialIndexInfo() {
		return DataTypeStore.getDataTypeNameByNo(specialIndex+"");
	}
	
	public String getTightIndexInfo() {
		return DataTypeStore.getDataTypeNameByNo(tightIndex+"");
	}


	public String getSexInfo() {
		if (this.getSex() != null) {
			if (this.getSex().intValue() == SEX_1) {
				return "男";
			} else if (this.getSex().intValue() == SEX_2) {
				return "女";
			} else if (this.getSex().intValue() == SEX_0) {
				return "未知";
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

	private String updateDate="";
	public String getUpdateDate() {
		String mydate = "";
		if (this.updateTime != null && "".equals(updateTime) == false) {
			Date tempDate = new Date(updateTime.getTime());
			mydate = DateUtil.getDateString(tempDate, "yyyy-MM-dd");
		}
		return mydate;
	}

	
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getShortAddress() {
		if(address!=null){
			if(address.length()>15){
				return address.substring(0, 10);
			}else{
				return address;
			}
			
		}
		return address;
	}

	public long getDirectLevelId() {
		return directLevelId;
	}

	public void setDirectLevelId(long directLevelId) {
		this.directLevelId = directLevelId;
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public String[] getStampGroupItem() {
		return stampGroupItem;
	}

	public void setStampGroupItem(String[] stampGroupItem) {
		this.stampGroupItem = stampGroupItem;
	}

	public String getAddType() {
		return addType;
	}

	public void setAddType(String addType) {
		this.addType = addType;
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public BigDecimal getPhysicalAmount() {
		if (physicalAmount == null) {
			return BigDecimal.ZERO;
		}
		return physicalAmount;
	}

	public BigDecimal getVirtualAmount() {
		if (virtualAmount == null) {
			return BigDecimal.ZERO;
		}
		return virtualAmount;
	}

	public BigDecimal getTotalIntegral() {
		if (totalIntegral == null) {
			return BigDecimal.ZERO;
		}
		return totalIntegral;
	}

	public BigDecimal getCreditAmount() {
		if (creditAmount == null) {
			return BigDecimal.ZERO;
		}
		return creditAmount;
	}

}
