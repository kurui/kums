package com.kurui.kums.message.event.jms;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.kurui.kums.message.BusinessEventMessage;

/**
 * @author yanrui
 */
public class EventSenderJms {

	private static Log log = LogFactory.getLog(EventSenderJms.class);

	private JmsTemplate jmsTemplate;



	public void sendMessage(final BusinessEventMessage businessEvent) {
		jmsTemplate.send(new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {

				log.debug("Creating the message from loan parameters.");

				MapMessage mapMessage = session.createMapMessage();
				mapMessage.setLong("requestId",businessEvent.getRequestId());
				mapMessage.setString("author",businessEvent.getAuthor());
				mapMessage.setString("content",businessEvent.getContent());
				mapMessage.setString("updateDate",businessEvent.getUpdateDate());

				return mapMessage;
			}
		});
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
