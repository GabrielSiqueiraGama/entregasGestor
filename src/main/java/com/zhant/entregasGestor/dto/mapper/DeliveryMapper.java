package com.zhant.entregasGestor.dto.mapper;

import org.springframework.stereotype.Component;

import com.zhant.entregasGestor.dto.DeliveryDTO;
import com.zhant.entregasGestor.enums.DeliveryStatus;
import com.zhant.entregasGestor.models.Delivery;
import com.zhant.entregasGestor.models.Courier;
import com.zhant.entregasGestor.models.Vehicle;
import com.zhant.entregasGestor.repositories.CourierRepository;
import com.zhant.entregasGestor.repositories.VehicleRepository;

import jakarta.persistence.EntityNotFoundException;

@Component
public class DeliveryMapper {
    private final CourierRepository courierRepository;
    private final VehicleRepository vehicleRepository;

    public DeliveryMapper(CourierRepository courierRepository, VehicleRepository vehicleRepository) {
        this.courierRepository = courierRepository;
        this.vehicleRepository = vehicleRepository;
    }

	public DeliveryDTO toDto(Delivery delivery) {
	    int courierId = delivery.getCourier() != null ? delivery.getCourier().getId() : 0;
	    int vehicleId = delivery.getVehicle() != null ? delivery.getVehicle().getId() : 0;
	    
		return new DeliveryDTO(delivery.getId(),delivery.getOrderDate(), delivery.getCustomerName(), 
				delivery.getNeighborhood(), delivery.getAmount(), delivery.getCashChange(), delivery.isFragile(),
				delivery.getNoteCode(),delivery.getStatus().toString(), courierId, vehicleId);
	}
	
	public Delivery toEntity(DeliveryDTO deliveryDTO) {
        Courier courier = courierRepository.findById(deliveryDTO.courierId())
                .orElseThrow(() -> new EntityNotFoundException("Entregador não encontrado"));

        Vehicle vehicle = vehicleRepository.findById(deliveryDTO.vehicleId())
                .orElseThrow(() -> new EntityNotFoundException("Veiculo não encontrado"));

		Delivery delivery = new Delivery();
		
		delivery.setId(deliveryDTO.id());
		delivery.setOrderDate(deliveryDTO.orderDate());
		delivery.setCustomerName(deliveryDTO.customerName());
		delivery.setNeighborhood(deliveryDTO.neighborhood());
		delivery.setAmount(deliveryDTO.amount());
		delivery.setCashChange (deliveryDTO.cashChange());
		delivery.setFragile(deliveryDTO.fragile());
		delivery.setNoteCode(deliveryDTO.noteCode());
		delivery.setStatus(DeliveryStatus.valueOf(deliveryDTO.status()));
		delivery.setCourier(courier);
		delivery.setVehicle(vehicle);
		
		return delivery;
		
		//search about builder patterns
	}
}
