package com.kurui.kums.message.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kurui.kums.message.MessageResult;

public class MessageResultReceiverImpl implements MessageResultReceiver {
	
	private static Log log = LogFactory.getLog(MessageResultReceiverImpl.class);
	
	private MessageResultReceiver jmsReceiver;
	

	
	public MessageResult receiveMessage() {
		MessageResult messageResult = new MessageResult();
		try {
			log.debug("MessageReceiverImpl started");
	
			messageResult = jmsReceiver.receiveMessage();
		
			log.debug("MessageReceiverImpl end");
		} catch(Exception e) {
			log.error(e,e);
		}
		
		return messageResult;
	}



	public MessageResultReceiver getJmsReceiver() {
		return jmsReceiver;
	}



	public void setJmsReceiver(MessageResultReceiver jmsReceiver) {
		this.jmsReceiver = jmsReceiver;
	}





	
	
}
