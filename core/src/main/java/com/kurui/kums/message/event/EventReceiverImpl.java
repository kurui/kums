package com.kurui.kums.message.event;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kurui.kums.message.BusinessEventMessage;
import com.kurui.kums.message.event.jms.EventReceiverJms;

public class EventReceiverImpl implements EventReceiver {
	
	private static Log log = LogFactory.getLog(EventReceiverImpl.class);
	
	private EventReceiverJms jmsReceiver;
	
	
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
	
	public EventReceiverJms getJmsReceiver() {
		return this.jmsReceiver;
	}
	
	public void setJmsReceiver (EventReceiverJms jmsReceiver) {
		this.jmsReceiver = jmsReceiver;
	}
}
