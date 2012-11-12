package com.kurui.kums.report.biz;

import javax.servlet.http.HttpServletRequest;

import com.kurui.kums.agent.AgentListForm;
import com.kurui.kums.base.exception.AppException;

public interface AgentReportBiz {

	public HttpServletRequest viewReport(AgentListForm agentListForm, HttpServletRequest request)
			throws AppException;


}
