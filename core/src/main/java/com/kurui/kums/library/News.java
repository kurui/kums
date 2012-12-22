package com.kurui.kums.library;

import java.util.Date;

import com.kurui.kums.base.util.time.DateUtil;
import com.kurui.kums.library._entity._News;

public class News extends _News {
	private static final long serialVersionUID = 1L;
	
	public static final long TYPE_1 = 1;// 系统新闻
	public static final long TYPE_10 = 10;// 业务动态
	public static final long TYPE_20 = 20;// 建议
	
	public static final long STATUS_1 = 1;// 正常
	public static final long STATUS_2 = 2;// 暂不显示
	public static final long STATUS_0 = 0;//无效

	public String getStatusText() {
		String statusText = "";
		statusText = getStatusTextByValue(this.getStatus());
		return statusText;
	}
	
	public static String getStatusTextByValue(Long status) {
		String statusText = "";
		if (status != null) {
			if (status == STATUS_1) {
				statusText = "正常";
			}  else if (status == STATUS_2) {
				statusText = "暂不显示";
			}else if (status == STATUS_0) {
				statusText = "无效";
			} else {
				statusText = "未定义状态";
			}
		} else {
			statusText = "";
		}
		return statusText;
	}
	
	public String getTypeText() {
		String typeText = "";
		typeText = getTypeTextByValue(this.getType());
		return typeText;
	}
	
	public static String getTypeTextByValue(Long type) {
		String typeText = "";
		if (type != null) {
			if (type == TYPE_1) {
				typeText = "系统新闻";
			} else if (type == TYPE_10) {
				typeText = "业务动态";
			} else if (type == TYPE_20) {
				typeText = "大家有话说";//建议
			}else {
				typeText = "未定义状态";
			}
		} else {
			typeText = "";
		}
		return typeText;
	}

	public String getCreateDate() {
		String mydate = "";
		if (this.createTime != null && "".equals(createTime) == false) {
			Date tempDate = new Date(createTime.getTime());
			mydate = DateUtil.getDateString(tempDate, "yyyy-MM-dd HH:mm:ss");
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
}
