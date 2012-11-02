//package com.kurui.kums.finance.test;
//
//import java.util.List;
//
//import junit.framework.TestCase;
//
//import com.kurui.kums.base.exception.AppException;
//import com.kurui.kums.finance.AssetsItemListForm;
//import com.kurui.kums.finance.biz.AssetsItemBiz;
//import com.kurui.kums.test.commons.ApplicationContextFactory;
//
//
///**
// * @author Yanrui
// * 
// * 
// * 
// * */
//public class AssetsItemBizTest extends TestCase {
//
//	private com.kurui.kums.finance.biz.AssetsItemBiz assetsItemBiz;
//
//	static {
//		ApplicationContextFactory.init("F:\\project\\kums\\war\\war\\WEB-INF\\applicationContext.xml");
//	}
//
//	public AssetsItemBizTest(String name) {
//		super(name);
//	}
//
//	protected void setUp() throws Exception {
//		super.setUp();
//		
//		assetsItemBiz = (AssetsItemBiz) ApplicationContextFactory.getApplicationContext()
//				.getBean("assetsItemBiz");
//
//	}
//
//	public void testListAssetsItem() {
//		try {
//			AssetsItemListForm assetsItemListForm =new AssetsItemListForm();
//			List assetsItemList = assetsItemBiz.listSTA(assetsItemListForm);
//			assertNotNull(assetsItemList);
//			System.out.println("assetsItemList size:"+assetsItemList.size());
//		} catch (AppException e) {
//			e.printStackTrace();
//		}
//
//	}
//	
//
//}
