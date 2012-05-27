package com.kurui.kums.report.biz;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.kurui.kums.report.CreditReport;
import com.kurui.kums.report.CreditReportListForm;
import com.kurui.kums.base.exception.AppException;

public interface CreditReportBiz {

	public void createCreditReport(CreditReportListForm creditReportListForm,
			HttpServletRequest request);

	public void createDeditReport(CreditReportListForm creditReportListForm,
			HttpServletRequest request);

	public List list(CreditReportListForm creditReportListForm,HttpServletRequest request)
			throws AppException;

	public long delete(long id) throws AppException;

	public void deleteCreditReport(Long id) throws AppException;

	public long save(CreditReport creditReport) throws AppException;

	public long update(CreditReport creditReport) throws AppException;

	public CreditReport getCreditReportById(long id) throws AppException;

	public List<CreditReport> getCreditReportList() throws AppException;

	public List<CreditReport> getCreditReportList(Long type)
			throws AppException;

}
