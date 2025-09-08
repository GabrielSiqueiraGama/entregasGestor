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

	public DeliveryDTO toDto(Delivery entrega) {
	    int courierId = entrega.getCourier() != null ? entrega.getCourier().getId() : 0;
	    int vehicleId = entrega.getVehicle() != null ? entrega.getVehicle().getId() : 0;
	    
		return new DeliveryDTO(entrega.getId(),entrega.getOrderDate(), entrega.getCustomerName(), 
				entrega.getNeighborhood(), entrega.getAmount(), entrega.getChange(), entrega.isFragile(),
				entrega.getNoteCode(),entrega.getStatus().toString(), courierId, vehicleId);
	}
	
	public Delivery toEntity(DeliveryDTO entregaDTO) {
        Courier courier = courierRepository.findById(entregaDTO.courierId())
                .orElseThrow(() -> new EntityNotFoundException("Entregador não encontrado"));

        Vehicle vehicle = vehicleRepository.findById(entregaDTO.vehicleId())
                .orElseThrow(() -> new EntityNotFoundException("Veiculo não encontrado"));

		Delivery entrega = new Delivery();
		
		entrega.setId(entregaDTO.id());
		entrega.setOrderDate(entregaDTO.orderDate());
		entrega.setCustomerName(entregaDTO.customerName());
		entrega.setNeighborhood(entregaDTO.neighborhood());
		entrega.setAmount(entregaDTO.amount());
		entrega.setChange(entregaDTO.change());
		entrega.setFragile(entregaDTO.fragile());
		entrega.setNoteCode(entregaDTO.noteCode());
		entrega.setStatus(DeliveryStatus.valueOf(entregaDTO.status()));
		entrega.setCourier(courier);
		entrega.setVehicle(vehicle);
		
		return entrega;
		
		//search about builder patterns
	}
}
