package com.kurui.kums.message.trends.jms;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.kurui.kums.message.MessageResult;

/**
 * @author Administrator
 */
public class TrendsSenderJms {

	private static Log log = LogFactory
			.getLog(TrendsSenderJms.class);

	private JmsTemplate jmsTemplate;

	public void sendMessage(final MessageResult messageResult) {
		jmsTemplate.send(new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {

				MapMessage mapMessage = session.createMapMessage();
				mapMessage.setLong("requestId", messageResult.getRequestId());
				mapMessage.setString("content", messageResult.getContent());
				mapMessage.setBoolean("success", messageResult.isSuccess());
				log.debug("jms template send message result ..");
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
	 * @param jmsTemplate
	 *            The jmsTemplate to set.
	 */
	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}
}
