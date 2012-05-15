package com.kurui.kums.transaction.biz;

import java.util.List;

import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.transaction.PaymentTool;
import com.kurui.kums.transaction.PaymentToolListForm;

public interface PaymentToolBiz {

	public List list(PaymentToolListForm paymentToolForm) throws AppException;

	public long delete(long id) throws AppException;
	public void deletePaymentTool(Long paymentToolId) throws AppException;

	public long save(PaymentTool paymentTool) throws AppException;

	public long update(PaymentTool paymentTool) throws AppException;

	public PaymentTool getPaymentToolById(long paymentToolId)
			throws AppException;

	public List<PaymentTool> getPaymentToolList() throws AppException;

	public List getPaymentToolListByType(String type) throws AppException;

}
