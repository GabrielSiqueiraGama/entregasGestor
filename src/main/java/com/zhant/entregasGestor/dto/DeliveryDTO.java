package com.zhant.entregasGestor.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zhant.entregasGestor.enums.DeliveryStatus;
import com.zhant.entregasGestor.enums.validation.ValueOfEnum;

import jakarta.validation.constraints.NotBlank;

public record DeliveryDTO(@JsonProperty("_id") int id,
		LocalDateTime orderDate, @NotBlank String customerName, 
		@NotBlank String neighborhood,
		@NotBlank String amount, @NotBlank  String cashChange, boolean fragile, int noteCode,
		@NotBlank @ValueOfEnum(enumClass = DeliveryStatus.class) String status, int courierId, int vehicleId
		) {
	
}	