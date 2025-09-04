package com.zhant.entregasGestor.dto.mapper;

import org.springframework.stereotype.Component;

import com.zhant.entregasGestor.dto.EntregadorDTO;
import com.zhant.entregasGestor.models.Entregador;

@Component
public class EntregadorMapper {

	public EntregadorDTO toDto(Entregador entregador) {
		return new EntregadorDTO(entregador.getId(), entregador.getNome());
	}
	
	public Entregador toEntity(EntregadorDTO entregadorDTO) {

		Entregador entregador = new Entregador();
		entregador.setNome(entregadorDTO.nome());
		return entregador;
		
		//search about builder patterns
	}
}
