package com.kurui.kums.message.trends;

import com.kurui.kums.message.MessageResult;

/**
 * JMSReceiver
 * */

public interface TrendsReceiver {
	public MessageResult receive();
}
