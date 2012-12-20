package com.kurui.kums.message.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kurui.kums.message.MessageResult;

public class MessageResultSenderImpl implements MessageResultSender {
	
	private static Log log = LogFactory.getLog(MessageResultSenderImpl.class);
	
	private MessageResultSenderJms jmsSender = null;	

	
	public void send(MessageResult result) {
		try {
			jmsSender.sendMessage(result);
			
			log.debug("Message sent.");
		} catch(Exception e) {
			log.error(e,e);
		}
	}



	public MessageResultSenderJms getJmsSender() {
		return jmsSender;
	}



	public void setJmsSender(MessageResultSenderJms jmsSender) {
		this.jmsSender = jmsSender;
	}

	
	
}
