package com.kurui.kums.report.action;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.kurui.kums.base.struts.BaseAction;
import com.kurui.kums.base.ui.inform.Inform;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.base.Constant;
import com.kurui.kums.library.util.PlatComAccountStore;
import com.kurui.kums.report.Balance;
import com.kurui.kums.report.BalanceListForm;
import com.kurui.kums.report.biz.BalanceBiz;
import com.kurui.kums.report.biz.BalanceBizImp;

public class BalanceListAction extends BaseAction {
	private BalanceBiz balanceBiz;

	static Logger logger = Logger.getLogger(BalanceBizImp.class.getName());

	public void createReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		BalanceListForm crlf = (BalanceListForm) form;

		if (crlf == null) {
			crlf = new BalanceListForm();
		}
		try {
			balanceBiz.createBalance(crlf, request);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	}

	// 利润表
	public ActionForward listEarnings(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		BalanceListForm blf = (BalanceListForm) form;

		if (blf == null) {
			blf = new BalanceListForm();
		}
		try {
			List<Balance> earningsList = balanceBiz.listEarnings(blf, request);
			request.setAttribute("earningsList", earningsList);
			
			BigDecimal totalValue1 = Balance.getTotalValue(earningsList);
			blf.setTotalValue1(totalValue1);

		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("blf", blf);
		return mapping.findForward("listEarnings");
	}

	// 平衡表
	public ActionForward listEquity(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		BalanceListForm blf = (BalanceListForm) form;

		if (blf == null) {
			blf = new BalanceListForm();
		}
		try {
			balanceBiz.listEquity(blf, request);

		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("blf", blf);
		return mapping.findForward("listEquity");
	}

	public ActionForward listBalance(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		BalanceListForm blf = (BalanceListForm) form;

		if (blf == null) {
			blf = new BalanceListForm();
		}
		try {
			balanceBiz.listEarnings(blf, request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("blf", blf);
		return mapping.findForward("listBalance");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		BalanceListForm alf = (BalanceListForm) form;
		Balance balance = new Balance();

		balance.setThisAction("insert");
		request.setAttribute("balance", balance);

		String forwardPage = "editBalance";
		return mapping.findForward(forwardPage);
	}

	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		BalanceListForm arlf = (BalanceListForm) form;
		try {
			long balanceId = Constant.toLong(arlf.getId());
			Balance balance = balanceBiz.getBalanceById(balanceId);
			request.setAttribute("balance", balance);

		} catch (Exception e) {
			e.printStackTrace();
		}
		forwardPage = "viewBalance";
		return mapping.findForward(forwardPage);
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		Inform inf = new Inform();
		BalanceListForm arlf = (BalanceListForm) form;

		long balanceId = arlf.getId();
		if (balanceId < 1) {
			balanceId = arlf.getSelectedItems()[0];
		}

		if (balanceId > 0) {
			Balance balance = balanceBiz.getBalanceById(balanceId);
			balance.setThisAction("update");

			request.setAttribute("balance", balance);
		} else {
			inf.setMessage("缺少balanceId");
			return forwardInformPage(inf, mapping, request);
		}
		request.setAttribute("companyList", PlatComAccountStore
				.getGroupCompnayList());
		return mapping.findForward("editBalance");
	}

	public void setBalanceBiz(BalanceBiz balanceBiz) {
		this.balanceBiz = balanceBiz;
	}

}