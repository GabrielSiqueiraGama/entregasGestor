package com.zhant.entregasGestor.dto.mapper;

import org.springframework.stereotype.Component;

import com.zhant.entregasGestor.dto.CourierDTO;
import com.zhant.entregasGestor.models.Courier;

@Component
public class CourierMapper {

	public CourierDTO toDto(Courier courier) {
		return new CourierDTO(courier.getId(), courier.getName());
	}
	
	public Courier toEntity(CourierDTO courierDTO) {

		Courier courier = new Courier();
		courier.setName(courierDTO.name());
		return courier;
		
		//search about builder patterns
	}
}
