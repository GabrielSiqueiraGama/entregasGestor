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

import com.zhant.entregasGestor.dto.VehicleDTO;
import com.zhant.entregasGestor.services.VehicleService;

@Validated
@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

	@Autowired
	private VehicleService vehicleService;
	
	@GetMapping
	public List<VehicleDTO> findVehicles() {
		return vehicleService.findAll();
	}
	
	@GetMapping("/{id}")
	public VehicleDTO findVehicleById(@PathVariable int id) throws BadRequestException {
		return vehicleService.findById(id);
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public VehicleDTO create(@RequestBody VehicleDTO vehicle) {
		return vehicleService.create(vehicle);
	}
	
	@PutMapping("/{id}")
	public VehicleDTO update(@PathVariable int id, @RequestBody VehicleDTO vehicle) throws BadRequestException {
		return vehicleService.update(id, vehicle);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable int id) throws BadRequestException {
		vehicleService.delete(id);
	}
}
