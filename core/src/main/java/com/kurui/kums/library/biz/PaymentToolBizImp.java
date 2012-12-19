package com.kurui.kums.library.biz;

import java.util.List;

import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.library.Company;
import com.kurui.kums.library.PaymentTool;
import com.kurui.kums.library.PaymentToolListForm;
import com.kurui.kums.library.dao.PaymentToolDAO;

public class PaymentToolBizImp implements PaymentToolBiz {
	private PaymentToolDAO paymentToolDAO;

	public List list(PaymentToolListForm paymentToolForm) throws AppException {
		return paymentToolDAO.list(paymentToolForm);
	}

	public List getPaymentToolListByType(String type) throws AppException {
		return paymentToolDAO.getPaymentToolListByType(type);
	}

	public long delete(long id) throws AppException {
		try {
			paymentToolDAO.delete(id);
			return 1;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}
	
	public void deletePaymentTool(Long paymentToolId) throws AppException {
		PaymentTool paymentTool = getPaymentToolById(paymentToolId);
		paymentTool.setStatus(PaymentTool.STATES_0);// 将状态变为无效
		update(paymentTool);
	}

	public long save(PaymentTool paymentTool) throws AppException {
		return paymentToolDAO.save(paymentTool);
	}

	public long update(PaymentTool paymentTool) throws AppException {
		return paymentToolDAO.update(paymentTool);
	}

	public PaymentTool getPaymentToolById(long paymentToolId)
			throws AppException {
		return paymentToolDAO.getPaymentToolById(paymentToolId);
	}

	public List<PaymentTool> getPaymentToolList() throws AppException {
		return paymentToolDAO.getPaymentToolList();
	}

	public void setPaymentToolDAO(PaymentToolDAO paymentToolDAO) {
		this.paymentToolDAO = paymentToolDAO;
	}
}
