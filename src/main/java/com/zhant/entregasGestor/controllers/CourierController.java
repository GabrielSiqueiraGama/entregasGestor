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

import com.zhant.entregasGestor.dto.CourierDTO;
import com.zhant.entregasGestor.services.CourierService;

import jakarta.validation.Valid;

@Validated
@RestController
@RequestMapping("api/couriers")
public class CourierController {

	@Autowired
	private CourierService courierService;
	
	@GetMapping
	public List<CourierDTO> findCouriers(){
		return courierService.findAll();
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public CourierDTO create(@Valid @RequestBody CourierDTO courier) {
		return courierService.create(courier);
	}
	
	@GetMapping("/{id}")
	public CourierDTO findById(@PathVariable int id) throws BadRequestException {
		return courierService.findById(id);
	}
	
	@PutMapping("/{id}")
	public CourierDTO update(@PathVariable int id, @RequestBody CourierDTO courier) throws BadRequestException {
		return courierService.update(id, courier);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable int id) throws BadRequestException{
		courierService.delete(id);
	}
}
