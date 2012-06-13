package com.kurui.kums.system;

import java.util.Date;

import com.kurui.kums.base.util.DateUtil;
import com.kurui.kums.system.entity._News;

public class News extends _News {
	private static final long serialVersionUID = 1L;

	public String getCreateDate() {
		String mydate = "";
		if (this.createTime != null && "".equals(createTime) == false) {
			Date tempDate = new Date(createTime.getTime());
			mydate = DateUtil.getDateString(tempDate, "yyyy-MM-dd");
		}
		return mydate;
	}
}
