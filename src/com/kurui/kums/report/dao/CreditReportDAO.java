package com.kurui.kums.report.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.kurui.kums.report.CreditReport;
import com.kurui.kums.report.CreditReportListForm;
import com.kurui.kums.base.exception.AppException;

public interface CreditReportDAO {

	public void createCreditReport(CreditReportListForm creditReportListForm,
			HttpServletRequest request);

	public void createDeditReport(CreditReportListForm creditReportListForm,
			HttpServletRequest request);

	public List list(CreditReportListForm agentListForm,HttpServletRequest request) throws AppException;

	public void delete(long id) throws AppException;

	public long save(CreditReport agentRelation) throws AppException;

	public long update(CreditReport agentRelation) throws AppException;

	public CreditReport getCreditReportById(long id) throws AppException;

	public List<CreditReport> getCreditReportList() throws AppException;

	public List<CreditReport> getCreditReportList(Long type)
			throws AppException;

	public List<CreditReport> getValidCreditReportList() throws AppException;
}
