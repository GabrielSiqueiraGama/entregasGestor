package com.zhant.entregasGestor.controllers;

import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.zhant.entregasGestor.dto.EntregaDTO;
import com.zhant.entregasGestor.models.Entrega;
import com.zhant.entregasGestor.services.EntregaService;

import jakarta.validation.Valid;

public class EntregaController {

	@Autowired
	EntregaService entregaService;
	
	@GetMapping
	public List<EntregaDTO> findEntregas(){
		return entregaService.findAll();
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Entrega create(@Valid @RequestBody Entrega entrega) {
		return entregaService.create(entrega);
	}
	
	@PutMapping("/{id}")
	public Entrega update(@Valid @RequestBody Entrega entrega, int id) throws BadRequestException {
		return entregaService.update(id, entrega);
	}
	
	@DeleteMapping
	public void delete(@Valid @RequestBody Entrega entrega, int id) throws BadRequestException {
		entregaService.delete(id);
	}
	
}
