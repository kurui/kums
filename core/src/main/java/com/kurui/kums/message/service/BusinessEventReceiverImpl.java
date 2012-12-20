package com.kurui.kums.message.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kurui.kums.message.BusinessEventMessage;

public class BusinessEventReceiverImpl implements BusinessEventReceiver {
	
	private static Log log = LogFactory.getLog(BusinessEventReceiverImpl.class);
	
	private BusinessEventReceiverJms jmsReceiver;
	
	
	public BusinessEventMessage receive() {
		BusinessEventMessage businessEvent = new BusinessEventMessage();
		try {
			log.debug("MessageReceiverBusinessEventProcessImpl started");
	
			businessEvent = jmsReceiver.receiveMessage();
		
			log.debug("MessageReceiverImpl end");
		} catch(Exception e) {
			log.error(e,e);
		}
		
		return businessEvent;
	}
	
	public BusinessEventReceiverJms getJmsReceiver() {
		return this.jmsReceiver;
	}
	
	public void setJmsReceiver (BusinessEventReceiverJms jmsReceiver) {
		this.jmsReceiver = jmsReceiver;
	}
}
