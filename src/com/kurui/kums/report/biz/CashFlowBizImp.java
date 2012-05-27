package com.kurui.kums.report.biz;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import com.kurui.kums.report.biz.CashFlowBiz;
import com.kurui.kums.report.CashFlow;
import com.kurui.kums.report.CashFlowListForm;
import com.kurui.kums.report.dao.CashFlowDAO;
import com.kurui.kums.base.exception.AppException;

public class CashFlowBizImp implements CashFlowBiz {

	private CashFlowDAO cashFlowDAO;

	static Logger logger = Logger.getLogger(CashFlowBizImp.class.getName());

	public void createCashFlow(CashFlowListForm cashFlowListForm,
			HttpServletRequest request) {
		cashFlowDAO.createCashFlow(cashFlowListForm, request);
	}

	public List list(CashFlowListForm cashFlowListForm,
			HttpServletRequest request) throws AppException {
		return cashFlowDAO.list(cashFlowListForm, request);
	}

	public long delete(long id) throws AppException {
		try {
			cashFlowDAO.delete(id);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public void deleteCashFlow(Long id) throws AppException {
		CashFlow cashFlow = getCashFlowById(id);
		// cashFlow.setStatus(CashFlow.STATES_0);// 将状态变为无效
		update(cashFlow);
	}

	public long save(CashFlow cashFlow) throws AppException {
		return cashFlowDAO.save(cashFlow);
	}

	public long update(CashFlow cashFlow) throws AppException {
		return cashFlowDAO.update(cashFlow);
	}

	public CashFlow getCashFlowById(long id) throws AppException {
		return cashFlowDAO.getCashFlowById(id);
	}

	public List<CashFlow> getCashFlowList() throws AppException {
		return cashFlowDAO.getCashFlowList();
	}

	public List<CashFlow> getCashFlowList(Long type) throws AppException {
		return cashFlowDAO.getCashFlowList(type);
	}

	public void setCashFlowDAO(CashFlowDAO cashFlowDAO) {
		this.cashFlowDAO = cashFlowDAO;
	}
}
