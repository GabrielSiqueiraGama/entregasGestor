package com.zhant.entregasGestor.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhant.entregasGestor.dto.EntregaDTO;
import com.zhant.entregasGestor.models.Entrega;
import com.zhant.entregasGestor.models.Entregador;
import com.zhant.entregasGestor.models.Veiculo;
import com.zhant.entregasGestor.repositories.EntregaRepository;

import jakarta.validation.Valid;

@Service
public class EntregaService {

	@Autowired
	private EntregaRepository entregaRepository;
	
	public List<EntregaDTO> findAll() {
		List<Entrega> allEntregas = entregaRepository.findAll();
		List<EntregaDTO> dtos = new ArrayList<EntregaDTO>(allEntregas.size());
		for(Entrega entrega: allEntregas) {
			EntregaDTO dto = new EntregaDTO(entrega.getId(),entrega.getData(), entrega.getNomeCliente(), 
					entrega.getBairro(), entrega.getValor(), entrega.getTroco(), entrega.isFragil(),
					entrega.getNota(),entrega.getStatus().toString(), entrega.getEntregador().getId(),
					entrega.getVeiculo().getId());
			dtos.add(dto);
		}
		
		return dtos;
	}
	public List<EntregaDTO> findByVeiculo(Veiculo veiculo){
		return entregaRepository.findByVeiculo(veiculo);
	}
	public List<EntregaDTO> findByEntregador(Entregador entregador){
		return entregaRepository.findByEntregador(entregador);
	}
	public List<EntregaDTO> findByBairro(String bairro){
		return entregaRepository.findByBairro(bairro);
	}
	public List<EntregaDTO> findByNomeCliente(String nomeCliente){
		return entregaRepository.findByNomeCliente(nomeCliente);
	}
	public List<EntregaDTO> findByNota(int nota){
		return entregaRepository.findByNota(nota);
	}
	
	public Entrega create(@Valid Entrega entrega) {
		return entregaRepository.save(entrega);
	}
	
	public Entrega update(int id,@Valid Entrega entrega) throws BadRequestException {
		return entregaRepository.findById(id).map(entregaFunction ->{
			entregaFunction.setData(entrega.getData());
			entregaFunction.setNomeCliente(entrega.getNomeCliente());
			entregaFunction.setBairro(entrega.getBairro());
			entregaFunction.setValor(entrega.getValor());
			entregaFunction.setTroco(entrega.getTroco());
			entregaFunction.setFragil(entrega.isFragil());
			entregaFunction.setNota(entrega.getNota());
			entregaFunction.setEntregador(entrega.getEntregador());
			entregaFunction.setVeiculo(entrega.getVeiculo());
			entregaFunction.setStatus(entrega.getStatus());
			return entregaRepository.save(entregaFunction);
		}).orElseThrow(()-> new BadRequestException("aaaaa"));
	}
	
	public void delete(int id) throws BadRequestException {
		entregaRepository.delete(entregaRepository.findById(id).orElseThrow(()-> new BadRequestException("Entrega não encontrada!")));
	}
	
}
