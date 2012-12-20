package com.kurui.kums.message.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kurui.kums.message.BusinessEventMessage;
import com.kurui.kums.message.MessageResult;

public class BusinessEventProcessService {

	private static Log log = LogFactory.getLog(BusinessEventProcessService.class);
	
	private MessageResultSender messageSender = null;
	private BusinessEventReceiverProcess messageReceiver = null;


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

	public BusinessEventReceiverProcess getMessageReceiver() {
		return messageReceiver;
	}

	public void setMessageReceiver(BusinessEventReceiverProcess messageReceiver) {
		this.messageReceiver = messageReceiver;
	}

	public MessageResultSender getMessageSender() {
		return messageSender;
	}

	public void setMessageSender(MessageResultSender messageSender) {
		this.messageSender = messageSender;
	}
	
	
	
}
