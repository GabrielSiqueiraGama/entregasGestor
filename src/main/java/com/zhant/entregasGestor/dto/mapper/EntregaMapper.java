package com.zhant.entregasGestor.dto.mapper;

import org.springframework.stereotype.Component;

import com.zhant.entregasGestor.dto.EntregaDTO;
import com.zhant.entregasGestor.enums.StatusEntrega;
import com.zhant.entregasGestor.models.Entrega;
import com.zhant.entregasGestor.models.Courier;
import com.zhant.entregasGestor.models.Vehicle;
import com.zhant.entregasGestor.repositories.CourierRepository;
import com.zhant.entregasGestor.repositories.VehicleRepository;

import jakarta.persistence.EntityNotFoundException;

@Component
public class EntregaMapper {
    private final CourierRepository courierRepository;
    private final VehicleRepository vehicleRepository;

    public EntregaMapper(CourierRepository courierRepository, VehicleRepository vehicleRepository) {
        this.courierRepository = courierRepository;
        this.vehicleRepository = vehicleRepository;
    }

	public EntregaDTO toDto(Entrega entrega) {
	    int courierId = entrega.getCourier() != null ? entrega.getCourier().getId() : 0;
	    int vehicleId = entrega.getVehicle() != null ? entrega.getVehicle().getId() : 0;
	    
		return new EntregaDTO(entrega.getId(),entrega.getData(), entrega.getNomeCliente(), 
				entrega.getBairro(), entrega.getValor(), entrega.getTroco(), entrega.isFragil(),
				entrega.getNota(),entrega.getStatus().toString(), courierId, vehicleId);
	}
	
	public Entrega toEntity(EntregaDTO entregaDTO) {
        Courier courier = courierRepository.findById(entregaDTO.courierId())
                .orElseThrow(() -> new EntityNotFoundException("Entregador não encontrado"));

        Vehicle vehicle = vehicleRepository.findById(entregaDTO.vehicleId())
                .orElseThrow(() -> new EntityNotFoundException("Veiculo não encontrado"));

		Entrega entrega = new Entrega();
		
		entrega.setId(entregaDTO.id());
		entrega.setData(entregaDTO.data());
		entrega.setNomeCliente(entregaDTO.nomeCliente());
		entrega.setBairro(entregaDTO.bairro());
		entrega.setValor(entregaDTO.valor());
		entrega.setTroco(entregaDTO.troco());
		entrega.setFragil(entregaDTO.fragil());
		entrega.setNota(entregaDTO.nota());
		entrega.setStatus(StatusEntrega.valueOf(entregaDTO.status()));
		entrega.setCourier(courier);
		entrega.setVehicle(vehicle);
		
		return entrega;
		
		//search about builder patterns
	}
}
