package com.kurui.kums.message;

import java.util.Date;

import com.kurui.kums.base.util.time.DateUtil;
/**
 * 执行业务事件时，发送此Message Instance,更新动态
 * @author yanrui
 * 
 * */
public class BusinessEventMessage extends BaseDomainObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long requestId;
	private String author;
	private String content;
	private Date updateTime;



	public long getRequestId() {
		return requestId;
	}

	public void setRequestId(long requestId) {
		this.requestId = requestId;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateDate() {
		String mydate = "";
		if (this.updateTime != null && "".equals(updateTime) == false) {
			Date tempDate = new Date(updateTime.getTime());
			mydate = DateUtil.getDateString(tempDate, "yyyy-MM-dd HH:mm:ss");
		}
		return mydate;
	}

	

}
