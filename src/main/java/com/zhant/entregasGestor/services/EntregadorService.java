package com.zhant.entregasGestor.services;

import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhant.entregasGestor.dto.EntregadorDTO;
import com.zhant.entregasGestor.dto.mapper.EntregadorMapper;
import com.zhant.entregasGestor.repositories.EntregadorRepository;

@Service
public class EntregadorService {

	@Autowired
	private EntregadorRepository entregadorRepository;
	private EntregadorMapper entregadorMapper;
	
	public EntregadorService(EntregadorRepository entregadorRepository, EntregadorMapper entregadorMapper) {
		this.entregadorRepository = entregadorRepository;
		this.entregadorMapper = entregadorMapper;
	}
	
	public List<EntregadorDTO> findAll() {
		return entregadorRepository.findAll().stream().map(entregadorMapper::toDto).toList();
	}
	
	public EntregadorDTO create(EntregadorDTO entregador) {
		return entregadorMapper.toDto(entregadorRepository.save(entregadorMapper.toEntity(entregador)));
	}
	
	public EntregadorDTO findById(int id) throws BadRequestException {
		return entregadorRepository.findById(id).map(entregadorMapper::toDto).orElseThrow(()-> new BadRequestException("Entregador não encontrado"));
	}
	
	public EntregadorDTO update(int id, EntregadorDTO entregador) throws BadRequestException {
		return entregadorRepository.findById(id).map(entregadorFunction->{
			entregadorFunction.setNome(entregador.nome());
			return entregadorRepository.save(entregadorFunction);
		}).map(entregadorMapper::toDto).orElseThrow(()-> new BadRequestException("Entregador não encontrado"));
	}
	
	public void delete(int id) throws BadRequestException {
		entregadorRepository.delete(entregadorRepository.findById(id).orElseThrow(()-> new BadRequestException("Entregador não encontrado")));
	}
}
