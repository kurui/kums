package com.kurui.kums.message.biz;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kurui.kums.message.BusinessEventMessage;
import com.kurui.kums.message.MessageResult;
import com.kurui.kums.message.event.EventReceiver;
import com.kurui.kums.message.trends.TrendsSender;
/**
 * 监听业务事件消息，执行，返回处理动态
 * 
 * */
public class TrendsListener {

	private static Log log = LogFactory.getLog(TrendsListener.class);
	
	private EventReceiver messageReceiver = null;
	private TrendsSender messageSender = null;


	public void processCreditRequest() {
		log.debug("processCreditRequest called.");
		
		BusinessEventMessage notice = messageReceiver.receive();
		
		MessageResult messageResult = new MessageResult();
		messageResult.setRequestId(notice.getRequestId());
		messageResult.setContent(notice.getContent());
		messageResult.setSuccess(true);
		
	
		
		messageSender.send(messageResult);
		
		log.debug("Done sending the loan details to credit request.");
	}

	public EventReceiver getMessageReceiver() {
		return messageReceiver;
	}

	public void setMessageReceiver(EventReceiver messageReceiver) {
		this.messageReceiver = messageReceiver;
	}

	public TrendsSender getMessageSender() {
		return messageSender;
	}

	public void setMessageSender(TrendsSender messageSender) {
		this.messageSender = messageSender;
	}
	
	
	
}
