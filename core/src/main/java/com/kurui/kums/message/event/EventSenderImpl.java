package com.kurui.kums.message.event;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kurui.kums.message.BusinessEventMessage;
import com.kurui.kums.message.event.jms.EventSenderJms;

public class EventSenderImpl implements EventSender {
	
	private static Log log = LogFactory.getLog(EventSenderImpl.class);
	
	private EventSenderJms jmsSender = null;
	
	
	
	public void send(BusinessEventMessage loanApp) {
		try {
			jmsSender.sendMessage(loanApp);
			
			log.debug("Message sent.");
		} catch(Exception e) {
			log.error(e,e);
		}
	}

}
