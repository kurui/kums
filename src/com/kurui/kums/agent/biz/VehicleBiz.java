package com.kurui.kums.agent.biz;

import java.util.List;

import com.kurui.kums.agent.Vehicle;
import com.kurui.kums.agent.VehicleListForm;
import com.kurui.kums.base.exception.AppException;

public interface VehicleBiz {

	public List list(VehicleListForm vehicleListForm) throws AppException;

	public long delete(long id) throws AppException;

	public void deleteVehicle(Long id) throws AppException;

	public long save(Vehicle vehicle) throws AppException;

	public long update(Vehicle vehicle) throws AppException;

	public Vehicle getVehicleById(long id) throws AppException;

	public List<Vehicle> getVehicleList(Long type) throws AppException;

}
