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

import com.zhant.entregasGestor.dto.DeliveryDTO;
import com.zhant.entregasGestor.services.DeliveryService;

import jakarta.validation.Valid;

@Validated
@RestController
@RequestMapping("/api/deliveries")
public class DeliveryController {

	@Autowired
	DeliveryService deliveryService;
	
	@GetMapping
	public List<DeliveryDTO> findDeliveries(){
		return deliveryService.findAll();
	}
	@GetMapping("/{id}")
	public DeliveryDTO findDeliveriesById(@PathVariable int id) throws BadRequestException{
		return deliveryService.findById(id);
	}
	
	@GetMapping("/vehicle/{vehicleId}")
	public List<DeliveryDTO> findDeliveriesByVehicle(@PathVariable int vehicleId) throws BadRequestException{
		return deliveryService.findByVehicle(vehicleId);
	}
	
	@GetMapping("/courier/{courierId}")
	public List<DeliveryDTO> findDeliveriesByCourier(@PathVariable int courierId) throws BadRequestException{
		return deliveryService.findByCourier(courierId);
	}
	
	@GetMapping("/bairro/{bairro}")
	public List<DeliveryDTO> findDeliveriesByNeighborhood(@PathVariable String neighborhood){
		return deliveryService.findDeliveriesByNeighborhood(neighborhood);
	}
	
	@GetMapping("/nomeCliente/{nomeCliente}")
	public List<DeliveryDTO> findByNomeCliente(@PathVariable String custormerName){
		return deliveryService.findDeliveriesByCustomerName(custormerName);
	}
	
	@GetMapping("/nota/{nota}")
	public List<DeliveryDTO> findDeliveriesByNoteCode(@PathVariable int noteCode){
		return deliveryService.findDeliveriesByNoteCode(noteCode);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public DeliveryDTO create(@Valid @RequestBody DeliveryDTO delivery)  {
	    return deliveryService.create(delivery);
	}
	
	@PutMapping("/{id}")
	public DeliveryDTO update(@PathVariable int id, @Valid @RequestBody DeliveryDTO delivery) throws BadRequestException {
		return deliveryService.update(id, delivery);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable int id) throws BadRequestException {
		deliveryService.delete(id);
	}
	
}
