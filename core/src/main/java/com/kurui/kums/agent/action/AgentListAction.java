package com.kurui.kums.agent.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.kurui.kums.agent.Agent;
import com.kurui.kums.agent.AgentListForm;
import com.kurui.kums.agent.biz.AgentBiz;
import com.kurui.kums.agent.biz.DirectLevelBiz;
import com.kurui.kums.base.Constant;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.base.external.service.sms.SMUtil;
import com.kurui.kums.base.struts.BaseAction;
import com.kurui.kums.base.ui.inform.Inform;
import com.kurui.kums.base.util.RegularUtil;
import com.kurui.kums.finance.FinanceOrder;
import com.kurui.kums.finance.biz.FinanceOrderBiz;
import com.kurui.kums.library.util.DataTypeStore;
import com.kurui.kums.library.util.PlatComAccountStore;
import com.kurui.kums.report.biz.AgentReportBiz;

public class AgentListAction extends BaseAction {
	private AgentBiz agentBiz;
	private AgentReportBiz agentReportBiz;
	private DirectLevelBiz directLevelBiz;	
	private FinanceOrderBiz financeOrderBiz;

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		AgentListForm agentListForm = (AgentListForm) form;
		request.setAttribute("companyList", PlatComAccountStore
				.getGroupCompnayList());
		if (agentListForm == null) {
			agentListForm = new AgentListForm();
		}
		try {
			List<Agent> formAgentList = agentBiz.list(agentListForm);
			agentListForm.setList(formAgentList);
			request = setAgentIdList(formAgentList, request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("agentListForm", agentListForm);
		return mapping.findForward("listAgent");
	}
	
	public ActionForward viewAgentReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		AgentListForm agentListForm = (AgentListForm) form;
		if (agentListForm == null) {
			agentListForm = new AgentListForm();
		}
		try {
			request = agentReportBiz.viewReport(agentListForm,request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("viewAgentReport");
	}

	/**
	 * 通讯录
	 */
	public ActionForward listAddress(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		AgentListForm agentListForm = (AgentListForm) form;
		request.setAttribute("companyList", PlatComAccountStore
				.getGroupCompnayList());
		if (agentListForm == null) {
			agentListForm = new AgentListForm();
		}
		try {
			List<Agent> formAgentList = agentBiz.list(agentListForm);
			agentListForm.setList(formAgentList);
			request = setAgentIdList(formAgentList, request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("agentListForm", agentListForm);
		return mapping.findForward("listAddress");
	}

	/**
	 * 客户评级
	 */
	public ActionForward listGrade(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		AgentListForm agentListForm = (AgentListForm) form;
		request.setAttribute("companyList", PlatComAccountStore
				.getGroupCompnayList());
		if (agentListForm == null) {
			agentListForm = new AgentListForm();
		}
		try {
			List<Agent> formAgentList = agentBiz.listGrade(agentListForm);
			agentListForm.setList(formAgentList);
			request = setAgentIdList(formAgentList, request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("agentListForm", agentListForm);
		loadData(request);
		return mapping.findForward("listGrade");
	}

	public ActionForward listDirectAgent(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws AppException {
		AgentListForm agentListForm = (AgentListForm) form;
		request.setAttribute("companyList", PlatComAccountStore
				.getGroupCompnayList());
		if (agentListForm == null) {
			agentListForm = new AgentListForm();
		}
		try {
			List<Agent> formAgentList = agentBiz.listDirect(agentListForm);
			agentListForm.setList(formAgentList);
			request = setAgentIdList(formAgentList, request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("agentListForm", agentListForm);
		return mapping.findForward("listDirectAgent");
	}

	private HttpServletRequest setAgentIdList(List<Agent> formAgentList,
			HttpServletRequest request) throws AppException {
		List<Agent> agentList = agentBiz.getAgentList();
		List<Long> agentIdList = new ArrayList<Long>();

		for (Agent ag : agentList) {
			agentIdList.add(ag.getId());
		}
		for (Agent ag : formAgentList) {
			agentIdList.remove(agentIdList.indexOf(ag.getId()));
		}

		request.setAttribute("agentIdList", agentIdList);
		return request;
	}

	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		AgentListForm agentListForm = (AgentListForm) form;
		try {
			long agentId = Constant.toLong(agentListForm.getId());
			Agent agent = agentBiz.getAgentById(agentId);
			request.setAttribute("agent", agent);

			List<FinanceOrder> creditOrderList = financeOrderBiz.listByAgent(
					agentId, FinanceOrder.TRAN_TYPE_GROUP_1300 + ","
							+ FinanceOrder.TRAN_TYPE_GROUP_1400);
			request.setAttribute("creditOrderList", creditOrderList);

			List<FinanceOrder> mainOrderList = financeOrderBiz.listByAgent(
					agentId, FinanceOrder.TRAN_TYPE_GROUP_15);
			request.setAttribute("mainOrderList", mainOrderList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		forwardPage = "viewAgent";
		return mapping.findForward(forwardPage);
	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		AgentListForm alf = (AgentListForm) form;
		Agent agent = new Agent();
		agent.setThisAction("insert");
		agent.setSex(Agent.SEX_1);
		if(alf.getType()!=0){
			agent.setType(alf.getType());
		}else{
			agent.setType(Agent.TYPE_35);
		}
		
		if(alf.getCompanyId()!=0){
			agent.setCompanyId(alf.getCompanyId());
		}else{
			agent.setCompanyId(534);//默认
		}
		

		request.setAttribute("agent", agent);

		loadData(request);

		String forwardPage = "editAgent";
		return mapping.findForward(forwardPage);
	}

	/***************************************************************************
	 * 选择商户
	 **************************************************************************/
	public ActionForward selectFinanceAgent(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws AppException {
		String forwardPage = "";
		AgentListForm agentListForm = (AgentListForm) form;
		agentListForm.setList(agentBiz.list(agentListForm));
		agentListForm.setThisAction("selectFinanceAgent");
		request.setAttribute("agentListForm", agentListForm);
		
		request.setAttribute("companyList", PlatComAccountStore
				.getGroupCompnayList());
		
		forwardPage = "listAgentSelect";
		return (mapping.findForward(forwardPage));
	}

	public ActionForward saveSpeed(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		AgentListForm alf = (AgentListForm) form;
		Agent agent = new Agent();
		request.setAttribute("companyList", PlatComAccountStore
				.getGroupCompnayList());
		agent.setThisAction("insert");
		agent.setAddType("Speed");// 快速建档
		agent.setSex(Agent.SEX_1);
		agent.setType(alf.getType());

		request.setAttribute("agent", agent);
		String forwardPage = "editAgentSpeed";
		return mapping.findForward(forwardPage);
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		Inform inf = new Inform();
		AgentListForm agentListForm = (AgentListForm) form;

		long agentId = agentListForm.getId();
		if (agentId < 1) {
			agentId = agentListForm.getSelectedItems()[0];
		}

		if (agentId > 0) {
			Agent agent = agentBiz.getAgentById(agentId);
			agent.setThisAction("update");
			agent.setCompanyId(agent.getCompany().getId());
			request.setAttribute("agent", agent);
		} else {
			inf.setMessage("缺少AgentId");
			return forwardInformPage(inf, mapping, request);
		}
		loadData(request);
		return mapping.findForward("editAgent");
	}

	private HttpServletRequest loadData(HttpServletRequest request) throws AppException {
		request.setAttribute("companyList", PlatComAccountStore
				.getGroupCompnayList());
		request
				.setAttribute("directLevelList", directLevelBiz.getDirectLevelList());

		request.setAttribute("loyalIndexList", DataTypeStore
				.getSubDataTypeList(5901));
		request.setAttribute("friendIndexList", DataTypeStore
				.getSubDataTypeList(5902));
		request.setAttribute("assetIndexList", DataTypeStore
				.getSubDataTypeList(5903));
		request.setAttribute("specialIndexList", DataTypeStore
				.getSubDataTypeList(5904));
		request.setAttribute("tightIndexList", DataTypeStore
				.getSubDataTypeList(5905));
		return request;
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		AgentListForm agentListForm = (AgentListForm) form;
		String forwardPage = "";
		long id = 0;
		Inform inf = new Inform();
		int message = 0;
		try {
			for (int i = 0; i < agentListForm.getSelectedItems().length; i++) {
				id = agentListForm.getSelectedItems()[i];
				if (id > 0) {
					agentBiz.deleteAgent(id);
				}
			}
			return list(mapping, form, request, response);
		} catch (Exception ex) {
			ex.printStackTrace();
			inf.setMessage("删除失败" + ex.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}

	// 跳转发送短信页面
	public ActionForward sendMessagePage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws AppException {
		AgentListForm agentListForm = (AgentListForm) form;
		int[] idArray = agentListForm.getSelectedItems();
		Agent agent = new Agent();
		StringBuffer mobelArray = new StringBuffer();
//		for (int i = 0; i < idArray.length; i++) {
//			agent = agentBiz.getAgentById(idArray[i]);
//			String name = agent.getName();
//			String mobel = agent.getMobilePhone();
//			if (mobel == null || mobel.trim().length() < 11) { // 号码小于11位
//				if (i == idArray.length - 1
//						&& mobelArray.toString().endsWith(",")) {
//					mobelArray.deleteCharAt(mobelArray.lastIndexOf(","));
//				}
//				continue;
//			}
//			if (i != idArray.length - 1) {
//				if (name != null && !"".equals(name.trim())) {
//					if (name.indexOf("-") != 0) {
//						mobelArray.append(mobel
//								+ "("
//								+ name.substring(name.lastIndexOf("-") + 1)
//										.trim() + ")" + ",");
//					} else {
//						mobelArray
//								.append(mobel + "(" + name.trim() + ")" + ",");
//					}
//				} else {
//					mobelArray.append(mobel + ",");
//				}
//			} else {
//				if (name != null && !"".equals(name.trim())) {
//					if (name.indexOf("-") != 0) {
//						mobelArray.append(mobel
//								+ "("
//								+ name.substring(name.lastIndexOf("-") + 1)
//										.trim() + ")");
//					} else {
//						mobelArray.append(mobel + "(" + name.trim() + ")");
//					}
//				} else {
//					mobelArray.append(mobel);
//				}
//			}
//		}
//		System.out.println(mobelArray.toString());
//		agentListForm.setReceiver(mobelArray.toString());
//		agentListForm.setThisAction("sendMobelMessage");
//		request.setAttribute("agentListForm", agentListForm);
		String forwardPage = "sendMessage";
		return mapping.findForward(forwardPage);
	}

	// 发送短信
	public ActionForward sendMobelMessage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws AppException {
		boolean flag = true;
		String forwardPage = "";
		Inform inf = new Inform();
		AgentListForm agentListForm = (AgentListForm) form;
		String content = agentListForm.getContent();
		String receiver = agentListForm.getReceiver();
		System.out.println("短信内容：" + content);
		System.out.println("接收人号码集合：：" + receiver);
		String[] mobel = receiver.split("[,，]");
		for (int i = 0; i < mobel.length; i++) {
			mobel[i] = mobel[i].substring(mobel[i].indexOf("1"), mobel[i]
					.indexOf("1") + 11);
			if (!RegularUtil.isMobel(mobel[i])) {
				flag = false;
				break;
			}
		}
		if (flag) {
			for (int i = 0; i < mobel.length; i++) {
				mobel[i] = mobel[i].substring(mobel[i].indexOf("1"), mobel[i]
						.indexOf("1") + 11);
				SMUtil.send(mobel[i], content);
			}
			inf.setMessage("短信已成功发送");
		} else {
			inf.setMessage("短信发送失败");
		}
		inf.setForwardPage("/agent/agentList.do");
		inf.setParamId("thisAction");
		inf.setParamValue("list");

		return forwardInformPage(inf, mapping, request);
	}

	public void setAgentBiz(AgentBiz agentBiz) {
		this.agentBiz = agentBiz;
	}
	
	

	public void setDirectLevelBiz(DirectLevelBiz directLevelBiz) {
		this.directLevelBiz = directLevelBiz;
	}

	public void setAgentReportBiz(AgentReportBiz agentReportBiz) {
		this.agentReportBiz = agentReportBiz;
	}

	public void setFinanceOrderBiz(FinanceOrderBiz financeOrderBiz) {
		this.financeOrderBiz = financeOrderBiz;
	}

}