package com.kurui.kums.message.service;

import com.kurui.kums.message.BusinessEventMessage;


public interface BusinessEventReceiverProcess {
	public BusinessEventMessage receive();
}
