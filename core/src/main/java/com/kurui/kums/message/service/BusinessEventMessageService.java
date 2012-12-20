package com.kurui.kums.message.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kurui.kums.message.BusinessEventMessage;

public class BusinessEventMessageService {

	private static Log log = LogFactory
			.getLog(BusinessEventMessageService.class);

	private BusinessEventSender messageSender = null;
	private BusinessEventReceiver messageReceiver = null;

	public void sendAUSCreditRequest(BusinessEventMessage businessEventMessage) {
		log.debug("sendAUSCreditRequest called.");

		messageSender.send(businessEventMessage);

		log.debug("Done sending the loan details to credit request.");
	}

	public BusinessEventSender getMessageSender() {
		return messageSender;
	}

	public void setMessageSender(BusinessEventSender messageSender) {
		this.messageSender = messageSender;
	}

	public BusinessEventReceiver getMessageReceiver() {
		return messageReceiver;
	}

	public void setMessageReceiver(BusinessEventReceiver messageReceiver) {
		this.messageReceiver = messageReceiver;
	}

}
