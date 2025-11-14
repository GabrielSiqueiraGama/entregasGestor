package com.zhant.entregasGestor.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zhant.entregasGestor.enums.DeliveryStatus;
import com.zhant.entregasGestor.enums.validation.ValueOfEnum;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record DeliveryDTO(@JsonProperty("_id") int id,
                          @Schema(example = "2025-01-15T14:30:00") LocalDateTime orderDate, @Schema(example = "Zhant") @NotBlank String customerName,
                          @Schema(example = "Catumbi") @NotBlank String neighborhood,
                          @Schema(example = "75,00") @NotBlank String amount, @NotBlank  @Schema(example = "25,00") String cashChange, @Schema(example = "true") boolean fragile, @Schema(example = "10")int noteCode,
                          @Schema(example = "EM_ANDAMENTO", allowableValues = {"EM_ANDAMENTO", "FINALIZADA"}) @NotBlank @ValueOfEnum(enumClass = DeliveryStatus.class) String status, @Schema(example = "0") int courierId, @Schema(example = "0") int vehicleId
		) {
	
}	