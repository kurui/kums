package com.kurui.kums.message.service;

import javax.jms.MapMessage;
import javax.jms.Message;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jms.core.JmsTemplate;

import com.kurui.kums.base.util.time.DateUtil;
import com.kurui.kums.message.BusinessEventMessage;

public class BusinessEventReceiverJms {
	
	private static Log log = LogFactory.getLog(BusinessEventReceiverJms.class);
	
	private JmsTemplate jmsTemplate;

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
	
	public BusinessEventMessage receiveMessage() {
		Message msg = jmsTemplate.receive();
		BusinessEventMessage businessEvent = new BusinessEventMessage();
		try {
			MapMessage mapMessage = (MapMessage) msg;
			if (msg != null) {
				log.debug("Credit Request Message Received: " + mapMessage.toString());
			}
			businessEvent.setRequestId(mapMessage.getLong("requestId"));
			businessEvent.setAuthor(mapMessage.getString("author"));
			businessEvent.setContent(mapMessage.getString("content"));
			businessEvent.setUpdateTime(DateUtil.getDate(mapMessage.getString("updateDate"),"yyyy-MM-dd HH:mm:ss"));
			
		} catch(Exception e) {
			log.error(e,e);
		}
		
		return businessEvent;
	}
}
