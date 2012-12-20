package com.kurui.kums.message.service.jboss;

import java.util.Hashtable;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MessageSenderJMS {

	private static Log log = LogFactory.getLog(MessageSenderJMS.class);

	String queueName = null;
	Context jndiContext = null;
	QueueConnectionFactory queueConnectionFactory = null;
	QueueConnection queueConnection = null;
	QueueSession queueSession = null;
	Queue queue = null;
	QueueSender queueSender = null;
	TextMessage message = null;

	public void sendMessage() {
		queueName = "queue/CreditRequestSendQueue";
		log.debug("Queue name is " + queueName);

		/*
		 * Create a JNDI API InitialContext object if none exists yet.
		 */
		try {
			Hashtable env = new Hashtable();
			// JBoss
			env.put("java.naming.factory.initial",
					"org.jnp.interfaces.NamingContextFactory");
			env.put("java.naming.provider.url", "localhost");
			env.put("java.naming.factory.url.pkgs",
					"org.jnp.interfaces:org.jboss.naming");

			jndiContext = new InitialContext(env);
		} catch (NamingException e) {
			log.error(e, e);
		}

		/*
		 * Get queue connection factory and queue objects
		 */
		try {
			queueConnectionFactory = (QueueConnectionFactory) jndiContext
					.lookup("UIL2ConnectionFactory");

			queue = (Queue) jndiContext.lookup(queueName);
		} catch (NamingException e) {
			log.debug("JNDI API lookup failed: " + e.toString());
		}

		/*
		 * Create queue connection and session objects
		 */
		try {
			queueConnection = queueConnectionFactory.createQueueConnection();
			queueSession = queueConnection.createQueueSession(false,
					Session.AUTO_ACKNOWLEDGE);
			queueSender = queueSession.createSender(queue);
			message = queueSession.createTextMessage();
			message.setText("This is a sample JMS message.");
			log.debug("Sending message: " + message.getText());
			queueSender.send(message);
		} catch (JMSException e) {
			log.debug("Exception occurred: " + e.toString());
		} finally {
			if (queueConnection != null) {
				try {
					queueConnection.close();
				} catch (JMSException e) {
				}
			}
		}
	}
}
