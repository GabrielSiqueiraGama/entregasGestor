package com.zhant.entregasGestor.dto.mapper;

import org.springframework.stereotype.Component;

import com.zhant.entregasGestor.dto.VehicleDTO;
import com.zhant.entregasGestor.models.Vehicle;

@Component
public class VehicleMapper {

	public VehicleDTO toDto(Vehicle vehicle) {
		return new VehicleDTO(vehicle.getId(), vehicle.getName());
	}
	
	public Vehicle toEntity(VehicleDTO vehicleDTO) {

		Vehicle vehicle = new Vehicle();
		vehicle.setName(vehicleDTO.name());
		return vehicle;
		
		//search about builder patterns
	}
}
