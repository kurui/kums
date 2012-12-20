package com.kurui.kums.message.trends;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kurui.kums.message.MessageResult;
import com.kurui.kums.message.trends.jms.TrendsReceiverJms;

public class TrendsReceiverImpl implements TrendsReceiver {
	
	private static Log log = LogFactory.getLog(TrendsReceiverImpl.class);
	
	private TrendsReceiverJms jmsReceiver;
	
	public MessageResult receive() {
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

	public TrendsReceiverJms getJmsReceiver() {
		return jmsReceiver;
	}

	public void setJmsReceiver(TrendsReceiverJms jmsReceiver) {
		this.jmsReceiver = jmsReceiver;
	}
	
}
