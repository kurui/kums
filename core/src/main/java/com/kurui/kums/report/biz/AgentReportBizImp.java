package com.kurui.kums.report.biz;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.kurui.kums.agent.AgentListForm;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.report.AgentReport;
import com.kurui.kums.report.dao.AgentReportDAO;

public class AgentReportBizImp implements AgentReportBiz{
	private AgentReportDAO agentReportDAO;

	public HttpServletRequest viewReport(AgentListForm agentListForm, HttpServletRequest request)
			throws AppException {
		List<AgentReport> sexList=agentReportDAO.getSexList();
		
		List<AgentReport> typeList=agentReportDAO.getTypeList();
		
		List<AgentReport> typeSexList=agentReportDAO.getTypeSexList();
		
		List<AgentReport> companyList=agentReportDAO.getCompanyList();
	

		request.setAttribute("sexList", sexList);
		request.setAttribute("typeList", typeList);
		request.setAttribute("typeSexList", typeSexList);
		request.setAttribute("companyList",companyList);
		
		
		return request;
	}

	public void setAgentReportDAO(AgentReportDAO agentReportDAO) {
		this.agentReportDAO = agentReportDAO;
	}
	
	

}
