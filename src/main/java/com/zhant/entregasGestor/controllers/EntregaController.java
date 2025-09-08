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
import com.zhant.entregasGestor.services.EntregaService;

import jakarta.validation.Valid;

@Validated
@RestController
@RequestMapping("/api/deliveries")
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
	
	@GetMapping("/vehicle/{vehicleId}")
	public List<EntregaDTO> findEntregasByVehicle(@PathVariable int vehicleId) throws BadRequestException{
		return entregaService.findByVehicle(vehicleId);
	}
	
	@GetMapping("/courier/{courierId}")
	public List<EntregaDTO> findByEntregador(@PathVariable int courierId) throws BadRequestException{
		return entregaService.findByCourier(courierId);
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
	@ResponseStatus(HttpStatus.CREATED)
	public EntregaDTO create(@Valid @RequestBody EntregaDTO entrega)  {
	    return entregaService.create(entrega);
	}
	
	@PutMapping("/{id}")
	public EntregaDTO update(@PathVariable int id, @Valid @RequestBody EntregaDTO entrega) throws BadRequestException {
		return entregaService.update(id, entrega);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable int id) throws BadRequestException {
		entregaService.delete(id);
	}
	
}
