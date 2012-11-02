package com.kurui.kums.agent.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;

import com.kurui.kums.agent.Agent;
import com.kurui.kums.agent.DirectLevel;
import com.kurui.kums.agent.biz.AgentBiz;
import com.kurui.kums.agent.biz.DirectLevelBiz;
import com.kurui.kums.base.BaseAction;
import com.kurui.kums.base.Constant;
import com.kurui.kums.base.Inform;
import com.kurui.kums.base.KumsDataStoreListener;
import com.kurui.kums.base.MainTask;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.base.util.KumsNoUtil;
import com.kurui.kums.base.util.StringUtil;
import com.kurui.kums.right.UserRightInfo;
import com.kurui.kums.system.biz.SysInitBiz;
import com.kurui.kums.transaction.Company;
import com.kurui.kums.transaction.biz.CompanyBiz;

public class AgentAction extends BaseAction {
	private AgentBiz agentBiz;
	private CompanyBiz companyBiz;
	private DirectLevelBiz directLevelBiz;
	private SysInitBiz sysInitBiz;
	private KumsNoUtil noUtil;

	public ActionForward insert(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		Agent agentForm = (Agent) form;
		Inform inf = new Inform();
		UserRightInfo uri = (UserRightInfo) request.getSession().getAttribute(
				"URI");
		try {
			long companyId = agentForm.getCompanyId();
			if (companyId > 0) {
				Agent agent = new Agent();

				String agentNo = noUtil.getAgentNo();
				agent.setAgentNo(agentNo);
				agent.setCardNo(agentForm.getCardNo());
				agent.setName(agentForm.getName());
				String stampGroup = StringUtil.getStringByArray(agentForm
						.getStampGroupItem(), ",");
				agent.setStampGroup(stampGroup);

				agent.setType(agentForm.getType());
				agent.setLoyalIndex(agentForm.getLoyalIndex());
				agent.setFriendIndex(agentForm.getFriendIndex());
				agent.setAssetIndex(agentForm.getAssetIndex());
				agent.setSpecialIndex(agentForm.getSpecialIndex());
				agent.setTightIndex(agentForm.getTightIndex());

				agent.setStatus(agentForm.getStatus());
				agent.setMemo(agentForm.getMemo());
				agent.setUpdateTime(new Timestamp(System.currentTimeMillis()));
				agent.setUserName(uri.getUser().getUserName());
				Company company = companyBiz.getCompanyById(companyId);
				agent.setCompany(company);
				long directLevelId = agentForm.getDirectLevelId();
				if (directLevelId > 0) {
					DirectLevel directLevel = directLevelBiz
							.getDirectLevelById(directLevelId);
					agent.setDirectLevel(directLevel);
				}

				agent.setPhysicalAmount(Constant.toBigDecimal(agentForm
						.getPhysicalAmount()));
				agent.setVirtualAmount(Constant.toBigDecimal(agentForm
						.getVirtualAmount()));
				agent.setTotalIntegral(Constant.toBigDecimal(agentForm
						.getTotalIntegral()));
				agent.setGrade(Constant.toLong(agentForm.getGrade()));

				agent.setSex(Constant.toLong(agentForm.getSex()));
				agent.setBirthday(agentForm.getBirthday());
				agent.setMarriage(agentForm.getMarriage());
				agent.setLanguage(agentForm.getLanguage());
				agent.setStrongSuit(agentForm.getStrongSuit());
				agent.setAssetInfo(agentForm.getAssetInfo());
				agent.setCreditInfo(agentForm.getCreditInfo());
				agent.setCreditAmount(agentForm.getCreditAmount());
				agent.setHealthInfo(agentForm.getHealthInfo());
				long num = agentBiz.save(agent);
				// --更新静态库
				KumsDataStoreListener listener = new KumsDataStoreListener(sysInitBiz,
						"Agent");
				MainTask.put(listener);
				//
				if (num > 0) {
					if (agentForm.getAddType().equals("Speed")) {
						// System.out.println("agentNo:--------"+agent.getAgentNo());
						request.setAttribute("agentId", agent.getId());
						request.setAttribute("agentNo", agent.getAgentNo()
								+ "|" + agent.getName());
						forwardPage = "viewAgentSpeed";
						return (mapping.findForward(forwardPage));
					} else {
						return redirect(agent);
					}
				} else {
					inf.setMessage("您添加客户数据异常！");
				}
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
		Agent agentForm = (Agent) form;
		Inform inf = new Inform();
		UserRightInfo uri = (UserRightInfo) request.getSession().getAttribute(
				"URI");
		try {
			if (agentForm.getId() > 0) {
				long companyId = agentForm.getCompanyId();
				if (companyId > 0) {
					Agent agent = agentBiz.getAgentById(agentForm.getId());

					if ("".equals(Constant.toString(agent.getAgentNo()))) {
						agent.setAgentNo(noUtil.getAgentNo());
					}

					agent.setCardNo(agentForm.getCardNo());
					agent.setName(agentForm.getName());
					String stampGroup = StringUtil.getStringByArray(agentForm
							.getStampGroupItem(), ",");
					agent.setStampGroup(stampGroup);

					agent.setType(agentForm.getType());
					agent.setLoyalIndex(agentForm.getLoyalIndex());
					agent.setFriendIndex(agentForm.getFriendIndex());
					agent.setAssetIndex(agentForm.getAssetIndex());
					agent.setSpecialIndex(agentForm.getSpecialIndex());
					agent.setTightIndex(agentForm.getTightIndex());

					agent.setStatus(agentForm.getStatus());
					agent.setMemo(agentForm.getMemo());
					agent.setUpdateTime(new Timestamp(System
							.currentTimeMillis()));
					agent.setUserName(uri.getUser().getUserName());

					Company company = companyBiz.getCompanyById(companyId);
					agent.setCompany(company);
					long directLevelId = agentForm.getDirectLevelId();
					if (directLevelId > 0) {
						DirectLevel directLevel = directLevelBiz
								.getDirectLevelById(directLevelId);
						agent.setDirectLevel(directLevel);
					}
					agent.setPhysicalAmount(Constant.toBigDecimal(agentForm
							.getPhysicalAmount()));
					agent.setVirtualAmount(Constant.toBigDecimal(agentForm
							.getVirtualAmount()));
					agent.setTotalIntegral(Constant.toBigDecimal(agentForm
							.getTotalIntegral()));
					agent.setGrade(Constant.toLong(agentForm.getGrade()));

					agent.setSex(Constant.toLong(agentForm.getSex()));
					agent.setBirthday(agentForm.getBirthday());
					agent.setMarriage(agentForm.getMarriage());
					agent.setLanguage(agentForm.getLanguage());
					agent.setStrongSuit(agentForm.getStrongSuit());
					agent.setAssetInfo(agentForm.getAssetInfo());
					agent.setCreditInfo(agentForm.getCreditInfo());
					agent.setCreditAmount(agentForm.getCreditAmount());
					agent.setHealthInfo(agentForm.getHealthInfo());
					long flag = agentBiz.update(agent);
					// --更新静态库
					KumsDataStoreListener listener = new KumsDataStoreListener(
							sysInitBiz, "Agent");
					MainTask.put(listener);
					//
					if (flag > 0) {
						return redirectList();
					} else {
						inf.setMessage("修改客户数据异常!");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			inf.setMessage("异常:" + e.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}

	/**
	 * 更新客户评级信息
	 * */
	public ActionForward updateGrade(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		Agent agentForm = (Agent) form;
		Inform inf = new Inform();
		try {
			if (agentForm.getId() > 0) {
				Agent agent = agentBiz.getAgentById(agentForm.getId());

				agent.setType(agentForm.getType());
				agent.setLoyalIndex(agentForm.getLoyalIndex());
				agent.setFriendIndex(agentForm.getFriendIndex());
				agent.setAssetIndex(agentForm.getAssetIndex());
				agent.setSpecialIndex(agentForm.getSpecialIndex());
				agent.setTightIndex(agentForm.getTightIndex());

				agent.setUpdateTime(new Timestamp(System.currentTimeMillis()));

				long flag = agentBiz.update(agent);

				if (flag > 0) {
					return redirectListGrade();
				} else {
					inf.setMessage("修改客户数据异常!");
				}
			} else {
				inf.setMessage("缺少AgentId");
			}
		} catch (Exception e) {
			e.printStackTrace();
			inf.setMessage("异常:" + e.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}
	
	/**
	 * 更新客户通讯录信息
	 * */
	public ActionForward updateAddress(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		Agent agentForm = (Agent) form;
		Inform inf = new Inform();
		try {
			if (agentForm.getId() > 0) {
				Agent agent = agentBiz.getAgentById(agentForm.getId());


				agent.setUpdateTime(new Timestamp(System.currentTimeMillis()));

				long flag = agentBiz.update(agent);

				if (flag > 0) {
					return redirectListGrade();
				} else {
					inf.setMessage("修改客户通讯信息异常!");
				}
			} else {
				inf.setMessage("缺少AgentId");
			}
		} catch (Exception e) {
			e.printStackTrace();
			inf.setMessage("异常:" + e.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}


	// 更新统计数据
	public ActionForward updateStatistic(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws AppException {
		Agent agentForm = (Agent) form;
		Inform inf = new Inform();
		UserRightInfo uri = (UserRightInfo) request.getSession().getAttribute(
				"URI");
		try {
			long agentId = agentForm.getId();
			if (agentId > 0) {
				agentBiz.updateStatistic(agentId);

				return redirect(agentForm);
			}
		} catch (Exception e) {
			e.printStackTrace();
			inf.setMessage("异常:" + e.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}

	public ActionForward redirect(Agent agent) throws AppException {
		return new ActionRedirect("/agent/agentList.do?thisAction=view&id="
				+ agent.getId());
	}

	public ActionForward redirectList() throws AppException {
		ActionRedirect redirect = new ActionRedirect(
				"/agent/agentList.do?thisAction=list");
		return redirect;
	}

	public ActionForward redirectListGrade() throws AppException {
		ActionRedirect redirect = new ActionRedirect(
				"/agent/agentList.do?thisAction=listGrade");
		return redirect;
	}

	public void setSysInitBiz(SysInitBiz sysInitBiz) {
		this.sysInitBiz = sysInitBiz;
	}

	public void setAgentBiz(AgentBiz agentBiz) {
		this.agentBiz = agentBiz;
	}

	public void setCompanyBiz(CompanyBiz companyBiz) {
		this.companyBiz = companyBiz;
	}

	public void setDirectLevelBiz(DirectLevelBiz directLevelBiz) {
		this.directLevelBiz = directLevelBiz;
	}

	public void setNoUtil(KumsNoUtil noUtil) {
		this.noUtil = noUtil;
	}
}