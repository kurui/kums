package com.kurui.kums.message.event;

import com.kurui.kums.message.BusinessEventMessage;

public interface EventSender {
	public void send(BusinessEventMessage businessEventMessage);
}
