package com.kurui.kums.transaction.util;

import java.util.Comparator;
import com.kurui.kums.base.Constant;
import com.kurui.kums.transaction.DataType;

public class DataTypeComparator implements Comparator<Object> {

	public DataTypeComparator() {

	}

	public DataTypeComparator(String aa) {

	}

	public int compare(Object o1, Object o2) {
		DataType order1 = (DataType) o1;
		DataType order2 = (DataType) o2;

		Long id1 = Constant.toLong(order1.getNo());
		Long id2 = Constant.toLong(order2.getId());

		// System.out.println("time1:"+time1);
		// System.out.println("time2:"+time2);
		int flag = id1.compareTo(id2);

		// System.out.println("flag:"+flag);

		if (flag > 0) {
			return 1;// 第一个大于第二个
		}
		if (flag < 0) {
			return -1;// 第一个小于第二个
		} else {
			return 0;// 等于
		}
	}

}
