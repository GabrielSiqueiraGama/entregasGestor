package com.zhant.entregasGestor.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;

public record CourierDTO(@JsonProperty("_id") int id,@NotBlank String name) {
}
