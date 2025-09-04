package com.zhant.entregasGestor.dto.mapper;

import org.springframework.stereotype.Component;

import com.zhant.entregasGestor.dto.VeiculoDTO;
import com.zhant.entregasGestor.models.Veiculo;

@Component
public class VeiculoMapper {

	public VeiculoDTO toDto(Veiculo veiculo) {
		return new VeiculoDTO(veiculo.getId(), veiculo.getNome());
	}
	
	public Veiculo toEntity(VeiculoDTO veiculoDTO) {

		Veiculo veiculo = new Veiculo();
		veiculo.setNome(veiculoDTO.nome());
		return veiculo;
		
		//search about builder patterns
	}
}
