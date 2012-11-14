package com.kurui.kums.agent.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;
import com.kurui.kums.agent.Agent;
import com.kurui.kums.agent.Vehicle;
import com.kurui.kums.agent.biz.AgentBiz;
import com.kurui.kums.agent.biz.VehicleBiz;
import com.kurui.kums.base.struts.BaseAction;
import com.kurui.kums.base.ui.inform.Inform;
import com.kurui.kums.base.exception.AppException;

public class VehicleAction extends BaseAction {
	private AgentBiz agentBiz;
	private VehicleBiz vehicleBiz;

	public ActionForward insert(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		Vehicle vehicleForm = (Vehicle) form;
		Inform inf = new Inform();

		try {
			long agentId = vehicleForm.getAgentId();

			if (agentId > 0) {
				Agent agent = agentBiz.getAgentById(agentId);
				if (agent != null) {

					Vehicle vehicle = new Vehicle();
					vehicle.setAgent(agent);
					vehicle.setCarNo(vehicleForm.getCarNo());
					vehicle.setCarType(vehicleForm.getCarType());
					vehicle.setEngineNo(vehicleForm.getEngineNo());
					vehicle.setCarcaseNo(vehicleForm.getCarcaseNo());
					vehicle.setSeatCount(vehicleForm.getSeatCount());
					vehicle.setColor(vehicleForm.getColor());

					vehicle.setMemo(vehicleForm.getMemo());

					vehicle.setType(Vehicle.TYPE_1);
					vehicle.setStatus(Vehicle.STATES_1);

					long newVehicleId = vehicleBiz.save(vehicle);

					if (newVehicleId > 0) {
						return redirect(vehicle);
					} else {
						inf.setMessage("保存车辆异常");
					}
				} else {
					inf.setMessage("客户不能为空");
				}
			} else {
				inf.setMessage("客户ID不能为空");
			}
		} catch (Exception e) {
			e.printStackTrace();
			inf.setMessage("增加客户账号：" + e.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}

	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		Vehicle vehicleForm = (Vehicle) form;
		Inform inf = new Inform();
		try {
			long vehicleId = vehicleForm.getId();
			if (vehicleId > 0) {
				Vehicle vehicle = vehicleBiz.getVehicleById(vehicleId);

				long agentId = vehicleForm.getAgentId();
				if (agentId > 0) {
					Agent agent = agentBiz.getAgentById(agentId);
					if (agent != null) {
						vehicle.setAgent(agent);
						vehicle.setCarNo(vehicleForm.getCarNo());
						vehicle.setCarType(vehicleForm.getCarType());
						vehicle.setEngineNo(vehicleForm.getEngineNo());
						vehicle.setCarcaseNo(vehicleForm.getCarcaseNo());
						vehicle.setSeatCount(vehicleForm.getSeatCount());
						vehicle.setColor(vehicleForm.getColor());

						vehicle.setMemo(vehicleForm.getMemo());

						vehicle.setType(Vehicle.TYPE_1);
						vehicle.setStatus(Vehicle.STATES_1);
						vehicle.setStatus(vehicleForm.getStatus());
						long flag = vehicleBiz.update(vehicle);

						if (flag > 0) {
							return redirect(vehicle);
						} else {
							inf.setMessage("修改车辆数据异常!");
						}
					} else {
						inf.setMessage("缺少agent");
					}
				} else {
					inf.setMessage("缺少agentId");
				}
			} else {
				inf.setMessage("缺少vehicleId");
			}
		} catch (Exception e) {
			e.printStackTrace();
			inf.setMessage("异常:" + e.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}

	public ActionRedirect redirect(Vehicle vehicle) {
		ActionRedirect redirect = new ActionRedirect("/agent/vehicleList.do");
		redirect.addParameter("thisAction", "view");
		redirect.addParameter("id", vehicle.getId());
		return redirect;
	}

	public void setAgentBiz(AgentBiz agentBiz) {
		this.agentBiz = agentBiz;
	}

	public void setVehicleBiz(VehicleBiz vehicleBiz) {
		this.vehicleBiz = vehicleBiz;
	}

}