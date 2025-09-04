package com.zhant.entregasGestor.controllers;

import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.zhant.entregasGestor.dto.EntregaDTO;
import com.zhant.entregasGestor.models.Entrega;
import com.zhant.entregasGestor.services.EntregaService;

import jakarta.validation.Valid;

@Validated
@RestController
@RequestMapping("/api/entrega")
public class EntregaController {

	@Autowired
	EntregaService entregaService;
	
	@GetMapping
	public List<EntregaDTO> findEntregas(){
		return entregaService.findAll();
	}
	@GetMapping("/{id}")
	public EntregaDTO findEntregasById(@PathVariable int id) throws BadRequestException{
		return entregaService.findById(id);
	}
	
	@GetMapping("/veiculo/{veiculoId}")
	public List<EntregaDTO> findEntregasByVeiculo(@PathVariable int veiculoId) throws BadRequestException{
		return entregaService.findByVeiculo(veiculoId);
	}
	
	@GetMapping("/entregador/{entregadorId}")
	public List<EntregaDTO> findByEntregador(@PathVariable int entregadorId) throws BadRequestException{
		return entregaService.findByEntregador(entregadorId);
	}
	
	@GetMapping("/bairro/{bairro}")
	public List<EntregaDTO> findByBairro(@PathVariable String bairro){
		return entregaService.findByBairro(bairro);
	}
	
	@GetMapping("/nomeCliente/{nomeCliente}")
	public List<EntregaDTO> findByNomeCliente(@PathVariable String nomeCliente){
		return entregaService.findByNomeCliente(nomeCliente);
	}
	
	@GetMapping("/nota/{nota}")
	public List<EntregaDTO> findByNota(@PathVariable int nota){
		return entregaService.findByNota(nota);
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public EntregaDTO create(@Valid @RequestBody Entrega entrega) {
		return entregaService.create(entrega);
	}
	
	@PutMapping("/{id}")
	public EntregaDTO update(@PathVariable int id, @Valid @RequestBody Entrega entrega) throws BadRequestException {
		return entregaService.update(id, entrega);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable int id) throws BadRequestException {
		entregaService.delete(id);
	}
	
}
