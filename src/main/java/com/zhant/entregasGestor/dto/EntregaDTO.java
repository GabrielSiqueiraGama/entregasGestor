package com.zhant.entregasGestor.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zhant.entregasGestor.enums.StatusEntrega;
import com.zhant.entregasGestor.enums.validation.ValueOfEnum;

import jakarta.validation.constraints.NotBlank;

public record EntregaDTO(@JsonProperty("_id") int id,
		LocalDateTime data, @NotBlank String nomeCliente, 
		@NotBlank String bairro,
		@NotBlank String valor, @NotBlank  String troco, boolean fragil, int nota,
		@NotBlank @ValueOfEnum(enumClass = StatusEntrega.class) String status, int entregadorId, int veiculoId
		) {
	
}	