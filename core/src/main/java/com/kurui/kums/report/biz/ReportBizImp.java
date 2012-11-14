package com.kurui.kums.report.biz;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jfree.data.category.DefaultCategoryDataset;
import com.kurui.kums.base.chart.ChartUtil;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.base.file.FileExcelUtil;
import com.kurui.kums.base.Constant;
import com.kurui.kums.base.util.time.DateUtil;
import com.kurui.kums.base.util.PrintDataUtil;
import com.kurui.kums.base.util.StringUtil;
import com.kurui.kums.base.util.UnitConverter;
import com.kurui.kums.finance.FinanceOrder;
import com.kurui.kums.finance.FinanceOrderListForm;
import com.kurui.kums.finance.dao.FinanceOrderDAO;
import com.kurui.kums.report.dao.ReportDAO;
import com.kurui.kums.right.dao.UserDAO;
import com.kurui.kums.transaction.DataType;
import com.kurui.kums.transaction.dao.DataTypeDAO;
import com.kurui.kums.transaction.util.DataTypeStore;

public class ReportBizImp implements ReportBiz {
	private ReportDAO reportDAO;
	private UserDAO userDAO;
	private DataTypeDAO dataTypeDAO;
	private FinanceOrderDAO financeOrderDAO;

	public void exportLiveReport(List<FinanceOrder> orderList)
			throws AppException {
		ArrayList<ArrayList<Object>> lists = new ArrayList<ArrayList<Object>>();
		ArrayList<Object> title = new ArrayList<Object>();
		title.add("生效日期");
		title.add("类型");
		title.add("金额");
		title.add("说明");
		title.add("状态");
		title.add("负责人");
		title.add("流水号");
		
		for (int i = 0; i < orderList.size(); i++) {
			FinanceOrder order=orderList.get(i);
			if(order!=null){
				ArrayList<Object> rowContent = new ArrayList<Object>();
				String entryTime=order.getBusinessDate();
				rowContent.add(entryTime);
				rowContent.add(order.getTranTypeText());
				rowContent.add(order.getTotalAmount().toString());
				rowContent.add(order.getMemo());
				rowContent.add(order.getStatusText());
				rowContent.add(order.getEntryOperator());
				rowContent.add(order.getOrderNo());
				
				lists.add(rowContent);
			}			
		}
		

		String fileName = "管理费用" + DateUtil.getDateString("yyyyMMddhhmmss")
				+ ".xls";
		String filePath = "e:\\" + fileName;
		FileExcelUtil.createXLSFile(title, lists, "管理费用", filePath);

	}

	public HttpServletRequest setLiveChartList(List<FinanceOrder> orderList,
			FinanceOrderListForm ulf, HttpServletRequest request,
			HttpServletResponse response) throws AppException {
		List<String> charUrlList = new ArrayList<String>();

		charUrlList = createLivePieChart(charUrlList, orderList, ulf, request,
				response);
		charUrlList = createLiveSpitorChart(charUrlList, orderList, ulf,
				request, response);

		request.setAttribute("chartUrlList", charUrlList);

		return request;
	}

	/**
	 * 每日成本雷达图
	 */
	private List<String> createLiveSpitorChart(List<String> charUrlList,
			List<FinanceOrder> orderList, FinanceOrderListForm ulf,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String[] objKeys = ulf.getDaysArray();
		String[] varKeys = getLiveSpitorKeys(dataTypeDAO
				.getSubDataTypeList("1"));
		PrintDataUtil.printArrayln(varKeys);

		if (objKeys != null) {
			PrintDataUtil.printArrayln(objKeys);
			if (varKeys != null) {
				PrintDataUtil.printArrayln(varKeys);

				DefaultCategoryDataset dataset = getSpitorDataset(objKeys,
						varKeys, orderList);

				String graphURL = ChartUtil.createSpitorChart(dataset,
						"每日成本雷达图", Constant.SERVLET_CHART_PATH, request,
						response);

				charUrlList.add(graphURL);
			}
		}
		return charUrlList;
	}

	private String[] getLiveSpitorKeys(List<DataType> dataTypeList) {
		String keys[] = null;
		String temp = "";
		for (int i = 0; i < dataTypeList.size(); i++) {
			DataType dataType = dataTypeList.get(i);
			String tranTypeNo = dataType.getNo();
			if (tranTypeNo != null && tranTypeNo != "") {
				if (!StringUtil.containsExistString(tranTypeNo, temp)) {
					temp = StringUtil.appendString(temp, tranTypeNo, ",");
				}
			}
		}
		keys = StringUtil.getSplitString(temp, ",");
		return keys;
	}

