package com.kurui.kums.transaction.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.log4j.Logger;

import com.kurui.kums.base.Constant;
import com.kurui.kums.base.util.StringUtil;
import com.kurui.kums.finance.util.FinanceComparator;
import com.kurui.kums.transaction.DataType;

public class DataTypeStore {

	static Logger logger = Logger.getLogger(DataTypeStore.class.getName());

	public static List<DataType> dataTypeList = new ArrayList<DataType>();

	public static String getDataTypesNameByNos(String nos) {
		String info = "";
		if (nos != null && "".equals(nos) == false) {
			if (dataTypeList != null && dataTypeList.size() > 0) {
				String[] noArray = StringUtil.split(nos, ",");
				if (noArray != null) {
					for (int i = 0; i < noArray.length; i++) {
						String no = Constant.toString(noArray[i]);
						if ("".equals(no) == false) {

							for (int j = 0; j < dataTypeList.size(); j++) {
								DataType dataType = dataTypeList.get(j);
								String tempNo = dataType.getNo();

								if ("".equals(tempNo) == false) {
									if (no.equals(tempNo)) {
										String tempName = dataType.getName();
										info += tempName + ",";
										break;
									}
								} else {
									logger.error("tempNo is null..");
								}
							}
						} else {
							logger.error("nos is null..");
						}
					}
				} else {
					logger.error("noArray is null");
				}
			} else {
				logger.error("dataTypeList is null..");
			}

			if (info.indexOf(",", info.length() - 1) > 1) {
				info = info.substring(0, info.length() - 1);
			}
		} else {
			logger.error("DataTypeStore  nos is null ");
		}
		return info;
	}

	public static String getDataTypeGroup(String groupNo) {
		String info = "";
		if (groupNo != null && "".equals(groupNo) == false) {
			if (dataTypeList != null) {
				for (int i = 0; i < dataTypeList.size(); i++) {
					DataType dataType = dataTypeList.get(i);

					String tempSuperNo = Constant.toUpperCase(dataType
							.getSuperNo());

					if (tempSuperNo != null && "".equals(tempSuperNo) == false) {
						if (groupNo.equals(tempSuperNo)) {
							String tempNo = dataType.getNo();
							info += tempNo + ",";
						}
					}
				}
			}
			if (info.indexOf(",", info.length() - 1) > 1) {
				info = info.substring(0, info.length() - 1);
			}
		}
		return info;
	}

	public static DataType getDataTypeByNo(String no) {
		if (no != null && "".equals(no) == false) {
			if (dataTypeList != null) {
				for (int i = 0; i < dataTypeList.size(); i++) {
					DataType dataType = dataTypeList.get(i);

					String tempdataTypeNo = Constant.toUpperCase(dataType
							.getNo());
					if (tempdataTypeNo != null
							&& "".equals(tempdataTypeNo) == false) {
						if (no.equals(tempdataTypeNo)) {
							return dataType;
						}
					}
				}
			}
		}
		return null;
	}

	public static String getDataTypeNameByNo(String no) {
		DataType dataType = getDataTypeByNo(no);
		if (dataType != null) {
			return dataType.getName();
		}
		return "";
	}

	public static long getDataTypeIdByNo(String no) {
		DataType dataType = getDataTypeByNo(no);
		if (dataType != null) {
			return dataType.getId();
		}
		return Long.valueOf(0);
	}

	public static String getDataTypeNoByName(String dataTypeName) {
		if ("".equals(Constant.toString(dataTypeName)) == false) {
			if (dataTypeList != null) {
				for (int i = 0; i < dataTypeList.size(); i++) {
					DataType dataType = dataTypeList.get(i);

					String tempDataTypeName = dataType.getName();
					if ("".equals(Constant.toString(tempDataTypeName)) == false) {
						if (dataTypeName.equals(tempDataTypeName)) {
							return dataType.getNo();
						}
					}
				}
			}
		}
		return dataTypeName;
	}

	public static DataType getSuperDataTypeByNo(String no) {
		DataType superDataType = null;
		DataType dataType = getDataTypeByNo(no);
		if (dataType != null) {
			superDataType = getDataTypeByNo(dataType.getSuperNo());
			return superDataType;
		}
		return superDataType;
	}

	public static long getSuperDataTypeIdByNo(String no) {
		DataType dataType = getSuperDataTypeByNo(no);
		if (dataType != null) {
			return dataType.getId();
		} else {
			logger.error("getSuperDataTypeIdByNo dataType is null..");
		}
		return Long.valueOf(0);
	}

	public static List<DataType> getSubDataTypeList(long no) {
		List<DataType> subDataTypeList = new ArrayList<DataType>();
		if (no > 0) {
			if (dataTypeList != null) {
				for (int i = 0; i < dataTypeList.size(); i++) {
					DataType dataType = dataTypeList.get(i);
					long tempNo = Constant.toLong(dataType.getSuperNo());
					if (tempNo > 0) {
						if (no == tempNo) {
							subDataTypeList.add(dataType);
						}
					}
				}
			}
		}
		return subDataTypeList;
	}

	
	public List<DataType> sortListByNo(List<DataType> orderList) {

		FinanceComparator comp = new FinanceComparator();
		// 执行排序方法
		Collections.sort(orderList, comp);

		return orderList;
	}
	public static List<DataType> getDataTypeList() {
		return dataTypeList;
	}

	public static void setDataTypeList(List<DataType> dataTypeList) {
		DataTypeStore.dataTypeList = dataTypeList;
	}
}
