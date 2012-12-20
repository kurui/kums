package com.kurui.kums.message.biz;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kurui.kums.message.BusinessEventMessage;
import com.kurui.kums.message.event.EventReceiver;
import com.kurui.kums.message.event.EventSender;
/**
 * 发送业务事件
 * */
public class EventListener {

	private static Log log = LogFactory
			.getLog(EventListener.class);

	private EventSender messageSender = null;
	private EventReceiver messageReceiver = null;

	public void sendRequest(BusinessEventMessage businessEventMessage) {
		log.debug("sendAUSCreditRequest called.");

		messageSender.send(businessEventMessage);

		log.debug("Done sending the loan details to credit request.");
	}

	public EventSender getMessageSender() {
		return messageSender;
	}

	public void setMessageSender(EventSender messageSender) {
		this.messageSender = messageSender;
	}

	public EventReceiver getMessageReceiver() {
		return messageReceiver;
	}

	public void setMessageReceiver(EventReceiver messageReceiver) {
		this.messageReceiver = messageReceiver;
	}

}
