package com.kurui.kums.message.test;

import java.io.IOException;
import java.util.Date;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.kurui.kums.message.BusinessEventMessage;
import com.kurui.kums.message.biz.EventListener;

public class EventListenerTest extends TestCase {

		public EventListenerTest(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

		private static Log log = LogFactory.getLog(EventListenerTest.class);

		private EventListener service = null;

		private ApplicationContext appContext;

		public void setUp() throws IOException {

//			appContext = new ClassPathXmlApplicationContext(new String[] {
//					"applicationContext-message.xml"});

			appContext=new FileSystemXmlApplicationContext("F:\\project\\kums\\war\\war\\WEB-INF\\applicationContext-message.xml");
			log.debug("Got Spring Application Context:" + appContext);
		}

		protected void tearDown() throws Exception {
			super.tearDown();
			
			log.debug("Closing Application Context");
			appContext = null;
		}

		public void testProcessEventRequest() {
			try {
				service = (EventListener) appContext.getBean("eventListener");
				// Receive the 4 messages sent to the message queue by 
				// LoanApplicationServiceTest test class
				BusinessEventMessage businessEventMessage=new BusinessEventMessage();
				businessEventMessage.setRequestId(111003899);
				businessEventMessage.setAuthor("kurui");
				businessEventMessage.setContent("hello world");
				businessEventMessage.setUpdateTime(new Date());
				
				for (int i=0; i<4; i++) {
					service.sendRequest(businessEventMessage);
				}
			} catch(Exception de) {
				TestCase.fail();
			}
		}

	}
