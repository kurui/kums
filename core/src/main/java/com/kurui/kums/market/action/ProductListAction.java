package com.kurui.kums.market.action;

import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.kurui.kums.base.struts.BaseAction;
import com.kurui.kums.base.ui.inform.Inform;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.market.Product;
import com.kurui.kums.market.ProductListForm;
import com.kurui.kums.market.biz.ProductBiz;

public class ProductListAction extends BaseAction {
	private ProductBiz productBiz;
	
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		ProductListForm productListForm = (ProductListForm) form;
		if (productListForm == null) {
			productListForm = new ProductListForm();
		}
		try {
			productListForm.setList(productBiz
					.list(productListForm));
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("product", productListForm);
		return mapping.findForward("listProduct");
	}
	
	/***************************************************************************
	 * 选择产品
	 **************************************************************************/
	public ActionForward selectFinanceProduct(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws AppException {
		String forwardPage = "";
		ProductListForm productListForm = (ProductListForm) form;
		productListForm.setList(productBiz.list(productListForm));
		productListForm.setThisAction("selectFinanceProduct");
		
		request.setAttribute("productListForm", productListForm);
//		System.out.println("rowId:"+productListForm.getRowId());
		forwardPage = "listProductSelect";
		return (mapping.findForward(forwardPage));
	}
	public static void main(String[] args) {
		String aa="5,8，";
		aa=aa.substring(0,aa.lastIndexOf(","));
		System.out.println(aa);
	}

	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		try {
			String productId = request.getParameter("id");
			Product product = productBiz
					.getProductById(Long.parseLong(productId));
			request.setAttribute("product", product);
		} catch (Exception e) {
			e.printStackTrace();
		}
		forwardPage = "viewProduct";
		return mapping.findForward(forwardPage);

	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		Product product = new Product();
		product.setProportion(BigDecimal.ONE);
		product.setClassType(Product.CLASS_TYPE_1);
		product.setType(Product.TYPE_1);
		product.setThisAction("insert");
		request.setAttribute("product", product);
		String forwardPage = "editProduct";
		return mapping.findForward(forwardPage);
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		ProductListForm productListForm = (ProductListForm) form;
		
		long productId = productListForm.getId();
		if (productId < 1) {
			productId = productListForm.getSelectedItems()[0];
		}
		
		if (productId > 0) {
			Product product = productBiz
					.getProductById(productId);
			product.setThisAction("update");
			request.setAttribute("product", product);
		} else {
			request.setAttribute("product", new Product());
		}
		return mapping.findForward("editProduct");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		ProductListForm productListForm = (ProductListForm) form;
		// String forwardPage = "";
		long id = 0;
		Inform inf = new Inform();
		try {
			for (int i = 0; i < productListForm.getSelectedItems().length; i++) {
				id = productListForm.getSelectedItems()[i];
				productBiz.deleteProduct(id);
			}
			return list(mapping, form, request, response);
		} catch (Exception ex) {
			inf.setMessage("删除失败" + ex.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}

	public void setProductBiz(ProductBiz productBiz) {
		this.productBiz = productBiz;
	}
}