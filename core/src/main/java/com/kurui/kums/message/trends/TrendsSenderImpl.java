package com.kurui.kums.message.trends;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kurui.kums.message.MessageResult;
import com.kurui.kums.message.trends.jms.TrendsSenderJms;

public class TrendsSenderImpl implements TrendsSender {
	
	private static Log log = LogFactory.getLog(TrendsSenderImpl.class);
	
	private TrendsSenderJms jmsSender = null;	

	
	public void send(MessageResult result) {
		try {
			jmsSender.sendMessage(result);
			
			log.debug("Message sent.");
		} catch(Exception e) {
			log.error(e,e);
		}
	}



	public TrendsSenderJms getJmsSender() {
		return jmsSender;
	}



	public void setJmsSender(TrendsSenderJms jmsSender) {
		this.jmsSender = jmsSender;
	}

	
	
}
