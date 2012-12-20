package com.kurui.kums.message.service;

import com.kurui.kums.message.MessageResult;

/**
 * JMSReceiver
 * */

public interface MessageResultReceiver {
	public MessageResult receiveMessage();
}