	// 时间段管理费用雷达图
	public DefaultCategoryDataset getSpitorDataset(String[] objKeys,
			String[] varKeys, List<FinanceOrder> orderList) throws AppException {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		int objCount = objKeys.length;
		for (int i = 0; i < objCount; i++) {
			String objKey = objKeys[i];
			String objName = objKey;

			for (int j = 0; j < varKeys.length; j++) {
				String varKey = Constant.toString(varKeys[j]);
				String varName = DataTypeStore.getDataTypeNameByNo(varKey);
				List<Double> dataList = getSpitorData(objKey, varKey, orderList);
				for (int k = 0; k < dataList.size(); k++) {
					double data = dataList.get(k);
					dataset.addValue(data, objName, varName);
				}
			}
		}
		return dataset;
	}

	private List<Double> getSpitorData(String objKey, String varKey,
			List<FinanceOrder> orderList) throws AppException {
		List<Double> dataList = new ArrayList<Double>();

		for (int i = 0; i < orderList.size(); i++) {
			FinanceOrder order = orderList.get(i);
			String tranType = Constant.toString(order.getTranType() + "");
			String formatDate = order.getBusinessDate();

			if (objKey.equals(formatDate) && varKey.equals(tranType)) {
				BigDecimal totalAmount = order.getTotalAmount();
				double data = UnitConverter.getDoubleByBigDecimal(totalAmount);
				dataList.add(data);
			}
		}

		return dataList;
	}

	/**
	 * 成本比例饼图
	 */
	private List<String> createLivePieChart(List<String> charUrlList,
			List<FinanceOrder> orderList, FinanceOrderListForm ulf,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String[] keys = getLivePieKeys(orderList);
		double[] data = getLivePieData(keys, orderList);

		if (keys != null) {
			// PrintDataUtil.printArrayln(keys);
			if (data != null) {
				// PrintDataUtil.printArrayln(data);
				String chartName = DateUtil.getDateString(new Timestamp(System
						.currentTimeMillis()), "yyyyMMddHHmmss")
						+ ".png";

				String graphURL = ChartUtil.createNormalPieChart(data, keys,
						"成本比例图", Constant.SERVLET_CHART_PATH, chartName, keys,
						request, response);

				charUrlList.add(graphURL);
			}
		}
		return charUrlList;
	}

	private double[] getLivePieData(String[] keys, List<FinanceOrder> orderList) {
		double data[] = null;
		if (keys != null) {
			int keysLength = keys.length;
			data = new double[keysLength];

			for (int j = 0; j < keysLength; j++) {
				String key = keys[j];
				if (key != null && !"".equals(key)) {
					double tempData = getLiveDataByKey(key, orderList);
					data[j] = tempData;
				}
			}
		}
		return data;
	}

	private double getLiveDataByKey(String key, List<FinanceOrder> orderList) {
		double data = 0;
		for (int i = 0; i < orderList.size(); i++) {
			FinanceOrder order = orderList.get(i);
			String tranType = order.getTranTypeText();
			if (key.equals(tranType)) {
				double totalAmount = UnitConverter.getDoubleByBigDecimal(order
						.getTotalAmount());
				data += totalAmount;
			}
		}
		return data;
	}

	private String[] getLivePieKeys(List<FinanceOrder> orderList) {
		String keys[] = null;
		String temp = "";
		for (int i = 0; i < orderList.size(); i++) {
			FinanceOrder order = orderList.get(i);
			String tranType = order.getTranTypeText();
			if (tranType != null && tranType != "") {
				if (!StringUtil.containsExistString(tranType, temp)) {
					temp = StringUtil.appendString(temp, tranType, ",");
				}
			}
		}
		keys = StringUtil.getSplitString(temp, ",");
		return keys;
	}

	public void setReportDAO(ReportDAO reportDAO) {
		this.reportDAO = reportDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public void setDataTypeDAO(DataTypeDAO dataTypeDAO) {
		this.dataTypeDAO = dataTypeDAO;
	}

	public void setFinanceOrderDAO(FinanceOrderDAO financeOrderDAO) {
		this.financeOrderDAO = financeOrderDAO;
	}

}
