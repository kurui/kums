package com.kurui.kums.agent.dao;

import java.util.List;

import com.kurui.kums.agent.Vehicle;
import com.kurui.kums.agent.VehicleListForm;
import com.kurui.kums.base.exception.AppException;

public interface VehicleDAO {
	public List list(VehicleListForm agentListForm) throws AppException;

	public void delete(long id) throws AppException;

	public long save(Vehicle vehicle) throws AppException;

	public long update(Vehicle vehicle) throws AppException;

	public Vehicle getVehicleById(long id) throws AppException;

	public Vehicle getVehicleByAgentId(long agentId) throws AppException;

	public List<Vehicle> getVehicleList(Long type) throws AppException;

	public List<Vehicle> getValidVehicleList() throws AppException;
}
