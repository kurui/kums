package com.kurui.kums.message.trends.jms;

import javax.jms.MapMessage;
import javax.jms.Message;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jms.core.JmsTemplate;

import com.kurui.kums.message.MessageResult;

public class TrendsReceiverJms {
	
	private static Log log = LogFactory.getLog(TrendsReceiverJms.class);
	
	private JmsTemplate jmsTemplate;

	
	public MessageResult receiveMessage() {
		Message msg = jmsTemplate.receive();
		MessageResult messageResult = new MessageResult();
		try {
			MapMessage mapMessage = (MapMessage) msg;
			if (msg != null) {
				log.debug("Credit Request Message Received: " + mapMessage.toString());
			}
			messageResult.setRequestId(mapMessage.getLong("requestId"));
			messageResult.setSuccess(mapMessage.getBoolean("success"));
			messageResult.setContent(mapMessage.getString("content"));
		
		} catch(Exception e) {
			log.error(e,e);
		}
		
		return messageResult;
	}
	
	/**
	 * @return Returns the jmsTemplate.
	 */
	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}
	/**
	 * @param jmsTemplate The jmsTemplate to set.
	 */
	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}
}
