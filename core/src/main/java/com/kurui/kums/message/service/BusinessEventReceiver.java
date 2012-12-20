package com.kurui.kums.message.service;

import com.kurui.kums.message.BusinessEventMessage;

public interface BusinessEventReceiver {
	public BusinessEventMessage receive();
}
