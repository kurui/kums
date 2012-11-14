package com.kurui.kums.market.biz;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.data.category.CategoryDataset;
import com.kurui.kums.base.chart.ChartUtil;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.base.Constant;
import com.kurui.kums.base.util.time.DateUtil;
import com.kurui.kums.base.util.StringUtil;
import com.kurui.kums.base.util.UnitConverter;
import com.kurui.kums.base.util.time.DateUtil.MonthUtil;
import com.kurui.kums.market.PriceIndex;
import com.kurui.kums.market.PriceIndexListForm;
import com.kurui.kums.market.PriceReference;
import com.kurui.kums.market.dao.PriceIndexDAO;
import com.kurui.kums.market.dao.PriceReferenceDAO;

public class PriceIndexBizImp implements PriceIndexBiz {
	private PriceIndexDAO priceIndexDAO;
	private PriceReferenceDAO priceReferenceDAO;

	// 每日/每月/每年/对比数据
	public ArrayList<String[]> listByPhaseTime(
			PriceIndexListForm priceIndexListForm, HttpServletRequest request)
			throws AppException {

		String priceReferenceIds = priceIndexListForm.getPriceReferenceIds();

		List<PriceReference> priceReferenceList = getPriceReferenceList(
				priceReferenceIds, priceIndexListForm);

		ArrayList<String[]> phaseData = new ArrayList<String[]>();

		for (int i = 0; i < priceReferenceList.size(); i++) {
			PriceReference priceReference = priceReferenceList.get(i);
			long priceReferenceId = priceReference.getId();

			int year = Constant.toInt(priceIndexListForm.getYear());
			int month = Constant.toInt(priceIndexListForm.getMonth());

			int[] timePhase = null;
			String phaseFlag = "month";
			if (year > 0 && month == 0) {
				int[] monthsInYear = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
				timePhase = monthsInYear;
				request.setAttribute("timePhaseUnit", "月");
				phaseFlag = "month";
			} else if (year > 0 && month > 0) {
				MonthUtil monthUtil = new MonthUtil(year, month);
				int[] daysInMonth = monthUtil.getDaysNoOfMonth();
				timePhase = daysInMonth;
				request.setAttribute("timePhaseUnit", "日");
				phaseFlag = "day";
			} else if (year == 0) {
				int[] yearsInAll = { 2009, 2010, 2011, 2012 };
				timePhase = yearsInAll;
				request.setAttribute("timePhaseUnit", "年");
				phaseFlag = "year";
			}

			request.setAttribute("timePhase", timePhase);

			String[] objArray = new String[timePhase.length + 2];
			objArray[0] = priceReferenceId + "";
			objArray[1] = priceReference.getName();

			for (int j = 0; j < timePhase.length; j++) {
				int timePhaseValue = timePhase[j];

				String[] dateArray = getStartEndDateAsPhase(year, month,
						timePhaseValue, phaseFlag);
				String startDate = dateArray[0];
				String endDate = dateArray[1];

				PriceIndex priceIndex = getPriceIndexByPhase(priceReferenceId,
						startDate, endDate);
				if (priceIndex != null) {
					objArray[j + 2] = priceIndex.getPrice() + "";
				}
			}
			phaseData.add(objArray);
		}

		return phaseData;
	}

	// 图形报表
	public List<String> listChart(PriceIndexListForm priceIndexListForm,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		List<String> charUrlList = new ArrayList<String>();

		CategoryDataset dataset = getListPriceIndexData(priceIndexListForm);
		charUrlList = createPriceIndexTimeXYChart(dataset, charUrlList,
				request, response);

		return charUrlList;
	}

	public List<String> listChartByReference(long priceReferenceId,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		List<String> charUrlList = new ArrayList<String>();

		CategoryDataset dataset = getListPriceIndexDataByReference(priceReferenceId);
		charUrlList = createPriceIndexTimeXYChart(dataset, charUrlList,
				request, response);

		return charUrlList;
	}

	public List<String> createPriceIndexTimeXYChart(CategoryDataset dataset,
			List<String> charUrlList, HttpServletRequest request,
			HttpServletResponse response) throws AppException {
		String chartName = DateUtil.getDateString(new Timestamp(System
				.currentTimeMillis()), "yyyyMMddHHmmss")
				+ ".png";
		String charUrl = ChartUtil
				.createTimeXYChart(dataset, "物价指数折线图", "时间", "价格",
						Constant.SERVLET_CHART_PATH, chartName, request,
						response);
		charUrlList.add(charUrl);

		return charUrlList;
	}

