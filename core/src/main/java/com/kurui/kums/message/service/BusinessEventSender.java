package com.kurui.kums.message.service;

import com.kurui.kums.message.BusinessEventMessage;

public interface BusinessEventSender {
	public void send(BusinessEventMessage businessEventMessage);
}
