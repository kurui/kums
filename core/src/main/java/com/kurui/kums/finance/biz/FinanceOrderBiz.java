package com.kurui.kums.finance.biz;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.finance.FinanceOrder;
import com.kurui.kums.finance.FinanceOrderListForm;
import com.kurui.kums.right.SysUser;
import com.kurui.kums.right.UserRightInfo;

public interface FinanceOrderBiz {
	public String insertLiveOrder(HttpServletRequest request,
			FinanceOrder financeOrderForm) throws AppException;

	public String insertMainOrder(HttpServletRequest request,
			FinanceOrder financeOrderForm) throws AppException;

	public String insertCreditOrder(HttpServletRequest request,
			FinanceOrder financeOrderForm) throws AppException;

	public String insertRepayCreditOrder(HttpServletRequest request,
			FinanceOrder financeOrderForm) throws AppException;

	public String updateOrder(FinanceOrder form, HttpServletRequest request)
			throws AppException;

	public void editOrder(FinanceOrderListForm ulf, HttpServletRequest request)
			throws AppException;

	public void processing(FinanceOrderListForm ulf, HttpServletRequest request)
			throws AppException;

	public String view(long id, HttpServletRequest request) throws AppException;

	public void deleteFinanceOrder(Long financeOrderId) throws AppException;

	public FinanceOrder getFinanceOrderById(long financeOrderId)
			throws AppException;

	public FinanceOrder getFinanceOrderByStatementId(long statementId)
			throws AppException;

	public void saveOperateLog(FinanceOrder order, SysUser sysUser,
			HttpServletRequest request, long operateLogType, String content)
			throws AppException;

	public FinanceOrder getFinanceOrderByGroupIdAndTranType(long groupId,
			String tranType) throws AppException;

	public FinanceOrder getFinanceOrderByGroupIdStatus(long groupId,
			String tranType, String status) throws AppException;

	public List list(FinanceOrderListForm rlf, UserRightInfo uri)
			throws AppException;

	public List listData(FinanceOrderListForm rlf, UserRightInfo uri)
			throws AppException;

	public List list() throws AppException;

	public List<FinanceOrder> listByGroupId(long groupId) throws AppException;

	public List<FinanceOrder> listBySubGroupAndGroupId(long orderGroupId,
			Long subMarkNo) throws AppException;

	public List listIDBySubGroupAndGroupId(long orderGroupId, Long subMarkNo)
			throws AppException;

	public List<FinanceOrder> listByAgent(long agentId, String tranType)
			throws AppException;

	public long getGroupIdByOrderId(long orderId) throws AppException;

	public void delete(long id) throws AppException;

	public long save(FinanceOrder financeOrder) throws AppException;

	public long update(FinanceOrder financeOrder) throws AppException;
}
