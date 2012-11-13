package com.kurui.kums.report.dao;

import java.util.List;

import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.report.AgentReport;

public interface AgentReportDAO {
	public List<AgentReport> getSexList() throws AppException ;

	public List<AgentReport> getTypeList() throws AppException;
	
	public List<AgentReport> getTypeSexList() throws AppException;	
	
	public List<AgentReport> getCompanyList() throws AppException;

}
