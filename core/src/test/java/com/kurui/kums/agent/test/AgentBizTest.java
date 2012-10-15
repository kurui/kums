//package com.kurui.kums.agent.test;
//
//import java.util.List;
//
//import junit.framework.TestCase;
//
//import com.kurui.kums.agent.Agent;
//import com.kurui.kums.agent.biz.AgentBiz;
//import com.kurui.kums.agent.dao.AgentDAO;
//import com.kurui.kums.base.exception.AppException;
//import com.kurui.kums.test.commons.ApplicationContextFactory;
//
//
///**
// * @author Yanrui
// * 
// * 
// * 
// * */
//public class AgentBizTest extends TestCase {
//
//	private AgentDAO agentDAO;//项目设定*Biz 为事务提交，如需直接运行测试DAO，要在application-context.xml 加 *DAO
//	private AgentBiz agentBiz;
//
//	static {
//		ApplicationContextFactory.init("F:\\project\\kums\\war\\war\\WEB-INF\\applicationContext.xml");
//	}
//
//	public AgentBizTest(String name) {
//		super(name);
//	}
//
//	protected void setUp() throws Exception {
//		super.setUp();
//		agentDAO = (AgentDAO) ApplicationContextFactory.getApplicationContext()
//				.getBean("agentDAO");
//		
//		agentBiz = (AgentBiz) ApplicationContextFactory.getApplicationContext()
//				.getBean("agentBiz");
//
//	}
//
//	public void testListAgent() {
//		try {
//			List<Agent> agentList = agentBiz.getAgentList();
//			assertNotNull(agentList);
//			System.out.println("agentList size:"+agentList.size());
//		} catch (AppException e) {
//			e.printStackTrace();
//		}
//
//	}
//	
//
//}
