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

import com.zhant.entregasGestor.dto.EntregadorDTO;
import com.zhant.entregasGestor.models.Entregador;
import com.zhant.entregasGestor.services.EntregadorService;

import jakarta.validation.Valid;

@Validated
@RestController
@RequestMapping("api/entregadores")
public class EntregadorController {

	@Autowired
	private EntregadorService entregadorService;
	
	@GetMapping
	public List<EntregadorDTO> findEntregadores(){
		return entregadorService.findAll();
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public EntregadorDTO create(@Valid @RequestBody Entregador entregador) {
		return entregadorService.create(entregador);
	}
	
	@GetMapping("/{id}")
	public EntregadorDTO findById(@PathVariable int id) throws BadRequestException {
		return entregadorService.findById(id);
	}
	
	@PutMapping("/{id}")
	public EntregadorDTO update(@PathVariable int id, @RequestBody Entregador entregador) throws BadRequestException {
		return entregadorService.update(id, entregador);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable int id) throws BadRequestException{
		entregadorService.delete(id);
	}
}