	private List<PriceReference> getPriceReferenceList(
			String priceReferenceIds, PriceIndexListForm priceIndexListForm)
			throws AppException {
		List<PriceReference> priceReferenceList = new ArrayList<PriceReference>();
		if ("".equals(priceReferenceIds) == false) {
			priceReferenceList = priceReferenceDAO
					.getPriceReferenceByIds(priceReferenceIds);
		} else {
			int[] priceIndexIds = priceIndexListForm.getSelectedItems();
			if (priceIndexIds != null && priceIndexIds.length > 0) {
				priceReferenceList = priceReferenceDAO
						.getPriceReferenceListByIndexIds(priceIndexIds);
			} else {
				long priceReferenceId = priceIndexListForm
						.getPriceReferenceId();
				if (priceReferenceId > 0) {
					priceReferenceList.add(priceReferenceDAO
							.getPriceReferenceById(priceReferenceId));
				} else {
					long priceReferenceType = priceIndexListForm
							.getPriceReferenceType();
					if (priceReferenceType > 0) {
						priceReferenceList = priceReferenceDAO
								.getPriceReferenceListByType(priceReferenceType
										+ "");
					}
				}
			}
		}
		if (priceReferenceList.size() < 1) {
			priceReferenceList = priceReferenceDAO.getValidPriceReferenceList();
		}
		return priceReferenceList;
	}

	private CategoryDataset getListPriceIndexData(
			PriceIndexListForm priceIndexListForm) throws AppException {
		List<PriceReference> priceReferenceList = new ArrayList<PriceReference>();

		String priceReferenceIds = priceIndexListForm.getPriceReferenceIds();

		priceReferenceList = getPriceReferenceList(priceReferenceIds,
				priceIndexListForm);

		String[] rowKeys = null;
		String[] columnKeys = null;
		double[][] data = null;
		int referenceSize = priceReferenceList.size();
		if (referenceSize > 0) {
			rowKeys = new String[referenceSize];
			long[] rowKeyIds = new long[referenceSize];

			for (int i = 0; i < referenceSize; i++) {
				rowKeyIds[i] = priceReferenceList.get(i).getId();
				rowKeys[i] = priceReferenceList.get(i).getName();
			}

			priceReferenceIds = StringUtil.getStringByArray(rowKeyIds, ",");

			columnKeys = getColumnDateArray(priceIndexListForm,
					priceReferenceIds);

			int columnKeyLength = columnKeys.length;
			if (columnKeyLength > 0) {
				data = new double[rowKeys.length][columnKeyLength];
				for (int i = 0; i < rowKeys.length; i++) {
					double[] objArray = new double[columnKeyLength];
					for (int j = 0; j < columnKeyLength; j++) {
						PriceIndex priceIndex = priceIndexDAO.getPriceIndex(
								rowKeyIds[i], columnKeys[j]);
						if (priceIndex != null) {
							objArray[j] = UnitConverter
									.getDoubleByBigDecimal(priceIndex
											.getPrice());
						}
					}
					data[i] = objArray;
				}
				// ArrayUtil.printData(data);
			}
		}
		CategoryDataset dataset = ChartUtil.getBarData(data, rowKeys,
				columnKeys);

		return dataset;
	}

	private CategoryDataset getListPriceIndexDataByReference(
			long priceReferenceId) throws AppException {
		PriceReference priceReference = priceReferenceDAO
				.getPriceReferenceById(priceReferenceId);
		String[] rowKeys = null;
		String[] columnKeys = null;
		double[][] data = null;
		rowKeys = new String[1];
		long[] rowKeyIds = new long[1];

		rowKeyIds[0] = priceReferenceId;
		rowKeys[0] = priceReference.getName();

		columnKeys = priceIndexDAO.getExistDateArray(priceReferenceId);

		int columnKeyLength = columnKeys.length;
		if (columnKeyLength > 0) {
			data = new double[rowKeys.length][columnKeyLength];
			for (int i = 0; i < rowKeys.length; i++) {
				double[] objArray = new double[columnKeyLength];
				for (int j = 0; j < columnKeyLength; j++) {
					PriceIndex priceIndex = priceIndexDAO.getPriceIndex(
							rowKeyIds[i], columnKeys[j]);
					if (priceIndex != null) {
						objArray[j] = UnitConverter
								.getDoubleByBigDecimal(priceIndex.getPrice());
					}
				}
				data[i] = objArray;
			}
		}

		CategoryDataset dataset = ChartUtil.getBarData(data, rowKeys,
				columnKeys);

		return dataset;
	}

	private String[] getColumnDateArray(PriceIndexListForm priceIndexListForm,
			String priceReferenceId) throws AppException {
		String[] dateArray = getStartEndDate(priceIndexListForm);
		String[] columnKeys = new String[0];

		if (dateArray != null && dateArray.length > 1) {
			String startDate = dateArray[0];
			String endDate = dateArray[1];

			columnKeys = priceIndexDAO.getExistDateArray(priceReferenceId,
					startDate, endDate);
		}

		return columnKeys;
	}

