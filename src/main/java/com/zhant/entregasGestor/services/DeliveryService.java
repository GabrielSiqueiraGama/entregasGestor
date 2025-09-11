package com.zhant.entregasGestor.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhant.entregasGestor.dto.DeliveryDTO;
import com.zhant.entregasGestor.dto.mapper.DeliveryMapper;
import com.zhant.entregasGestor.enums.DeliveryStatus;
import com.zhant.entregasGestor.models.Courier;
import com.zhant.entregasGestor.models.Vehicle;
import com.zhant.entregasGestor.repositories.DeliveryRepository;
import com.zhant.entregasGestor.repositories.CourierRepository;
import com.zhant.entregasGestor.repositories.VehicleRepository;

import jakarta.validation.Valid;

@Service
public class DeliveryService {

	@Autowired
	private DeliveryRepository deliveryRepository;
	private DeliveryMapper deliveryMapper;
	private VehicleRepository vehicleRepository;
	private CourierRepository courierRepository;
	
	public DeliveryService(DeliveryRepository deliveryRepository, DeliveryMapper deliveryMapper, VehicleRepository vehicleRepository, CourierRepository courierRepository) {
		this.deliveryMapper = deliveryMapper;
		this.deliveryRepository = deliveryRepository;
		this.vehicleRepository = vehicleRepository;
		this.courierRepository = courierRepository;
	}
	
	public List<DeliveryDTO> findAll() {
		/*List<Delivery> allDeliveries = deliveryRepository.findAll();
		List<DeliveryDTO> dtos = new ArrayList<DeliveryDTO>(allDelivery.size());
		for(Delivery delivery: allDeliveries) {
			DeliveryDTO dto = new deliveryDTO(delivery.getId(),delivery.getData(), delivery.getNomeCliente(), 
					delivery.getBairro(), delivery.getValor(), delivery.getTroco(), delivery.isFragil(),
					delivery.getNota(),delivery.getStatus().toString(), delivery.getCourier().getId(),
					delivery.getVehicle().getId());
			dtos.add(dto);
		}
		return dtos;*/
		return deliveryRepository.findAll().stream().map(deliveryMapper::toDto).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
	}
	
	public DeliveryDTO findById(int id) throws BadRequestException{
		return deliveryRepository.findById(id).map(deliveryMapper::toDto).orElseThrow(()-> new BadRequestException("Entrega não encontrada!"));
	}
	
	public List<DeliveryDTO> findByVehicle(int vehicleId) throws BadRequestException{
		Vehicle vehicle = vehicleRepository.findById(vehicleId).orElseThrow(()-> new BadRequestException("Veiculo não encontrado"));
		return deliveryRepository.findByVehicle(vehicle).stream().map(deliveryMapper::toDto).toList();
	}
	
	public List<DeliveryDTO> findByCourier(int courierId) throws BadRequestException{
		Courier courier = courierRepository.findById(courierId).orElseThrow(()-> new BadRequestException("Entregador não encontrado"));
		return deliveryRepository.findByCourier(courier).stream().map(deliveryMapper::toDto).toList();
	}
	
	public List<DeliveryDTO> findDeliveriesByNeighborhood(String neighborhood){
		return deliveryRepository.findByNeighborhood(neighborhood).stream().map(deliveryMapper::toDto).toList();
	}
	public List<DeliveryDTO> findDeliveriesByCustomerName(String customerName){
		return deliveryRepository.findByCustomerName(customerName).stream().map(deliveryMapper::toDto).toList();
	}
	public List<DeliveryDTO> findDeliveriesByNoteCode(int noteCode){
		return deliveryRepository.findByNoteCode(noteCode).stream().map(deliveryMapper::toDto).toList();
	}
	
	public DeliveryDTO create(@Valid DeliveryDTO delivery) {
	    return deliveryMapper.toDto(deliveryRepository.save(deliveryMapper.toEntity(delivery)));
	}
	
	public DeliveryDTO update(int id,@Valid DeliveryDTO delivery) throws BadRequestException {
        Courier courier = courierRepository.findById(delivery.courierId())
                .orElseThrow(() -> new BadRequestException("Entregador não encontrado"));
		Vehicle vehicle = vehicleRepository.findById(delivery.vehicleId())
		          .orElseThrow(() -> new BadRequestException("Veículo não encontrado"));

		return deliveryRepository.findById(id).map(deliveryFunction ->{
			deliveryFunction.setOrderDate(delivery.orderDate());
			deliveryFunction.setCustomerName(delivery.customerName());
			deliveryFunction.setNeighborhood(delivery.neighborhood());
			deliveryFunction.setAmount(delivery.amount());
			deliveryFunction.setCashChange (delivery.cashChange());
			deliveryFunction.setFragile(delivery.fragile());
			deliveryFunction.setNoteCode(delivery.noteCode());
			deliveryFunction.setCourier(courier);
			deliveryFunction.setVehicle(vehicle);
			deliveryFunction.setStatus(DeliveryStatus.valueOf(delivery.status()));
			return deliveryRepository.save(deliveryFunction);
		}).map(deliveryMapper::toDto).orElseThrow(()-> new BadRequestException("aaaaa"));
	}
	
	public void delete(int id) throws BadRequestException {
		deliveryRepository.delete(deliveryRepository.findById(id).orElseThrow(()-> new BadRequestException("Entrega não encontrada!")));
	}
	
}
