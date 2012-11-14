package com.kurui.kums.agent.action;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.kurui.kums.base.struts.BaseAction;
import com.kurui.kums.base.ui.inform.Inform;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.base.Constant;
import com.kurui.kums.agent.Agent;
import com.kurui.kums.agent.Vehicle;
import com.kurui.kums.agent.VehicleListForm;
import com.kurui.kums.agent.biz.AgentBiz;
import com.kurui.kums.agent.biz.VehicleBiz;

public class VehicleListAction extends BaseAction {
	private AgentBiz agentBiz;
	private VehicleBiz vehicleBiz;

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		VehicleListForm vehicleListForm = (VehicleListForm) form;
		if (vehicleListForm == null) {
			vehicleListForm = new VehicleListForm();
		}
		try {
			List<Vehicle> vehicleList = vehicleBiz
					.list(vehicleListForm);
			vehicleListForm.setList(vehicleList);

			request.setAttribute("vehicleListForm", vehicleListForm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("vehicleListForm", vehicleListForm);
		return mapping.findForward("listVehicle");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		VehicleListForm vehicleListForm = (VehicleListForm) form;
		try {
			long vehicleId = Constant.toLong(vehicleListForm.getId());
			Vehicle vehicle = vehicleBiz
					.getVehicleById(vehicleId);
			request.setAttribute("vehicle", vehicle);

		} catch (Exception e) {
			e.printStackTrace();
		}
		forwardPage = "viewVehicle";
		return mapping.findForward(forwardPage);
	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		VehicleListForm alf = (VehicleListForm) form;
		Vehicle vehicle = new Vehicle();

		long agentId = alf.getAgentId();
		if (agentId > 0) {
			Agent agent = agentBiz.getAgentById(agentId);
			vehicle.setAgent(agent);
		}

		vehicle.setThisAction("insert");
		request.setAttribute("vehicle", vehicle);

		String forwardPage = "editVehicle";
		return mapping.findForward(forwardPage);
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		Inform inf = new Inform();
		VehicleListForm vehicleListForm = (VehicleListForm) form;

		long vehicleId = vehicleListForm.getId();
		if (vehicleId < 1) {
			vehicleId = vehicleListForm.getSelectedItems()[0];
		}

		if (vehicleId > 0) {
			Vehicle vehicle = vehicleBiz
					.getVehicleById(vehicleId);
			vehicle.setThisAction("update");

			request.setAttribute("vehicle", vehicle);
		} else {
			inf.setMessage("缺少vehicleId");
			return forwardInformPage(inf, mapping, request);
		}
		return mapping.findForward("editVehicle");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		VehicleListForm vehicleListForm = (VehicleListForm) form;
		String forwardPage = "";
		long id = 0;
		Inform inf = new Inform();
		int message = 0;
		try {
			for (int i = 0; i < vehicleListForm.getSelectedItems().length; i++) {
				id = vehicleListForm.getSelectedItems()[i];
				if (id > 0) {
					vehicleBiz.deleteVehicle(id);
				}
			}
			return list(mapping, form, request, response);
		} catch (Exception ex) {
			ex.printStackTrace();
			inf.setMessage("删除失败" + ex.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}

	public void setAgentBiz(AgentBiz agentBiz) {
		this.agentBiz = agentBiz;
	}

	public void setVehicleBiz(VehicleBiz vehicleBiz) {
		this.vehicleBiz = vehicleBiz;
	}

}