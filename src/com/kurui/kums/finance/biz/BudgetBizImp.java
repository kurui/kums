package com.kurui.kums.finance.biz;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import com.kurui.kums.finance.dao.BudgetDAO;
import com.kurui.kums.finance.dao.BudgetOrderDAO;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.finance.Budget;
import com.kurui.kums.finance.BudgetListForm;
import com.kurui.kums.finance.BudgetOrder;
import com.kurui.kums.finance.Statement;
import com.kurui.kums.right.SysUser;
import com.kurui.kums.right.UserRightInfo;

public class BudgetBizImp implements BudgetBiz {
	private BudgetDAO budgetDAO;
	private BudgetOrderDAO budgetOrderDAO;

	static Logger logger = Logger.getLogger(BudgetBizImp.class.getName());

	// 主营业务录入
	public String insertMainOrder(HttpServletRequest request, Budget orderForm)
			throws AppException {
		String forwardPage = "";

		if (orderForm != null) {

			UserRightInfo uri = (UserRightInfo) request.getSession()
					.getAttribute("URI");
			SysUser sysUser = uri.getUser();
			String userNo = sysUser.getUserNo();

			Budget budget = new Budget();
			// budget.setOutOrderNo(orderForm.getOutOrderNo());
			//
			// budget.setTotalAmount(orderForm.getTotalAmount());
			// budget.setStatementAmount(orderForm.getTotalAmount() + "");
			// budget.setHandlingCharge(orderForm.getHandlingCharge());
			// budget.setEntryOperator(userNo);
			// budget.setEntryTime(DateUtil.getTimestamp(orderForm.getEntryDate(),
			// "yyyy-MM-dd"));
			// budget.setMemo(orderForm.getMemo());
			// budget.setLocked(Budget.LOCK0);
			// budget.setCreateDate(new Timestamp(System.currentTimeMillis()));
			budget.setStatus(Budget.STATUS_1);
			save(budget);

			// 保存订单明细
			insertBudgetOrder(orderForm, budget, request);

			forwardPage = Statement.ORDERTYPE_15 + "";
		} else {
			forwardPage = "NULLFORM";
		}
		return forwardPage;
	}

	/**
	 * 保存订单明细
	 */
	private String insertBudgetOrder(Budget orderForm, Budget budget,
			HttpServletRequest request) throws AppException {
		String result = "";
		// Long[] productIds = orderForm.getProductIds();
		// String[] productNames = orderForm.getProductNames();
		// BigDecimal[] productPrices = orderForm.getProductPrices();
		// BigDecimal[] productCounts = orderForm.getProductCounts();
		// BigDecimal[] detailAmounts = orderForm.getDetailAmounts();
		// String[] detailMemos = orderForm.getDetailMemos();

		// for (int i = 0; i < productIds.length; i++) {
		// Long productId = productIds[i];
		// String productName = productNames[i];
		// BigDecimal productPrice = productPrices[i];
		// BigDecimal productCount = productCounts[i];
		// BigDecimal detailAmount = detailAmounts[i];
		// String detailMemo = detailMemos[i];
		// BudgetOrder udgetOrder = new BudgetOrder();
		// udgetOrder.setBudget(budget);
		// udgetOrder.setDiscountPrice(productPrice);
		// udgetOrder.setProductCount(productCount);
		// udgetOrder.setDetailAmount(detailAmount);
		// udgetOrder.setStatus(BudgetOrder.STATES_1);
		// udgetOrder.setMemo(detailMemo);
		// long num = budgetOrderDAO.save(udgetOrder);
		// }

		return result;
	}

	// 删除订单(改变状态)
	public void deleteBudget(Long id) throws AppException {
		Budget budget = getBudgetById(id);
		budget.setStatus(Budget.STATUS_0);// 将订单状态变为已废弃
		update(budget);

		deleteDetailByOrderId(id);
	}

	// 删除订单的关联明细记录(改变状态)
	public void deleteDetailByOrderId(Long budgetId) throws AppException {
		List budgetOrderList = budgetOrderDAO
				.getBudgetOrderByBudgetId(budgetId);
		for (int i = 0; i < budgetOrderList.size(); i++) {
			BudgetOrder budgetOrder = (BudgetOrder) budgetOrderList.get(i);
			budgetOrder.setStatus(BudgetOrder.STATES_0);//
			budgetOrderDAO.update(budgetOrder);
		}
	}

	public String view(long id, HttpServletRequest request) throws AppException {
		String forwardPage = "";

		if (id > 0) {
			List<BudgetOrder> budgetOrderList = new ArrayList<BudgetOrder>();

			Budget budget = getBudgetById(id);
			if (budget != null) {
				String ordersString = "";

				request.setAttribute("budget", budget);

				// udgetOrderList = bbudgetOrderDAO
				// .getBudgetOrderListByOrderIds(ordersString);
				// request.setAttribute("udgetOrderList", udgetOrderList);
				//
				// forwardPage = budget.getOrderGroup().getNo();
			} else {
				forwardPage = "NOORDER";
			}
		} else {
			forwardPage = "ERROR";
		}
		return forwardPage;
	}

	public List list(BudgetListForm rlf) throws AppException {
		return budgetDAO.list(rlf);
	}

	public void delete(long id) throws AppException {
		budgetDAO.delete(id);
	}

	public long save(Budget budget) throws AppException {
		return budgetDAO.save(budget);
	}

	public long update(Budget budget) throws AppException {
		return budgetDAO.update(budget);
	}

	public Budget getBudgetById(long budgetId) throws AppException {
		return budgetDAO.getBudgetById(budgetId);
	}

	public void setBudgetDAO(BudgetDAO budgetDAO) {
		this.budgetDAO = budgetDAO;
	}

	public void setBudgetOrderDAO(BudgetOrderDAO budgetOrderDAO) {
		this.budgetOrderDAO = budgetOrderDAO;
	}

}
