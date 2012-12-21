package com.kurui.kums.message.test;

import java.util.Hashtable;

import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ProcessMessageTestClient {

	private static Log log = LogFactory.getLog(ProcessMessageTestClient.class);

	public static void main(String[] args) throws Exception {

		log.debug("Get JNDI context");
		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
		env.put(Context.URL_PKG_PREFIXES, "org.jnp.interfaces");
		env.put(Context.PROVIDER_URL, "localhost");
		InitialContext ctx = new InitialContext(env);

		log.debug("Get Queue connection factory and Queue objects from JNDI context.");
		QueueConnectionFactory qcf = (QueueConnectionFactory) ctx.lookup("XAConnectionFactory");//UIL2
		Queue queue = (Queue) ctx.lookup("queue/EventRequestSendQueue");

		log.info("Create queue connection");
		QueueConnection qc = qcf.createQueueConnection();
		try {
			log.debug("Creating session");
			QueueSession qs = qc.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

			log.debug("Creating sender");
			QueueSender sender = qs.createSender(queue);

			log.debug("Creating message");
			TextMessage message = qs.createTextMessage("hello");

			log.debug("Sending message");
			sender.send(message);

			log.debug("Creating receiver");
			QueueReceiver receiver = qs.createReceiver(queue);

			log.debug("Try to receive message, it will not work");
			Message received = null;

			log.debug("You have to start the connection before receiving messages");
			qc.start();

			received = receiver.receiveNoWait();

			log.debug("Got message: " + received);
	      } finally {
	         qc.close();
	      }
	   }
	}
