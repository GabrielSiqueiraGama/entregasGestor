package com.zhant.entregasGestor.dto.mapper;

import org.springframework.stereotype.Component;

import com.zhant.entregasGestor.dto.EntregaDTO;
import com.zhant.entregasGestor.enums.StatusEntrega;
import com.zhant.entregasGestor.models.Entrega;
import com.zhant.entregasGestor.models.Entregador;
import com.zhant.entregasGestor.models.Veiculo;
import com.zhant.entregasGestor.repositories.EntregadorRepository;
import com.zhant.entregasGestor.repositories.VeiculoRepository;

import jakarta.persistence.EntityNotFoundException;

@Component
public class EntregaMapper {
    private final EntregadorRepository entregadorRepository;
    private final VeiculoRepository veiculoRepository;

    public EntregaMapper(EntregadorRepository entregadorRepository, VeiculoRepository veiculoRepository) {
        this.entregadorRepository = entregadorRepository;
        this.veiculoRepository = veiculoRepository;
    }

	public EntregaDTO toDto(Entrega entrega) {
	    int entregadorId = entrega.getEntregador() != null ? entrega.getEntregador().getId() : 0;
	    int veiculoId = entrega.getVeiculo() != null ? entrega.getVeiculo().getId() : 0;
	    
		return new EntregaDTO(entrega.getId(),entrega.getData(), entrega.getNomeCliente(), 
				entrega.getBairro(), entrega.getValor(), entrega.getTroco(), entrega.isFragil(),
				entrega.getNota(),entrega.getStatus().toString(), entregadorId, veiculoId);
	}
	
	public Entrega toEntity(EntregaDTO entregaDTO) {
        Entregador entregador = entregadorRepository.findById(entregaDTO.entregadorId())
                .orElseThrow(() -> new EntityNotFoundException("Entregador não encontrado"));

        Veiculo veiculo = veiculoRepository.findById(entregaDTO.veiculoId())
                .orElseThrow(() -> new EntityNotFoundException("Veiculo não encontrado"));

		Entrega entrega = new Entrega();
		
		entrega.setId(entregaDTO.id());
		entrega.setData(entregaDTO.data());
		entrega.setNomeCliente(entregaDTO.nomeCliente());
		entrega.setBairro(entregaDTO.bairro());
		entrega.setValor(entregaDTO.valor());
		entrega.setTroco(entregaDTO.troco());
		entrega.setFragil(entregaDTO.fragil());
		entrega.setNota(entregaDTO.nota());
		entrega.setStatus(StatusEntrega.valueOf(entregaDTO.status()));
		entrega.setEntregador(entregador);
		entrega.setVeiculo(veiculo);
		
		return entrega;
		
		//search about builder patterns
	}
}
