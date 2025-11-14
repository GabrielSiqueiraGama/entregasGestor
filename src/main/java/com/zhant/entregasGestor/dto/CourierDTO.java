package com.zhant.entregasGestor.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record CourierDTO(@JsonProperty("_id") int id,@Schema(example = "Cleberson") @NotBlank String name) {
}
