package com.kurui.kums.finance.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import com.kurui.kums.base.database.BaseDAOSupport;
import com.kurui.kums.base.database.Hql;
import com.kurui.kums.base.Constant;
import com.kurui.kums.base.util.KumsNoUtil;
import com.kurui.kums.base.util.StringUtil;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.finance.FinanceOrder;
import com.kurui.kums.finance.FinanceOrderListForm;
import com.kurui.kums.finance.OrderGroup;
import com.kurui.kums.right.UserRightInfo;

public class FinanceOrderDAOImp extends BaseDAOSupport implements
		FinanceOrderDAO {
	private KumsNoUtil noUtil;

	static Logger logger = Logger.getLogger(FinanceOrderDAOImp.class.getName());

	private Hql listHql(FinanceOrderListForm rlf, UserRightInfo uri)
			throws AppException {
		Hql hql = new Hql();
		hql.add(" from FinanceOrder b where exists(select distinct orderGroup.id ");
		hql.add(" from FinanceOrder a where 1=1 ");

		if (StringUtil.isEmpty(Constant.toString(rlf.getKeyWord())) == false) {
			hql.add(" and ( ");
			hql.add("  a.orderNo='" + Constant.toString(rlf.getKeyWord()) + "'");
			hql.add(" or ");
			hql.add("  a.memo like '%" + Constant.toString(rlf.getKeyWord())
					+ "%'");
			hql.add(" ) ");
		}

		// // orderNO
		// if (!"".equals(Constant.toString(rlf.getOrderNo()))) {
		// hql.add(" and a.orderNo='" + Constant.toString(rlf.getOrderNo())
		// + "'");
		// }
		//
		// // memo
		// if (!"".equals(Constant.toString(rlf.getMemo()))) {
		// hql.add(" and a.memo like '%" + Constant.toString(rlf.getMemo())
		// + "%'");
		// }

		// 客户
		if (Constant.toLong(rlf.getAgentId()) > 0) {
			hql.add(" and a.agent.id=" + rlf.getAgentId());
		}

		// 多个订单状态
		if (StringUtil.isEmpty(Constant.toString(rlf.getStatusGroup())) == false) {
			hql.add(" and a.status  in (" + rlf.getStatusGroup() + ") ");
		}

		// 买入平台
		if (rlf.getFromPlatformId() > 0) {
			hql.add(" and a.platform.id=" + rlf.getFromPlatformId()
					+ " and businessType=" + FinanceOrder.BUSINESSTYPE_2);
		}
		// 卖出平台
		if (rlf.getToPlatformId() > 0) {
			hql.add(" and a.platform.id=" + rlf.getToPlatformId()
					+ " and businessType=" + FinanceOrder.BUSINESSTYPE_1);
		}

		// 付款
		if (rlf.getFromAccountId() > 0) {
			hql.add(" and a.account.id=" + rlf.getFromAccountId()
					+ " and businessType=" + FinanceOrder.BUSINESSTYPE_2);
		}

		// 收款
		if (rlf.getToAccountId() > 0) {
			hql.add(" and a.account.id=" + rlf.getToAccountId()
					+ " and businessType=" + FinanceOrder.BUSINESSTYPE_1);
		}

		// 按日期搜索
		String startDate = rlf.getStartDate();
		String endDate = rlf.getEndDate();
		if (StringUtil.isEmpty(startDate) == false
				&& StringUtil.isEmpty(endDate) == false) {
			hql.add(" and  a.businessTime  between to_date(?,'yyyy-mm-dd hh24:mi:ss') ");
			hql.add(" and to_date(?,'yyyy-mm-dd hh24:mi:ss') ");
			hql.addParamter(startDate);
			hql.addParamter(endDate);
			
			rlf.setRecentlyDay(null);
		}
		

		// 最近N天
		if (rlf.getRecentlyDay() != null && rlf.getRecentlyDay().intValue() > 0) {
			hql.add(" and  trunc(sysdate -to_date(a.businessTime))<= "
					+ rlf.getRecentlyDay());
		}

		// 多个 交易类型
		if (StringUtil.isEmpty(rlf.getTranTypeGroup()) == false) {
			hql.add(" and a.tranType  in (" + rlf.getTranTypeGroup() + ")");
		}

		hql.add(" and b.orderGroup.id=a.orderGroup.id and b.subGroupMarkNo=a.subGroupMarkNo )");

		hql.add(" and b.status not in (" + FinanceOrder.STATUS_88 + ") ");

		if (Constant.toString(rlf.getOrderBy()).equals("updateTime")) {
			hql.add(" order by  b.orderGroup.lastTime desc,b.orderGroup.id,b.subGroupMarkNo,b.tranType");
		} else if (Constant.toString(rlf.getOrderBy()).equals("businessTime")) {
			hql.add(" order by  b.businessTime desc,b.orderGroup.id desc,b.subGroupMarkNo,b.tranType");
		} else {
			hql.add(" order by  b.businessTime desc,b.orderGroup.id desc,b.subGroupMarkNo,b.tranType");
		}

		// logger.info("listHql:" + hql);
		return hql;
	}

	public List list(FinanceOrderListForm rlf, UserRightInfo uri)
			throws AppException {
		long a = System.currentTimeMillis();
		Hql hql = listHql(rlf, uri);

		Hql hql1 = new Hql();
		hql1.add(" select count(distinct b.orderGroup.id) ");
		hql1.addHql(hql);

		List list = this.list(hql1);
		if (list != null && list.size() > 0) {
			Object obj = list.get(0);
			Long count = (Long) obj;
			rlf.setGroupCount(count);
		}
		return this.list(hql, rlf);
	}

	public List listData(FinanceOrderListForm rlf, UserRightInfo uri)
			throws AppException {
		List<FinanceOrder> list = new ArrayList<FinanceOrder>();

		Hql hql = listHql(rlf, uri);

		Query query = this.getQuery(hql);
		if (query != null) {
			list = query.list();
			if (list != null && list.size() > 0)
				return list;

		}
		return list;
	}

	public FinanceOrder getFinanceOrderById(long airtickeOrderId)
			throws AppException {
		Hql hql = new Hql();
		hql.add("from FinanceOrder a where a.id=" + airtickeOrderId);
		Query query = this.getQuery(hql);
		FinanceOrder financeOrder = null;
		if (query != null) {
			List<FinanceOrder> list = query.list();
			if (list != null && list.size() > 0)
				financeOrder = list.get(0);

		}
		return financeOrder;
	}

	public FinanceOrder getFinanceOrderByStatementId(long statementId)
			throws AppException {
		Hql hql = new Hql();
		hql.add("from FinanceOrder a,Statement s where a.id=s.orderId  and s.id="
				+ statementId);

		Query query = this.getQuery(hql);
		FinanceOrder financeOrder = null;
		if (query != null) {
			List<FinanceOrder> list = query.list();
			if (list != null && list.size() > 0)
				financeOrder = list.get(0);

		}
		return financeOrder;
	}

	public long getGroupIdByOrderId(long orderId) throws AppException {
		Hql hql = new Hql();
		hql.add("select a.orderGroup.id from FinanceOrder a where a.id="
				+ orderId);

		Query query = this.getQuery(hql);

		Long groupId = new Long(0);
		if (query != null) {
			List list = query.list();
			if (list != null) {
				if (list.size() > 0) {
					groupId = (Long) list.get(0);
				}
			}
		}
		return groupId;
	}

	// 订单组
	public List<FinanceOrder> listByGroupId(long groupId) throws AppException {
		List<FinanceOrder> list = new ArrayList<FinanceOrder>();
		Hql hql = new Hql();
		hql.add("from FinanceOrder a where a.orderGroup.id=" + groupId);
		hql.add(" and a.status not in(" + FinanceOrder.STATUS_88 + ")");
		hql.add(" order by a.tranType ");
		Query query = this.getQuery(hql);
		if (query != null) {
			list = query.list();
			if (list != null) {
				if (list.size() > 0) {
					return list;
				}
			}
		}
		return list;
	}

	public List<FinanceOrder> listByGroupIdAndTranType(long orderGroupId,
			String tranType) throws AppException {
		List<FinanceOrder> list = new ArrayList();
		Hql hql = new Hql("from FinanceOrder a where 1=1 ");
		hql.add(" and a.orderGroup.id=" + orderGroupId);
		if (tranType != null && !"".equals(tranType.trim())) {
			hql.add("and tranType in(" + tranType + ")");
		}
		// System.out.println(hql);
		Query query = this.getQuery(hql);
		if (query != null) {
			list = query.list();
			if (list != null && list.size() > 0)
				return list;

		}
		return list;
	}

	public List<FinanceOrder> listByGroupIdAndBusinessType(long orderGroupId,
			String businessType) throws AppException {
		List<FinanceOrder> list = new ArrayList();
		Hql hql = new Hql("from FinanceOrder a where 1=1 ");
		hql.add(" and a.orderGroup.id=" + orderGroupId);
		if (businessType != null && !"".equals(businessType.trim())) {
			hql.add("and businessType in(" + businessType + ")");
		}
		// System.out.println(hql);
		Query query = this.getQuery(hql);
		if (query != null) {
			list = query.list();
			if (list != null && list.size() > 0)
				return list;

		}
		return list;
	}

	// 根据订单组号 查询
	public List<FinanceOrder> listByGroupIdAndTranTypeStatus(long orderGroupId,
			String tranType, String status) throws AppException {
		List<FinanceOrder> list = new ArrayList<FinanceOrder>();
		Hql hql = new Hql("from FinanceOrder a where 1=1 ");
		hql.add(" and a.orderGroup.id=" + orderGroupId);

		if (tranType != null && !"".equals(tranType.trim())) {
			hql.add(" and tranType in(" + tranType + ")");
		}
		if (status != null && !"".equals(status.trim())) {
			hql.add(" and status in(" + status + ")");
		}

		// System.out.println("listByGroupIdAndTranTypeStatus>>" + hql);

		Query query = this.getQuery(hql);
		if (query != null) {
			list = query.list();
			if (list != null) {
				if (list.size() > 0) {
					return list;
				}
			}
		}
		return list;
	}

	// 根据订单组、小组号 查询
	public List<FinanceOrder> listBySubGroupAndGroupId(long orderGroupId,
			Long subMarkNo) throws AppException {
		List<FinanceOrder> list = new ArrayList();
		Hql hql = new Hql(" from FinanceOrder a where 1=1 ");
		hql.add(" and a.orderGroup.id=" + orderGroupId);

		hql.add(" and subGroupMarkNo =" + subMarkNo);

		hql.add(" and status not in(" + FinanceOrder.STATUS_88 + " ) ");

		hql.add(" order by a.id  ");

		Query query = this.getQuery(hql);
		if (query != null) {
			list = query.list();
			if (list != null) {
				if (list.size() > 0) {
					return list;
				}
			}
		}
		return list;
	}

	// 根据订单组、小组号 查询
	public List<FinanceOrder> listBySubGroupByGroupIdAndType(long orderGroupId,
			long subMarkNo, long businessType) throws AppException {
		List<FinanceOrder> list = new ArrayList();
		Hql hql = new Hql(" from FinanceOrder a where 1=1 ");
		hql.add(" and a.orderGroup.id=" + orderGroupId);

		hql.add(" and subGroupMarkNo =" + subMarkNo);

		hql.add(" and businessType=" + businessType);

		// System.out.println(hql);

		Query query = this.getQuery(hql);
		if (query != null) {
			list = query.list();
			if (list != null) {
				if (list.size() > 0) {
					return list;
				}
			}
		}
		return list;
	}

	// 查询小组的订单IDList
	public List listIDBySubGroupAndGroupId(long orderGroupId, Long subMarkNo)
			throws AppException {
		List<FinanceOrder> list = new ArrayList();
		Hql hql = new Hql(" select a.id from FinanceOrder a where 1=1 ");
		hql.add(" and a.orderGroup.id=" + orderGroupId);

		hql.add(" and subGroupMarkNo =" + subMarkNo);

		hql.add(" and status not in(" + FinanceOrder.STATUS_88 + " ) ");

		hql.add(" order by a.id  ");

		Query query = this.getQuery(hql);
		if (query != null) {
			list = query.list();
			if (list != null) {
				if (list.size() > 0) {
					return list;
				}
			}
		}
		return list;
	}

	// 查询整个大组的订单IDList
	public List listIDByGroupId(long orderGroupId) throws AppException {
		List<FinanceOrder> list = new ArrayList();
		Hql hql = new Hql(" select a.id from FinanceOrder a where 1=1 ");
		hql.add(" and a.orderGroup.id=" + orderGroupId);
		hql.add(" and status not in(" + FinanceOrder.STATUS_88 + " ) ");
		hql.add(" order by a.id  ");

		Query query = this.getQuery(hql);
		if (query != null) {
			list = query.list();
			if (list != null) {
				if (list.size() > 0) {
					return list;
				}
			}
		}
		return list;
	}

	// 根据订单组号 业务、交易类型 查询
	public List<FinanceOrder> listByGroupIdAndBusinessTranType(
			long orderGroupId, String tranType, String businessType)
			throws AppException {
		List<FinanceOrder> list = new ArrayList();
		Hql hql = new Hql("from FinanceOrder a where 1=1 ");
		hql.add("and a.orderGroup.id=" + orderGroupId);

		if (tranType != null && !"".equals(tranType.trim())) {
			hql.add("and tranType in(" + tranType + ")");
		}

		if (businessType != null && !"".equals(businessType.trim())) {
			hql.add("and businessType in(" + businessType + ")");
		}

		Query query = this.getQuery(hql);
		if (query != null) {
			list = query.list();
			if (list != null) {
				if (list.size() > 0) {
					return list;
				}
			}
		}
		return list;
	}

	public List<FinanceOrder> listByTranType(String tranType)
			throws AppException {
		List<FinanceOrder> list = new ArrayList();
		Hql hql = new Hql("from FinanceOrder f where 1=1 ");

		if (tranType != null && !"".equals(tranType.trim())) {
			hql.add("and f.tranType in(" + tranType + ")");
		}

		hql.add(" and f.status not in(" + FinanceOrder.STATUS_88 + " ) ");
		// System.out.println(hql);
		Query query = this.getQuery(hql);
		if (query != null) {
			list = query.list();
			if (list != null && list.size() > 0) {
				return list;
			} else {
				list = new ArrayList();
			}
		}
		return list;
	}

	public List<FinanceOrder> listByAgent(long agentId, String tranType)
			throws AppException {
		List<FinanceOrder> list = new ArrayList();
		Hql hql = new Hql("from FinanceOrder f where 1=1 ");
		hql.add("and f.agent.id=" + agentId);

		if (tranType != null && !"".equals(tranType.trim())) {
			hql.add("and f.tranType in(" + tranType + ")");
		}

		hql.add(" and f.status not in(" + FinanceOrder.STATUS_88 + " ) ");
		// System.out.println(hql);
		Query query = this.getQuery(hql);
		if (query != null) {
			list = query.list();
		}
		return list;

	}

	public List list() throws AppException {
		Hql hql = new Hql();
		hql.add("from FinanceOrder where 1=1");
		return this.list(hql);
	}

	public void delete(long id) throws AppException {
		if (id > 0) {
			FinanceOrder financeOrder = (FinanceOrder) this
					.getHibernateTemplate().get(FinanceOrder.class,
							Long.valueOf(id));
			this.getHibernateTemplate().delete(financeOrder);
		}
	}

	public long save(FinanceOrder financeOrder) throws AppException {
		if (financeOrder.getId() <= 0) {
			financeOrder.setOrderNo(noUtil.getFinanceOrderNo());
			financeOrder
					.setCreateTime(new Timestamp(System.currentTimeMillis()));
		}
		this.getHibernateTemplate().save(financeOrder);
		return financeOrder.getId();
	}

	public long update(FinanceOrder financeOrder) throws AppException {
		if (financeOrder.getId() <= 0) {
			financeOrder.setOrderNo(noUtil.getFinanceOrderNo());
			financeOrder
					.setCreateTime(new Timestamp(System.currentTimeMillis()));
		}
		this.getHibernateTemplate().saveOrUpdate(financeOrder);
		return financeOrder.getId();
	}

	/**
	 * 取子分组号
	 */
	public long getNewSubGroupMarkNo(long orderGroupId) {
		try {
			Hql hql = new Hql(
					"select max(subGroupMarkNo) from FinanceOrder a where 1=1 and status<>88");
			hql.add("and a.orderGroup.id=" + orderGroupId);

			Query query = this.getQuery(hql);
			if (query != null) {
				Object temp = query.uniqueResult();
				if (temp != null) {
					long x = (((Long) temp).intValue()) + 1;
					return x;
				}
			}
			return 0;
		} catch (Exception ex) {
			System.out.print(ex.getMessage());
			return -1;
		}
	}

	public long saveOrderGroup(OrderGroup og) throws AppException {

		this.getHibernateTemplate().saveOrUpdate(og);
		return og.getId();
	}

	public OrderGroup getOrderGroupById(long id) throws AppException {
		if (id > 0) {
			OrderGroup orderGroup = (OrderGroup) this.getHibernateTemplate()
					.get(OrderGroup.class, Long.valueOf(id));
			return orderGroup;
		}
		return null;
	}

	public void setNoUtil(KumsNoUtil noUtil) {
		this.noUtil = noUtil;
	}

}
