package com.kurui.kums.report.biz;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import com.kurui.kums.report.biz.CreditReportBiz;
import com.kurui.kums.report.CreditReport;
import com.kurui.kums.report.CreditReportListForm;
import com.kurui.kums.report.dao.CreditReportDAO;
import com.kurui.kums.base.exception.AppException;

public class CreditReportBizImp implements CreditReportBiz {

	private CreditReportDAO creditReportDAO;

	static Logger logger = Logger.getLogger(CreditReportBizImp.class.getName());

	public void createCreditReport(CreditReportListForm creditReportListForm,
			HttpServletRequest request) {
		creditReportDAO.createCreditReport(creditReportListForm, request);
	}

	public void createDeditReport(CreditReportListForm creditReportListForm,
			HttpServletRequest request) {
		creditReportDAO.createDeditReport(creditReportListForm, request);
	}

	public List list(CreditReportListForm creditReportListForm,HttpServletRequest request)
			throws AppException {
		return creditReportDAO.list(creditReportListForm, request);
	}

	public long delete(long id) throws AppException {
		try {
			creditReportDAO.delete(id);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public void deleteCreditReport(Long id) throws AppException {
		CreditReport creditReport = getCreditReportById(id);
		// creditReport.setStatus(CreditReport.STATES_0);// 将状态变为无效
		update(creditReport);
	}

	public long save(CreditReport creditReport) throws AppException {
		return creditReportDAO.save(creditReport);
	}

	public long update(CreditReport creditReport) throws AppException {
		return creditReportDAO.update(creditReport);
	}

	public CreditReport getCreditReportById(long id) throws AppException {
		return creditReportDAO.getCreditReportById(id);
	}

	public List<CreditReport> getCreditReportList() throws AppException {
		return creditReportDAO.getCreditReportList();
	}

	public List<CreditReport> getCreditReportList(Long type)
			throws AppException {
		return creditReportDAO.getCreditReportList(type);
	}

	public void setCreditReportDAO(CreditReportDAO creditReportDAO) {
		this.creditReportDAO = creditReportDAO;
	}
}
