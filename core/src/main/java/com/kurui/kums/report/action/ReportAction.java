package com.kurui.kums.report.action;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.kurui.kums.base.BaseAction;
import com.kurui.kums.base.file.report.DownLoadFile;
import com.kurui.kums.base.util.DateUtil;
import com.kurui.kums.base.file.FileUtil;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.report.biz.ReportBiz;
import com.kurui.kums.report.Report;
import com.kurui.kums.transaction.biz.PaymentToolBiz;

public class ReportAction extends BaseAction {
	private ReportBiz reportBiz;
	private PaymentToolBiz paymentToolBiz;
	
	/**
	 * 下载管理费用报表
	 */
	public ActionForward downloadLiveReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws AppException {
		Report report = (Report) form;
		if (report != null) {
			ArrayList<ArrayList<Object>> lists = null;
			Long reportType = report.getReportType();
			if (reportType != null) {
				List data = null;
				// if (reportType.compareTo(Report.ReportType1) == 0) {
				// data=reportBiz.getSaleReportContent(report);
				// lists = reportBiz.downloadSaleReport(report,data);// 财务版
				// } else if (reportType.compareTo(Report.ReportType2) == 0) {
				// data=reportBiz.getSaleReportContent(report);
				// lists = reportBiz.downloadPolicySaleReport(report,data);//
				// 政策版
				// }else if (reportType.compareTo(Long.valueOf(3)) == 0) {
				// data=reportBiz.getSaleOrderReportContent(report);//测试
				// lists = reportBiz.downloadPolicySaleReport(report,data);//
				// 政策版<测试>
				// }
			}

			String outFileName = "销售"
					+ DateUtil.getDateString("yyyyMMddhhmmss") + ".csv";
			String outText = FileUtil.createCSVFile(lists);

			try {
				// outText = new String(outText.getBytes("UTF-8"));
				outText = new String(outText.getBytes(System
						.getProperty("file.encoding")));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			DownLoadFile df = new DownLoadFile();
			df.performTask(response, outText, outFileName, "GBK");
			return null;
		} else {
			request.getSession().invalidate();
			return mapping.findForward("exit");
		}
	}

	/**
	 * 下载销售报表
	 */
	public ActionForward downloadSaleReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws AppException {
		Report report = (Report) form;
		if (report != null) {
			ArrayList<ArrayList<Object>> lists = null;
			Long reportType = report.getReportType();
			if (reportType != null) {
				List data = null;
				// if (reportType.compareTo(Report.ReportType1) == 0) {
				// data=reportBiz.getSaleReportContent(report);
				// lists = reportBiz.downloadSaleReport(report,data);// 财务版
				// } else if (reportType.compareTo(Report.ReportType2) == 0) {
				// data=reportBiz.getSaleReportContent(report);
				// lists = reportBiz.downloadPolicySaleReport(report,data);//
				// 政策版
				// }else if (reportType.compareTo(Long.valueOf(3)) == 0) {
				// data=reportBiz.getSaleOrderReportContent(report);//测试
				// lists = reportBiz.downloadPolicySaleReport(report,data);//
				// 政策版<测试>
				// }
			}

			String outFileName = "销售"
					+ DateUtil.getDateString("yyyyMMddhhmmss") + ".csv";
			String outText = FileUtil.createCSVFile(lists);

			try {
				// outText = new String(outText.getBytes("UTF-8"));
				outText = new String(outText.getBytes(System
						.getProperty("file.encoding")));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			DownLoadFile df = new DownLoadFile();
			df.performTask(response, outText, outFileName, "GBK");
			return null;
		} else {
			request.getSession().invalidate();
			return mapping.findForward("exit");
		}
	}

	public void setReportBiz(ReportBiz reportBiz) {
		this.reportBiz = reportBiz;
	}

	public void setPaymentToolBiz(PaymentToolBiz paymentToolBiz) {
		this.paymentToolBiz = paymentToolBiz;
	}

}
