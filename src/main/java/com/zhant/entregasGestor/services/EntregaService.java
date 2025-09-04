package com.zhant.entregasGestor.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhant.entregasGestor.dto.EntregaDTO;
import com.zhant.entregasGestor.dto.mapper.EntregaMapper;
import com.zhant.entregasGestor.models.Entrega;
import com.zhant.entregasGestor.models.Entregador;
import com.zhant.entregasGestor.models.Veiculo;
import com.zhant.entregasGestor.repositories.EntregaRepository;
import com.zhant.entregasGestor.repositories.EntregadorRepository;
import com.zhant.entregasGestor.repositories.VeiculoRepository;

import jakarta.validation.Valid;

@Service
public class EntregaService {

	@Autowired
	private EntregaRepository entregaRepository;
	private EntregaMapper entregaMapper;
	private VeiculoRepository veiculoRepository;
	private EntregadorRepository entregadorRepository;
	
	public EntregaService(EntregaRepository entregaRepository, EntregaMapper entregaMapper, VeiculoRepository veiculoRepository, EntregadorRepository entregadorRepository) {
		this.entregaMapper = entregaMapper;
		this.entregaRepository = entregaRepository;
		this.veiculoRepository = veiculoRepository;
		this.entregadorRepository = entregadorRepository;
	}
	
	public List<EntregaDTO> findAll() {
		/*List<Entrega> allEntregas = entregaRepository.findAll();
		List<EntregaDTO> dtos = new ArrayList<EntregaDTO>(allEntregas.size());
		for(Entrega entrega: allEntregas) {
			EntregaDTO dto = new EntregaDTO(entrega.getId(),entrega.getData(), entrega.getNomeCliente(), 
					entrega.getBairro(), entrega.getValor(), entrega.getTroco(), entrega.isFragil(),
					entrega.getNota(),entrega.getStatus().toString(), entrega.getEntregador().getId(),
					entrega.getVeiculo().getId());
			dtos.add(dto);
		}
		return dtos;*/
		return entregaRepository.findAll().stream().map(entregaMapper::toDto).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
	}
	
	public EntregaDTO findById(int id) throws BadRequestException{
		return entregaRepository.findById(id).map(entregaMapper::toDto).orElseThrow(()-> new BadRequestException("Entrega não encontrada!"));
	}
	
	public List<EntregaDTO> findByVeiculo(int veiculoId) throws BadRequestException{
		Veiculo veiculo = veiculoRepository.findById(veiculoId).orElseThrow(()-> new BadRequestException("Veiculo não encontrado"));
		return entregaRepository.findByVeiculo(veiculo).stream().map(entregaMapper::toDto).toList();
	}
	
	public List<EntregaDTO> findByEntregador(int entregadorId) throws BadRequestException{
		Entregador entregador = entregadorRepository.findById(entregadorId).orElseThrow(()-> new BadRequestException("Entregador não encontrado"));
		return entregaRepository.findByEntregador(entregador).stream().map(entregaMapper::toDto).toList();
	}
	
	public List<EntregaDTO> findByBairro(String bairro){
		return entregaRepository.findByBairro(bairro).stream().map(entregaMapper::toDto).toList();
	}
	public List<EntregaDTO> findByNomeCliente(String nomeCliente){
		return entregaRepository.findByNomeCliente(nomeCliente).stream().map(entregaMapper::toDto).toList();
	}
	public List<EntregaDTO> findByNota(int nota){
		return entregaRepository.findByNota(nota).stream().map(entregaMapper::toDto).toList();
	}
	
	public EntregaDTO create(@Valid Entrega entrega) {
		return entregaMapper.toDto(entregaRepository.save(entrega));
	}
	
	public EntregaDTO update(int id,@Valid Entrega entrega) throws BadRequestException {
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
		}).map(entregaMapper::toDto).orElseThrow(()-> new BadRequestException("aaaaa"));
	}
	
	public void delete(int id) throws BadRequestException {
		 entregaRepository.delete(entregaRepository.findById(id).orElseThrow(()-> new BadRequestException("Entrega não encontrada!")));
	}
	
}
