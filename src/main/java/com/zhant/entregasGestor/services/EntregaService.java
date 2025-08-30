package com.zhant.entregasGestor.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhant.entregasGestor.models.Entrega;
import com.zhant.entregasGestor.models.Entregador;
import com.zhant.entregasGestor.models.Veiculo;
import com.zhant.entregasGestor.repositories.EntregaRepository;

@Service
public class EntregaService {

	@Autowired
	private EntregaRepository entregaRepository;
	
	public List<Entrega>  findAll() {
		return entregaRepository.findAll();
	}
	
	public List<Entrega> findByVeiculo(Veiculo veiculo){
		return entregaRepository.findByVeiculo(veiculo);
	}
	
	public List<Entrega> findByEntregador(Entregador entregador){
		return entregaRepository.findByEntregador(entregador);
	}
	public List<Entrega> findByBairro(String bairro){
		return entregaRepository.findByBairro(bairro);
	}
	public List<Entrega> findByNomeCliente(String nomeCliente){
		return entregaRepository.findByNomeCliente(nomeCliente);
	}
	public List<Entrega> findByNota(int nota){
		return entregaRepository.findByNota(nota);
	}
	
}
