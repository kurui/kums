package com.kurui.kums.agent.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;

import com.kurui.kums.agent.Agent;
import com.kurui.kums.agent.AgentHabit;
import com.kurui.kums.agent.biz.AgentBiz;
import com.kurui.kums.agent.biz.AgentHabitBiz;
import com.kurui.kums.base.struts.BaseAction;
import com.kurui.kums.base.ui.inform.Inform;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.right.UserRightInfo;

public class AgentHabitAction extends BaseAction {
	private AgentBiz agentBiz;
	private AgentHabitBiz agentHabitBiz;

	public ActionForward insert(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		AgentHabit agentHabitForm = (AgentHabit) form;
		Inform inf = new Inform();
		UserRightInfo uri = (UserRightInfo) request.getSession().getAttribute(
				"URI");
		try {
			long agentId = agentHabitForm.getAgentId();
			if (agentId > 0) {
				Agent agent = agentBiz.getAgentById(agentId);
				if (agent != null) {
					AgentHabit agentHabit = new AgentHabit();
					agentHabit.setAmuse(agentHabitForm.getAmuse());
					agentHabit.setReading(agentHabitForm.getReading());
					agentHabit
							.setAirplaneSeat(agentHabitForm.getAirplaneSeat());
					agentHabit.setTravelAppoint(agentHabitForm
							.getTravelAppoint());
					agentHabit.setDrink(agentHabitForm.getDrink());
					agentHabit.setFood(agentHabitForm.getFood());
					agentHabit.setFlower(agentHabitForm.getFlower());
					agentHabit
							.setScaredAnimal(agentHabitForm.getScaredAnimal());
					agentHabit.setBreedAnimal(agentHabitForm.getBreedAnimal());
					agentHabit.setFilmType(agentHabitForm.getFilmType());
					agentHabit.setSports(agentHabitForm.getSports());
					agentHabit.setOppositeSex(agentHabitForm.getOppositeSex());
					agentHabit.setHomoSex(agentHabitForm.getHomoSex());
					agentHabit.setBigWish(agentHabitForm.getBigWish());
					agentHabit.setReligion(agentHabitForm.getReligion());
					agentHabit.setMemo(agentHabitForm.getMemo());

					agentHabit.setStatus(agentHabitForm.getStatus());
					agentHabit.setUpdateTime(new Timestamp(System
							.currentTimeMillis()));
					long num = agentHabitBiz.save(agentHabit);

					agent.setAgentHabit(agentHabit);
					
					agentBiz.update(agent);

					if (num > 0) {
						ActionRedirect redirect = new ActionRedirect(
								"/agent/agentHabitList.do?thisAction=list");
						return redirect;
					} else {
						inf.setMessage("您添加客户数据异常！");
					}
				} else {
					inf.setMessage("异常:Agent为空");
				}
			} else {
				inf.setMessage("异常:AgentId为空");
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
		AgentHabit agentHabitForm = (AgentHabit) form;
		Inform inf = new Inform();
		UserRightInfo uri = (UserRightInfo) request.getSession().getAttribute(
				"URI");
		try {
			long habitId = agentHabitForm.getId();
			if (habitId > 0) {
				AgentHabit agentHabit = agentHabitBiz
						.getAgentHabitById(habitId);

				agentHabit.setAmuse(agentHabitForm.getAmuse());
				agentHabit.setReading(agentHabitForm.getReading());
				agentHabit.setAirplaneSeat(agentHabitForm.getAirplaneSeat());
				agentHabit.setTravelAppoint(agentHabitForm.getTravelAppoint());
				agentHabit.setDrink(agentHabitForm.getDrink());
				agentHabit.setFlower(agentHabitForm.getFlower());
				agentHabit.setFood(agentHabitForm.getFood());
				agentHabit.setScaredAnimal(agentHabitForm.getScaredAnimal());
				agentHabit.setBreedAnimal(agentHabitForm.getBreedAnimal());
				agentHabit.setFilmType(agentHabitForm.getFilmType());
				agentHabit.setSports(agentHabitForm.getSports());
				agentHabit.setOppositeSex(agentHabitForm.getOppositeSex());
				agentHabit.setHomoSex(agentHabitForm.getHomoSex());
				agentHabit.setBigWish(agentHabitForm.getBigWish());
				agentHabit.setReligion(agentHabitForm.getReligion());
				agentHabit.setMemo(agentHabitForm.getMemo());

				agentHabit.setStatus(agentHabitForm.getStatus());
				agentHabit.setUpdateTime(new Timestamp(System
						.currentTimeMillis()));
				long flag = agentHabitBiz.update(agentHabit);

				if (flag > 0) {
					return new ActionRedirect(
							"/agent/agentHabitList.do?thisAction=list");
				} else {
					inf.setMessage("修改客户数据异常!");
				}
			} else {
				inf.setMessage("缺少directLevelId");
			}
		} catch (Exception e) {
			e.printStackTrace();
			inf.setMessage("异常:" + e.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}

	public void setAgentBiz(AgentBiz agentBiz) {
		this.agentBiz = agentBiz;
	}

	public void setAgentHabitBiz(AgentHabitBiz agentHabitBiz) {
		this.agentHabitBiz = agentHabitBiz;
	}

}