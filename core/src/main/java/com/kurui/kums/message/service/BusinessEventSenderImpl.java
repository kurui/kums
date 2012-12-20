package com.kurui.kums.message.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kurui.kums.message.BusinessEventMessage;

public class BusinessEventSenderImpl implements BusinessEventSender {
	
	private static Log log = LogFactory.getLog(BusinessEventSenderImpl.class);
	
	private BusinessEventSenderJms jmsSender = null;
	
	
	
	public void send(BusinessEventMessage loanApp) {
		try {
			jmsSender.sendMessage(loanApp);
			
			log.debug("Message sent.");
		} catch(Exception e) {
			log.error(e,e);
		}
	}

}
