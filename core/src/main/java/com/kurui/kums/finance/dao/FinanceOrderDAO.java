package com.kurui.kums.finance.dao;

import java.util.List;

import com.kurui.kums.base.database.BaseDAO;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.finance.FinanceOrder;
import com.kurui.kums.finance.FinanceOrderListForm;
import com.kurui.kums.finance.OrderGroup;
import com.kurui.kums.right.UserRightInfo;

public interface FinanceOrderDAO extends BaseDAO {

	public FinanceOrder getFinanceOrderByStatementId(long statementId)
			throws AppException;

	public FinanceOrder getFinanceOrderById(long airtickeOrderId)
			throws AppException;

	public List list(FinanceOrderListForm rlf, UserRightInfo uri)
			throws AppException;

	public List listData(FinanceOrderListForm rlf, UserRightInfo uri)
			throws AppException;

	public List list() throws AppException;

	public List<FinanceOrder> listByGroupId(long groupId) throws AppException;

	public List<FinanceOrder> listBySubGroupAndGroupId(long groupId,
			Long subMarkNo) throws AppException;

	public List listIDBySubGroupAndGroupId(long orderGroupId, Long subMarkNo)
			throws AppException;

	public List listIDByGroupId(long orderGroupId) throws AppException;

	public List<FinanceOrder> listBySubGroupByGroupIdAndType(long orderGroupId,
			long subMarkNo, long businessType) throws AppException;

	public List<FinanceOrder> listByGroupIdAndTranType(long groupId,
			String tranType) throws AppException;

	public List<FinanceOrder> listByGroupIdAndTranTypeStatus(long groupId,
			String tranType, String status) throws AppException;

	public List<FinanceOrder> listByGroupIdAndBusinessTranType(long groupId,
			String tranType, String businessType) throws AppException;

	public List<FinanceOrder> listByAgent(long agentId, String tranType)
			throws AppException;

	public List<FinanceOrder> listByTranType(String tranType)
			throws AppException;
	
	public List<FinanceOrder> listFinanceForAssetsItem(FinanceOrderListForm ulf)
			throws AppException;

	public void delete(long id) throws AppException;

	public long save(FinanceOrder financeOrder) throws AppException;

	public long update(FinanceOrder financeOrder) throws AppException;

	public long getNewSubGroupMarkNo(long orgerGroupId) throws AppException;

	public long saveOrderGroup(OrderGroup og) throws AppException;

	public OrderGroup getOrderGroupById(long id) throws AppException;

	public long getGroupIdByOrderId(long orderId) throws AppException;
}
