package com.kurui.kums.transaction.dao;

import java.util.List;

import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.transaction.PaymentTool;
import com.kurui.kums.transaction.PaymentToolListForm;

public interface PaymentToolDAO {

	public List list(PaymentToolListForm paymentToolForm) throws AppException;

	public void delete(long id) throws AppException;

	public long save(PaymentTool paymentTool) throws AppException;

	public long update(PaymentTool paymentTool) throws AppException;

	public PaymentTool getPaymentToolById(long paymentToolId) throws AppException;

	public List<PaymentTool> getPaymentToolList() throws AppException;

	public List<PaymentTool> getValidPaymentToolList() throws AppException;

	public List getPaymentToolListByType(String type) throws AppException;
}