	// 查询开始结束时间
	private String[] getStartEndDate(PriceIndexListForm priceIndexListForm)
			throws AppException {
		String[] dateArray = null;

		String startDate = Constant.toString(priceIndexListForm.getStartDate());
		String endDate = priceIndexListForm.getEndDate();

		if ("".equals(startDate) == false && "".equals(endDate) == false) {
			dateArray = new String[2];
			dateArray[0] = DateUtil.getDateString(startDate,
					"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd");
			dateArray[1] = DateUtil.getDateString(endDate,
					"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd");
			return dateArray;
		}

		// String rapid = priceIndexListForm.getRapid();// 本周/上周
		// if (rapid != "") {
		// if ("thisweek".equals(rapid)) {
		//
		// }
		// if ("lastweek".equals(rapid)) {
		// dateArray = DateUtil.getLastWeekDays7();
		// }
		// if ("last30".equals(rapid)) {
		// dateArray = DateUtil.getLastDays(30);
		// }
		// }

		int year = Constant.toInt(priceIndexListForm.getYear());
		int month = Constant.toInt(priceIndexListForm.getMonth());

		if (year > 0 && month > 0) {
			MonthUtil monthUtil = new MonthUtil(year, month);
			int allDay = monthUtil.allday;
			startDate = year + "-" + month + "-1" + " 00:00:00";
			endDate = year + "-" + month + "-" + allDay + " 23:59:59";
		} else if (year == 0) {
			startDate = 2000 + "-" + 1 + "-1" + " 00:00:00";
			endDate = 2020 + "-" + 12 + "-" + 30 + " 23:59:59";
		} else if (year > 0 && month == 0) {
			startDate = year + "-" + 1 + "-1" + " 00:00:00";
			endDate = year + "-" + 12 + "-" + 30 + " 23:59:59";
		}

		if ("".equals(startDate) == false && "".equals(endDate) == false) {
			dateArray = new String[2];
			dateArray[0] = startDate;
			dateArray[1] = endDate;
		}

		return dateArray;
	}

	// 需要查询的时间段
	private String[] getStartEndDateAsPhase(int year, int month,
			int timePhaseValue, String phaseFlag) throws AppException {
		String[] dateArray = new String[2];

		String startDate = "";
		String endDate = "";

		if (year > 0) {
			if ("day".equals(phaseFlag)) {
				startDate = year + "-" + month + "-" + timePhaseValue
						+ " 00:00:00";
				endDate = year + "-" + month + "-" + timePhaseValue
						+ " 23:59:59";
			} else if ("month".equals(phaseFlag)) {
				MonthUtil monthUtil = new MonthUtil(year, timePhaseValue);
				int allDay = monthUtil.allday;
				startDate = year + "-" + timePhaseValue + "-1" + " 00:00:00";
				endDate = year + "-" + timePhaseValue + "-" + allDay
						+ " 23:59:59";
			} else if ("year".equals(phaseFlag)) {
				startDate = timePhaseValue + "-" + 1 + "-1" + " 00:00:00";
				endDate = timePhaseValue + "-" + 12 + "-" + 31 + " 23:59:59";
			}
		}

		dateArray[0] = startDate;
		dateArray[1] = endDate;

		return dateArray;
	}

	public PriceIndex getPriceIndexByPhase(long priceReferenceId,
			String startDate, String endDate) throws AppException {

		return priceIndexDAO.getPriceIndexByPhase(priceReferenceId, startDate,
				endDate);
	}

	public List list(PriceIndexListForm priceIndexListForm) throws AppException {
		String[] dateArray = getStartEndDate(priceIndexListForm);
		if (dateArray != null && dateArray.length > 1) {
			priceIndexListForm.setStartDate(dateArray[0]);
			priceIndexListForm.setEndDate(dateArray[1]);
		}

		return priceIndexDAO.list(priceIndexListForm);
	}

	public void delete(long id) throws AppException {
		try {
			priceIndexDAO.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deletePriceIndex(Long priceIndexId) throws AppException {
		PriceIndex priceIndex = getPriceIndexById(priceIndexId);
		priceIndex.setStatus(PriceIndex.STATES_0);// 将状态变为无效
		update(priceIndex);
	}

	public long save(PriceIndex priceIndex) throws AppException {
		return priceIndexDAO.save(priceIndex);
	}

	public long update(PriceIndex priceIndex) throws AppException {
		return priceIndexDAO.update(priceIndex);
	}

	public PriceIndex getPriceIndexById(long priceIndexId) throws AppException {
		return priceIndexDAO.getPriceIndexById(priceIndexId);
	}

	public List<PriceIndex> getPriceIndexByReferenceId(long referenceId)
			throws AppException {
		return priceIndexDAO.getPriceIndexByReferenceId(referenceId);
	}

	public List<PriceIndex> getPriceIndexByIds(String indexIds)
			throws AppException {
		return priceIndexDAO.getPriceIndexByIds(indexIds);
	}

	public List<PriceIndex> getPriceIndexList() throws AppException {
		return priceIndexDAO.getPriceIndexList();
	}

	public List<PriceIndex> getValidPriceIndexList() throws AppException {
		return priceIndexDAO.getValidPriceIndexList();
	}

	public void setPriceIndexDAO(PriceIndexDAO priceIndexDAO) {
		this.priceIndexDAO = priceIndexDAO;
	}

	public void setPriceReferenceDAO(PriceReferenceDAO priceReferenceDAO) {
		this.priceReferenceDAO = priceReferenceDAO;
	}
}
