package com.zhant.entregasGestor.dto.mapper;

import org.springframework.stereotype.Component;

import com.zhant.entregasGestor.dto.EntregaDTO;
import com.zhant.entregasGestor.models.Entrega;

@Component
public class EntregaMapper {

	public EntregaDTO toDto(Entrega entrega) {
		return new EntregaDTO(entrega.getId(),entrega.getData(), entrega.getNomeCliente(), 
				entrega.getBairro(), entrega.getValor(), entrega.getTroco(), entrega.isFragil(),
				entrega.getNota(),entrega.getStatus().toString(), entrega.getEntregador().getId(),
				entrega.getVeiculo().getId());
	}
}
