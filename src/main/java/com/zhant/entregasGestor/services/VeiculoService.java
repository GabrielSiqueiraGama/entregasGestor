package com.zhant.entregasGestor.services;

import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhant.entregasGestor.dto.VeiculoDTO;
import com.zhant.entregasGestor.dto.mapper.VeiculoMapper;
import com.zhant.entregasGestor.models.Veiculo;
import com.zhant.entregasGestor.repositories.VeiculoRepository;

@Service
public class VeiculoService {

	@Autowired
	private VeiculoRepository veiculoRepository;
	private VeiculoMapper veiculoMapper;
	
	public VeiculoService(VeiculoRepository veiculoRepository, VeiculoMapper veiculoMapper ) {
		this.veiculoRepository = veiculoRepository;
		this.veiculoMapper = veiculoMapper;
	}
	
	
	public List<VeiculoDTO> findAll(){
		return veiculoRepository.findAll().stream().map(veiculoMapper::toDto).toList();
	}
	
	public VeiculoDTO findById(int id) throws BadRequestException {
		return veiculoRepository.findById(id).map(veiculoMapper::toDto).orElseThrow(()-> new BadRequestException("Veiculo não encontrado"));
	}
	
	public VeiculoDTO update(int id, Veiculo veiculo) throws BadRequestException {
		return veiculoRepository.findById(id).map(veiculoFunction ->{
			veiculoFunction.setNome(veiculo.getNome());
			return veiculoRepository.save(veiculoFunction);
		}).map(veiculoMapper::toDto).orElseThrow(()-> new BadRequestException("Error ao editar"));
	}
	
	public VeiculoDTO create(Veiculo veiculo) {
		return veiculoMapper.toDto(veiculoRepository.save(veiculo));
	}
	
	
	public void delete(int id) throws BadRequestException {
		veiculoRepository.delete(veiculoRepository.findById(id).orElseThrow(()-> new BadRequestException("Entrega não encontrada!")));
	}
}
