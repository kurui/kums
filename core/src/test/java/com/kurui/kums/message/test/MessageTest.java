package com.kurui.kums.message.test;

import java.io.IOException;
import java.util.Date;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.kurui.kums.message.BusinessEventMessage;
import com.kurui.kums.message.biz.EventListener;
import com.kurui.kums.message.biz.TrendsListener;

public class MessageTest extends TestCase {

	public MessageTest(String name) {
		super(name);
	}

	private static Log log = LogFactory.getLog(MessageTest.class);

	private EventListener eventListener = null;
	private TrendsListener trendsListener = null;
	private ApplicationContext appContext;


	public void testProcessTrendsRequest() {
		try {		
			//发送消息
		BusinessEventMessage businessEventMessage=new BusinessEventMessage();
		businessEventMessage.setRequestId(111003899);
		businessEventMessage.setAuthor("kurui");
		businessEventMessage.setContent("hello world");
		businessEventMessage.setUpdateTime(new Date());
		
		eventListener = (EventListener) appContext.getBean("eventListener");
		for (int i=0; i<4; i++) {
			eventListener.sendRequest(businessEventMessage);
		}
		
		// 收听业务动态，执行持久化，返回消息
		trendsListener = (TrendsListener) appContext.getBean("trendsListener");
		trendsListener.processBusinessEventLogRequest();
		
	} catch (Exception de) {
		de.printStackTrace();
		TestCase.fail();
	}
	}

	public void setUp() throws IOException {

		// appContext = new ClassPathXmlApplicationContext(new String[] {
		// "applicationContext-message.xml"});

		appContext = new FileSystemXmlApplicationContext(
				"F:\\project\\kums\\war\\war\\WEB-INF\\applicationContext-message.xml");
		log.debug("Got Spring Application Context:" + appContext);
	}

	protected void tearDown() throws Exception {
		super.tearDown();

		log.debug("Closing Application Context");
		appContext = null;
	}

}
