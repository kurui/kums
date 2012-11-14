package com.kurui.kums.market.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;

import com.kurui.kums.base.struts.BaseAction;
import com.kurui.kums.base.ui.inform.Inform;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.base.Constant;
import com.kurui.kums.base.util.KumsNoUtil;
import com.kurui.kums.market.Product;
import com.kurui.kums.market.biz.ProductBiz;
import com.kurui.kums.right.UserRightInfo;

public class ProductAction extends BaseAction {
	private ProductBiz productBiz;
	private KumsNoUtil noUtil;

	public ActionForward insert(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		Product productForm = (Product) form;
		Inform inf = new Inform();
		UserRightInfo uri = (UserRightInfo) request.getSession().getAttribute(
				"URI");
		try {
			Product product = new Product();

			String no = Constant.toUpperCase(productForm.getNo());
			if (no == null || no == "") {
				no = noUtil.getProductNo();
			}
			product.setNo(no);
			product.setName(productForm.getName());
			product.setProductType(productForm.getProductType());
			product.setPrice(productForm.getPrice());
			product.setTradePrice(productForm.getTradePrice());
			product.setPurchasePrice(productForm.getPurchasePrice());
			product.setStockCount(productForm.getStockCount());
			product.setClassType(productForm.getClassType());
			product.setStandard(productForm.getStandard());
			product.setProportion(productForm.getProportion());
			product.setMemo(productForm.getMemo());
			product.setType(productForm.getType());
			product.setStatus(productForm.getStatus());
			long num = productBiz.save(product);

			if (num > 0) {
				return redirectList(product);
			} else {
				inf.setMessage("您添加数据失败！");
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
		Product productForm = (Product) form;
		Inform inf = new Inform();
		UserRightInfo uri = (UserRightInfo) request.getSession().getAttribute(
				"URI");
		try {
			if (productForm.getId() > 0) {
				Product product = productBiz
						.getProductById(productForm.getId());
				product.setName(productForm.getName());
				product.setProductType(productForm.getProductType());
				product.setPrice(productForm.getPrice());
				product.setTradePrice(productForm.getTradePrice());
				product.setPurchasePrice(productForm.getPurchasePrice());
				product.setStockCount(productForm.getStockCount());
				product.setClassType(productForm.getClassType());
				product.setStandard(productForm.getStandard());
				product.setProportion(productForm.getProportion());
				product.setMemo(productForm.getMemo());
				product.setType(productForm.getType());
				product.setStatus(productForm.getStatus());

				long flag = productBiz.update(product);

				if (flag > 0) {
					return redirectList(product);
				} else {
					inf.setMessage("您修改数据失败！");
				}
			} else {
				inf.setMessage("缺少ID");
			}
		} catch (Exception e) {
			e.printStackTrace();
			inf.setMessage("异常:" + e.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}

	public ActionForward redirectList(Product product) {
		ActionRedirect redirect = new ActionRedirect("/market/productList.do");
		redirect.addParameter("thisAction", "list");
		if (product != null) {
			redirect.addParameter("type", product.getType());
		}
		return redirect;
	}

	public void setNoUtil(KumsNoUtil noUtil) {
		this.noUtil = noUtil;
	}

	public void setProductBiz(ProductBiz productBiz) {
		this.productBiz = productBiz;
	}
}