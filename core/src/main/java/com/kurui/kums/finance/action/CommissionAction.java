package com.kurui.kums.finance.action;

import java.sql.Timestamp;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;
import com.kurui.kums.agent.Agent;
import com.kurui.kums.agent.biz.AgentBiz;
import com.kurui.kums.base.struts.BaseAction;
import com.kurui.kums.base.ui.inform.Inform;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.finance.Commission;
import com.kurui.kums.finance.FinanceOrder;
import com.kurui.kums.finance.biz.CommissionBiz;
import com.kurui.kums.finance.biz.FinanceOrderBiz;
import com.kurui.kums.market.SaleEvent;
import com.kurui.kums.market.biz.SaleEventBiz;
import com.kurui.kums.right.UserRightInfo;

public class CommissionAction extends BaseAction {
	private CommissionBiz commissionBiz;
	private FinanceOrderBiz financeOrderBiz;
	private AgentBiz agentBiz;
	private SaleEventBiz saleEventBiz;

	public ActionForward insert(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		Commission pform = (Commission) form;
		Inform inf = new Inform();
		try {
			Commission commission = new Commission();
			commission.setTotalAmount(pform.getTotalAmount());
			commission.setType(pform.getType());
			commission.setCreateTime(new Timestamp(System.currentTimeMillis()));
			commission.setMemo(pform.getMemo());
			commission.setStatus(pform.getStatus());
			FinanceOrder order = financeOrderBiz.getFinanceOrderById(pform
					.getFinanceOrderId());
			commission.setFinanceOrder(order);
			Agent crossAgent = agentBiz.getAgentById(pform.getCrossAgentId());
			commission.setCrossAgent(crossAgent);
			SaleEvent saleEvent = saleEventBiz.getSaleEventById(pform
					.getSaleEventId());
			commission.setSaleEvent(saleEvent);

			long num = commissionBiz.save(commission);
			if (num > 0) {
				return new ActionRedirect(
						"/finance/commissionList.do?thisAction=list");
			} else {
				inf.setMessage("您添加佣金明细失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			inf.setMessage("异常:" + e.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}

	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		Commission pform = (Commission) form;
		Inform inf = new Inform();
		UserRightInfo uri = (UserRightInfo) request.getSession().getAttribute(
				"URI");
		try {
			if (pform.getId() > 0) {
				Commission commission = commissionBiz.getCommissionById(pform
						.getId());

				commission.setTotalAmount(pform.getTotalAmount());
				commission.setType(pform.getType());
				commission.setCreateTime(new Timestamp(System.currentTimeMillis()));
				commission.setMemo(pform.getMemo());
				commission.setStatus(pform.getStatus());
				FinanceOrder order = financeOrderBiz.getFinanceOrderById(pform
						.getFinanceOrderId());
				commission.setFinanceOrder(order);
				Agent crossAgent = agentBiz.getAgentById(pform.getCrossAgentId());
				commission.setCrossAgent(crossAgent);
				SaleEvent saleEvent = saleEventBiz.getSaleEventById(pform
						.getSaleEventId());
				commission.setSaleEvent(saleEvent);
				long flag = commissionBiz.update(commission);

				if (flag > 0) {
					return new ActionRedirect(
							"/finance/commissionList.do?thisAction=list");
				} else {
					inf.setMessage("您修改佣金明细失败！");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			inf.setMessage("异常:" + e.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}

	public void setCommissionBiz(CommissionBiz commissionBiz) {
		this.commissionBiz = commissionBiz;
	}

	public void setFinanceOrderBiz(FinanceOrderBiz financeOrderBiz) {
		this.financeOrderBiz = financeOrderBiz;
	}

	public void setAgentBiz(AgentBiz agentBiz) {
		this.agentBiz = agentBiz;
	}

	public void setSaleEventBiz(SaleEventBiz saleEventBiz) {
		this.saleEventBiz = saleEventBiz;
	}

}