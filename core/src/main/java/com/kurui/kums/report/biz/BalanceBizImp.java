package com.kurui.kums.report.biz;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import com.kurui.kums.report.biz.BalanceBiz;
import com.kurui.kums.report.Balance;
import com.kurui.kums.report.BalanceListForm;
import com.kurui.kums.report.dao.BalanceDAO;
import com.kurui.kums.transaction.DataType;
import com.kurui.kums.transaction.util.DataTypeStore;
import com.kurui.kums.base.Constant;
import com.kurui.kums.base.threads.listener.PerformListener;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.finance.FinanceOrder;

public class BalanceBizImp implements BalanceBiz {

	private BalanceDAO balanceDAO;

	static Logger logger = Logger.getLogger(BalanceBizImp.class.getName());

	public void createBalance(BalanceListForm balanceListForm,
			HttpServletRequest request) {
		balanceDAO.createBalance(balanceListForm, request);
	}

	public List list(BalanceListForm balanceListForm, HttpServletRequest request)
			throws AppException {
		return balanceDAO.list(balanceListForm, request);
	}

	// 利润表
	public List<Balance> listEarnings(BalanceListForm balanceListForm,
			HttpServletRequest request) throws AppException {
		long a = System.currentTimeMillis();

		List<Balance> earningsList = new ArrayList<Balance>();

		Balance itemLive = new Balance(1201 + "", "管理费用", "0",
				FinanceOrder.BUSINESSTYPE_2);
		earningsList = addTotalItem(earningsList, itemLive);

		Balance itemMain = new Balance(15 + "", "主营业务", "0",
				FinanceOrder.BUSINESSTYPE_1);
		earningsList = addTotalItem(earningsList, itemMain);

		new PerformListener("listEarnings 利润表", a);
		
		return earningsList;
	}

	// 平衡表
	public void listEquity(BalanceListForm balanceListForm,
			HttpServletRequest request) throws AppException {
		List<Balance> leftList = new ArrayList<Balance>();
		List<Balance> rightList = new ArrayList<Balance>();

		leftList.add(new Balance("1301", "负债1", "223.31"));
		leftList.add(new Balance("1302", "所有者权益2", "223.32"));
		leftList.add(new Balance("1303", "管理费用3", "223.33"));

		Balance itemM = new Balance(1201 + "", "管理费用");
		rightList = addTotalItem(rightList, itemM);

		long a = System.currentTimeMillis();
		rightList = addItemList(rightList, itemM);
		new PerformListener("listBalance 管理费用", a);

		request.setAttribute("leftList", leftList);
		request.setAttribute("rightList", rightList);
	}

	// 填充父节点(统计)
	private List<Balance> addTotalItem(List<Balance> balanceList, Balance item)
			throws AppException {
		String superItemKey = item.getItemKey();
		if (!"".equals(superItemKey)) {
			String itemAmount = balanceDAO
					.getItemAmountBySuperItem(superItemKey);
			item.setItemAmount(itemAmount);
			balanceList.add(item);
		}
		return balanceList;
	}

	// 根据父节点增加所有二级子节点
	private List<Balance> addItemList(List<Balance> balanceList, Balance item)
			throws AppException {
		String superItemKey = item.getItemKey();
		if (!"".equals(superItemKey)) {
			List<DataType> dataTypeList = DataTypeStore
					.getSubDataTypeList(Constant.toLong(superItemKey));
			for (int i = 0; i < dataTypeList.size(); i++) {
				DataType dataType = dataTypeList.get(i);
				Balance balance = getBalanceByDataType(dataType);
				balanceList.add(balance);
			}
		}
		return balanceList;
	}

	// 填充节点
	private Balance getBalanceByDataType(DataType dataType) throws AppException {
		String itemKey = dataType.getNo();
		String itemName = dataType.getName();
		String itemAmount = balanceDAO.getItemAmount(itemKey);
		return new Balance(itemKey, itemName, itemAmount, 0);
	}

	public void _listBalance(BalanceListForm balanceListForm,
			HttpServletRequest request) {
		List<Balance> leftList = new ArrayList<Balance>();
		leftList.add(new Balance("1201", "资产1", "222.31"));
		leftList.add(new Balance("1202", "资产2", "222.32"));
		leftList.add(new Balance("1203", "资产3", "222.33"));

		List<Balance> rightList = new ArrayList<Balance>();
		rightList.add(new Balance("1301", "管理费用1", "223.31"));
		rightList.add(new Balance("1302", "管理费用2", "223.32"));
		rightList.add(new Balance("1303", "管理费用3", "223.33"));

		request.setAttribute("leftList", leftList);
		request.setAttribute("rightList", rightList);
	}

	public long delete(long id) throws AppException {
		try {
			balanceDAO.delete(id);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public void deleteBalance(Long id) throws AppException {
		Balance balance = getBalanceById(id);
		// balance.setStatus(Balance.STATES_0);// 将状态变为无效
		update(balance);
	}

	public long save(Balance balance) throws AppException {
		return balanceDAO.save(balance);
	}

	public long update(Balance balance) throws AppException {
		return balanceDAO.update(balance);
	}

	public Balance getBalanceById(long id) throws AppException {
		return balanceDAO.getBalanceById(id);
	}

	public List<Balance> getBalanceList() throws AppException {
		return balanceDAO.getBalanceList();
	}

	public List<Balance> getBalanceList(Long type) throws AppException {
		return balanceDAO.getBalanceList(type);
	}

	public void setBalanceDAO(BalanceDAO balanceDAO) {
		this.balanceDAO = balanceDAO;
	}
}