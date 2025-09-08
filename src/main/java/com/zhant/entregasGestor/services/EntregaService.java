package com.zhant.entregasGestor.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhant.entregasGestor.dto.EntregaDTO;
import com.zhant.entregasGestor.dto.mapper.EntregaMapper;
import com.zhant.entregasGestor.enums.StatusEntrega;
import com.zhant.entregasGestor.models.Courier;
import com.zhant.entregasGestor.models.Vehicle;
import com.zhant.entregasGestor.repositories.EntregaRepository;
import com.zhant.entregasGestor.repositories.CourierRepository;
import com.zhant.entregasGestor.repositories.VehicleRepository;

import jakarta.validation.Valid;

@Service
public class EntregaService {

	@Autowired
	private EntregaRepository entregaRepository;
	private EntregaMapper entregaMapper;
	private VehicleRepository vehicleRepository;
	private CourierRepository courierRepository;
	
	public EntregaService(EntregaRepository entregaRepository, EntregaMapper entregaMapper, VehicleRepository vehicleRepository, CourierRepository courierRepository) {
		this.entregaMapper = entregaMapper;
		this.entregaRepository = entregaRepository;
		this.vehicleRepository = vehicleRepository;
		this.courierRepository = courierRepository;
	}
	
	public List<EntregaDTO> findAll() {
		/*List<Entrega> allEntregas = entregaRepository.findAll();
		List<EntregaDTO> dtos = new ArrayList<EntregaDTO>(allEntregas.size());
		for(Entrega entrega: allEntregas) {
			EntregaDTO dto = new EntregaDTO(entrega.getId(),entrega.getData(), entrega.getNomeCliente(), 
					entrega.getBairro(), entrega.getValor(), entrega.getTroco(), entrega.isFragil(),
					entrega.getNota(),entrega.getStatus().toString(), entrega.getCourier().getId(),
					entrega.getVehicle().getId());
			dtos.add(dto);
		}
		return dtos;*/
		return entregaRepository.findAll().stream().map(entregaMapper::toDto).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
	}
	
	public EntregaDTO findById(int id) throws BadRequestException{
		return entregaRepository.findById(id).map(entregaMapper::toDto).orElseThrow(()-> new BadRequestException("Entrega não encontrada!"));
	}
	
	public List<EntregaDTO> findByVehicle(int vehicleId) throws BadRequestException{
		Vehicle vehicle = vehicleRepository.findById(vehicleId).orElseThrow(()-> new BadRequestException("Veiculo não encontrado"));
		return entregaRepository.findByVehicle(vehicle).stream().map(entregaMapper::toDto).toList();
	}
	
	public List<EntregaDTO> findByCourier(int courierId) throws BadRequestException{
		Courier courier = courierRepository.findById(courierId).orElseThrow(()-> new BadRequestException("Entregador não encontrado"));
		return entregaRepository.findByCourier(courier).stream().map(entregaMapper::toDto).toList();
	}
	
	public List<EntregaDTO> findByBairro(String bairro){
		return entregaRepository.findByBairro(bairro).stream().map(entregaMapper::toDto).toList();
	}
	public List<EntregaDTO> findByNomeCliente(String nomeCliente){
		return entregaRepository.findByNomeCliente(nomeCliente).stream().map(entregaMapper::toDto).toList();
	}
	public List<EntregaDTO> findByNota(int nota){
		return entregaRepository.findByNota(nota).stream().map(entregaMapper::toDto).toList();
	}
	
	public EntregaDTO create(@Valid EntregaDTO entrega) {
	    return entregaMapper.toDto(entregaRepository.save(entregaMapper.toEntity(entrega)));
	}
	
	public EntregaDTO update(int id,@Valid EntregaDTO entrega) throws BadRequestException {
        Courier courier = courierRepository.findById(entrega.courierId())
                .orElseThrow(() -> new BadRequestException("Entregador não encontrado"));
		Vehicle vehicle = vehicleRepository.findById(entrega.vehicleId())
		          .orElseThrow(() -> new BadRequestException("Veículo não encontrado"));

		return entregaRepository.findById(id).map(entregaFunction ->{
			entregaFunction.setData(entrega.data());
			entregaFunction.setNomeCliente(entrega.nomeCliente());
			entregaFunction.setBairro(entrega.bairro());
			entregaFunction.setValor(entrega.valor());
			entregaFunction.setTroco(entrega.troco());
			entregaFunction.setFragil(entrega.fragil());
			entregaFunction.setNota(entrega.nota());
			entregaFunction.setCourier(courier);
			entregaFunction.setVehicle(vehicle);
			entregaFunction.setStatus(StatusEntrega.valueOf(entrega.status()));
			return entregaRepository.save(entregaFunction);
		}).map(entregaMapper::toDto).orElseThrow(()-> new BadRequestException("aaaaa"));
	}
	
	public void delete(int id) throws BadRequestException {
		 entregaRepository.delete(entregaRepository.findById(id).orElseThrow(()-> new BadRequestException("Entrega não encontrada!")));
	}
	
}
