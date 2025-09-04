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

import com.zhant.entregasGestor.dto.VeiculoDTO;
import com.zhant.entregasGestor.models.Veiculo;
import com.zhant.entregasGestor.services.VeiculoService;

@Validated
@RestController
@RequestMapping("/api/veiculo")
public class VeiculoController {

	@Autowired
	private VeiculoService veiculoService;
	
	@GetMapping
	public List<VeiculoDTO> findVeiculos() {
		return veiculoService.findAll();
	}
	
	@GetMapping("/{id}")
	public VeiculoDTO findVeiculoById(@PathVariable int id) throws BadRequestException {
		return veiculoService.findById(id);
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public VeiculoDTO create(@RequestBody Veiculo veiculo) {
		return veiculoService.create(veiculo);
	}
	
	@PutMapping("/{id}")
	public VeiculoDTO update(@PathVariable int id, @RequestBody Veiculo veiculo) throws BadRequestException {
		return veiculoService.update(id, veiculo);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable int id) throws BadRequestException {
		veiculoService.delete(id);
	}
}
