package com.kurui.kums.finance.util;

import java.util.Comparator;
import com.kurui.kums.finance.FinanceOrder;


public class FinanceComparator implements Comparator<Object>{
	
	public FinanceComparator() {
		
	}
	
	public FinanceComparator(String aa){
		
	}
	
	public int compare(Object o1, Object o2) {
		FinanceOrder order1=(FinanceOrder)o1;
		FinanceOrder order2=(FinanceOrder)o2;
		
		Long id1=order1.getId();
		Long id2=order2.getId();
		
//		System.out.println("time1:"+time1);
//		System.out.println("time2:"+time2);
		int flag=id1.compareTo(id2);
		
	
//		System.out.println("flag:"+flag);
		
		
		if (flag>0) {
			return 1;//第一个大于第二个
		}if (flag<0) {
			return -1;//第一个小于第二个
		}else{
			return 0;//等于
		}
	}
	
}
		