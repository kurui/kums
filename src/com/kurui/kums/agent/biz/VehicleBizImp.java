package com.kurui.kums.agent.biz;

import java.util.List;

import com.kurui.kums.agent.biz.VehicleBiz;
import com.kurui.kums.agent.Vehicle;
import com.kurui.kums.agent.VehicleListForm;
import com.kurui.kums.agent.dao.VehicleDAO;
import com.kurui.kums.base.exception.AppException;

public class VehicleBizImp implements VehicleBiz {
	private VehicleDAO vehicleDAO;

	public List list(VehicleListForm vehicleListForm) throws AppException {
		return vehicleDAO.list(vehicleListForm);
	}

	public long delete(long id) throws AppException {
		try {
			vehicleDAO.delete(id);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public void deleteVehicle(Long id) throws AppException {
		Vehicle vehicle = getVehicleById(id);
		vehicle.setStatus(Vehicle.STATES_0);// 将状态变为无效
		update(vehicle);
	}

	public long save(Vehicle vehicle) throws AppException {
		return vehicleDAO.save(vehicle);
	}

	public long update(Vehicle vehicle) throws AppException {
		return vehicleDAO.update(vehicle);
	}

	public Vehicle getVehicleById(long id) throws AppException {
		return vehicleDAO.getVehicleById(id);
	}

	public List<Vehicle> getVehicleList(Long type) throws AppException {
		return vehicleDAO.getVehicleList(type);
	}

	public void setVehicleDAO(VehicleDAO vehicleDAO) {
		this.vehicleDAO = vehicleDAO;
	}
}
