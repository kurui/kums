package com.kurui.kums.message.event;

import com.kurui.kums.message.BusinessEventMessage;

public interface EventReceiver {
	public BusinessEventMessage receive();
}
